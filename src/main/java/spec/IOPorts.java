package spec;

import spec.math.Bites;
import spec.state.StateProvider;

import static spec.Constants.*;
import static spec.math.WordMath.*;

public class IOPorts implements StateProvider {

    public static final int SNAPSHOT_IO_PORTS_SIZE = 1;

    private boolean Ain;   // порт A на ввод
    private boolean Bin;   // порт B на ввод
    private boolean C0in;  // порт C0 на ввод
    private boolean C1in;  // порт C1 на ввод

    public static final int PortA = 0xFFE0; // Порт А ППА
    public static final int PortB = 0xFFE1; // Порт В ППА
    public static final int PortC = 0xFFE2; // Порт С ППА
    public static final int RgRYS = 0xFFE3; // рег. Упр.Слова ППА
    public static final Range INTERFACE = new Range(PortA, RgRYS);

    public static final int RgRGB = 0xFFF8;  // порт контроллера цвета

    // маски битов
    private final Bites bit = Bites.of(0x00FE, 0x00FD, 0x00FB, 0x00F7, 0x00EF, 0x00DF, 0x00BF, 0x007F);

    // биты установки
    private final Bites msk = Bites.of(b0000_0001, b0000_0010, b0000_0100, b0000_1000, b0001_0000, b0010_0000, b0100_0000, b1000_0000);

    private Memory memory;
    private Keyboard keyboard;

    public IOPorts(Memory memory, Keyboard keyboard) {
        this.memory = memory;
        this.keyboard = keyboard;
        reset();
    }

    @Override
    public int stateSize() {
        return SNAPSHOT_IO_PORTS_SIZE;
    }

    @Override
    public void state(Bites bites) {
        validateState("I/O ports", bites);

        int bite = bites.get(0);

        // 0b__0_0_0_A__C1_0_B_C0
        Ain(bite);
        Bin(bite);
        C0in(bite);
        C1in(bite);
    }

    @Override
    public Bites state() {
        // 0b__0_0_0_A__C1_0_B_C0
        return Bites.of(
                (Ain ? b0001_0000 : 0)
                | (Bin ? b0000_0010 : 0)
                | (C0in ? b0000_0001 : 0)
                | (C1in ? b0000_1000 : 0));
    }

    // ввод из порта памяти КР580ВВ55
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
                            // по битам порта A от 0 до 7
                            for (int i = 0; i < 8; i++) {
                                // по битам порта B от 0 до 7
                                for (int j = 0; j < 6; j++) {
                                    // если такая нажата  и  такой бит порта B = 0, ставим бит A = 0
                                    if (keyboard.keyStatus(i, j) && (B() & msk.get(j + 2)) == 0) {
                                        result &= bit.get(i);
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
                                for (int j = 0; j < 6; j++) {
                                    // если такая нажата  и  такой бит порта A = 0, ставим бит В = 0
                                    if (keyboard.keyStatus(i, j) && (A() & msk.get(i)) == 0) {
                                        result &= bit.get(j + 2);
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
                                    if (keyboard.keyStatus(i + 8, j) && (C() & msk.get(i)) == 0) {
                                        result &= bit.get(j + 2);
                                    }
                                }
                            }
                        }

                        // если порт C - на ввод то и делать нечего
                        // если порт A - на ввод то и делать нечего

                        // КАКОЙ_ТО КОНФЛИКТ СО <CapsLock> - он работает наоборот
                        // <CpsLk> = Р/Л ЭТО ДАЁТ СБОЙ ЗДЕСЬ Т.К. ВЛИЯЕТ НА КОД КЛАВИШИ!!!
                        // в Мониторе "Shift" не влияет на клавишу. Влияет РУС/ЛАТ (НР.ФИКС) !!!
                        if (keyboard.shift()) {
                            result &= 0b1111_1101; // выставим состояние Shift: B1 = 0
                        } else {
                            result |= b0000_0010; // выставим состояние Shift: B1 = 1
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
                            // по битам порта CLow от 0 до 3
                            for (int i = 0; i < 4; i++) {
                                // по битам порта B от 2 до 7
                                for (int j = 0; j < 6; j++) {
                                    // если такая нажата  и  такой бит порта В = 0, ставим бит C = 0
                                    if (keyboard.keyStatus(i + 8, j) && (B() & msk.get(j + 2)) == 0) {
                                        result &= bit.get(i);
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

        if (!INTERFACE.includes(addr)) {
            memory.write8(addr, bite);
            return;
        }

        if (PortA == addr) {
            A(bite);
            return;
        }

        if (PortB == addr) {
            B(bite);
            return;
        }

        if (PortC == addr) {
            C(bite);
            return;
        }

        // Разбор управляющего слова ППА 580ВВ55
        // РУС
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
        } else {
            // побитное управление портом 0xFFE3
            // если порт C0- на вывод
            if (!C0in) {
                if ((bite & 0b0000_0001) == 1) {
                    // биты 0-3
                    // уст. в 1
                    if (((bite & 0b0000_1110) >> 1) < 4) {
                        C(C() | msk.get((bite & 0b0000_1110) >> 1));
                    }
                } else {
                    // биты 0-3
                    // уст. в 0
                    if (((bite & 0b0000_1110) >> 1) < 4) {
                        C(C() & bit.get((bite & 0b0000_1110) >> 1));
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
                        C(b | msk.get((b & 0b0000_1110) >> 1));
                    }
                } else {
                    // биты 4-7
                    // уст. в 0
                    if (((bite & 0b0000_1110) >> 1) > 3) {
                        int b = C();
                        C(b & bit.get((b & 0b0000_1110) >> 1));
                    }
                }
            }
        }
        // в ПОРТ RYC запишем YC ПОРТЫ 0xFFE3
        R(bite);
    }


    private void Ain(int bite) {
        Ain = isSet(bite, b0001_0000);
    }

    private void C1in(int bite) {
        C1in = isSet(bite, b0000_1000);
    }

    private void Bin(int bite) {
        Bin = isSet(bite, b0000_0010);
    }

    private void C0in(int bite) {
        C0in = isSet(bite, b0000_0001);
    }

    public int A() {
        return memory.read8(PortA);
    }

    protected void A(int bite) {
        memory.write8(PortA, bite);
    }

    public int B() {
        return memory.read8(PortB);
    }

    protected void B(int bite) {
        memory.write8(PortB, bite);
    }

    public int C() {
        return memory.read8(PortC);
    }

    protected void C(int bite) {
        memory.write8(PortC, bite);
    }

    public int R() {
        return memory.read8(RgRYS);
    }

    protected void R(int bite) {
        memory.write8(RgRYS, bite);
    }

    private int shiftPortAddress(int addr) {
        if (addr > RgRYS) {
            // адреса 0xFFE4...0xFFFF не трогаем
            return addr;
        }

        // для всех портов ППА из диапазона 0xF800...0xFFE3
        // берем первые два бита 0x0000...0x0003
        // превращаем их в 0xFFE0...0xFFE3
        // короче там циклическое дублирование во все диапазоне
        return (addr & 0b0000_0000_0000_0011)
                | 0b1111_1111_1110_0000;
    }

    public synchronized void reset() {
        Ain = true;
        Bin = true;
        C0in = true;
        C1in = true;

        // порт RYC[0xFFE3] = 9Bh (все на ввод)
        memory.write16(RgRYS, 0x009B);

        // порт цвета - зелёный на черном.
        memory.write16(RgRGB, 0x0020);
    }

    public String toStringDetails() {
        return String.format(
                "Ain   : %s\n" +
                "Bin   : %s\n" +
                "C0in  : %s\n" +
                "C1in  : %s\n" +
                "\n" +
                "%s",
                bitToString(Ain),
                bitToString(Bin),
                bitToString(C0in),
                bitToString(C1in),
                keyboard.toString());
    }
}