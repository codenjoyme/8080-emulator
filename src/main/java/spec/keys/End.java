package spec.keys;

import java.awt.*;

public class End implements K {

    @Override
    public Point pt() {
        return new Point(0xA, 0x5); // like F1
    }

    @Override
    public int awtCode() {
        return 0x03E9;
    }

    @Override
    public int swingCode() {
        return 0x0023;
    }
}