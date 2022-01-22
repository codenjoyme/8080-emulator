package spec.keys;

import java.awt.*;

public class Enter implements K {

    @Override
    public char ch() {
        return 0x0A;
    }

    @Override
    public Point pt() {
        return new Point(0, 0);
    }
}