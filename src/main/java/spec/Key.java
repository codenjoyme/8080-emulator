package spec;

import java.awt.event.KeyEvent;

import static spec.IOPorts.PAUSE_KEY;

public class Key {

    public static final int MOD_SHIFT =      0b0000_0000_0100_0000;
    public static final int MOD_LEFT_ALT =   0b0000_0010_0000_0000;
    public static final int MOD_RIGHT_ALT =  0b0010_0010_0000_0000;
    public static final int MOD_CTRL =       0b0000_0000_1000_0000;

    public static final int SHIFT = 0b0000_0001_0000_0000;
    public static final int CYRYLIC = 0x1000000;

    private int code;
    private boolean press;
    private int mods;

    public Key(KeyEvent event, boolean press) {
        this.press = press;
        code = event.getExtendedKeyCode();
        mods = event.getModifiersEx();
    }

    public boolean pause() {
        return code == PAUSE_KEY;
    }

    public boolean pressed() {
        return press;
    }

    public int joint() {
        return code | (shift() ? SHIFT : 0);
    }

    public int code() {
        return code;
    }

    public boolean shift() {
        return (mods & MOD_SHIFT) == MOD_SHIFT;
    }
}
