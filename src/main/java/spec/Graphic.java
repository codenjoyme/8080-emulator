package spec;

import javax.swing.*;
import java.awt.*;

public class Graphic implements Video.Drawer {

    private int width;
    private int height;
    private int border;

    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

    private Image image;
    private Graphics buffer;

    private JPanel panel;

    public Graphic(int width, int height, int border, Container parent) {
        this.width = width;
        this.height = height;
        this.border = border;

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                if (buffer == null) return;

                refreshBorder();
                Graphic.this.paint(graphics);
            }
        };
        // переопределяем лиснер только для Swing приложения,
        // иначе не будут отлавливаться там клавиши так как panel перекрывает
        if (parent.getKeyListeners().length > 0) {
            panel.addKeyListener(parent.getKeyListeners()[0]);
        }
        panel.setVisible(true);
        panel.setSize(width + 2 * border, height + 2 * border);
        parent.add(panel);
        
        // тут мы кешируем уже отрисованное из видеопамяти изображение
        image = parent.createImage(panel.getWidth(), panel.getHeight());
        // через него мы рисуем на cache-image пиксели
        buffer = image.getGraphics();
    }

    @Override
    public void draw(int x, int y, Image pattern) {
        buffer.drawImage(pattern, border + x, border + y, null);
    }

    @Override
    public void done() {
        buffer.drawImage(image, 0, 0, null);
        repaint();
    }

    public void changeColor(Color color) {
        newBorder = color;
    }

    public void refreshBorder() {
        currentBorder = null; // обновить Border
    }

    private void drawBorder() {
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

    public void paint(Graphics graphics) {
        drawBorder();
        graphics.drawImage(image, 0, 0, null);
    }

    public void repaint() {
        panel.repaint();
    }
}