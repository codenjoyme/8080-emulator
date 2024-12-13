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

    public void putNorm(int code, int pt) {
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

    // Этот воркераунд метод используется, когда чтобы достать до символа надо его вместе
    // с шифтом зажать на клавиатуре внутри виртуальной машины, но мы жмем эту клавишу
    // на хостовой машине без шифта TODO подумать может можно поправить как-то
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

    public static final int H_ВК = 0x00;     // ввод каретки
    public static final int H_ЗБ = 0x01;     // забой
    public static final int H_SYMB7 = 0x02;
    public static final int H_SYMB2 = 0x03;
    public static final int H_SYMB9 = 0x04;
    public static final int H_F11 = 0x05;

    public static final int H_ПС = 0x10;     // перевод строки
    public static final int H_SYMB6 = 0x11;
    public static final int H_SYMB5 = 0x12;
    public static final int H_H = 0x13;
    public static final int H_SYMB11 = 0x14;
    public static final int H_F10 = 0x15;

    public static final int H_RIGHT = 0x20;
    public static final int HH_SYMB6 = 0x21;
    public static final int H_V = 0x22;
    public static final int H_Z = 0x23;
    public static final int H_9 = 0x24;
    public static final int H_F9 = 0x25;

    public static final int H_ПВ = 0x30;     // повтор
    public static final int H_SYMB8 = 0x31;
    public static final int H_D = 0x32;
    public static final int H_LEFT_SQUARE_BRACKET = 0x33;
    public static final int H_8 = 0x34;
    public static final int H_F8 = 0x35;

    public static final int H_LEFT = 0x40;
    public static final int H_B = 0x41;
    public static final int H_L = 0x42;
    public static final int H_RIGHT_SQUARE_BRACKET = 0x43;
    public static final int H_7 = 0x44;
    public static final int H_F7 = 0x45;

    public static final int H_SPACE = 0x50;
    public static final int H_SYMB1 = 0x51;
    public static final int H_O = 0x52;
    public static final int H_G = 0x53;
    public static final int H_6 = 0x54;
    public static final int H_F6 = 0x55;

    public static final int H_TAB = 0x60;
    public static final int H_T = 0x61;
    public static final int H_R = 0x62;
    public static final int H_N = 0x63;
    public static final int H_5 = 0x64;
    public static final int H_F5 = 0x65;

    public static final int H_ESC = 0x70;
    public static final int H_I = 0x71;
    public static final int H_P = 0x72;
    public static final int H_E = 0x73;
    public static final int H_4 = 0x74;
    public static final int H_F4 = 0x75;

    public static final int H_DOWN = 0x80;
    public static final int H_M = 0x81;
    public static final int H_A = 0x82;
    public static final int H_K = 0x83;
    public static final int H_3 = 0x84;
    public static final int H_F3 = 0x85;

    public static final int H_UP = 0x90;
    public static final int H_S = 0x91;
    public static final int H_W = 0x92;
    public static final int H_U = 0x93;
    public static final int H_2 = 0x94;
    public static final int H_F2 = 0x95;

    public static final int H_HOME = 0xA0;
    public static final int H_SYMB4 = 0xA1;
    public static final int H_Y = 0xA2;
    public static final int H_C = 0xA3;
    public static final int H_1 = 0xA4;
    public static final int H_F1 = 0xA5;

    public static final int H_РУС_ЛАТ = 0xB0;  // переключение рус/лат клавиатуры
    public static final int H_Q = 0xB1;
    public static final int H_F = 0xB2;
    public static final int H_J = 0xB3;
    public static final int H_SYMB3 = 0xB4;
    public static final int H_F12 = 0xB5;

}
