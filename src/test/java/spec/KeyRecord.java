package spec;

import java.util.HashMap;
import java.util.Map;

import static spec.Key.MOD_NONE;

public class KeyRecord {

    public static final int K10 = 10_000;
    private Map<Integer, Action> scenario = new HashMap<>();

    private Runnable screenShoot;
    private Runnable stopCpu;
    private IOPorts ports;

    public KeyRecord(IOPorts ports, Runnable screenShoot, Runnable stopCpu) {
        this.ports = ports;
        this.screenShoot = screenShoot;
        this.stopCpu = stopCpu;
    }

    public Action after(int tick) {
        System.out.println(tick);
        Action action = new Action(tick);
        scenario.put(tick, action);
        return action;
    }

    public class Action {

        int tick;
        boolean screenShoot;
        boolean stopCpu;

        Integer keyCode;
        boolean press;
        int mode;

        public Action(int tick) {
            this.tick = tick;
        }

        public Action shot() {
            screenShoot = true;
            return this;
        }

        public Action enter(String text) {
            Action action = this;
            for (char ch : text.toCharArray()) {
                action = action.press(ch).after(2);
            }
            return action;
        }

        public Action press(int code) {
            return press(code, MOD_NONE);
        }

        public Action press(int code, int mode) {
            // жмем кнопку в этом тике
            down(code, mode);
            // а это уже другой Action через 20k тиков, в котором отпускаем кнопку
            return after(2).up(code, mode);
        }

        public Action down(int code) {
            return down(code, MOD_NONE);
        }

        public Action down(int code, int mode) {
            press = true;
            keyCode = code;
            this.mode = mode;
            return this;
        }

        public Action up(int code) {
            return up(code, MOD_NONE);
        }

        public Action up(int code, int mode) {
            press = false;
            keyCode = code;
            this.mode = mode;
            return this;
        }

        public Action stopCpu() {
            stopCpu = true;
            return this;
        }

        public Action after(int delta) {
            return KeyRecord.this.after(tick + delta);
        }
    }

    public void accept(int tick) {
        // нам интересны каждые 10 000 тиков, реже смотреть нет смысла
        if (tick % K10 != 0) return;
        int kiloTick = tick / K10;
        Action action = scenario.get(kiloTick);
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
