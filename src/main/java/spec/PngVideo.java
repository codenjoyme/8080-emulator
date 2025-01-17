package spec;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static spec.Constants.SCREEN_WIDTH;

public class PngVideo implements Video.Drawer {

    private Graphics graphics;
    private BufferedImage image;
    private Video video;
    private Memory memory;

    public PngVideo(Video video, Memory memory) {
        this.video = video;
        this.memory = memory;
        image = new BufferedImage(video.width(), video.height(), TYPE_INT_ARGB);
        graphics = image.getGraphics();
        video.drawer(this);
    }

    /**
     * It will print the memory to the file like this range is a video memory.
     *
     * @param range    range of memory to print
     * @param maxWidth maximum width of the image
     * @param memory   memory to read from
     * @param path     file to write to
     */
    public static void drawToFile(Range range, int maxWidth, Memory memory, String path) {
        int width = maxWidth - maxWidth % Video.PATTERN;
        int pwidth = width / Video.PATTERN;
        int notEnoughBytes = Math.floorMod(range.length(), pwidth);
        int height = range.length() / pwidth + (notEnoughBytes > 0 ? 1 : 0);

        Video video = new Video(width, height){
            public void plot(int offset, int pattern) {
                int px = offset % pwidth;
                int y = offset / pwidth;
                changes[px][y] = pattern(pattern);
            }
        };

        PngVideo png = new PngVideo(video, memory);
        png.drawToFile(range.begin(), path);
    }

    @Override
    public void draw(int x, int y, Image pattern) {
        graphics.drawImage(pattern, x, y, null);
    }

    @Override
    public void done() {
        // do nothing
    }

    public void drawToFile(int start, String path) {
        Logger.debug("Saving screenshot to %s", path);

        video.redraw(start, memory);

        try {
            ImageIO.write(image, "PNG", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}