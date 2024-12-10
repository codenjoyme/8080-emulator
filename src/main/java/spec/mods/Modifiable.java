package spec.mods;

import java.util.*;

public abstract class Modifiable {

    private final List<CpuMod> readMem = new ArrayList<>(5);
    private final List<CpuMod> writeMem = new ArrayList<>(5);
    private final List<CpuMod> changePC = new ArrayList<>(5);
    private final List<CpuMod> runCall = new ArrayList<>(5);
    private final List<CpuMod> runRet = new ArrayList<>(5);

    public void on(Event event, Object... params) {
        List<CpuMod> mods = mods(event);

        for (int i = 0; i < mods.size(); i++) {
            CpuMod mod = mods.get(i);
            if (mod != null) {
                mod.on(event, params);
            }
        }
    }

    private List<CpuMod> mods(Event event) {
        switch (event) {
            case CHANGE_PC:
                return changePC;
            case READ_MEM:
                return readMem;
            case WRITE_MEM:
                return writeMem;
            case RUN_CALL:
                return runCall;
            case RUN_RET:
                return runRet;
            default:
                throw new IllegalArgumentException("Event not supported: " + event);
        }
    }

    public void modAdd(CpuMod mod) {
        mod.supports().forEach(event -> {
            List<CpuMod> mods = mods(event);
            if (mods != null) {
                mods.add(mod);
            }
        });
    }

    public CallDeep callDeepMod() {
        return (CallDeep) runCall.get(0); // This is only for performance reasons
    }

    public <M extends CpuMod> void modRemove(Class<M> clazz) {
        allMods().forEach(modsList -> modsList.removeIf(mod -> clazz.isInstance(mod)));
    }

    private List<List<CpuMod>> allMods() {
        return Arrays.asList(readMem, writeMem, changePC, runCall, runRet);
    }

    public void reset() {
        allMods().forEach(modsList -> modsList.forEach(CpuMod::reset));
    }
}