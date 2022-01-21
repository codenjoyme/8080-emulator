package spec;

/**
 * Надо обозначить все константы "Специалист" в портах ОЗУ ПЗУ и экране
 *  Заменены константами все характерные адреса типа 0xFFE0 чтобы читалось
 *  удобнее и легче было переходить к внедрению нового экрана.
 *  Всё фактически готово! (Может ещё проверю подпрограммы).
 *
 *  Уточнить вопрос с прерываниями и с адресом старта!!!
 *  protected int( Z80.java)_SP = 0, _PC = 0; //программный счётчик = C000h - адрес старта!
 *     public int( Z80.java)interrupt() - здесь заглушить переход на адреса прерываний.
 *
 *  переписал функции write8(), write16();
 *  ЭТО ПОСЛЕДНЯЯ РАБОЧАЯ ВЕРСИЯ, ГДЕ "СПЕЦИАЛИСТ" и "ZX" ВМЕСТЕ.
 *
 *  ЭТО ПЕРВАЯ РАБОЧАЯ ВЕРСИЯ "СПЕЦИАЛИСТ" ГДЕ "ZX"-код ПРАКТИЧЕСКИ ВЕСЬ НА МЕСТЕ!
 *  СДЕЛАНА ЗАГРУЗКА ФАЙЛА *.RKS - работает! Подглюкивает игра Бабник...
 *  СДЕЛАНО МИГАНИЕ БОРДЮРА ПРИ ПОТЕРЕ ФОКУСА ОКНОМ АППЛЕТА.
 *
 *  Версия от 21.05.2011 19:11:48, реализован К580ВВ55А рабочая копия
 *  прототип для ПК "Специалист".
 *  Вариант с заменой: next[], last[] на nextAddr[], lastByte[].
 *  @(#)Spechard.java 1.1 27/04/97 Adam Davidson & Andrew Pollard.
 */

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import static spec.Constants.*;
import static spec.IOPorts.RgRGB;

/**
 * The Spechard class extends the Z80 class implementing the supporting
 * hardware emulation which was specific to the 'Specialist' computer.
 * This includes the memory mapped screen and the IO ports which were
 * used to read the keyboard, change the border color and turn the
 * speaker on/off. There is no sound support in this version.<P>
 *
 * @author <A HREF="http://www.odie.demon.co.uk/spectrum">Adam Davidson & Andrew Pollard</A>
 * @version 1.1 27 Apr 1997
 * @see Main
 * @see Cpu
 */

public class Hardware {

    public Graphics parentGraphics;
    public Graphics canvasGraphics;
    public Graphics bufferGraphics;
    public Image bufferImage;

    // SpecApp usually (where border is drawn)
    public Container parent;

    // Main screen
    public Canvas canvas;

    // ESC shows a URL popup
    public TextField urlField;

    // How much loaded/how fast?
    public AMDProgressBar progressBar;

    public boolean pbaron = false;  // progbar - невидим.
    private boolean wfocus = false; // фокус окном не получен
    private boolean invfoc = false; // моргание бордюром

    // массив байт цвета контроллера цвета 0xC000-9000h
    public int[] rgb = new int[12288];

    private Memory memory;
    private Cpu cpu;
    private RomLoader roms;
    private IOPorts ports;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Hardware(Container _parent) {
        memory = new Memory(x10000);

        // Specialist runs at 3.5Mhz;
        cpu = new Cpu(1.6, new Data() {

            @Override
            public boolean interrupt() {
                return Hardware.this.interrupt();
            }

            @Override
            public void out8(int port, int bite) {
                Hardware.this.outb(port, bite);
            }

            @Override
            public int read8(int addr) {
                return Hardware.this.read8(addr);
            }

            @Override
            public void write8(int addr, int bite) {
                Hardware.this.write8(addr, bite);
            }
        });

        ports = new IOPorts(memory);
        roms = new RomLoader(memory, cpu);

        // Конструктору класса Spechard() из Main.class передается ссылка на компонент,
        // для которого необходимо отслеживать загрузку изображений (или что-то?).
        // В данном случае это наш аплет, > мы передаем конструктору значение (this)!!!
        parent = _parent;
        parent.add(canvas = new Canvas());

        // размер canvas
        canvas.setSize(nPixelsWide * pixelScale, nPixelsHigh * pixelScale);

        // сделали canvas видимым.
        canvas.setVisible(true);

        bufferImage = parent.createImage(nPixelsWide * pixelScale, nPixelsHigh * pixelScale);

        // контенты графики
        bufferGraphics = bufferImage.getGraphics();
        parentGraphics = parent.getGraphics();
        canvasGraphics = canvas.getGraphics();

        // добавили Z80 AMDProgressBar
        parent.add(progressBar = new AMDProgressBar());
        progressBar.setBarColor(new Color(192, 52, 4));
        progressBar.setVisible(true);  // show();
        progressBar.setVisible(false); // hide();

        // добавили TextField - поле ввода url файла для загрузки
        parent.add(urlField = new TextField());
        urlField.setVisible(true);   // show();
        urlField.setVisible(false);  // hide();
    }

    //
    public void setBorderWidth(int width) {
        borderWidth = width;
        canvas.setLocation(borderWidth, borderWidth);


        // установить границы
        urlField.setBounds(0, 0, parent.getPreferredSize().width,
                urlField.getPreferredSize().height);

        progressBar.setBounds(1, (borderWidth + nPixelsHigh * pixelScale) + 2,
                nPixelsWide * pixelScale + borderWidth * 2 - 2, borderWidth - 2);

        progressBar.setFont(urlField.getFont());
    }



    private void outb(int port, int outByte) {
        if ((port & 0x0001) == 0) {   // port xx.FEh
            newBorder = (outByte & 0x000F); // 0000.0111 бордюр & 0x07
        }
    }


    // Поскольку исполнение проходит как плотный цикл, некоторые реализации виртуальной машины
    // Java не позволяют любым другим процессам получить доступ. Это даёт (GUI) Графическому
    // Интерфейсу Пользователя время для обновления.
    //
    // Since execute runs as a tight loop, some Java VM implementations
    //  don't allow any other threads to get a look in. This give the
    //  GUI time to update. If anyone has a better solution please
    //  email us at mailto:spectrum@odie.demon.co.uk
    //
    public int sleepHack = 0;
    public int refreshRate = 1;  // refresh every 'n' interrupts

    private int interruptCounter = 0;
    private boolean resetAtNextInterrupt = false;
    private boolean pauseAtNextInterrupt = false;
    private boolean refreshNextInterrupt = true;
    private boolean loadFromURLFieldNextInterrupt = false;

    public Thread pausedThread = null;
    public long timeOfLastInterrupt = 0;
    private long timeOfLastSample = 0;

    //
    private void loadFromURLField() {
        try {
            pauseOrResume();

            urlField.setVisible(false);
            URL url = new URL(urlField.getText());
            URLConnection snap = url.openConnection();

            InputStream input = snap.getInputStream();
            loadSnapshot(url.toString(), input, snap.getContentLength());
            input.close();
        } catch (Exception e) {
            showMessage(e.toString()); 
        }
    }

    // прерывание
    private boolean interrupt() {
        if (pauseAtNextInterrupt) {
            // поле ввода url
            urlField.setVisible(true);

            pausedThread = Thread.currentThread();

            while (pauseAtNextInterrupt) {
                if (!pbaron) {
                    showStats = true;
                    progressBar.setVisible(true);
                }
                showMessage("© 2011 Sam_Computers LTD");

                if (refreshNextInterrupt) { // проверка флага - рисовать.
                    refreshNextInterrupt = false; // сбросим флаг рисовать.
                    oldBorder = -1;// обновить Border
                    paintBuffer(); // перерисовка экрана РИСУЕМ !!!
                }

                if (loadFromURLFieldNextInterrupt) {
                    loadFromURLFieldNextInterrupt = false;
                    loadFromURLField();
                } else {
                    try {
                        Thread.sleep(500); // дали случиться внешним событиям
                    } catch (Exception ignored) {
                        // Exception
                    }
                }
            }
            pausedThread = null;
            // поле ввода url
            urlField.setVisible(false);
            if (!pbaron) {
                showStats = false;
                progressBar.setVisible(false);
            }
        }

        if (refreshNextInterrupt) {
            refreshNextInterrupt = false;
            oldBorder = -1;// обновить Border
            // перерисовка экрана РИСУЕМ !!!
            paintBuffer(); // рисовать разрешили из repaint() < paint( Graphics g )
        }

        if (resetAtNextInterrupt) {
            showMessage("Total RESET !!!");
            resetAtNextInterrupt = false;
            reset();
        }

        interruptCounter++;

        // Characters flash every 1/2 a second

        if (!wfocus) // если потерян фокус - мигаем бордюром
        {
            if ((interruptCounter % 200) == 0) // every 4 seconds of 'Spechard time'
            {  // Этот оператор возвращает остаток от деления первого операнда на второй.
                invfoc = !invfoc;
                if (invfoc) { // обновить мигающий бордюр
                    outb(254, 0x06);
                } else {
                    outb(254, 0x04);
                }
            }
        }

        // Update speed indicator every 2 seconds of 'Spechard time'
        if ((interruptCounter % 100) == 0) {
            refreshSpeed(); // обновить значение скорости эмуляции
        }

        // Refresh screen every interrupt by default
        // Обновлять экран каждое прерывание по умолчанию
        if ((interruptCounter % refreshRate) == 0) {
            screenPaint(); // вызывается из interrupt() >>>
        }
        // возвращает текущее системное время в виде миллисекунд,
        // прошедших с 1 января 1970 года
        timeOfLastInterrupt = System.currentTimeMillis();

        // Trying to slow to 100%, browsers resolution on the system
        // time is not accurate enough to check every interrurpt. So
        // we check every 4 interrupts.

        if ((interruptCounter % 4) == 0) {
            long durOfLastInterrupt = timeOfLastInterrupt - timeOfLastSample;
            timeOfLastSample = timeOfLastInterrupt;
            // запомним текущее время, как предыдущее.
            if (!runAtFullSpeed && (durOfLastInterrupt < 40)) {
                try {
                    Thread.sleep(50 - durOfLastInterrupt); // 50
                } catch (Exception ignored) {
                    // Exception
                }
            }
        }

        // This was put in to handle Netscape 2 which was prone to
        // locking up if one thread never gave up its time slice.
        if (sleepHack > 0) {
            try {
                Thread.sleep(sleepHack);
            } catch (Exception ignored) {
                // do nothing
            }
        }

        return true;
    }

    public void pauseOrResume() {
        // Pause
        // Resume
        pauseAtNextInterrupt = pausedThread == null;
    }

    // Метод repaint используется для принудительного перерисовывания апплета.
    // Этот метод, в свою очередь, вызывает метод   update
    // вызывается из класса "Main" методом  paint( Graphics g ).
    public void repaint() {
        refreshNextInterrupt = true; // установили флаг - разрешить обновление экрана
    }

    //================================== сброс =================================================
    public void reset() {
        cpu.reset(); // reset() class Z80
        if (wfocus) {
            outb(254, 0x02);
        } else {
            outb(254, 0x06); // White border on startup: port 0FEh ff
        }
        ports.reset();
    }

    //
    // Пользуясь ключевыми словами static и final, можно определять внутри классов
    // глобальные константы. Внутри класса можно определять статические методы и поля
    // (с помощью ключевого слова static), которые будут играть роль глобальных методов
    // и данных. Если в базовом классе метод определен с ключевым словом {final}, его
    // нельзя переопределить в дочернем классе, созданном на базе данного метода.
    //
    // Screen stuff - метрики экрана
    // ширина бордюра
    public int borderWidth = 20;   // absolute, not relative to pixelScale
    public static final int pixelScale = 1;    // scales pixels in main screen, not border

    public static final int nPixelsWide = 384;  // точек по X 384 - Спец.; 256 - ZX;
    public static final int nPixelsHigh = 256;  // точек по Y 256 - Спец.; 192 - ZX;

    public static final int nCharsWide = 48;   // 256/8=32 (Байт в строке); 48 - Спец.; 32 - ZX;
    public static final int nCharsHigh = 256;   // 192/8=24 строки в экране;256 - Спец.; 24 - ZX;

    private static final int str = 238;         // насыщенность (saturation) 0EEh
    private static final Color[] SpecMXColors = {// 16 цветов - массив цветов "Специалист"
            Color.black, ////00-черный тусклые цвета
            Color.blue,              // 01 синий
            Color.green,             // 02 зелёный
            Color.cyan,              // 03 бирюзовый
            Color.red,               // 04 красный
            Color.magenta,           // 05 фиолетовый
            Color.yellow,            // 06 коричневый
            Color.white,             // 07 белый
            new Color(0, 0, 0),  // 08 серый   // яркие цвета
            new Color(0, 0, str),  // 09 голубой
            new Color(0, str, 0),  // 0A светло-зелёный
            new Color(0, str, str),  // 0B светло-бирюзовый
            new Color(str, 0, 0),  // 0C розовый
            new Color(str, 0, str),  // 0D светло-фиолетовый
            new Color(str, str, 0),  // 0E желтый
            new Color(str, str, str)   // 0F ярко-белый
            //   R     G    B
    };

    // 4000h = 16384 - начало экрана;  6144 байт (1800h) - размер экрана
    // 32 байта в сроке * 192 строки = 1800 смещение 1 аттрибута = 5800h-4000h (22528)
    private static final int firstAttr = (nPixelsHigh * nCharsWide); // 768 байт аттрибутов(300h)

    // адрес 1 аттрибута + 24 строки * по 32 символа = адрес последнего аттрибута
    // 5B00h (23296) - конец аттрибутов               24 строки  32 символа
    private static final int lastAttr = firstAttr + firstAttr;  // для "Специалист"
    // (nCharsHigh*nCharsWide);

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

    //        массив смещений [] = 6912 в буфере - ФАКТИЧЕСКИ АДРЕСА В ЭКРАНЕ
    private final int[] nextAddr = new int[(nPixelsHigh + nCharsHigh) * nCharsWide];

    public int newBorder = 6;  // White border on startup  0x0111b

    public int oldBorder = -1; // -1 mean update screen

    public long oldTime = 0;
    public int oldSpeed = -1; // -1 mean update progressBar
    public int newSpeed = 0;
    public boolean showStats = true;
    public String statsMessage = null;

    private static final String cancelMessage = "Click here at any time to cancel sleep";

    public void refreshSpeed() {
        long newTime = timeOfLastInterrupt;

        if (oldTime != 0) {
            newSpeed = (int) (200000.0 / (newTime - oldTime));
        }

        oldTime = newTime;

        if ((statsMessage != null) && (sleepHack > 0) && (statsMessage != cancelMessage)) {
            showMessage(cancelMessage); // "Click here at any time to cancel sleep"
        } else {
            showMessage(null);
        }
    }

    // стартовый refresh экрана
    public void refreshWholeScreen() {//   первый раз вызывается из Main.class после вызова reset();
        // от  0  до  5800Н
        for (int i = 0; i < firstAttr; i++) {//  [ 0 ] = -1, 0, 1 ...  firstAttr - 1
            nextAddr[i] = i - 1; // ВСЕ смещения в области экрана: -1, 0, 1 ...  firstAttr - 1
            lastByte[i] = (~memory.read8(i + SCREEN.begin())) & 0xFF; // ВСЕ инвертированные байты из видео-ОЗУ.
            //lastByte[ i ] = (mem( i + 16384 )) & 0xFF;
        }
        first = firstAttr - 1; // последнее смещение байта буфера экрана = 57FFH  != -1 !!!

        //   по области аттрибутов
        for (int i = 0; i < firstAttr; i++) // у "Специалист" область аттрибутов отдельная.
        {
            nextAddr[i + firstAttr] = -1; // во все адреса аттрибутов заносим -1.
            // буфер аттрибутов
            lastByte[i + firstAttr] = rgb[i];
        }
        FIRST = -1; // признак обновления

        oldBorder = -1; // -1 mean update screen, newBorder - текущий цвет Border.
        oldSpeed = -1; // update progressBar
    }

    // запись в видео-ОЗУ
    private void plot(int addr) {
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

    public void borderPaint() {// может понадобиться в Специалист для фокуса экрана
        if (oldBorder == newBorder) {// если признак равен цвету Border - ничего не делать!
            return;
        }

        oldBorder = newBorder; // иначе - присвоить признаку цвет Border

        if (borderWidth == 0) { // если бордюра нет - ничего не делать!
            return;
        }
        // если бордюр есть - перерисовать его цветом  newBorder;
        // parentGraphics.setColor( brightColors[ newBorder ] );
        parentGraphics.setColor(SpecMXColors[newBorder]);

        //   обновить Border - это нагло нарисовать прямоугольник цвета newBorder?!!!  Font.PLAIN|Font.BOLD
        parentGraphics.fillRect(0, 0, (nPixelsWide * pixelScale) + borderWidth * 2,
                (nPixelsHigh * pixelScale) + borderWidth * 2);
        if (wfocus) {
            parentGraphics.setFont(urlField.getFont()); //  установить шрифт.
            parentGraphics.setColor(SpecMXColors[0x01]);
            parentGraphics.drawString("'Specialist'", 24, 16);
            parentGraphics.setColor(SpecMXColors[newBorder]);
        } else {
            parentGraphics.setFont(urlField.getFont()); //  установить шрифт.
            parentGraphics.setColor(SpecMXColors[0]);
            parentGraphics.drawString("'Specialist' - old Russian 8-bit computer emulator. (Click here to test it.)", 24, 16);
            parentGraphics.setColor(SpecMXColors[newBorder]);
        }
    }

    private static final String fullSpeed = "Full Speed: ";
    private static final String slowSpeed = "Slow Speed: ";
    public boolean runAtFullSpeed = true;

    //
    private void toggleSpeed() {
        runAtFullSpeed = !runAtFullSpeed;
        showMessage(statsMessage); //
    }

    //
    public void showMessage(String m) {
        statsMessage = m;
        oldSpeed = -1; // Force update of progressBar
        statsPaint();
    }

    //
    public void statsPaint() {
        if (oldSpeed == newSpeed) {
            return;
        }
        oldSpeed = newSpeed;

        if ((!showStats) || (borderWidth < 10)) {
            return;
        }

        String stats = statsMessage;
        if (stats == null) {
            String speedString = runAtFullSpeed ? fullSpeed : slowSpeed;

            if (newSpeed > 0) {
                stats = speedString + newSpeed + "%";
            } else {
                stats = "Speed: calculating";
            }
            if (sleepHack > 0) {
                stats = stats + ", Sleep: " + sleepHack;
            }
        }
        progressBar.setText(stats);
        progressBar.setVisible(true);
    }

    //===========================================================================================
    public static synchronized Image getImage(Component comp, int attr, int pattern) {
        try {//                 аттрибут, ниббл = 4 бита.
            return tryGetImage(comp, attr, pattern);
        } catch (OutOfMemoryError e) { // может и не хватить памяти на все нибблы...
            imageMap = null;          // в таком случае обнулим imageMap
            patternMap = null;          //                обнулим patternMap

            System.gc();                // подчистим мусор после ошибки

            // HashTable — это подкласс Dictionary, являющийся конкретной реализацией словаря.
            // Представителя класса HashTable можно использовать для хранения произвольных объектов,
            // причем для индексации в этой коллекции также годятся любые объекты.

            // после ошибки придётся заново создать patternMap
            patternMap = new Hashtable();
            // и заново создать imageMap  // <10987654321<

            imageMap = new Image[1 << 12];// 100000000000b = 2048d = 800h  Image[ 1<<11 ]; для "ZX"
            // и попытаемся создать с теми-же параметрами Image...
            return tryGetImage(comp, attr, pattern);
        }
    }

    //  создали первоначально словарь patternMap
    public static Hashtable patternMap = new Hashtable();
    //  создали первоначально массив рисунков в 2048 экземпляров

    public static Image[] imageMap = new Image[1 << 12];// 100000000000b = 2048d = 800h
    // 7 bits for attr, 4 bits for pattern = 11 bits > 2^11=2048 элементов
    // "Специалист": 8 bits for attr, 4 bits for pattern = 12 bits > 2^12=4096 элементов

    //===========================================================================================
    private static Image tryGetImage(Component comp, int attr, int pattern) {
        int ink = ((attr >> 4) & 0x000f); // старший ниббл - цвет (ink)
        int pap = ((attr) & 0x000f); // младший ниббл - фон  (paper)
        int hashValue = 0;
        for (int i = 0; i < 4; i++) { // побитно просматриваем ниббл из ОЗУ экрана
            int col = ((pattern & (1 << i)) == 0) ? pap : ink; // присваиваем цвета

            hashValue |= (col << (i << 2)); //? ? ?
        }
        //  Что же касается данных базовых типов, если вам нужно передавать на них ссылки,
        //  то следует заменить базовые типы на соответствующие   замещающие классы.
        //  Например, вместо типа int используйте класс Integer, вместо типа long - класс Long
        //  и так далее.
        //  Инициализация таких объектов должна выполняться с помощью конструктора,
        //  как это показано ниже:

        // это будет ссылкой на объект( hashValue ).
        Integer imageKey = new Integer(hashValue);

        // возвращает запись, соответствующую ключу( imageKey )
        Image image = (Image) patternMap.get(imageKey);

        if (image == null) { // если готового image в словаре нет, сделаем его...
            // сменили палитру
            Color[] colors = SpecMXColors;
            image = comp.createImage(4, 1); // создали Image 4 х 1 точек: ниббл.
            Graphics g = image.getGraphics(); // создали для него графический контент

            for (int i = 0; i < 4; i++) { // по 4-м точкам ниббла:
                int col = ((pattern & (1 << i)) == 0) ? pap : ink; // вычисляем цвет.

                g.setColor(colors[col]); // выставляем для точки цвет из  SpecMXColors[];
                g.fillRect((3 - i), 0, 1, 1); // ставим точку на графический контент
            }
            // добавляем запись: ключ(imageKey=Integer( hashValue )),значение (image)
            patternMap.put(imageKey, image); // положим image в словарь под ключом imageKey
        }
        return image; // вернём созданный или считанный image
    }

    // Отрисовка экрана в буфере
    // Основная идея - как можно меньше РИСОВАТЬ, для этого отслеживаются изменения и
    // ОТРИСОВЫВАЮТСЯ только они.
    public void screenPaint() // вызывается из interrupt()
    {
        int addr = FIRST; // часто = -1 -> не обновляем...
        // в первый заход FIRST = -1 => цикл  while( addr >= 0 ) пропускаем.
        // сначала изменим все аттрибуты цвета
        // Update attribute affected pixels
        while (addr >= 0) { // если FIRST = -1 то и не делаем ничего.
            int oldAttr = lastByte[addr + firstAttr];// байт из буфера байтов по смещению "FIRST"
            int newAttr = rgb[addr]; // байт из ОЗУ аттриб.по смещению "FIRST"
            lastByte[addr + firstAttr] = newAttr; // байт из буфера байтов заменим байтом из ОЗУ аттриб.

            //                        Бит 6 - бит яркости. цвета фона и чернил - высокой яркости.
            //                              0100.0111b Биты 2, 1, 0 цвет чернил: зелёный,красный,синий.
            //   boolean inkChange    = ((oldAttr & 0x47) != (newAttr & 0x47));
            //                              0111.1000b Биты 5, 4, 3 цвета фона:  зелёный,красный,синий.
            //   boolean papChange    = ((oldAttr & 0x78) != (newAttr & 0x78));
            //                              1000.0000b Бит 7 - признак мерцания.
            //   boolean flashChange  = ((oldAttr & 0x80) != (newAttr & 0x80));
            //
            boolean inkChange = ((oldAttr & 0x00F0) != (newAttr & 0x00F0)); // цвет чернил
            boolean papChange = ((oldAttr & 0x000F) != (newAttr & 0x000F)); // цвет фона

            // отличие ПК "Специалист" в том, что аттрибут = код цвета нельзя изменить отдельно от
            // байта графики. Они всегда пишутся синхронно. Код цвета выставляется в порт 0xFFF8 и
            // запись байта по адресу в ОЗУ экрана запишет цвет по этому же адресу в ОЗУ цвета.
            // Код цвета единожды выставленный красит все байты экрана. Так что изменений ink и
            // paper может при записи и не быть. (Вероятно надо следить за изменением 0xFFF8.)
            //
            if (inkChange || papChange) { // если случились изменения:   || flashChange
                //     boolean allChange = ((inkChange && papChange) || flashChange); //
                // изменения:       чернил и фона       или мерцания
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
                // экранный адрес точки ZX_Spec вычисляется по адресу памяти из след.соотношения:
                // если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
                //                   CS 01 00 y7 y6 Y2 Y1 Y0 y5 y4 y3|X7 X6 X5 X4 X3 |
                // где X2...X0 - координаты бита в байте;
                //     X7...X3 - координаты байта в строке: 2^5=32 байта в строке;
                //     Y7...Y0 - координаты строки: 2^8=256, но только (0 — верх, 0BFh=191 — низ);
                //          CS - область памяти (=0 — 4000h...5AFFh, =1 — C000h...DAFFh).
                //
                //  Соответствие битов адреса в области атрибутов координатам экрана
                // если адрес как А: 15 14 13 12 11 10  9  8__7  6  5  4  3  2  1  0 ; то
                //                   CS  1  0  1  1  0 Y7 Y6 Y5 Y4 Y3 X7 X6 X5 X4 X3
                // где X7...X3 — биты горизонт. координаты (0 — левая сторона, 0FFh — правая);
                //     Y7...Y3 — биты вертикальной координаты (0 — верх, 0BFh — низ);
                // Так как атрибут покрывает целое поле 8?8, то 3 младших бита координаты
                // X и Y не используются.
                //  это адрес аттрибута, а надо попасть в первую строку его знакоместа.
                // ***
                //int scrAddr   = ((addr & 0x0300) << 3) | (addr & 0xFF);
                int scrAddr = addr;

                //                  54321098.76543210    .765_43210
                //                  00000011.00000000   Y.543_76543—X
                //                       Y76 - старшая координата аттрибута
                //             3 << 00011000.00000000 = 00011000.543_76543
                //                    Y76 - сдвинута в старшую координату экрана.

                int oldPixels = lastByte[scrAddr + firstAttr]; // байт из буфера байтов по смещению ""
                int newPixels = rgb[scrAddr]; // байт из ОЗУ аттриб.по смещению ""
                int changes = oldPixels ^ newPixels; // changes = 0, если они одинаковы...
                if (inkChange) { // если изменения чернил:
                    changes |= newPixels;
                } else { // если не менялись чернила?
                    changes |= ((~newPixels) & 0xFF);
                }//inkChange

                if (changes != 0) { // если изменений нет - просто продолжаем...
                    lastByte[scrAddr + firstAttr] = changes ^ newPixels;
                    if (nextAddr[scrAddr + firstAttr] == -1) {
                        nextAddr[scrAddr] = first; //   + firstAttr  ????
                        first = scrAddr;
                    }
                }

            } // if( inkChange || papChange )

            int newAddr = nextAddr[addr];
            nextAddr[addr] = -1;
            addr = newAddr;
        } //  while( addr >= 0 )
        FIRST = -1;

        // Only update screen if necessary
        if (first < 0) { // =-1
            return;
        }
        // далее изменим все нужные пиксели экрана
        // Update affected pixels
        addr = first;  // в первый заход first = адресу последнего байта экрана...
        while (addr >= 0) { // !=-1
            int oldPixels = lastByte[addr];   // байт из буфера байтов по смещению "first"
            int newPixels = memory.read8(addr + SCREEN.begin()); // байт из видео-ОЗУ по смещению "first"
            int changes = oldPixels ^ newPixels; // changes = 0, если они одинаковы...
            //                   ^-XOR

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
            // Swap colors around if doing flash
            // Redraw left nibble if necessary
            if ((changes & 0xF0) != 0) { // если есть изменения в старшем ниббле:
                // старший ниббл байта сдвинули в младший - 0000.0000bbbb
                int newPixels1 = (newPixels & 0xF0) >> 4;
                // аттрибуты кроме мерц. сдвинули выше младшего ниббла 0aaa.aaaa.0000
                int imageMapEntry1 = (((attr & 0x7F) << 4) | newPixels1);
                // получили хитрый индекс: 0aaa.aaaabbbb
                Image image1 = imageMap[imageMapEntry1]; // по индексу ищем image1

                if (image1 == null) { // если такого image1 нет
                    // получим новый:    аттрибут, младший ниббл
                    image1 = getImage(parent, attr, newPixels1); // новый image1
                    imageMap[imageMapEntry1] = image1; // занесём в массив imageMap
                } // похоже - это убыстряет всё; если есть рисунок полубайта, то
                // берём из массива (это быстро), если нет - создадим его, но в
                // другой раз - не создаём, а быстро берём из массива.

                // Метод paint использует drawlmage с четырьмя аргументами:
                // это ссылка на изображение art, координаты левого верхнего угла рисунка х, у
                // и объект типа ImageObserver.
                bufferGraphics.drawImage(image1, X, Y, null); // рисуем старшие нибблы байта
            }

            // Redraw right nibble if necessary
            if ((changes & 0x0F) != 0) { // если есть изменения в младшем ниббле:
                // выделили младший ниббл байта - 0000.0000bbbb
                int newPixels2 = (newPixels & 0x0F);
                // аттрибуты кроме мерц. сдвинули выше младшего ниббла 0aaa.aaaa.0000
                int imageMapEntry2 = (((attr & 0x7F) << 4) | newPixels2);
                // получили хитрый индекс: 0aaa.aaaabbbb
                Image image2 = imageMap[imageMapEntry2]; //  по индексу ищем image2

                if (image2 == null) { // если такого image2 нет
                    // получим новый:    аттрибут, младший ниббл
                    image2 = getImage(parent, attr, newPixels2); // новый image2
                    imageMap[imageMapEntry2] = image2; // занесём в массив imageMap
                }// похоже - это убыстряет всё; рисунок есть - берём из массива,
                // если нет - создадим его, но в другой раз - быстро берём из массива.

                // Метод paint использует drawlmage с четырьмя аргументами:
                // это ссылка на изображение art, координаты левого верхнего угла рисунка х, у
                // и объект типа ImageObserver.
                bufferGraphics.drawImage(image2, X + 4, Y, null); // младшие нибблы байта
            }

            int newAddr = nextAddr[addr]; // новый адрес из буфера адресов по смещению "first"
            nextAddr[addr] = -1; // в буфере сюда записать "адрес" = -1;
            addr = newAddr;          // текущий "адрес" = новому адресу, пока != -1;
        }
        first = -1; // признак, что обновлений более нет.
        paintBuffer(); // перерисовка экрана РИСУЕМ !!!
    }
    // Рисует на Graphics g - bufferImage: это сама отрисовка на canvas, остальное - в буфере!
    public void paintBuffer() {  // вызывается из screenPaint( )
        canvasGraphics.drawImage(bufferImage, 0, 0, null); // ПЕРЕРИСОВКА ЭКРАНА ИЗ БУФЕРА
        // Бордюр
        borderPaint();
    }

    // Process events from UI - обработка событий

    // вызывается  Main.handleEvent-ом.-
    public boolean handleEvent(Event e) {  // По сути это переопределённый handleEvent();
        if (e.target == progressBar) { // progressBar Event
            if (e.id == Event.MOUSE_DOWN) {

                if (sleepHack > 0) {
                    sleepHack = 0;
                    showMessage("Sleep Cancelled");
                } else {
                    toggleSpeed();
                }
                canvas.requestFocus();
                return true;
            }
            return false;
        } // progressBar END_

        // urlField Event - событие от поля ввода url
        if (e.target == urlField) {
            if (e.id == Event.ACTION_EVENT) {
                loadFromURLFieldNextInterrupt = true;
                return true;
            }
            return false;
        }
        // остальные события - от canvas-а Spechard (он же Z80)
        switch (e.id) {
            case Event.MOUSE_DOWN:
                // showMessage( "MOUSE_DOWN Event" );
                canvas.requestFocus();
                return true;

            case Event.KEY_ACTION:

                // событие клавиатуры - КЛАВИША_НАЖАТА_: e.key - код нажатой клавиши.
                // Обычно является Unicode значением символа, который представлен этой клавишей.
            case Event.KEY_PRESS:
                return doKey(true, e.key, e.modifiers); // пересчет клавиатуры

            case Event.KEY_ACTION_RELEASE:

                // событие клавиатуры - КЛАВИША_ОТПУЩЕНА_
            case Event.KEY_RELEASE:
                //     doKey - вызываем отсюда...
                return doKey(false, e.key, e.modifiers);// пересчет клавиатуры

            case Event.GOT_FOCUS:
                showMessage("'SPECIALIST' GOT FOCUS");
                outb(254, 0x02);
                wfocus = true;
                ports.resetKeyboard();
                return true;

            case Event.LOST_FOCUS:
                showMessage("'SPECIALIST' LOST FOCUS");
                outb(254, 0x06);
                wfocus = false;
                ports.resetKeyboard();
                return true;
        }
        return false;
    }

    // перекодировка кода клавиши в линии порта
    // при нажатии/отжатии клавиши сбрасываем/устанавливаем биты в переменных _B_SPC..._CAPS_V
    //
    // down=true клавиша_нажата_  false клавиша_отпущена_

    // пересчет клавиатуры
    // по коду клавиши и модификаторам определяем в каких переменных  _B_SPC..._CAPS_V изменять
    // отдельные биты, вызывая функции из списка выше...
    public boolean doKey(boolean down, int ascii, int mods) { // вызывается  handleEvent      ascii - код
        ports.processKey(down, ascii);

        // boolean CTRL = ((mods & Event.CTRL_MASK) != 0); // Была нажата клавиша <Ctrl>
        // boolean ALT = ((mods & Event.ALT_MASK) != 0);  // Была нажата мета-клавиша <Alt>

        // клавиши - модификаторы:
        //  boolean  CAPS = ((mods & Event.CTRL_MASK) != 0); // Была нажата клавиша <Ctrl>
        //  boolean  SYMB = ((mods & Event.META_MASK) != 0); // Была нажата мета-клавиша <Alt> Meta key
        //  boolean SHIFT = ((mods & Event.SHIFT_MASK)!= 0); // Была нажата клавиша <Shift>
        // клавиши-модификаторы - на то они и модификаторы, чтобы поступать ВМЕСТЕ с основной клавишей

        // A Meta key is a key on the keyboard that modifies each character you type by
        // controlling the 0200 bit. This bit is on if and only if the Meta key is held down
        // when the character is typed. Characters typed using the Meta key are called Meta
        // characters.
        //     if ((mods & Event.META_MASK) != 0){
        //           showMessage( "META PRESSed!!!" );
        //        }
        //     if ((mods & Event.CTRL_MASK) != 0){
        //           showMessage( "CTRL PRESSed!!!" );
        //        }
        //     if ((mods & Event.SHIFT_MASK) != 0){
        //           showMessage( "SHIFT PRESSed!!!" );
        //        }
        //
        // Meta — условное обозначение для набора мета-клавиш. На современном PC это обычно
        // Alt и клавиша Windows. На некоторых устройствах/ОС под мета клавиши попадает и Esc.

        switch (ascii) {  // down=true КЛАВИША_НАЖАТА_, false КЛАВИША_ОТПУЩЕНА_
            case 0x0400: { // <Pause>
                if (down) {
                    resetAtNextInterrupt = true;
                }
                break;
            }
            default:
                return false;
        }
        return true;
    }

    // Методы Java могут создавать исключения, вызванные возникновением ошибок или других
    // событий. Все исключения должны либо обрабатываться внутри метода, либо описываться
    // в определении метода после ключевого слова throws.
    //
    public void loadSnapshot(String name, InputStream is, int snapshotLength) throws Exception {
        // Linux  JDK doesn't always know the size of files
        if (snapshotLength < 0) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            is = new BufferedInputStream(is, 4096);

            int bite;
            while ((bite = is.read()) != -1) {
                os.write((byte) bite);
            }

            is = new ByteArrayInputStream(os.toByteArray());
        }
        // Грубая проверка, но будет работать (SNA имеет фиксированный размер)
        // Crude check but it'll work (SNA is a fixed size)

        roms.loadRKS(name, is);
        refreshWholeScreen();
        ports.resetKeyboard();

        canvas.requestFocus();
    }

    public void execute() {
        cpu.execute();
    }

    private int read8(int addr) {
        if (PORTS.includes(addr)) {
            return ports.inPort(addr);
        }
        return memory.read8(addr);
    }

    private void write8(int addr, int bite) {
        if (ROM.includes(addr)) {
            // в ПЗУ не пишем
            return;
        }

        if (SCREEN.includes(addr)) {
            if (memory.read8(addr) != bite) {
                plot(addr); // было изменение ячейки видеопамяти
            }
        }

        if (PORTS.includes(addr)) {
            ports.outPort(addr, bite);
        }

        memory.write8(addr, bite);
    }

    public RomLoader roms() {
        return roms;
    }
}