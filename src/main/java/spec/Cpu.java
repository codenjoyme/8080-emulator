package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;
import spec.math.Bites;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static spec.math.WordMath.*;
import static spec.mods.Event.CHANGE_PC;

public class Cpu extends Registry implements StateProvider {

    public static final int SNAPSHOT_CPU_STATE_SIZE = 30;

    private Consumer<Cpu> onTick;
    private Supplier<Boolean> onInterrupt;

    protected int interrupt; // количество тактов на 1 прерывание
    protected int tick;      // количество операций, которые сделал процессор
    protected int tact;

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
        enable();
        tick = 0;
    }

    /**
     * @return Количество операций, которые сделал cpu.
     */
    public int tick() {
        return tick;
    }

    public int tact() {
        return tact;
    }

    public void tact(int value) {
        tact = value;
    }

    public void execute() {
        tact = 0; // счетчик тактов команд (из него будут извлекаться this.interrupt)

        while (enabled) {
            if (onTick != null) {
                onTick.accept(this);
            }
            tick++;

            if (tact >= interrupt) {
                while (tact >= interrupt) {
                    tact -= interrupt;
                }
                if (!onInterrupt.get()) {
                    break;
                }
            }

            debug.log();
            int bite = data.read8(PC());
            Command command = asm.find(bite);
            on(CHANGE_PC, command, this);
            if (command == null) {
                throw new IllegalArgumentException("Unknown command: " + hex8(bite));
            }
            rPC.inc16();
            command.apply(bite, this);
            // каждая операция уменьшает число тактов на
            // прерывание на свою длительность в тактах
            tact += command.ticks(bite);
        }

        enable();
    }

    public Assembler asm() {
        return asm;
    }

    public CpuDebug debug() {
        return debug;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    public Bites commandBites() {
        int addr = PC();
        int commandBite = data.read8(addr);
        Command command = asm.find(commandBite);
        if (command.size() == 1) {
            return Bites.of(commandBite);
        }
        int lo = data.read8(addr + 1);
        if (command.size() == 2) {
            return Bites.of(commandBite, lo);
        }
        int hi = data.read8(addr + 2);
        return Bites.of(commandBite, lo, hi);
    }

    @Override
    public int stateSize() {
        return SNAPSHOT_CPU_STATE_SIZE;
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());

        bites.set(0, lo(PC()));
        bites.set(1, hi(PC()));
        bites.set(2, lo(SP()));
        bites.set(3, hi(SP()));
        bites.set(4, F());
        bites.set(5, A());
        bites.set(6, C());
        bites.set(7, B());
        bites.set(8, E());
        bites.set(9, D());
        bites.set(10, L());
        bites.set(11, H());

        bites.set(new Range(12, 15), splitBites(interrupt, 4));
        bites.set(new Range(16, 19), splitBites(tick, 4));
        bites.set(new Range(20, 23), splitBites(tact, 4));

        return bites;
    }

    @Override
    public void state(Bites bites) {
        if (bites.size() != stateSize()) {
            throw new IllegalArgumentException("Invalid CPU state size: " + bites.size());
        }

        PC(merge(bites.get(1), bites.get(0)));
        SP(merge(bites.get(3), bites.get(2)));
        F(bites.get(4));
        A(bites.get(5));
        C(bites.get(6));
        B(bites.get(7));
        E(bites.get(8));
        D(bites.get(9));
        L(bites.get(10));
        H(bites.get(11));

        interrupt = (int) joinBites(bites, new Range(12, 15));
        tick = (int) joinBites(bites, new Range(16, 19));
        tact = (int) joinBites(bites, new Range(20, 23));
    }

    public String toStringDetails(boolean withTicks) {
        if (withTicks) {
            return String.format(
                    "tick:      %s\n" +
                    "tact:      %s\n" +
                    "interrupt: %s\n" +
                    "%s",
                    withTicks ? tick : "",
                    withTicks ? tact : "",
                    interrupt,
                    super.toStringDetails());
        } else {
            return super.toStringDetails();
        }

    }
}