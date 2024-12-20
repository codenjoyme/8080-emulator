package spec;

import java.util.function.Supplier;

import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class KeyLogger {

    private Supplier<Integer> getTick;
    private int tick;
    private FileRecorder recorder;
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
}