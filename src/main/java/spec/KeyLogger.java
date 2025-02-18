package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.state.JsonState;

import java.util.function.Supplier;

import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class KeyLogger implements JsonState {

    private Supplier<Integer> getTick;
    private FileRecorder recorder;

    private int tick;
    private boolean console;

    public KeyLogger(FileRecorder recorder, Supplier<Integer> getTick) {
        this.recorder = recorder;
        this.getTick = getTick;
        reset();
        console(true);
    }

    public void console(boolean print) {
        console = print;
    }

    public void process(Key key, Integer point) {
        logInFile(key);
        if (console) {
            logForConsole(key, point);
        }
    }

    private void logInFile(Key key) {
        int nextTick = getTick.get();
        int delta = nextTick - tick;
        tick = nextTick;

        recorder.write(delta, key);
    }

    private void logForConsole(Key key, Integer point) {
        char ch = (char) key.code();
        Logger.debug("Key %s at tick [%s]: ch:'%s' " +
                        "code:0x%s joint:0x%s point:0x%s" +
                        "%s%s%s",
                key.pressed() ? "down" : "up  ",
                getTick.get(),
                String.valueOf(ch == '\n' ? "\\n" :
                        ch == '\r' ? "\\r" :
                                ch == '\b' ? "\\b" : ch),
                hex8(key.code()),
                hex16(key.joint()),
                hex8(point),
                key.ctrl() ? " ctrl" : "",
                key.alt() ? " alt" : "",
                key.shift() ? " shift" : ""
        );
    }

    public void reset() {
        tick = 0;
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("tick", tick);
        result.addProperty("console", console);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        tick = json.get("tick").getAsInt();
        console = json.get("console").getAsBoolean();
    }
}