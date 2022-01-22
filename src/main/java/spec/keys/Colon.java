package spec.keys;

public class Colon implements K {

    @Override
    public char ch() {
        return ':';
    }

    @Override
    public int code() {
        return ';';
    }

    @Override
    public boolean shiftIn() {
        return true;
    }

    @Override
    public int pt() {
        return 0x03;
    }

    @Override
    public boolean shiftOut() {
        return false;
    }
}