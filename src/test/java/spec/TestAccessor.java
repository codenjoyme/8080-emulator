package spec;

public class TestAccessor implements Accessor {

    private Memory memory = new Memory(65536);
    private boolean working = true;
    private Runnable onInterrupt;

    public TestAccessor(Runnable onInterrupt) {
        this.onInterrupt = onInterrupt;
    }

    @Override
    public boolean interrupt() {
        onInterrupt.run();
        return working;
    }

    @Override
    public void outb(int port, int bite) {
        // do nothing
    }

    @Override
    public int peekb(int addr) {
        return memory.get(addr);
    }

    @Override
    public void pokeb(int addr, int bite) {
        memory.set(addr, bite);
    }

    public Memory memory() {
        return memory;
    }

    public void stopCpu() {
        working = false;
    }
}
