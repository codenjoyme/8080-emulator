package spec.mods;

import java.util.List;

public interface CpuMod {

    void on(Event event, Object... params);

    default void reset() {
        // do nothing
    }

    List<Event> supports();

    default <M extends CpuMod> boolean itsMe(Class<M> clazz) {
        return getClass().equals(clazz);
    }
}
