package spec;

import java.util.HashMap;
import java.util.Map;

import static spec.Key.*;

public class Keyboard {

    private Map<Integer, Integer> keys;

    public Keyboard() {
        this.keys = new HashMap<>();
    }

    public Integer key(int code) {
        return keys.get(code);
    }

    public void putChar(char en, int enPt, char cyr, int cyrPt) {
        // '1 -> ...'        просто нажатая клавиша в english регистре хостовой машины
        // '... -> 1 ...'    результат нажатия в english регистре виртуальной машины
        // '... -> ... [3]'  результат нажатия в cyrillic регистре виртуальной машины
        // '(4) -> ...'      нажатая клавиша в cyrillic раскладка хостовой машины
        // 'ctrl ... -> ...' нажатая c control клавиша на хостовой машине
        putNorm(en, enPt);   //  1  -> 1 [3]
        putCyrl(cyr, cyrPt); // (4) -> 2 [4]
        putCtrl(en, cyrPt);  // ctrl  1  -> 2 [4]
        putCyCt(cyr, enPt);  // ctrl (4) -> 3 [1]
    }

    public void putNorm(Integer code, int pt) {
        keys.put(code, pt);
    }

    public void putNorm(char code, int pt) {
        keys.put((int) code, pt);
    }

    public void putShft(char code, int pt) {
        keys.put((int) code | SHIFT_MASK, pt);
    }

    public void putAlt_(char code, int pt) {
        keys.put((int) code | ALT_MASK, pt);
    }

    public void putAltS(char code, int pt) {
        keys.put((int) code | ALT_MASK | SHIFT_MASK, pt);
    }

    public void putCtrl(char code, int pt) {
        keys.put((int) code | CTRL_MASK, pt);
    }

    public void putCyCt(char code, int pt) {
        keys.put((int) code | CYRILLIC_MASK | CTRL_MASK, pt);
    }

    public void putCyrl(char code, int pt) {
        keys.put((int) code | CYRILLIC_MASK, pt);
    }

    public void putCySh(char code, int pt) {
        keys.put((int) code | CYRILLIC_MASK | SHIFT_MASK, pt);
    }

    /**
     * Матрица клавиш 12х6. True = замкнуто, False = разомкнуто
     * +========================================================================================+
     * |         C3    C2    C1    C0    A7    A6    A5    A4    A3    A2    A1    A0 <=:ПОРТ:  |
     * +==============================================================================+    |    |
     * |  Y/X>|  B_ |  A_ |  9_ |  8_ |  7_ |  6_ |  5_ |  4_ |  3_ |  2_ |  1_ |  0_ |    |    |
     * +==^===============================================================================^=====+
     * |  _5  |  F  |  F1 |  F2 |  F3 |  F4 |  F5 |  F6 |  F7 |  F8 | [X] | [ ] | [/] |    B5   |
     * |  _4  | + ; | ! 1 | " 2 | # 3 | $ 4 | % 5 | & 6 | ' 7 | ( 8 | ) 9 |   0 | = = |    B4   |
     * |  _3  | J Й | C Ц | U У | K K | E E | N Н | G Г | [ Ш | ] Щ | Z З | H Х | : * |    B3   |
     * |  _2  | F Ф | Y Ы | W В | A A | P П | R Р | O O | L Л | D Д | V Ж | \ Э | . > |    B2   |
     * |  _1  | Q Я | ^ Ч | S С | M M | I И | T T | X Ь | B Б | @ Ю | < , | ? / |  ЗБ |    B1   |
     * |  _0  | Р/Л |HOME |  Up |Down | ESC | TAB | SPС |  <= |  ПВ |  => |  ПС |  ВК |    B0   |
     * +========================================================================================+
     **/

}
