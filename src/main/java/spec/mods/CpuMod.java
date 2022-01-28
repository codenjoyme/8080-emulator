package spec.mods;

public interface CpuMod<T> {

    void on(String event, T data);

    void reset();
}
