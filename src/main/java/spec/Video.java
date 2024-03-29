package spec;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static spec.Constants.SCREEN;
import static spec.WordMath.BITE;

public class Video {

    public static Color[] COLORS = new Color[BITE];

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
        setupColors();
    }

    private void setupColors() {
        for (int bite = 0; bite < 0xFF; bite++) {
            COLORS[bite] = new Color(
                    (bite & 0b1100_0000),
                    (bite & 0b0011_1000) << 2,
                    (bite & 0b0000_0111) << 5);
        }
    }

    public interface Drawer {
        void draw(int x, int y, Image pattern);

        void done();
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
                if (drawer != null) {
                    drawer.draw(toWidth(px), y, pattern.image());
                }
                changes[px][y] = null;
            }
        }
        if (drawer != null) {
            drawer.done();
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