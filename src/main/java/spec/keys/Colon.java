package spec.keys;

public class Colon implements K {

    @Override
    public char ch() {
        return ':';
    }

    @Override
    public int pt() {
        return 0x03;
    }

    @Override
    public int swingCode() {
        return ';';
    }

    @Override
    public boolean shiftIn() {
        return true;
    }

    @Override
    public boolean shiftOut() {
        return false;
    }
}