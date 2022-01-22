package spec.keys;

import static spec.IOPorts.LAYOUT_AWT;

public interface K {

    default char ch() {
        return 0x00;
    }

    int pt();

    default boolean itsMe(int keyCode, int layout, boolean shift) {
        return (layout == LAYOUT_AWT)
                ? keyCode == awtCode()
                : shiftIn() == shift && keyCode == swingCode();
    }

    default int x() {
        return (pt() & 0xF0) >> 4;
    }

    default int y() {
        return pt() & 0x0F;
    }

    default int awtCode() {
        return ch();
    }

    default int swingCode() {
        return ch();
    }

    default boolean shiftIn() {
        return false;
    }

    default boolean shiftOut() {
        return shiftIn();
    }
}
