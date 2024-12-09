package spec.platforms;

import spec.Range;
import spec.RomLoader;

import java.net.URL;

public class Specialist implements Platform {

    public static final String NAME = "specialist";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public Range loadRom(URL base, RomLoader roms) {
        return Range.of(
                roms.loadROM(base, name() + "/roms/monitor0.rom", 0xC000),
                roms.loadROM(base, name() + "/roms/monitor1.rom", 0xC800));
    }

    @Override
    public Range loadGame(URL base, RomLoader roms, String name) {
        return roms.loadRKS(base, name() + "/apps/" + name + "/" + name + ".rks");
    }

    @Override
    public Range loadTest(URL base, RomLoader roms, String name) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
