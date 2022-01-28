package spec.mods;

import spec.Registry;

public class CallDeep implements CpuMod<Registry> {

    private int callDeep;

    @Override
    public void on(String event, Registry registry) {
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
