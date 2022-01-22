package spec.keys;

public class Star extends Colon {

    @Override
    public char ch() {
        return '*';
    }

    @Override
    public int swingCode() {
        return '8';
    }

    @Override
    public boolean shiftIn() {
        return true;
    }

    @Override
    public boolean shiftOut() {
        return true;
    }
}