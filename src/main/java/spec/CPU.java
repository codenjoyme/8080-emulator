package spec;

/**
 * @(#)Z80.java 1.1 27/04/97 Adam Davidson & Andrew Pollard
 */

import static spec.Constants.*;
import static spec.WordMath.*;

/**
 * <p>The Z80 class emulates the Zilog Z80 microprocessor.</p>
 *
 * @author <A HREF="http://www.odie.demon.co.uk/spectrum">Adam Davidson & Andrew Pollard</A>
 * @version 1.1 27 Apr 1997
 */

public class CPU {

    private final Accessor accessor;

    public CPU(double clockFrequencyInMHz, Accessor accessor) {
        // Количество тактов на 1 прерывание, которое происходит 50 раз в секунду.
        // 1000000/50 раз в секунду
        interruptTimeout = (int) ((clockFrequencyInMHz * 1e6) / 50);
        this.accessor = accessor;
    }

    private int interruptTimeout;

    private static final int T0c = x01; // Разряд Tc =1, если был перенос или заем
    private static final int T11 = x02; // Всегда 1
    private static final int T2p = x04; // Разряд Tp = 1, если число единиц в результате четно
    private static final int T30 = x08; // Всегда 0
    private static final int T4h = x10; // Разряд Th = 1, если был перенос из старшей тетрады в младшую
    private static final int T50 = x20; // Всегда 0
    private static final int T6z = x40; // Разряд tZ = 1, если результат = 0
    private static final int T7s = x80; // Разряд Ts = 1, если результат отрицательный (первый бит результата = 1)

    private static final boolean[] parity = new boolean[256];

    static {
        for (int i = 0; i < 256; i++) {
            boolean p = true;
            for (int j = 0; j < 8; j++) {
                if ((i & (1 << j)) != 0) {
                    p = !p;
                }
            }
            parity[i] = p;
        }
    }

    /**
     * Main registers
     */
    private int A = 0;
    private int HL = 0;
    private int BC = 0;
    private int DE = 0;

    private boolean ts = false;
    private boolean tz = false;
    private boolean th = false;
    private boolean tp = false;
    private boolean tc = false;

    /**
     * Stack Pointer and Program Counter
     */
    private int SP = 0;
    private int PC = START_POINT;

    /**
     * 16 bit register access
     */
    private int AF() {
        return MERGE(A, F());
    }

    private void AF(int word) {
        A = word >> 8;
        F(LO(word));
    }

    private int F() {
        return (ts ? T7s : 0) |
                (tz ? T6z : 0) |
                (false ? T50 : 0) |
                (th ? T4h : 0) |
                (false ? T30 : 0) |
                (tp ? T2p : 0) |
                (true ? T11 : 0) |
                (tc ? T0c : 0);
    }

    private void F(int bite) {
        ts = (bite & T7s) != 0;
        tz = (bite & T6z) != 0;
        th = (bite & T4h) != 0;
        tp = (bite & T2p) != 0;
        tc = (bite & T0c) != 0;
    }

    private int B() {
        return HI(BC);
    }

    private void B(int bite) {
        BC = LO_MERGE(bite, BC);
    }

    private int C() {
        return LO(BC);
    }

    private void C(int bite) {
        BC = HI_MERGE(BC, bite);
    }

    private int D() {
        return HI(DE);
    }

    private void D(int bite) {
        DE = LO_MERGE(bite, DE);
    }

    private int E() {
        return LO(DE);
    }

    private void E(int bite) {
        DE = HI_MERGE(DE, bite);
    }

    private int H() {
        return HI(HL);
    }

    private void H(int bite) {
        HL = LO_MERGE(bite, HL);
    }

    private int L() {
        return LO(HL);
    }

    private void L(int bite) {
        HL = HI_MERGE(HL, bite);
    }

    // его часто суммируют, а потому тут этот метод
    private int Tci() {
        return tc ? 1 : 0;
    }

    private int peekw(int addr) {
        int t = peekb(addr);
        addr++;
        return t | (peekb(WORD(addr)) << 8);
    }

    private int peekb(int addr) {
        return accessor.peekb(addr);
    }

    private void pokew(int addr, int word) {
        accessor.pokew(addr, word);
    }

    private void pokeb(int addr, int bite) {
        accessor.pokeb(addr, bite);
    }


    /**
     * Stack access
     */
    private void pushw(int word) {
        SP = WORD(SP - 2);
        pokew(SP, word);
    }


    private int popw() {
        int t = peekb(SP);
        SP = WORD(++SP);
        t |= (peekb(SP) << 8);
        SP = WORD(++SP);
        return t;
    }

    /**
     * Call stack
     */
    private void pushpc() {
        pushw(PC);
    }

    private void poppc() {
        PC = popw();
    }

    /**
     *  Program access
     *  байт из памяти по адресу п-счетчика PC() и увеличение PC()
     */
    private int nxtpcb() {
        int t = peekb(PC);
        PC = WORD(++PC);
        return t;
    }

    /**
     * слово из памяти по адресу п-счетчика PC()  и увеличение PC()
     */
    private int nxtpcw() {
        int t = peekb(PC);
        PC = WORD(++PC);
        t |= (peekb(PC) << 8);
        PC = WORD(++PC);
        return t;
    }

    /**
     * Reset all registers to power on state
     */
    public void reset() {
        PC = START_POINT;
        SP = 0;

        A = 0;
        F(0);
        BC = 0;
        DE = 0;
        HL = 0;
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

    /**
     * Interrupt handlers
     */
    private static boolean interruptTriggered(int tstates) {                       // tstates = local_tstates
        return tstates >= 0;  // >= если tstates  больше или равно = 0 -> true иначе false
    }

    /**
     * Основной цикл выполнения кода
     */
    public void execute() {
        // в начале local_tstates = числу тактов на прерывание.
        int ticks = -interruptTimeout;

        // цикл выборки/выполнения
        while (true) {
            if (interruptTriggered(ticks)) {
              // число тактов на прерывание исчерпано - вызываем прерывание.
                accessor.interrupt();
                ticks -= interruptTimeout;
            }
            // local_tstates = числу тактов на прерывание + время прерывания.

            switch (nxtpcb()) { // байт из памяти по адресу п-счетчика PC()
                               // и разбор этого байта как кода
                case 0:    /* NOP */ {
                    ticks += 4;  // каждая операция уменьшает число тактов на прерывание
                    break;                   // на свою длительность в тактах
                }
                case 16:    /* DJNZ dis */ {
                    int b;

                    B(b = qdec8(B()));
                    if (b != 0) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC = WORD(PC + d);
                        ticks += 13;
                    } else {
                        PC = inc16(PC);
                        ticks += 8;
                    }
                    break;
                }
                case 24: /* JR dis */ {
                    byte d = (byte) nxtpcb();  // байт из памяти по адресу п-счетчика PC()
                    PC = WORD(PC + d);
                    ticks += 12;
                    break;
                }
                /* JR cc,dis */
                case 32:    /* JR NZ,dis */ {
                    if (!tz) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC = WORD(PC + d);
                        ticks += 12;
                    } else {
                        PC = inc16(PC);
                        ticks += 7;
                    }
                    break;
                }
                case 40:    /* JR Z,dis */ {
                    if (tz) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC = WORD(PC + d);
                        ticks += 12;
                    } else {
                        PC = inc16(PC);
                        ticks += 7;
                    }
                    break;
                }
                case 48:    /* JR NC,dis */ {
                    if (!tc) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC = WORD(PC + d);
                        ticks += 12;
                    } else {
                        PC = inc16(PC);
                        ticks += 7;
                    }
                    break;
                }
                case 56:    /* JR C,dis */ {
                    if (tc) {
                        byte d = (byte) nxtpcb();  // байт из памяти по адресу п-счетчика PC()
                        PC = WORD(PC + d);
                        ticks += 12;
                    } else {
                        PC = inc16(PC);
                        ticks += 7;
                    }
                    break;
                }

                /* LD rr,nn / ADD HL,rr */
                case 1:    /* LD BC(),nn */ {
                    // слово из памяти по адресу п-счетчика PC()
                    BC = nxtpcw();
                    ticks += 10;
                    break;
                }
                case 9:    /* ADD HL,BC */ {
                    HL = add16(HL, BC);
                    ticks += 11;
                    break;
                }
                case 17:    /* LD DE,nn */ {
                    // слово из памяти по адресу п-счетчика PC()
                    DE = nxtpcw();
                    ticks += 10;
                    break;
                }
                case 25:    /* ADD HL,DE */ {
                    HL = add16(HL, DE);
                    ticks += 11;
                    break;
                }
                case 33:    /* LD HL,nn */ {
                    // слово из памяти по адресу п-счетчика PC()
                    HL = nxtpcw();
                    ticks += 10;
                    break;
                }
                case 41:    /* ADD HL,HL */ {
                    int hl = HL;
                    HL = add16(hl, hl);
                    ticks += 11;
                    break;
                }
                case 49:    /* LD SP,nn */ {
                    // слово из памяти по адресу п-счетчика PC()
                    SP = nxtpcw();
                    ticks += 10;
                    break;
                }
                case 57:    /* ADD HL,SP */ {
                    HL = add16(HL, SP);
                    ticks += 11;
                    break;
                }

                /* LD (**),A/A,(**) */
                case 2:    /* LD (BC),A */ {
                    pokeb(BC, A);
                    ticks += 7;
                    break;
                }
                case 10:    /* LD A,(BC) */ {
                    A = peekb(BC);
                    ticks += 7;
                    break;
                }
                case 18:    /* LD (DE),A */ {
                    pokeb(DE, A);
                    ticks += 7;
                    break;
                }
                case 26:    /* LD A,(DE) */ {
                    A = peekb(DE);
                    ticks += 7;
                    break;
                }
                case 34:    /* LD (nn),HL */ {
                    pokew(nxtpcw(), HL);
                    ticks += 16;
                    break;
                }
                case 42:    /* LD HL,(nn) */ {
                    HL = peekw(nxtpcw());
                    ticks += 16;
                    break;
                }
                case 50:    /* LD (nn),A */ {
                    pokeb(nxtpcw(), A);
                    ticks += 13;
                    break;
                }
                case 58:    /* LD A,(nn) */ {
                    A = peekb(nxtpcw());
                    ticks += 13;
                    break;
                }

                /* INC/DEC * */
                case 3:    /* INC BC */ {
                    BC = inc16(BC);
                    ticks += 6;
                    break;
                }
                case 11:    /* DEC BC */ {
                    BC = dec16(BC);
                    ticks += 6;
                    break;
                }
                case 19:    /* INC DE */ {
                    DE = inc16(DE);
                    ticks += 6;
                    break;
                }
                case 27:    /* DEC DE */ {
                    DE = dec16(DE);
                    ticks += 6;
                    break;
                }
                case 35:    /* INC HL */ {
                    HL = inc16(HL);
                    ticks += 6;
                    break;
                }
                case 43:    /* DEC HL */ {
                    HL = dec16(HL);
                    ticks += 6;
                    break;
                }
                case 51:    /* INC SP */ {
                    SP = inc16(SP);
                    ticks += 6;
                    break;
                }
                case 59:    /* DEC SP */ {
                    SP = dec16(SP);
                    ticks += 6;
                    break;
                }

                /* INC * */
                case 4:    /* INC B */ {
                    B(inc8(B()));
                    ticks += 4;
                    break;
                }
                case 12:    /* INC C */ {
                    C(inc8(C()));
                    ticks += 4;
                    break;
                }
                case 20:    /* INC D */ {
                    D(inc8(D()));
                    ticks += 4;
                    break;
                }
                case 28:    /* INC E */ {
                    E(inc8(E()));
                    ticks += 4;
                    break;
                }
                case 36:    /* INC H */ {
                    H(inc8(H()));
                    ticks += 4;
                    break;
                }
                case 44:    /* INC L */ {
                    L(inc8(L()));
                    ticks += 4;
                    break;
                }
                case 52:    /* INC (HL) */ {
                    int hl = HL;
                    pokeb(hl, inc8(peekb(hl)));
                    ticks += 11;
                    break;
                }
                case 60:    /* INC A() */ {
                    A = inc8(A);
                    ticks += 4;
                    break;
                }

                /* DEC * */
                case 5:    /* DEC B */ {
                    B(dec8(B()));
                    ticks += 4;
                    break;
                }
                case 13:    /* DEC C */ {
                    C(dec8(C()));
                    ticks += 4;
                    break;
                }
                case 21:    /* DEC D */ {
                    D(dec8(D()));
                    ticks += 4;
                    break;
                }
                case 29:    /* DEC E */ {
                    E(dec8(E()));
                    ticks += 4;
                    break;
                }
                case 37:    /* DEC H */ {
                    H(dec8(H()));
                    ticks += 4;
                    break;
                }
                case 45:    /* DEC L */ {
                    L(dec8(L()));
                    ticks += 4;
                    break;
                }
                case 53:    /* DEC (HL) */ {
                    int hl = HL;
                    pokeb(hl, dec8(peekb(hl)));
                    ticks += 11;
                    break;
                }
                case 61:    /* DEC A() */ {
                    A = dec8(A);
                    ticks += 4;
                    break;
                }

                /* LD *,N */
                case 6:    /* LD B,n */ {
                    B(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 14:    /* LD C,n */ {
                    C(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 22:    /* LD D,n */ {
                    D(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 30:    /* LD E,n */ {
                    E(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 38:    /* LD H,n */ {
                    H(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 46:    /* LD L,n */ {
                    L(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 54:    /* LD (HL),n */ {
                    pokeb(HL, nxtpcb());
                    ticks += 10;
                    break;
                }
                case 62:    /* LD A,n */ {
                    A = nxtpcb();
                    ticks += 7;
                    break;
                }

                /* R**A */
                case 7: /* RLCA */ {
                    rlc_a();
                    ticks += 4;
                    break;
                }
                case 15: /* RRCA */ {
                    rrc_a();
                    ticks += 4;
                    break;
                }
                case 23: /* RLA */ {
                    rl_a();
                    ticks += 4;
                    break;
                }
                case 31: /* RRA */ {
                    rr_a();
                    ticks += 4;
                    break;
                }
                case 39: /* DAA */ {
                    daa_a();
                    ticks += 4;
                    break;
                }
                case 47: /* CPL */ {
                    cpl_a();
                    ticks += 4;
                    break;
                }
                case 55: /* SCF */ {
                    scf();
                    ticks += 4;
                    break;
                }
                case 63: /* CCF */ {
                    ccf();
                    ticks += 4;
                    break;
                }

                /* LD B,* */
                case 64:    /* LD B,B */ {
                    ticks += 4;
                    break;
                }
                case 65:    /* LD B,C */ {
                    B(C());
                    ticks += 4;
                    break;
                }
                case 66:    /* LD B,D */ {
                    B(D());
                    ticks += 4;
                    break;
                }
                case 67:    /* LD B,E */ {
                    B(E());
                    ticks += 4;
                    break;
                }
                case 68:    /* LD B,H */ {
                    B(H());
                    ticks += 4;
                    break;
                }
                case 69:    /* LD B,L */ {
                    B(L());
                    ticks += 4;
                    break;
                }
                case 70:    /* LD B,(HL) */ {
                    B(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 71:    /* LD B,A */ {
                    B(A);
                    ticks += 4;
                    break;
                }

                /* LD C,* */
                case 72:    /* LD C,B */ {
                    C(B());
                    ticks += 4;
                    break;
                }
                case 73:    /* LD C,C */ {
                    ticks += 4;
                    break;
                }
                case 74:    /* LD C,D */ {
                    C(D());
                    ticks += 4;
                    break;
                }
                case 75:    /* LD C,E */ {
                    C(E());
                    ticks += 4;
                    break;
                }
                case 76:    /* LD C,H */ {
                    C(H());
                    ticks += 4;
                    break;
                }
                case 77:    /* LD C,L */ {
                    C(L());
                    ticks += 4;
                    break;
                }
                case 78:    /* LD C,(HL) */ {
                    C(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 79:    /* LD C,A */ {
                    C(A);
                    ticks += 4;
                    break;
                }

                /* LD D,* */
                case 80:    /* LD D,B */ {
                    D(B());
                    ticks += 4;
                    break;
                }
                case 81:    /* LD D,C */ {
                    D(C());
                    ticks += 4;
                    break;
                }
                case 82:    /* LD D,D */ {
                    ticks += 4;
                    break;
                }
                case 83:    /* LD D,E */ {
                    D(E());
                    ticks += 4;
                    break;
                }
                case 84:    /* LD D,H */ {
                    D(H());
                    ticks += 4;
                    break;
                }
                case 85:    /* LD D,L */ {
                    D(L());
                    ticks += 4;
                    break;
                }
                case 86:    /* LD D,(HL) */ {
                    D(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 87:    /* LD D,A */ {
                    D(A);
                    ticks += 4;
                    break;
                }

                /* LD E,* */
                case 88:    /* LD E,B */ {
                    E(B());
                    ticks += 4;
                    break;
                }
                case 89:    /* LD E,C */ {
                    E(C());
                    ticks += 4;
                    break;
                }
                case 90:    /* LD E,D */ {
                    E(D());
                    ticks += 4;
                    break;
                }
                case 91:    /* LD E,E */ {
                    ticks += 4;
                    break;
                }
                case 92:    /* LD E,H */ {
                    E(H());
                    ticks += 4;
                    break;
                }
                case 93:    /* LD E,L */ {
                    E(L());
                    ticks += 4;
                    break;
                }
                case 94:    /* LD E,(HL) */ {
                    E(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 95:    /* LD E,A */ {
                    E(A);
                    ticks += 4;
                    break;
                }

                /* LD H,* */
                case 96:    /* LD H,B */ {
                    H(B());
                    ticks += 4;
                    break;
                }
                case 97:    /* LD H,C */ {
                    H(C());
                    ticks += 4;
                    break;
                }
                case 98:    /* LD H,D */ {
                    H(D());
                    ticks += 4;
                    break;
                }
                case 99:    /* LD H,E */ {
                    H(E());
                    ticks += 4;
                    break;
                }
                case 100: /* LD H,H */ {
                    ticks += 4;
                    break;
                }
                case 101:    /* LD H,L */ {
                    H(L());
                    ticks += 4;
                    break;
                }
                case 102:    /* LD H,(HL) */ {
                    H(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 103:    /* LD H,A */ {
                    H(A);
                    ticks += 4;
                    break;
                }

                /* LD L,* */
                case 104:    /* LD L,B */ {
                    L(B());
                    ticks += 4;
                    break;
                }
                case 105:    /* LD L,C */ {
                    L(C());
                    ticks += 4;
                    break;
                }
                case 106:    /* LD L,D */ {
                    L(D());
                    ticks += 4;
                    break;
                }
                case 107:    /* LD L,E */ {
                    L(E());
                    ticks += 4;
                    break;
                }
                case 108:    /* LD L,H */ {
                    L(H());
                    ticks += 4;
                    break;
                }
                case 109:    /* LD L,L */ {
                    ticks += 4;
                    break;
                }
                case 110:    /* LD L,(HL) */ {
                    L(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 111:    /* LD L,A */ {
                    L(A);
                    ticks += 4;
                    break;
                }

                /* LD (HL),* */
                case 112:    /* LD (HL),B */ {
                    pokeb(HL, B());
                    ticks += 7;
                    break;
                }
                case 113:    /* LD (HL),C */ {
                    pokeb(HL, C());
                    ticks += 7;
                    break;
                }
                case 114:    /* LD (HL),D */ {
                    pokeb(HL, D());
                    ticks += 7;
                    break;
                }
                case 115:    /* LD (HL),E */ {
                    pokeb(HL, E());
                    ticks += 7;
                    break;
                }
                case 116:    /* LD (HL),H */ {
                    pokeb(HL, H());
                    ticks += 7;
                    break;
                }
                case 117:    /* LD (HL),L */ {
                    pokeb(HL, L());
                    ticks += 7;
                    break;
                }
                case 118:    /* HALT */ {
                    int haltsToInterrupt = (-ticks - 1) / 4 + 1;
                    ticks += haltsToInterrupt * 4;
                    break;
                }
                case 119:    /* LD (HL),A */ {
                    pokeb(HL, A);
                    ticks += 7;
                    break;
                }

                /* LD A,* */
                case 120:    /* LD A,B */ {
                    A = B();
                    ticks += 4;
                    break;
                }
                case 121:    /* LD A,C */ {
                    A = C();
                    ticks += 4;
                    break;
                }
                case 122:    /* LD A,D */ {
                    A = D();
                    ticks += 4;
                    break;
                }
                case 123:    /* LD A,E */ {
                    A = E();
                    ticks += 4;
                    break;
                }
                case 124:    /* LD A,H */ {
                    A = H();
                    ticks += 4;
                    break;
                }
                case 125:    /* LD A,L */ {
                    A = L();
                    ticks += 4;
                    break;
                }
                case 126:    /* LD A,(HL) */ {
                    A = peekb(HL);
                    ticks += 7;
                    break;
                }
                case 127:    /* LD A,A */ {
                    ticks += 4;
                    break;
                }

                /* ADD A,* */
                case 128:    /* ADD A,B */ {
                    add_a(B());
                    ticks += 4;
                    break;
                }
                case 129:    /* ADD A,C */ {
                    add_a(C());
                    ticks += 4;
                    break;
                }
                case 130:    /* ADD A,D */ {
                    add_a(D());
                    ticks += 4;
                    break;
                }
                case 131:    /* ADD A,E */ {
                    add_a(E());
                    ticks += 4;
                    break;
                }
                case 132:    /* ADD A,H */ {
                    add_a(H());
                    ticks += 4;
                    break;
                }
                case 133:    /* ADD A,L */ {
                    add_a(L());
                    ticks += 4;
                    break;
                }
                case 134:    /* ADD A,(HL) */ {
                    add_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 135:    /* ADD A,A */ {
                    add_a(A);
                    ticks += 4;
                    break;
                }

                /* ADC A,* */
                case 136:    /* ADC A,B */ {
                    adc_a(B());
                    ticks += 4;
                    break;
                }
                case 137:    /* ADC A,C */ {
                    adc_a(C());
                    ticks += 4;
                    break;
                }
                case 138:    /* ADC A,D */ {
                    adc_a(D());
                    ticks += 4;
                    break;
                }
                case 139:    /* ADC A,E */ {
                    adc_a(E());
                    ticks += 4;
                    break;
                }
                case 140:    /* ADC A,H */ {
                    adc_a(H());
                    ticks += 4;
                    break;
                }
                case 141:    /* ADC A,L */ {
                    adc_a(L());
                    ticks += 4;
                    break;
                }
                case 142:    /* ADC A,(HL) */ {
                    adc_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 143:    /* ADC A,A */ {
                    adc_a(A);
                    ticks += 4;
                    break;
                }

                /* SUB * */
                case 144:    /* SUB B */ {
                    sub_a(B());
                    ticks += 4;
                    break;
                }
                case 145:    /* SUB C */ {
                    sub_a(C());
                    ticks += 4;
                    break;
                }
                case 146:    /* SUB D */ {
                    sub_a(D());
                    ticks += 4;
                    break;
                }
                case 147:    /* SUB E */ {
                    sub_a(E());
                    ticks += 4;
                    break;
                }
                case 148:    /* SUB H */ {
                    sub_a(H());
                    ticks += 4;
                    break;
                }
                case 149:    /* SUB L */ {
                    sub_a(L());
                    ticks += 4;
                    break;
                }
                case 150:    /* SUB (HL) */ {
                    sub_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 151:    /* SUB A() */ {
                    sub_a(A);
                    ticks += 4;
                    break;
                }

                /* SBC A,* */
                case 152:    /* SBC A,B */ {
                    sbc_a(B());
                    ticks += 4;
                    break;
                }
                case 153:    /* SBC A,C */ {
                    sbc_a(C());
                    ticks += 4;
                    break;
                }
                case 154:    /* SBC A,D */ {
                    sbc_a(D());
                    ticks += 4;
                    break;
                }
                case 155:    /* SBC A,E */ {
                    sbc_a(E());
                    ticks += 4;
                    break;
                }
                case 156:    /* SBC A,H */ {
                    sbc_a(H());
                    ticks += 4;
                    break;
                }
                case 157:    /* SBC A,L */ {
                    sbc_a(L());
                    ticks += 4;
                    break;
                }
                case 158:    /* SBC A,(HL) */ {
                    sbc_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 159:    /* SBC A,A */ {
                    sbc_a(A);
                    ticks += 4;
                    break;
                }

                /* AND * */
                case 160:    /* AND B */ {
                    and_a(B());
                    ticks += 4;
                    break;
                }
                case 161:    /* AND C */ {
                    and_a(C());
                    ticks += 4;
                    break;
                }
                case 162:    /* AND D */ {
                    and_a(D());
                    ticks += 4;
                    break;
                }
                case 163:    /* AND E */ {
                    and_a(E());
                    ticks += 4;
                    break;
                }
                case 164:    /* AND H */ {
                    and_a(H());
                    ticks += 4;
                    break;
                }
                case 165:    /* AND L */ {
                    and_a(L());
                    ticks += 4;
                    break;
                }
                case 166:    /* AND (HL) */ {
                    and_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 167:    /* AND A() */ {
                    and_a(A);
                    ticks += 4;
                    break;
                }

                /* XOR * */
                case 168:    /* XOR B */ {
                    xor_a(B());
                    ticks += 4;
                    break;
                }
                case 169:    /* XOR C */ {
                    xor_a(C());
                    ticks += 4;
                    break;
                }
                case 170:    /* XOR D */ {
                    xor_a(D());
                    ticks += 4;
                    break;
                }
                case 171:    /* XOR E */ {
                    xor_a(E());
                    ticks += 4;
                    break;
                }
                case 172:    /* XOR H */ {
                    xor_a(H());
                    ticks += 4;
                    break;
                }
                case 173:    /* XOR L */ {
                    xor_a(L());
                    ticks += 4;
                    break;
                }
                case 174:    /* XOR (HL) */ {
                    xor_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 175:    /* XOR A() */ {
                    xor_a(A);
                    ticks += 4;
                    break;
                }

                /* OR * */
                case 176:    /* OR B */ {
                    or_a(B());
                    ticks += 4;
                    break;
                }
                case 177:    /* OR C */ {
                    or_a(C());
                    ticks += 4;
                    break;
                }
                case 178:    /* OR D */ {
                    or_a(D());
                    ticks += 4;
                    break;
                }
                case 179:    /* OR E */ {
                    or_a(E());
                    ticks += 4;
                    break;
                }
                case 180:    /* OR H */ {
                    or_a(H());
                    ticks += 4;
                    break;
                }
                case 181:    /* OR L */ {
                    or_a(L());
                    ticks += 4;
                    break;
                }
                case 182:    /* OR (HL) */ {
                    or_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 183:    /* OR A() */ {
                    or_a(A);
                    ticks += 4;
                    break;
                }

                /* CP * */
                case 184:    /* CP B */ {
                    cp_a(B());
                    ticks += 4;
                    break;
                }
                case 185:    /* CP C */ {
                    cp_a(C());
                    ticks += 4;
                    break;
                }
                case 186:    /* CP D */ {
                    cp_a(D());
                    ticks += 4;
                    break;
                }
                case 187:    /* CP E */ {
                    cp_a(E());
                    ticks += 4;
                    break;
                }
                case 188:    /* CP H */ {
                    cp_a(H());
                    ticks += 4;
                    break;
                }
                case 189:    /* CP L */ {
                    cp_a(L());
                    ticks += 4;
                    break;
                }
                case 190:    /* CP (HL) */ {
                    cp_a(peekb(HL));
                    ticks += 7;
                    break;
                }
                case 191:    /* CP A() */ {
                    cp_a(A);
                    ticks += 4;
                    break;
                }

                /* RET cc */
                case 192:    /* RET NZ */ {
                    if (!tz) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 200:    /* RET Z */ {
                    if (tz) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 208:    /* RET NC */ {
                    if (!tc) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 216:    /* RET C */ {
                    if (tc) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 224:    /* RET PO */ {
                    if (!tp) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 232:    /* RET PE */ {
                    if (tp) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 240:    /* RET P */ {
                    if (!ts) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 248:    /* RET M */ {
                    if (ts) {
                        poppc();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }

                /* POP,Various */
                case 193:    /* POP BC */ {
                    BC = popw();
                    ticks += 10;
                    break;
                }
                case 201: /* RET */ {
                    poppc();
                    ticks += 10;
                    break;
                }
                case 209:    /* POP DE */ {
                    DE = popw();
                    ticks += 10;
                    break;
                }
                case 0xD9: { //+ ???
                    break;
                }
                case 225:    /* POP HL */ {
                    HL = popw();
                    ticks += 10;
                    break;
                }
                case 233: /* JP (HL) */ {
                    PC = HL;
                    ticks += 4;
                    break;
                }
                case 241:    /* POP AF */ {
                    AF(popw());
                    ticks += 10;
                    break;
                }
                case 249:    /* LD SP,HL */ {
                    SP = HL;
                    ticks += 6;
                    break;
                }

                /* JP cc,nn */
                case 194:    /* JP NZ,nn */ {
                    if (!tz) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 202:    /* JP Z,nn */ {
                    if (tz) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 210:    /* JP NC,nn */ {
                    if (!tc) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 218:    /* JP C,nn */ {
                    if (tc) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 226:    /* JP PO,nn */ {
                    if (!tp) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 234:    /* JP PE,nn */ {
                    if (tp) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 242:    /* JP P,nn */ {
                    if (!ts) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }
                case 250:    /* JP M,nn */ {
                    if (ts) {
                        PC = nxtpcw();
                    } else {
                        PC = WORD(PC + 2);
                    }
                    ticks += 10;
                    break;
                }


                /* Various */
                case 195:    /* JP nn */ {
                    PC = peekw(PC);
                    ticks += 10;
                    break;
                }
                case 0xCB: {  //+ ???
                    break;
                }
                case 211:    /* OUT (n),A */ {
                    accessor.outb(nxtpcb(), A);
                    ticks += 11;
                    break;
                }
                case 219:    /* IN A,(n) */ {
                    A = inb((A << 8) | nxtpcb());
                    ticks += 11;
                    break;
                }
                case 227:    /* EX (SP),HL */ {
                    int t = HL;
                    int sp = SP;
                    HL = peekw(sp);
                    pokew(sp, t);
                    ticks += 19;
                    break;
                }
                case 235:    /* EX DE,HL */ {
                    int t = HL;
                    HL = DE;
                    DE = t;
                    ticks += 4;
                    break;
                }
                case 243:    /* DI */ {
                    ticks += 4;
                    break;
                }
                case 251:    /* EI */ {
                    ticks += 4;
                    break;
                }

                /* CALL cc,nn */
                case 196: /* CALL NZ,nn */ {
                    if (!tz) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 204: /* CALL Z,nn */ {
                    if (tz) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 212: /* CALL NC,nn */ {
                    if (!tc) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 220: /* CALL C,nn */ {
                    if (tc) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 228: /* CALL PO,nn */ {
                    if (!tp) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 236: /* CALL PE,nn */ {
                    if (tp) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 244: /* CALL P,nn */ {
                    if (!ts) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }
                case 252: /* CALL M,nn */ {
                    if (ts) {
                        int t = nxtpcw();
                        pushpc();
                        PC = t;
                        ticks += 17;
                    } else {
                        PC = WORD(PC + 2);
                        ticks += 10;
                    }
                    break;
                }

                /* PUSH,Various */
                case 197:    /* PUSH BC */ {
                    pushw(BC);
                    ticks += 11;
                    break;
                }
                case 205:    /* CALL nn */ {
                    int t = nxtpcw();
                    pushpc();
                    PC = t;
                    ticks += 17;
                    break;
                }
                case 213:    /* PUSH DE */ {
                    pushw(DE);
                    ticks += 11;
                    break;
                }
                case 0xDD: { //+ ???
                    break;
                }
                case 229:    /* PUSH HL */ {
                    pushw(HL);
                    ticks += 11;
                    break;
                }
                case 0xED: { //+ ???
                    break;
                }
                case 245:    /* PUSH AF */ {
                    pushw(AF());
                    ticks += 11;
                    break;
                }
                case 0xFD: { //+ ???
                    break;
                }

                /* op A,N */
                case 198: /* ADD A,N */ {
                    add_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 206: /* ADC A,N */ {
                    adc_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 214: /* SUB N */ {
                    sub_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 222: /* SBC A,N */ {
                    sbc_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 230: /* AND N */ {
                    and_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 238: /* XOR N */ {
                    xor_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 246: /* OR N */ {
                    or_a(nxtpcb());
                    ticks += 7;
                    break;
                }
                case 254: /* CP N */ {
                    cp_a(nxtpcb());
                    ticks += 7;
                    break;
                }

                /* RST n */
                case 199:    /* RST 0 */ {
                    pushpc();
                    PC = 0;
                    ticks += 11;
                    break;
                }
                case 207:    /* RST 8 */ {
                    pushpc();
                    PC = 8;
                    ticks += 11;
                    break;
                }
                case 215:    /* RST 16 */ {
                    pushpc();
                    PC = 16;
                    ticks += 11;
                    break;
                }
                case 223:    /* RST 24 */ {
                    pushpc();
                    PC = 24;
                    ticks += 11;
                    break;
                }
                case 231:    /* RST 32 */ {
                    pushpc();
                    PC = 32;
                    ticks += 11;
                    break;
                }
                case 239:    /* RST 40 */ {
                    pushpc();
                    PC = 40;
                    ticks += 11;
                    break;
                }
                case 247:    /* RST 48 */ {
                    pushpc();
                    PC = 48;
                    ticks += 11;
                    break;
                }
                case 255:    /* RST 56 */ {
                    pushpc();
                    PC = 56;
                    ticks += 11;
                    break;
                }
            }
        }
    }

    /**
     * Add with carry - alters all flags (CHECKED)
     */
    private void adc_a(int b) {
        int a = A;
        int c = Tci();
        int wans = a + b + c;
        int ans = LO(wans);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tc = (wans & x100) != 0;
        boolean f = ((a ^ ~b) & (a ^ ans) & x80) != 0;
        tp = f;
        th = (((a & x0F) + (b & x0F) + c) & T4h) != 0;

        A = ans;
    }

    /**
     * Add - alters all flags (CHECKED)
     */
    private void add_a(int b) {
        int a = A;
        int wans = a + b;
        int ans = LO(wans);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tc = (wans & x100) != 0;
        boolean f = ((a ^ ~b) & (a ^ ans) & x80) != 0;
        tp = f;
        th = (((a & x0F) + (b & x0F)) & T4h) != 0;

        A = ans;
    }

    /**
     * Subtract with carry - alters all flags (CHECKED)
     */
    private void sbc_a(int b) {
        int a = A;
        int c = Tci();
        int wans = a - b - c;
        int ans = LO(wans);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tc = (wans & x100) != 0;
        tp = ((a ^ b) & (a ^ ans) & x80) != 0;
        th = (((a & x0F) - (b & x0F) - c) & T4h) != 0;

        A = ans;
    }

    /**
     * Subtract - alters all flags (CHECKED)
     */
    private void sub_a(int b) {
        int a = A;
        int wans = a - b;
        int ans = LO(wans);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tc = (wans & x100) != 0;
        tp = ((a ^ b) & (a ^ ans) & x80) != 0;
        th = (((a & x0F) - (b & x0F)) & T4h) != 0;

        A = ans;
    }

    /**
     * Rotate Left - alters H N C 3 5 flags (CHECKED)
     */
    private void rlc_a() {
        int ans = A;
        boolean c = (ans & x80) != 0;

        if (c) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }
        ans = LO(ans);

        th = false;
        tc = c;

        A = ans;
    }

    /**
     * Rotate Right - alters H N C 3 5 flags (CHECKED)
     */
    private void rrc_a() {
        int ans = A;
        boolean c = (ans & x01) != 0;

        if (c) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        th = false;
        tc = c;

        A = ans;
    }

    /**
     * Rotate Left through Carry - alters H N C 3 5 flags (CHECKED)
     */
    private void rl_a() {
        int ans = A;
        boolean c = (ans & x80) != 0;

        if (tc) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }

        ans = LO(ans);

        th = false;
        tc = c;

        A = ans;
    }

    /**
     * Rotate Right through Carry - alters H N C 3 5 flags (CHECKED)
     */
    private void rr_a() {
        int ans = A;
        boolean c = (ans & x01) != 0;

        if (tc) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        th = false;
        tc = c;

        A = ans;
    }

    /**
     * Compare - alters all flags (CHECKED)
     */
    private void cp_a(int b) {
        int a = A;
        int wans = a - b;
        int ans = LO(wans);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tc = (wans & x100) != 0;
        th = (((a & x0F) - (b & x0F)) & T4h) != 0;
        tp = ((a ^ b) & (a ^ ans) & x80) != 0;
    }

    /**
     * Bitwise and - alters all flags (CHECKED)
     */
    private void and_a(int b) {
        int ans = A & b;

        ts = (ans & T7s) != 0;
        th = true;
        tp = parity[ans];
        tz = ans == 0;
        tc = false;

        A = ans;
    }

    /**
     * Bitwise or - alters all flags (CHECKED)
     */
    private void or_a(int b) {
        int ans = A | b;

        ts = (ans & T7s) != 0;
        th = false;
        tp = parity[ans];
        tz = ans == 0;
        tc = false;

        A = ans;
    }

    /**
     * Bitwise exclusive or - alters all flags (CHECKED)
     */
    private void xor_a(int b) {
        int ans = LO(A ^ b);

        ts = (ans & T7s) != 0;
        th = false;
        tp = parity[ans];
        tz = ans == 0;
        tc = false;

        A = ans;
    }


    /**
     * One's complement - alters N H 3 5 flags (CHECKED)
     */
    private void cpl_a() {
        int ans = A ^ xFF;

        th = true;

        A = ans;
    }

    /**
     * Decimal Adjust Accumulator - alters all flags (CHECKED)
     */
    private void daa_a() {
        int ans = A;
        int incr = 0;
        boolean carry = tc;

        if (th || (ans & x0F) > x09) {
            incr |= x06;
        }
        if (carry || (ans > x9F) || ((ans > x8F) && ((ans & x0F) > x09))) {
            incr |= x60;
        }
        if (ans > x99) {
            carry = true;
        }
        sub_a(incr);

        ans = A;

        tc = carry;
        tp = parity[ans];
    }

    /**
     * Set carry flag - alters N H 3 5 C flags (CHECKED)
     */
    private void scf() {
        th = false;
        tc = true;
    }

    /**
     * Complement carry flag - alters N 3 5 C flags (CHECKED)
     */
    private void ccf() {
        boolean f = !tc;
        tc = f;
    }

    /**
     * Decrement - alters all but C flag (CHECKED)
     */
    private int dec8(int ans) {
        boolean pv = (ans == x80);
        boolean h = (((ans & x0F) - 1) & T4h) != 0;
        ans = LO(ans - 1);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tp = pv;
        th = h;

        return ans;
    }

    /**
     * Increment - alters all but C flag (CHECKED)
     */
    private int inc8(int ans) {
        boolean pv = (ans == x7F);
        boolean h = (((ans & x0F) + 1) & T4h) != 0;
        ans = LO(ans + 1);

        ts = (ans & T7s) != 0;
        tz = ans == 0;
        tp = pv;
        th = h;

        return ans;
    }

    /**
     * Add - (NOT CHECKED)
     */
    private int add16(int a, int b) {
        int lans = a + b;
        int ans = WORD(lans);

        tc = (lans & x10000) != 0;
        th = (((a & x0FFF) + (b & x0FFF)) & x1000) != 0;

        return ans;
    }


    /**
     * Quick Increment : no flags
     */
    private static int inc16(int a) {
        return WORD(a + 1);
    }


    /**
     * Quick Decrement : no flags
     */
    private static int dec16(int a) {
        return WORD(a - 1);
    }

    private static int qdec8(int a) {
        return LO(a - 1);
    }

    public void runAt(int addr) {
        PC = addr;
    }
}