package spec.mods;

public class StopWhen extends WhenPC {

    public StopWhen(int addr) {
        super(addr, (cpu, pc) -> cpu.disabled());
    }
}
