package spec;

import java.util.HashMap;
import java.util.Map;

import static spec.Key.MOD_NONE;

public class KeyRecord {

    private Map<Integer, Action> scenario = new HashMap<>();

    private Runnable screenShoot;
    private Runnable stopCpu;
    private IOPorts ports;

    public KeyRecord(IOPorts ports, Runnable screenShoot, Runnable stopCpu) {
        this.ports = ports;
        this.screenShoot = screenShoot;
        this.stopCpu = stopCpu;
    }

    public Action when(int tick) {
        Action action = new Action();
        scenario.put(tick, action);
        return action;
    }

    public class Action {

        boolean screenShoot;
        boolean stopCpu;

        Integer keyCode;
        boolean press;
        int mode;

        public Action shot() {
            screenShoot = true;
            return this;
        }

        public Action press(int code) {
            press(code, MOD_NONE);
            return this;
        }

        public Action press(int code, int mode) {
            press = true;
            keyCode = code;
            this.mode = mode;
            return this;
        }

        public Action release(int code) {
            press = false;
            keyCode = code;
            return this;
        }

        public Action stopCpu() {
            stopCpu = true;
            return this;
        }
    }

    public void accept(int tick) {
        Action action = scenario.get(tick);
        if (action == null) return;
        if (action.screenShoot) {
            screenShoot.run();
        }
        if (action.keyCode != null) {
            ports.processKey(new Key(action.keyCode, action.press, action.mode));
        }
        if (action.stopCpu) {
            stopCpu.run();
        }
    }
}
