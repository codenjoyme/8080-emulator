package spec;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphic {

    private int width;
    private int height;
    private int borderWidth;

    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

    private Container container;
    private Graphics background;

    private Canvas canvas;
    private Graphics scene;

    private Image bufferImage;
    private Graphics buffer;

    public Graphic(int width, int height, int borderWidth, Container parent) {
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;
        container = parent;

        // мы рисуем на канве, чтобы не мерцало изображение
        canvas = new Canvas();
        canvas.setSize(width, height);
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
        bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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