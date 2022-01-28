package spec.mods;

import spec.Cpu;

public class CallDeep implements CpuMod<Cpu> {

    private int callDeep;

    @Override
    public void on(String event, Cpu cpu) {
        if (event.equals("call")) {
            callDeep++;
        } else if (event.equals("ret")) {
            callDeep--;
        }
    }

    @Override
    public void reset() {
        callDeep = 0;
    }

    public int callDeep() {
        return callDeep;
    }
}
