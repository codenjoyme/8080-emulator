package spec.mods;

public abstract class Modifiable<T> {

    protected CpuMod<T> mod;

    public void on(String event) {
        if (mod != null) {
            mod.on(event, (T) this);
        }
    }

    public void mod(CpuMod<T> mod) {
        this.mod = mod;
    }

    public <M extends CpuMod<T>> M mod(Class<M> clazz) {
        return (M) mod;
    }
}
