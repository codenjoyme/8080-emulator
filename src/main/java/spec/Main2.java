package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;

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

        app = new Application(this, new File("src/main/resources").toURI().toURL());
        app.gotFocus();
        app.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
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