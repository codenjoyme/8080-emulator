package spec.platforms;

import spec.RomLoader;

import java.net.URL;

public class Specialist {

    public static final boolean PLATFORM = false;

    public static void loadRom(URL base, RomLoader roms) {
        roms.loadROM(base, "specialist/roms/monitor0.rom", 0xC000);
        roms.loadROM(base, "specialist/roms/monitor1.rom", 0xC800);
    }

    public static void loadGame(URL base, RomLoader roms, String name) {
        roms.loadRKS(base, "specialist/apps/" + name + "/" + name + ".rks");
    }
}
