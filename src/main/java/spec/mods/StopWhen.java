package spec.mods;

import spec.Cpu;
import spec.math.Bites;

public class StopWhen extends WhenPC {

    public StopWhen(int addr) {
        super(addr, Cpu::disable);
    }

    public StopWhen(Bites commandCodes) {
        super(commandCodes, Cpu::disable);
    }
}
