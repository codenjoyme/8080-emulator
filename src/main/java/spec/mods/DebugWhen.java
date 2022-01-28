package spec.mods;

import spec.Cpu;

public class DebugWhen implements CpuMod<Cpu> {

    private int addr;
    private Runnable trigger;

    public DebugWhen(int addr, Runnable trigger) {
        this.addr = addr;
        this.trigger = trigger;
    }

    @Override
    public void on(String event, Cpu cpu) {
        if (event.equals("PC")) {
            if (cpu.PC() == addr) {
                trigger.run();
            }
        }
    }

    @Override
    public void reset() {
        // do nothing
    }
}
