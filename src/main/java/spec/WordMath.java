package spec;

import java.util.LinkedList;
import java.util.List;

public class WordMath {

    public static final int WORD = 0xFFFF;
    public static final int HI_BYTE = 0xFF00;
    public static final int LO_BYTE = 0x00FF;

    public static int lo(int word) {
        return word & LO_BYTE;
    }

    public static int hi(int word) {
        return (word >> 8) & LO_BYTE;
    }

    public static int merge(int bite1, int bite2) {
        return (bite1 << 8) | bite2;
    }

    public static int word(int word) {
        return word & WORD;
    }

    public static String hex(int bite) {
        return String.format("%02x", bite);
    }

    public static List<Integer> hex(String bites) {
        return new LinkedList<Integer>(){{
            for (int i = 0; i < bites.length() / 2; i++) {
                add(Integer.parseInt(bites.substring(i * 2, (i + 1) * 2), 16));
            }
        }};
    }
}