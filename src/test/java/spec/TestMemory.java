package spec;

public class TestMemory extends Memory {

    private Range updated;

    public TestMemory(int size) {
        super(size);
        clear();
    }

    public void clear() {
        updated = new Range(0xFFFF, 0x0000);
    }

    @Override
    public void write8(int addr, int bite) {
        if (updated.begin() > addr) {
            updated.begin(addr);
        }

        if (updated.end() < addr) {
            updated.end(addr);
        }
        super.write8(addr, bite);
    }

    public String changes() {
        return read8srt(updated);
    }
}