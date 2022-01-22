package spec.keyboard.keys;

public class ZB implements K {

    @Override
    public char ch() {
        return (char) 0x08;
    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 1;
    }

    @Override
    public int code(int layout, boolean shift) {
        return ch();
    }

    @Override
    public boolean shift() {
        return false;
    }
}
