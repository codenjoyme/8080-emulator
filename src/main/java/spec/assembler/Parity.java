package spec.assembler;

public class Parity {

    public static final boolean[] parity = new boolean[256];
    public static final boolean[] half_carry_table =
            { false, false, true, false, true, false, true, true };
    public static final boolean[] sub_half_carry_table =
            { false, true, true, true, false, false, false, true };

    static {
        for (int i = 0; i < 256; i++) {
            boolean p = true;
            for (int j = 0; j < 8; j++) {
                if ((i & (1 << j)) != 0) {
                    p = !p;
                }
            }
            parity[i] = p;
        }
    }
}
