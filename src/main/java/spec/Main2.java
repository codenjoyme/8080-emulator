package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;

import static spec.keyboard.KeyParser.LAYOUT_SWING;

public class Main2 extends JFrame implements KeyListener {

    private Application app;

    public static void main(String[] args) throws Exception {
        new Main2();
    }

    public Main2() throws Exception {
        super("i8080 emulator");
        setMinimumSize(Graphic.getMinimumSize(15, 40));
        setVisible(true);
        setFocusable(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowFocusListener(focusListener());
        setFocusTraversalKeysEnabled(true);
        addKeyListener(this);

        app = new Application(this, new File("src/main/resources").toURI().toURL(), LAYOUT_SWING);
        app.gotFocus();
        app.start();
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (app != null) {
            app.handleKey(true, event.getExtendedKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (app != null) {
            app.handleKey(false, event.getExtendedKeyCode());
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