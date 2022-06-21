package spec.assembler.command.system;

import spec.Cpu;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

public class HLT extends Command {

    private static final List<Integer> CODES = from(
            0x76);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 0; // расчет идет в apply
    }

    @Override
    public void apply(int command, Registry r) {
        Cpu cpu = (Cpu) r;
        int tacts = cpu.tact();
        int haltsToInterrupt = (-tacts - 1) / 4 + 1;
        cpu.tact(tacts + haltsToInterrupt * 4);
        cpu.disabled();
    }
}