package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public interface Platform {

    String name();

    boolean isLikOrSpecialist();

    Range loadRom(URL base, RomLoader roms);

    default Range loadGame(URL base, RomLoader roms, String name) {
        return roms.loadRKS(base, app(name, ".rks"));
    }

    default Range loadBasic2(URL base, RomLoader roms, String name) {
        return roms.loadBSS(base, basic("basic", name, ".bss"));
    }

    default Range loadBasic1(URL base, RomLoader roms, String name) {
        return roms.loadBS1(base, basic("basic2", name, ".bs1"));
    }

    Range loadTest(URL base, RomLoader roms, String name);

    default String platform() {
        return name();
    }

    default String apps() {
        return platform() + "/apps";
    }

    default String app(String name, String type) {
        return apps() + "/" + name + "/" + name + type;
    }

    default String basic(String basic, String name, String type) {
        return apps() + "/" + basic + "/program/" + name + "/" + name + type;
    }
}
