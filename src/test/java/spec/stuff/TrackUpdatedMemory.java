package spec.stuff;

import spec.Range;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class TrackUpdatedMemory extends UpdatedMemory {

    private List<UpdatedBite> updated;

    public TrackUpdatedMemory(int size) {
        super(size);
        resetChanges();
    }

    @Override
    public void resetChanges() {
        updated = new LinkedList<>();
    }

    @Override
    public void write8(int addr, int bite) {
        int prev = super.read8(addr);
        updated.add(new UpdatedBite(addr, prev, bite));
        super.write8(addr, bite);
    }

    @Override
    public String details() {
        return updated.stream()
                .map(UpdatedBite::toString)
                .collect(joining("\n"));
    }

    @Override
    public String changedBites() {
        int start = updated.stream()
                .mapToInt(UpdatedBite::addr)
                .min()
                .orElse(-1);
        int end = updated.stream()
                .mapToInt(UpdatedBite::addr)
                .max()
                .orElse(-1);
        if (start == -1 || end == -1) {
            return "";
        }
        return read8srt(new Range(start, end));
    }
}