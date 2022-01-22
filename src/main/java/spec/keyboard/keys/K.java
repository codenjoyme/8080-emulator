package spec.keyboard.keys;

public interface K {

    int x();

    int y();

    int code(int layout, boolean shift);

    boolean shift();

    char ch();
}
