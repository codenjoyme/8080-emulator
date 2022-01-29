package spec;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static spec.Constants.SCREEN;

public class Video {

    // 16 цветов - массив цветов "Специалист"
    private static final int str = 238;
    public static final Color[] COLORS = {
            Color.black,             // 00-черный
            // тусклые цвета
            Color.blue,              // 01 синий
            Color.green,             // 02 зелёный
            Color.cyan,              // 03 бирюзовый
            Color.red,               // 04 красный
            Color.magenta,           // 05 фиолетовый
            Color.yellow,            // 06 коричневый
            Color.white,             // 07 белый
            new Color(0, 0, 0),      // 08 серый
            // яркие цвета
            new Color(0, 0, str),    // 09 голубой
            new Color(0, str, 0),    // 0A светло-зелёный
            new Color(0, str, str),  // 0B светло-бирюзовый
            new Color(str, 0, 0),    // 0C розовый
            new Color(str, 0, str),  // 0D светло-фиолетовый
            new Color(str, str, 0),  // 0E желтый
            new Color(str, str, str) // 0F ярко-белый
    };
    public static final int PATTERN = 0x08;

    private int pwidth; // ширина экрана в 8 байтовых паттернах
    private int height; // высота экрана в пикселях
    private Drawer drawer;
    private Pattern[][] changes;
    private Pattern[] patterns;

    public Video(int width, int height) {
        this.pwidth = fromWidth(width);
        this.height = height;
        changes = new Pattern[pwidth][height];
        patterns = new Pattern[0x100];
        clean();
    }

    @FunctionalInterface
    public interface Drawer {
        void draw(int x, int y, Image pattern);
    }

    public static class Pattern {

        private Graphics buffer;
        private BufferedImage image;

        public Pattern(int bite) {
            image = new BufferedImage(PATTERN, 1, TYPE_INT_ARGB);
            buffer = image.getGraphics();
            for (int x = 0; x < PATTERN; x++) {
                Color color = ((bite & (1 << x)) == 0) ? BLACK : WHITE;
                buffer.setColor(color);
                buffer.fillRect(PATTERN - 1 - x, 0, 1, 1);
            }
        }

        public Image image() {
            return image;
        }
    }

    public void drawer(Drawer drawer) {
        this.drawer = drawer;
    }

    public void clean() {
        for (int px = 0; px < pwidth; px++) {
            for (int y = 0; y < height; y++) {
                changes[px][y] = pattern(0);
            }
        }
    }

    private Pattern pattern(int bite) {
        Pattern result = patterns[bite];
        if (result == null) {
            result = patterns[bite] = new Pattern(bite);
        }
        return result;
    }

    public void plot(int addr, int pattern) {
        int offset = addr - SCREEN.begin();
        int px = fromWidth((offset & 0x3F00) >> 5);
        int y = offset & 0x00FF;
        changes[px][y] = pattern(pattern);
    }

    public void screenPaint() {
        for (int px = 0; px < pwidth; px++) {
            for (int y = 0; y < height; y++) {
                if (changes[px][y] == null) continue;

                Pattern pattern = changes[px][y];
                if (drawer != null){
                    drawer.draw(toWidth(px), y, pattern.image());
                }
                changes[px][y] = null;
            }
        }
    }

    private int toWidth(int px) {
        return px * PATTERN;
    }

    private int fromWidth(int width) {
        return width / PATTERN;
    }

    public int width() {
        return toWidth(pwidth);
    }

    public int height() {
        return height;
    }
}