package spec;

import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.awt.*;
import java.net.URL;

import static java.awt.Event.*;
import static spec.Video.COLORS;

public class Application {

    private static final int BORDER_PORT = 254;
    private static final int PAUSE = 0x0400;

    private int refreshRate = 1;  // refresh every 'n' interrupts

    private int interruptCounter = 0;
    private boolean resetAtNextInterrupt = false;
    private boolean pauseAtNextInterrupt = false;
    private boolean refreshNextInterrupt = true;

    private long timeOfLastInterrupt = 0;
    private long timeOfLastSample = 0;
    private boolean runAtFullSpeed = false;

    private Graphic graphic;
    private Hardware hard;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Application(Container parent, URL base) {
        graphic = new Graphic(parent);

        hard = new Hardware() {
            @Override
            protected void outb(int port, int bite) {
                Application.this.outb(port, bite);
            }

            @Override
            protected boolean interrupt() {
                return Application.this.interrupt();
            }

            @Override
            protected void drawPixel(Point point, Color color) {
                Application.this.graphic.drawPixel(point, color);
            }
        };

        loadRoms(base);
    }

    private void loadRoms(URL base) {
        boolean lik = true;
        if (lik) {
            Lik.loadRom(base, hard.roms());
            Lik.loadGame(base, hard.roms(), "klad");
            // Lik.loadTest(base, hard.roms(), "test");
        } else {
            Specialist.loadRom(base, hard.roms());
            Specialist.loadGame(base, hard.roms(), "blobcop");
        }

        String snapshot = null; // TODO научиться сохранять и загружать снепшоты
        if (snapshot != null) {
            loadSnapshot(base, snapshot);
        } else {
            reset();
        }
    }

    private void outb(int port, int outByte) {
        // port xx.FEh
        if ((port & 0x0001) == 0) {
            // 0000.0111 бордюр & 0x07
            graphic.changeColor(COLORS[outByte & 0x000F]);
        }
    }

    private boolean interrupt() {
        if (pauseAtNextInterrupt) {
            while (pauseAtNextInterrupt) {
                if (refreshNextInterrupt) {
                    refreshNextInterrupt = false;
                    graphic.refreshBorder();
                    graphic.paintBuffer();
                }
            }
        }

        if (refreshNextInterrupt) {
            refreshNextInterrupt = false;
            graphic.refreshBorder();
            graphic.paintBuffer();
        }

        if (resetAtNextInterrupt) {
            System.out.println("TOTAL RESET");
            resetAtNextInterrupt = false;
            hard.reset();
        }

        interruptCounter++;

        // Update speed indicator every 2 seconds of 'Spechard time'
        if ((interruptCounter % 100) == 0) {
            // обновить значение скорости эмуляции
        }

        // Обновлять экран каждое прерывание по умолчанию
        if ((interruptCounter % refreshRate) == 0) {
            hard.video.screenPaint();
            graphic.paintBuffer();
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

    public boolean handleEvent(Event event) {
        switch (event.id) {
            case MOUSE_DOWN: {
                graphic.requestFocus();
                return true;
            }
            case KEY_ACTION:
            case KEY_PRESS: {
                return doKey(true, event.key, event.modifiers);
            }
            case KEY_ACTION_RELEASE:
            case KEY_RELEASE: {
                return doKey(false, event.key, event.modifiers);
            }
            case GOT_FOCUS: {
                gotFocus();
                return true;
            }
            case LOST_FOCUS: {
                lostFocus();
                return true;
            }
        }
        return false;
    }

    public void lostFocus() {
        System.out.println("LOST FOCUS");
        outb(BORDER_PORT, 0x06);
        hard.ports.resetKeyboard();
    }

    public void gotFocus() {
        System.out.println("GOT FOCUS");
        outb(BORDER_PORT, 0x02);
        hard.ports.resetKeyboard();
    }

    private boolean doKey(boolean down, int ascii, int mods) {
        hard.ports.processKey(down, ascii);

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

    private void refreshWholeScreen() {
        graphic.refreshBorder();
    }

    private void loadSnapshot(URL base, String snapshot) {
        hard.loadSnapshot(base, snapshot);
        refreshWholeScreen();
        graphic.requestFocus();
    }

    public void start() {
        hard.start();
    }

    private void reset() {
        hard.reset();
        refreshWholeScreen();
    }
}