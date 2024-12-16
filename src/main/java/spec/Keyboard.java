package spec;

import org.apache.commons.lang3.tuple.Pair;
import spec.math.Bites;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static spec.AbstractLayout.nonH;
import static spec.AbstractLayout.shfH;
import static spec.Constants.*;
import static spec.KeyCode.*;
import static spec.math.WordMath.bitToString;
import static spec.math.WordMath.isSet;

public class Keyboard implements StateProvider {

    public static final int SNAPSHOT_KEYBOARD_SIZE = 13;

    // 0b__shift_alt_ctrl_shiftEmu__cyrLat_0_0_0
    public static final int SHIFT_BIT = b1000_0000;
    public static final int ALT_BIT = b0100_0000;
    public static final int CTRL_BIT = b0010_0000;
    public static final int SHIFT_EMU_BIT = b0001_0000;
    public static final int CYL_LAT_BIT = b0000_1000;

    private BiConsumer<Key, Integer> keyLogger;
    private Layout layout;

    // массив матрицы клавиш "Специалиста" (true - нажата, false - отпущена)
    // 12 x 6 + Shift + Reset
    private boolean[][] keyStatus = new boolean[12][6];

    // зажата ли клавиша
    private boolean shift;
    private boolean alt;
    private boolean ctrl;

    // захата ли клавиша на эмулируемой клавиатуре
    private boolean shiftEmu;

    // переключение кириллица/латиница клавиатуры клавшией РУС/ЛАТ
    private boolean cyrLat;

    /**
     * @see #shift() - так IOPorts узнает о том, что нажат шифт на эмулируемой машине
     * @see #tick() - тут обрабатываются нажатые клавиши с задержкой после нажатия и обработки shift с помощью IOPorts
     * @see #pressKey(Key) - тут обрабатываются нажатые клавиши сразу Как приходят из хостовой машины
     */
    private List<Pair<Integer, Key>> points = new LinkedList<>();

    public Keyboard(BiConsumer<Key, Integer> keyLogger, Layout layout) {
        this.layout = layout;
        this.keyLogger = keyLogger;
    }

    public Pair<Integer, Boolean> key(int code) {
        return layout.get(code, cyrLat);
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

    public synchronized void reset(boolean total) {
        shift = false;
        alt = false;
        ctrl = false;
        if (total) {
            cyrLat = false;
        }

        for (int i = 0; i < 12; i++) {  // все кнопки не нажаты
            for (int j = 0; j < 6; j++) {
                keyStatus[i][j] = false;
            }
        }
    }

    private void pressKey(Key key) {
        if (key.capsLock()) {
            cyrLat = !cyrLat;
        }

        Pair<Integer, Boolean> value = key(key.joint());
        if (value == null) {
            return;
        }
        int point = value.getLeft();
        if (key.pressed()) {
            shiftEmu = value.getRight();
        } else {
            if (value.getRight()) {
                shiftEmu = false;
            }
        }

        logKey(key, point);

        /**
         * @see #shift() - так IOPorts узнает о том, что нажат шифт на эмулируемой машине
         * @see #tick() - тут обрабатываются нажатые клавиши с задержкой после нажатия и обработки shift с помощью IOPorts
         * @see #pressKey(Key) - тут обрабатываются нажатые клавиши сразу Как приходят из хостовой машины
         */
        points.add(Pair.of(point, key));
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
                "shiftEmu : %s\n" +
                "cyrLat : %s\n" +
                "\n" +
                "keyStatus:\n" +
                "%s",
                bitToString(shift),
                bitToString(alt),
                bitToString(ctrl),
                bitToString(shiftEmu),
                bitToString(cyrLat),
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
        // 0b__shift_alt_ctrl_shiftEmu__cyrLat_0_0_0
        setShift(bite);
        setAlt(bite);
        setCtrl(bite);
        setShiftEmu(bite);
        setRusLat(bite);
    }

    public int bitesState() {
        // 0b__shift_alt_ctrl_shiftEmu__cyrLat_0_0_0
        return (shift ? SHIFT_BIT : 0)
                | (alt ? ALT_BIT : 0)
                | (ctrl ? CTRL_BIT : 0)
                | (shiftEmu ? SHIFT_EMU_BIT : 0)
                | (cyrLat ? CYL_LAT_BIT : 0);
    }

    public boolean keyStatus(int j, int i) {
        return keyStatus[j][i];
    }

    /**
     * Этот флаг опрашивается IOPorts в свое какое-то известное программе время.
     * Так как мы эмулируем поведение шифта на эмулируемой машине и он не зависит
     * от нажатия шифта на хостовой машине, то мы должны его сохранить и передать
     * в нужный момент. Вся магия в том, что вначале должен отработать этот метод,
     * а уже потом с течением времени (когда процессор интераптентся) должен
     *  отработать метод tick() и обработать нажатые клавиши.
     */
    /**
     * @see #shift() - так IOPorts узнает о том, что нажат шифт на эмулируемой машине
     * @see #tick() - тут обрабатываются нажатые клавиши с задержкой после нажатия и обработки shift с помощью IOPorts
     * @see #pressKey(Key) - тут обрабатываются нажатые клавиши сразу Как приходят из хостовой машины
     */
    public boolean shift() {
        return shiftEmu;
    }

    private void setShift(int bite) {
        shift = isSet(bite, SHIFT_BIT);
    }

    private void setAlt(int bite) {
        alt = isSet(bite, ALT_BIT);
    }

    private void setCtrl(int bite) {
        ctrl = isSet(bite, CTRL_BIT);
    }

    private void setShiftEmu(int bite) {
        shiftEmu = isSet(bite, SHIFT_EMU_BIT);
    }

    private void setRusLat(int bite) {
        cyrLat = isSet(bite, CYL_LAT_BIT);
    }

    /**
     * @see #shift() - так IOPorts узнает о том, что нажат шифт на эмулируемой машине
     * @see #tick() - тут обрабатываются нажатые клавиши с задержкой после нажатия и обработки shift с помощью IOPorts
     * @see #pressKey(Key) - тут обрабатываются нажатые клавиши сразу Как приходят из хостовой машины
     */
    public void tick() {
        if (points.isEmpty()) {
            return;
        }

        while (!points.isEmpty()) {
            Pair<Integer, Key> data = points.remove(0);
            Key key = data.getRight();
            int point = data.getLeft();

            int x = (point & 0xF0) >> 4;
            int y = point & 0x0F;
            if (keyStatus[x][y] == key.pressed()) {
                return;
            }

            keyStatus[x][y] = key.pressed();
        }
    }

    /**
     * Матрица клавиш 12х6. True = замкнуто, False = разомкнуто
     * +========================================================================================+
     * |         C3    C2    C1    C0    A7    A6    A5    A4    A3    A2    A1    A0 <=:ПОРТ:  |
     * +==============================================================================+    |    |
     * |  Y/X>|  B_ |  A_ |  9_ |  8_ |  7_ |  6_ |  5_ |  4_ |  3_ |  2_ |  1_ |  0_ |    |    |
     * +==^===============================================================================^=====+
     * |  _5  |  F  |  F1 |  F2 |  F3 |  F4 |  F5 |  F6 |  F7 |  F8 | [X] | [ ] | [/] |    B5   |
     * |  _4  | ;↕+ | 1↕! | 2↕" | 3↕# | 4↕$ | 5↕% | 6↕& | 7↕' | 8↕( | 9↕) | 0↕  | -↕= |    B4   |
     * |  _3  | J Й | C Ц | U У | K K | E E | N Н | G Г | [ Ш | ] Щ | Z З | H Х | :↕* |    B3   |
     * |  _2  | F Ф | Y Ы | W В | A A | P П | R Р | O O | L Л | D Д | V Ж | \ Э | .↕> |    B2   |
     * |  _1  | Q Я | ^ Ч | S С | M M | I И | T T | X Ь | B Б | @ Ю | ,↕< | /↕? |  ЗБ |    B1   |
     * |  _0  | Р/Л |HOME |  Up |Down | ESC | TAB | SPС |  <= |  ПВ |  => |  ПС |  ВК |    B0   |
     * +========================================================================================+
     * Символы разделенные ' ' - переключения РУС/ЛАТ
     * Символы разделенные '↕' - переключения без shift / с shift
     * Рядом с '0' у нас ' ' (пробел)
     * 'Р/Л' - переключение рус/лат клавиатуры
     * 'ВК' - ввод каретки
     * 'ПС' - перевод строки
     * 'ЗБ' - забой
     * 'ПВ' - повтор
     **/

    public static final int H_ВК = 0x00;     // ввод каретки

    public static final int H_ЗБ = 0x01;     // забой

    public static final int H_none_Dot_OR_shift_RightAngleBracket = 0x02; // без шифта '.' а с шифтом '>'
    public static final Pair<Integer, Boolean> HS_DOT = nonH(H_none_Dot_OR_shift_RightAngleBracket); // '.'
    public static final Pair<Integer, Boolean> HS_RIGHT_ANGLE_BRACKET = shfH(H_none_Dot_OR_shift_RightAngleBracket); // '>'

    public static final int H_none_Colon_OR_shift_Asterisk = 0x03; // без шифта ':' а с шифтом '*'
    public static final Pair<Integer, Boolean> HS_COLON = nonH(H_none_Colon_OR_shift_Asterisk);    // ':'
    public static final Pair<Integer, Boolean> HS_ASTERISK = shfH(H_none_Colon_OR_shift_Asterisk); // '*'

    public static final int H_none_Minus_OR_shift_Equals = 0x04; // без шифта '-' а с шифтом '='
    public static final Pair<Integer, Boolean> HS_MINUS = nonH(H_none_Minus_OR_shift_Equals);  // '-'
    public static final Pair<Integer, Boolean> HS_EQUALS = shfH(H_none_Minus_OR_shift_Equals); // '='

    public static final int H_F11 = 0x05;

    public static final int H_ПС = 0x10;                 // перевод строки

    public static final int H_none_Slash_OR_shift_QuestionMark = 0x11; // без шифта '/' а с шифтом '?'
    public static final Pair<Integer, Boolean> HS_SLASH = nonH(H_none_Slash_OR_shift_QuestionMark);         // '/'
    public static final Pair<Integer, Boolean> HS_QUESTION_MARK = shfH(H_none_Slash_OR_shift_QuestionMark); // '?'

    public static final int H_cyr_Э_OR_lat_BackSlash = 0x12; // в кириллице 'Э' а в латинице '\'
    public static final Pair<Integer, Boolean> HS_CYR_Э = nonH(H_cyr_Э_OR_lat_BackSlash);         // 'Э'
    public static final Pair<Integer, Boolean> HS_LAT_BACKSLASH = nonH(H_cyr_Э_OR_lat_BackSlash); // '\'

    public static final int H_cyr_Х_OR_lat_H = 0x13; // в кириллице 'Х' а в латинице 'H'
    public static final Pair<Integer, Boolean> HS_CYR_Х = nonH(H_cyr_Х_OR_lat_H); // 'Х'
    public static final Pair<Integer, Boolean> HS_LAT_H = nonH(H_cyr_Х_OR_lat_H); // 'H'

    public static final int H_none_Zero_OR_shift_Space = 0x14; // без шифта '0' а с шифтом ' '
    public static final Pair<Integer, Boolean> HS_ZERO = nonH(H_none_Zero_OR_shift_Space);  // '0'
    public static final Pair<Integer, Boolean> HS_SPACE = shfH(H_none_Zero_OR_shift_Space); // ' '

    public static final int H_F10 = 0x15;

    public static final int H_RIGHT = 0x20;

    public static final int H_none_Comma_OR_shift_LeftAngleBracket = 0x21; // без шифта ',' а с шифтом '<'
    public static final Pair<Integer, Boolean> HS_COMMA = nonH(H_none_Comma_OR_shift_LeftAngleBracket);              // ','
    public static final Pair<Integer, Boolean> HS_LEFT_ANGLE_BRACKET = shfH(H_none_Comma_OR_shift_LeftAngleBracket); // '<'

    public static final int H_cyr_Ж_OR_lat_V = 0x22; // в кириллице 'Ж' а в латинице 'V'
    public static final Pair<Integer, Boolean> HS_CYR_Ж = nonH(H_cyr_Ж_OR_lat_V); // 'Ж'
    public static final Pair<Integer, Boolean> HS_LAT_V = nonH(H_cyr_Ж_OR_lat_V); // 'V'

    public static final int H_cyr_З_OR_lat_Z = 0x23; // в кириллице 'З' а в латинице 'Z'
    public static final Pair<Integer, Boolean> HS_CYR_З = nonH(H_cyr_З_OR_lat_Z); // 'З'
    public static final Pair<Integer, Boolean> HS_LAT_Z = nonH(H_cyr_З_OR_lat_Z); // 'Z'

    public static final int H_none_9_OR_shift_RightParenthesis = 0x24; // без шифта '9' а с шифтом ')'
    public static final Pair<Integer, Boolean> HS_9 = nonH(H_none_9_OR_shift_RightParenthesis);         // '9'
    public static final Pair<Integer, Boolean> HS_RIGHT_PARENTHESIS = shfH(H_none_9_OR_shift_RightParenthesis); // ')'

    public static final int H_F9 = 0x25;

    public static final int H_ПВ = 0x30;     // повтор

    public static final int H_cyr_Ю_OR_lat_At = 0x31; // в кириллице 'Ю' а в латинице '@'
    public static final Pair<Integer, Boolean> HS_CYR_Ю = nonH(H_cyr_Ю_OR_lat_At);  // 'Ю'
    public static final Pair<Integer, Boolean> HS_LAT_AT = shfH(H_cyr_Ю_OR_lat_At); // '@'

    public static final int H_cyr_Д_OR_lat_D = 0x32; // в кириллице 'Д' а в латинице 'D'
    public static final Pair<Integer, Boolean> HS_CYR_Д = nonH(H_cyr_Д_OR_lat_D); // 'Д'
    public static final Pair<Integer, Boolean> HS_LAT_D = nonH(H_cyr_Д_OR_lat_D); // 'D'

    public static final int H_cyr_Щ_OR_lat_RightSquareBracket = 0x33; // в кириллице 'Щ' а в латинице ']'
    public static final Pair<Integer, Boolean> HS_CYR_Щ = nonH(H_cyr_Щ_OR_lat_RightSquareBracket); // 'Щ'
    public static final Pair<Integer, Boolean> HS_LAT_RIGHT_SQUARE_BRACKET = nonH(H_cyr_Щ_OR_lat_RightSquareBracket); // ']'

    public static final int H_none_8_OR_shift_LeftParenthesis = 0x34; // без шифта '8' а с шифтом '('
    public static final Pair<Integer, Boolean> HS_8 = nonH(H_none_8_OR_shift_LeftParenthesis);         // '8'
    public static final Pair<Integer, Boolean> HS_LEFT_PARENTHESIS = shfH(H_none_8_OR_shift_LeftParenthesis); // '('

    public static final int H_F8 = 0x35;

    public static final int H_LEFT = 0x40;

    public static final int H_cyr_Б_OR_lat_B = 0x41; // в кириллице 'Б' а в латинице 'B'
    public static final Pair<Integer, Boolean> HS_CYR_Б = nonH(H_cyr_Б_OR_lat_B); // 'Б'
    public static final Pair<Integer, Boolean> HS_LAT_B = nonH(H_cyr_Б_OR_lat_B); // 'B'

    public static final int H_cyr_Л_OR_lat_L = 0x42; // в кириллице 'Л' а в латинице 'L'
    public static final Pair<Integer, Boolean> HS_CYR_Л = nonH(H_cyr_Л_OR_lat_L); // 'Л'
    public static final Pair<Integer, Boolean> HS_LAT_L = nonH(H_cyr_Л_OR_lat_L); // 'L'

    public static final int H_cyr_Ш_OR_lat_LeftSquareBracket = 0x43; // в кириллице 'Ш' а в латинице '['
    public static final Pair<Integer, Boolean> HS_CYR_Ш = nonH(H_cyr_Ш_OR_lat_LeftSquareBracket); // 'Ш'
    public static final Pair<Integer, Boolean> HS_LAT_LEFT_SQUARE_BRACKET = nonH(H_cyr_Ш_OR_lat_LeftSquareBracket); // '['

    public static final int H_none_7_OR_shift_Apostrophe = 0x44; // без шифта '7' а с шифтом '''
    public static final Pair<Integer, Boolean> HS_7 = nonH(H_none_7_OR_shift_Apostrophe);         // '7'
    public static final Pair<Integer, Boolean> HS_APOSTROPHE = shfH(H_none_7_OR_shift_Apostrophe); // '''

    public static final int H_F7 = 0x45;

    public static final int H_SPACE = 0x50;

    public static final int H_cyr_Ь_OR_lat_X = 0x51; // в кириллице 'Ь' а в латинице 'X'
    public static final Pair<Integer, Boolean> HS_CYR_Ь = nonH(H_cyr_Ь_OR_lat_X); // 'Ь'
    public static final Pair<Integer, Boolean> HS_LAT_X = nonH(H_cyr_Ь_OR_lat_X); // 'X'

    public static final int H_cyr_О_OR_lat_O = 0x52; // в кириллице 'О' а в латинице 'O'
    public static final Pair<Integer, Boolean> HS_CYR_О = nonH(H_cyr_О_OR_lat_O); // 'О'
    public static final Pair<Integer, Boolean> HS_LAT_O = nonH(H_cyr_О_OR_lat_O); // 'O'

    public static final int H_cyr_Г_OR_lat_G = 0x53; // в кириллице 'Г' а в латинице 'G'
    public static final Pair<Integer, Boolean> HS_CYR_Г = nonH(H_cyr_Г_OR_lat_G); // 'Г'
    public static final Pair<Integer, Boolean> HS_LAT_G = nonH(H_cyr_Г_OR_lat_G); // 'G'

    public static final int H_none_6_OR_shift_Ampersand = 0x54; // без шифта '6' а с шифтом '&'
    public static final Pair<Integer, Boolean> HS_6 = nonH(H_none_6_OR_shift_Ampersand);         // '6'
    public static final Pair<Integer, Boolean> HS_AMPERSAND = shfH(H_none_6_OR_shift_Ampersand); // '&'

    public static final int H_F6 = 0x55;

    public static final int H_TAB = 0x60;

    public static final int H_cyr_Т_OR_lat_T = 0x61; // в кириллице 'Т' а в латинице 'T'
    public static final Pair<Integer, Boolean> HS_CYR_Т = nonH(H_cyr_Т_OR_lat_T); // 'Т'
    public static final Pair<Integer, Boolean> HS_LAT_T = nonH(H_cyr_Т_OR_lat_T); // 'T'

    public static final int H_cyr_Р_OR_lat_R = 0x62; // в кириллице 'Р' а в латинице 'R'
    public static final Pair<Integer, Boolean> HS_CYR_Р = nonH(H_cyr_Р_OR_lat_R); // 'Р'
    public static final Pair<Integer, Boolean> HS_LAT_R = nonH(H_cyr_Р_OR_lat_R); // 'R'

    public static final int H_cyr_Н_OR_lat_N = 0x63; // в кириллице 'Н' а в латинице 'N'
    public static final Pair<Integer, Boolean> HS_CYR_Н = nonH(H_cyr_Н_OR_lat_N); // 'Н'
    public static final Pair<Integer, Boolean> HS_LAT_N = nonH(H_cyr_Н_OR_lat_N); // 'N'

    public static final int H_none_5_OR_shift_Percent = 0x64; // без шифта '5' а с шифтом '%'
    public static final Pair<Integer, Boolean> HS_5 = nonH(H_none_5_OR_shift_Percent);         // '5'
    public static final Pair<Integer, Boolean> HS_PERCENT = shfH(H_none_5_OR_shift_Percent); // '%'

    public static final int H_F5 = 0x65;

    public static final int H_ESC = 0x70;

    public static final int H_cyr_И_OR_lat_I = 0x71; // в кириллице 'И' а в латинице 'I'
    public static final Pair<Integer, Boolean> HS_CYR_И = nonH(H_cyr_И_OR_lat_I); // 'И'
    public static final Pair<Integer, Boolean> HS_LAT_I = nonH(H_cyr_И_OR_lat_I); // 'I'

    public static final int H_cyr_П_OR_lat_P = 0x72; // в кириллице 'П' а в латинице 'P'
    public static final Pair<Integer, Boolean> HS_CYR_П = nonH(H_cyr_П_OR_lat_P); // 'П'
    public static final Pair<Integer, Boolean> HS_LAT_P = nonH(H_cyr_П_OR_lat_P); // 'P'

    public static final int H_cyr_Е_OR_lat_E = 0x73; // в кириллице 'Е' а в латинице 'E'
    public static final Pair<Integer, Boolean> HS_CYR_Е = nonH(H_cyr_Е_OR_lat_E); // 'Е'

    public static final int H_none_4_OR_shift_Dollar = 0x74; // без шифта '4' а с шифтом '$'
    public static final Pair<Integer, Boolean> HS_4 = nonH(H_none_4_OR_shift_Dollar);         // '4'
    public static final Pair<Integer, Boolean> HS_DOLLAR = shfH(H_none_4_OR_shift_Dollar); // '$'

    public static final int H_F4 = 0x75;

    public static final int H_DOWN = 0x80;

    public static final int H_cyr_М_OR_lat_M = 0x81; // в кириллице 'М' а в латинице 'M'
    public static final Pair<Integer, Boolean> HS_CYR_М = nonH(H_cyr_М_OR_lat_M); // 'М'
    public static final Pair<Integer, Boolean> HS_LAT_M = nonH(H_cyr_М_OR_lat_M); // 'M'

    public static final int H_cyr_А_OR_lat_A = 0x82; // в кириллице 'А' а в латинице 'A'
    public static final Pair<Integer, Boolean> HS_CYR_А = nonH(H_cyr_А_OR_lat_A); // 'А'
    public static final Pair<Integer, Boolean> HS_LAT_A = nonH(H_cyr_А_OR_lat_A); // 'A'

    public static final int H_cyr_К_OR_lat_K = 0x83; // в кириллице 'К' а в латинице 'K'
    public static final Pair<Integer, Boolean> HS_CYR_К = nonH(H_cyr_К_OR_lat_K); // 'К'
    public static final Pair<Integer, Boolean> HS_LAT_K = nonH(H_cyr_К_OR_lat_K); // 'K'

    public static final int H_none_3_OR_shift_NumberSign = 0x84; // без шифта '3' а с шифтом '#'
    public static final Pair<Integer, Boolean> HS_3 = nonH(H_none_3_OR_shift_NumberSign);         // '3'
    public static final Pair<Integer, Boolean> HS_NUMBER_SIGN = shfH(H_none_3_OR_shift_NumberSign); // '#'

    public static final int H_F3 = 0x85;

    public static final int H_UP = 0x90;

    public static final int H_cyr_С_OR_lat_S = 0x91; // в кириллице 'С' а в латинице 'S'
    public static final Pair<Integer, Boolean> HS_CYR_С = nonH(H_cyr_С_OR_lat_S); // 'С'
    public static final Pair<Integer, Boolean> HS_LAT_S = nonH(H_cyr_С_OR_lat_S); // 'S'

    public static final int H_cyr_В_OR_lat_W = 0x92; // в кириллице 'В' а в латинице 'W'
    public static final Pair<Integer, Boolean> HS_CYR_В = nonH(H_cyr_В_OR_lat_W); // 'В'
    public static final Pair<Integer, Boolean> HS_LAT_W = nonH(H_cyr_В_OR_lat_W); // 'W'

    public static final int H_cyr_У_OR_lat_U = 0x93; // в кириллице 'У' а в латинице 'U'
    public static final Pair<Integer, Boolean> HS_CYR_У = nonH(H_cyr_У_OR_lat_U); // 'У'
    public static final Pair<Integer, Boolean> HS_LAT_U = nonH(H_cyr_У_OR_lat_U); // 'U'

    public static final int H_none_2_OR_shift_Quote = 0x94; // без шифта '2' а с шифтом '"'
    public static final Pair<Integer, Boolean> HS_2 = nonH(H_none_2_OR_shift_Quote);         // '2'
    public static final Pair<Integer, Boolean> HS_QUOTE = shfH(H_none_2_OR_shift_Quote); // '"'

    public static final int H_F2 = 0x95;

    public static final int H_HOME = 0xA0;

    public static final int H_cyr_Ч_OR_lat_Circumflex = 0xA1; // в кириллице 'Ч' а в латинице '^'
    public static final Pair<Integer, Boolean> HS_CYR_Ч = nonH(H_cyr_Ч_OR_lat_Circumflex);  // 'Ч'
    public static final Pair<Integer, Boolean> HS_LAT_CIRCUMFLEX = nonH(H_cyr_Ч_OR_lat_Circumflex); // '^'

    public static final int H_cyr_Ы_OR_lat_Y = 0xA2; // в кириллице 'Ы' а в латинице 'Y'
    public static final Pair<Integer, Boolean> HS_CYR_Ы = nonH(H_cyr_Ы_OR_lat_Y); // 'Ы'
    public static final Pair<Integer, Boolean> HS_LAT_Y = nonH(H_cyr_Ы_OR_lat_Y); // 'Y'

    public static final int H_cyr_Ц_OR_lat_C = 0xA3; // в кириллице 'Ц' а в латинице 'C'
    public static final Pair<Integer, Boolean> HS_CYR_Ц = nonH(H_cyr_Ц_OR_lat_C); // 'Ц'
    public static final Pair<Integer, Boolean> HS_LAT_C = nonH(H_cyr_Ц_OR_lat_C); // 'C'

    public static final int H_none_1_OR_shift_ExclamationMark = 0xA4; // без шифта '1' а с шифтом '!'
    public static final Pair<Integer, Boolean> HS_1 = nonH(H_none_1_OR_shift_ExclamationMark);         // '1'
    public static final Pair<Integer, Boolean> HS_EXCLAMATION_MARK = shfH(H_none_1_OR_shift_ExclamationMark); // '!'

    public static final int H_F1 = 0xA5;

    public static final int H_РУС_ЛАТ = 0xB0;  // переключение рус/лат клавиатуры

    public static final int H_cyr_Я_OR_lat_Q = 0xB1; // в кириллице 'Я' а в латинице 'Q'
    public static final Pair<Integer, Boolean> HS_CYR_Я = nonH(H_cyr_Я_OR_lat_Q); // 'Я'
    public static final Pair<Integer, Boolean> HS_LAT_Q = nonH(H_cyr_Я_OR_lat_Q); // 'Q'

    public static final int H_cyr_Ф_OR_lat_F = 0xB2; // в кириллице 'Ф' а в латинице 'F'
    public static final Pair<Integer, Boolean> HS_CYR_Ф = nonH(H_cyr_Ф_OR_lat_F); // 'Ф'
    public static final Pair<Integer, Boolean> HS_LAT_F = nonH(H_cyr_Ф_OR_lat_F); // 'F'

    public static final int H_cyr_Й_OR_lat_J = 0xB3; // в кириллице 'Й' а в латинице 'J'
    public static final Pair<Integer, Boolean> HS_CYR_Й = nonH(H_cyr_Й_OR_lat_J); // 'Й'
    public static final Pair<Integer, Boolean> HS_LAT_J = nonH(H_cyr_Й_OR_lat_J); // 'J'

    public static final int H_none_Semicolon_OR_shift_Plus = 0xB4; // без шифта ';' а с шифтом '+'
    public static final Pair<Integer, Boolean> HS_SEMICOLON = nonH(H_none_Semicolon_OR_shift_Plus); // ';'
    public static final Pair<Integer, Boolean> HS_PLUS = shfH(H_none_Semicolon_OR_shift_Plus); // '+'

    public static final int H_F12 = 0xB5;
}