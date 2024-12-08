package spec;

import spec.math.Bites;

public interface StateProvider {

    int stateSize();

    void state(Bites bites);

    Bites state();
}
