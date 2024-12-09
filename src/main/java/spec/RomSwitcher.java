package spec;

import spec.math.Bites;
import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.net.URL;

public class RomSwitcher implements StateProvider {

    protected boolean lik = true;

    private final Hardware hard;

    public RomSwitcher(Hardware hard) {
        this.hard = hard;
    }

    public void load(URL base, String rom) {
        hard.loadData(base, rom, lik);
    }

    public void loadRoms(URL base) {
        if (lik) {
            Lik.loadRom(base, hard.roms());
        } else {
            Specialist.loadRom(base, hard.roms());
        }

        hard.reset();
    }


    public void nextRom(URL base) {
        lik = !lik;
        Logger.debug("Switch to %s", lik ? "LIK" : "Specialist");
        hard.pause();
        loadRoms(base);
        hard.reset();
    }

    public String current() {
        return lik ? "lik" : "specialist";
    }

    @Override
    public int stateSize() {
        return 1;
    }

    @Override
    public void state(Bites bites) {
        if (bites.size() != stateSize()) {
            throw new IllegalArgumentException("Invalid size of ROM switcher state: " + bites.size());
        }
        lik = bites.get(0) == 1;
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());

        bites.set(0, lik ? 1 : 0);

        return bites;
    }
}
