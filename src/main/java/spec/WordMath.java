package spec;

public class WordMath {

    public static final int WORD = 0xFFFF;
    public static final int HI_BYTE = 0xFF00;
    public static final int LO_BYTE = 0x00FF;

    public static int LO(int word) {
        return word & LO_BYTE;
    }

    public static int HI(int word) {
        return (word >> 8) & LO_BYTE;
    }

    public static int HI_MERGE(int word, int bite) {
        return (word & HI_BYTE) | bite;
    }

    public static int MERGE(int bite1, int bite2) {
        return (bite1 << 8) | bite2;
    }

    public static int LO_MERGE(int bite, int word) {
        return MERGE(bite, LO(word));
    }

    public static int WORD(int word) {
        return word & WORD;
    }

    public static int inc(int word) {
        return WORD(++word);
    }

}
