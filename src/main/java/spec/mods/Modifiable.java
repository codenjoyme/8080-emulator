package spec.mods;

import java.util.HashMap;
import java.util.Map;

public abstract class Modifiable<T> {

    protected Map<Class<CpuMod<T>>, CpuMod<T>> mods = new HashMap<>();

    public void on(String event) {
        mods.forEach((key, value) ->
                value.on(event, (T) this));
    }

    public void modAdd(CpuMod<T> mod) {
        mods.put((Class) mod.getClass(), mod);
    }

    public <M extends CpuMod<T>> M mod(Class<M> clazz) {
        return (M) mods.get(clazz);
    }

    public <M extends CpuMod<T>> void modRemove(Class<M> clazz) {
        mods.remove(clazz);
    }

    public void reset() {
        mods.entrySet().forEach(entry ->
                entry.getValue().reset());
    }
}