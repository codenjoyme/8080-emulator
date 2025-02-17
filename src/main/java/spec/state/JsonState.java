package spec.state;

import com.google.gson.JsonElement;

public interface JsonState {

    JsonElement toJson();

    void fromJson(JsonElement jsonElement);
}
