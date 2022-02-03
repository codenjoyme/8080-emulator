package spec.assembler;

import spec.Data;
import spec.Range;
import spec.mods.WhereIsData;

import java.util.LinkedList;
import java.util.List;

import static spec.WordMath.hex8;
import static spec.mods.WhereIsData.Type.COMMAND;
import static spec.mods.WhereIsData.Type.DATA;

public class DizAssembler {

    private Data data;

    public DizAssembler(Data data) {
        this.data = data;
    }

    public String program(Range range, WhereIsData.Info[] infoData, boolean canonicalData) {
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
}
