package spec;

import spec.keys.*;

import java.util.Arrays;
import java.util.List;

import static spec.Constants.ROM;
import static spec.WordMath.hex16;

public class IOPorts {

    public static final int PAUSE_AWT = 1024;
    public static final int PAUSE_SWING = 19;

    public static final int LAYOUT_SWING = 1;
    public static final int LAYOUT_AWT = 2;

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

    private List<K> keys = Arrays.asList(
            new Enter(),
            new Backspace(),
            new Dot(),
            new MoreThan(),
            new End(),
            new Semicolon(),
            new Colon(),
            new Star());

    private Memory memory;

    public IOPorts(Memory memory) {
        this.memory = memory;
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
    int tick = 0;

    public synchronized void processKey(Key key) {
        if (key.layout() == LAYOUT_SWING && key.code() == 0x0010) {
            shift = key.pressed();
            return;
        }

        keys.stream()
                .filter(k -> k.itsMe(key))
                .findFirst()
                .ifPresent(button -> {
                    keyStatus[button.x()][button.y()] = key.pressed();
                });
    }

    private String toString(boolean[][] keys) {
        StringBuilder result = new StringBuilder();
        result.append(' ');
        for (int x = keys.length - 1; x >= 0; x--) {
            result.append(String.format("%1X", x));
        }
        result.append('\n');
        for (int y = keys[0].length - 1; y >= 0; y--) {
            result.append(String.format("%1X", y));
            for (int x = keys.length - 1; x >= 0; x--) {
                result.append(keys[x][y] ? '+' : '-');
            }
            result.append('\n');
        }
        return result.toString();
    }

    private void log(Key key, K button) {
        System.out.println((++tick) + "> "
                + (key.shift() ? "+" : " ")
                + " "
                + (String.format("%1X", button.x()) + "," + String.format("%1X", button.y()))
                + " "
                + (key.pressed() ? "down " : "up   ")
                + "0x" + hex16(key.code())
                + " " + (button.shiftOut() ? "+" : " ")
                + button.getClass().getSimpleName());
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