package spec;

import static spec.Constants.x10000;

public class TestData implements Data {

    private TestMemory memory;
    private boolean working;
    private Runnable onInterrupt;

    public TestData(Runnable onInterrupt) {
        this.onInterrupt = onInterrupt;
        clear();
        memory = new TestMemory(x10000);
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
    public void out8(int port, int bite) {
        // do nothing
    }

    @Override
    public int read8(int addr) {
        return memory.read8(addr);
    }

    @Override
    public void write8(int addr, int bite) {
        memory.write8(addr, bite);
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
