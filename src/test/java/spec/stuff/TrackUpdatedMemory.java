package spec.stuff;

import spec.Memory;
import spec.Range;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class TrackUpdatedMemory extends Memory {

    private List<UpdatedBite> updated;
    private boolean trackChanges;
    private int start;
    private int end;

    public TrackUpdatedMemory(int size) {
        super(size);
        resetChanges();
        trackChanges = true;
    }

    public void resetChanges() {
        updated = new ArrayList<>(100_000);
        start = Integer.MAX_VALUE;
        end = Integer.MIN_VALUE;
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
            start = Math.min(start, addr);
            end = Math.max(end, addr);
        }
        super.write8(addr, bite);
    }

    public String details() {
        return updated.stream()
                .map(UpdatedBite::toString)
                .collect(joining("\n"));
    }

    public String changedBites() {
        if (start == Integer.MAX_VALUE || end == Integer.MIN_VALUE) {
            return "";
        }
        return read8srt(new Range(start, end));
    }
}