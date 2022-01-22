package spec.keys;

public class Backspace implements K {

    @Override
    public char ch() {
        return 0x08;
    }

    @Override
    public int pt() {
        return 0x01;
    }
}