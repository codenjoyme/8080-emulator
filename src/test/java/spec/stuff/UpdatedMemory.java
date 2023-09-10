package spec.stuff;

import spec.Memory;

public abstract class UpdatedMemory extends Memory {

    public UpdatedMemory(int size) {
        super(size);
    }

    public abstract void resetChanges();

    public abstract String changedBites();

    public abstract String details();
}
