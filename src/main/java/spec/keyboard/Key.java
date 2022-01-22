package spec.keyboard;


public class Key {

    private short point;

    public Key(short point) {
        this.point = point;
    }

    public int x() {
        return (point & 0x0F00) >> 8;
    }

    public int y() {
        return point & 0x00FF;
    }

    public boolean shift() {
        return (point & 0x7000) != 0;
    }
}
