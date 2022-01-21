package spec;

import java.awt.*;
import java.util.function.BiConsumer;

import static spec.Constants.SCREEN;

public class Video {

    // Screen stuff - метрики экрана
    public static final int width = 384;  // точек по X
    public static final int height = 256;  // точек по Y

    private Memory memory;
    private BiConsumer<Point, Color> drawer;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Video(Memory memory,
                 BiConsumer<Point, Color> drawer) {
        this.memory = memory;
        this.drawer = drawer;
    }

    public void screenPaint() {
        for (int offset = 0; offset <= SCREEN.length(); offset++) {
            int pattern = memory.read8(SCREEN.begin() + offset);
            int x = (offset & 0x3F00) >> 5;
            int y = offset & 0x00FF;
            for (int i = 0; i < 8; i++) {
                Color col = ((pattern & (1 << i)) == 0) ? Color.BLACK : Color.WHITE;
                drawer.accept(new Point(x + (7 - i), y), col);
            }
        }
    }
}