package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;
import spec.mods.Event;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static spec.mods.Event.CHANGE_PC;

public class Cpu extends Registry {

    private Consumer<Cpu> onTick;
    private Supplier<Boolean> onInterrupt;
    private int interrupt; // количество тактов на 1 прерывание
    private int tick;      // количество операций, которые сделал процессор
    private Assembler asm;
    private CpuDebug debug;
    private boolean enabled;

    public Cpu(int ticksPerInterrupt, Data data, Supplier<Boolean> onInterrupt, Consumer<Cpu> onTick) {
        super(data);
        interrupt = ticksPerInterrupt;
        this.onInterrupt = onInterrupt;
        asm = new Assembler();
        this.onTick = onTick;
        debug = new CpuDebug(asm, data, this);
    }

    @Override
    public void reset() {
        super.reset();
        enabled();
        tick = 0;
    }

    /**
     * @return Количество операций, которые сделал cpu.
     */
    public int tick() {
        return tick;
    }

    public void execute() {
        int tacts = 0; // счетчик тактов команд (из него будут извлекаться this.interrupt)

        while (enabled) {
            if (onTick != null) {
                onTick.accept(this);
            }
            tick++;

            if (tacts >= interrupt) {
                while (tacts >= interrupt) {
                    tacts -= interrupt;
                }
                if (!onInterrupt.get()) {
                    break;
                }
            }

            int pc = PC();
            debug.log(pc);
            int bite = data.read8(rPC);
            Command command = asm.find(bite);
            on(CHANGE_PC, pc, command, this);
            if (command != null) {
                command.apply(bite, this);
                // каждая операция уменьшает число тактов на
                // прерывание на свою длительность в тактах
                tacts += command.ticks();
                continue;
            }

            if (bite == 0x76) { // TODO выделить эту команду
                int haltsToInterrupt = (-tacts - 1) / 4 + 1;
                tacts += haltsToInterrupt * 4;
                enabled = false;
                continue;
            }
        }

        enabled();
    }

    public Assembler asm() {
        return asm;
    }

    public CpuDebug debug() {
        return debug;
    }

    public void disabled() {
        enabled = false;
    }

    public void enabled() {
        enabled = true;
    }
}