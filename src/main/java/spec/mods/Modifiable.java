package spec.mods;

import java.util.ArrayList;
import java.util.List;

public abstract class Modifiable {

    protected List<CpuMod> mods = new ArrayList<>(10);

    public void on(Event event, Object... params) {
        for (int i = 0; i < mods.size(); i++) {
            mods.get(i).on(event, params);
        }
    }

    public void modAdd(CpuMod mod) {
        mods.add(mod);
    }

    public <M extends CpuMod> M mod(Class<M> clazz) {
        for (CpuMod mod : mods) {
            if (mod.itsMe(clazz)) {
                return (M) mod;
            }
        }
        throw new IllegalArgumentException("Mod not found: " + clazz);
    }

    public <M extends CpuMod> void modRemove(Class<M> clazz) {
        mods.removeIf(mod ->
                mod.itsMe(clazz));
    }

    public void reset() {
        mods.forEach(CpuMod::reset);
    }
}