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

    private int refreshRate = 1;  // refresh every 'n' interrupts

    private int interrupt = 0;

    // TODO а точно тут надо так заморачиваться с многопоточностью?
    private boolean willReset = false;
    private boolean willPause = false;
    private boolean willRefresh = true;

    private long last = 0;
    private int delay = 100;
    private boolean fullSpeed = false;
    private boolean lik = true;

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
            protected void update() {
                updateState();
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
        if (willPause) {
            while (willPause) {
                if (willRefresh) {
                    willRefresh = false;
                    graphic.refreshBorder();
                    graphic.paintBuffer();
                }
            }
        }

        if (willRefresh) {
            willRefresh = false;
            graphic.refreshBorder();
            graphic.paintBuffer();
        }

        if (willReset) {
            Logger.info("Reset Hardware!");
            willReset = false;
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
                try {
                    Thread.sleep(delay - duration);
                } catch (Exception ignored) {
                    // do nothing
                }
            }
        }
    }

    public void repaint() {
        willRefresh = true;
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
        if (key.numZero()) {
            if (key.pressed()) {
                lik = !lik;
                hard.pause();
                loadRoms(base);
                hard.reset();
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
                openFileDialog(file -> hard.loadData(file.getAbsolutePath(), lik),
                        base.getFile() + "/" + folder + "/apps",
                        "Data file",
                        "com", "rom", "rks", "bin");
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
                willReset = true;
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
                    delay = (int)(delay / 0.8);
                }
                Logger.debug("Delay increased: " + delay);
            }
            return;
        }

        if (key.numPlus()) {
            if (key.pressed()) {
                delay = (int)(delay * 0.8);
                Logger.debug("Delay decreased: " + delay);
            }
            return;
        }


        hard.ports().processKey(key);
    }

    private void openFileDialog(Consumer<File> onSelect,
                                String directory,
                                String fileType,
                                String... ext)
    {
        JFileChooser files = new JFileChooser();
        files.setCurrentDirectory(new File(directory));
        files.setDialogTitle("Select " + fileType);
        files.setFileFilter(new FileNameExtensionFilter(String.format(
                "%s %s", fileType, Arrays.asList(ext)), ext));
        int option = files.showOpenDialog(parent);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = files.getSelectedFile();
            onSelect.accept(file);
        }
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