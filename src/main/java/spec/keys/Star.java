package spec.keys;

public class Star implements K {

    @Override
    public char ch() {
        return '*';
    }

    @Override
    public int code() {
        return '8';
    }

    @Override
    public boolean shiftIn() {
        return true;
    }

    @Override
    public int pt() {
        return 0x03;
    }

}