package spec.keyboard.keys;

public interface K {

    char ch();

    int x();

    int y();

    boolean itsMe(int keyCode, int layout, boolean shift);

    boolean shift();
}
