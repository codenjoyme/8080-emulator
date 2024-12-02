package spec.mods;

import spec.Cpu;

public class StopWhen extends WhenPC {

    public StopWhen(int addr) {
        super(addr, Cpu::disable);
    }

    public StopWhen(String commandName) {
        super(commandName, Cpu::disable);
    }
}
