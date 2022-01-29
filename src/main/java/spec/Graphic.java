package spec;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Graphic implements Video.Drawer {

    private int width;
    private int height;
    private int border;

    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

    private Graphics scene;
    private Image bufferImage;
    private Graphics buffer;

    public Graphic(int width, int height, int border, Container parent) {
        this.width = width;
        this.height = height;
        this.border = border;

        Canvas canvas = new Canvas();
        // переопределяем лиснер только для Swing приложения,
        // иначе не будут отлавливаться там клавиши так как canvas перекрывает
        if (parent.getKeyListeners().length > 0) {
            canvas.addKeyListener(parent.getKeyListeners()[0]);
        }
        canvas.setVisible(true);
        canvas.setSize(width + 2 * border, height + 2 * border);
        parent.add(canvas);

        // тут мы кешируем уже отрисованное из видеопамяти изображение
        bufferImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), TYPE_INT_ARGB);
        // через него мы рисуем на cache-image пиксели
        buffer = bufferImage.getGraphics();
        // тут мы рисуем результат, когда потребуется обновить часть окна
        scene = canvas.getGraphics();
    }

    @Override
    public void draw(int x, int y, Image pattern) {
        buffer.drawImage(pattern, border + x, border + y, null);
    }

    public void changeColor(Color color) {
        newBorder = color;
    }

    public void refreshBorder() {
        currentBorder = null; // обновить Border
    }

    private void borderPaint() {
        if (border == 0) { // если бордюра нет - ничего не делать!
            return;
        }
        if (currentBorder == newBorder) { // цвет не менялся
            return;
        }
        currentBorder = newBorder;
        buffer.setColor(currentBorder);
        buffer.fillRect(
                0,
                0,
                width + 2 * border,
                border);
        buffer.fillRect(
                0,
                0,
                border,
                height + 2 * border);
        buffer.fillRect(
                width + border,
                0,
                border,
                height + 2 * border);
        buffer.fillRect(
                0,
                height + border,
                width + 2 * border,
                border);
    }

    public void paintBuffer() {
        borderPaint();
        scene.drawImage(bufferImage, 0, 0, null);
    }
}