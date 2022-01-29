package spec.mods;

import spec.Cpu;

import java.util.function.Consumer;

public class WhenPC extends When {

    public WhenPC(int addr, Consumer<Cpu> trigger) {
        super((event, cpu) -> {
            if (event.equals("PC")) {
                if (cpu.PC() == addr) {
                    trigger.accept(cpu);
                }
            }
        });
    }
}
