package spec;

import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import static java.awt.Event.*;
import static spec.Video.*;

public class Application {

    private static final int BORDER_PORT = 254;
    private static final int PAUSE = 0x0400;

    private Container container;
    private Graphics background;

    private Canvas canvas;
    private Graphics scene;

    private Image bufferImage;
    private Graphics buffer;

    private static int BORDER_WIDTH = 20;

    private int refreshRate = 1;  // refresh every 'n' interrupts

    private int interruptCounter = 0;
    private boolean resetAtNextInterrupt = false;
    private boolean pauseAtNextInterrupt = false;
    private boolean refreshNextInterrupt = true;

    private long timeOfLastInterrupt = 0;
    private long timeOfLastSample = 0;
    private boolean runAtFullSpeed = false;
    private Color currentBorder = null; // null mean update screen
    private Color newBorder = Color.YELLOW;

    private Hardware hard;

    /**
     * Container — это абстрактный подкласс класса Component, определяющий дополнительные методы,
     * которые дают возможность помещать в него другие компоненты, что дает возможность построения
     * иерархической системы визуальных объектов. Container отвечает за расположение содержащихся
     * в нем компонентов с помощью интерфейса LayoutManager.
     */
    public Application(Container parent, URL base) {
        container = parent;
        hard = new Hardware() {
            @Override
            protected void outb(int port, int bite) {
                Application.this.outb(port, bite);
            }

            @Override
            protected boolean interrupt() {
                return Application.this.interrupt();
            }

            @Override
            protected void drawPixel(Point point, Color color) {
                Application.this.drawPixel(point, color);
            }
        };

        // мы рисуем на канве, чтобы не мерцало изображение
        container.add(canvas = new Canvas());
        canvas.setSize(WIDTH, HEIGHT);
        // переопределяем лиснер только для Swing приложения,
        // иначе не будут отлавливаться там клавиши так как canvas перекрывает
        if (container.getKeyListeners().length > 0) {
            canvas.addKeyListener(container.getKeyListeners()[0]);
        }
        canvas.setVisible(true);

        // тут мы кешируем уже отрисованное из видеопамяти изображение
        bufferImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        // через него мы рисуем на cache-image пиксели
        buffer = bufferImage.getGraphics();
        // на нем мы рисуем рамку, это фон под canvas
        background = container.getGraphics();
        // а через это мы рисуем на канве
        scene = canvas.getGraphics();

        loadRoms(base);
    }

    public static Dimension getMinimumSize(int dx, int dy) {
        int border = BORDER_WIDTH;
        return new Dimension(
                Video.WIDTH + border * 2 + dx,
                Video.HEIGHT + border * 2 + dy);
    }

    private void loadRoms(URL base) {
        setBorderWidth(BORDER_WIDTH);

        boolean lik = true;
        if (lik) {
            Lik.loadRom(base, hard.roms());
            Lik.loadGame(base, hard.roms(), "klad");
            // Lik.loadTest(base, hard.roms(), "test");
        } else {
            Specialist.loadRom(base, hard.roms());
            Specialist.loadGame(base, hard.roms(), "blobcop");
        }

        String snapshot = null; // TODO научиться сохранять и загружать снепшоты
        if (snapshot != null) {
            loadSnapshot(base, snapshot);
        } else {
            reset();
        }
    }

    private void drawPixel(Point pt, Color color) {
        buffer.setColor(color);
        buffer.fillRect(pt.x, pt.y, 1, 1);
    }

    private void setBorderWidth(int width) {
        BORDER_WIDTH = width;
        canvas.setLocation(BORDER_WIDTH, BORDER_WIDTH);
    }

    private void outb(int port, int outByte) {
        // port xx.FEh
        if ((port & 0x0001) == 0) {
            // 0000.0111 бордюр & 0x07
            newBorder = COLORS[outByte & 0x000F];
        }
    }

    private boolean interrupt() {
        if (pauseAtNextInterrupt) {
            while (pauseAtNextInterrupt) {
                if (refreshNextInterrupt) {
                    refreshNextInterrupt = false;
                    currentBorder = null; // обновить Border
                    paintBuffer();
                }
            }
        }

        if (refreshNextInterrupt) {
            refreshNextInterrupt = false;
            currentBorder = null; // обновить Border
            paintBuffer();
        }

        if (resetAtNextInterrupt) {
            System.out.println("TOTAL RESET");
            resetAtNextInterrupt = false;
            hard.reset();
        }

        interruptCounter++;

        // Update speed indicator every 2 seconds of 'Spechard time'
        if ((interruptCounter % 100) == 0) {
            // обновить значение скорости эмуляции
        }

        // Обновлять экран каждое прерывание по умолчанию
        if ((interruptCounter % refreshRate) == 0) {
            hard.video.screenPaint();
            paintBuffer();
        }
        // возвращает текущее системное время в виде миллисекунд,
        // прошедших с 1 января 1970 года
        timeOfLastInterrupt = System.currentTimeMillis();

        // Trying to slow to 100%, browsers resolution on the system
        // time is not accurate enough to check every interrurpt. So
        // we check every 4 interrupts.
        if ((interruptCounter % 4) == 0) {
            long durOfLastInterrupt = timeOfLastInterrupt - timeOfLastSample;
            timeOfLastSample = timeOfLastInterrupt;
            // запомним текущее время, как предыдущее.
            if (!runAtFullSpeed && (durOfLastInterrupt < 40)) {
                try {
                    Thread.sleep(50 - durOfLastInterrupt); // 50
                } catch (Exception ignored) {
                    // do nothing
                }
            }
        }
        return true;
    }

    public void repaint() {
        refreshNextInterrupt = true;
    }

    private void borderPaint() {
        if (BORDER_WIDTH == 0) { // если бордюра нет - ничего не делать!
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

    private void paintBuffer() {
        scene.drawImage(bufferImage, 0, 0, null);
        borderPaint();
    }

    public boolean handleEvent(Event e) {
        switch (e.id) {
            case MOUSE_DOWN: {
                canvas.requestFocus();
                return true;
            }
            case KEY_ACTION:
            case KEY_PRESS: {
                // событие клавиатуры - КЛАВИША_НАЖАТА_: e.key - код нажатой клавиши.
                // Обычно является Unicode значением символа, который представлен этой клавишей.
                return doKey(true, e.key, e.modifiers);
            }
            case KEY_ACTION_RELEASE:
            case KEY_RELEASE: {
                // событие клавиатуры - КЛАВИША_ОТПУЩЕНА_
                return doKey(false, e.key, e.modifiers);
            }
            case GOT_FOCUS: {
                System.out.println("GOT FOCUS");
                outb(BORDER_PORT, 0x02);
                hard.ports.resetKeyboard();
                return true;
            }
            case LOST_FOCUS: {
                System.out.println("LOST FOCUS");
                outb(BORDER_PORT, 0x06);
                hard.ports.resetKeyboard();
                return true;
            }
        }
        return false;
    }

    private boolean doKey(boolean down, int ascii, int mods) {
        hard.ports.processKey(down, ascii);

        switch (ascii) {
            case PAUSE: {
                if (down) resetAtNextInterrupt = true;
                break;
            }
            default:
                return false;
        }
        return true;
    }

    private void refreshWholeScreen() {
        currentBorder = null;
    }

    private void loadSnapshot(URL base, String snapshot) {
        hard.loadSnapshot(base, snapshot);
        refreshWholeScreen();
        canvas.requestFocus();
    }

    public void start() {
        hard.start();
    }

    private void reset() {
        hard.reset();
        refreshWholeScreen();
    }
}