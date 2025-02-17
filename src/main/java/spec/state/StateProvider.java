package spec.state;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.math.Bites;

public interface StateProvider extends JsonState {

    int stateSize();

    void state(Bites bites);

    Bites state();

    default void validateState(String name, Bites actual) {
        if (actual.size() != stateSize()) {
            throw new IllegalArgumentException(String.format(
                    "Invalid " + name + " state size: %s, expected: %s",
                    actual.size(), stateSize()));
        }
    }

    // TODO remove me from here
    @Override
    default JsonElement toJson() {
        return new JsonObject();
    }

    @Override
    default void fromJson(JsonElement element) {
        // do nothing
    }
}
