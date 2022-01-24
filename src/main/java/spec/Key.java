package spec;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static spec.KeyCode.PAUSE;

public class Key {

    // Swing key event mods
    public static final int MOD_NONE =       0b0000_0000_0000_0000;
    public static final int MOD_SHIFT =      0b0000_0000_0100_0000;
    public static final int MOD_LEFT_ALT =   0b0000_0010_0000_0000;
    public static final int MOD_RIGHT_ALT =  0b0010_0010_0000_0000;
    public static final int MOD_CTRL =       0b0000_0000_1000_0000;

    // наши кастомные флаги, для изменения key code
    public static final int SHIFT_MASK = 0b0000_0001_0000_0000;
    public static final int LEFT_ALT_MASK = 0b0000_0010_0000_0000;
    public static final int RIGHT_ALT_MASK = 0b0000_0100_0000_0000;
    public static final int CTRL_MASK = 0b0000_1000_0000_0000;

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
                | (leftAlt() ? LEFT_ALT_MASK : 0)
                | (rightAlt() ? RIGHT_ALT_MASK : 0)
                | (ctrl() ? CTRL_MASK : 0);
    }

    public int code() {
        return code;
    }

    public boolean shift() {
        return (mods & MOD_SHIFT) == MOD_SHIFT;
    }

    public boolean leftAlt() {
        // TODO Придумать другой сопособ соответствия, так как флаги
        // MOD_LEFT_ALT и MOD_RIGHT_ALT пересекаются
        return !rightAlt()
                && (mods & MOD_LEFT_ALT) == MOD_LEFT_ALT;
    }

    public boolean rightAlt() {
        return (mods & MOD_RIGHT_ALT) == MOD_RIGHT_ALT;
    }

    public boolean ctrl() {
        return (mods & MOD_CTRL) == MOD_CTRL;
    }

    public Set<Integer> allKeysWithMods() {
        return new LinkedHashSet<>(Arrays.asList(
                code,

                code | SHIFT_MASK,
                code | LEFT_ALT_MASK,
                code | RIGHT_ALT_MASK,
                code | CTRL_MASK,

                code | SHIFT_MASK | LEFT_ALT_MASK,
                code | SHIFT_MASK | RIGHT_ALT_MASK,
                code | SHIFT_MASK | CTRL_MASK,
                code | LEFT_ALT_MASK | RIGHT_ALT_MASK,
                code | LEFT_ALT_MASK | CTRL_MASK,
                code | RIGHT_ALT_MASK | CTRL_MASK,

                code | SHIFT_MASK | LEFT_ALT_MASK | RIGHT_ALT_MASK,
                code | SHIFT_MASK | LEFT_ALT_MASK | CTRL_MASK,
                code | SHIFT_MASK | RIGHT_ALT_MASK | CTRL_MASK,
                code | LEFT_ALT_MASK | RIGHT_ALT_MASK | CTRL_MASK,

                code | SHIFT_MASK | LEFT_ALT_MASK | RIGHT_ALT_MASK | CTRL_MASK
        ));
    }
}
