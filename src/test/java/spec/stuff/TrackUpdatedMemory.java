package spec.stuff;

import spec.Memory;
import spec.Range;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class TrackUpdatedMemory extends Memory {

    private List<UpdatedBite> updated;
    private boolean trackChanges;

    public TrackUpdatedMemory(int size) {
        super(size);
        resetChanges();
        trackChanges = true;
    }

    public void resetChanges() {
        updated = new LinkedList<>();
    }

    public void doTrackChanges() {
        trackChanges = true;
    }

    public void doNotTrackChanges() {
        trackChanges = false;
    }

    @Override
    public void write8(int addr, int bite) {
        int prev = super.read8(addr);
        if (trackChanges) {
            updated.add(new UpdatedBite(addr, prev, bite));
        }
        super.write8(addr, bite);
    }

    public String details() {
        return updated.stream()
                .map(UpdatedBite::toString)
                .collect(joining("\n"));
    }

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