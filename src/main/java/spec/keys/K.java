package spec.keys;

import java.awt.*;

import static spec.IOPorts.LAYOUT_AWT;

public interface K {

    default char ch() {
        return 0x00;
    }

    Point pt();

    default boolean itsMe(int keyCode, int layout, boolean shift) {
        return (layout == LAYOUT_AWT)
                ? keyCode == awtCode()
                : shift() == shift && keyCode == swingCode();
    }

    default int awtCode() {
        return ch();
    }

    default int swingCode() {
        return ch();
    }

    default boolean shift() {
        return false;
    }
}
