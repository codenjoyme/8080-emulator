package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.math.WordMath;
import spec.state.JsonState;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

import static spec.Key.MOD_NONE;

public class KeyRecord implements JsonState {

    public static final int KEY_PRESS_DELTA = 20_000;

    private FileRecorder fileRecorder;
    private Keyboard keyboard;
    private Consumer<String> screenshot;
    private Runnable stopCpu;
    private Runnable pauseCpu;

    private Map<Integer, Action> scenario;
    private int shootIndex; // индекс сделанного скриншота
    private Integer lastRecordedTick;

    public KeyRecord(FileRecorder fileRecorder, Keyboard keyboard, Runnable stopCpu, Runnable pauseCpu) {
        this.fileRecorder = fileRecorder;
        this.keyboard = keyboard;
        this.stopCpu = stopCpu;
        this.pauseCpu = pauseCpu;
        this.shootIndex = 0;
        reset();
    }

    public void screenshot(Consumer<String> screenshot) {
        this.screenshot = screenshot;
    }

    public Action after(int tick) {
        scenario = (scenario == null) ? new LinkedHashMap<>() : scenario;
        Action action = new Action(tick);
        scenario.put(tick, action);
        return action;
    }

    public Action afterLast() {
        Integer lastKey = scenario.keySet().stream().max(Integer::compareTo).orElse(null);
        if (lastKey == null) {
            return after(0);
        }
        return scenario.get(lastKey);
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

    public int load(String base, String path) {
        AtomicReference<Action> after = new AtomicReference<>(begin().after(0));
        fileRecorder.stopWriting();
        fileRecorder.with(base, path);
        fileRecorder.read((delta, key) -> {
            Action it = after.get().after(delta);
            if (key.pressed()) {
                if (key.pause()) {
                    after.set(it.pauseCpu());
                } else {
                    after.set(it.down(key.code(), key.mods()));
                }
            } else {
                after.set(it.up(key.code(), key.mods()));
            }
        });
        after.get().end();
        lastRecordedTick = scenario.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);
        fileRecorder.writeNew();
        return lastRecordedTick;
    }

    public Action at(int index) {
        for (Action action : scenario.values()) {
            if (action.index == index) {
                return action;
            }
        }
        throw new IllegalArgumentException("Action with index not found: " + index);
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("shootIndex", shootIndex);
        result.addProperty("lastRecordedTick", lastRecordedTick);

        JsonObject scenarioJson = new JsonObject();
        if (scenario != null) {
            scenario.forEach((tick, action) ->
                    scenarioJson.add(String.valueOf(tick), action.toJson()));

            result.add("scenario", scenarioJson);
        } else {
            result.add("scenario", null);
        }

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        shootIndex = json.get("shootIndex").getAsInt();
        lastRecordedTick = JsonState.nullableInteger(json.get("lastRecordedTick"));

        scenario = new LinkedHashMap<>();
        JsonObject scenarioJson = JsonState.nullableObject(json.get("scenario"));
        if (scenarioJson != null) {
            scenarioJson.entrySet().forEach(entry -> {
                Action action = new Action(0);
                action.fromJson(entry.getValue());
                scenario.put(Integer.parseInt(entry.getKey()), action);
            });
        }
    }

    public KeyRecord begin() {
        reset();
        return this;
    }

    public boolean ready() {
        return scenario == null || scenario.isEmpty();
    }

    public void stop() {
        reset();
        fileRecorder.startWriting();
    }

    public class Action implements JsonState {

        int index;
        int tick;
        String shoot;
        boolean stopCpu;
        boolean pauseCpu;
        boolean resetRecord;

        Integer keyCode;
        boolean press;
        int mode;

        public Action(int tick) {
            this.tick = tick;
            this.index = KeyRecord.this.scenario.size();
        }

        @Override
        public String toString() {
            if (stopCpu || pauseCpu || resetRecord) {
                return "Action{" +
                        "tick=" + tick +
                        ", stopCpu=" + stopCpu +
                        ", pauseCpu=" + pauseCpu +
                        ", resetRecord=" + resetRecord +
                        '}';
            }
            return "Action{" +
                    "tick=" + tick +
                    ", keyCode='" + (char) keyCode.intValue() +
                    "', press=" + press +
                    ", mode=" + WordMath.bits(mode) +
                    '}';
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

        public Action pauseCpu() {
            pauseCpu = true;
            return this;
        }

        public Action after(int delta) {
            return KeyRecord.this.after(tick + delta);
        }

        public Action shoot(String name, Function<Action, Action> actions) {
            return KeyRecord.this.shoot(this, name, actions);
        }

        public void end() {
            resetRecord = true;
        }

        @Override
        public JsonElement toJson() {
            JsonObject result = new JsonObject();

            result.addProperty("index", index);
            result.addProperty("tick", tick);
            result.addProperty("shoot", shoot);
            result.addProperty("stopCpu", stopCpu);
            result.addProperty("pauseCpu", pauseCpu);
            result.addProperty("resetRecord", resetRecord);
            result.addProperty("keyCode", keyCode);
            result.addProperty("press", press);
            result.addProperty("mode", mode);

            return result;
        }

        @Override
        public void fromJson(JsonElement element) {
            JsonObject json = element.getAsJsonObject();

            index = json.get("index").getAsInt();
            tick = json.get("tick").getAsInt();
            shoot = JsonState.nullableString(json.get("shoot"));
            stopCpu = json.get("stopCpu").getAsBoolean();
            pauseCpu = json.get("pauseCpu").getAsBoolean();
            resetRecord = json.get("resetRecord").getAsBoolean();
            keyCode = JsonState.nullableInteger(json.get("keyCode"));
            press = json.get("press").getAsBoolean();
            mode = json.get("mode").getAsInt();
        }
    }

    public void accept(Cpu cpu) {
        if (scenario == null) {
            return;
        }
        int tick = cpu.tick();

        Action action = scenario.get(tick);

        if (action != null) {
            if (action.shoot != null && screenshot != null) {
                screenshot.accept(action.shoot);
            }
            if (action.keyCode != null) {
                Logger.debugLine("[%s] ", action.index);
                keyboard.processKey(new Key(action.keyCode, action.press, action.mode));
            }
            if (action.stopCpu) {
                stopCpu.run();
            }
            if (action.pauseCpu) {
                pauseCpu.run();
            }
            if (action.resetRecord) {
                reset();
            }
        }

        if ((lastRecordedTick != null && lastRecordedTick == tick)
                || (lastRecordedTick == null && scenario == null))
        {
            lastRecordedTick = null;
            stop();
        }
    }
}