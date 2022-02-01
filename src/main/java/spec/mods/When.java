package spec.mods;

import java.util.function.BiConsumer;

public class When implements CpuMod {

    protected BiConsumer<String, Object[]> trigger;

    public When(BiConsumer<String, Object[]> trigger) {
        this.trigger = trigger;
    }

    @Override
    public void on(String event, Object... params) {
        trigger.accept(event, params);
    }

    @Override
    public void reset() {
        // do nothing
    }
}
