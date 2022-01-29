package spec.mods;

import java.util.ArrayList;
import java.util.List;

public abstract class Modifiable<T> {

    protected List<CpuMod<T>> mods = new ArrayList<>(10);

    public void on(String event) {
        for (int i = 0; i < mods.size(); i++) {
            mods.get(i).on(event, (T) this);
        }
    }

    public void modAdd(CpuMod<T> mod) {
        mods.add(mod);
    }

    public <M extends CpuMod<T>> M mod(Class<M> clazz) {
        for (CpuMod<T> mod : mods) {
            if (mod.itsMe(clazz)) {
                return (M) mod;
            }
        }
        throw new IllegalArgumentException("Mod not found: " + clazz);
    }

    public <M extends CpuMod<T>> void modRemove(Class<M> clazz) {
        mods.removeIf(mod ->
                mod.itsMe(clazz));
    }

    public void reset() {
        mods.forEach(CpuMod::reset);
    }
}