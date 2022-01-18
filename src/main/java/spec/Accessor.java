package spec;

public interface Accessor {

    void plot(int addr, int newByte);

    void outPort(int addr, int newByte);

    int inport(int addr);

    void interrupt();

    void mem(int addr, int data);

    int mem(int addr);

    void outb(int port, int outByte, int tstates);
}