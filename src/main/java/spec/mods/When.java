package spec.mods;

import java.util.function.BiConsumer;

public class When implements CpuMod {

    protected BiConsumer<Event, Object[]> trigger;

    public When(BiConsumer<Event, Object[]> trigger) {
        this.trigger = trigger;
    }

    @Override
    public void on(Event event, Object... params) {
        trigger.accept(event, params);
    }
}
