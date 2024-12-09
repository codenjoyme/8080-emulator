package spec;

import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.net.URL;

public class RomSwitcher {

    private boolean lik = true;

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
}
