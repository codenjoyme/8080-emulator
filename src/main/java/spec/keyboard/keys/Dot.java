package spec.keyboard.keys;

public class Dot implements K {

    @Override
    public char ch() {
        return '.';
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
        return !shift && keyCode == 0x002E;
    }

    @Override
    public boolean shift() {
        return false;
    }
}
