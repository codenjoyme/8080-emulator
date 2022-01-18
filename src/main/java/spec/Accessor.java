package spec;

public interface Accessor {

    void interrupt();

    void outb(int port, int outByte, int tstates);

    int peekb(int addr);

    void pokeb(int addr, int newByte);

    void pokew(int addr, int word);
}