package spec.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.joining;
import static spec.Constants.SCREEN_CHAR_HEIGHT;
import static spec.Constants.SCREEN_CHAR_WIDTH;

public class PngScreenToText {

    private static final String LAT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CYR = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЭЮЯ";
    private static final String NUMBERS = "1234567890";
    private static final String SYMBOLS = "!\"#$%&'()-=+;:[]^*/?<,.>_\\@";

    private static final String LAT_SYNONYM = "ABCEHKMOPTX";
    private static final String CYR_SYNONYM = "АВСЕНКМОРТХ";
    private static final char UNKNOWN = '¤';

    private Map<String, Character> map = new LinkedHashMap<>();

    private int width;
    private int height;
    private int pxWidth;
    private int pxHeight;

    public PngScreenToText(String base) {
        // TODO #42 Во все места конкатенации `base` и `file` добавить проверку на то что там между ними будет слеш
        process(new File(base + "lik/docs/screen/chars-map.png").getAbsolutePath(),
            NUMBERS + "\n" + // цифры
            SYMBOLS + "\n" + // спецсимволы
            LAT + "\n" +     // латиница
            CYR + "\n" +     // кириллица
            "█ \n" +         // курсор и пробел
            "ЪЁ");           // символы несуществующие в ЛИК

        map.put(
                "......\n" +
                ".█..█.\n" +
                "..██..\n" +
                ".█..█.\n" +
                ".█..█.\n" +
                ".█..█.\n" +
                "..██..\n" +
                ".█..█.\n" +
                "......\n" +
                "......",
                UNKNOWN);
    }

    private void process(String path, String text) {
        List<String> masks = masks(path);
        int x = 0;
        int y = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '\n') {
                x = 0;
                y++;
                continue;
            }
            String mask = masks.get(x + y * width);
            if (!map.containsKey(mask)) {
                map.put(mask, ch);
                map.put(invert(mask), ch);
            }
            x++;
        }
    }

    private List<String> masks(String path) {
        BufferedImage image = loadImage(path);
        List<String> result = new LinkedList<>();

        pxWidth = image.getWidth();
        pxHeight = image.getHeight();
        width = pxWidth / SCREEN_CHAR_WIDTH;
        height = width * (pxHeight / SCREEN_CHAR_HEIGHT);

        process(image, (mask, newLine) -> {
            result.add(mask);
        });

        return result;
    }

    private String invert(String mask) {
        return mask.replace("█", "_")
                .replace(".", "█")
                .replace("_", ".");
    }

    private void process(BufferedImage image, BiConsumer<String, Boolean> set) {
        for (int i = 0; i < height; i++) {
            int x = (i % width) * SCREEN_CHAR_WIDTH;
            int y = (i / width) * SCREEN_CHAR_HEIGHT;
            String mask = "";

            for (int row = 0; row < SCREEN_CHAR_HEIGHT; row++) {
                for (int col = 0; col < SCREEN_CHAR_WIDTH; col++) {
                    int pixel = image.getRGB(x + col, y + row);
                    switch (pixel) {
                        case 0xFFFFFFFF:
                            mask += "█";
                            break;
                        default: // black 0xFF000000 and others
                            mask += ".";
                            break;
                    }
                }
                mask += "\n";
            }
            set.accept(mask, x == 0);
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    public String parse(String path) {
        BufferedImage image = loadImage(path);

        StringBuilder result = new StringBuilder();
        AtomicReference<Character> last = new AtomicReference<>();
        process(image, (mask, newLine) -> {
            Character ch = map.get(mask);
            last.set(ch);
            if (ch == null) {
                ch = UNKNOWN;
            }
            if (newLine && result.length() > 0) {
                result.append("\n");
            }
            result.append(ch);
        });

        String string = result.toString();
        string = Arrays.stream(string.split("\n"))
                // trim right only
                .map(s -> s.replaceAll("\\s+$", ""))
                .map(s -> changeToCyr(s, 3))
                .collect(joining("\n"));
        // replace all last empty lines to ""
        string = string.replaceAll("(\n\\s*)+$", "");
        return string;
    }

    private String changeToCyr(String line, int maxDepth) {
        boolean changes;
        String currentLine = line;

        do {
            changes = false;
            StringBuilder modifiedLine = new StringBuilder(currentLine);

            for (int i = 0; i < currentLine.length(); i++) {
                char currentChar = currentLine.charAt(i);
                int synonymIndex = LAT_SYNONYM.indexOf(currentChar);
                if (synonymIndex == -1) continue;

                boolean influencedByCyrillic = checkForCyrillicInfluence(currentLine, i, maxDepth);

                if (influencedByCyrillic) {
                    char cyrillicChar = CYR_SYNONYM.charAt(synonymIndex);
                    modifiedLine.setCharAt(i, cyrillicChar);
                    if (currentChar != cyrillicChar) {
                        changes = true;
                    }
                }
            }

            currentLine = modifiedLine.toString();
        } while (changes);

        return currentLine;
    }

    private boolean checkForCyrillicInfluence(String line, int index, int maxDepth) {
        // Проверка влево от текущего символа
        for (int i = index - 1; i >= Math.max(0, index - maxDepth); i--) {
            if (isStoppingCharacter(line.charAt(i))) {
                break;
            }
            if (CYR.contains(String.valueOf(line.charAt(i)))) {
                return true;
            }
        }
        // Проверка вправо от текущего символа
        for (int i = index + 1; i < Math.min(line.length(), index + maxDepth + 1); i++) {
            if (isStoppingCharacter(line.charAt(i))) {
                break;
            }
            if (CYR.contains(String.valueOf(line.charAt(i)))) {
                return true;
            }
        }

        return false;
    }

    private boolean isStoppingCharacter(char ch) {
        return !Character.isLetter(ch) || Character.isWhitespace(ch);
    }

    public void draw(String text, String outputPath) {
        String[] lines = text.split("\n");

        BufferedImage image = new BufferedImage(pxWidth, pxHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pxWidth, pxHeight);

        for (int lineNum = 0; lineNum < lines.length; lineNum++) {
            String line = lines[lineNum];
            for (int charNum = 0; charNum < line.length(); charNum++) {
                char ch = line.charAt(charNum);
                String mask = getMaskByChar(ch);
                if (mask == null) {
                    mask = getMaskByChar(' ');
                }
                drawChar(mask, g, charNum * SCREEN_CHAR_WIDTH, lineNum * SCREEN_CHAR_HEIGHT);
            }
        }

        g.dispose();

        try {
            ImageIO.write(image, "PNG", new File(outputPath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to save image: " + outputPath, e);
        }
    }

    private void drawChar(String mask, Graphics2D g, int x, int y) {
        String[] maskLines = mask.split("\n");
        g.setColor(Color.WHITE);
        for (int i = 0; i < maskLines.length; i++) {
            for (int j = 0; j < maskLines[i].length(); j++) {
                if (maskLines[i].charAt(j) == '█') {
                    g.fillRect(x + j, y + i, 1, 1);
                }
            }
        }
    }

    private String getMaskByChar(char ch) {
        for (Map.Entry<String, Character> entry : map.entrySet()) {
            int index = CYR_SYNONYM.indexOf(ch);
            if (index != -1) {
                char ch2 = LAT_SYNONYM.charAt(index);
                if (entry.getValue().equals(ch2)) {
                    return entry.getKey();
                }
            }
            if (entry.getValue().equals(ch)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        PngScreenToText scanner = new PngScreenToText("src/main/resources");

        // парсим картинку в текст
        String parse = scanner.parse("./src/test/resources/IntegrationTest/testLik/smoke/7_memory-exit.png");

        // печатаем на экран
        System.out.println(parse);
        //         0   1   2   3   4   5   6   7     01234567
        //         8   9   A   B   C   D   E   F     89ABCDEF
        // 9000:   00  00  00  00  00  00  00  00    ........
        // 9008:   00  00  00  00  00  00  00  00    ........
        // 9010:   00  00  00  00  00  00  00  00    ........
        // 9018:   00  00  00  00  00  00  00  38    .......8
        // 9020:   45  45  3D  05  09  70  00  00    EE=..П..
        // 9028:   00  38  45  45  3D  05  09  70    .8EE=..П
        // 9030:   00  00  00  38  45  45  3D  05    ...8EE=.
        // 9038:   09  70  00  00  00  38  45  45    .П...8EE
        // 9040:   3D  05  09  70  00  00  00  38    =..П...8
        // 9048:   45  45  3D  05  09  70  00  00    EE=..П..
        // 9050:   00  38  45  45  3D  05  09  70    .8EE=..П
        // 9058:   00  00  00  38  45  45  3D  05    ...8EE=.
        // 9060:   09  70  00  00  00  38  45  45    .П...8EE
        // 9068:   3D  05  09  70  00  00  00  38    =..П...8
        // 9070:   45  45  3D  05  09  70  00  00    EE=..П..
        // 9078:   00  38  45  45  3D  05  09  70    .8EE=..П
        // ===>
        // * MOHИTOP-1M *
        // ===>█

        // сохраняем текст в картинку
        String path = new File("target/screen.png").getAbsolutePath();
        scanner.draw(parse, path);
        System.out.println("Saved to: " + path);
    }
}