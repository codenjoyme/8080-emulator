package spec;

import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.awt.*;
import java.net.URL;

import static spec.Constants.*;
import static spec.Video.COLORS;

public class Application {

    private int refreshRate = 1;  // refresh every 'n' interrupts

    private int interrupt = 0;
    private boolean resetAtNextInterrupt = false;
    private boolean pauseAtNextInterrupt = false;
    private boolean refreshNextInterrupt = true;

    private long last = 0;
    private boolean fullSpeed = false;

    private Graphic graphic;
    private Hardware hard;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Application(Container parent, URL base) {
        graphic = new Graphic(SCREEN_WIDTH, SCREEN_HEIGHT, BORDER_WIDTH, parent);

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT) {

            @Override
            protected void out8(int port, int bite) {
                printIO(port, bite);
            }

            @Override
            protected void update() {
                updateState();
            }

            @Override
            protected void drawPixel(Point point, Color color) {
                graphic.drawPixel(point, color);
            }
        };

        hard.fileRecorder().with(RECORD_LOG_FILE);
        loadRoms(base);
    }

    private void printIO(int port, int bite) {
        // port xx.FEh
        if ((port & 0x0001) == 0) {
            // 0000.0111 бордюр & 0x07
            graphic.changeColor(COLORS[bite & 0x000F]);
        }
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

    private void updateState() {
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
            Logger.info("Reset Hardware!");
            resetAtNextInterrupt = false;
            hard.reset();
        }

        interrupt++;

        // Обновлять экран каждое прерывание по умолчанию
        if ((interrupt % refreshRate) == 0) {
            hard.video().screenPaint();
            graphic.paintBuffer();
        }

        if (!fullSpeed) {
            sleep();
        }
    }

    private void sleep() {
        // Trying to slow to 100%, browsers resolution on the system
        // time is not accurate enough to check every interrurpt. So
        // we check every 4 interrupts.
        if ((interrupt % 4) == 0) {
            long time = System.currentTimeMillis();
            long duration = time - last;
            last = time;
            // запомним текущее время, как предыдущее.
            if (duration < 40) {
                try {
                    Thread.sleep(50 - duration);
                } catch (Exception ignored) {
                    // do nothing
                }
            }
        }
    }

    public void repaint() {
        refreshNextInterrupt = true;
    }

    public void lostFocus() {
        Logger.debug("Lost focus");
        printIO(BORDER_PORT, 0x06);
        hard.ports().resetKeyboard();
    }

    public void gotFocus() {
        Logger.debug("Got focus");
        printIO(BORDER_PORT, 0x02);
        hard.ports().resetKeyboard();
    }

    public void handleKey(Key key) {
        if (key.numSlash()) {
            if (key.pressed()) {
                hard.loadRecord(RECORD_LOG_FILE);
            }
            return;
        }

        if (key.numStar()) {
            if (key.pressed()) {
                fullSpeed = !fullSpeed;
            }
            return;
        }

        if (key.pause()) {
            if (key.pressed()) {
                resetAtNextInterrupt = true;
            }
            return;
        }

        hard.ports().processKey(key);
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