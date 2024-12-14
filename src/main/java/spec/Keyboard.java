package spec;

import spec.math.Bites;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static spec.Constants.*;
import static spec.Key.*;
import static spec.KeyCode.*;
import static spec.math.WordMath.bitToString;
import static spec.math.WordMath.isSet;

public class Keyboard implements StateProvider {

    public static final int SNAPSHOT_KEYBOARD_SIZE = 13;

    private BiConsumer<Key, Integer> keyLogger;

    // массив матрицы клавиш "Специалиста" (true - нажата, false - отпущена)
    // 12 x 6 + Shift + Reset
    private boolean[][] keyStatus = new boolean[12][6];

    private Map<Integer, Integer> keys;

    // зажата ли клавиша
    private boolean shift;
    private boolean alt;
    private boolean ctrl;

    public Keyboard(BiConsumer<Key, Integer> keyLogger) {
        this.keys = new HashMap<>();
        this.keyLogger = keyLogger;
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

    public void putCtrlS(char code, int pt) {
        keys.put((int) code | CTRL_MASK | SHIFT_MASK, pt);
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

    @Override
    public int stateSize() {
        return SNAPSHOT_KEYBOARD_SIZE;
    }

    @Override
    public void state(Bites bites) {
        validateState("Keyboard", bites);

        int lastIndex = bites.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            for (int j = 0; j < 6; j++) {
                keyStatus[i][j] = isSet(bites.get(i), b0000_0001 << j);
            }
        }
        bitesState(bites.get(lastIndex));
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());
        int lastIndex = bites.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            int bite = 0;
            for (int j = 0; j < 6; j++) {
                bite |= keyStatus[i][j] ? (b0000_0001 << j) : 0;
            }
            bites.set(i, bite);
        }
        bites.set(lastIndex, bitesState());
        return bites;
    }

    // переменные-заготовки для полу-рядов матрицы клавиатуры .
    // значение в них устанавливается при нажатии-отпускании клавиши.
    // в порт клавиатуры - xxfeh они записываются во время его опроса
    // согласно "0", выбирающему конкретный полуряд: public int inb( int port )

    public synchronized void reset() {
        shift = false;
        alt = false;
        ctrl = false;

        for (int i = 0; i < 12; i++) {  // все кнопки не нажаты
            for (int j = 0; j < 6; j++) {
                keyStatus[i][j] = false;
            }
        }
    }

    private void pressKey(Key key) {
        Integer point = key(key.joint());
        if (point == null) {
            return;
        }

        int x = (point & 0xF0) >> 4;
        int y = point & 0x0F;
        if (keyStatus[x][y] == key.pressed()) {
            return;
        }

        logKey(key, point);
        keyStatus[x][y] = key.pressed();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("       | | | | | | | | | | |1|1|\n")
                .append("       |0|1|2|3|4|5|6|7|8|9|0|1|\n");

        for (int i = 0; i < keyStatus[0].length; i++) {
            sb.append("    |").append(i).append("|");
            for (int j = 0; j < keyStatus.length; j++) {
                if (keyStatus[j][i]) {
                    sb.append(" +");
                } else {
                    sb.append(" -");
                }
            }
            sb.append("\n");
        }

        return String.format(
                "shift : %s\n" +
                "alt   : %s\n" +
                "ctrl  : %s\n" +
                "\n" +
                "keyStatus:\n" +
                "%s",
                bitToString(shift),
                bitToString(alt),
                bitToString(ctrl),
                sb);
    }

    public synchronized void processKey(Key key) {
        if (key.pause()) {
            logKey(key, 0xFF);
            return;
        }
        if (key.mods()) {
            if (key.code() == SHIFT && shift != key.pressed()) {
                shift = key.pressed();
                logKey(key, 0xFA);
            }
            if (key.code() == CTRL && ctrl != key.pressed()) {
                ctrl = key.pressed();
                logKey(key, 0xFB);
            }
            if (key.code() == ALT && alt != key.pressed()) {
                alt = key.pressed();
                logKey(key, 0xFC);
            }
            return;
        }

        if (key.pressed()) {
            // если кнопка нажата, то мы изменяем оригинальную с учетом модификатора
            // и кликаем кнопку (возможно уже иную) на виртуальной машине
            pressKey(key);
        } else {
            // если кнопка отпущена, то нам надо отжать потенциально сразу все
            // возможные зажатые кнопки (с модификаторами или без)
            for (Key key2 : key.allKeysWithMods()) {
                pressKey(key2);
            }
        }

    }

    private void logKey(Key key, int point) {
        if (keyLogger == null) return;

        keyLogger.accept(key, point);
    }

    public void bitesState(int bite) {
        // 0b__shift_alt_ctrl_0__0_0_0_0
        shift(bite);
        alt(bite);
        ctrl(bite);
    }

    public int bitesState() {
        // 0b__shift_alt_ctrl_0__0_0_0_0
        return (shift ? b1000_0000 : 0)
                | (alt ? b0100_0000 : 0)
                | (ctrl ? b0010_0000 : 0);
    }

    public boolean keyStatus(int j, int i) {
        return keyStatus[j][i];
    }

    public boolean shift() {
        return shift;
    }


    private void shift(int bite) {
        shift = isSet(bite, b1000_0000);
    }

    private void alt(int bite) {
        alt = isSet(bite, b0100_0000);
    }

    private void ctrl(int bite) {
        ctrl = isSet(bite, b0010_0000);
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
    public static final int H_SYMB1 = 0x21;
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
    public static final int H_X = 0x51;
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
