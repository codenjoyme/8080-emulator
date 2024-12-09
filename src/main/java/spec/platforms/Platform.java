package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public interface Platform {

    String name();

    Range loadRom(URL base, RomLoader roms);

    Range loadGame(URL base, RomLoader roms, String name);

    Range loadTest(URL base, RomLoader roms, String name);
}
