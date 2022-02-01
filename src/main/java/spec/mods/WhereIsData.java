package spec.mods;

import spec.Range;

import static spec.WordMath.*;

public class WhereIsData extends When {

    private int[] access = new int[0x10000];
    private Range range;

    public WhereIsData(Range range) {
        super(null);
        this.range = range;
        trigger = (event, cpu) -> {
            if (event.equals("PC")) {
                access[cpu.PC()]++;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int length = maxLength();
        result.append("     00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F\n");
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
