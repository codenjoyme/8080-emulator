package spec.keys;

import java.awt.*;

public class Dot implements K {

    @Override
    public char ch() {
        return '.';
    }

    @Override
    public Point pt() {
        return new Point(0, 2);
    }
}