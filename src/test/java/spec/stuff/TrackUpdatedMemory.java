package spec.stuff;

import spec.Memory;
import spec.Range;
import spec.math.Bites;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class TrackUpdatedMemory extends Memory {

    private List<UpdatedBite> updated;
    private boolean trackChanges;
    private int start;
    private int end;

    public TrackUpdatedMemory(int size) {
        super();
        mem = new TrackUpdatedBites(size);
        resetChanges();
        trackChanges = true;
    }

    public void resetChanges() {
        updated = new ArrayList<>(100_000);
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
                updated.add(new UpdatedBite(addr, prev, bite));
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
                .map(UpdatedBite::toString)
                .collect(joining("\n"));
    }

    public String detailsTable() {
        // по каждому изменению адреса оставляем только последние изменения
        LinkedList<UpdatedBite> changes = new LinkedList<>(updated);
        changes.sort(Comparator.comparingInt(UpdatedBite::addr));
        for (int i = 0; i < changes.size(); i++) {
            UpdatedBite ub = changes.get(i);
            for (int j = i + 1; j < changes.size(); j++) {
                UpdatedBite ub2 = changes.get(j);
                if (ub.addr() == ub2.addr()) {
                    changes.remove(i);
                    i--;
                    break;
                } else if (ub.addr() < ub2.addr()) {
                    break;
                }
            }
        }

        // Начинаем с минимального адреса, округлённого до начала строки (16 байт на строку)
        int startLine = start - start % 16;
        // Окончание вывода - с конца изменений, округлённо вверх до конца строки
        int endLine = (end + 16) - (end % 16);

        StringBuilder builder = new StringBuilder();

        builder.append("       ");
        for (int i = 0; i < 16; i++) {
            builder.append(hex8(i))
                    .append("     ");
        }
        builder.append("\n");

        for (int line = startLine; line < endLine; line += 16) {
            builder.append(hex16(line))
                    .append(": ");

            for (int i = line; i < line + 16; i++) {
                String display = "-";
                for (UpdatedBite ub : changes) {
                    if (ub.addr() == i) {
                        display = hex8(ub.prev()) + ">" + hex8(ub.next());
                        break;
                    }
                }
                builder.append(String.format(" %-5s ", display));
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    public String changedBites() {
        if (start == Integer.MAX_VALUE || end == Integer.MIN_VALUE) {
            return "";
        }
        return read8srt(new Range(start, end));
    }
}