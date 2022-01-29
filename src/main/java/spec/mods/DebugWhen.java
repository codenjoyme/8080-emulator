package spec.mods;

public class DebugWhen extends WhenPC {

    public DebugWhen(int addr, Runnable trigger) {
        super(addr, cpu -> trigger.run());
    }
}
