package spec.mods;

import spec.Cpu;

import java.util.function.BiConsumer;

public class When implements CpuMod<Cpu> {

    private BiConsumer<String, Cpu> trigger;

    public When(BiConsumer<String, Cpu> trigger) {
        this.trigger = trigger;
    }

    @Override
    public void on(String event, Cpu cpu) {
        trigger.accept(event, cpu);
    }

    @Override
    public void reset() {
        // do nothing
    }
}
