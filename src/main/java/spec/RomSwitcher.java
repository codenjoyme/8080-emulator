package spec;

import spec.math.Bites;
import spec.platforms.Lik;
import spec.platforms.Platform;
import spec.platforms.PlatformFactory;

import java.net.URL;

public class RomSwitcher implements StateProvider {

    protected String platform = Lik.NAME;

    private final Hardware hard;

    public RomSwitcher(Hardware hard) {
        this.hard = hard;
    }

    public void load(URL base, String rom) {
        hard.loadData(base, rom, platform);
    }

    public void loadRoms(URL base) {
        PlatformFactory.platform(platform)
                .loadRom(base, hard.roms());
        hard.reset();
    }

    public void nextRom(URL base) {
        platform = PlatformFactory.next(platform);
        Logger.debug("Switch to %s", platform);
        hard.pause();
        loadRoms(base);
        hard.reset();
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
