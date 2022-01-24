package spec;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static spec.Key.MOD_NONE;
import static spec.KeyLogger.K10;

public class KeyRecord {

    private Map<Integer, Action> scenario = new HashMap<>();

    // наша клавиатура
    private IOPorts ports;

    // методы внешних компонентов, которые мы хотим дергать
    private Consumer<String> screenShoot;
    private Runnable stopCpu;

    private int shootIndex; // индекс сделанного скриншота

    public KeyRecord(IOPorts ports, Consumer<String> screenShoot, Runnable stopCpu) {
        this.ports = ports;
        this.screenShoot = screenShoot;
        this.stopCpu = stopCpu;
        this.shootIndex = 0;
    }

    public Action after(int tick) {
        Action action = new Action(tick);
        scenario.put(tick, action);
        return action;
    }

    public Action shoot(String name, Function<Action, Action> actions) {
        return shoot(new Action(0), name, actions);
    }

    private Action shoot(Action action, String name, Function<Action, Action> actions) {
        return actions.apply(action)
                .shoot(name);
    }

    public KeyRecord reset() {
        scenario.clear();
        return this;
    }

    public class Action {

        int tick;
        String shoot;
        boolean stopCpu;

        Integer keyCode;
        boolean press;
        int mode;

        public Action(int tick) {
            this.tick = tick;
        }

        public Action shoot(String name) {
            if (name != null) {
                shoot = ++shootIndex + "_" + name;
            }
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

        public Action shoot(String name, Function<Action, Action> actions) {
            return KeyRecord.this.shoot(this, name, actions);
        }
    }

    public void accept(int tick) {
        // нам интересны каждые 10 000 тиков, реже смотреть нет смысла
        if (tick % K10 != 0) return;

        int kiloTick = tick / K10;
        Action action = scenario.get(kiloTick);
        if (action == null) return;

        if (action.shoot != null) {
            screenShoot.accept(action.shoot);
        }
        if (action.keyCode != null) {
            ports.processKey(new Key(action.keyCode, action.press, action.mode));
        }
        if (action.stopCpu) {
            stopCpu.run();
        }
    }
}
