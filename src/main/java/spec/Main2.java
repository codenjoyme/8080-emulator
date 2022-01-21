package spec;

import javax.swing.*;
import java.awt.*;

public class Main2 extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(100, 50, 60, 80);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Demo");
        frame.add(new Main2());
        frame.setSize(550, 250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}