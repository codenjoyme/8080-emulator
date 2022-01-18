package spec;

public interface Accessor {

    void interrupt();

    void outb(int port, int bite);

    int peekb(int addr);

    void pokeb(int addr, int bite);

    void pokew(int addr, int word);
}