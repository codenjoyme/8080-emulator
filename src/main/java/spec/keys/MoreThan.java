package spec.keys;

public class MoreThan extends Dot {

    @Override
    public char ch() {
        return '>';
    }

    @Override
    public int swingCode() {
        return 0x002E;
    }

    @Override
    public boolean shiftIn() {
        return true;
    }
}