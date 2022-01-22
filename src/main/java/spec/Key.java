package spec;

import java.awt.*;
import java.awt.event.KeyEvent;

import static spec.IOPorts.*;

public class Key {

    public static final int SHIFT_SWING =      0b0000_0000_0100_0000;
    public static final int LEFT_ALT_SWING =   0b0000_0010_0000_0000;
    public static final int RIGHT_ALT_SWING =  0b0010_0010_0000_0000;
    public static final int CTRL_SWING =       0b0000_0000_1000_0000;

    public static final int SHIFT_AWT = 0b0000_0001;
    public static final int ALT_AWT =   0b0000_1000;
    public static final int CTRL_AWT =  0b0000_0010;

    private int layout;
    private int code;
    private boolean press;
    private int mods;

    public Key(KeyEvent event, boolean press) {
        this.press = press;
        layout = LAYOUT_SWING;
        code = event.getExtendedKeyCode();
        mods = event.getModifiersEx();
    }

    public Key(Event event, boolean press) {
        this.press = press;
        layout = LAYOUT_AWT;
        code = event.key;
        mods = event.modifiers;
    }

    public boolean pause() {
        if (layout == LAYOUT_SWING) {
            return code == PAUSE_SWING;
        } else {
            return code == PAUSE_AWT;
        }
    }

    public boolean pressed() {
        return press;
    }

    public int code() {
        return code;
    }

    public int layout() {
        return layout;
    }

    public boolean shift() {
        if (layout == LAYOUT_SWING) {
            return (mods & SHIFT_SWING) == SHIFT_SWING;
        } else {
            return (mods & SHIFT_AWT) == SHIFT_AWT;
        }
    }
}
