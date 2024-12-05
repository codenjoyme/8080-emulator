package spec.stuff;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static spec.stuff.SmartAssert.fail;

public class FileAssert {

    private String base;
    private Map<String, String> hashes = new HashMap<>();

    public FileAssert(String base) {
        this.base = base;
    }

    private File testDir() {
        File result = new File(base);
        if (!result.exists()) {
            result.mkdir();
        }
        return result;
    }

    public void removeTestsData() {
        File dir = testDir();
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            String name = file.getName();
            // на всякий случай чтобы не удалить лишнего
            if (!name.endsWith(".png")
                    && !name.endsWith(".log"))
            {
                continue;
            }

            // перед удалением сохраним хеш, потом по нему будем сравнивать
            hashes.put(file.getAbsolutePath(), hash(file));
            file.delete();
        }
        dir.delete();
    }

    public void check(String info, String name, Consumer<File> save) {
        File file = new File(testDir().getAbsolutePath() + "/" + name);
        String hash = hashes.get(file.getAbsolutePath());

        save.accept(file);

        if (!Objects.equals(hash, hash(file))) {
            fail(info + " was changed.\n"
                    + file.getAbsolutePath() + "\n"
                    + "Please check git diff to see differences.\n");
        }
    }

    private String hash(File file) {
        return HashUtils.hashFile(file, "MD5");
    }

    public static void write(File file, String string) {
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file.getAbsolutePath(), false)) {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(File file) {
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseJson(JsonElement jsonElement, Gson gson, Class<T> clazz) {
        if (jsonElement.isJsonObject()) {
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            return (T) gson.fromJson(jsonElement, type);
        } else if (jsonElement.isJsonArray()) {
            Type type = new TypeToken<List<Object>>() {}.getType();
            return (T) gson.fromJson(jsonElement, type);
        }
        throw new IllegalArgumentException("Unsupported json type: " + jsonElement);
    }

    public void checkJson(String name, BiFunction<List<Object>, Function<String, Map>, Object> function) {
        check(name, name, file -> {
            String data = read(file);

            Gson gson = new Gson();
            JsonElement jsonElement = JsonParser.parseString(data);

            List<Object> values = parseJson(jsonElement, gson, List.class);
            values.forEach(it -> {
                Map<String, Object> map = (Map<String, Object>) it;
                List<Object> input = (List) map.get("input");
                List<Object> fields = (List) map.get("fields");
                Function<String, Map> getField = (String field) -> getField(fields, field);

                Object result = function.apply(input, getField);

                map.put("result", result);
            });

            write(file, asString(values));
        });
    }

    public static String asString(Object data) {
        boolean isJson = data instanceof Map || data instanceof List;
        if (isJson) {
            return new GsonBuilder().setPrettyPrinting()
                    .serializeNulls()
                    .disableHtmlEscaping()
                    .registerTypeAdapter(HashMap.class, new MapSerializer())
                    .registerTypeAdapter(Double.class, new NumberSerializer())
                    .create().toJson(data);
        } else {
            return data.toString();
        }
    }

    private static Map getField(List<Object> fields, String name) {
        Map field = (Map) fields.stream()
                .filter(it2 -> ((Map) it2).get("name").equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No labels field"));
        return (Map) field.get("value");
    }

    static class NumberSerializer implements JsonSerializer<Double> {
        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.intValue());
        }
    }

    static class MapSerializer implements JsonSerializer<Map<?, ?>> {
        @Override
        public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            src = new TreeMap<>(src);
            src.forEach((key, value) -> json.add(String.valueOf(key), context.serialize(value)));
            return json;
        }
    }
}
