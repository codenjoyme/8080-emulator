package spec;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static spec.Constants.SCREEN;
import static spec.IOPorts.RgRGB;

public class Video {

    // Screen stuff - метрики экрана
    public static final int pixelScale = 1;    // scales pixels in main screen, not border

    public static final int nPixelsWide = 384;  // точек по X
    public static final int nPixelsHigh = 256;  // точек по Y

    public static final int nCharsWide = 48;   // 256/8=32 (Байт в строке)
    public static final int nCharsHigh = 256;   // 192/8=24 строки в экране

    private static final int str = 238;          // насыщенность (saturation) 0EEh
    public static final Color[] SpecMXColors = { // 16 цветов - массив цветов "Специалист"
            Color.black,             // 00-черный
            // тусклые цвета
            Color.blue,              // 01 синий
            Color.green,             // 02 зелёный
            Color.cyan,              // 03 бирюзовый
            Color.red,               // 04 красный
            Color.magenta,           // 05 фиолетовый
            Color.yellow,            // 06 коричневый
            Color.white,             // 07 белый
            new Color(0, 0, 0),      // 08 серый
            // яркие цвета
            new Color(0, 0, str),    // 09 голубой
            new Color(0, str, 0),    // 0A светло-зелёный
            new Color(0, str, str),  // 0B светло-бирюзовый
            new Color(str, 0, 0),    // 0C розовый
            new Color(str, 0, str),  // 0D светло-фиолетовый
            new Color(str, str, 0),  // 0E желтый
            new Color(str, str, str) // 0F ярко-белый
    };

    // 4000h = 16384 - начало экрана;  6144 байт (1800h) - размер экрана
    // 32 байта в сроке * 192 строки = 1800 смещение 1 аттрибута = 5800h-4000h (22528)
    private static final int firstAttr = (nPixelsHigh * nCharsWide); // 768 байт аттрибутов(300h)

    // адрес 1 аттрибута + 24 строки * по 32 символа = адрес последнего аттрибута
    // 5B00h (23296) - конец аттрибутов               24 строки  32 символа

    // first screen line in linked list to be redrawn
    //         первая строка экрана в списке указателей, для перерисовки
    private int first = -1;

    // first attribute in linked list to be redrawn
    //         первый атрибут в списке указателей, для перерисовки
    private int FIRST = -1;

    // массив БАЙТ ЭКРАНА[]    192 точек по Y + 24 строки * 32 Байт в строке
    private final int[] lastByte = new int[(nPixelsHigh + nCharsHigh) * nCharsWide];

    // 192 точек по Y * 32 Байт в строке = 6144 байта в области экрана
    // 24 строки символов * 32 Байт в строке = 768 байт аттрибутов(300h)
    // 6144 байта в области экрана + 768 байт аттрибутов = 6912 в буфере

    //  массив смещений [] = 6912 в буфере - ФАКТИЧЕСКИ АДРЕСА В ЭКРАНЕ
    private final int[] nextAddr = new int[(nPixelsHigh + nCharsHigh) * nCharsWide];

    public static Image[] imageMap = new Image[1 << 12];// 100000000000b = 2048d = 800h
    // 7 bits for attr, 4 bits for pattern = 11 bits > 2^11=2048 элементов
    // "Специалист": 8 bits for attr, 4 bits for pattern = 12 bits > 2^12=4096 элементов

    // массив байт цвета контроллера цвета 0x9000-9FFFh
    private int[] rgb = new int[0x3000];

    private Memory memory;
    private BiFunction<Integer, Integer, Image> imageCreator;
    private BiConsumer<Image, Point> drawer;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Video(Memory memory,
                 BiFunction<Integer, Integer, Image> imageCreator,
                 BiConsumer<Image, Point> drawer)
    {
        this.memory = memory;
        this.imageCreator = imageCreator;
        this.drawer = drawer;
    }

    // стартовый refresh экрана
    public void refresh() {
        // от  0  до  5800Н
        for (int i = 0; i < firstAttr; i++) {
            nextAddr[i] = i - 1; // ВСЕ смещения в области экрана
            lastByte[i] = (~memory.read8(i + SCREEN.begin())) & 0xFF; // ВСЕ инвертированные байты из видео-ОЗУ.
        }
        first = firstAttr - 1; // последнее смещение байта буфера экрана = 57FFH  != -1 !!!

        // по области аттрибутов
        for (int i = 0; i < firstAttr; i++) { // у "Специалист" область аттрибутов отдельная.
            nextAddr[i + firstAttr] = -1; // во все адреса аттрибутов заносим -1.
            // буфер аттрибутов
            lastByte[i + firstAttr] = rgb[i];
        }
        FIRST = -1; // признак обновления
    }

    // запись в видео-ОЗУ
    public void plot(int addr) {
        int offset = addr - SCREEN.begin();  // смещение в видео-ОЗУ: (адрес в ОЗУ) - 4000h

        if (nextAddr[offset] == -1) { // если по ЭТОМУ адресу в видео-ОЗУ есть признак ОБНОВИТЬ,
            rgb[offset] = memory.read8(RgRGB); // по смещению видео-ОЗУ пишем код в ОЗУ цвета
            if (offset < firstAttr) {  // если ЭТОТ адрес в видео-ОЗУ, а не в аттрибутах,
                nextAddr[offset] = first;// указали в буфере смещений по ЭТОМУ адресу: first
                first = offset;        // first присвоили значение ЭТОГО адреса смещения в экране
            } else {                   // адрес в ОЗУ аттрибутов:
                nextAddr[offset] = FIRST;
                FIRST = offset; // значит обработается в screenPaint()
            }
        }
    }

    // Отрисовка экрана в буфере
    // Основная идея - как можно меньше РИСОВАТЬ, для этого отслеживаются изменения и
    // ОТРИСОВЫВАЮТСЯ только они.
    public void screenPaint() { // вызывается из interrupt()
        int addr = FIRST; // часто = -1 -> не обновляем...
        // в первый заход FIRST = -1 => цикл  while( addr >= 0 ) пропускаем.
        // сначала изменим все аттрибуты цвета
        // Update attribute affected pixels
        while (addr >= 0) { // если FIRST = -1 то и не делаем ничего.
            int newAttr = rgb[addr]; // байт из ОЗУ аттриб.по смещению "FIRST"
            lastByte[addr + firstAttr] = newAttr; // байт из буфера байтов заменим байтом из ОЗУ аттриб.

            int newAddr = nextAddr[addr];
            nextAddr[addr] = -1;
            addr = newAddr;
        }
        FIRST = -1;

        // Only update screen if necessary
        if (first < 0) {
            return;
        }
        // далее изменим все нужные пиксели экрана
        // Update affected pixels
        addr = first;  // в первый заход first = адресу последнего байта экрана...
        while (addr >= 0) {
            int newPixels = memory.read8(addr + SCREEN.begin()); // байт из видео-ОЗУ по смещению "first"

            lastByte[addr] = newPixels; // в буфер байтов по смещению "first" запишем -
            // байт из видео-ОЗУ по смещению "first"
            //
            // экранный адрес точки Специалист вычисляется по адресу памяти из след.соотношения:
            // если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
            //                  CS1CS0 X8 X7 X6 X5 X4 X3|Y7 Y6 Y5 Y4 Y3 Y2 Y1 Y0
            // где X2...X0 - координаты бита в байте;
            //     X7...X3 - координаты байта в строке: 2^6=64 возможных байта в строке,
            //               из которых отображаем только 48 байт: 00H - 2Fh.
            //     Y7...Y0 - координаты строки: 2^8=256, (00h — верх, 0FFh=255 — низ);
            //    CS1 -CS0 - область памяти, где расположен экран: 9000h - текущий.
            //

            // выделим из адреса памяти координату X = А4...А0
            //
            //    int x = ((addr & 0x1f) << 3); // сдвиг на 3 = * 8 (т.к. x2...x0 - адрес бита в байте)
            //
            int x = ((addr & 0x3F00) >> 5); // сдвиг на 5 (т.к. x2...x0 - адрес бита в байте)
            //                    0011.1111.0000.0000
            //                    0000.0001.1111.1000

            // выделим из адреса памяти координату Y:
            //
            //    int y = (((int)(addr&0x00e0))>>2)+(((int)(addr&0x0700))>>8)+(((int)(addr&0x1800))>>5);
            //                0000.0000.1110.0000 >>2 = 0000.0000.0011.1000 = 0000.0000.0054.3000
            //                0000.0111.0000.0000 >>8 = 0000.0000.0000.0111 = 0000.0000.0000.0210
            //                0001.1000.0000.0000 >>5 = 0000.0000.1100.0000 = 0000.0000.7600.0000
            // полный экранный адрес по координате Y: = 0000.0000.7654.3210
            int y = addr & 0x00FF;

            int X = (x * pixelScale); // Учтём_
            int Y = (y * pixelScale); // масштаб.

//
            // адрес 1 аттрибута 22528
            //    int attr = mem( ATRBeg + (addr & 0x1f) + ((y >> 3) * nCharsWide) );
            //           5800h        0001.1111b
            int attr = rgb[addr];

//
            // Swap colors around   if doing flash
            // Redraw left nibble if necessary
            qwe((newPixels & 0xF0) >> 4, X, Y, attr);
            qwe(newPixels & 0x0F, X + 4, Y, attr);

            int newAddr = nextAddr[addr]; // новый адрес из буфера адресов по смещению "first"
            nextAddr[addr] = -1; // в буфере сюда записать "адрес" = -1;
            addr = newAddr;          // текущий "адрес" = новому адресу, пока != -1;
        }
        first = -1; // признак, что обновлений более нет.
    }

    private void qwe(int newPixels, int x, int y, int attr) {
        // получим новый:    аттрибут, младший ниббл
        Image image = getImage(attr, newPixels); // новый image1
        // Метод paint использует drawlmage с четырьмя аргументами:
        // это ссылка на изображение art, координаты левого верхнего угла рисунка х, у
        // и объект типа ImageObserver.
        drawer.accept(image, new Point(x, y)); // рисуем старшие нибблы байта
    }

    public synchronized Image getImage(int attr, int pattern) {
        int ink = ((attr >> 4) & 0x000f); // старший ниббл - цвет (ink)
        int pap = ((attr) & 0x000f); // младший ниббл - фон  (paper)
        // сменили палитру
        Color[] colors = SpecMXColors;
        Image image = imageCreator.apply(4, 1); // создали Image 4 х 1 точек: ниббл.
        Graphics g = image.getGraphics(); // создали для него графический контент

        for (int i = 0; i < 4; i++) { // по 4-м точкам ниббла:
            int col = ((pattern & (1 << i)) == 0) ? pap : ink; // вычисляем цвет.

            g.setColor(colors[col]); // выставляем для точки цвет из  SpecMXColors[];
            g.fillRect((3 - i), 0, 1, 1); // ставим точку на графический контент
        }
        return image; // вернём созданный или считанный image
    }
}