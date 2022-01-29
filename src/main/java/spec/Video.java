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
    public static final int PATTERN_WIDTH = 8;

    private int width;
    private int height;
    private Drawer drawer;
    private Pattern[][] colors;
    private Pattern[] patterns;

    public Video(int width, int height) {
        this.width = width / PATTERN_WIDTH;
        this.height = height;
        colors = new Pattern[width][height];
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

        public Pattern(int pattern) {
            image = new BufferedImage(PATTERN_WIDTH, 1, TYPE_INT_ARGB);
            buffer = image.getGraphics();
            for (int x = 0; x < PATTERN_WIDTH; x++) {
                Color color = ((pattern & (1 << x)) == 0) ? BLACK : WHITE;
                buffer.setColor(color);
                buffer.fillRect(PATTERN_WIDTH - 1 - x, 0, 1, 1);
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
        for (int px = 0; px < width; px++) {
            for (int y = 0; y < height; y++) {
                colors[px][y] = pattern(0);
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
        int x = ((offset & 0x3F00) >> 5) / PATTERN_WIDTH;
        int y = offset & 0x00FF;
        colors[x][y] = pattern(pattern);
    }

    public void screenPaint() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (colors[x][y] == null) continue;

                Pattern pattern = colors[x][y];
                if (drawer != null){
                    drawer.draw(x * PATTERN_WIDTH, y, pattern.image());
                }
                colors[x][y] = null;
            }
        }
    }

    public int width() {
        return width * PATTERN_WIDTH;
    }

    public int height() {
        return height;
    }
}