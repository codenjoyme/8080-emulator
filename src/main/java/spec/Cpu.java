package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.assembler.Assembler;
import spec.assembler.Command;
import spec.math.Bites;
import spec.state.JsonState;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static spec.math.WordMath.*;
import static spec.mods.Event.CHANGE_PC;

public class Cpu extends Registry implements JsonState {

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
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("PC", hex16(PC()));
        result.addProperty("SP", hex16(SP()));
        result.addProperty("AF", hex16(AF()));
        result.addProperty("BC", hex16(BC()));
        result.addProperty("DE", hex16(DE()));
        result.addProperty("HL", hex16(HL()));

        result.addProperty("interrupt", interrupt);
        result.addProperty("tick", tick);
        result.addProperty("tact", tact);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        PC(hex16(json.get("PC").getAsString()));
        SP(hex16(json.get("SP").getAsString()));
        AF(hex16(json.get("AF").getAsString()));
        BC(hex16(json.get("BC").getAsString()));
        DE(hex16(json.get("DE").getAsString()));
        HL(hex16(json.get("HL").getAsString()));

        interrupt = json.get("interrupt").getAsInt();
        tick = json.get("tick").getAsInt();
        tact = json.get("tact").getAsInt();
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