package spec;

import spec.math.Bites;

public interface StateProvider {

    int stateSize();

    void state(Bites bites);

    Bites state();

    default void validateState(String name, Bites actual) {
        if (actual.size() != stateSize()) {
            throw new IllegalArgumentException(String.format(
                    "Invalid " + name + " state size: %s, expected: %s",
                    actual.size(), stateSize()));
        }
    }
}
