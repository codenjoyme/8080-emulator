package spec;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static spec.KeyCode.*;

public class Key {

    // Swing key event mods
    public static final int MOD_NONE =   0b0000_0000_0000_0000;
    public static final int MOD_SHIFT =  0b0000_0000_0100_0000;
    public static final int MOD_ALT =    0b0000_0010_0000_0000;
    public static final int MOD_CTRL =   0b0000_0000_1000_0000;

    // наши кастомные флаги, для изменения key code
    public static final int SHIFT_MASK = 0b0000_0001_0000_0000;
    public static final int ALT_MASK =   0b0000_0010_0000_0000;
    public static final int CTRL_MASK =  0b0000_0100_0000_0000;

    public static final int CYRILLIC_MASK = 0x1000000;

    private int code;
    private boolean press;
    private int mods;

    public Key(int code, boolean press, int mods) {
        this.code = code;
        this.press = press;
        this.mods = mods;
    }

    public Key(KeyEvent event, boolean press) {
        this.press = press;
        code = event.getExtendedKeyCode();
        mods = event.getModifiersEx();
    }

    public boolean pause() {
        return code == PAUSE;
    }

    public boolean pressed() {
        return press;
    }

    public int joint() {
        return code
                | (shift() ? SHIFT_MASK : 0)
                | (alt() ? ALT_MASK : 0)
                | (ctrl() ? CTRL_MASK : 0);
    }

    public int code() {
        return code;
    }

    public boolean shift() {
        return (mods & MOD_SHIFT) == MOD_SHIFT;
    }

    public boolean alt() {
        return (mods & MOD_ALT) == MOD_ALT;
    }

    public boolean ctrl() {
        return (mods & MOD_CTRL) == MOD_CTRL;
    }

    public List<Key> allKeysWithMods() {
        boolean up = false;
        return new LinkedList<>(Arrays.asList(
                new Key(code, up, MOD_NONE),

                new Key(code, up, MOD_SHIFT),
                new Key(code, up, MOD_ALT),
                new Key(code, up, MOD_CTRL),
                
                new Key(code, up, MOD_SHIFT | MOD_ALT),
                new Key(code, up, MOD_SHIFT | MOD_CTRL),
                new Key(code, up, MOD_ALT | MOD_CTRL),

                new Key(code, up, MOD_SHIFT | MOD_ALT | MOD_CTRL)
        ));
    }

    public boolean system() {
        return code == CTRL
                || code == ALT
                || code == SHIFT
                || code == PAUSE;
    }
}