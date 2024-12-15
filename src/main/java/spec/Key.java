package spec;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static spec.KeyCode.*;
import static spec.math.WordMath.*;

public class Key {

    // Swing key event mods
    public static final int MOD_NONE =   0b0000_0000_0000_0000;
    public static final int MOD_SHIFT =  0b0000_0000_0100_0000;
    public static final int MOD_ALT =    0b0000_0010_0000_0000;
    public static final int MOD_CTRL =   0b0000_0000_1000_0000;

    // наши кастомные флаги, для изменения key code
    public static final int SHIFT_MASK =    0x8000000;
    public static final int ALT_MASK =      0x4000000;
    public static final int CTRL_MASK =     0x2000000;
    // а это реально приходит из фреймворка
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

    public boolean capsLock() {
        return code == CAPS_LOCK;
    }

    public boolean numSlash() {
        return code == NUM_SLASH;
    }

    public boolean numZero() {
        return code == NUM_0;
    }

    public boolean numOne() {
        return code == NUM_1;
    }

    public boolean numTwo() {
        return code == NUM_2;
    }

    public boolean numThree() {
        return code == NUM_3;
    }

    public boolean numFour() {
        return code == NUM_4;
    }

    public boolean numFive() {
        return code == NUM_5;
    }

    public boolean numSix() {
        return code == NUM_6;
    }

    public boolean numStar() {
        return code == NUM_STAR;
    }

    public boolean numMinus() {
        return code == NUM_MINUS;
    }

    public boolean numPlus() {
        return code == NUM_PLUS;
    }

    public boolean numComma() {
        return code == NUM_COMMA;
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

    public boolean mods() {
        return code == CTRL
                || code == ALT
                || code == SHIFT;
    }

    public boolean system() {
        return pause()
                || numSlash()
                || numStar()
                || numMinus()
                || numPlus()
                || numComma()
                || numZero()
                || numOne()
                || numTwo()
                || numThree()
                || numFour()
                || numFive()
                || numSix();
    }

    @Override
    public String toString() {
        return String.format("Key{code=0x%s / '%s', press=%s, mods=%s}",
                hex8(code),
                (char) code,
                bitToString(press),
                bits(mods));
    }

}