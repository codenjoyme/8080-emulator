package spec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class Main2 extends JPanel {

    private Application app;
    private JFrame frame;

    public static void main(String[] args) throws Exception {
        new Main2();
    }

    public Main2() throws Exception {
        frame = new JFrame("Demo");
        frame.add(this);
        frame.setSize(550, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        app = new Application(this, new File("src/main/resources").toURI().toURL());
        app.start();
    }

    @Override
    public void paint(Graphics g) {
        app.repaint();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
    }
}