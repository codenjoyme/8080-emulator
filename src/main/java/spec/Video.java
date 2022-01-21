package spec;

import java.awt.*;
import java.util.function.BiConsumer;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static spec.Constants.SCREEN;

public class Video {

    public static final int width = 384;
    public static final int height = 256;

    private BiConsumer<Point, Color> drawer;
    private Color[][] colors = new Color[width][height];

    public Video(BiConsumer<Point, Color> drawer) {
        this.drawer = drawer;
        clean();
    }

    private void clean() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                colors[x][y] = BLACK;
            }
        }
    }

    public void plot(int addr, int pattern) {
        int offset = addr - SCREEN.begin();
        int x = (offset & 0x3F00) >> 5;
        int y = offset & 0x00FF;
        for (int i = 0; i < 8; i++) {
            Color col = ((pattern & (1 << i)) == 0) ? BLACK : WHITE;
            colors[x + (7 - i)][y] = col;
        }
    }

    public void screenPaint() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (colors[x][y] == null) continue;

                Color color = colors[x][y];
                drawer.accept(new Point(x, y), color);
                colors[x][y] = null;
            }
        }
    }
}