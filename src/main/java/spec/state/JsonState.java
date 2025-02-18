package spec.state;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface JsonState {

    JsonElement toJson();

    void fromJson(JsonElement element);

    static JsonObject nullableObject(JsonElement element) {
        return element == null || element.isJsonNull()
                ? null
                : element.getAsJsonObject();
    }

    static String nullableString(JsonElement element) {
        return element == null || element.isJsonNull()
                ? null
                : element.getAsString();
    }

    static Integer nullableInteger(JsonElement element) {
        return element == null || element.isJsonNull()
                ? null
                : element.getAsInt();
    }
}
