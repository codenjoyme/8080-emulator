package spec.mods;

import spec.Cpu;

import java.util.function.Consumer;

public class DebugWhen extends WhenPC {

    public DebugWhen(int addr, Runnable trigger) {
        super(addr, cpu -> trigger.run());
    }

    public DebugWhen(int addr, Consumer<Cpu> trigger) {
        super(addr, trigger::accept);
    }
}
