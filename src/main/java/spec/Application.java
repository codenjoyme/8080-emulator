package spec;

import spec.platforms.Platform;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;

import static spec.Constants.*;
import static spec.Files.RECORDS;
import static spec.Files.SNAPSHOTS;

public class Application {

    private String base;
    private Container parent;
    private Hardware hard;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Application(Container parent, String base, String platform, String rom) {
        this.parent = parent;
        this.base = base;

        Files.createFolders(base);

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, parent);

        hard.fileRecorder().with(Files.newRecord(base));
        hard.fileRecorder().startWriting();
        load(platform, rom);
    }

    public void lostFocus() {
        Logger.debug("Lost focus");
        hard.graphic().printIO(BORDER_PORT, 0x50);
        hard.keyboard().reset(false);
    }

    public void gotFocus() {
        Logger.debug("Got focus");
        hard.graphic().printIO(BORDER_PORT, 0x30);
        hard.keyboard().reset(false);
    }

    public void handleKey(Key key) {
        if (!key.system()) {
            hard.keyboard().processKey(key);
            return;
        }

        if (!key.pressed()) {
            return;
        }

        if (key.numZero()) {
            hard.romSwitcher().nextRom(base);
        }

        if (key.numOne()) {
            hard.pauseResume();
        }

        if (key.numTwo()) {
            hard.graphic().nextDrawMode();
        }

        if (key.numThree()) {
            String file = Files.newScreenshot(base);
            hard.png().drawToFile(SCREEN.begin(), file);
        }

        if (key.numFour()) {
            Logger.debug("Stop record/replay");
            hard.record().reset();
        }

        if (key.numFive()) {
            openFileDialog(file -> hard.loadSnapshot(base, toRelative(base, file)),
                    base + SNAPSHOTS,
                    "Snapshot file",
                    "snp");
        }

        if (key.numSix()) {
            String file = Files.newSnapshot(base);
            hard.saveSnapshot(file);
        }

        if (key.numSlash()) {
            openFileDialog(file -> hard.loadRecord(base, toRelative(base, file)),
                    base + RECORDS,
                    "Recording file",
                    "rec");
        }

        if (key.numComma()) {
            RomSwitcher switcher = hard.romSwitcher();
            Platform platform = switcher.current();
            openFileDialog(file -> switcher.load(base, toRelative(base, file)),
                    base + platform.apps(),
                    "Data file",
                    "com", "rom", "rks", "bin", "mem", "bss", "bs1");
        }

        if (key.numStar()) {
            hard.timings().changeFullSpeed();
        }

        if (key.pause()) {
            hard.timings().willReset();
        }

        if (key.numMinus()) {
            hard.timings().increaseDelay();
        }

        if (key.numPlus()) {
            hard.timings().decreaseDelay();
        }
    }

    public static String toRelative(String base, File file) {
        try {
            String result = Paths.get(base).toAbsolutePath().relativize(Paths.get(file.getPath())).toString();
            // TODO это надо только для того, чтобы иметь возможность запускать приложение в веб версии, там с путями не все просто
            if (result.startsWith("../")) {
                return result.substring(3);
            }
            return result;
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
        gotFocus();
        hard.start();
    }

    private void load(String platform, String rom) {
        Logger.debug("Load platform: '%s', rom file: '%s'", platform, rom);

        if (platform == null && rom == null) {
            hard.romSwitcher().loadRoms(base);
        }
        if (platform != null) {
            hard.romSwitcher().selectRom(base, platform);
        }
        if (rom != null) {
            hard.romSwitcher().load(base, rom);
        }
    }
}