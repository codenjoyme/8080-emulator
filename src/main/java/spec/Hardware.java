package spec;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static spec.Constants.*;
import static spec.Video.height;
import static spec.Video.width;

public class Hardware {

    private static final double CLOCK = 1.6; // Specialist runs at 3.5Mhz;

    private Graphics parentGraphics;
    private Graphics canvasGraphics;
    private Graphics bufferGraphics;
    private Image bufferImage;

    // SpecApp usually (where border is drawn)
    private Container parent;

    // Main screen
    private Canvas canvas;

    // ширина бордюра
    public int borderWidth = 20;   // absolute, not relative to pixelScale

    // ESC shows a URL popup
    private TextField urlField;

    // How much loaded/how fast?
    private AMDProgressBar progressBar;

    public boolean pbaron = false;  // progbar - невидим.
    private boolean wfocus = false; // фокус окном не получен
    private boolean invfoc = false; // моргание бордюром

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

        video = new Video(memory,
                (pt, color) -> {
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

        // добавили Z80 AMDProgressBar
        parent.add(progressBar = new AMDProgressBar());
        progressBar.setBarColor(new Color(192, 52, 4));
        progressBar.setVisible(true);  
        progressBar.setVisible(false); 

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

        progressBar.setBounds(1, (borderWidth + height) + 2,
                Video.width + borderWidth * 2 - 2, borderWidth - 2);

        progressBar.setFont(urlField.getFont());
    }

    private void outb(int port, int outByte) {
        if ((port & 0x0001) == 0) {   // port xx.FEh
            // TODO borderColor = (outByte & 0x000F); // 0000.0111 бордюр & 0x07
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
            showMessage(e.toString());
        }
    }

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
                        // do noting
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
        if (wfocus) {
            outb(254, 0x02);
        } else {
            outb(254, 0x06); // White border on startup: port 0FEh ff
        }
        ports.reset();
    }

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

    public void borderPaint() {
        if (borderWidth == 0) { // если бордюра нет - ничего не делать!
            return;
        }
        if (wfocus) {
            parentGraphics.setColor(Color.GREEN);
        } else {
            parentGraphics.setColor(Color.YELLOW);
        }
        parentGraphics.fillRect(0, 0,
                width + borderWidth * 2,
                height + borderWidth * 2);
    }

    private static final String fullSpeed = "Full Speed: ";
    private static final String slowSpeed = "Slow Speed: ";
    public boolean runAtFullSpeed = true;

    private void toggleSpeed() {
        runAtFullSpeed = !runAtFullSpeed;
        showMessage(statsMessage); //
    }

    public void showMessage(String m) {
        statsMessage = m;
        oldSpeed = -1; // Force update of progressBar
        statsPaint();
    }

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

    public void paintBuffer() {
        canvasGraphics.drawImage(bufferImage, 0, 0, null);
        borderPaint();
    }

    public boolean handleEvent(Event e) {
        if (e.target == progressBar) {
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
        }

        // urlField Event - событие от поля ввода url
        if (e.target == urlField) {
            if (e.id == Event.ACTION_EVENT) {
                loadFromURLFieldNextInterrupt = true;
                return true;
            }
            return false;
        }

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
                // video.plot(addr);
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
        oldBorder = -1; // -1 mean update screen, newBorder - текущий цвет Border.
        oldSpeed = -1; // update progressBar
    }
}