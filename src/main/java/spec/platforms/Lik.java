package spec.platforms;

import spec.RomLoader;

public class Lik {

    public static void loadRom(String base, RomLoader roms) throws Exception {
        // ЛИК
        roms.loadROM(base + "/lik/roms/01_zagr.bin", 0xC000);
        roms.loadROM(base + "/lik/roms/02_mon-1m.bin", 0xC800);
        roms.loadROM(base + "/lik/roms/03_mon-1m_basicLik.bin", 0xD000);
        roms.loadROM(base + "/lik/roms/04_basicLik.bin", 0xD800);
        roms.loadROM(base + "/lik/roms/05_basicLik.bin", 0xE000);
        roms.loadROM(base + "/lik/roms/06_basicLik.bin", 0xE800);
    }

    public static void loadGame(String base, RomLoader roms, String game) throws Exception {
        roms.loadRKS(base + "/lik/apps/" + game + ".rks");
    }
}
