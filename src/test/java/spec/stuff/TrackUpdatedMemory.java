package spec.stuff;

import spec.Memory;
import spec.Range;
import spec.math.Bites;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class TrackUpdatedMemory extends Memory {

    public static boolean TRACK_ALL_CHANGES = false;
    public static boolean TRACK_ONLY_UPDATED_VALUES = !TRACK_ALL_CHANGES;

    private List<UpdatedBite> updated;
    private boolean trackChanges;
    private boolean trackOnlyUpdates;
    private int start;
    private int end;

    /**
     * @param size Memory size
     * @param trackOnlyUpdates
     *      true - if save as UpdatedBite only changed bites;
     *      false - track all saved bites.
     */
    public TrackUpdatedMemory(int size, boolean trackOnlyUpdates) {
        super();
        mem = new TrackUpdatedBites(size);
        resetChanges();
        trackChanges = true;
        this.trackOnlyUpdates = trackOnlyUpdates;
    }

    public void resetChanges() {
        updated = new ArrayList<>(mem.size());
        for (int i = 0; i < mem.size(); i++) {
            updated.add(new UpdatedBite(i, mem.get(i)));
        }
        start = Integer.MAX_VALUE;
        end = Integer.MIN_VALUE;
    }

    public class TrackUpdatedBites extends Bites {

        public TrackUpdatedBites(int size) {
            super(size);
        }

        @Override
        public void set(int addr, int bite) {
            int prev = super.get(addr);
            if (trackChanges) {
                if (trackOnlyUpdates && prev == bite) {
                    return;
                }
                updated.get(addr).add(bite);
                start = Math.min(start, addr);
                end = Math.max(end, addr);
            }
            super.set(addr, bite);
        }
    }

    public void doTrackChanges() {
        trackChanges = true;
    }

    public void doNotTrackChanges() {
        trackChanges = false;
    }

    public String details() {
        return updated.stream()
                .filter(UpdatedBite::changed)
                .map(UpdatedBite::toString)
                .collect(joining("\n"));
    }

    public String detailsTable() {
        if (updated.isEmpty()) {
            return "";
        }

        // Начинаем с минимального адреса, округлённого до начала строки (16 байт на строку)
        int startLine = start - start % 16;
        // Окончание вывода - с конца изменений, округлённо вверх до конца строки
        int endLine = (end + 16) - (end % 16);

        StringBuilder builder = new StringBuilder();
        for (int line = startLine; line < endLine; line += 16) {

            StringBuilder lineString = new StringBuilder();
            boolean empty = true;
            for (int i = line; i < line + 16; i++) {
                String display = "-";
                UpdatedBite ub = updated.get(i);
                if (ub.changed()) {
                    display = hex8(ub.original()) + ">" + hex8(ub.last());
                    empty = false;
                }
                lineString.append(String.format(" %-5s ", display));
            }
            if (!empty) {
                builder.append(hex16(line))
                        .append(": ")
                        .append(lineString)
                        .append("\n");
            }
        }

        if (builder.length() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append("       ");
        for (int i = 0; i < 16; i++) {
            result.append(hex8(i))
                    .append("     ");
        }
        result.append("\n")
                .append(builder);

        return result.toString();
    }

    public String changedBites() {
        if (start == Integer.MAX_VALUE || end == Integer.MIN_VALUE) {
            return "";
        }
        return read8str(new Range(start, end));
    }
}