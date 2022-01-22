package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;

import static java.awt.Event.GOT_FOCUS;
import static java.awt.Event.LOST_FOCUS;

public class Main2 extends JPanel {

    private Application app;
    private JFrame frame;

    public static void main(String[] args) throws Exception {
        new Main2();
    }

    public Main2() throws Exception {
        frame = new JFrame("i8080 emulator");
        frame.add(this);
        frame.setMinimumSize(Application.getMinimumSize(15, 40));
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowFocusListener(focusListener());

        app = new Application(this, new File("src/main/resources").toURI().toURL());
        onFocus(GOT_FOCUS);
        app.start();
    }

    private WindowFocusListener focusListener() {
        return new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent event) {
                if (app != null) {
                    onFocus(GOT_FOCUS);
                }
            }

            @Override
            public void windowLostFocus(WindowEvent event) {
                if (app != null) {
                    onFocus(LOST_FOCUS);
                }
            }
        };
    }

    private void onFocus(int status) {
        app.handleEvent(new Event(null, status, null));
    }

    @Override
    public void paint(Graphics g) {
        if (app != null) {
            app.repaint();
        }
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        System.out.println(e);
    }
}