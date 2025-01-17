package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static spec.Constants.*;
import static spec.RomLoader.getFileExt;

public class Main extends JFrame implements KeyListener {

    private Application app;

    public static void main(String[] args) {
        // первый параметр - base папка с ресурсами где расположены roms
        // второй параметр - всегда платформа lik или specialist
        // третий параметр - файл с приложением/rom
        // все параметры опциональны
        String base = null;
        String platform = null;
        String rom = null;
        if (args.length >= 1) {
            base = args[0];
        }
        if (args.length >= 2) {
            platform = args[1];
        }
        if (args.length >= 3) {
            String param = args[2];
            if (!isEmpty(param) && !isEmpty(getFileExt(param))) {
                rom = param;
            } else {
                throw new IllegalArgumentException("Invalid file: " + param);
            }
        }

        new Main(base, platform, rom);
    }

    public Main(String base, String platform, String rom) {
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

        base = base == null ? "." : base;
        Logger.debug("Base url: " + base);
        app = new Application(this, base, platform, rom);
        app.start();
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