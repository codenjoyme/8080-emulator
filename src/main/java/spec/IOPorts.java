package spec;

import java.util.HashMap;
import java.util.Map;

import static spec.Constants.ROM;
import static spec.Key.*;
import static spec.WordMath.hex16;
import static spec.WordMath.hex8;

public class IOPorts {

    public static final int PAUSE_KEY = 0x13;
    public static final int SHIFT_KEY = 0x10;
    public static final int CTRL_KEY = 0x11;
    public static final int ALT_KEY = 0x12;

    private boolean Ain;   // порт A на ввод
    private boolean Bin;   // порт B на ввод
    private boolean C0in;  // порт C0 на ввод
    private boolean C1in;  // порт C1 на ввод

    private boolean shift; // нажат ли shift на эмулируемой машине

    private static final int PortA = 0xFFE0; // Порт А ППА
    private static final int PortB = 0xFFE1; // Порт В ППА
    private static final int PortC = 0xFFE2; // Порт С ППА
    private static final int RgRYS = 0xFFE3; // рег. Упр.Слова ППА
    public static final int RgRGB = 0xFFF8;  // порт контроллера цвета

    // маски битов
    private final int[] bit = {0x00FE, 0x00FD, 0x00FB, 0x00F7, 0x00EF, 0x00DF, 0x00BF, 0x007F};

    // биты установки
    private final int[] msk = {0x0001, 0x0002, 0x0004, 0x0008, 0x0010, 0x0020, 0x0040, 0x0080};

    // массив матрицы клавш "Специалиста" ( true - нажата, false - отпущена)
    // 12 x 6 + Shift + Reset
    private boolean[][] keyStatus = new boolean[12][6];

    private Map<Integer, Integer> keys = new HashMap<>();

    private Memory memory;

    public IOPorts(Memory memory) {
        this.memory = memory;
        initKeys();
        reset();
    }

    // ввод из порта памяти 580ВВ55
    public synchronized int read8(int addr) {
        // все порты ППА перенесём в область 0xFFE0 - 0xFFE3
        addr = shiftPortAddress(addr);

        int result = 0b1111_1111; // предварительно ни одна кнопка не нажата
        // port 580BB55
        if (addr > ROM.end() && addr < RgRYS) {
            // разбираем по каналам...
            switch (addr) {
                case PortA: {
                    // если порт A - на ввод
                    if (Ain) {
                        // если порт B - на вывод
                        if (!Bin) {
                            // по битам порта B от 2 до 7
                            for (int i = 0; i < 6; i++) {
                                // по битам порта A от 0 до 7
                                for (int j = 0; j < 8; j++)  {
                                    // если такая нажата  и  такой бит порта B = 0, ставим бит A = 0
                                    if (keyStatus[j][i] && (B() & msk[i + 2]) == 0) {
                                        result &= bit[j];
                                    }
                                }
                            }
                            //  возвращаем состояние порта A
                            return result;
                        }
                        // если порт B - на ввод то и делать нечего
                    }
                    //  порта A - на вывод < последнее записанное
                    return A();
                }

                // Порт В
                case PortB: {
                    // если порта В - на ввод
                    if (Bin) {
                        // если порт A - на вывод
                        if (!Ain) {
                            // по битам порта A от 0 до 7
                            for (int i = 0; i < 8; i++) {
                                // по битам порта В от 2 до 7
                                for (int j = 0; j < 6; j++)  {
                                    // если такая нажата  и  такой бит порта A = 0, ставим бит В = 0
                                    if (keyStatus[i][j] && (A() & msk[i]) == 0) {
                                        result &= bit[j + 2];
                                    }
                                }
                            }
                        }

                        // если порт CLow - на вывод
                        if (!C0in) {
                            // по битам порта CLow от 0 до 3
                            for (int i = 0; i < 4; i++) {
                                // по битам порта В от 2 до 7
                                for (int j = 0; j < 6; j++) {
                                    // если такая нажата  и  такой бит порта C = 0, ставим бит В = 0
                                    if (keyStatus[i + 8][j] && (C() & msk[i]) == 0) {
                                        result &= bit[j + 2];
                                    }
                                }
                            }
                        }

                        // если порт C - на ввод то и делать нечего
                        // если порт A - на ввод то и делать нечего

                        // КАКОЙ_ТО КОНФЛИКТ СО <CapsLock> - он работает наоборот
                        // <CpsLk> = Р/Л ЭТО ДАЁТ СБОЙ ЗДЕСЬ Т.К. ВЛИЯЕТ НА КОД КЛАВИШИ!!!
                        // в Мониторе "Shift" не влияет на клавишу. Влияет РУС/ЛАТ (НР.ФИКС) !!!
                        if (shift) {
                            result &= 0b1111_1101; // выставим состояние Shift: B1 = 0
                        } else {
                            result |= 0b0000_0010; // выставим состояние Shift: B1 = 1
                        }

                        //  возвращаем состояние порта В
                        return result;
                    }
                    //  порта В - на вывод < последнее записанное
                    return B();
                }

                // Порт С orphaned default
                default: {
                    // если порта CLow - на ввод
                    if (C0in) {
                        // если порт B - на вывод
                        if (!Bin) {
                            // по битам порта B от 2 до 7
                            for (int i = 0; i < 6; i++) {
                                // по битам порта CLow от 0 до 3
                                for (int j = 0; j < 4; j++) {
                                    // если такая нажата  и  такой бит порта В = 0, ставим бит C = 0
                                    if (keyStatus[j + 8][i] && (B() & msk[i + 2]) == 0) {
                                        result = result & bit[j];
                                    }
                                }
                            }
                            //  возвращаем состояние порта C
                            return result;
                        }
                    } else {
                        // если порта CLow - на вывод
                        return C() & 0b0000_1111 | 0b1111_0000;
                    }
                }
                //ЗДЕСЬ - ПОДУМАТЬ И ПРОВЕРИТЬ!!!
            }
        } else {
            // остальные порты и РУС ВРЕМЕННО считаем ячейками памяти!
            result = memory.read8(addr);
        }
        return result;
    }

    public synchronized void write8(int addr, int bite) {
        // все порты ППА перенесём в область 0xFFE0 - 0xFFE3
        addr = shiftPortAddress(addr);

        // Разбор управляющего слова ППА 580ВВ55
        // РУС
        if (addr == RgRYS) {
            // управляющие слова 1-старший бит
            if ((bite & 0b1000_0000) != 0) {
                // КАНАЛ_С(МЛ.)(РС0-РС3)
                C0in = (bite & 0b0000_0001) != 0;
                if (!C0in) {
                    C(C() & 0b1111_0000);
                }
                // КАНАЛ_В(РВ0-РВ7)
                Bin = (bite & 0b0000_0010) != 0;
                if (!Bin) {
                    B(0);
                }
                // КАНАЛ_С(СТ.)(РС4-РС7)
                C1in = (bite & 0b0000_1000) != 0;
                if (!C1in) {
                    C(C() & 0b0000_1111);
                }
                // КАНАЛ_A(РА0-РА7)
                Ain = (bite & 0b0001_0000) != 0;
                if (!Ain) {
                    A(0);
                }
                // в ПОРТ RYC запишем YC ПОРТЫ 0xFFE3
                memory.write8(addr, bite);
                return;
            } else {
                // побитное управление портом 0xFFE3
                // если порт C0- на вывод
                if (!C0in) {
                    if ((bite & 0b0000_0001) == 1) {
                        // биты 0-3
                        // уст. в 1
                        if (((bite & 0b0000_1110) >> 1) < 4) {
                            C(C() | msk[((bite & 0b0000_1110) >> 1)]);
                        }
                    } else {
                        // биты 0-3
                        // уст. в 0
                        if (((bite & 0b0000_1110) >> 1) < 4) {
                            C(C() & bit[((bite & 0b0000_1110) >> 1)]);
                        }
                    }
                }
                // если порт C1- на вывод
                if (!C1in) {
                    if ((bite & 0b0000_0001) == 1) {
                        // биты 4-7
                        // уст. в 1
                        if (((bite & 0b0000_1110) >> 1) > 3) {
                            int b = C();
                            C(b | msk[((b & 0b0000_1110) >> 1)]);
                        }
                    } else {
                        // биты 4-7
                        // уст. в 0
                        if (((bite & 0b0000_1110) >> 1) > 3) {
                            int b = C();
                            C(b & bit[((b & 0b0000_1110) >> 1)]);
                        }
                    }
                }
            }
        }
        // в остальные ПОРТЫ : в том числе и 0xFFF8 - цвет
        // пишем в память: 0xC000 < ПОРТЫ < 0xFFFF
        memory.write8(addr, bite);
    }

    private int A() {
        return memory.read8(PortA);
    }

    private void A(int bite) {
        memory.write8(PortA, bite);
    }

    private int B() {
        return memory.read8(PortB);
    }

    private void B(int bite) {
        memory.write8(PortB, bite);
    }

    private int C() {
        return memory.read8(PortC);
    }

    private void C(int bite) {
        memory.write8(PortC, bite);
    }

    private int shiftPortAddress(int addr) {
        if (addr > RgRYS) {
            // адреса 0xFFF9...0xFFFE не трогаем
            return addr;
        }

        // для всех портов ППА из диапазона 0xF800...0xFFF8
        // берем первые два бита 0x0000...0x0003
        // превращаем их в 0xFFE0...0xFFE3
        // короче там циклическое дублирование во все диапазоне
        return (addr & 0b0000_0000_0000_0011)
                | 0b1111_1111_1110_0000;
    }

    // переменные-заготовки для полу-рядов матрицы клавиатуры .
    // значение в них устанавливается при нажатии-отпускании клавиши.
    // в порт клавиатуры - xxfeh они записываются во время его опроса
    // согласно "0", выбирающему конкретный полуряд: public int inb( int port )

    public synchronized void resetKeyboard() {
        for (int i = 0; i < 12; i++) {  // все кнопки не нажаты
            for (int j = 0; j < 6; j++) {
                keyStatus[i][j] = false;
            }
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
 * |  _4  | + ; | ! 1 | " 2 | # 3 | $ 4 | % 5 | & 6 | ' 7 | ( 8 | ) 9 |   0 | = = |    B4   |
 * |  _3  | J Й | C Ц | U У | K K | E E | N Н | G Г | [ Ш | ] Щ | Z З | H Х | : * |    B3   |
 * |  _2  | F Ф | Y Ы | W В | A A | P П | R Р | O O | L Л | D Д | V Ж | \ Э | . > |    B2   |
 * |  _1  | Q Я | ^ Ч | S С | M M | I И | T T | X Ь | B Б | @ Ю | < , | ? / |  ЗБ |    B1   |
 * |  _0  | Р/Л |HOME |  Up |Down | ESC | TAB | SPС |  <= |  ПВ |  => |  ПС |  ВК |    B0   |
 * +========================================================================================+
 **/

    private void putChar(char en, int enPt, char cyr, int cyrPt) {
        putNorm(en, enPt);   //  Q  -> Q [Я]
        putCyrl(cyr, cyrPt); // [Й] -> J [Й]
        putCtrl(en, cyrPt);  // ctrl  Q  -> J [Й]
        putCyCt(cyr, enPt);  // ctrl [Й] -> Я [Q]
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
        // пример того как можно навешать на одну клавишу
        putAlLS('2', 0x94); // left_alt shift 2 -> "

        putNorm('3', 0x84); // 3 -> 3
        putShft('3', 0x84); // # -> #

        putNorm('4', 0x74); // 4 -> 4
        putShft('4', 0x74); // shift 4 -> $
        putAltL('4', 0x03); // left_alt 4 -> :
        putCtrl('4', 0xB4); // ctrl 4 -> ;

        putNorm('5', 0x64); // 5 -> 5
        putShft('5', 0x64); // shift 5 -> %

        putNorm('6', 0x54); // 6 -> 6
        putShft('6', 0xA1); // shift 6 -> ^ [Ч]
        putAltL('6', 0x03); // left_alt 6 -> :
        putCtrl('6', 0xB4); // ctrl 6 -> ;

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

        putNorm(0x0A, 0x00); // backspace -> backspace

        // вторая линия стандартной клавиатуры

        // Tab не определяется в Swing

        putChar('Q', 0xB1,  //  Q  -> Q [Я]
                'й', 0xB3); // [Й] -> J [Й]
                            // ctrl  Q  -> J [Й]
                            // ctrl [Й] -> Я [Q]

        putChar('W', 0x92,  //  W  -> W [В]
                'ц', 0xA3); // [Ц] -> С [Ц]
                            // ctrl  W  -> С [Ц]
                            // ctrl [Ц] -> В [W]  // TODO продолжить с этим чудом

        putNorm('E', 0x73); //  E  -> E [E]
        putCyrl('у', 0x93); // [У] -> u [У]

        putNorm('R', 0x62); //  R  -> R [Р]
        putCyrl('к', 0x83); // [К] -> К [К]

        putNorm('T', 0x61); //  T  -> T [Т]
        putCyrl('е', 0x73); // [Е] -> Е [Е]

        putNorm('Y', 0xA2); //  Y  -> Y [Ы]
        putCyrl('н', 0x63); // [Н] -> N [Н]

        putNorm('U', 0x93); //  U  -> U [У]
        putCyrl('г', 0x53); // [Г] -> G [Г]

        putNorm('I', 0x71); //  I  -> I [И]
        putCyrl('ш', 0x43); // [Ш] -> [ [Ш]

        putNorm('O', 0x52); //  O  -> O [О]
        putCyrl('щ', 0x33); // [Щ] -> ] [Щ]

        putNorm('P', 0x72); //  P  -> P [П]
        putCyrl('з', 0x23); // [З] -> Z [З]

        putNorm('[', 0x43); //  [  -> [ [Ш]
        putCyrl('х', 0x51); // [Х] -> Ь [Х]

        putNorm(']', 0x33); //  ]  -> ] [Щ]
        //putCyrl('ъ', 0x00);  // нет такого знака

        putNorm(0x00, 0x01); // enter -> enter

        // третья линия стандартной клавиатуры

        putNorm(0x14, 0xB0);  // caps_lock -> RusLat

        putNorm('A', 0x82); //  A  -> A [А]
        putCyrl('ф', 0xB2); // [Ф] -> F [Ф]

        putNorm('S', 0x91); //  S  -> S [С]
        putCyrl('ы', 0xA2); // [Ы] -> Y [Ы]

        putNorm('D', 0x32); //  D  -> D [Д]
        putCyrl('в', 0x92); // [В] -> W [В]

        putNorm('F', 0xB2); //  F  -> F [Ф]
        putCyrl('а', 0x82); // [А] -> A [А]

        putNorm('G', 0x53); //  G  -> G [Г]
        putCyrl('п', 0x72); // [П] -> p [П]

        putNorm('H', 0x13); //  H  -> H [Х]
        putCyrl('р', 0x62); // [Р] -> R [Р]

        putNorm('J', 0xB3); //  J  -> J [й]
        putCyrl('о', 0x52); // [О] -> O [О]

        putNorm('K', 0x83); //  K  -> K [К]
        putCyrl('л', 0x42); // [Л] -> L [Л]

        putNorm('L', 0x42); //  L  -> L [Л]
        putCyrl('д', 0x32); // [Д] -> D [Д]

        putNorm(';', 0x03); // ;   -> :
        putShft(';', 0xB4); // shift ;  -> +
        putAltR(';', 0xB4); // right_alt ; -> ;
        putCyrl('ж', 0x22); // [Ж] -> V [Ж]

        putNorm('Þ', 0xA1); // ' -> ^ [Ч]
        putShft('Þ', 0x94); // shift ' -> "
        putCyrl('э', 0x12); // Э -> \ [Э]

        putNorm('\\', 0x11); // \ -> /
        putShft('\\', 0x12); // shift \ -> \ [Э]

        // четвертая линия стандартной клавиатуры

        // shift работает как системная клавиша

        putNorm('Z', 0x23); //  Z  -> Z [З]
        putCyrl('я', 0xB1); // [Я] -> Q [Я]

        putNorm('X', 0x51); //  X  -> X [Ь]
        putCyrl('ч', 0xA1); // [Ч] -> ^ [Ч]

        putNorm('C', 0xA3); //  C  -> C [Ц]
        putCyrl('с', 0x91); // [С] -> S [С]

        putNorm('V', 0x22); //  V  -> V [Ж]
        putCyrl('м', 0x81); // [М] -> M [М]

        putNorm('B', 0x41); //  B  -> B [Б]
        putCyrl('и', 0x71); // [И] -> I [И]

        putNorm('N', 0x63); //  N  -> N [Н]
        putCyrl('т', 0x61); // [Т] -> T [Т]

        putNorm('M', 0x81); //  M  -> M [М]
        putCyrl('ь', 0x51); // [Ь] -> X [Ь]

        putNorm(',', 0x21); //  ,  -> ,
        putShft(',', 0x21); //  shift , -> <
        putCyrl('б', 0x41); // [Б] -> B [Б]

        putNorm('.', 0x02); //  .  -> .
        putShft('.', 0x02); //  shift . -> >
        putCyrl('ю', 0x31); // [Ю] -> @ [Ю]

        putNorm('/', 0x11); //  /  -> /
        putShft('/', 0x11); //  shift / -> ?

        // другие клавиши

        putNorm(0x1B, 0x70); //  esc -> esc
        putNorm(' ', 0x50);  //  space -> space

        putNorm(0x9B, 0x30); //  ins -> ПВ
        putNorm(0x24, 0xA0); //  home -> home
        putNorm(0x24, 0x60); //  page up -> tab
        putNorm(0x7F, 0x00); //  delete -> backspace
        putNorm(0x23, 0x05); //  end -> F11
        putNorm(0x22, 0x15); //  page down -> F10

        putNorm(0x26, 0x90); //  up -> up
        putNorm(0x28, 0x80); //  down -> down
        putNorm(0x25, 0x40); //  left -> left
        putNorm(0x27, 0x20); //  right -> right

        putNorm(0x70, 0xA5); //  F1 -> F1
        putNorm(0x71, 0x95); //  F2 -> F2
        putNorm(0x72, 0x85); //  F3 -> F3
        putNorm(0x73, 0x75); //  F4 -> F4
        putNorm(0x74, 0x65); //  F5 -> F5
        putNorm(0x75, 0x55); //  F6 -> F6
        putNorm(0x76, 0x45); //  F7 -> F7
        putNorm(0x77, 0x35); //  F8 -> F8
        putNorm(0x78, 0x25); //  F9 -> F9
        putNorm(0x79, 0x15); //  F10 -> F10
        putNorm(0x7A, 0x05); //  F11 -> F11
        putNorm(0x7B, 0xB5); //  F -> F12
    }

    public synchronized void processKey(Key key) {
        if (key.pressed()) {
            System.out.println(
                    (char)key.code() + " _ "
                    + hex8(key.code()) + " _ "
                    + hex16(key.joint()));
        }

        if (key.code() == SHIFT_KEY) {
            shift = key.pressed();
            return;
        }

        if (key.code() == ALT_KEY
                || key.code() == CTRL_KEY
                || key.code() == PAUSE_KEY)
        {
            return;
        }

        Integer pt = keys.get(key.joint());
        if (pt != null) {
            int x = (pt & 0xF0) >> 4;
            int y = pt & 0x0F;
            keyStatus[x][y] = key.pressed();
            return;
        }
    }

    public synchronized void reset() {
        shift = false;
        Ain = true;
        Bin = true;
        C0in = true;
        C1in = true;

        // все кнопки не нажаты
        resetKeyboard();

        // порт RYC[0xFFE3] = 9Bh (все на ввод)
        memory.write16(RgRYS, 0x009B);

        // порт цвета - зелёный на черном.
        memory.write16(RgRGB, 0x0020);
    }
}