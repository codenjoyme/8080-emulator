package spec.assembler;

public class Parity {

    public static final boolean[] parity = new boolean[256];

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
