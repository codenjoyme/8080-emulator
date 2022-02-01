package spec.mods;

import spec.Cpu;
import spec.Range;

import static spec.WordMath.*;

public class WhereIsData extends When {

    private int[] access = new int[0x10000];
    private Range range;

    public WhereIsData(Range range) {
        super(null);
        this.range = range;
        trigger = (event, params) -> {
            if (event.equals("pc")) {
                Cpu cpu = (Cpu)(params[0]);
                access[cpu.PC()]++;
            }
        };
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

        // данные
        for (int y = range.begin() / 0x10; y <= range.end() / 0x10; y++) {

            int dy = y * 0x10;
            result.append(hex16(dy));
            for (int x = 0; x < 0x10; x++) {
                if (range.includes(x + dy)) {
                    int c = access[dy + x];
                    String count = (c == 0) ? ".." : hex8(c);
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
            max = Math.max(max, access[i]);
        }
        return hex8(max).length();
    }
}
