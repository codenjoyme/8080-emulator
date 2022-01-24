package spec;

import java.util.function.Supplier;

import static spec.WordMath.hex16;
import static spec.WordMath.hex8;

public class KeyLogger {

    private Supplier<Integer> tick;

    public KeyLogger(Supplier<Integer> tick) {
        this.tick = tick;
    }

    public void process(Key key, Integer point) {
        char ch = (char) key.code();
        Logger.debug("Key %s at tick [%s]: ch:'%s' " +
                        "code:0x%s joint:0x%s point:0x%s" +
                        "%s%s%s",
                key.pressed() ? "down" : "up  ",
                tick.get(),
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
}