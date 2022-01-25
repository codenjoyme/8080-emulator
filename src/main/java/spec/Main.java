package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static spec.Constants.BORDER_WIDTH;

public class Main extends JFrame implements KeyListener {

    private Application app;

    public static void main(String[] args) {
        String base = (args.length == 1) ? args[0] : null;
        new Main(base);
    }

    public Main(String base) {
        super("i8080 emulator");
        setMinimumSize(new Dimension(
                WIDTH + BORDER_WIDTH * 2 + 15,
                HEIGHT + BORDER_WIDTH * 2 + 40));
        setVisible(true);
        setFocusable(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowFocusListener(focusListener());
        setFocusTraversalKeysEnabled(true);
        addKeyListener(this);

        app = new Application(this, getBaseUrl(base));
        app.gotFocus();
        app.start();
    }

    private URL getBaseUrl(String base) {
        try {
            return base == null
                    ? contentRoot().toURI().toURL()
                    : new URL(base);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File contentRoot() {
        File result = new File("src/main/resources");
        if (!result.exists()) {
            result = new File(".");
        }
        return result;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (app != null) {
            app.handleKey(new Key(event, true));
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (app != null) {
            app.handleKey(new Key(event, false));
        }
    }

    private WindowFocusListener focusListener() {
        return new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent event) {
                if (app != null) {
                    app.gotFocus();
                }
            }

            @Override
            public void windowLostFocus(WindowEvent event) {
                if (app != null) {
                    app.lostFocus();
                }
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        if (app != null) {
            app.repaint();
        }
    }
}