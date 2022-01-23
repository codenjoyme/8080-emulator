package spec;

import java.util.HashMap;
import java.util.Map;

import static spec.Key.*;

public class Keyboard {

    private Map<Integer, Integer> keys;

    public Keyboard() {
        this.keys = new HashMap<>();
        initKeys();
    }

    public Integer key(int code) {
        return keys.get(code);
    }

    private void putChar(char en, int enPt, char cyr, int cyrPt) {
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

    private void putNorm(Integer code, int pt) {
        keys.put(code, pt);
    }

    private void putNorm(char code, int pt) {
        keys.put((int)code, pt);
    }

    private void putShft(char code, int pt) {
        keys.put((int)code | SHIFT, pt);
    }

    private void putAltR(char code, int pt) {
        keys.put((int)code | RIGHT_ALT, pt);
    }

    private void putAltL(char code, int pt) {
        keys.put((int)code | LEFT_ALT , pt);
    }

    private void putAlLS(char code, int pt) {
        keys.put((int)code | LEFT_ALT | SHIFT, pt);
    }

    private void putCtrl(char code, int pt) {
        keys.put((int)code | CTRL, pt);
    }

    private void putCyCt(char code, int pt) {
        keys.put((int)code | CYRYLIC | CTRL, pt);
    }

    private void putCyrl(char code, int pt) {
        keys.put((int)code | CYRYLIC, pt);
    }

    private void putCySh(char code, int pt) {
        keys.put((int)code | CYRYLIC | SHIFT, pt);
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

    private void initKeys() {
        // первая линия стандартной клавиатуры

        //putNorm('À', 0x00); // первая клавиша ~/[Ё]
        //putShft('À', 0x00);
        //putCyrl('ё', 0x00);
        //putCrSh('ё', 0x00);

        putNorm('1', 0xA4); // 1 -> 1
        putShft('1', 0xA4); // shift 1 -> !

        putNorm('2', 0x94); // 2 -> 2
        putShft('2', 0x31); // shift 2 -> @ [Ю]
        putAlLS('2', 0x94); // left_alt shift 2 -> "

        putNorm('3', 0x84); // 3 -> 3
        putShft('3', 0x84); // # -> #

        putNorm('4', 0x74); // 4 -> 4
        putShft('4', 0x74); // shift 4 -> $
        putCtrl('4', 0x03); // ctrl 4 -> :
        putAltL('4', 0xB4); // left_alt 4 -> ;

        putNorm('5', 0x64); // 5 -> 5
        putShft('5', 0x64); // shift 5 -> %

        putNorm('6', 0x54); // 6 -> 6
        putShft('6', 0xA1); // shift 6 -> ^ [Ч]
        putCtrl('6', 0x03); // ctrl 6 -> :
        putAltL('6', 0xB4); // left_alt 6 -> ;

        putNorm('7', 0x44); // 7 -> 7
        putShft('7', 0x54); // shift 7 -> &
        putAlLS('7', 0x11); // left_alt shift 7 -> ?

        putNorm('8', 0x34); // 8 -> 8
        putShft('8', 0x03); // shift 8 -> *

        putNorm('9', 0x24); // 9 -> 9
        putShft('9', 0x34); // shift 9 -> (

        putNorm('0', 0x14); // 0 -> 0
        putShft('0', 0x24); // shift 0 -> )

        putNorm('-', 0x04); // - -> -
        putShft('-', 0x04); // shift - -> =

        putNorm('=', 0xB4); // = -> ;
        putShft('=', 0xB4); // shift = -> +
        putCtrl('=', 0x03); // ctrl = -> :
        putAltL('=', 0xB4); // left_alt = -> ;

        putNorm(0x0A, 0x00); // backspace -> backspace

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        putChar('Q', 0xB1,  //  Q  -> Q [Я]
                'й', 0xB3); // (Й) -> J [Й]
        // ctrl  Q  -> J [Й]
        // ctrl (Й) -> Я [Q]

        putChar('W', 0x92,  //  W  -> W [В]
                'ц', 0xA3); // (Ц) -> С [Ц]
        // ctrl  W  -> С [Ц]
        // ctrl (Ц) -> В [W]

        putChar('E', 0x73,  //  E  -> E [E]
                'у', 0x93); // (У) -> U [У]
        // ctrl  E  -> U [У]
        // ctrl (У) -> E [E]

        putChar('R', 0x62,  //  R  -> R [Р]
                'к', 0x83); // (К) -> К [К]
        // ctrl  R  -> К [К]
        // ctrl (К) -> Р [R]

        putChar('T', 0x61,  //  T  -> T [Т]
                'е', 0x73); // (Е) -> Е [Е]
        // ctrl  T  -> Е [Е]
        // ctrl (Е) -> Т [T]

        putChar('Y', 0xA2,  //  Y  -> Y [Ы]
                'н', 0x63); // (Н) -> N [Н]
        // ctrl  Y  -> N [Н]
        // ctrl (Н) -> Ы [Y]

        putChar('U', 0x93,  //  U  -> U [У]
                'г', 0x53); // (Г) -> G [Г]
        // ctrl  U  -> G [Г]
        // ctrl (Г) -> У [U]

        putChar('I', 0x71,  //  I  -> I [И]
                'ш', 0x43); // (Ш) -> [ [Ш]
        // ctrl  I  -> [ [Ш]
        // ctrl (Ш) -> И [I]

        putChar('O', 0x52,  //  O  -> O [О]
                'щ', 0x33); // (Щ) -> ] [Щ]
        // ctrl  O  -> ] [Щ]
        // ctrl (Щ) -> О [O]

        putChar('P', 0x72,  //  P  -> P [П]
                'з', 0x23); // (З) -> Z [З]
        // ctrl  P  -> Z [З]
        // ctrl (З) -> П [P]

        putChar('[', 0x43,  //  [  -> [ [Ш]
                'х', 0x51); // (Х) -> Ь [Х]
        // ctrl  [  -> Ь [Х]
        // ctrl (Х) -> Ш [[]

        putNorm(']', 0x33); //  ]  -> ] [Щ]
        //putCyrl('ъ', 0x00);  // нет такого знака

        putNorm(0x00, 0x01); // enter -> enter

        // третья линия стандартной клавиатуры

        putNorm(0x14, 0xB0);  // caps_lock -> RusLat

        putChar('A', 0x82,  //  A  -> A [А]
                'ф', 0xB2); // (Ф) -> F [Ф]
        // ctrl  A  -> F [Ф]
        // ctrl (Ф) -> А [A]

        putChar('S', 0x91,  //  S  -> S [С]
                'ы', 0xA2); // (Ы) -> Y [Ы]
        // ctrl  S  -> Y [Ы]
        // ctrl (Ы) -> С [S]

        putChar('D', 0x32,  //  D  -> D [Д]
                'в', 0x92); // (В) -> W [В]
        // ctrl  D  -> W [В]
        // ctrl (В) -> Д [D]

        putChar('F', 0xB2,  //  F  -> F [Ф]
                'а', 0x82); // (А) -> A [А]
        // ctrl  F  -> A [А]
        // ctrl (А) -> Ф [F]

        putChar('G', 0x53,  //  G  -> G [Г]
                'п', 0x72); // (П) -> P [П]
        // ctrl  G  -> P [П]
        // ctrl (П) -> Г [G]

        putChar('H', 0x13,  //  H  -> H [Х]
                'р', 0x62); // (Р) -> R [Р]
        // ctrl  H  -> R [Р]
        // ctrl (Р) -> Х [H]

        putChar('J', 0xB3,  //  J  -> J [Й]
                'о', 0x52); // (О) -> O [О]
        // ctrl  J  -> O [О]
        // ctrl (О) -> Й [J]

        putChar('K', 0x83,  //  K  -> K [К]
                'л', 0x42); // (Л) -> L [Л]
        // ctrl  K  -> L [Л]
        // ctrl (Л) -> К [K]

        putChar('L', 0x42,  //  L  -> L [Л]
                'д', 0x32); // (Д) -> D [Д]
        // ctrl  L  -> D [Д]
        // ctrl (Д) -> Л [L]

        putChar(';', 0x03,  // ;   -> :
                'ж', 0x22); // (Ж) -> V [Ж]
        // ctrl  ;  -> V [Ж]
        // ctrl (Ж) -> :
        putShft(';', 0xB4); // shift ;  -> +
        putAltL(';', 0xB4); // left_alt ; -> ;

        putChar('Þ', 0xA1,  // ' -> ^ [Ч]
                'э', 0x12); // Э -> \ [Э]
        // ctrl  '  -> \ [Э]
        // ctrl (Э) -> Ч [^]
        putShft('Þ', 0x94); // shift ' -> "

        putNorm('\\', 0x11); // \ -> /
        putShft('\\', 0x12); // shift \ -> \ [Э]

        // четвертая линия стандартной клавиатуры

        // shift работает как системная клавиша

        putChar('Z', 0x23,  //  Z  -> Z [З]
                'я', 0xB1); // (Я) -> Q [Я]
        // ctrl  Z  -> Q [Я]
        // ctrl (Я) -> З [Z]

        putChar('X', 0x51,  //  X  -> X [Ь]
                'ч', 0xA1); // (Ч) -> ^ [Ч]
        // ctrl  X  -> ^ [Ч]
        // ctrl (Ч) -> Ь [X]

        putChar('C', 0xA3,  //  C  -> C [Ц]
                'с', 0x91); // (С) -> S [С]
        // ctrl  C  -> S [С]
        // ctrl (С) -> Ц [C]

        putChar('V', 0x22,  //  V  -> V [Ж]
                'м', 0x81); // (М) -> M [М]
        // ctrl  V  -> M [М]
        // ctrl (М) -> Ж [V]

        putChar('B', 0x41,  //  B  -> B [Б]
                'и', 0x71); // (И) -> I [И]
        // ctrl  B  -> I [И]
        // ctrl (И) -> Б [B]

        putChar('N', 0x63,  //  N  -> N [Н]
                'т', 0x61); // (Т) -> T [Т]
        // ctrl  N  -> T [Т]
        // ctrl (Т) -> Н [N]

        putChar('M', 0x81,  //  M  -> M [М]
                'ь', 0x51); // (Ь) -> X [Ь]
        // ctrl  M  -> X [Ь]
        // ctrl (Ь) -> М [M]

        putChar(',', 0x21,  //  ,  -> ,
                'б', 0x41); // (Б) -> B [Б]
        // ctrl  ,  -> B [Б]
        // ctrl (Б) -> ,
        putShft(',', 0x21); // shift , -> <
        putCtrl(',', 0x02); // ctrl , -> >

        putChar('.', 0x02,  //  .  -> .
                'ю', 0x31); // (Ю) -> @ [Ю]
        // ctrl  .  -> @ [Ю]
        // ctrl (Ю) -> .
        putShft('.', 0x02); // shift . -> >
        putCtrl('.', 0x21); // ctrl . -> <

        putNorm('/', 0x11); // /  -> /
        putCtrl('/', 0x12); // ctrl /  -> \
        putShft('/', 0x11); // shift / -> ?

        // другие клавиши

        putNorm(0x1B, 0x70); // esc -> esc
        putNorm(' ', 0x50);  // space -> space

        putNorm(0x9B, 0x30); // ins -> ПВ
        putNorm(0x24, 0xA0); // home -> home
        putNorm(0x24, 0x60); // page up -> tab
        putNorm(0x7F, 0x00); // delete -> backspace
        putNorm(0x23, 0x05); // end -> F11
        putNorm(0x22, 0x15); // page down -> F10

        putNorm(0x26, 0x90); // up -> up
        putNorm(0x28, 0x80); // down -> down
        putNorm(0x25, 0x40); // left -> left
        putNorm(0x27, 0x20); // right -> right

        putNorm(0x70, 0xA5); // F1 -> F1
        putNorm(0x71, 0x95); // F2 -> F2
        putNorm(0x72, 0x85); // F3 -> F3
        putNorm(0x73, 0x75); // F4 -> F4
        putNorm(0x74, 0x65); // F5 -> F5
        putNorm(0x75, 0x55); // F6 -> F6
        putNorm(0x76, 0x45); // F7 -> F7
        putNorm(0x77, 0x35); // F8 -> F8
        putNorm(0x78, 0x25); // F9 -> F9
        putNorm(0x79, 0x15); // F10 -> F10
        putNorm(0x7A, 0x05); // F11 -> F11
        putNorm(0x7B, 0xB5); // F -> F12
    }
}
