package spec.platforms;

import spec.RomLoader;

import java.net.URL;

public class Lik {

    public static void loadRom(URL base, RomLoader roms) throws Exception {
        roms.loadROM(base, "lik/roms/01_zagr.bin", 0xC000);
        roms.loadROM(base, "lik/roms/02_mon-1m.bin", 0xC800);
        roms.loadROM(base, "lik/roms/03_mon-1m_basicLik.bin", 0xD000);
        roms.loadROM(base, "lik/roms/04_basicLik.bin", 0xD800);
        roms.loadROM(base, "lik/roms/05_basicLik.bin", 0xE000);
        roms.loadROM(base, "lik/roms/06_basicLik.bin", 0xE800);
    }

    public static void loadGame(URL base, RomLoader roms, String name) throws Exception {
        roms.loadRKS(base, "lik/apps/" + name + ".rks");
    }

    public static void loadTest(URL base, RomLoader roms, String name) throws Exception {
        roms.loadROM(base, "test/" + name + ".com", 0x0000);
    }
}
