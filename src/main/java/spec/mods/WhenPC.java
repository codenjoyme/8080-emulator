package spec.mods;

import spec.Cpu;

import java.util.function.Consumer;

public class WhenPC extends When {

    public WhenPC(int addr, Consumer<Cpu> trigger) {
        super((event, params) -> {
            if (event.equals("pc")) {
                Cpu cpu = (Cpu)(params[0]);
                if (cpu.PC() == addr) {
                    trigger.accept(cpu);
                }
            }
        });
    }
}
