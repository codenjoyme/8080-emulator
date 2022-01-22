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
    public int code(int layout, boolean shift) {
        return 0x002E;
    }

    @Override
    public boolean shift() {
        return false;
    }
}
