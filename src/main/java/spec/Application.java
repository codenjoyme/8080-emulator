package spec;

import spec.platforms.Lik;
import spec.platforms.Specialist;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.function.Consumer;

import static spec.Constants.*;
import static spec.Video.COLORS;

public class Application {

    private int interrupt = 0;
    private int refreshRate = REFRESH_RATE;

    // TODO а точно тут надо так заморачиваться с многопоточностью?
    private boolean willReset = false;

    private long last = 0;
    private int delay = CPU_INTERRUPT_DELAY;
    private boolean fullSpeed = false;
    private boolean lik = true;

    // 0 - бордюр подсвечивает активно ли окно
    // 1 - бордюр подсвечивает запись байта в порт А
    // 2 - бордюр подсвечивает запись байта в порт B
    // 3 - бордюр подсвечивает запись байта в порт C
    // 4 - бордюр подсвечивает запись байта в порт RgSYS
    private int ioDrawMode = 0;

    private URL base;
    private Container parent;
    private Graphic graphic;
    private Hardware hard;

    private long time;
    private int iterations;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Application(Container parent, URL base) {
        this.parent = parent;
        this.base = base;
        graphic = new Graphic(SCREEN_WIDTH, SCREEN_HEIGHT, BORDER_WIDTH, parent);

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, graphic) {

            @Override
            protected void out8(int port, int bite) {
                printIO(port, bite);
            }

            @Override
            protected void outPort8(int port, int bite) {
                printIO(port, bite);
            }

            @Override
            protected void update() {
                updateState();
            }
        };

        hard.fileRecorder().with(RECORD_LOG_FILE);
        loadRoms(base);
    }

    private void printIO(int port, int bite) {
        switch (ioDrawMode) {
            case 0 : {
                if (port != BORDER_PORT) {
                    return;
                }
            } break;

            case 1 : {
                if (port != IOPorts.PortA) {
                    return;
                }
            } break;

            case 2 : {
                if (port != IOPorts.PortB) {
                    return;
                }
            } break;

            case 3 : {
                if (port != IOPorts.PortC) {
                    return;
                }
            } break;

            case 4 : {
                if (port != IOPorts.RgRGB) {
                    return;
                }
            } break;

        }

        graphic.refreshBorder();
        graphic.changeColor(COLORS[bite]);
    }

    public void load(String rom) {
        hard.loadData(rom, lik);
    }

    private void loadRoms(URL base) {
        if (lik) {
            Lik.loadRom(base, hard.roms());
        } else {
            Specialist.loadRom(base, hard.roms());
        }

        String snapshot = null; // TODO научиться сохранять и загружать снепшоты
        if (snapshot != null) {
            loadSnapshot(base, snapshot);
        } else {
            reset();
        }
    }

    private void updateState() {
        profiling();

        if (willReset) {
            Logger.info("Reset Hardware!");
            willReset = false;
            hard.reset();
        }

        interrupt++;

        // Обновлять экран каждое прерывание по умолчанию
        if ((interrupt % refreshRate) == 0) {
            hard.video().screenPaint();
        }

        hard.audio().tick();

        if (!fullSpeed) {
            sleep();
        }
    }

    private void profiling() {
        if (++iterations % 1000 == 0) {
            if (time != 0) {
                Logger.debug("Time per 10k interrupts: %ss", 1.0D * (now() - time) / 1000);
            }
            time = now();
        }
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private void sleep() {
        // Trying to slow to 100%, browsers resolution on the system
        // time is not accurate enough to check every interrurpt. So
        // we check every 4 interrupts.
        if ((interrupt % 4) == 0) {
            long time = now();
            long duration = time - last;
            last = time;
            // запомним текущее время, как предыдущее.
            if (duration < delay) {
                sleep(delay - duration);
            }
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {
            // do nothing
        }
    }

    public void lostFocus() {
        Logger.debug("Lost focus");
        printIO(BORDER_PORT, 0x50);
        hard.ports().resetKeyboard();
    }

    public void gotFocus() {
        Logger.debug("Got focus");
        printIO(BORDER_PORT, 0x30);
        hard.ports().resetKeyboard();
    }

    public void handleKey(Key key) {
        if (key.numZero()) {
            if (key.pressed()) {
                lik = !lik;
                hard.pause();
                loadRoms(base);
                hard.reset();
            }
            return;
        }

        if (key.numOne()) {
            if (key.pressed()) {
                if (hard.isPaused()) {
                    hard.resume();
                } else {
                    hard.pause();
                }
            }
            return;
        }

        if (key.numTwo()) {
            if (key.pressed()) {
                ioDrawMode++;
                if (ioDrawMode == 5) {
                    ioDrawMode = 0;
                    printIO(BORDER_PORT, 0x30);
                } else {
                    printIO(BORDER_PORT, 0x00);
                }
                graphic.refreshBorder();
                Logger.debug("IO Draw Mode: " + ioDrawMode);
            }
            return;
        }

        if (key.numSlash()) {
            if (key.pressed()) {
                // TODO как сделать рабочим в веб версии?
                if (base.toString().startsWith("http")) return;

                openFileDialog(file -> hard.loadRecord(file.getAbsolutePath()),
                        ".",
                        "Recording file",
                        "rec");
            }
            return;
        }

        if (key.numComma()) {
            if (key.pressed()) {
                // TODO как сделать рабочим в веб версии?
                if (base.toString().startsWith("http")) return;

                String folder = lik ? "lik" : "specialist";
                openFileDialog(file -> load(file.getAbsolutePath()),
                        base.getFile() + "/" + folder + "/apps",
                        "Data file",
                        "com", "rom", "rks", "bin", "mem");
            }
            return;
        }

        if (key.numStar()) {
            if (key.pressed()) {
                fullSpeed = !fullSpeed;
                if (fullSpeed) {
                    refreshRate = MAX_REFRESH_RATE;
                } else {
                    refreshRate = REFRESH_RATE;
                }
            }
            return;
        }

        if (key.pause()) {
            if (key.pressed()) {
                willReset = true;
            }
            return;
        }

        if (key.numMinus()) {
            if (key.pressed()) {
                if (delay < 10) {
                    delay++;
                } else {
                    delay = (int) (delay / 0.8);
                }
                Logger.debug("Delay increased: " + delay);
            }
            return;
        }

        if (key.numPlus()) {
            if (key.pressed()) {
                delay = (int) (delay * 0.8);
                Logger.debug("Delay decreased: " + delay);
            }
            return;
        }


        hard.ports().processKey(key);
    }

    private void openFileDialog(Consumer<File> onSelect,
                                String directory,
                                String fileType,
                                String... ext) {
        JFileChooser files = new JFileChooser();
        files.setCurrentDirectory(new File(directory));
        files.setDialogTitle("Select " + fileType);
        files.setFileFilter(new FileNameExtensionFilter(String.format(
                "%s %s", fileType, Arrays.asList(ext)), ext));
        int option = files.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = files.getSelectedFile();
            onSelect.accept(file);
        }
    }

    private void loadSnapshot(URL base, String snapshot) {
        hard.loadSnapshot(base, snapshot);
    }

    public void start() {
        hard.start();
    }

    private void reset() {
        hard.reset();
    }

}