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

    private int c = 1;

    public void clarifyInfo() {
        Queue<Integer> toProcess = new LinkedList<>();
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            WhereIsData.Info info = infoData[addr];
            if (info.type == COMMAND
                    && (info.command.isJump()
                        || info.command.isCall()))
            {
                int nextAddr = data.read16(addr + 1);
                if (processingNeeded(nextAddr)) {
                    System.out.println(hex16(addr) + " " + c + "> " + hex16(nextAddr));
                    toProcess.add(nextAddr);
                }
            }
        }
        c++;
        toProcess = process(toProcess);
        c++;
        toProcess = process(toProcess);
        c++;
        toProcess = process(toProcess);
        c++;
        toProcess = process(toProcess);
    }

    private Queue<Integer> process(Queue<Integer> toProcess) {
        Queue<Integer> result = new LinkedList<>();
        while (!toProcess.isEmpty()) {
            int addr = toProcess.remove();
            if (!processingNeeded(addr)) {
                continue;
            }
            do {
                Command command = asm.find(data.read8(addr));
                markCommand(infoData, addr, command, true);
                if (command.isJump() || command.isCall()) {
                    int nextAddr = data.read16(addr + 1);
                    if (processingNeeded(nextAddr)) {
                        System.out.println(hex16(addr) + " " + c + "> " + hex16(nextAddr));
                        result.add(nextAddr);
                    }
                }
                if (command.name().startsWith("RET")
                    || command.name().startsWith("JMP")
                    || command.name().startsWith("PCHL"))
                {
                    break;
                }

                addr += command.size();
            } while (true);
        }
        return result;
    }

    private boolean processingNeeded(int nextAddr) {
        return range.includes(nextAddr) && infoData[nextAddr].type == DATA;
    }

    public String program(Range range, WhereIsData.Info[] inputInfo, boolean canonicalData) {
        this.range = range;
        this.infoData = clone(inputInfo);

        clarifyInfo();

        Assembler asm = new Assembler();
        StringBuilder result = new StringBuilder();
        int count = 0;
        boolean first = true;
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            WhereIsData.Info info = infoData[addr];

            // если у нас данные
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
                if (canonicalData) {
                    result.append('0');
                }
                result.append(hex8(data.read8(addr)));
                if (canonicalData) {
                    result.append('h');
                }
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
                List<Integer> bites = new LinkedList<>();
                for (int i = 0; i < info.command.size(); i++) {
                    bites.add(data.read8(addr + i));
                }
                result.append(asm.dizAssembly(bites, canonicalData)).append('\n');
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
