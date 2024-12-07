package spec;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class PngVideo implements Video.Drawer {

    private Graphics graphics;
    private BufferedImage image;
    private Video video;
    private Memory memory;

    public PngVideo(Video video, Memory memory) {
        this.video = video;
        video.drawer(this);
        this.memory = memory;
        image = new BufferedImage(video.width(), video.height(), TYPE_INT_ARGB);
        graphics = image.getGraphics();
    }

    @Override
    public void draw(int x, int y, Image pattern) {
        graphics.drawImage(pattern, x, y, null);
    }

    @Override
    public void done() {
        // do nothing
    }

    public void drawToFile(Range range, File file) {
        draw(range);

        try {
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Range range) {
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            video.plot(addr, memory.read8(addr));
        }
        video.screenPaint();
    }
}