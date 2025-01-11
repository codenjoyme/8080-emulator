package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public interface Platform {

    String RESOURCES = "src/main/resources/";

    String name();

    boolean isLikOrSpecialist();

    Range loadRom(URL base, RomLoader roms);

    Range loadGame(URL base, RomLoader roms, String name);

    Range loadBasic(URL base, RomLoader roms, String name);

    Range loadTest(URL base, RomLoader roms, String name);

    default String platform() {
        return RESOURCES + name();
    }

    default String apps() {
        return platform() + "/apps";
    }

    default String app(String name, String type) {
        return apps() + "/" + name + "/" + name + type;
    }

    default String basic(String name, String type) {
        return apps() + "/basic/program/" + name + "/" + name + type;
    }
}
