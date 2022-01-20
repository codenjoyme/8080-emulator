package spec.platforms;

import spec.RomLoader;

import java.net.URL;

public class Lik {

    public static void loadRom(URL baseURL, RomLoader roms) throws Exception {
        // ЛИК
        URL rom1 = new URL(baseURL, "lik/roms/01_zagr.bin");
        roms.loadROM(rom1.toString(), rom1.openStream(), 0xC000);

        URL rom2 = new URL(baseURL, "lik/roms/02_mon-1m.bin");
        roms.loadROM(rom2.toString(), rom2.openStream(), 0xC800);

        URL rom3 = new URL(baseURL, "lik/roms/03_mon-1m_basicLik.bin");
        roms.loadROM(rom3.toString(), rom3.openStream(), 0xD000);

        URL rom4 = new URL(baseURL, "lik/roms/04_basicLik.bin");
        roms.loadROM(rom4.toString(), rom4.openStream(), 0xD800);

        URL rom5 = new URL(baseURL, "lik/roms/05_basicLik.bin");
        roms.loadROM(rom5.toString(), rom5.openStream(), 0xE000);

        URL rom6 = new URL(baseURL, "lik/roms/06_basicLik.bin");
        roms.loadROM(rom6.toString(), rom6.openStream(), 0xE800);
    }

    public static void loadGame(URL baseURL, RomLoader roms, String game) throws Exception {
        URL url = new URL(baseURL, "lik/apps/" + game + ".rks");
        roms.loadRKS(url.toString(), url.openStream());
    }
}
