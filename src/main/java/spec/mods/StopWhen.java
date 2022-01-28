package spec.mods;

import spec.Cpu;

public class StopWhen implements CpuMod<Cpu> {

    private int addr;

    public StopWhen(int addr) {
        this.addr = addr;
    }

    @Override
    public void on(String event, Cpu cpu) {
        if (event.equals("PC")) {
            if (cpu.PC() == addr) {
                cpu.disabled();
            }
        }
    }

    @Override
    public void reset() {
        // do nothing
    }
}
