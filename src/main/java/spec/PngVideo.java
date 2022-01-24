package spec;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static spec.Constants.SCREEN;
import static spec.Video.HEIGHT;
import static spec.Video.WIDTH;

public class PngVideo {

    private BufferedImage image;
    private Video video;
    private Memory memory;

    public PngVideo(Memory memory) {
        this.memory = memory;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        video = new Video((pt, color) -> {
            graphics.setColor(color);
            graphics.fillRect(pt.x, pt.y, 1, 1);
        });
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
