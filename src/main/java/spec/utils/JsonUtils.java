package spec.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JsonUtils {

    public static String asString(JsonElement element) {
        return gson().toJson(element);
    }

    public static String asString(Object data) {
        boolean isJson = data instanceof Map || data instanceof List;
        if (isJson) {
            return gson().toJson(data);
        } else {
            return data.toString();
        }
    }

    public static Gson gson() {
        return new GsonBuilder().setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .registerTypeAdapter(HashMap.class, new MapSerializer())
                .registerTypeAdapter(Double.class, new NumberSerializer())
                .create();
    }

    public static JsonElement parse(String json) {
        return JsonParser.parseString(json);
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
