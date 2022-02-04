package spec.assembler;

import spec.Data;
import spec.Range;
import spec.mods.WhereIsData;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static spec.WordMath.*;
import static spec.mods.WhereIsData.Type.*;
import static spec.mods.WhereIsData.markCommand;

public class DizAssembler {

    private Assembler asm;
    private Data data;
    private Range range;
    private WhereIsData.Info[] infoData;

    public DizAssembler(Data data) {
        this.data = data;
        this.asm = new Assembler();
    }

    public void clarifyInfo() {
        List<Integer> processed = new LinkedList<>();
        Queue<Integer> toProcess = new LinkedList<>();
        toProcess.add(range.begin());
        while (!toProcess.isEmpty()) {
            int addr = toProcess.remove();
            if (processed.contains(addr)) {
                continue;
            }
            processed.add(addr);
            do {
                Command command = asm.find(data.read8(addr));
                markCommand(infoData, addr, command, true);
                if (command.isJump() || command.isCall()) {
                    int nextAddr = data.read16(addr + 1);
                    toProcess.add(nextAddr);
                }
                if (command.name().startsWith("RET")
                        || command.name().startsWith("JMP")
                        || command.name().startsWith("PCHL")) {
                    break;
                }

                addr += command.size();
            } while (true);
        }
    }

    public String program(Range range, WhereIsData.Info[] inputInfo, boolean canonicalData) {
        this.range = range;
        this.infoData = clone(inputInfo);

        clarifyInfo();
        dizAssembly(range, canonicalData);
        return buildProgram(range);
    }

    private void dizAssembly(Range range, boolean canonicalData) {
        Assembler asm = new Assembler();
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            WhereIsData.Info info = infoData[addr];

            // если у нас данные
            if (info.type == DATA) {
                StringBuilder result = new StringBuilder();
                result.append("DB ");
                if (canonicalData) {
                    result.append('0');
                }
                result.append(hex8(data.read8(addr)));
                if (canonicalData) {
                    result.append('h');
                }
                info.asm(result.toString());
                continue;
            }

            // если у нас команды
            if (info.type == COMMAND) {
                List<Integer> bites = new LinkedList<>();
                for (int i = 0; i < info.command.size(); i++) {
                    bites.add(data.read8(addr + i));
                }
                info.asm(asm.dizAssembly(bites, canonicalData));
            }
        }
    }

    private String buildProgram(Range range) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        boolean first = true;
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            WhereIsData.Info info = infoData[addr];

            // если у нас данные, выстраиваем их по 10 в ряд
            if (info.type == DATA) {
                if (count == 10) {
                    count = 0;
                    first = true;
                    result.append('\n');
                }
                if (first) {
                    first = false;
                    result.append("DB ");
                } else {
                    result.append(", ");
                }
                result.append(info.asm.replace("DB ", ""));
                count++;
                continue;
            }
            if (count != 0) {
                result.append('\n');
                count = 0;
                first = true;
            }

            // если у нас команды
            if (info.type == COMMAND) {
                result.append(info.asm).append('\n');
            }
        }
        result.append("\nEND");
        return result.toString();
    }

    private WhereIsData.Info[] clone(WhereIsData.Info[] info) {
        WhereIsData.Info[] result = new WhereIsData.Info[info.length];
        for (int addr = 0; addr < info.length; addr++) {
            result[addr] = new WhereIsData.Info(info[addr]);
        }
        return result;
    }
}
