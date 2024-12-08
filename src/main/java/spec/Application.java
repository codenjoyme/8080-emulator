package spec;

import spec.platforms.Lik;
import spec.platforms.Specialist;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

import static spec.Constants.*;
import static spec.Video.COLORS;

public class Application {

    public static final String SNAPSHOTS = "snapshots/";
    public static final String SCREENSHOTS = "screenshots/";

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");

    private boolean lik = true;

    private URL base;
    private Container parent;
    private Hardware hard;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Application(Container parent, URL base) {
        this.parent = parent;
        this.base = base;
        createFolders();

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, parent);

        hard.fileRecorder().with(RECORD_LOG_FILE);
        loadRoms(base);
    }

    private void createFolders() {
        new File(base.getFile() + SNAPSHOTS).mkdirs();
        new File(base.getFile() + SCREENSHOTS).mkdirs();
    }

    public void load(String rom) {
        hard.loadData(base, rom, lik);
    }

    private void loadRoms(URL base) {
        if (lik) {
            Lik.loadRom(base, hard.roms());
        } else {
            Specialist.loadRom(base, hard.roms());
        }

        reset();
    }

    public void lostFocus() {
        Logger.debug("Lost focus");
        hard.graphic().printIO(BORDER_PORT, 0x50);
        hard.ports().resetKeyboard();
    }

    public void gotFocus() {
        Logger.debug("Got focus");
        hard.graphic().printIO(BORDER_PORT, 0x30);
        hard.ports().resetKeyboard();
    }

    public void handleKey(Key key) {
        if (key.numZero()) {
            if (key.pressed()) {
                lik = !lik;
                Logger.debug("Switch to %s", lik ? "LIK" : "Specialist");
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
                hard.graphic().nextDrawMode();
            }
            return;
        }

        if (key.numThree()) {
            if (key.pressed()) {
                File file = new File(SCREENSHOTS + "screen_" + DATE_FORMAT.format(new Date()) + ".png");
                Logger.debug("Save screenshoot to %s", file);
                hard.png().drawToFile(SCREEN.begin(), file);
            }
            return;
        }

        if (key.numFour()) {
            if (key.pressed()) {
                Logger.debug("Stop record/replay");
                hard.record().reset();
            }
            return;
        }

        if (key.numFive()) {
            if (key.pressed()) {
                // TODO как сделать рабочим в веб версии?
                if (base.toString().startsWith("http")) return;

                openFileDialog(file -> hard.loadSnapshot(base, toRelative(base, file)),
                        base.getFile() + SNAPSHOTS,
                        "Snapshot file",
                        "snp");
            }
            return;
        }

        if (key.numSix()) {
            if (key.pressed()) {
                File file = new File(SNAPSHOTS + "snapshot_" + DATE_FORMAT.format(new Date()) + ".snp");
                hard.saveSnapshot(base, file.getPath());
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
                openFileDialog(file -> load(toRelative(base, file)),
                        base.getFile() + "/" + folder + "/apps",
                        "Data file",
                        "com", "rom", "rks", "bin", "mem");
            }
            return;
        }

        if (key.numStar()) {
            if (key.pressed()) {
                hard.timings().changeFullSpeed();
            }
            return;
        }

        if (key.pause()) {
            if (key.pressed()) {
                hard.timings().willReset();
            }
            return;
        }

        if (key.numMinus()) {
            if (key.pressed()) {
                hard.timings().increaseDelay();
            }
            return;
        }

        if (key.numPlus()) {
            if (key.pressed()) {
                hard.timings().decreaseDelay();
            }
            return;
        }

        hard.ports().processKey(key);
    }

    private String toRelative(URL base, File file) {
        try {
            return Paths.get(base.toURI()).relativize(Paths.get(file.getPath())).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public void start() {
        hard.start();
    }

    private void reset() {
        hard.reset();
    }

}