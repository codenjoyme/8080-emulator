package spec;

import javax.swing.*;
import java.awt.*;

public class Main2 extends JPanel {

    private static final double CLOCK = 1.6; // Specialist runs at 3.5Mhz;

    private JFrame frame;

    public static void main(String[] args) {
        new Main2();
    }

    public Main2() {
        frame = new JFrame("Demo");
        frame.add(this);
        frame.setSize(550, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(100, 50, 60, 80);
    }
}