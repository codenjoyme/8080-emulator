package spec;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static spec.Constants.*;
import static spec.Video.*;

public class Hardware {

    private static final double CLOCK = 1.6; // Specialist runs at 3.5Mhz;
    public static final int BORDER_PORT = 254;

    private Graphics parentGraphics;
    private Graphics canvasGraphics;
    private Graphics bufferGraphics;
    private Image bufferImage;

    // SpecApp usually (where border is drawn)
    private Container parent;

    // Main screen
    private Canvas canvas;

    // ширина бордюра
    public int borderWidth = 20;

    // ESC shows a URL popup
    private TextField urlField;

    public int refreshRate = 1;  // refresh every 'n' interrupts

    private int interruptCounter = 0;
    private boolean resetAtNextInterrupt = false;
    private boolean pauseAtNextInterrupt = false;
    private boolean refreshNextInterrupt = true;

    public Thread pausedThread = null;
    public long timeOfLastInterrupt = 0;
    private long timeOfLastSample = 0;

    private Memory memory;
    private Cpu cpu;
    private RomLoader roms;
    private IOPorts ports;
    private Video video;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Hardware(Container container) {
        parent = container;

        memory = new Memory(x10000);

        video = new Video((pt, color) -> {
                    bufferGraphics.setColor(color);
                    bufferGraphics.fillRect(pt.x, pt.y, 1, 1);
                });

        cpu = new Cpu(CLOCK, new Data() {

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

        parent.add(canvas = new Canvas());

        // размер canvas
        canvas.setSize(width, height);

        // сделали canvas видимым.
        canvas.setVisible(true);

        bufferImage = parent.createImage(width, height);

        // контенты графики
        bufferGraphics = bufferImage.getGraphics();
        parentGraphics = parent.getGraphics();
        canvasGraphics = canvas.getGraphics();

        // добавили TextField - поле ввода url файла для загрузки
        parent.add(urlField = new TextField());
        urlField.setVisible(true);   
        urlField.setVisible(false);  
    }

    public void setBorderWidth(int width) {
        borderWidth = width;
        canvas.setLocation(borderWidth, borderWidth);

        // установить границы
        urlField.setBounds(0, 0, parent.getPreferredSize().width,
                urlField.getPreferredSize().height);
    }

    private void outb(int port, int outByte) {
        if ((port & 0x0001) == 0) {   // port xx.FEh
            newBorder = COLORS[outByte & 0x000F]; // 0000.0111 бордюр & 0x07
        }
    }

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
            e.printStackTrace();
        }
    }

    private boolean interrupt() {
        if (pauseAtNextInterrupt) {
            urlField.setVisible(true);

            pausedThread = Thread.currentThread();

            while (pauseAtNextInterrupt) {
                if (refreshNextInterrupt) {
                    refreshNextInterrupt = false;
                    currentBorder = null; // обновить Border
                    paintBuffer();
                }
            }
            pausedThread = null;
            urlField.setVisible(false);
        }

        if (refreshNextInterrupt) {
            refreshNextInterrupt = false;
            currentBorder = null; // обновить Border
            paintBuffer();
        }

        if (resetAtNextInterrupt) {
            System.out.println("Total RESET !!!");
            resetAtNextInterrupt = false;
            reset();
        }

        interruptCounter++;

        // Update speed indicator every 2 seconds of 'Spechard time'
        if ((interruptCounter % 100) == 0) {
            // обновить значение скорости эмуляции
        }

        // Refresh screen every interrupt by default
        // Обновлять экран каждое прерывание по умолчанию
        if ((interruptCounter % refreshRate) == 0) {
            video.screenPaint(); // вызывается из interrupt()
            paintBuffer(); // перерисовка экрана РИСУЕМ !!!
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
                    // do nothing
                }
            }
        }
        return true;
    }

    public void pauseOrResume() {
        pauseAtNextInterrupt = pausedThread == null;
    }

    // Метод repaint используется для принудительного перерисовывания апплета.
    // Этот метод, в свою очередь, вызывает метод   update
    // вызывается из класса "Main" методом  paint( Graphics g ).
    public void repaint() {
        refreshNextInterrupt = true; // установили флаг - разрешить обновление экрана
    }

     public void reset() {
        cpu.reset(); // reset() class Z80
        ports.reset();
    }

    public Color currentBorder = null; // null mean update screen
    public Color newBorder = Color.YELLOW;

    public void borderPaint() {
        if (borderWidth == 0) { // если бордюра нет - ничего не делать!
            return;
        }
        if (currentBorder == newBorder) { // цвет не менялся
            return;
        }
        currentBorder = newBorder;
        parentGraphics.setColor(currentBorder);
        parentGraphics.fillRect(0, 0,
                width + borderWidth * 2,
                height + borderWidth * 2);
    }

    private static final String fullSpeed = "Full Speed: ";
    private static final String slowSpeed = "Slow Speed: ";
    public boolean runAtFullSpeed = true;

    private void toggleSpeed() {
        runAtFullSpeed = !runAtFullSpeed;
    }

    public void paintBuffer() {
        canvasGraphics.drawImage(bufferImage, 0, 0, null);
        borderPaint();
    }

    public boolean handleEvent(Event e) {
        // остальные события - от canvas-а
        switch (e.id) {
            case Event.MOUSE_DOWN:
                // showMessage( "MOUSE_DOWN Event" );
                canvas.requestFocus();
                return true;

            case Event.KEY_ACTION:

                // событие клавиатуры - КЛАВИША_НАЖАТА_: e.key - код нажатой клавиши.
                // Обычно является Unicode значением символа, который представлен этой клавишей.
            case Event.KEY_PRESS:
                return doKey(true, e.key, e.modifiers);

            case Event.KEY_ACTION_RELEASE:

                // событие клавиатуры - КЛАВИША_ОТПУЩЕНА_
            case Event.KEY_RELEASE:
                return doKey(false, e.key, e.modifiers);

            case Event.GOT_FOCUS:
                System.out.println("'SPECIALIST' GOT FOCUS");
                outb(BORDER_PORT, 0x02);
                ports.resetKeyboard();
                return true;

            case Event.LOST_FOCUS:
                System.out.println("'SPECIALIST' LOST FOCUS");
                outb(BORDER_PORT, 0x06);
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
                // было изменение ячейки видеопамяти
                video.plot(addr, bite);
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

    public void refreshWholeScreen() {
        currentBorder = null; // обновить Border
    }
}