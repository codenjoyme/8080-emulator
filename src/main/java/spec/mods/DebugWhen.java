package spec.mods;

import spec.Cpu;

import java.util.function.BiConsumer;

public class DebugWhen extends WhenPC {

    public DebugWhen(int addr, Runnable trigger) {
        super(addr, (cpu, pc) -> trigger.run());
    }

    public DebugWhen(int addr, BiConsumer<Cpu, Integer> trigger) {
        super(addr, trigger::accept);
    }
}
