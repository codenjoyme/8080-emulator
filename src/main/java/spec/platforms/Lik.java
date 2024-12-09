package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public class Lik implements Platform {

    public static final String NAME = "lik";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Range loadRom(URL base, RomLoader roms) {
        return Range.of(
            roms.loadROM(base, platform() + "/roms/01_zagr.bin", 0xC000),
            roms.loadROM(base, platform() + "/roms/02_mon-1m.bin", 0xC800),
            roms.loadROM(base, platform() + "/roms/03_mon-1m_basicLik.bin", 0xD000),
            roms.loadROM(base, platform() + "/roms/04_basicLik.bin", 0xD800),
            roms.loadROM(base, platform() + "/roms/05_basicLik.bin", 0xE000),
            roms.loadROM(base, platform() + "/roms/06_basicLik.bin", 0xE800));
    }

    @Override
    public Range loadGame(URL base, RomLoader roms, String name) {
        return roms.loadRKS(base, platform() + "/apps/" + name + "/" + name + ".rks");
    }

    @Override
    public Range loadTest(URL base, RomLoader roms, String name) {
        if (name.endsWith(".com")) {
            return roms.loadROM(base, platform() + "/test/" + name, 0x0000);
        } else {
            return roms.loadRKS(base, platform() + "/test/" + name);
        }
    }
}
