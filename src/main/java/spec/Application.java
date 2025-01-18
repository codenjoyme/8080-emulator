package spec;

import spec.platforms.Platform;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;

import static spec.Constants.*;
import static spec.Files.RECORDS;
import static spec.Files.SNAPSHOTS;
import static spec.RomLoader.TYPE_SNP;

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

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, parent, base);

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
        // обработка не системных клавиш
        if (!key.system()) {
            hard.keyboard().processKey(key);
            return;
        }

        // если кнопка отпущена, то ничего не делать - дальше только хоткеи
        if (!key.pressed()) {
            return;
        }

        // ======== сброс =========

        // PAUSE - сброс ємулятора
        if (key.pause()) {
            hard.timings().willReset();
        }

        // ========= загрузка ромов =========

        // NUM_0 - переключение ромов `Лик` и `Специалист`
        if (key.numZero()) {
            hard.romSwitcher().nextRom(base);
        }

        // NUM_. - загрузить данные из файла
        if (key.numDot()) {
            RomSwitcher switcher = hard.romSwitcher();
            Platform platform = switcher.current();
            openFileDialog(file -> switcher.load(base, toRelative(base, file)),
                    base + platform.apps(),
                    "Data file",
                    "com", "rom", "rks", "bin", "mem", "bss", "bs1");
        }

        // ========= запись / воспроизведение =========

        // NUM_3 - загрузить запись
        if (key.numThree()) {
            openFileDialog(file -> hard.loadRecord(base, toRelative(base, file)),
                    base + RECORDS,
                    "Recording file",
                    "rec");
        }

        // NUM_2 - сбросить запись/воспроизведение
        if (key.numTwo()) {
            Logger.debug("Stop record/replay");
            hard.record().reset();
        }

        // NUM_1 - что-то еще с записью TODO надо ли это?
        if (key.numOne()) {
            Logger.debug("Do not implemented");
        }

        // ========= работа со снепшотами =========

        // NUM_6 - загрузить снапшот
        if (key.numSix()) {
            openFileDialog(file -> hard.loadSnapshot(base, toRelative(base, file)),
                    base + SNAPSHOTS,
                    "Snapshot file",
                    "snp");
        }

        // NUM_5 - сохранить снапшот
        if (key.numFive()) {
            String file = Files.newSnapshot(base);
            hard.saveSnapshot(file);
        }

        // NUM_4 - что-то еще со стейтом машины TODO потом решить
        if (key.numFour()) {
            Logger.debug("Do not implemented");
        }

        // ========= другие клавиши =========

        // NUM_9 - загрузить что-то еще TODO потом решить
        if (key.numNine()) {
            Logger.debug("Do not implemented");
            // openFileDialog(file -> hard.loadSomething(base, toRelative(base, file)),
            //         base + SOMETHING,
            //         "Something file",
            //         "smth");
        }

        // NUM_8 - сделать скриншот
        // CTRL + NUM_8 - скопировать так же распознанный на экране текст в буфер обмена
        if (key.numEight()) {
            String file = Files.newScreenshot(base);
            hard.png().drawToFile(SCREEN.begin(), file);
            if (key.ctrl()) {
                String text = hard.screenToText(file);
                System.out.println(text);
                toClipboard(text);
            }
        }

        // NUM_7 - переключение режима отображения рамки (какой порт отображать)
        if (key.numSeven()) {
            hard.graphic().nextDrawMode();
        }

        // ======== управление эмулятором =========

        // NUM_/ - CPU пауза/продолжение
        if (key.numSlash()) {
            hard.pauseResume();
        }

        // NUM_* - включение максимальной скорости
        if (key.numStar()) {
            hard.timings().changeFullSpeed();
        }

        // NUM_- - замедление работы эмулятора
        if (key.numMinus()) {
            hard.timings().increaseDelay();
        }

        // NUM_+ - ускорение работы эмулятора
        if (key.numPlus()) {
            hard.timings().decreaseDelay();
        }
    }

    private void toClipboard(String text) {
        Logger.debug("Copy to clipboard");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);
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
        Logger.debug("Load platform2: '%s', file: '%s'", platform, rom);

        // ничего нет, грузим по умолчанию
        if (platform == null && rom == null) {
            hard.romSwitcher().loadRoms(base);
            return;
        }
        // грузим снепшот и только его
        if (rom != null && rom.endsWith("." + TYPE_SNP)) {
            // TODO #41 этот костыыль надо при загрузке снепшота из командной строки по полному
            //      пути там под windows не работает добавление base
            String realBase = rom.contains(":") ? "" : base;
            hard.forceLoadSnapshot(realBase, rom);
            return;
        }
        // грузим платформу если указана
        if (platform != null) {
            hard.romSwitcher().selectRom(base, platform);
        }
        // грузим файл если указан
        if (rom != null) {
            // TODO #41 этот костыыль надо при загрузке снепшота из командной строки по полному
            //      пути там под windows не работает добавление base
            String realBase = rom.contains(":") ? "" : base;
            hard.romSwitcher().load(realBase, rom);
        }
    }
}