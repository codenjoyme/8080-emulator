package spec.platforms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlatformFactory {

    private static Map<String, Platform> PLATFORMS = new LinkedHashMap<>(){{
        put("lik", new Lik());
        put("specialist", new Specialist());
    }};

    public static Platform platform(String name) {
        Platform result = PLATFORMS.get(name);
        if (result == null) {
            throw new IllegalArgumentException("Unknown platform: " + name);
        }
        return result;
    }

    public static String next(String name) {
        // PLATFORMS is ordered so we can just iterate over it
        List<String> keys = all();
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(name)) {
                return keys.get((i + 1) % keys.size());
            }
        }
        throw new IllegalArgumentException("Unknown platform: " + name);
    }

    public static List<String> all() {
        return new ArrayList<>(PLATFORMS.keySet());
    }

    public static String valueOf(int index) {
        return all().get(index);
    }

    public static int indexOf(String platform) {
        return all().indexOf(platform);
    }
}
