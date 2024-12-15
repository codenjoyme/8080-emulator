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

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, parent);

        hard.fileRecorder().with(Files.newRecord());
        hard.fileRecorder().startWriting();
        hard.romSwitcher().loadRoms(base);
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
            File file = Files.newScreenshot();
            Logger.debug("Save screenshot to %s", file.getAbsolutePath());
            hard.png().drawToFile(SCREEN.begin(), file);
        }

        if (key.numFour()) {
            Logger.debug("Stop record/replay");
            hard.record().reset();
        }

        if (key.numFive()) {
            // TODO как сделать рабочим в веб версии?
            if (base.toString().startsWith("http")) return;

            openFileDialog(file -> hard.loadSnapshot(base, toRelative(base, file)),
                    base.getFile() + SNAPSHOTS,
                    "Snapshot file",
                    "snp");
        }

        if (key.numSix()) {
            File file = Files.newSnapshot();
            hard.saveSnapshot(base, file.getPath());
        }

        if (key.numSlash()) {
            // TODO как сделать рабочим в веб версии?
            if (base.toString().startsWith("http")) return;

            openFileDialog(file -> hard.loadRecord(file.getAbsolutePath()),
                    base.getFile() + RECORDS,
                    "Recording file",
                    "rec");
        }

        if (key.numComma()) {
            // TODO как сделать рабочим в веб версии?
            if (base.toString().startsWith("http")) return;

            RomSwitcher switcher = hard.romSwitcher();
            Platform platform = switcher.current();
            openFileDialog(file -> switcher.load(base, toRelative(base, file)),
                    base.getFile() + platform.apps(),
                    "Data file",
                    "com", "rom", "rks", "bin", "mem");
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
        return;
    }

    public static String toRelative(URL base, File file) {
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

    public void load(String rom) {
        hard.romSwitcher().load(base, rom);
    }
}