package spec;

import java.awt.*;
import java.util.function.BiConsumer;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
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

    private int width;
    private int height;
    private BiConsumer<Point, Color> drawer;
    private Color[][] colors;

    public Video(int width, int height) {
        this.width = width;
        this.height = height;
        colors = new Color[width][height];
        clean();
    }

    public void drawer(BiConsumer<Point, Color> drawer) {
        this.drawer = drawer;
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
                if (drawer != null){
                    drawer.accept(new Point(x, y), color);
                }
                colors[x][y] = null;
            }
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}