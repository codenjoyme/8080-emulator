package spec.platforms;

import spec.Range;
import spec.RomLoader;

public class Specialist implements Platform {

    public static final String NAME = "specialist";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean isLikOrSpecialist() {
        return false;
    }

    @Override
    public Range loadRom(String base, RomLoader roms) {
        return Range.of(
                roms.loadROM(base, platform() + "/roms/monitor0.rom", 0xC000),
                roms.loadROM(base, platform() + "/roms/monitor1.rom", 0xC800));
    }

    @Override
    public Range loadTest(String base, RomLoader roms, String name) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
