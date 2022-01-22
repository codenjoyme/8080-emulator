package spec.keys;

public class Dot implements K {

    @Override
    public char ch() {
        return '.';
    }

    @Override
    public int pt() {
        return 0x02;
    }
}