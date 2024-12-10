package spec.mods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Modifiable {

    protected Map<Event, CpuMod[]> mods = new HashMap<>();

    public void on(Event event, Object... params) {
        CpuMod[] mods = mods(event);
        if (mods == null) {
            return;
        }

        for (int i = 0; i < mods.length; i++) {
            mods[i].on(event, params);
        }
    }

    private CpuMod[] mods(Event event) {
        return mods.get(event);
    }

    public void modAdd(CpuMod mod) {
        mod.supports().forEach(event -> {
            CpuMod[] arr = mods(event);
            CpuMod[] newArr = new CpuMod[arr == null ? 1 : arr.length + 1];
            if (arr != null) {
                System.arraycopy(arr, 0, newArr, 0, arr.length);
            }
            newArr[newArr.length - 1] = mod;
            mods.put(event, newArr);
        });

    }

    public <M extends CpuMod> M mod(Class<M> clazz) {
        return mods.values().stream()
                .flatMap(Arrays::stream)
                .filter(mod -> mod.itsMe(clazz))
                .map(clazz::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Mod not found: " + clazz));
    }

    public <M extends CpuMod> void modRemove(Class<M> clazz) {
        mods.values().forEach(arr -> {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].itsMe(clazz)) {
                    CpuMod[] newArr = new CpuMod[arr.length - 1];
                    System.arraycopy(arr, 0, newArr, 0, i);
                    System.arraycopy(arr, i + 1, newArr, i, arr.length - i - 1);
                    mods.put(arr[i].supports().get(0), newArr);
                    return;
                }
            }
        });
    }

    public void reset() {
        mods.values().forEach(arr -> {
            for (CpuMod mod : arr) {
                mod.reset();
            }
        });
    }
}