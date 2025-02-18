package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.state.JsonState;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spec.KeyCode.PAUSE;
import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class FileRecorder implements JsonState {

    public static final Pattern RECORD_LINE = Pattern.compile("after\\((.+?)\\).(up|down)\\(0x(.+?)(, 0x(.+?))?\\);|stop\\(\\);");

    private String base;
    private final Runnable onStatusChanged;

    private String path;
    private boolean writing;

    public FileRecorder(String base, Runnable onStatusChanged) {
        this.base = base;
        writing = false;
        this.onStatusChanged = onStatusChanged;
    }

    public void write(int delta, Key key) {
        if (!writing) return;

        writeLine(String.format(
                "after(%s).%s(0x%s%s);",
                (delta == 0) ? 1 : delta,
                key.pressed() ? "down" : "up",
                hex8(key.code()),  // TODO #7 тут пишется на самом деле не 1 байт, а up to 4 байт
                (key.mods() != 0x0000) ? (", 0x" + hex16(key.mods())) : ""));
    }

    private void writeLine(String string) {
        try (FileWriter writer = new FileWriter(base + path, true)) {
            writer.write(string + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopWriting() {
        Logger.debug("FileRecord writing disabled");
        writing = false;
        onStatusChanged.run();
    }

    public void startWriting() {
        Logger.debug("FileRecord writing enabled");
        writing = true;
        onStatusChanged.run();
    }

    public void read(BiConsumer<Integer, Key> onLine) {
        try {
            int index = 0;
            for (String line : Files.readAllLines(new File(base + path).toPath())) {
                index++;
                if (line.startsWith("//")) continue;
                if (line.equals("stop();")) {
                    onLine.accept(1, new Key(PAUSE, true, 0x00));
                    continue;
                }

                Matcher matcher = RECORD_LINE.matcher(line);
                if (!matcher.matches() || (matcher.groupCount() != 3 && matcher.groupCount() != 5)) {
                    throw new IllegalArgumentException(String.format(
                            "Error reading key record file at %s: %s",
                            index, line));
                }

                int delta = Integer.parseInt(matcher.group(1));
                boolean press = matcher.group(2).equals("down");
                int code = Integer.parseInt(matcher.group(3), 16);
                int mods = (matcher.group(5) == null) ? 0x00 : Integer.parseInt(matcher.group(5), 16);

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

    public void with(String base, String path) {
        Logger.info("Saving records to: %s", base + path);

        this.base = base;
        this.path = path;
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("path", path);
        result.addProperty("writing", writing);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        path = JsonState.nullableString(json.get("path"));
        writing = json.get("writing").getAsBoolean();
    }

    public void base(String base) {
        this.base = base;
    }
}
