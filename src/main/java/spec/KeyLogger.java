package spec;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

import static spec.WordMath.hex16;
import static spec.WordMath.hex8;

public class KeyLogger {

    private Supplier<Integer> getTick;
    private File logFile;
    private int precision;
    private int tick;

    public KeyLogger(File file, int precision, Supplier<Integer> getTick) {
        logFile = file;
        this.precision = precision;
        this.getTick = getTick;
        reset();
    }

    public void process(Key key, Integer point) {
        logInFile(key);
        logForConsole(key, point);
    }

    private void logInFile(Key key) {
        int delta = getTick.get() - tick;
        tick = getTick.get();

        try (FileWriter writer = new FileWriter(logFile.getAbsolutePath(), true)) {
            writer.write(String.format(
                    "after(%s).%s(0x%s);\n",
                    (delta / precision == 0) ? 1 : delta / precision,
                    key.pressed() ? "down" : "up",
                    hex8(key.code())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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