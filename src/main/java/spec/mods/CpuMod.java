package spec.mods;

public interface CpuMod {

    void on(Event event, Object... params);

    default void reset() {
        // do nothing
    }

    default <M extends CpuMod> boolean itsMe(Class<M> clazz) {
        return getClass().equals(clazz);
    }
}
