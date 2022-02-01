package spec.mods;

import spec.Range;

import static spec.WordMath.hex16;
import static spec.WordMath.hex8;

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
        result.append("     00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F\n");
        for (int y = range.begin() / 0x10; y <= range.end() / 0x10; y++) {
            int dy = y * 0x10;
            result.append(hex16(dy)).append(" ");
            for (int x = 0; x < 0x10; x++) {
                if (range.includes(x + dy)) {
                    int c = access[dy + x];
                    String count = (c == 0) ? ".." : hex8(c);
                    result.append(count).append(" ");
                } else {
                    result.append("   ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
