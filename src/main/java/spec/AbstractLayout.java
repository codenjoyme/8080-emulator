package spec;

import org.apache.commons.lang3.tuple.Pair;

import static spec.Key.*;

public abstract class AbstractLayout {

    public Pair<Integer, Boolean> is(int code, boolean rusLat, char en, int enPt, char cr, int crPt) {
        if (rusLat) {
            if (code == non(en)) return nonH(crPt);
            if (code == cyr(cr)) return nonH(enPt);
            if (code == ctr(en)) return nonH(enPt);
            if (code == ctr(cyr(cr))) return nonH(crPt);
        } else {
            if (code == non(en)) return nonH(enPt);
            if (code == cyr(cr)) return nonH(crPt);
            if (code == ctr(en)) return nonH(crPt);
            if (code == ctr(cyr(cr))) return nonH(enPt);
        }
        return null;
    }

    public static Pair<Integer, Boolean> nonH(int point) {
        return Pair.of(point, false);
    }

    public static Pair<Integer, Boolean> shfH(int point) {
        return Pair.of(point, true);
    }

    public static int non(int code) {
        return code;
    }

    public static int non(char code) {
        return code;
    }

    public static int cyr(char code) {
        return cyr((int) code);
    }

    public static int cyr(int code) {
        return code | CYRILLIC_MASK;
    }

    public static int alt(char code) {
        return alt((int) code);
    }

    public static int alt(int code) {
        return code | ALT_MASK;
    }

    public static int ctr(char code) {
        return ctr((int) code);
    }

    public static int ctr(int code) {
        return code | CTRL_MASK;
    }

    public static int shf(char code) {
        return shf((int) code);
    }

    public static int shf(int code) {
        return code | SHIFT_MASK;
    }
}
