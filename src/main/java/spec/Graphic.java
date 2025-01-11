package spec;

import javax.swing.*;
import java.awt.*;

public class Graphic implements Video.Drawer {
    
    // размеры оригинального экрана
    private int width;
    private int height;
    
    // размер рамки вокруг экрана
    private int border;
    
    // размеры экрана с учетом рамки
    private final int fullWidth;
    private final int fullHeight;

    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

    private Image image; // буфер для рисования, его размер не меняется
    private Graphics buffer; // graphics для рисования на image

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
                Graphic.this.paint(this, graphics);
            }
        };
        // переопределяем лиснер только для Swing приложения,
        // иначе не будут отлавливаться там клавиши так как panel перекрывает
        if (parent.getKeyListeners().length > 0) {
            panel.addKeyListener(parent.getKeyListeners()[0]);
        }
        panel.setVisible(true);
        fullWidth = width + 2 * border;
        fullHeight = height + 2 * border;
        panel.setSize(fullWidth, fullHeight);
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
        
        // обновляем текущий цвет рамки
        currentBorder = newBorder;
        buffer.setColor(currentBorder);

        // рисуем верхнюю и нижнюю рамки
        buffer.fillRect(0, 0, fullWidth, border);
        buffer.fillRect(0, height + border, fullWidth, border);

        // рисуем левую и правую рамки
        buffer.fillRect(0, 0, border, fullHeight);
        buffer.fillRect(width + border, 0, border, fullHeight);
    }

    public void paint(JPanel panel, Graphics graphics) {
        drawBorder();
        
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();

        // масштаб для отображения bufferImage на panel
        float scaleX = (float) panelWidth / fullWidth;
        float scaleY = (float) panelHeight / fullHeight;

        // рисуем масштабированное изображение из bufferImage на panel
        graphics.drawImage(image, 0, 0, Math.round(fullWidth * scaleX),
                Math.round(fullHeight * scaleY), null);
    }

    public void repaint() {
        panel.repaint();
    }
}