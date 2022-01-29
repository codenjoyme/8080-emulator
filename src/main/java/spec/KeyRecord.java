package spec;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

import static spec.Key.MOD_NONE;

public class KeyRecord {

    public static final int KEY_PRESS_DELTA = 20_000;
    private Map<Integer, Action> scenario;
    private FileRecorder fileRecorder;
    private IOPorts ports;
    private Consumer<String> screenShoot;
    private Runnable stopCpu;
    private int shootIndex; // индекс сделанного скриншота
    private Integer lastRecordedTick;

    public KeyRecord(FileRecorder fileRecorder, IOPorts ports, Runnable stopCpu) {
        this.fileRecorder = fileRecorder;
        this.ports = ports;
        this.stopCpu = stopCpu;
        this.shootIndex = 0;
        reset();
    }

    public void screenShoot(Consumer<String> screenShoot) {
        this.screenShoot = screenShoot;
    }

    public Action after(int tick) {
        scenario = (scenario == null) ? new HashMap<>() : scenario;
        Action action = new Action(tick);
        scenario.put(tick, action);
        return action;
    }

    public Action shoot(String name, Function<Action, Action> actions) {
        return shoot(after(0), name, actions);
    }

    private Action shoot(Action action, String name, Function<Action, Action> actions) {
        return actions.apply(action)
                .shoot(name);
    }

    public KeyRecord reset() {
        scenario = null;
        return this;
    }

    public boolean ready() {
        return fileRecorder.ready();
    }

    public int load(String path) {
        AtomicReference<Action> after = new AtomicReference<>(reset().after(0));
        fileRecorder.stopWriting();
        fileRecorder.with(path);
        fileRecorder.read((delta, key) -> {
            Action it = after.get().after(delta);
            if (key.pressed()) {
                after.set(it.down(key.code()));
            } else {
                after.set(it.up(key.code()));
            }
        });
        lastRecordedTick = scenario.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);
        fileRecorder.writeNew();
        return lastRecordedTick;
    }

    public class Action {

        int tick;
        String shoot;
        boolean stopCpu;
        boolean resetRecord;

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
                action = action.press(ch).after(KEY_PRESS_DELTA);
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
            return after(KEY_PRESS_DELTA).up(code, mode);
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

        public void reset() {
            resetRecord = true;
        }
    }

    public void accept(int tick) {
        if (scenario == null) return;

        Action action = scenario.get(tick);

        if (action != null) {
            if (action.shoot != null && screenShoot != null) {
                screenShoot.accept(action.shoot);
            }
            if (action.keyCode != null) {
                ports.processKey(new Key(action.keyCode, action.press, action.mode));
            }
            if (action.stopCpu) {
                stopCpu.run();
            }
            if (action.resetRecord) {
                reset();
            }
        }

        if (lastRecordedTick != null && lastRecordedTick == tick) {
            lastRecordedTick = null;
            fileRecorder.startWriting();
        }
    }
}