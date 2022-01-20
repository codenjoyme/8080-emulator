package spec.platforms;

import spec.RomLoader;

import java.net.URL;

public class Specialist {

    public static void loadRom(URL baseURL, RomLoader roms) throws Exception {
        URL rom0 = new URL(baseURL, "specialist/roms/monitor0.rom");
        roms.loadROM(rom0.toString(), rom0.openStream(), 0xC000);

        URL rom1 = new URL(baseURL, "specialist/roms/monitor1.rom");
        roms.loadROM(rom1.toString(), rom1.openStream(), 0xC800);
    }

    public static void loadGame(URL baseURL, RomLoader roms, String game) throws Exception {
        URL url = new URL(baseURL, "specialist/apps/" + game + ".rks");
        roms.loadRKS(url.toString(), url.openStream());
    }
}
