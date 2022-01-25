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

    public PngVideo(Video video, Memory memory) {
        this.video = video;
        video.drawer(this::draw);
        this.memory = memory;
        image = new BufferedImage(video.width(), video.height(), TYPE_INT_ARGB);
        graphics = image.getGraphics();
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