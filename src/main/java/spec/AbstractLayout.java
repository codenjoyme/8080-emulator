package spec;

import org.apache.commons.lang3.tuple.Pair;

import static spec.Key.*;

public abstract class AbstractLayout {

    public Pair<Integer, Boolean> is(int code, boolean rusLat, char en, int enPt, char cr, int crPt) {
        // '1        ->  ...'      просто нажатая клавиша в english регистре хостовой машины
        // '...      ->  1 ...'    результат нажатия в english регистре виртуальной машины
        // '...      ->  ... [3]'  результат нажатия в cyrillic регистре виртуальной машины
        // '(4)      ->  ...'      нажатая клавиша в cyrillic раскладка хостовой машины
        // 'ctrl ... ->  ...'      нажатая c control клавиша на хостовой машине
        if (code == non(en)) return nonH(enPt);     //  1       ->  1 [3]
        if (code == cyr(cr)) return nonH(crPt);     // (4)      ->  2 [4]
        if (code == ctr(en)) return nonH(crPt);     // ctrl  1  ->  2 [4]
        if (code == ctr(cyr(cr))) return nonH(enPt); // ctrl (4) ->  3 [1]
        return null;
    }

    public Pair<Integer, Boolean> nonH(int point) {
        return Pair.of(point, false);
    }

    public Pair<Integer, Boolean> shfH(int point) {
        return Pair.of(point, true);
    }

    public int non(int code) {
        return code;
    }

    public int non(char code) {
        return code;
    }

    public int cyr(char code) {
        return cyr((int) code);
    }

    public int cyr(int code) {
        return code | CYRILLIC_MASK;
    }

    public int alt(char code) {
        return alt((int) code);
    }

    public int alt(int code) {
        return code | ALT_MASK;
    }

    public int ctr(char code) {
        return ctr((int) code);
    }

    public int ctr(int code) {
        return code | CTRL_MASK;
    }

    public int shf(char code) {
        return shf((int) code);
    }

    public int shf(int code) {
        return code | SHIFT_MASK;
    }
}
