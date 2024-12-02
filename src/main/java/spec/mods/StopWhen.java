package spec.mods;

import spec.Cpu;

import java.util.List;

public class StopWhen extends WhenPC {

    public StopWhen(int addr) {
        super(addr, Cpu::disable);
    }

    public StopWhen(List<Integer> commandCodes) {
        super(commandCodes, Cpu::disable);
    }
}
