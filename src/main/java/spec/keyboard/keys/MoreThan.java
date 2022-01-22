package spec.keyboard.keys;

import static spec.keyboard.KeyParser.LAYOUT_AWT;

public class MoreThan implements K {

    @Override
    public char ch() {
        return '>';
    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 2;
    }

    @Override
    public boolean itsMe(int keyCode, int layout, boolean shift) {
        return (layout == LAYOUT_AWT)
                ? keyCode == 0x003E
                : shift && keyCode == 0x002E;
    }

    @Override
    public boolean shift() {
        return true;
    }
}
