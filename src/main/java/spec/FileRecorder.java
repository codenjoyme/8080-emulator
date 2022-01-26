package spec;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spec.WordMath.hex8;

public class FileRecorder {

    public static final Pattern RECORD_LINE = Pattern.compile("after\\((.*)\\).(up|down)\\(0x(.*)\\);");
    private File file;
    private boolean writing;

    public FileRecorder(File file) {
        this.file = file;
        startWriting();
    }

    public void write(int delta, Key key) {
        if (!writing) return;

        writeLine(String.format(
                "after(%s).%s(0x%s);",
                (delta == 0) ? 1 : delta,
                key.pressed() ? "down" : "up",
                hex8(key.code())));
    }

    private void writeLine(String string) {
        try (FileWriter writer = new FileWriter(file.getAbsolutePath(), true)) {
            writer.write(string + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopWriting() {
        writing = false;
    }

    public boolean ready() {
        return writing;
    }

    public void startWriting() {
        writing = true;
    }

    public void read(BiConsumer<Integer, Key> onLine) {
        try  {
            int index = 0;
            for (String line : Files.readAllLines(file.toPath())) {
                index++;
                if (line.startsWith("//")) continue;

                Matcher matcher = RECORD_LINE.matcher(line);
                if (!matcher.matches() || matcher.groupCount() != 3) {
                    throw new IllegalArgumentException(String.format(
                            "Error reading key record file at %s: %s",
                            index, line));
                }

                int delta = Integer.parseInt(matcher.group(1));
                boolean press = matcher.group(2).equals("down");
                int mods = 0x00; // TODO save mods also
                int code = Integer.parseInt(matcher.group(3), 16);

                Key key = new Key(code, press, mods);
                onLine.accept(delta, key);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeNew() {
        // do nothing
    }

    public void with(String path) {
        this.file = new File(path);
    }
}
