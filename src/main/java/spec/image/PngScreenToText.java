package spec.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.joining;

public class PngScreenToText {

    private static final String LAT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CYR = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЭЮЯ";
    private static final String NUMBERS = "1234567890";
    private static final String SYMBOLS = "!\"#$%&'()-=+;:[]^*/?<,.>:\\";

    private static final String LAT_SYNONYM = "ABCEHKMOPTX";
    private static final String CYR_SYNONYM = "АВСЕНКМОРТХ";

    private Map<String, Character> map = new LinkedHashMap<>();

    private int width;
    private int height;
    private int pxWidth;
    private int pxHeight;

    public PngScreenToText() {
        process(new File("./src/main/resources/lik/docs/chars-map.png").getAbsolutePath(),
            NUMBERS + "\n" + // цифры
            SYMBOLS + "\n" + // спецсимволы
            LAT + "\n" +     // латиница
            CYR + "\n" +     // кириллица
            "█ \n" +         // курсор и пробел
            "ЪЁ");           // символы несуществующие в ЛИК
    }

    private static final int CHAR_WIDTH = 6;
    private static final int CHAR_HEIGHT = 10;

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
        width = pxWidth / CHAR_WIDTH;
        height = width * (pxHeight / CHAR_HEIGHT);

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
            int x = (i % width) * CHAR_WIDTH;
            int y = (i / width) * CHAR_HEIGHT;
            String mask = "";

            for (int row = 0; row < CHAR_HEIGHT; row++) {
                for (int col = 0; col < CHAR_WIDTH; col++) {
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
                ch = '¤';
            }
            if (newLine) {
                result.append("\n");
            }
            result.append(ch);
        });

        String string = result.toString();
        return Arrays.stream(string.split("\n"))
                // trim right only
                .map(s -> s.replaceAll("\\s+$", ""))
                .filter(s -> !s.isEmpty())
                .map(s -> changeToCyr(s, 3))
                .collect(joining("\n"));
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

                if (synonymIndex != -1) {
                    boolean influencedByCyrillic = checkForCyrillicInfluence(currentLine, i, maxDepth);

                    if (influencedByCyrillic) {
                        char cyrillicChar = CYR_SYNONYM.charAt(synonymIndex);
                        modifiedLine.setCharAt(i, cyrillicChar);
                        if (currentChar != cyrillicChar) {
                            changes = true;
                        }
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

    public static void main(String[] args) {
        PngScreenToText scanner = new PngScreenToText();

        String parse = scanner.parse("./src/test/resources/IntegrationTest/testLik/smoke/7_exit.png");

        System.out.println(parse);
        // prints
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
    }
}