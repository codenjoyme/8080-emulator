package spec;

import java.awt.*;
import java.awt.image.BufferedImage;

import static spec.Application.BORDER_WIDTH;
import static spec.Video.HEIGHT;
import static spec.Video.WIDTH;

public class Graphic {

    private int borderWidth;

    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

    private Container container;
    private Graphics background;

    private Canvas canvas;
    private Graphics scene;

    private Image bufferImage;
    private Graphics buffer;

    public Graphic(int borderWidth, Container parent) {
        this.borderWidth = borderWidth;
        container = parent;

        // мы рисуем на канве, чтобы не мерцало изображение
        canvas = new Canvas();
        canvas.setSize(WIDTH, HEIGHT);
        // переопределяем лиснер только для Swing приложения,
        // иначе не будут отлавливаться там клавиши так как canvas перекрывает
        if (container.getKeyListeners().length > 0) {
            canvas.addKeyListener(container.getKeyListeners()[0]);
        }
        setBorderWidth(borderWidth);
        canvas.setVisible(true);
        sleep(50);
        container.add(canvas);

        // тут мы кешируем уже отрисованное из видеопамяти изображение
        bufferImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        // через него мы рисуем на cache-image пиксели
        buffer = bufferImage.getGraphics();
        // на нем мы рисуем рамку, это фон под canvas
        background = container.getGraphics();
        // а через это мы рисуем на канве
        scene = canvas.getGraphics();
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    public static Dimension getMinimumSize(int dx, int dy) {
        return new Dimension(
                Video.WIDTH + BORDER_WIDTH * 2 + dx,
                Video.HEIGHT + BORDER_WIDTH * 2 + dy);
    }

    public void drawPixel(Point pt, Color color) {
        buffer.setColor(color);
        buffer.fillRect(pt.x, pt.y, 1, 1);
    }

    private void setBorderWidth(int width) {
        borderWidth = width;
        canvas.setLocation(borderWidth, borderWidth);
    }

    public void changeColor(Color color) {
        newBorder = color;
    }

    public void refreshBorder() {
        currentBorder = null; // обновить Border
    }

    private void borderPaint() {
        if (borderWidth == 0) { // если бордюра нет - ничего не делать!
            return;
        }
        if (currentBorder == newBorder) { // цвет не менялся
            return;
        }
        currentBorder = newBorder;
        background.setColor(currentBorder);
        background.fillRect(0, 0,
                container.getWidth(),
                container.getHeight());
    }

    public void paintBuffer() {
        scene.drawImage(bufferImage, 0, 0, null);
        borderPaint();
    }

    public void requestFocus() {
        canvas.requestFocus();
    }
}