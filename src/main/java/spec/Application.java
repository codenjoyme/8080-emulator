package spec;

import spec.platforms.Platform;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;

import static spec.Constants.*;
import static spec.Files.RECORDS;
import static spec.Files.SNAPSHOTS;
import static spec.utils.FileUtils.toRelative;
import static spec.utils.ScreenUtils.addSpaces;
import static spec.utils.ScreenUtils.putInBorder;

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
    public Application(Container parent, String base, String platform, String rom, String command) {
        this.parent = parent;
        this.base = base;

        Files.createFolders(base);

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, parent, base);
        hard.startRecord();
        hard.load(platform, rom, command);
    }

    public void lostFocus() {
        Logger.debug("Lost focus");
        hard.lostFocus();
        hard.keyboard().reset(false);
    }

    public void gotFocus() {
        Logger.debug("Got focus");
        hard.gotFocus();
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
            Platform platform = hard.platform();
            openFileDialog(file -> hard.loadRom(base, toRelative(base, file), null),
                    base + platform.apps(),
                    hard.lastRom(),
                    "Data file",
                    "com", "rom", "rks", "bin", "mem", "bss", "bs1", "asm");
        }

        // ========= запись / воспроизведение =========

        // NUM_3 - загрузить запись
        if (key.numThree()) {
            openFileDialog(file -> hard.loadRecord(base, toRelative(base, file)),
                    base + RECORDS,
                    hard.lastRecord(),
                    "Recording file",
                    "rec");
        }

        // NUM_2 - сбросить запись/воспроизведение
        if (key.numTwo()) {
            Logger.debug("Stop record/replay");
            hard.record().stop();
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
                    hard.lastSnapshot(),
                    "Snapshot file",
                    "snp");
        }

        // NUM_5 - сохранить снапшот
        if (key.numFive()) {
            hard.saveSnapshot();
        }

        // NUM_4 - что-то еще со стейтом машины TODO потом решить
        if (key.numFour()) {
            Logger.debug("Do not implemented");
        }

        // ========= другие клавиши =========

        // NUM_9 - переключение режима аудио (звук вывода на магнитофон / звук динамика)
        // CTRL + NUM_9 - переключение режима аудио (ок ли мы с тем, что данные будут
        //                теряться при ускорении эмуляции или нет)
        if (key.numNine()) {
            if (key.ctrl()) {
                hard.audio().switchAllowDataSkip();
            } else {
                hard.audio().switchOut();
            }
            hard.refreshBorder();
        }

        // NUM_8 - сделать скриншот
        // CTRL + NUM_8 - скопировать так же распознанный на экране текст в буфер обмена
        if (key.numEight()) {
            String file = Files.newScreenshot(base);
            hard.png().drawToFile(SCREEN.begin(), file);
            if (key.ctrl()) {
                String text = addSpaces(hard.screenToText(file));
                Logger.info("Text from screen:\n%s", putInBorder(text));
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

    private void openFileDialog(Consumer<File> onSelect,
                                String directory,
                                String selectedFile,
                                String fileType,
                                String... ext) {
        JFileChooser files = new JFileChooser();
        if (selectedFile != null) {
            File file = new File(selectedFile);
            files.setSelectedFile(file);
            files.setCurrentDirectory(file.getParentFile());
        } else {
            files.setCurrentDirectory(new File(directory));
        }
        files.setDialogTitle("Select " + fileType);
        files.setFileFilter(new FileNameExtensionFilter(String.format(
                "%s %s", fileType, Arrays.asList(ext)), ext));
        int option = files.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = files.getSelectedFile();
            if (file.exists()) {
                onSelect.accept(file);
            }
        }
    }

    public void start() {
        gotFocus();
        hard.start();
    }

    public Hardware hardware() {
        return hard;
    }
}