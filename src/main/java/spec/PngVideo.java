package spec;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static spec.Constants.SCREEN;

public class PngVideo {

    private Graphics graphics;
    private BufferedImage image;
    private Video video;
    private Memory memory;

    public PngVideo(int width, int height, Memory memory) {
        this.memory = memory;
        image = new BufferedImage(width, height, TYPE_INT_ARGB);
        graphics = image.getGraphics();
        video = new Video(width, height, this::draw);
    }

    private void draw(Point pt, Color color) {
        graphics.setColor(color);
        graphics.fillRect(pt.x, pt.y, 1, 1);
    }

    public void drawToFile(String name) {
        draw();

        try {
            ImageIO.write(image, "PNG", new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        for (int addr = SCREEN.begin(); addr <= SCREEN.end(); addr++) {
            video.plot(addr, memory.read8(addr));
        }
        video.screenPaint();
    }
}