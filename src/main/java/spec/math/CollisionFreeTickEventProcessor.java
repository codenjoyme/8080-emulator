package spec.math;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class CollisionFreeTickEventProcessor<T> {

    private List<T> events = new ArrayList<>();
    private List<T> mediator;
    private List<Integer> ticks = new ArrayList<>();
    private int xorShift;
    private static final int MULTIPLIER = 4; // Увеличение размера массива в N раз для уменьшения коллизий

    public void addEvent(int tick, T event) {
        ticks.add(tick);
        events.add(event);
        tryToFitEvents();
    }

    private int hash(int tick, int xorShift, int size) {
        int hash = tick;
        hash ^= (hash >>> xorShift) ^ (hash << xorShift);
        hash ^= (hash >>> (xorShift / 2));
        return Math.abs(hash % size);
    }

    private void tryToFitEvents() {
        int attempts = Integer.MAX_VALUE; // Ограничение по количеству попыток
        for (xorShift = 0; xorShift < attempts; xorShift++) {
            mediator = new ArrayList<>(Collections.nCopies(events.size() * MULTIPLIER, null));
            boolean collisionFound = false;
            Set<Integer> usedIndices = new HashSet<>();

            for (int i = 0; i < ticks.size(); i++) {
                int index = hash(ticks.get(i), xorShift, mediator.size());
                if (usedIndices.contains(index)) {
                    collisionFound = true;
                    break;
                }
                usedIndices.add(index);
                mediator.set(index, events.get(i));
            }

            if (!collisionFound) {
                return;
            }
        }
        throw new RuntimeException("Cannot find collision-free hash function");
    }

    public T getEvent(int tick) {
        int index = hash(tick, xorShift, mediator.size());
        return mediator.get(index);
    }

    public Set<Integer> getTicks() {
        return new HashSet<>(ticks);
    }
}
