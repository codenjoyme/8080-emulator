package spec.keys;

import java.awt.*;

public class Backspace implements K {

    @Override
    public char ch() {
        return 0x08;
    }

    @Override
    public Point pt() {
        return new Point(0, 1);
    }
}