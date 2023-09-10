package spec.stuff;

import spec.Range;

/**
 * Показывает область памяти, которая была изменена.
 * Диапазон берется от самого меньшего адреса, до самого большего.
 */
public class TrackUpdatedRangeMemory extends UpdatedMemory {

    private int size;
    private Range updated;

    public TrackUpdatedRangeMemory(int size) {
        super(size);
        this.size = size;
        resetChanges();
    }

    @Override
    public void resetChanges() {
        updated = new Range(size - 1, 0x0000);
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

    @Override
    public String changedBites() {
        return read8srt(updated);
    }

    @Override
    public String details() {
        return String.format("Updated in range: %s",
                updated.toString());
    }
}