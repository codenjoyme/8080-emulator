package spec.mods;

import spec.Cpu;
import spec.Data;
import spec.Logger;
import spec.Range;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.math.WordMath.*;
import static spec.mods.Event.*;
import static spec.mods.WhereIsData.Type.*;

public class WhereIsData implements CpuMod {

    public static boolean PRINT_RW = false;

    private Cpu cpu;
    private Info[] infos;
    private Range range;
    private int pc;

    public WhereIsData(Range range) {
        init();
        this.range = range;
    }

    @Override
    public List<Event> supports() {
        return Arrays.asList(CHANGE_PC, WRITE_MEM, READ_MEM);
    }

    @Override
    public void on(Event event, Object... params) {
        if (event == CHANGE_PC) {
            if (cpu == null) {
                cpu = (Cpu) params[1];
            }
            pc = cpu.PC();

            if (!range.includes(pc)) return;
            Command command = (Command) params[0];
            markCommand(infos, pc, cpu.data(), command, false);
        }

        if (!PRINT_RW) return;

        if (event == WRITE_MEM) {
            if (!range.includes(pc)) return;

            int addr = (int) params[0];
            int bite = (int) params[1];
            Info info = infos[pc];

            Logger.debug("Written in memory at [%s] byte [%s] by command '%s: %s'",
                    hex16(addr), hex8(bite), hex16(pc), info.asm(false));
        }

        if (event == READ_MEM) {
            if (!range.includes(pc)) return;

            int addr = (int) params[0];
            int bite = (int) params[1];
            Info info = infos[pc];

            Logger.debug("Read from memory at [%s] byte [%s] by command '%s: %s'",
                    hex16(addr), hex8(bite), hex16(pc), info.asm(false));
        }
    }

    public static Info markCommand(Info[] infos, int addr, Data data, Command command, boolean check) {
        for (int i = 0; i < command.size(); i++) {
            Type type = (i == 0) ? COMMAND : COMMAND_DATA;
            Info info = infos[addr + i];
            boolean isMarkAsNotData = info.type != DATA;
            boolean isTriedMarkAsDifferentType = info.type != type;
            boolean isMarkProbableCommandAsCommand = info.type == PROBABLE_COMMAND && type == COMMAND;

            if (check && isMarkAsNotData && isTriedMarkAsDifferentType && !isMarkProbableCommandAsCommand) {
                throw new IllegalArgumentException("Mark error. Tried to mark old command as new: " +
                        info.type.name() + " > " + type.name() + " " + hex16(addr + i));
            }
            info.command(command); // TODO #28 why I cant set here if (info.command == null) info.command(command);
            info.type(type);       // TODO #28 and same here. This will improve performance
            info.increase();
        }
        infos[addr].code(data.read8(addr));
        infos[addr].data(data.read16(wordShift(addr + 1)));
        return infos[addr];
    }

    private void init() {
        infos = new Info[0x10000];
        for (int addr = 0; addr < infos.length; addr++) {
            infos[addr] = new Info(0, Type.DATA, null);
        }
    }

    public Range range() {
        return range;
    }

    public Info[] info() {
        return infos;
    }

    public enum Type {

        PROBABLE_COMMAND,
        COMMAND,
        COMMAND_DATA,
        DATA,
        STACK
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int length = maxLength();

        // заголовок
        result.append("    ");
        for (int x = 0; x < 0x10; x++) {
            result.append(padLeft(hex8(x), length + 1, ' '));
        }
        result.append("\n");

        // память
        for (int y = range.begin() / 0x10; y <= range.end() / 0x10; y++) {

            int dy = y * 0x10;
            result.append(hex16(dy));
            for (int x = 0; x < 0x10; x++) {
                if (range.includes(x + dy)) {
                    Info i = infos[dy + x];
                    String count = (i.type == DATA) ? ".." : hex8(i.access);
                    result.append(padLeft(count, length + 1, ' '));
                } else {
                    result.append(padLeft(" ", length + 1, ' '));
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    private int maxLength() {
        int max = 0;
        for (int i = range.begin(); i <= range.end(); i++) {
            max = Math.max(max, infos[i].access);
        }
        return hex8(max).length();
    }
}
