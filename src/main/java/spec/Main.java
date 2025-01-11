package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static spec.Constants.*;

public class Main extends JFrame implements KeyListener {

    private Application app;

    public static void main(String[] args) {
        // передается или base в серверной версии,
        // либо файл с приложением, либо вообще ничего
        String base = null;
        String rom = null;
        if (args.length == 1) {
            String param = args[0];
            if (param.endsWith(".rks")) {
                rom = param;
            } else {
                base = param;
            }
        }

        new Main(base, rom);
    }

    public Main(String base, String rom) {
        super("i8080 emulator");
        setMinimumSize(new Dimension(
                SCREEN_WIDTH + BORDER_WIDTH * 2 + 15,
                SCREEN_HEIGHT + BORDER_WIDTH * 2 + 40));
        setVisible(true);
        setFocusable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowFocusListener(focusListener());
        setFocusTraversalKeysEnabled(true);
        addKeyListener(this);

        URL baseUrl = getBaseUrl(base);
        Logger.debug("Base url: " + baseUrl);
        app = new Application(this, baseUrl);
        if (rom != null) {
            app.load(rom);
        }
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
        return new File(".");
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
}