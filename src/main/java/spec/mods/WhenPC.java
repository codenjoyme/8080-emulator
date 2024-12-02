package spec.mods;

import spec.Cpu;
import spec.Range;
import spec.assembler.Command;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import static spec.mods.Event.CHANGE_PC;

public class WhenPC extends When {

    public WhenPC(BiPredicate<Cpu, Command> checker, Consumer<Cpu> trigger) {
        super((event, params) -> {
            if (event == CHANGE_PC) {
                Command command = (Command) (params[0]);
                Cpu cpu = (Cpu) (params[1]);
                if (checker.test(cpu, command)) {
                    trigger.accept(cpu);
                }
            }
        });
    }

    public WhenPC(int addr, Consumer<Cpu> trigger) {
        this((cpu, command) -> cpu.PC() == addr,
                trigger);
    }

    public WhenPC(Range range, Consumer<Cpu> trigger) {
        this((cpu, command) -> range.includes(cpu.PC()),
                trigger);
    }

    public WhenPC(List<Integer> commandCodes, Consumer<Cpu> trigger) {
        this((cpu, command) -> commandCodes.equals(cpu.commandBites()),
                trigger);
    }
}