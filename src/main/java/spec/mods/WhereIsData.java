package spec.mods;

import spec.Cpu;
import spec.Data;
import spec.Range;
import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.LinkedList;
import java.util.List;

import static spec.WordMath.*;
import static spec.mods.Event.CHANGE_PC;
import static spec.mods.WhereIsData.Type.*;

public class WhereIsData extends When {

    private Cpu cpu;
    private Info[] info;
    private Range range;

    public WhereIsData(Range range) {
        super(null);
        init();
        this.range = range;
        trigger = (event, params) -> {
            if (event == CHANGE_PC) {
                int pc = (int)params[0];
                Command command = (Command)params[1];
                if (cpu == null) {
                    cpu = (Cpu)params[2];
                }
                for (int i = 0; i < command.size(); i++) {
                    Type type = (i == 0) ? COMMAND : COMMAND_DATA;
                    info[pc + i].command(command).type(type).increase();
                }
            }
        };
    }

    private void init() {
         info = new Info[0x10000];
        for (int addr = 0; addr < info.length; addr++) {
            info[addr] = new Info(0, Type.DATA, null);
        }
    }

    public Range range() {
        return range;
    }

    public Info[] info() {
        return info;
    }

    public enum Type {

        COMMAND,
        COMMAND_DATA,
        DATA,
        STACK
    }

    public static class Info {

        public int access;
        public Type type;
        public Command command;

        public Info(int access, Type type, Command command) {
            this.access = access;
            this.type = type;
            this.command = command;
        }

        public Info increase() {
            access++;
            return this;
        }

        public Info type(Type type) {
            this.type = type;
            return this;
        }

        public Info command(Command command) {
            this.command = command;
            return this;
        }
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
                    Info i = info[dy + x];
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
            max = Math.max(max, info[i].access);
        }
        return hex8(max).length();
    }
}
