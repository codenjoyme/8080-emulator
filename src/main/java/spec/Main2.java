package spec;

import javax.swing.*;
import java.awt.*;
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

        app = new Application(this);
        app.readParameters(new File("src/main/resources").toURI().toURL());
        app.start();
    }

    @Override
    public void paint(Graphics g) {
        if (app != null) {
            app.repaint();
        }
    }
}