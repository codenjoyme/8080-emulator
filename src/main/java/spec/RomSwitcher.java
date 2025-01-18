package spec;

import spec.math.Bites;
import spec.platforms.Lik;
import spec.platforms.Platform;
import spec.platforms.PlatformFactory;

public class RomSwitcher implements StateProvider {

    protected String platform = Lik.NAME;

    private final Hardware hard;

    public RomSwitcher(Hardware hard) {
        this.hard = hard;
    }

    public void load(String base, String rom) {
        hard.loadData(base, rom, platform);
    }

    public void loadRoms(String base) {
        Platform platform = PlatformFactory.platform(this.platform);
        Logger.debug("Load ROMs for platform '%s'", platform.name());
        platform.loadRom(base, hard.roms());
        hard.reset();
    }

    public void nextRom(String base) {
        selectRom(base, PlatformFactory.next(platform));
    }

    public void selectRom(String base, String platform) {
        this.platform = platform;
        Logger.debug("Switch platform to %s", platform);
        hard.pause();
        loadRoms(base);
        hard.reset();
        Logger.debug("Platform switched");
    }

    public Platform current() {
        return PlatformFactory.platform(platform);
    }

    @Override
    public int stateSize() {
        return 1;
    }

    @Override
    public void state(Bites bites) {
        validateState("ROM switcher", bites);

        platform = PlatformFactory.valueOf(bites.get(0));
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());

        bites.set(0, PlatformFactory.indexOf(platform));

        return bites;
    }
}
