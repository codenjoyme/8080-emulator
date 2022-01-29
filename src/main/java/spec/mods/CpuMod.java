package spec.mods;

public interface CpuMod<T> {

    void on(String event, T data);

    void reset();

    default <M extends CpuMod<T>> boolean itsMe(Class<M> clazz) {
        return getClass().equals(clazz);
    }
}
