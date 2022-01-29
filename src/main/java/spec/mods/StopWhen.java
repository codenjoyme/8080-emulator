package spec.mods;

import spec.Cpu;

public class StopWhen extends WhenPC {

    public StopWhen(int addr) {
        super(addr, Cpu::disabled);
    }
}
