package spec.keys;

public class End implements K {

    @Override
    public int code() {
        return 0x0023;
    }

    @Override
    public int pt() {
        return 0xA5; // like F1
    }
}