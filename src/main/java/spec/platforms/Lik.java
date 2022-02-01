package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public class Lik {

    public static final boolean PLATFORM = true;

    public static void loadRom(URL base, RomLoader roms) {
        roms.loadROM(base, "lik/roms/01_zagr.bin", 0xC000);
        roms.loadROM(base, "lik/roms/02_mon-1m.bin", 0xC800);
        roms.loadROM(base, "lik/roms/03_mon-1m_basicLik.bin", 0xD000);
        roms.loadROM(base, "lik/roms/04_basicLik.bin", 0xD800);
        roms.loadROM(base, "lik/roms/05_basicLik.bin", 0xE000);
        roms.loadROM(base, "lik/roms/06_basicLik.bin", 0xE800);
    }

    public static Range loadGame(URL base, RomLoader roms, String name) {
        return roms.loadRKS(base, "lik/apps/" + name + ".rks");
    }

    public static Range loadTest(URL base, RomLoader roms, String name) {
        if (name.endsWith(".com")) {
            return roms.loadROM(base, "test/" + name, 0x0000);
        } else {
            return roms.loadRKS(base, "test/" + name);
        }
    }
}
