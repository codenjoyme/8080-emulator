package spec;

public class TestAccessor implements Accessor {

    private TestMemory memory;
    private boolean working;
    private Runnable onInterrupt;

    public TestAccessor(Runnable onInterrupt) {
        this.onInterrupt = onInterrupt;
        clear();
        memory = new TestMemory(65536);
    }

    public void clear() {
        working = true;
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

    public String updatedMemory() {
        return memory.changes();
    }
}
