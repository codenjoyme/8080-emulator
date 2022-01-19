package spec;

public class TestMemory extends Memory {

    private Range updated;

    public TestMemory(int size) {
        super(size);
        clear();
    }

    private void clear() {
        updated = new Range(0xFFFF, 0x0000);
    }

    @Override
    public void set(int addr, int bite) {
        if (updated.begin() > addr) {
            updated.begin(addr);
        }

        if (updated.end() < addr) {
            updated.end(addr);
        }
        super.set(addr, bite);
    }

    public String changes() {
        return asString(updated);
    }
}