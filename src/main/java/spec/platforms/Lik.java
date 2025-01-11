package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public class Lik implements Platform {

    public static final String NAME = "lik";

    public static final int START_ZAGRUZCHIK = 0xC000;
    public static final int START_MONITOR_1M = 0xC800;
    public static final int START_BASIC_LIK_V2 = 0xD240;

    public static final int START_ROM1 = START_ZAGRUZCHIK;
    public static final int START_ROM2 = START_MONITOR_1M;
    public static final int START_ROM3 = 0xD000;
    public static final int START_ROM4 = 0xD800;
    public static final int START_ROM5 = 0xE000;
    public static final int START_ROM6 = 0xE800;
    public static final int FINISH_ROM6 = 0xF000;

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean isLikOrSpecialist() {
        return true;
    }

    @Override
    public Range loadRom(URL base, RomLoader roms) {
        return Range.of(
            roms.loadROM(base, platform() + "/roms/01_zagr.bin", START_ROM1),
            roms.loadROM(base, platform() + "/roms/02_mon-1m.bin", START_ROM2),
            roms.loadROM(base, platform() + "/roms/03_mon-1m_basicLik.bin", START_ROM3),
            roms.loadROM(base, platform() + "/roms/04_basicLik.bin", START_ROM4),
            roms.loadROM(base, platform() + "/roms/05_basicLik.bin", START_ROM5),
            roms.loadROM(base, platform() + "/roms/06_basicLik.bin", START_ROM6));
    }

    @Override
    public Range loadGame(URL base, RomLoader roms, String name) {
        return roms.loadRKS(base, app(name, ".rks"));
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
