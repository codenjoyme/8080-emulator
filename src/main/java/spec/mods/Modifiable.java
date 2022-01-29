package spec.mods;

import java.util.LinkedList;
import java.util.List;

public abstract class Modifiable<T> {

    protected List<CpuMod<T>> mods = new LinkedList<>();

    public void on(String event) {
        mods.forEach(mod ->
                mod.on(event, (T) this));
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