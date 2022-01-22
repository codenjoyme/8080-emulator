package spec.keys;

import spec.Key;

public interface K {

    default char ch() {
        return 0x00;
    }

    int pt();

    default boolean itsMe(Key key) {
        int keyCode = key.code();
        boolean shift = key.shift();

        return shiftIn() == shift && keyCode == code();
    }

    default int x() {
        return (pt() & 0xF0) >> 4;
    }

    default int y() {
        return pt() & 0x0F;
    }

    default int code() {
        return ch();
    }

    default boolean shiftIn() {
        return false;
    }

    default boolean shiftOut() {
        return shiftIn();
    }
}
