package spec.mods;

import java.util.Arrays;
import java.util.List;

import static spec.mods.Event.RUN_CALL;
import static spec.mods.Event.RUN_RET;

public class CallDeep implements CpuMod {

    private int callDeep;

    @Override
    public List<Event> supports() {
        return Arrays.asList(RUN_CALL, RUN_RET);
    }

    @Override
    public void on(Event event, Object... params) {
        if (event == RUN_CALL) {
            callDeep++;
        } else if (event == RUN_RET) {
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
