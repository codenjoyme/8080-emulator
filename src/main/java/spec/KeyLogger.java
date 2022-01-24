package spec;

import java.util.function.Supplier;

import static spec.WordMath.hex16;
import static spec.WordMath.hex8;

public class KeyLogger {

    private static final boolean FOR_TEST = true;
    public static int K1 = 1;

    private Supplier<Integer> getTick;
    private int tick;

    public KeyLogger(Supplier<Integer> getTick) {
        this.getTick = getTick;
        reset();
        if (FOR_TEST) {
            Logger.info("KeyRecord.Action action = app.record();");
        }
    }

    public void process(Key key, Integer point) {
        if (FOR_TEST) {
            logForTest(key, point);
        } else {
            logForCondole(key, point);
        }
    }

    private void logForTest(Key key, Integer point) {
        int delta = getTick.get() - tick;
        tick = getTick.get();
        Logger.info("action = action.after(%s).%s(0x%s);",
                (delta / K1 == 0) ? 1 : delta / K1,
                key.pressed() ? "down" : "up",
                hex8(key.code()));
    }

    private void logForCondole(Key key, Integer point) {
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