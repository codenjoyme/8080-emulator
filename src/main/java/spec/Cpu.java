package spec;

/**
 * @(#)Z80.java 1.1 27/04/97 Adam Davidson & Andrew Pollard
 */

import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static spec.Constants.xFF;
import static spec.WordMath.word;

public class Cpu extends Registry {

    private Consumer<Integer> onTick;
    private Supplier<Boolean> onInterrupt;
    private int interrupt;
    private int tick;
    private Assembler asm;

    public Cpu(double clockFrequencyInMHz, Data data, Supplier<Boolean> onInterrupt, Consumer<Integer> onTick) {
        super(data);
        // Количество тактов на 1 прерывание, которое происходит 50 раз в секунду.
        // 1000000/50 раз в секунду
        interrupt = (int) ((clockFrequencyInMHz * 1e6) / 50);
        this.onInterrupt = onInterrupt;
        asm = new Assembler();
        this.onTick = onTick;
    }

    @Override
    public void reset() {
        super.reset();
        tick = 0;
    }

    public int tick() {
        return tick;
    }

    private int read8(int addr) {
        return data.read8(addr);
    }

    private int read16(int addr) {
        return data.read16(addr);
    }

    private void write16(int addr, int word) {
        data.write16(addr, word);
    }

    private void write8(int addr, int bite) {
        data.write8(addr, bite);
    }

    private void push16(int word) {
        SP(word(SP() - 2));
        write16(SP(), word);
    }

    private int read16(Reg reg) {
        return data.read16(reg);
    }

    private int read8(Reg reg) {
        return data.read8(reg);
    }

    private int pop16() {
        return read16(rSP);
    }

    private void pushPC() {
        push16(PC());
    }

    private void popPC() {
        PC(pop16());
    }

    private int read8PC() {
        return read8(rPC);
    }

    private int read16PC() {
        return read16(rPC);
    }

    // ввод из порта
    // при вводе из порта клавиатуры записываем в ответ состояние переменных _B_SPC..._CAPS_V.
    // в зависимости от выбраной "0" линии адреса порта 7F.FE ... FE.FE.
    // сами значения переменных _B_SPC..._CAPS_V (строк матрицы) готовятся при обработке событий
    // клавиатуры handleEvent(Event e): Event.KEY_PRESS - КЛАВИША_НАЖАТА_, Event.KEY_RELEASE: -
    // КЛАВИША_ОТПУЩЕНА_. Эти события вызывают doKey( true/false, e.key, e.modifiers ), где
    // код клавиши (e.key) и служебные клавиши (e.modifiers) превращаются в значения переменных
    // _B_SPC..._CAPS_V.
    private int inb(int port) {
        return xFF;
    }

    private static boolean interruptNeeded(int tick) {
        return tick >= 0;  
    }

    public void execute() {
        // закладываем время до прерывания
        int ticks = -interrupt;

        // цикл выборки/выполнения
        while (true) {
            if (onTick != null) {
                onTick.accept(tick);
            }
            tick++;

            if (interruptNeeded(ticks)) {
                if (!onInterrupt.get()) {
                    break;
                }
                ticks -= interrupt;
            }

            int bite = read8PC();
            Command command = asm.find(bite);
            if (command != null) {
                command.apply(bite, this);
                // каждая операция уменьшает число тактов на
                // прерывание на свою длительность в тактах
                ticks += command.ticks();
                continue;
            }
            
            switch (bite) {
                // DCX_R
//                case 11: {
//                    BC(dec16(BC()));
//                    ticks += 6;
//                    break;
//                }
//                case 27: {
//                    DE(dec16(DE()));
//                    ticks += 6;
//                    break;
//                }
//                case 43: {
//                    HL(dec16(HL()));
//                    ticks += 6;
//                    break;
//                }
//                case 59: {
//                    SP(dec16(SP()));
//                    ticks += 6;
//                    break;
//                }

                // INR_R
//                case 4: {
//                    B(inc8(B()));
//                    ticks += 4;
//                    break;
//                }
//                case 12: {
//                    C(inc8(C()));
//                    ticks += 4;
//                    break;
//                }
//                case 20: {
//                    D(inc8(D()));
//                    ticks += 4;
//                    break;
//                }
//                case 28: {
//                    E(inc8(E()));
//                    ticks += 4;
//                    break;
//                }
//                case 36: {
//                    H(inc8(H()));
//                    ticks += 4;
//                    break;
//                }
//                case 44: {
//                    L(inc8(L()));
//                    ticks += 4;
//                    break;
//                }
//                case 52: {
//                    write8(HL(), inc8(read8(HL())));
//                    ticks += 11;
//                    break;
//                }
//                case 60: {
//                    A(inc8(A()));
//                    ticks += 4;
//                    break;
//                }

                // DCR_R
//                case 5: {
//                    B(dec8(B()));
//                    ticks += 4;
//                    break;
//                }
//                case 13: {
//                    C(dec8(C()));
//                    ticks += 4;
//                    break;
//                }
//                case 21: {
//                    D(dec8(D()));
//                    ticks += 4;
//                    break;
//                }
//                case 29: {
//                    E(dec8(E()));
//                    ticks += 4;
//                    break;
//                }
//                case 37: {
//                    H(dec8(H()));
//                    ticks += 4;
//                    break;
//                }
//                case 45: {
//                    L(dec8(L()));
//                    ticks += 4;
//                    break;
//                }
//                case 53: {
//                    int hl = HL();
//                    write8(hl, dec8(read8(hl)));
//                    ticks += 11;
//                    break;
//                }
//                case 61: {
//                    A(dec8(A()));
//                    ticks += 4;
//                    break;
//                }

                // MVI_R_XX
//                case 6: {
//                    B(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 14: {
//                    C(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 22: {
//                    D(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 30: {
//                    E(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 38: {
//                    H(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 46: {
//                    L(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 54: {
//                    write8(HL(), read8PC());
//                    ticks += 10;
//                    break;
//                }
//                case 62: {
//                    A(read8PC());
//                    ticks += 7;
//                    break;
//                }


//                case 7: {
//                    rlc_a();
//                    ticks += 4;
//                    break;
//                }
//                case 15: {
//                    rrc_a();
//                    ticks += 4;
//                    break;
//                }
//                case 23: {
//                    rl_a();
//                    ticks += 4;
//                    break;
//                }
//                case 31: {
//                    rr_a();
//                    ticks += 4;
//                    break;
//                }
//                case 39: {
//                    daa_a();
//                    ticks += 4;
//                    break;
//                }
//                case 47: {
//                    cpl_a();
//                    ticks += 4;
//                    break;
//                }
//                case 55: {
//                    scf();
//                    ticks += 4;
//                    break;
//                }
//                case 63: {
//                    ccf();
//                    ticks += 4;
//                    break;
//                }


//                case 64: {
//                    ticks += 4;
//                    break;
//                }
//                case 65: {
//                    B(C());
//                    ticks += 4;
//                    break;
//                }
//                case 66: {
//                    B(D());
//                    ticks += 4;
//                    break;
//                }
//                case 67: {
//                    B(E());
//                    ticks += 4;
//                    break;
//                }
//                case 68: {
//                    B(H());
//                    ticks += 4;
//                    break;
//                }
//                case 69: {
//                    B(L());
//                    ticks += 4;
//                    break;
//                }
//                case 70: {
//                    B(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 71: {
//                    B(A());
//                    ticks += 4;
//                    break;
//                }


//                case 72: {
//                    C(B());
//                    ticks += 4;
//                    break;
//                }
//                case 73: {
//                    ticks += 4;
//                    break;
//                }
//                case 74: {
//                    C(D());
//                    ticks += 4;
//                    break;
//                }
//                case 75: {
//                    C(E());
//                    ticks += 4;
//                    break;
//                }
//                case 76: {
//                    C(H());
//                    ticks += 4;
//                    break;
//                }
//                case 77: {
//                    C(L());
//                    ticks += 4;
//                    break;
//                }
//                case 78: {
//                    C(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 79: {
//                    C(A());
//                    ticks += 4;
//                    break;
//                }
//
//
//                case 80: {
//                    D(B());
//                    ticks += 4;
//                    break;
//                }
//                case 81: {
//                    D(C());
//                    ticks += 4;
//                    break;
//                }
//                case 82: {
//                    ticks += 4;
//                    break;
//                }
//                case 83: {
//                    D(E());
//                    ticks += 4;
//                    break;
//                }
//                case 84: {
//                    D(H());
//                    ticks += 4;
//                    break;
//                }
//                case 85: {
//                    D(L());
//                    ticks += 4;
//                    break;
//                }
//                case 86: {
//                    D(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 87: {
//                    D(A());
//                    ticks += 4;
//                    break;
//                }
//
//
//                case 88: {
//                    E(B());
//                    ticks += 4;
//                    break;
//                }
//                case 89: {
//                    E(C());
//                    ticks += 4;
//                    break;
//                }
//                case 90: {
//                    E(D());
//                    ticks += 4;
//                    break;
//                }
//                case 91: {
//                    ticks += 4;
//                    break;
//                }
//                case 92: {
//                    E(H());
//                    ticks += 4;
//                    break;
//                }
//                case 93: {
//                    E(L());
//                    ticks += 4;
//                    break;
//                }
//                case 94: {
//                    E(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 95: {
//                    E(A());
//                    ticks += 4;
//                    break;
//                }
//
//
//                case 96: {
//                    H(B());
//                    ticks += 4;
//                    break;
//                }
//                case 97: {
//                    H(C());
//                    ticks += 4;
//                    break;
//                }
//                case 98: {
//                    H(D());
//                    ticks += 4;
//                    break;
//                }
//                case 99: {
//                    H(E());
//                    ticks += 4;
//                    break;
//                }
//                case 100: {
//                    ticks += 4;
//                    break;
//                }
//                case 101: {
//                    H(L());
//                    ticks += 4;
//                    break;
//                }
//                case 102: {
//                    H(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 103: {
//                    H(A());
//                    ticks += 4;
//                    break;
//                }
//
//
//                case 104: {
//                    L(B());
//                    ticks += 4;
//                    break;
//                }
//                case 105: {
//                    L(C());
//                    ticks += 4;
//                    break;
//                }
//                case 106: {
//                    L(D());
//                    ticks += 4;
//                    break;
//                }
//                case 107: {
//                    L(E());
//                    ticks += 4;
//                    break;
//                }
//                case 108: {
//                    L(H());
//                    ticks += 4;
//                    break;
//                }
//                case 109: {
//                    ticks += 4;
//                    break;
//                }
//                case 110: {
//                    L(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 111: {
//                    L(A());
//                    ticks += 4;
//                    break;
//                }
//
//
//                case 112: {
//                    write8(HL(), B());
//                    ticks += 7;
//                    break;
//                }
//                case 113: {
//                    write8(HL(), C());
//                    ticks += 7;
//                    break;
//                }
//                case 114: {
//                    write8(HL(), D());
//                    ticks += 7;
//                    break;
//                }
//                case 115: {
//                    write8(HL(), E());
//                    ticks += 7;
//                    break;
//                }
//                case 116: {
//                    write8(HL(), H());
//                    ticks += 7;
//                    break;
//                }
//                case 117: {
//                    write8(HL(), L());
//                    ticks += 7;
//                    break;
//                }
//                case 119: {
//                    write8(HL(), A());
//                    ticks += 7;
//                    break;
//                }
//
//                case 120: {
//                    A(B());
//                    ticks += 4;
//                    break;
//                }
//                case 121: {
//                    A(C());
//                    ticks += 4;
//                    break;
//                }
//                case 122: {
//                    A(D());
//                    ticks += 4;
//                    break;
//                }
//                case 123: {
//                    A(E());
//                    ticks += 4;
//                    break;
//                }
//                case 124: {
//                    A(H());
//                    ticks += 4;
//                    break;
//                }
//                case 125: {
//                    A(L());
//                    ticks += 4;
//                    break;
//                }
//                case 126: {
//                    A(read8(HL()));
//                    ticks += 7;
//                    break;
//                }


                case 118: {
                    int haltsToInterrupt = (-ticks - 1) / 4 + 1;
                    ticks += haltsToInterrupt * 4;
                    break;
                }

//                case 127: {
//                    ticks += 4;
//                    break;
//                }


//                case 128: {
//                    add_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 129: {
//                    add_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 130: {
//                    add_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 131: {
//                    add_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 132: {
//                    add_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 133: {
//                    add_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 134: {
//                    add_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 135: {
//                    add_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 136: {
//                    adc_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 137: {
//                    adc_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 138: {
//                    adc_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 139: {
//                    adc_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 140: {
//                    adc_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 141: {
//                    adc_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 142: {
//                    adc_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 143: {
//                    adc_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 144: {
//                    sub_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 145: {
//                    sub_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 146: {
//                    sub_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 147: {
//                    sub_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 148: {
//                    sub_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 149: {
//                    sub_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 150: {
//                    sub_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 151: {
//                    sub_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 152: {
//                    sbc_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 153: {
//                    sbc_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 154: {
//                    sbc_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 155: {
//                    sbc_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 156: {
//                    sbc_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 157: {
//                    sbc_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 158: {
//                    sbc_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 159: {
//                    sbc_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 160: {
//                    and_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 161: {
//                    and_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 162: {
//                    and_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 163: {
//                    and_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 164: {
//                    and_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 165: {
//                    and_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 166: {
//                    and_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 167: {
//                    and_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 168: {
//                    xor_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 169: {
//                    xor_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 170: {
//                    xor_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 171: {
//                    xor_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 172: {
//                    xor_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 173: {
//                    xor_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 174: {
//                    xor_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 175: {
//                    xor_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 176: {
//                    or_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 177: {
//                    or_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 178: {
//                    or_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 179: {
//                    or_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 180: {
//                    or_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 181: {
//                    or_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 182: {
//                    or_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 183: {
//                    or_a(A());
//                    ticks += 4;
//                    break;
//                }


//                case 184: {
//                    cp_a(B());
//                    ticks += 4;
//                    break;
//                }
//                case 185: {
//                    cp_a(C());
//                    ticks += 4;
//                    break;
//                }
//                case 186: {
//                    cp_a(D());
//                    ticks += 4;
//                    break;
//                }
//                case 187: {
//                    cp_a(E());
//                    ticks += 4;
//                    break;
//                }
//                case 188: {
//                    cp_a(H());
//                    ticks += 4;
//                    break;
//                }
//                case 189: {
//                    cp_a(L());
//                    ticks += 4;
//                    break;
//                }
//                case 190: {
//                    cp_a(read8(HL()));
//                    ticks += 7;
//                    break;
//                }
//                case 191: {
//                    cp_a(A());
//                    ticks += 4;
//                    break;
//                }


                case 192: {
                    if (!tz()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 200: {
                    if (tz()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 208: {
                    if (!tc()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 216: {
                    if (tc()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 224: {
                    if (!tp()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 232: {
                    if (tp()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 240: {
                    if (!ts()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 248: {
                    if (ts()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }


                case 193: {
                    BC(pop16());
                    ticks += 10;
                    break;
                }
                case 201: {
                    popPC();
                    ticks += 10;
                    break;
                }
                case 209: {
                    DE(pop16());
                    ticks += 10;
                    break;
                }
                case 225: {
                    HL(pop16());
                    ticks += 10;
                    break;
                }
                case 233: {
                    PC(HL());
                    ticks += 4;
                    break;
                }
                case 241: {
                    AF(pop16());
                    ticks += 10;
                    break;
                }
                case 249: {
                    SP(HL());
                    ticks += 6;
                    break;
                }


                case 194: {
                    if (!tz()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 202: {
                    if (tz()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 210: {
                    if (!tc()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 218: {
                    if (tc()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 226: {
                    if (!tp()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 234: {
                    if (tp()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 242: {
                    if (!ts()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 250: {
                    if (ts()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                

                case 195: {
                    PC(read16(PC()));
                    ticks += 10;
                    break;
                }
                case 211: {
                    data.out8(read8PC(), A());
                    ticks += 11;
                    break;
                }
                case 219: {
                    A(inb((A() << 8) | read8PC()));
                    ticks += 11;
                    break;
                }
                case 227: {
                    int t = HL();
                    int sp = SP();
                    HL(read16(sp));
                    write16(sp, t);
                    ticks += 19;
                    break;
                }
                case 235: {
                    int t = HL();
                    HL(DE());
                    DE(t);
                    ticks += 4;
                    break;
                }
                case 243: {
                    ticks += 4;
                    break;
                }
                case 251: {
                    ticks += 4;
                    break;
                }


                case 196: {
                    if (!tz()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 204: {
                    if (tz()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 212: {
                    if (!tc()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 220: {
                    if (tc()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 228: {
                    if (!tp()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 236: {
                    if (tp()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 244: {
                    if (!ts()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }
                case 252: {
                    if (ts()) {
                        int t = read16PC();
                        pushPC();
                        PC(t);
                        ticks += 17;
                    } else {
                        PC(word(PC() + 2));
                        ticks += 10;
                    }
                    break;
                }


                case 197: {
                    push16(BC());
                    ticks += 11;
                    break;
                }
                case 205: {
                    int t = read16PC();
                    pushPC();
                    PC(t);
                    ticks += 17;
                    break;
                }
                case 213: {
                    push16(DE());
                    ticks += 11;
                    break;
                }
                case 229: {
                    push16(HL());
                    ticks += 11;
                    break;
                }
                case 245: {
                    push16(AF());
                    ticks += 11;
                    break;
                }


//                case 198: {
//                    add_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 206: {
//                    adc_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 214: {
//                    sub_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 222: {
//                    sbc_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 230: {
//                    and_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 238: {
//                    xor_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 246: {
//                    or_a(read8PC());
//                    ticks += 7;
//                    break;
//                }
//                case 254: {
//                    cp_a(read8PC());
//                    ticks += 7;
//                    break;
//                }


                case 199: {
                    pushPC();
                    PC(0);
                    ticks += 11;
                    break;
                }
                case 207: {
                    pushPC();
                    PC(8);
                    ticks += 11;
                    break;
                }
                case 215: {
                    pushPC();
                    PC(16);
                    ticks += 11;
                    break;
                }
                case 223: {
                    pushPC();
                    PC(24);
                    ticks += 11;
                    break;
                }
                case 231: {
                    pushPC();
                    PC(32);
                    ticks += 11;
                    break;
                }
                case 239: {
                    pushPC();
                    PC(40);
                    ticks += 11;
                    break;
                }
                case 247: {
                    pushPC();
                    PC(48);
                    ticks += 11;
                    break;
                }
                case 255: {
                    pushPC();
                    PC(56);
                    ticks += 11;
                    break;
                }
            }
        }
    }

//    private void adc_a(int b) {
//        int a = A();
//        int c = tci();
//        int wans = a + b + c;
//        int ans = lo(wans);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tc((wans & x100) != 0);
//        tp(((a ^ ~b) & (a ^ ans) & x80) != 0);
//        th((((a & x0F) + (b & x0F) + c) & T4h) != 0);
//
//        A(ans);
//    }

//    private void add_a(int b) {
//        int a = A();
//        int wans = a + b;
//        int ans = lo(wans);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tc((wans & x100) != 0);
//        tp(((a ^ ~b) & (a ^ ans) & x80) != 0);
//        th((((a & x0F) + (b & x0F)) & T4h) != 0);
//
//        A(ans);
//    }

//    private void sbc_a(int b) {
//        int a = A();
//        int c = tci();
//        int wans = a - b - c;
//        int ans = lo(wans);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tc((wans & x100) != 0);
//        tp(((a ^ b) & (a ^ ans) & x80) != 0);
//        th((((a & x0F) - (b & x0F) - c) & T4h) != 0);
//
//        A(ans);
//    }

//    private void sub_a(int b) {
//        int a = A();
//        int wans = a - b;
//        int ans = lo(wans);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tc((wans & x100) != 0);
//        tp(((a ^ b) & (a ^ ans) & x80) != 0);
//        th((((a & x0F) - (b & x0F)) & T4h) != 0);
//
//        A(ans);
//    }

//    private void rlc_a() {
//        int ans = A();
//        boolean c = (ans & x80) != 0;
//
//        if (c) {
//            ans = (ans << 1) | x01;
//        } else {
//            ans <<= 1;
//        }
//        ans = lo(ans);
//
//        th(false);
//        tc(c);
//
//        A(ans);
//    }

//    private void rrc_a() {
//        int ans = A();
//        boolean c = (ans & x01) != 0;
//
//        if (c) {
//            ans = (ans >> 1) | x80;
//        } else {
//            ans >>= 1;
//        }
//
//        th(false);
//        tc(c);
//
//        A(ans);
//    }

//    private void rl_a() {
//        int ans = A();
//        boolean c = (ans & x80) != 0;
//
//        if (tc()) {
//            ans = (ans << 1) | x01;
//        } else {
//            ans <<= 1;
//        }
//
//        ans = lo(ans);
//
//        th(false);
//        tc(c);
//
//        A(ans);
//    }

//    private void rr_a() {
//        int ans = A();
//        boolean c = (ans & x01) != 0;
//
//        if (tc()) {
//            ans = (ans >> 1) | x80;
//        } else {
//            ans >>= 1;
//        }
//
//        th(false);
//        tc(c);
//
//        A(ans);
//    }

//    private void cp_a(int b) {
//        int a = A();
//        int wans = a - b;
//        int ans = lo(wans);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tc((wans & x100) != 0);
//        th((((a & x0F) - (b & x0F)) & T4h) != 0);
//        tp(((a ^ b) & (a ^ ans) & x80) != 0);
//    }

//    private void and_a(int b) {
//        int ans = A() & b;
//
//        ts((ans & T7s) != 0);
//        th(true);
//        tp(parity[ans]);
//        tz(ans == 0);
//        tc(false);
//
//        A(ans);
//    }

//    private void or_a(int b) {
//        int ans = A() | b;
//
//        ts((ans & T7s) != 0);
//        th(false);
//        tp(parity[ans]);
//        tz(ans == 0);
//        tc(false);
//
//        A(ans);
//    }

//    private void xor_a(int b) {
//        int ans = lo(A() ^ b);
//
//        ts((ans & T7s) != 0);
//        th(false);
//        tp(parity[ans]);
//        tz(ans == 0);
//        tc(false);
//
//        A(ans);
//    }

//    private void cpl_a() {
//        int ans = A() ^ BITE;
//
//        th(true);
//
//        A(ans);
//    }

//    private void daa_a() {
//        int ans = A();
//        int incr = 0;
//        boolean carry = tc();
//
//        if (th() || (ans & x0F) > x09) {
//            incr |= x06;
//        }
//        if (carry || (ans > x9F) || ((ans > x8F) && ((ans & x0F) > x09))) {
//            incr |= x60;
//        }
//        if (ans > x99) {
//            carry = true;
//        }
//        sub_a(incr);
//
//        ans = A();
//
//        tc(carry);
//        tp(parity[ans]);
//    }

//    private void scf() {
//        th(false);
//        tc(true);
//    }

//    private void ccf() {
//        tc(!tc());
//    }

    public Assembler asm() {
        return asm;
    }


//    private int dec8(int ans) {
//        boolean p = (ans == x80);
//        boolean h = (((ans & x0F) - 1) & T4h) != 0;
//        ans = lo(ans - 1);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tp(p);
//        th(h);
//
//        return ans;
//    }

//    private int inc8(int ans) {
//        boolean p = (ans == x7F);
//        boolean h = (((ans & x0F) + 1) & T4h) != 0;
//        ans = lo(ans + 1);
//
//        ts((ans & T7s) != 0);
//        tz(ans == 0);
//        tp(p);
//        th(h);
//
//        return ans;
//    }
}