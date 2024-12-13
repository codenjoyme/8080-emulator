package spec.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class PngScreenToText {

    private static final String EN = "ABCEHKMOPTX";
    private static final String RU = "АВСЕНКМОРТХ";

    private Map<String, Character> map = new LinkedHashMap<>();

    private int width;
    private int height;
    private int pxWidth;
    private int pxHeight;

    public PngScreenToText() {
        process(new File("./src/main/resources/lik/docs/chars-map.png").getAbsolutePath(),
            "1234567890\n" +
            "!\"#$%&'()-=+;:[]^*/?<,.>:\\\n" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n" +
            "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЭЮЯ\n" +
            "█ \n" +
            "ЪЁ");
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
            if (EN.contains(String.valueOf(ch))) {
                if (last.get() != null && RU.contains(String.valueOf(last.get()))) {
                    ch = RU.charAt(EN.indexOf(ch));
                }
            }
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
                .collect(Collectors.joining("\n"));
    }
}