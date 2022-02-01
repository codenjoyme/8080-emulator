package spec.mods;

public class CallDeep implements CpuMod {

    private int callDeep;

    @Override
    public void on(String event, Object... params) {
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
