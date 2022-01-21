package spec;

import java.awt.*;
import java.net.URL;

import static java.awt.Event.*;
import static spec.Constants.*;
import static spec.Video.*;

public class Hardware {

    private static final double CLOCK = 1.6; // Specialist runs at 3.5Mhz;
    private static final int BORDER_PORT = 254;
    public static final int PAUSE = 0x0400;

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

    private int refreshRate = 1;  // refresh every 'n' interrupts

    private int interruptCounter = 0;
    private boolean resetAtNextInterrupt = false;
    private boolean pauseAtNextInterrupt = false;
    private boolean refreshNextInterrupt = true;

    private Thread pausedThread = null;
    private long timeOfLastInterrupt = 0;
    private long timeOfLastSample = 0;
    private boolean runAtFullSpeed = false;
    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

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
        canvas.setSize(WIDTH, HEIGHT);

        // сделали canvas видимым.
        canvas.setVisible(true);

        bufferImage = parent.createImage(WIDTH, HEIGHT);

        // контенты графики
        bufferGraphics = bufferImage.getGraphics();
        parentGraphics = parent.getGraphics();
        canvasGraphics = canvas.getGraphics();
    }

    public void setBorderWidth(int width) {
        borderWidth = width;
        canvas.setLocation(borderWidth, borderWidth);

    }

    private void outb(int port, int outByte) {
        // port xx.FEh
        if ((port & 0x0001) == 0) {
            // 0000.0111 бордюр & 0x07
            newBorder = COLORS[outByte & 0x000F];
        }
    }

    private boolean interrupt() {
        if (pauseAtNextInterrupt) {
            pausedThread = Thread.currentThread();

            while (pauseAtNextInterrupt) {
                if (refreshNextInterrupt) {
                    refreshNextInterrupt = false;
                    currentBorder = null; // обновить Border
                    paintBuffer();
                }
            }
            pausedThread = null;
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

        // Обновлять экран каждое прерывание по умолчанию
        if ((interruptCounter % refreshRate) == 0) {
            video.screenPaint();
            paintBuffer();
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

    public void repaint() {
        refreshNextInterrupt = true;
    }

    public void reset() {
        cpu.reset();
        ports.reset();
    }

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
                WIDTH + borderWidth * 2,
                HEIGHT + borderWidth * 2);
    }

    public void paintBuffer() {
        canvasGraphics.drawImage(bufferImage, 0, 0, null);
        borderPaint();
    }

    public boolean handleEvent(Event e) {
        switch (e.id) {
            case MOUSE_DOWN: {
                canvas.requestFocus();
                return true;
            }
            case KEY_ACTION:
            case KEY_PRESS: {
                // событие клавиатуры - КЛАВИША_НАЖАТА_: e.key - код нажатой клавиши.
                // Обычно является Unicode значением символа, который представлен этой клавишей.
                return doKey(true, e.key, e.modifiers);
            }
            case KEY_ACTION_RELEASE:
            case KEY_RELEASE: {
                // событие клавиатуры - КЛАВИША_ОТПУЩЕНА_
                return doKey(false, e.key, e.modifiers);
            }
            case GOT_FOCUS: {
                System.out.println("'SPECIALIST' GOT FOCUS");
                outb(BORDER_PORT, 0x02);
                ports.resetKeyboard();
                return true;
            }
            case LOST_FOCUS: {
                System.out.println("'SPECIALIST' LOST FOCUS");
                outb(BORDER_PORT, 0x06);
                ports.resetKeyboard();
                return true;
            }
        }
        return false;
    }

    public boolean doKey(boolean down, int ascii, int mods) {
        ports.processKey(down, ascii);

        switch (ascii) {
            case PAUSE: {
                if (down) resetAtNextInterrupt = true;
                break;
            }
            default:
                return false;
        }
        return true;
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
        currentBorder = null;
    }

    public void loadSnapshot(URL base, String snapshot) throws Exception {
        roms.loadSnapshot(base, snapshot);
        refreshWholeScreen();
        ports.resetKeyboard();
        canvas.requestFocus();
    }
}