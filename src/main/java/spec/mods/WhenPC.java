package spec.mods;

import spec.Cpu;

import java.util.function.Consumer;

import static spec.mods.Event.CHANGE_PC;

public class WhenPC extends When {

    public WhenPC(int addr, Consumer<Cpu> trigger) {
        super((event, params) -> {
            if (event == CHANGE_PC) {
                int pc = (int)(params[0]);
                Cpu cpu = (Cpu)(params[2]);
                if (pc == addr) {
                    trigger.accept(cpu);
                }
            }
        });
    }
}
