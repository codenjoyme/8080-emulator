package spec;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.math.Bites;
import spec.state.JsonState;

import static spec.math.Bites.of;
import static spec.math.WordMath.*;

public class Memory implements JsonState {

    protected Bites mem;

    public Memory(int size) {
        mem = new Bites(size);
    }

    protected Memory() {
        // do nothing
    }

    public int read8(int addr) {
        return all().get(addr);
    }

    public void write8(int addr, int bite) {
        all().set(addr, bite);
    }

    public Bites all() {
        return mem;
    }

    public void write8arr(int addr, Bites bites) {
        mem.set(addr, bites);
    }

    public void write8str(int addr, String bites) {
        write8arr(addr, of(bites));
    }

    public void write16(int addr, int word) {
        write8(addr, lo(word));
        addr = inc16(addr);
        write8(addr, hi(word));
    }

    public Bites read8arr(Range range) {
        return mem.array(range);
    }

    public String read8str(Range range) {
        return read8arr(range).toString(false, false);
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        String lines = mem.toString(true, true);
        JsonArray array = new JsonArray();
        for (String line : lines.split("\n")) {
            array.add(line);
        }
        result.add("data", array);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject obj = element.getAsJsonObject();

        JsonArray array = obj.getAsJsonArray("data");
        StringBuilder sb = new StringBuilder();
        for (JsonElement line : array) {
            String string = line.getAsString();
            if (!string.contains(": ")) continue;
            string = string.split(": ")[1];
            sb.append(string).append("\n");
        }
        write8str(0, sb.toString());
    }
}