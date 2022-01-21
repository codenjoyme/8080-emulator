package spec.platforms;

import spec.RomLoader;

public class Specialist {

    public static void loadRom(String base, RomLoader roms) throws Exception {
        roms.loadROM(base + "/specialist/roms/monitor0.rom", 0xC000);
        roms.loadROM(base + "/specialist/roms/monitor1.rom", 0xC800);
    }

    public static void loadGame(String base, RomLoader roms, String game) throws Exception {
        roms.loadRKS(base + "/specialist/apps/" + game + ".rks");
    }
}
