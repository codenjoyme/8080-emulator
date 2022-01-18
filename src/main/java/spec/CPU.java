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
        tstatesPerInterrupt = (int) ((clockFrequencyInMHz * 1e6) / 50);
        this.accessor = accessor;
    }

    private int tstatesPerInterrupt;

    private static final int IM0 = 0;
    private static final int IM1 = 1;
    private static final int IM2 = 2;

    private static final int F_C = x01;
    private static final int F_N = x02;
    private static final int F_PV = x04;
    private static final int F_3 = x08;
    private static final int F_H = x10;
    private static final int F_5 = x20;
    private static final int F_Z = x40;
    private static final int F_S = x80;

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

    private boolean fS = false;
    private boolean fZ = false;
    private boolean f5 = false;
    private boolean fH = false;
    private boolean f3 = false;
    private boolean fPV = false;
    private boolean fN = false;
    private boolean fC = false;

    /**
     * Alternate registers
     */
    private int _AF_ = 0;
    private int _HL_ = 0;
    private int _BC_ = 0;
    private int _DE_ = 0;

    /**
     * Index registers - ID used as temporary for ix/iy
     */
    private int _IX = 0;
    private int _IY = 0;
    private int _ID = 0;

    /**
     * Stack Pointer and Program Counter
     */
    private int SP = 0;
    private int PC = START_POINT;

    /**
     * Interrupt and Refresh registers
     */
    private int _I = 0;
    private int _R = 0;
    private int _R7 = 0;

    /**
     * Interrupt flip-flops
     */
    private boolean _IFF1 = true;
    private boolean _IFF2 = true;
    private int _IM = 2;

    /**
     * 16 bit register access
     */
    private int AF() {
        return MERGE(A(), F());
    }

    private void AF(int word) {
        A(word >> 8);
        F(LO(word));
    }

    private int BC() {
        return MERGE(B(), C());
    }

    private void BC(int word) {
        B(word >> 8);
        C(LO(word));
    }

    private int DE() {
        return DE;
    }

    private void DE(int word) {
        DE = word;
    }

    private int HL() {
        return HL;
    }

    private void HL(int word) {
        HL = word;
    }

    private int PC() {
        return PC;
    }

    public void PC(int word) {
        PC = word;
    }

    private int SP() {
        return SP;
    }

    private void SP(int word) {
        SP = word;
    }

    private int ID() {
        return _ID;
    }

    private void ID(int word) {
        _ID = word;
    }

    private int IX() {
        return _IX;
    }

    private void IX(int word) {
        _IX = word;
    }

    private int IY() {
        return _IY;
    }

    private void IY(int word) {
        _IY = word;
    }

    /**
     * 8 bit register access
     */
    private int A() {
        return A;
    }

    private void A(int bite) {
        A = bite;
    }

    private int F() {
        return (Sset() ? F_S : 0) |
                (Zset() ? F_Z : 0) |
                (f5 ? F_5 : 0) |
                (Hset() ? F_H : 0) |
                (f3 ? F_3 : 0) |
                (PVset() ? F_PV : 0) |
                (Nset() ? F_N : 0) |
                (Cset() ? F_C : 0);
    }

    private void F(int bite) {
        fS = (bite & F_S) != 0;
        fZ = (bite & F_Z) != 0;
        f5 = (bite & F_5) != 0;
        fH = (bite & F_H) != 0;
        f3 = (bite & F_3) != 0;
        fPV = (bite & F_PV) != 0;
        fN = (bite & F_N) != 0;
        fC = (bite & F_C) != 0;
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

    private int IDH() {
        return _ID >> 8;
    }

    private void IDH(int bite) {
        _ID = LO_MERGE(bite, _ID);
    }

    private int IDL() {
        return LO(_ID);
    }

    private void IDL(int bite) {
        _ID = HI_MERGE(_ID, bite);
    }

    /**
     * Memory refresh register
     */
    private int R7() {
        return _R7;
    }

    private int R() {
        return (_R & x7F) | _R7;
    }

    private void R(int bite) {
        _R = bite;
        _R7 = bite & x80;
    }

    private void REFRESH(int t) {
        _R += t;
    }

    /**
     * Interrupt modes/register
     */
    private int I() {
        return _I;
    }

    private void I(int bite) {
        _I = bite;
    }

    private boolean IFF1() {
        return _IFF1;
    }

    private void IFF1(boolean iff1) {
        _IFF1 = iff1;
    }

    private boolean IFF2() {
        return _IFF2;
    }

    private void IFF2(boolean iff2) {
        _IFF2 = iff2;
    }

    private int IM() {
        return _IM;
    }

    private void IM(int im) {
        _IM = im;
    }

    /**
     * Flag access
     */
    private void setZ(boolean f) {
        fZ = f;
    }

    private void setC(boolean f) {
        fC = f;
    }

    private void setS(boolean f) {
        fS = f;
    }

    private void setH(boolean f) {
        fH = f;
    }

    private void setN(boolean f) {
        fN = f;
    }

    private void setPV(boolean f) {
        fPV = f;
    }

    private void set3(boolean f) {
        f3 = f;
    }

    private void set5(boolean f) {
        f5 = f;
    }

    private boolean Zset() {
        return fZ;
    }

    private boolean Cset() {
        return fC;
    }

    private boolean Sset() {
        return fS;
    }

    private boolean Hset() {
        return fH;
    }

    private boolean Nset() {
        return fN;
    }

    private boolean PVset() {
        return fPV;
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
     * Index register access
     */
    private int ID_d() {
        return WORD(ID() + (byte) nxtpcb());  // байт из памяти по адресу п-счетчика PC()
    }

    /**
     * Stack access
     */
    private void pushw(int word) {
        int sp = WORD(SP() - 2);
        SP(sp);
        pokew(sp, word);
    }


    private int popw() {
        int sp = SP();
        int t = peekb(sp);
        sp++;
        t |= (peekb(WORD(sp)) << 8);
        SP(WORD(++sp));
        return t;
    }

    /**
     * Call stack
     */
    private void pushpc() {
        pushw(PC());
    }

    private void poppc() {
        PC(popw());
    }

    /**
     *  Program access
     */
    private int nxtpcb() // байт из памяти по адресу п-счетчика PC()
    {                          // и увеличение PC()
        int pc = PC();
        int t = peekb(pc);
        PC(WORD(++pc));
        return t;
    }

    private int nxtpcw() // слово из памяти по адресу п-счетчика PC()
    {                          // и увеличение PC()
        int pc = PC();
        int t = peekb(pc);
        t |= (peekb(WORD(++pc)) << 8);
        PC(WORD(++pc));
        return t;
    }

    /**
     * Reset all registers to power on state
     */
    public void reset() {
        PC(START_POINT);
        SP(0);

        A(0);
        F(0);
        BC(0);
        DE(0);
        HL(0);

        exx();
        ex_af_af();

        A(0);
        F(0);
        BC(0);
        DE(0);
        HL(0);

        IX(0);
        IY(0);

        R(0);

        I(0);
        IFF1(false);
        IFF2(false);
        IM(IM0);
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
     *  Z80 fetch/execute loop (цикл выборки/выполнения)
     */
    // основной цикл выполнения кода
    public void execute() {
    // showStatus(" Z80_.execute >"); // вызывается из Jasper.class: spectrum.execute();
        int local_tstates = -tstatesPerInterrupt;
        // в начале local_tstates = числу тактов на прерывание.
        while (true)  // цикл выборки/выполнения
        {

            if (interruptTriggered(local_tstates)) // local_tstates >= 0 ? true
            {  // число тактов на прерывание исчерпано - вызываем прерывание.
                accessor.interrupt();
                local_tstates -= tstatesPerInterrupt;
            }  // local_tstates = числу тактов на прерывание + время прерывания.

            REFRESH(1);

            switch (nxtpcb()) // байт из памяти по адресу п-счетчика PC()
            {                   // и разбор этого байта как кода
                case 0:    /* NOP */ {
                    local_tstates += i4_b00000100;  // каждая операция уменьшает число тактов на прерывание
                    break;                   // на свою длительность в тактах
                }
                case 8:    /* EX AF,AF' */ {
                    ex_af_af();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 16:    /* DJNZ dis */ {
                    int b;

                    B(b = qdec8(B()));
                    if (b != 0) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC(WORD(PC() + d));
                        local_tstates += i13_b00001101;
                    } else {
                        PC(inc16(PC()));
                        local_tstates += i8_b00001000;
                    }
                    break;
                }
                case 24: /* JR dis */ {
                    byte d = (byte) nxtpcb();  // байт из памяти по адресу п-счетчика PC()
                    PC(WORD(PC() + d));
                    local_tstates += i12_b00001100;
                    break;
                }
                /* JR cc,dis */
                case 32:    /* JR NZ,dis */ {
                    if (!Zset()) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC(WORD(PC() + d));
                        local_tstates += i12_b00001100;
                    } else {
                        PC(inc16(PC()));
                        local_tstates += i7_b00000111;
                    }
                    break;
                }
                case 40:    /* JR Z,dis */ {
                    if (Zset()) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC(WORD(PC() + d));
                        local_tstates += i12_b00001100;
                    } else {
                        PC(inc16(PC()));
                        local_tstates += i7_b00000111;
                    }
                    break;
                }
                case 48:    /* JR NC,dis */ {
                    if (!Cset()) {
                        byte d = (byte) nxtpcb(); // байт из памяти по адресу п-счетчика PC()
                        PC(WORD(PC() + d));
                        local_tstates += i12_b00001100;
                    } else {
                        PC(inc16(PC()));
                        local_tstates += i7_b00000111;
                    }
                    break;
                }
                case 56:    /* JR C,dis */ {
                    if (Cset()) {
                        byte d = (byte) nxtpcb();  // байт из памяти по адресу п-счетчика PC()
                        PC(WORD(PC() + d));
                        local_tstates += i12_b00001100;
                    } else {
                        PC(inc16(PC()));
                        local_tstates += i7_b00000111;
                    }
                    break;
                }

                /* LD rr,nn / ADD HL,rr */
                case 1:    /* LD BC(),nn */ {
                    BC(nxtpcw());            // слово из памяти по адресу п-счетчика PC()
                    local_tstates += i10_b00001010;
                    break;
                }
                case 9:    /* ADD HL,BC */ {
                    HL(add16(HL(), BC()));
                    local_tstates += i11_b00001011;
                    break;
                }
                case 17:    /* LD DE,nn */ {
                    DE(nxtpcw());           // слово из памяти по адресу п-счетчика PC()
                    local_tstates += i10_b00001010;
                    break;
                }
                case 25:    /* ADD HL,DE */ {
                    HL(add16(HL(), DE()));
                    local_tstates += i11_b00001011;
                    break;
                }
                case 33:    /* LD HL,nn */ {
                    HL(nxtpcw());           // слово из памяти по адресу п-счетчика PC()
                    local_tstates += i10_b00001010;
                    break;
                }
                case 41:    /* ADD HL,HL */ {
                    int hl = HL();
                    HL(add16(hl, hl));
                    local_tstates += i11_b00001011;
                    break;
                }
                case 49:    /* LD SP,nn */ {
                    SP(nxtpcw());           // слово из памяти по адресу п-счетчика PC()
                    local_tstates += i10_b00001010;
                    break;
                }
                case 57:    /* ADD HL,SP */ {
                    HL(add16(HL(), SP()));
                    local_tstates += i11_b00001011;
                    break;
                }

                /* LD (**),A/A,(**) */
                case 2:    /* LD (BC),A */ {
                    pokeb(BC(), A());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 10:    /* LD A,(BC) */ {
                    A(peekb(BC()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 18:    /* LD (DE),A */ {
                    pokeb(DE(), A());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 26:    /* LD A,(DE) */ {
                    A(peekb(DE()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 34:    /* LD (nn),HL */ {
                    pokew(nxtpcw(), HL());
                    local_tstates += i16_b00010000;
                    break;
                }
                case 42:    /* LD HL,(nn) */ {
                    HL(peekw(nxtpcw()));
                    local_tstates += i16_b00010000;
                    break;
                }
                case 50:    /* LD (nn),A */ {
                    pokeb(nxtpcw(), A());
                    local_tstates += i13_b00001101;
                    break;
                }
                case 58:    /* LD A,(nn) */ {
                    A(peekb(nxtpcw()));
                    local_tstates += i13_b00001101;
                    break;
                }

                /* INC/DEC * */
                case 3:    /* INC BC */ {
                    BC(inc16(BC()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 11:    /* DEC BC */ {
                    BC(dec16(BC()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 19:    /* INC DE */ {
                    DE(inc16(DE()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 27:    /* DEC DE */ {
                    DE(dec16(DE()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 35:    /* INC HL */ {
                    HL(inc16(HL()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 43:    /* DEC HL */ {
                    HL(dec16(HL()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 51:    /* INC SP */ {
                    SP(inc16(SP()));
                    local_tstates += i6_b00000110;
                    break;
                }
                case 59:    /* DEC SP */ {
                    SP(dec16(SP()));
                    local_tstates += i6_b00000110;
                    break;
                }

                /* INC * */
                case 4:    /* INC B */ {
                    B(inc8(B()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 12:    /* INC C */ {
                    C(inc8(C()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 20:    /* INC D */ {
                    D(inc8(D()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 28:    /* INC E */ {
                    E(inc8(E()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 36:    /* INC H */ {
                    H(inc8(H()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 44:    /* INC L */ {
                    L(inc8(L()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 52:    /* INC (HL) */ {
                    int hl = HL();
                    pokeb(hl, inc8(peekb(hl)));
                    local_tstates += i11_b00001011;
                    break;
                }
                case 60:    /* INC A() */ {
                    A(inc8(A()));
                    local_tstates += i4_b00000100;
                    break;
                }

                /* DEC * */
                case 5:    /* DEC B */ {
                    B(dec8(B()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 13:    /* DEC C */ {
                    C(dec8(C()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 21:    /* DEC D */ {
                    D(dec8(D()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 29:    /* DEC E */ {
                    E(dec8(E()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 37:    /* DEC H */ {
                    H(dec8(H()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 45:    /* DEC L */ {
                    L(dec8(L()));
                    local_tstates += i4_b00000100;
                    break;
                }
                case 53:    /* DEC (HL) */ {
                    int hl = HL();
                    pokeb(hl, dec8(peekb(hl)));
                    local_tstates += i11_b00001011;
                    break;
                }
                case 61:    /* DEC A() */ {
                    A(dec8(A()));
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD *,N */
                case 6:    /* LD B,n */ {
                    B(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 14:    /* LD C,n */ {
                    C(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 22:    /* LD D,n */ {
                    D(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 30:    /* LD E,n */ {
                    E(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 38:    /* LD H,n */ {
                    H(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 46:    /* LD L,n */ {
                    L(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 54:    /* LD (HL),n */ {
                    pokeb(HL(), nxtpcb());
                    local_tstates += i10_b00001010;
                    break;
                }
                case 62:    /* LD A,n */ {
                    A(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }

                /* R**A */
                case 7: /* RLCA */ {
                    rlc_a();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 15: /* RRCA */ {
                    rrc_a();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 23: /* RLA */ {
                    rl_a();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 31: /* RRA */ {
                    rr_a();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 39: /* DAA */ {
                    daa_a();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 47: /* CPL */ {
                    cpl_a();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 55: /* SCF */ {
                    scf();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 63: /* CCF */ {
                    ccf();
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD B,* */
                case 64:    /* LD B,B */ {
                    local_tstates += i4_b00000100;
                    break;
                }
                case 65:    /* LD B,C */ {
                    B(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 66:    /* LD B,D */ {
                    B(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 67:    /* LD B,E */ {
                    B(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 68:    /* LD B,H */ {
                    B(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 69:    /* LD B,L */ {
                    B(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 70:    /* LD B,(HL) */ {
                    B(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 71:    /* LD B,A */ {
                    B(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD C,* */
                case 72:    /* LD C,B */ {
                    C(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 73:    /* LD C,C */ {
                    local_tstates += i4_b00000100;
                    break;
                }
                case 74:    /* LD C,D */ {
                    C(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 75:    /* LD C,E */ {
                    C(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 76:    /* LD C,H */ {
                    C(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 77:    /* LD C,L */ {
                    C(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 78:    /* LD C,(HL) */ {
                    C(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 79:    /* LD C,A */ {
                    C(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD D,* */
                case 80:    /* LD D,B */ {
                    D(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 81:    /* LD D,C */ {
                    D(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 82:    /* LD D,D */ {
                    local_tstates += i4_b00000100;
                    break;
                }
                case 83:    /* LD D,E */ {
                    D(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 84:    /* LD D,H */ {
                    D(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 85:    /* LD D,L */ {
                    D(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 86:    /* LD D,(HL) */ {
                    D(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 87:    /* LD D,A */ {
                    D(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD E,* */
                case 88:    /* LD E,B */ {
                    E(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 89:    /* LD E,C */ {
                    E(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 90:    /* LD E,D */ {
                    E(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 91:    /* LD E,E */ {
                    local_tstates += i4_b00000100;
                    break;
                }
                case 92:    /* LD E,H */ {
                    E(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 93:    /* LD E,L */ {
                    E(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 94:    /* LD E,(HL) */ {
                    E(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 95:    /* LD E,A */ {
                    E(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD H,* */
                case 96:    /* LD H,B */ {
                    H(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 97:    /* LD H,C */ {
                    H(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 98:    /* LD H,D */ {
                    H(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 99:    /* LD H,E */ {
                    H(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 100: /* LD H,H */ {
                    local_tstates += i4_b00000100;
                    break;
                }
                case 101:    /* LD H,L */ {
                    H(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 102:    /* LD H,(HL) */ {
                    H(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 103:    /* LD H,A */ {
                    H(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD L,* */
                case 104:    /* LD L,B */ {
                    L(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 105:    /* LD L,C */ {
                    L(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 106:    /* LD L,D */ {
                    L(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 107:    /* LD L,E */ {
                    L(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 108:    /* LD L,H */ {
                    L(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 109:    /* LD L,L */ {
                    local_tstates += i4_b00000100;
                    break;
                }
                case 110:    /* LD L,(HL) */ {
                    L(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 111:    /* LD L,A */ {
                    L(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* LD (HL),* */
                case 112:    /* LD (HL),B */ {
                    pokeb(HL(), B());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 113:    /* LD (HL),C */ {
                    pokeb(HL(), C());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 114:    /* LD (HL),D */ {
                    pokeb(HL(), D());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 115:    /* LD (HL),E */ {
                    pokeb(HL(), E());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 116:    /* LD (HL),H */ {
                    pokeb(HL(), H());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 117:    /* LD (HL),L */ {
                    pokeb(HL(), L());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 118:    /* HALT */ {
                    int haltsToInterrupt = (-local_tstates - 1) / 4 + 1;
                    local_tstates += haltsToInterrupt * 4;
                    REFRESH(haltsToInterrupt - 1);
                    break;
                }
                case 119:    /* LD (HL),A */ {
                    pokeb(HL(), A());
                    local_tstates += i7_b00000111;
                    break;
                }

                /* LD A,* */
                case 120:    /* LD A,B */ {
                    A(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 121:    /* LD A,C */ {
                    A(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 122:    /* LD A,D */ {
                    A(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 123:    /* LD A,E */ {
                    A(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 124:    /* LD A,H */ {
                    A(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 125:    /* LD A,L */ {
                    A(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 126:    /* LD A,(HL) */ {
                    A(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 127:    /* LD A,A */ {
                    local_tstates += i4_b00000100;
                    break;
                }

                /* ADD A,* */
                case 128:    /* ADD A,B */ {
                    add_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 129:    /* ADD A,C */ {
                    add_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 130:    /* ADD A,D */ {
                    add_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 131:    /* ADD A,E */ {
                    add_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 132:    /* ADD A,H */ {
                    add_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 133:    /* ADD A,L */ {
                    add_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 134:    /* ADD A,(HL) */ {
                    add_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 135:    /* ADD A,A */ {
                    add_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* ADC A,* */
                case 136:    /* ADC A,B */ {
                    adc_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 137:    /* ADC A,C */ {
                    adc_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 138:    /* ADC A,D */ {
                    adc_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 139:    /* ADC A,E */ {
                    adc_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 140:    /* ADC A,H */ {
                    adc_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 141:    /* ADC A,L */ {
                    adc_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 142:    /* ADC A,(HL) */ {
                    adc_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 143:    /* ADC A,A */ {
                    adc_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* SUB * */
                case 144:    /* SUB B */ {
                    sub_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 145:    /* SUB C */ {
                    sub_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 146:    /* SUB D */ {
                    sub_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 147:    /* SUB E */ {
                    sub_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 148:    /* SUB H */ {
                    sub_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 149:    /* SUB L */ {
                    sub_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 150:    /* SUB (HL) */ {
                    sub_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 151:    /* SUB A() */ {
                    sub_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* SBC A,* */
                case 152:    /* SBC A,B */ {
                    sbc_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 153:    /* SBC A,C */ {
                    sbc_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 154:    /* SBC A,D */ {
                    sbc_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 155:    /* SBC A,E */ {
                    sbc_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 156:    /* SBC A,H */ {
                    sbc_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 157:    /* SBC A,L */ {
                    sbc_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 158:    /* SBC A,(HL) */ {
                    sbc_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 159:    /* SBC A,A */ {
                    sbc_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* AND * */
                case 160:    /* AND B */ {
                    and_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 161:    /* AND C */ {
                    and_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 162:    /* AND D */ {
                    and_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 163:    /* AND E */ {
                    and_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 164:    /* AND H */ {
                    and_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 165:    /* AND L */ {
                    and_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 166:    /* AND (HL) */ {
                    and_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 167:    /* AND A() */ {
                    and_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* XOR * */
                case 168:    /* XOR B */ {
                    xor_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 169:    /* XOR C */ {
                    xor_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 170:    /* XOR D */ {
                    xor_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 171:    /* XOR E */ {
                    xor_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 172:    /* XOR H */ {
                    xor_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 173:    /* XOR L */ {
                    xor_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 174:    /* XOR (HL) */ {
                    xor_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 175:    /* XOR A() */ {
                    xor_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* OR * */
                case 176:    /* OR B */ {
                    or_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 177:    /* OR C */ {
                    or_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 178:    /* OR D */ {
                    or_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 179:    /* OR E */ {
                    or_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 180:    /* OR H */ {
                    or_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 181:    /* OR L */ {
                    or_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 182:    /* OR (HL) */ {
                    or_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 183:    /* OR A() */ {
                    or_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* CP * */
                case 184:    /* CP B */ {
                    cp_a(B());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 185:    /* CP C */ {
                    cp_a(C());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 186:    /* CP D */ {
                    cp_a(D());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 187:    /* CP E */ {
                    cp_a(E());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 188:    /* CP H */ {
                    cp_a(H());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 189:    /* CP L */ {
                    cp_a(L());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 190:    /* CP (HL) */ {
                    cp_a(peekb(HL()));
                    local_tstates += i7_b00000111;
                    break;
                }
                case 191:    /* CP A() */ {
                    cp_a(A());
                    local_tstates += i4_b00000100;
                    break;
                }

                /* RET cc */
                case 192:    /* RET NZ */ {
                    if (!Zset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 200:    /* RET Z */ {
                    if (Zset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 208:    /* RET NC */ {
                    if (!Cset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 216:    /* RET C */ {
                    if (Cset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 224:    /* RET PO */ {
                    if (!PVset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 232:    /* RET PE */ {
                    if (PVset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 240:    /* RET P */ {
                    if (!Sset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }
                case 248:    /* RET M */ {
                    if (Sset()) {
                        poppc();
                        local_tstates += i11_b00001011;
                    } else {
                        local_tstates += i5_b00000101;
                    }
                    break;
                }

                /* POP,Various */
                case 193:    /* POP BC */ {
                    BC(popw());
                    local_tstates += i10_b00001010;
                    break;
                }
                case 201: /* RET */ {
                    poppc();
                    local_tstates += i10_b00001010;
                    break;
                }
                case 209:    /* POP DE */ {
                    DE(popw());
                    local_tstates += i10_b00001010;
                    break;
                }
                case 217:    /* EXX */ {
                    exx();
                    local_tstates += i4_b00000100;
                    break;
                }
                case 225:    /* POP HL */ {
                    HL(popw());
                    local_tstates += i10_b00001010;
                    break;
                }
                case 233: /* JP (HL) */ {
                    PC(HL());
                    local_tstates += i4_b00000100;
                    break;
                }
                case 241:    /* POP AF */ {
                    AF(popw());
                    local_tstates += i10_b00001010;
                    break;
                }
                case 249:    /* LD SP,HL */ {
                    SP(HL());
                    local_tstates += i6_b00000110;
                    break;
                }

                /* JP cc,nn */
                case 194:    /* JP NZ,nn */ {
                    if (!Zset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 202:    /* JP Z,nn */ {
                    if (Zset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 210:    /* JP NC,nn */ {
                    if (!Cset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 218:    /* JP C,nn */ {
                    if (Cset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 226:    /* JP PO,nn */ {
                    if (!PVset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 234:    /* JP PE,nn */ {
                    if (PVset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 242:    /* JP P,nn */ {
                    if (!Sset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }
                case 250:    /* JP M,nn */ {
                    if (Sset()) {
                        PC(nxtpcw());
                    } else {
                        PC(WORD(PC() + 2));
                    }
                    local_tstates += i10_b00001010;
                    break;
                }


                /* Various */
                case 195:    /* JP nn */ {
                    PC(peekw(PC()));
                    local_tstates += i10_b00001010;
                    break;
                }
                case 203:    /* prefix CB */ {
                    local_tstates += execute_cb();
                    break;
                }
                case 211:    /* OUT (n),A */ {
                    accessor.outb(nxtpcb(), A());
                    local_tstates += i11_b00001011;
                    break;
                }
                case 219:    /* IN A,(n) */ {
                    A(inb((A() << 8) | nxtpcb()));
                    local_tstates += i11_b00001011;
                    break;
                }
                case 227:    /* EX (SP),HL */ {
                    int t = HL();
                    int sp = SP();
                    HL(peekw(sp));
                    pokew(sp, t);
                    local_tstates += i19_b00010011;
                    break;
                }
                case 235:    /* EX DE,HL */ {
                    int t = HL();
                    HL(DE());
                    DE(t);
                    local_tstates += i4_b00000100;
                    break;
                }
                case 243:    /* DI */ {
                    IFF1(false);
                    IFF2(false);
                    local_tstates += i4_b00000100;
                    break;
                }
                case 251:    /* EI */ {
                    IFF1(true);
                    IFF2(true);
                    local_tstates += i4_b00000100;
                    break;
                }

                /* CALL cc,nn */
                case 196: /* CALL NZ,nn */ {
                    if (!Zset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 204: /* CALL Z,nn */ {
                    if (Zset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 212: /* CALL NC,nn */ {
                    if (!Cset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 220: /* CALL C,nn */ {
                    if (Cset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 228: /* CALL PO,nn */ {
                    if (!PVset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 236: /* CALL PE,nn */ {
                    if (PVset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 244: /* CALL P,nn */ {
                    if (!Sset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }
                case 252: /* CALL M,nn */ {
                    if (Sset()) {
                        int t = nxtpcw();
                        pushpc();
                        PC(t);
                        local_tstates += i17_b00010001;
                    } else {
                        PC(WORD(PC() + 2));
                        local_tstates += i10_b00001010;
                    }
                    break;
                }

                /* PUSH,Various */
                case 197:    /* PUSH BC */ {
                    pushw(BC());
                    local_tstates += i11_b00001011;
                    break;
                }
                case 205:    /* CALL nn */ {
                    int t = nxtpcw();
                    pushpc();
                    PC(t);
                    local_tstates += i17_b00010001;
                    break;
                }
                case 213:    /* PUSH DE */ {
                    pushw(DE());
                    local_tstates += i11_b00001011;
                    break;
                }
                case 221:    /* prefix IX */ {
                    ID(IX());
                    local_tstates += execute_id();
                    IX(ID());
                    break;
                }
                case 229:    /* PUSH HL */ {
                    pushw(HL());
                    local_tstates += i11_b00001011;
                    break;
                }
                case 237:    /* prefix ED */ {
                    local_tstates += execute_ed(local_tstates);
                    break;
                }
                case 245:    /* PUSH AF */ {
                    pushw(AF());
                    local_tstates += i11_b00001011;
                    break;
                }
                case 253:    /* prefix IY */ {
                    ID(IY());
                    local_tstates += execute_id();
                    IY(ID());
                    break;
                }

                /* op A,N */
                case 198: /* ADD A,N */ {
                    add_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 206: /* ADC A,N */ {
                    adc_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 214: /* SUB N */ {
                    sub_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 222: /* SBC A,N */ {
                    sbc_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 230: /* AND N */ {
                    and_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 238: /* XOR N */ {
                    xor_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 246: /* OR N */ {
                    or_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }
                case 254: /* CP N */ {
                    cp_a(nxtpcb());
                    local_tstates += i7_b00000111;
                    break;
                }

                /* RST n */
                case 199:    /* RST 0 */ {
                    pushpc();
                    PC(0);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 207:    /* RST 8 */ {
                    pushpc();
                    PC(8);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 215:    /* RST 16 */ {
                    pushpc();
                    PC(16);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 223:    /* RST 24 */ {
                    pushpc();
                    PC(24);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 231:    /* RST 32 */ {
                    pushpc();
                    PC(32);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 239:    /* RST 40 */ {
                    pushpc();
                    PC(40);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 247:    /* RST 48 */ {
                    pushpc();
                    PC(48);
                    local_tstates += i11_b00001011;
                    break;
                }
                case 255:    /* RST 56 */ {
                    pushpc();
                    PC(56);
                    local_tstates += i11_b00001011;
                    break;
                }
            }
        }
    }

    private int execute_ed(int local_tstates) {
        REFRESH(1);

        switch (nxtpcb()) {
            case 0:  /* NOP */
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:

            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:

            case 164:
            case 165:
            case 166:
            case 167:

            case 172:
            case 173:
            case 174:
            case 175:

            case 180:
            case 181:
            case 182:
            case 183: {
                return i8_b00001000;
            }

            /* IN r,(c) */
            case 64:  /* IN B,(c) */ {
                B(in_bc());
                return i12_b00001100;
            }
            case 72:  /* IN C,(c) */ {
                C(in_bc());
                return i12_b00001100;
            }
            case 80:  /* IN D,(c) */ {
                D(in_bc());
                return i12_b00001100;
            }
            case 88:  /* IN E,(c) */ {
                E(in_bc());
                return i12_b00001100;
            }
            case 96:  /* IN H,(c) */ {
                H(in_bc());
                return i12_b00001100;
            }
            case 104:  /* IN L,(c) */ {
                L(in_bc());
                return i12_b00001100;
            }
            case 112:  /* IN (c) */ {
                in_bc();
                return i12_b00001100;
            }
            case 120:  /* IN A,(c) */ {
                A(in_bc());
                return i12_b00001100;
            }

            /* OUT (c),r */
            case 65:  /* OUT (c),B */ {
                accessor.outb(BC(), B());
                return i12_b00001100;
            }
            case 73:  /* OUT (c),C */ {
                accessor.outb(BC(), C());
                return i12_b00001100;
            }
            case 81:  /* OUT (c),D */ {
                accessor.outb(BC(), D());
                return i12_b00001100;
            }
            case 89:  /* OUT (c),E */ {
                accessor.outb(BC(), E());
                return i12_b00001100;
            }
            case 97:  /* OUT (c),H */ {
                accessor.outb(BC(), H());
                return i12_b00001100;
            }
            case 105:  /* OUT (c),L */ {
                accessor.outb(BC(), L());
                return i12_b00001100;
            }
            case 113:  /* OUT (c),0 */ {
                accessor.outb(BC(), 0);
                return i12_b00001100;
            }
            case 121:  /* OUT (c),A */ {
                accessor.outb(BC(), A());
                return i12_b00001100;
            }

            /* SBC/ADC HL,ss */
            case 66:  /* SBC HL,BC */ {
                HL(sbc16(HL(), BC()));
                return i15_b00001111;
            }
            case 74:  /* ADC HL,BC */ {
                HL(adc16(HL(), BC()));
                return i15_b00001111;
            }
            case 82:  /* SBC HL,DE */ {
                HL(sbc16(HL(), DE()));
                return i15_b00001111;
            }
            case 90:  /* ADC HL,DE */ {
                HL(adc16(HL(), DE()));
                return i15_b00001111;
            }
            case 98:  /* SBC HL,HL */ {
                int hl = HL();
                HL(sbc16(hl, hl));
                return i15_b00001111;
            }
            case 106:  /* ADC HL,HL */ {
                int hl = HL();
                HL(adc16(hl, hl));
                return i15_b00001111;
            }
            case 114:  /* SBC HL,SP */ {
                HL(sbc16(HL(), SP()));
                return i15_b00001111;
            }
            case 122:  /* ADC HL,SP */ {
                HL(adc16(HL(), SP()));
                return i15_b00001111;
            }

            /* LD (nn),ss, LD ss,(nn) */
            case 67:  /* LD (nn),BC */ {
                pokew(nxtpcw(), BC());
                return i20_b00010100;
            }
            case 75:  /* LD BC(),(nn) */ {
                BC(peekw(nxtpcw()));
                return i20_b00010100;
            }
            case 83:  /* LD (nn),DE */ {
                pokew(nxtpcw(), DE());
                return i20_b00010100;
            }
            case 91:  /* LD DE,(nn) */ {
                DE(peekw(nxtpcw()));
                return i20_b00010100;
            }
            case 99:  /* LD (nn),HL */ {
                pokew(nxtpcw(), HL());
                return i20_b00010100;
            }
            case 107:  /* LD HL,(nn) */ {
                HL(peekw(nxtpcw()));
                return i20_b00010100;
            }
            case 115:  /* LD (nn),SP */ {
                pokew(nxtpcw(), SP());
                return i20_b00010100;
            }
            case 123:  /* LD SP,(nn) */ {
                SP(peekw(nxtpcw()));
                return i20_b00010100;
            }

            /* NEG */
            case 68:  /* NEG */
            case 76:  /* NEG */
            case 84:  /* NEG */
            case 92:  /* NEG */
            case 100:  /* NEG */
            case 108:  /* NEG */
            case 116:  /* NEG */
            case 124:  /* NEG */ {
                neg_a();
                return i8_b00001000;
            }

            /* RETn */
            case 69:  /* RETN */
            case 85:  /* RETN */
            case 101:  /* RETN */
            case 117:  /* RETN */ {
                IFF1(IFF2());
                poppc();
                return i14_b00001110;
            }
            case 77:  /* RETI */
            case 93:  /* RETI */
            case 109:  /* RETI */
            case 125:  /* RETI */ {
                poppc();
                return i14_b00001110;
            }

            /* IM x */
            case 70:  /* IM 0 */
            case 78:  /* IM 0 */
            case 102:  /* IM 0 */
            case 110:  /* IM 0 */ {
                IM(IM0);
                return i8_b00001000;
            }
            case 86:  /* IM 1 */
            case 118:  /* IM 1 */ {
                IM(IM1);
                return i8_b00001000;
            }
            case 94:  /* IM 2 */
            case 126:  /* IM 2 */ {
                IM(IM2);
                return i8_b00001000;
            }

            /* LD A,s / LD s,A / RxD */
            case 71:  /* LD I,A */ {
                I(A());
                return i9_b00001001;
            }
            case 79:  /* LD R,A */ {
                R(A());
                return i9_b00001001;
            }
            case 87:  /* LD A,I */ {
                ld_a_i();
                return i9_b00001001;
            }
            case 95:  /* LD A,R */ {
                ld_a_r();
                return i9_b00001001;
            }
            case 103:  /* RRD */ {
                rrd_a();
                return i18_b00010010;
            }
            case 111:  /* RLD */ {
                rld_a();
                return i18_b00010010;
            }

            /* xxI */
            case 160:  /* LDI */ {
                pokeb(DE(), peekb(HL()));
                DE(inc16(DE()));
                HL(inc16(HL()));
                BC(dec16(BC()));

                setPV(BC() != 0);
                setH(false);
                setN(false);

                return i16_b00010000;
            }
            case 161:  /* CPI */ {
                boolean c = Cset();

                cp_a(peekb(HL()));
                HL(inc16(HL()));
                BC(dec16(BC()));

                setPV(BC() != 0);
                setC(c);

                return i16_b00010000;
            }
            case 162:  /* INI */ {
                int b;
                pokeb(HL(), inb(BC()));
                B(b = qdec8(B()));
                HL(inc16(HL()));

                setZ(b == 0);
                setN(true);

                return i16_b00010000;
            }
            case 163:  /* OUTI */ {
                int b;
                B(b = qdec8(B()));
                accessor.outb(BC(), peekb(HL()));
                HL(inc16(HL()));

                setZ(b == 0);
                setN(true);

                return i16_b00010000;
            }

            /* xxD */
            case 168:  /* LDD */ {
                pokeb(DE(), peekb(HL()));
                DE(dec16(DE()));
                HL(dec16(HL()));
                BC(dec16(BC()));

                setPV(BC() != 0);
                setH(false);
                setN(false);

                return i16_b00010000;
            }
            case 169:  /* CPD */ {
                boolean c = Cset();

                cp_a(peekb(HL()));
                HL(dec16(HL()));
                BC(dec16(BC()));

                setPV(BC() != 0);
                setC(c);

                return i16_b00010000;
            }
            case 170:  /* IND */ {
                int b;
                pokeb(HL(), inb(BC()));
                B(b = qdec8(B()));
                HL(dec16(HL()));

                setZ(b == 0);
                setN(true);

                return i16_b00010000;
            }
            case 171:  /* OUTD */ {
                int b;
                B(b = qdec8(B()));
                accessor.outb(BC(), peekb(HL()));
                HL(dec16(HL()));

                setZ(b == 0);
                setN(true);

                return i16_b00010000;
            }

            /* xxIR */
            case 176:  /* LDIR */ {
                int _local_tstates = 0;
                int count, dest, from;

                count = BC();
                dest = DE();
                from = HL();
                REFRESH(-2);
                do {
                    pokeb(dest, peekb(from));
                    from = inc16(from);
                    dest = inc16(dest);
                    count = dec16(count);

                    _local_tstates += i21_b00010101;
                    REFRESH(2);
                    if (interruptTriggered(_local_tstates)) {
                        break;
                    }
                } while (count != 0);
                if (count != 0) {
                    PC(WORD(PC() - 2));
                    setH(false);
                    setN(false);
                    setPV(true);
                } else {
                    _local_tstates += b11111011; 
                    setH(false);
                    setN(false);
                    setPV(false);
                }
                DE(dest);
                HL(from);
                BC(count);

                return _local_tstates;
            }
            case 177:  /* CPIR */ {
                boolean c = Cset();

                cp_a(peekb(HL()));
                HL(inc16(HL()));
                BC(dec16(BC()));

                boolean pv = BC() != 0;

                setPV(pv);
                setC(c);
                if (pv && !Zset()) {
                    PC(WORD(PC() - 2));
                    return i21_b00010101;
                }
                return i16_b00010000;
            }
            case 178:  /* INIR */ {
                int b;
                pokeb(HL(), inb(BC()));
                B(b = qdec8(B()));
                HL(inc16(HL()));

                setZ(true);
                setN(true);
                if (b != 0) {
                    PC(WORD(PC() - 2));
                    return i21_b00010101;
                }
                return i16_b00010000;
            }
            case 179:  /* OTIR */ {
                int b;
                B(b = qdec8(B()));
                accessor.outb(BC(), peekb(HL()));
                HL(inc16(HL()));

                setZ(true);
                setN(true);
                if (b != 0) {
                    PC(WORD(PC() - 2));
                    return i21_b00010101;
                }
                return i16_b00010000;
            }

            /* xxDR */
            case 184:  /* LDDR */ {
                int _local_tstates = 0;
                int count, dest, from;

                count = BC();
                dest = DE();
                from = HL();
                REFRESH(-2);
                do {
                    pokeb(dest, peekb(from));
                    from = dec16(from);
                    dest = dec16(dest);
                    count = dec16(count);

                    _local_tstates += i21_b00010101;
                    REFRESH(2);
                    if (interruptTriggered(_local_tstates)) {
                        break;
                    }
                } while (count != 0);
                if (count != 0) {
                    PC(WORD(PC() - 2));
                    setH(false);
                    setN(false);
                    setPV(true);
                } else {
                    _local_tstates += b11111011;
                    setH(false);
                    setN(false);
                    setPV(false);
                }
                DE(dest);
                HL(from);
                BC(count);

                return _local_tstates;
            }
            case 185:  /* CPDR */ {
                boolean c = Cset();

                cp_a(peekb(HL()));
                HL(dec16(HL()));
                BC(dec16(BC()));

                boolean pv = BC() != 0;

                setPV(pv);
                setC(c);
                if (pv && !Zset()) {
                    PC(WORD(PC() - 2));
                    return i21_b00010101;
                }
                return i16_b00010000;
            }
            case 186:  /* INDR */ {
                int b;
                pokeb(HL(), inb(BC()));
                B(b = qdec8(B()));
                HL(dec16(HL()));

                setZ(true);
                setN(true);
                if (b != 0) {
                    PC(WORD(PC() - 2));
                    return i21_b00010101;
                }
                return i16_b00010000;
            }
            case 187:  /* OTDR */ {
                int b;
                B(b = qdec8(B()));
                accessor.outb(BC(), peekb(HL()));
                HL(dec16(HL()));

                setZ(true);
                setN(true);
                if (b != 0) {
                    PC(WORD(PC() - 2));
                    return i21_b00010101;
                }
                return i16_b00010000;
            }

        } // end switch

        // NOP
        return i8_b00001000;
    }

    private int execute_cb() {
        REFRESH(1);

        switch (nxtpcb()) {

            case 0:  /* RLC B */ {
                B(rlc(B()));
                return i8_b00001000;
            }
            case 1:  /* RLC C */ {
                C(rlc(C()));
                return i8_b00001000;
            }
            case 2:  /* RLC D */ {
                D(rlc(D()));
                return i8_b00001000;
            }
            case 3:  /* RLC E */ {
                E(rlc(E()));
                return i8_b00001000;
            }
            case 4:  /* RLC H */ {
                H(rlc(H()));
                return i8_b00001000;
            }
            case 5:  /* RLC L */ {
                L(rlc(L()));
                return i8_b00001000;
            }
            case 6:  /* RLC (HL) */ {
                int hl = HL();
                pokeb(hl, rlc(peekb(hl)));
                return i15_b00001111;
            }
            case 7:  /* RLC A */ {
                A(rlc(A()));
                return i8_b00001000;
            }

            case 8:  /* RRC B */ {
                B(rrc(B()));
                return i8_b00001000;
            }
            case 9:  /* RRC C */ {
                C(rrc(C()));
                return i8_b00001000;
            }
            case 10:  /* RRC D */ {
                D(rrc(D()));
                return i8_b00001000;
            }
            case 11:  /* RRC E */ {
                E(rrc(E()));
                return i8_b00001000;
            }
            case 12:  /* RRC H */ {
                H(rrc(H()));
                return i8_b00001000;
            }
            case 13:  /* RRC L */ {
                L(rrc(L()));
                return i8_b00001000;
            }
            case 14:  /* RRC (HL) */ {
                int hl = HL();
                pokeb(hl, rrc(peekb(hl)));
                return i15_b00001111;
            }
            case 15:  /* RRC A */ {
                A(rrc(A()));
                return i8_b00001000;
            }

            case 16:  /* RL B */ {
                B(rl(B()));
                return i8_b00001000;
            }
            case 17:  /* RL C */ {
                C(rl(C()));
                return i8_b00001000;
            }
            case 18:  /* RL D */ {
                D(rl(D()));
                return i8_b00001000;
            }
            case 19:  /* RL E */ {
                E(rl(E()));
                return i8_b00001000;
            }
            case 20:  /* RL H */ {
                H(rl(H()));
                return i8_b00001000;
            }
            case 21:  /* RL L */ {
                L(rl(L()));
                return i8_b00001000;
            }
            case 22:  /* RL (HL) */ {
                int hl = HL();
                pokeb(hl, rl(peekb(hl)));
                return i15_b00001111;
            }
            case 23:  /* RL A */ {
                A(rl(A()));
                return i8_b00001000;
            }

            case 24:  /* RR B */ {
                B(rr(B()));
                return i8_b00001000;
            }
            case 25:  /* RR C */ {
                C(rr(C()));
                return i8_b00001000;
            }
            case 26:  /* RR D */ {
                D(rr(D()));
                return i8_b00001000;
            }
            case 27:  /* RR E */ {
                E(rr(E()));
                return i8_b00001000;
            }
            case 28:  /* RR H */ {
                H(rr(H()));
                return i8_b00001000;
            }
            case 29:  /* RR L */ {
                L(rr(L()));
                return i8_b00001000;
            }
            case 30:  /* RR (HL) */ {
                int hl = HL();
                pokeb(hl, rr(peekb(hl)));
                return i15_b00001111;
            }
            case 31:  /* RR A */ {
                A(rr(A()));
                return i8_b00001000;
            }

            case 32:  /* SLA B */ {
                B(sla(B()));
                return i8_b00001000;
            }
            case 33:  /* SLA C */ {
                C(sla(C()));
                return i8_b00001000;
            }
            case 34:  /* SLA D */ {
                D(sla(D()));
                return i8_b00001000;
            }
            case 35:  /* SLA E */ {
                E(sla(E()));
                return i8_b00001000;
            }
            case 36:  /* SLA H */ {
                H(sla(H()));
                return i8_b00001000;
            }
            case 37:  /* SLA L */ {
                L(sla(L()));
                return i8_b00001000;
            }
            case 38:  /* SLA (HL) */ {
                int hl = HL();
                pokeb(hl, sla(peekb(hl)));
                return i15_b00001111;
            }
            case 39:  /* SLA A */ {
                A(sla(A()));
                return i8_b00001000;
            }

            case 40:  /* SRA B */ {
                B(sra(B()));
                return i8_b00001000;
            }
            case 41:  /* SRA C */ {
                C(sra(C()));
                return i8_b00001000;
            }
            case 42:  /* SRA D */ {
                D(sra(D()));
                return i8_b00001000;
            }
            case 43:  /* SRA E */ {
                E(sra(E()));
                return i8_b00001000;
            }
            case 44:  /* SRA H */ {
                H(sra(H()));
                return i8_b00001000;
            }
            case 45:  /* SRA L */ {
                L(sra(L()));
                return i8_b00001000;
            }
            case 46:  /* SRA (HL) */ {
                int hl = HL();
                pokeb(hl, sra(peekb(hl)));
                return i15_b00001111;
            }
            case 47:  /* SRA A */ {
                A(sra(A()));
                return i8_b00001000;
            }

            case 48:  /* SLS B */ {
                B(sls(B()));
                return i8_b00001000;
            }
            case 49:  /* SLS C */ {
                C(sls(C()));
                return i8_b00001000;
            }
            case 50:  /* SLS D */ {
                D(sls(D()));
                return i8_b00001000;
            }
            case 51:  /* SLS E */ {
                E(sls(E()));
                return i8_b00001000;
            }
            case 52:  /* SLS H */ {
                H(sls(H()));
                return i8_b00001000;
            }
            case 53:  /* SLS L */ {
                L(sls(L()));
                return i8_b00001000;
            }
            case 54:  /* SLS (HL) */ {
                int hl = HL();
                pokeb(hl, sls(peekb(hl)));
                return i15_b00001111;
            }
            case 55:  /* SLS A */ {
                A(sls(A()));
                return i8_b00001000;
            }

            case 56:  /* SRL B */ {
                B(srl(B()));
                return i8_b00001000;
            }
            case 57:  /* SRL C */ {
                C(srl(C()));
                return i8_b00001000;
            }
            case 58:  /* SRL D */ {
                D(srl(D()));
                return i8_b00001000;
            }
            case 59:  /* SRL E */ {
                E(srl(E()));
                return i8_b00001000;
            }
            case 60:  /* SRL H */ {
                H(srl(H()));
                return i8_b00001000;
            }
            case 61:  /* SRL L */ {
                L(srl(L()));
                return i8_b00001000;
            }
            case 62:  /* SRL (HL) */ {
                int hl = HL();
                pokeb(hl, srl(peekb(hl)));
                return i15_b00001111;
            }
            case 63:  /* SRL A */ {
                A(srl(A()));
                return i8_b00001000;
            }

            case 64:  /* BIT 0,B */ {
                bit(x01, B());
                return i8_b00001000;
            }
            case 65:  /* BIT 0,C */ {
                bit(x01, C());
                return i8_b00001000;
            }
            case 66:  /* BIT 0,D */ {
                bit(x01, D());
                return i8_b00001000;
            }
            case 67:  /* BIT 0,E */ {
                bit(x01, E());
                return i8_b00001000;
            }
            case 68:  /* BIT 0,H */ {
                bit(x01, H());
                return i8_b00001000;
            }
            case 69:  /* BIT 0,L */ {
                bit(x01, L());
                return i8_b00001000;
            }
            case 70:  /* BIT 0,(HL) */ {
                bit(x01, peekb(HL()));
                return i12_b00001100;
            }
            case 71:  /* BIT 0,A */ {
                bit(x01, A());
                return i8_b00001000;
            }

            case 72:  /* BIT 1,B */ {
                bit(x02, B());
                return i8_b00001000;
            }
            case 73:  /* BIT 1,C */ {
                bit(x02, C());
                return i8_b00001000;
            }
            case 74:  /* BIT 1,D */ {
                bit(x02, D());
                return i8_b00001000;
            }
            case 75:  /* BIT 1,E */ {
                bit(x02, E());
                return i8_b00001000;
            }
            case 76:  /* BIT 1,H */ {
                bit(x02, H());
                return i8_b00001000;
            }
            case 77:  /* BIT 1,L */ {
                bit(x02, L());
                return i8_b00001000;
            }
            case 78:  /* BIT 1,(HL) */ {
                bit(x02, peekb(HL()));
                return i12_b00001100;
            }
            case 79:  /* BIT 1,A */ {
                bit(x02, A());
                return i8_b00001000;
            }

            case 80:  /* BIT 2,B */ {
                bit(x04, B());
                return i8_b00001000;
            }
            case 81:  /* BIT 2,C */ {
                bit(x04, C());
                return i8_b00001000;
            }
            case 82:  /* BIT 2,D */ {
                bit(x04, D());
                return i8_b00001000;
            }
            case 83:  /* BIT 2,E */ {
                bit(x04, E());
                return i8_b00001000;
            }
            case 84:  /* BIT 2,H */ {
                bit(x04, H());
                return i8_b00001000;
            }
            case 85:  /* BIT 2,L */ {
                bit(x04, L());
                return i8_b00001000;
            }
            case 86:  /* BIT 2,(HL) */ {
                bit(x04, peekb(HL()));
                return i12_b00001100;
            }
            case 87:  /* BIT 2,A */ {
                bit(x04, A());
                return i8_b00001000;
            }

            case 88:  /* BIT 3,B */ {
                bit(x08, B());
                return i8_b00001000;
            }
            case 89:  /* BIT 3,C */ {
                bit(x08, C());
                return i8_b00001000;
            }
            case 90:  /* BIT 3,D */ {
                bit(x08, D());
                return i8_b00001000;
            }
            case 91:  /* BIT 3,E */ {
                bit(x08, E());
                return i8_b00001000;
            }
            case 92:  /* BIT 3,H */ {
                bit(x08, H());
                return i8_b00001000;
            }
            case 93:  /* BIT 3,L */ {
                bit(x08, L());
                return i8_b00001000;
            }
            case 94:  /* BIT 3,(HL) */ {
                bit(x08, peekb(HL()));
                return i12_b00001100;
            }
            case 95:  /* BIT 3,A */ {
                bit(x08, A());
                return i8_b00001000;
            }

            case 96:  /* BIT 4,B */ {
                bit(x10, B());
                return i8_b00001000;
            }
            case 97:  /* BIT 4,C */ {
                bit(x10, C());
                return i8_b00001000;
            }
            case 98:  /* BIT 4,D */ {
                bit(x10, D());
                return i8_b00001000;
            }
            case 99:  /* BIT 4,E */ {
                bit(x10, E());
                return i8_b00001000;
            }
            case 100:  /* BIT 4,H */ {
                bit(x10, H());
                return i8_b00001000;
            }
            case 101:  /* BIT 4,L */ {
                bit(x10, L());
                return i8_b00001000;
            }
            case 102:  /* BIT 4,(HL) */ {
                bit(x10, peekb(HL()));
                return i12_b00001100;
            }
            case 103:  /* BIT 4,A */ {
                bit(x10, A());
                return i8_b00001000;
            }

            case 104:  /* BIT 5,B */ {
                bit(x20, B());
                return i8_b00001000;
            }
            case 105:  /* BIT 5,C */ {
                bit(x20, C());
                return i8_b00001000;
            }
            case 106:  /* BIT 5,D */ {
                bit(x20, D());
                return i8_b00001000;
            }
            case 107:  /* BIT 5,E */ {
                bit(x20, E());
                return i8_b00001000;
            }
            case 108:  /* BIT 5,H */ {
                bit(x20, H());
                return i8_b00001000;
            }
            case 109:  /* BIT 5,L */ {
                bit(x20, L());
                return i8_b00001000;
            }
            case 110:  /* BIT 5,(HL) */ {
                bit(x20, peekb(HL()));
                return i12_b00001100;
            }
            case 111:  /* BIT 5,A */ {
                bit(x20, A());
                return i8_b00001000;
            }

            case 112:  /* BIT 6,B */ {
                bit(x40, B());
                return i8_b00001000;
            }
            case 113:  /* BIT 6,C */ {
                bit(x40, C());
                return i8_b00001000;
            }
            case 114:  /* BIT 6,D */ {
                bit(x40, D());
                return i8_b00001000;
            }
            case 115:  /* BIT 6,E */ {
                bit(x40, E());
                return i8_b00001000;
            }
            case 116:  /* BIT 6,H */ {
                bit(x40, H());
                return i8_b00001000;
            }
            case 117:  /* BIT 6,L */ {
                bit(x40, L());
                return i8_b00001000;
            }
            case 118:  /* BIT 6,(HL) */ {
                bit(x40, peekb(HL()));
                return i12_b00001100;
            }
            case 119:  /* BIT 6,A */ {
                bit(x40, A());
                return i8_b00001000;
            }

            case 120:  /* BIT 7,B */ {
                bit(x80, B());
                return i8_b00001000;
            }
            case 121:  /* BIT 7,C */ {
                bit(x80, C());
                return i8_b00001000;
            }
            case 122:  /* BIT 7,D */ {
                bit(x80, D());
                return i8_b00001000;
            }
            case 123:  /* BIT 7,E */ {
                bit(x80, E());
                return i8_b00001000;
            }
            case 124:  /* BIT 7,H */ {
                bit(x80, H());
                return i8_b00001000;
            }
            case 125:  /* BIT 7,L */ {
                bit(x80, L());
                return i8_b00001000;
            }
            case 126:  /* BIT 7,(HL) */ {
                bit(x80, peekb(HL()));
                return i12_b00001100;
            }
            case 127:  /* BIT 7,A */ {
                bit(x80, A());
                return i8_b00001000;
            }

            case 128:  /* RES 0,B */ {
                B(res(x01, B()));
                return i8_b00001000;
            }
            case 129:  /* RES 0,C */ {
                C(res(x01, C()));
                return i8_b00001000;
            }
            case 130:  /* RES 0,D */ {
                D(res(x01, D()));
                return i8_b00001000;
            }
            case 131:  /* RES 0,E */ {
                E(res(x01, E()));
                return i8_b00001000;
            }
            case 132:  /* RES 0,H */ {
                H(res(x01, H()));
                return i8_b00001000;
            }
            case 133:  /* RES 0,L */ {
                L(res(x01, L()));
                return i8_b00001000;
            }
            case 134:  /* RES 0,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x01, peekb(hl)));
                return i15_b00001111;
            }
            case 135:  /* RES 0,A */ {
                A(res(x01, A()));
                return i8_b00001000;
            }

            case 136:  /* RES 1,B */ {
                B(res(x02, B()));
                return i8_b00001000;
            }
            case 137:  /* RES 1,C */ {
                C(res(x02, C()));
                return i8_b00001000;
            }
            case 138:  /* RES 1,D */ {
                D(res(x02, D()));
                return i8_b00001000;
            }
            case 139:  /* RES 1,E */ {
                E(res(x02, E()));
                return i8_b00001000;
            }
            case 140:  /* RES 1,H */ {
                H(res(x02, H()));
                return i8_b00001000;
            }
            case 141:  /* RES 1,L */ {
                L(res(x02, L()));
                return i8_b00001000;
            }
            case 142:  /* RES 1,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x02, peekb(hl)));
                return i15_b00001111;
            }
            case 143:  /* RES 1,A */ {
                A(res(x02, A()));
                return i8_b00001000;
            }

            case 144:  /* RES 2,B */ {
                B(res(x04, B()));
                return i8_b00001000;
            }
            case 145:  /* RES 2,C */ {
                C(res(x04, C()));
                return i8_b00001000;
            }
            case 146:  /* RES 2,D */ {
                D(res(x04, D()));
                return i8_b00001000;
            }
            case 147:  /* RES 2,E */ {
                E(res(x04, E()));
                return i8_b00001000;
            }
            case 148:  /* RES 2,H */ {
                H(res(x04, H()));
                return i8_b00001000;
            }
            case 149:  /* RES 2,L */ {
                L(res(x04, L()));
                return i8_b00001000;
            }
            case 150:  /* RES 2,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x04, peekb(hl)));
                return i15_b00001111;
            }
            case 151:  /* RES 2,A */ {
                A(res(x04, A()));
                return i8_b00001000;
            }

            case 152:  /* RES 3,B */ {
                B(res(x08, B()));
                return i8_b00001000;
            }
            case 153:  /* RES 3,C */ {
                C(res(x08, C()));
                return i8_b00001000;
            }
            case 154:  /* RES 3,D */ {
                D(res(x08, D()));
                return i8_b00001000;
            }
            case 155:  /* RES 3,E */ {
                E(res(x08, E()));
                return i8_b00001000;
            }
            case 156:  /* RES 3,H */ {
                H(res(x08, H()));
                return i8_b00001000;
            }
            case 157:  /* RES 3,L */ {
                L(res(x08, L()));
                return i8_b00001000;
            }
            case 158:  /* RES 3,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x08, peekb(hl)));
                return i15_b00001111;
            }
            case 159:  /* RES 3,A */ {
                A(res(x08, A()));
                return i8_b00001000;
            }

            case 160:  /* RES 4,B */ {
                B(res(x10, B()));
                return i8_b00001000;
            }
            case 161:  /* RES 4,C */ {
                C(res(x10, C()));
                return i8_b00001000;
            }
            case 162:  /* RES 4,D */ {
                D(res(x10, D()));
                return i8_b00001000;
            }
            case 163:  /* RES 4,E */ {
                E(res(x10, E()));
                return i8_b00001000;
            }
            case 164:  /* RES 4,H */ {
                H(res(x10, H()));
                return i8_b00001000;
            }
            case 165:  /* RES 4,L */ {
                L(res(x10, L()));
                return i8_b00001000;
            }
            case 166:  /* RES 4,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x10, peekb(hl)));
                return i15_b00001111;
            }
            case 167:  /* RES 4,A */ {
                A(res(x10, A()));
                return i8_b00001000;
            }

            case 168:  /* RES 5,B */ {
                B(res(x20, B()));
                return i8_b00001000;
            }
            case 169:  /* RES 5,C */ {
                C(res(x20, C()));
                return i8_b00001000;
            }
            case 170:  /* RES 5,D */ {
                D(res(x20, D()));
                return i8_b00001000;
            }
            case 171:  /* RES 5,E */ {
                E(res(x20, E()));
                return i8_b00001000;
            }
            case 172:  /* RES 5,H */ {
                H(res(x20, H()));
                return i8_b00001000;
            }
            case 173:  /* RES 5,L */ {
                L(res(x20, L()));
                return i8_b00001000;
            }
            case 174:  /* RES 5,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x20, peekb(hl)));
                return i15_b00001111;
            }
            case 175:  /* RES 5,A */ {
                A(res(x20, A()));
                return i8_b00001000;
            }

            case 176:  /* RES 6,B */ {
                B(res(x40, B()));
                return i8_b00001000;
            }
            case 177:  /* RES 6,C */ {
                C(res(x40, C()));
                return i8_b00001000;
            }
            case 178:  /* RES 6,D */ {
                D(res(x40, D()));
                return i8_b00001000;
            }
            case 179:  /* RES 6,E */ {
                E(res(x40, E()));
                return i8_b00001000;
            }
            case 180:  /* RES 6,H */ {
                H(res(x40, H()));
                return i8_b00001000;
            }
            case 181:  /* RES 6,L */ {
                L(res(x40, L()));
                return i8_b00001000;
            }
            case 182:  /* RES 6,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x40, peekb(hl)));
                return i15_b00001111;
            }
            case 183:  /* RES 6,A */ {
                A(res(x40, A()));
                return i8_b00001000;
            }

            case 184:  /* RES 7,B */ {
                B(res(x80, B()));
                return i8_b00001000;
            }
            case 185:  /* RES 7,C */ {
                C(res(x80, C()));
                return i8_b00001000;
            }
            case 186:  /* RES 7,D */ {
                D(res(x80, D()));
                return i8_b00001000;
            }
            case 187:  /* RES 7,E */ {
                E(res(x80, E()));
                return i8_b00001000;
            }
            case 188:  /* RES 7,H */ {
                H(res(x80, H()));
                return i8_b00001000;
            }
            case 189:  /* RES 7,L */ {
                L(res(x80, L()));
                return i8_b00001000;
            }
            case 190:  /* RES 7,(HL) */ {
                int hl = HL();
                pokeb(hl, res(x80, peekb(hl)));
                return i15_b00001111;
            }
            case 191:  /* RES 7,A */ {
                A(res(x80, A()));
                return i8_b00001000;
            }

            case 192:  /* SET 0,B */ {
                B(set(x01, B()));
                return i8_b00001000;
            }
            case 193:  /* SET 0,C */ {
                C(set(x01, C()));
                return i8_b00001000;
            }
            case 194:  /* SET 0,D */ {
                D(set(x01, D()));
                return i8_b00001000;
            }
            case 195:  /* SET 0,E */ {
                E(set(x01, E()));
                return i8_b00001000;
            }
            case 196:  /* SET 0,H */ {
                H(set(x01, H()));
                return i8_b00001000;
            }
            case 197:  /* SET 0,L */ {
                L(set(x01, L()));
                return i8_b00001000;
            }
            case 198:  /* SET 0,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x01, peekb(hl)));
                return i15_b00001111;
            }
            case 199:  /* SET 0,A */ {
                A(set(x01, A()));
                return i8_b00001000;
            }

            case 200:  /* SET 1,B */ {
                B(set(x02, B()));
                return i8_b00001000;
            }
            case 201:  /* SET 1,C */ {
                C(set(x02, C()));
                return i8_b00001000;
            }
            case 202:  /* SET 1,D */ {
                D(set(x02, D()));
                return i8_b00001000;
            }
            case 203:  /* SET 1,E */ {
                E(set(x02, E()));
                return i8_b00001000;
            }
            case 204:  /* SET 1,H */ {
                H(set(x02, H()));
                return i8_b00001000;
            }
            case 205:  /* SET 1,L */ {
                L(set(x02, L()));
                return i8_b00001000;
            }
            case 206:  /* SET 1,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x02, peekb(hl)));
                return i15_b00001111;
            }
            case 207:  /* SET 1,A */ {
                A(set(x02, A()));
                return i8_b00001000;
            }

            case 208:  /* SET 2,B */ {
                B(set(x04, B()));
                return i8_b00001000;
            }
            case 209:  /* SET 2,C */ {
                C(set(x04, C()));
                return i8_b00001000;
            }
            case 210:  /* SET 2,D */ {
                D(set(x04, D()));
                return i8_b00001000;
            }
            case 211:  /* SET 2,E */ {
                E(set(x04, E()));
                return i8_b00001000;
            }
            case 212:  /* SET 2,H */ {
                H(set(x04, H()));
                return i8_b00001000;
            }
            case 213:  /* SET 2,L */ {
                L(set(x04, L()));
                return i8_b00001000;
            }
            case 214:  /* SET 2,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x04, peekb(hl)));
                return i15_b00001111;
            }
            case 215:  /* SET 2,A */ {
                A(set(x04, A()));
                return i8_b00001000;
            }

            case 216:  /* SET 3,B */ {
                B(set(x08, B()));
                return i8_b00001000;
            }
            case 217:  /* SET 3,C */ {
                C(set(x08, C()));
                return i8_b00001000;
            }
            case 218:  /* SET 3,D */ {
                D(set(x08, D()));
                return i8_b00001000;
            }
            case 219:  /* SET 3,E */ {
                E(set(x08, E()));
                return i8_b00001000;
            }
            case 220:  /* SET 3,H */ {
                H(set(x08, H()));
                return i8_b00001000;
            }
            case 221:  /* SET 3,L */ {
                L(set(x08, L()));
                return i8_b00001000;
            }
            case 222:  /* SET 3,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x08, peekb(hl)));
                return i15_b00001111;
            }
            case 223:  /* SET 3,A */ {
                A(set(x08, A()));
                return i8_b00001000;
            }

            case 224:  /* SET 4,B */ {
                B(set(x10, B()));
                return i8_b00001000;
            }
            case 225:  /* SET 4,C */ {
                C(set(x10, C()));
                return i8_b00001000;
            }
            case 226:  /* SET 4,D */ {
                D(set(x10, D()));
                return i8_b00001000;
            }
            case 227:  /* SET 4,E */ {
                E(set(x10, E()));
                return i8_b00001000;
            }
            case 228:  /* SET 4,H */ {
                H(set(x10, H()));
                return i8_b00001000;
            }
            case 229:  /* SET 4,L */ {
                L(set(x10, L()));
                return i8_b00001000;
            }
            case 230:  /* SET 4,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x10, peekb(hl)));
                return i15_b00001111;
            }
            case 231:  /* SET 4,A */ {
                A(set(x10, A()));
                return i8_b00001000;
            }

            case 232:  /* SET 5,B */ {
                B(set(x20, B()));
                return i8_b00001000;
            }
            case 233:  /* SET 5,C */ {
                C(set(x20, C()));
                return i8_b00001000;
            }
            case 234:  /* SET 5,D */ {
                D(set(x20, D()));
                return i8_b00001000;
            }
            case 235:  /* SET 5,E */ {
                E(set(x20, E()));
                return i8_b00001000;
            }
            case 236:  /* SET 5,H */ {
                H(set(x20, H()));
                return i8_b00001000;
            }
            case 237:  /* SET 5,L */ {
                L(set(x20, L()));
                return i8_b00001000;
            }
            case 238:  /* SET 5,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x20, peekb(hl)));
                return i15_b00001111;
            }
            case 239:  /* SET 5,A */ {
                A(set(x20, A()));
                return i8_b00001000;
            }

            case 240:  /* SET 6,B */ {
                B(set(x40, B()));
                return i8_b00001000;
            }
            case 241:  /* SET 6,C */ {
                C(set(x40, C()));
                return i8_b00001000;
            }
            case 242:  /* SET 6,D */ {
                D(set(x40, D()));
                return i8_b00001000;
            }
            case 243:  /* SET 6,E */ {
                E(set(x40, E()));
                return i8_b00001000;
            }
            case 244:  /* SET 6,H */ {
                H(set(x40, H()));
                return i8_b00001000;
            }
            case 245:  /* SET 6,L */ {
                L(set(x40, L()));
                return i8_b00001000;
            }
            case 246:  /* SET 6,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x40, peekb(hl)));
                return i15_b00001111;
            }
            case 247:  /* SET 6,A */ {
                A(set(x40, A()));
                return i8_b00001000;
            }

            case 248:  /* SET 7,B */ {
                B(set(x80, B()));
                return i8_b00001000;
            }
            case 249:  /* SET 7,C */ {
                C(set(x80, C()));
                return i8_b00001000;
            }
            case 250:  /* SET 7,D */ {
                D(set(x80, D()));
                return i8_b00001000;
            }
            case 251:  /* SET 7,E */ {
                E(set(x80, E()));
                return i8_b00001000;
            }
            case 252:  /* SET 7,H */ {
                H(set(x80, H()));
                return i8_b00001000;
            }
            case 253:  /* SET 7,L */ {
                L(set(x80, L()));
                return i8_b00001000;
            }
            case 254:  /* SET 7,(HL) */ {
                int hl = HL();
                pokeb(hl, set(x80, peekb(hl)));
                return i15_b00001111;
            }
            case 255:  /* SET 7,A */ {
                A(set(x80, A()));
                return i8_b00001000;
            }
        }
        return 0;
    }

    private int execute_id() {
        REFRESH(1);
        switch (nxtpcb()) {
            case 0: /* NOP */
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:

            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:

            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:

            case 39:
            case 40:

            case 47:
            case 48:
            case 49:
            case 50:
            case 51:

            case 55:
            case 56:

            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:

            case 71:
            case 72:
            case 73:
            case 74:
            case 75:

            case 79:
            case 80:
            case 81:
            case 82:
            case 83:

            case 87:
            case 88:
            case 89:
            case 90:
            case 91:

            case 95:

            case 120:
            case 121:
            case 122:
            case 123:

            case 127:
            case 128:
            case 129:
            case 130:
            case 131:

            case 135:
            case 136:
            case 137:
            case 138:
            case 139:

            case 143:
            case 144:
            case 145:
            case 146:
            case 147:

            case 151:
            case 152:
            case 153:
            case 154:
            case 155:

            case 159:
            case 160:
            case 161:
            case 162:
            case 163:

            case 167:
            case 168:
            case 169:
            case 170:
            case 171:

            case 175:
            case 176:
            case 177:
            case 178:
            case 179:

            case 183:
            case 184:
            case 185:
            case 186:
            case 187:

            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:

            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:

            case 226:

            case 228:

            case 230:
            case 231:
            case 232:

            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248: {
                PC(dec16(PC()));
                REFRESH(-1);
                return i4_b00000100;
            }

            case 9: /* ADD ID,BC */ {
                ID(add16(ID(), BC()));
                return i15_b00001111;
            }
            case 25: /* ADD ID,DE */ {
                ID(add16(ID(), DE()));
                return i15_b00001111;
            }
            case 41: /* ADD ID,ID */ {
                int id = ID();
                ID(add16(id, id));
                return i15_b00001111;
            }
            case 57: /* ADD ID,SP */ {
                ID(add16(ID(), SP()));
                return i15_b00001111;
            }

            case 33: /* LD ID,nn */ {
                ID(nxtpcw());
                return i14_b00001110;
            }
            case 34: /* LD (nn),ID */ {
                pokew(nxtpcw(), ID());
                return i20_b00010100;
            }
            case 42: /* LD ID,(nn) */ {
                ID(peekw(nxtpcw()));
                return i20_b00010100;
            }
            case 35:/* INC ID */ {
                ID(inc16(ID()));
                return i10_b00001010;
            }
            case 43:/* DEC ID */ {
                ID(dec16(ID()));
                return i10_b00001010;
            }
            case 36:/* INC IDH */ {
                IDH(inc8(IDH()));
                return i8_b00001000;
            }
            case 44:/* INC IDL */ {
                IDL(inc8(IDL()));
                return i8_b00001000;
            }
            case 52:/* INC (ID+d) */ {
                int z = ID_d();
                pokeb(z, inc8(peekb(z)));
                return i23_b00010111;
            }
            case 37:/* DEC IDH */ {
                IDH(dec8(IDH()));
                return i8_b00001000;
            }
            case 45:/* DEC IDL */ {
                IDL(dec8(IDL()));
                return i8_b00001000;
            }
            case 53:/* DEC (ID+d) */ {
                int z = ID_d();
                pokeb(z, dec8(peekb(z)));
                return i23_b00010111;
            }

            case 38: /* LD IDH,n */ {
                IDH(nxtpcb());
                return i11_b00001011;
            }
            case 46: /* LD IDL,n */ {
                IDL(nxtpcb());
                return i11_b00001011;
            }
            case 54: /* LD (ID+d),n */ {
                int z = ID_d();
                pokeb(z, nxtpcb());
                return i19_b00010011;
            }

            case 68: /* LD B,IDH */ {
                B(IDH());
                return i8_b00001000;
            }
            case 69: /* LD B,IDL */ {
                B(IDL());
                return i8_b00001000;
            }
            case 70: /* LD B,(ID+d) */ {
                B(peekb(ID_d()));
                return i19_b00010011;
            }

            case 76: /* LD C,IDH */ {
                C(IDH());
                return i8_b00001000;
            }
            case 77: /* LD C,IDL */ {
                C(IDL());
                return i8_b00001000;
            }
            case 78: /* LD C,(ID+d) */ {
                C(peekb(ID_d()));
                return i19_b00010011;
            }

            case 84: /* LD D,IDH */ {
                D(IDH());
                return i8_b00001000;
            }
            case 85: /* LD D,IDL */ {
                D(IDL());
                return i8_b00001000;
            }
            case 86: /* LD D,(ID+d) */ {
                D(peekb(ID_d()));
                return i19_b00010011;
            }

            case 92: /* LD E,IDH */ {
                E(IDH());
                return i8_b00001000;
            }
            case 93: /* LD E,IDL */ {
                E(IDL());
                return i8_b00001000;
            }
            case 94: /* LD E,(ID+d) */ {
                E(peekb(ID_d()));
                return i19_b00010011;
            }

            case 96: /* LD IDH,B */ {
                IDH(B());
                return i8_b00001000;
            }
            case 97: /* LD IDH,C */ {
                IDH(C());
                return i8_b00001000;
            }
            case 98: /* LD IDH,D */ {
                IDH(D());
                return i8_b00001000;
            }
            case 99: /* LD IDH,E */ {
                IDH(E());
                return i8_b00001000;
            }
            case 100: /* LD IDH,IDH */ {
                return i8_b00001000;
            }
            case 101: /* LD IDH,IDL */ {
                IDH(IDL());
                return i8_b00001000;
            }
            case 102: /* LD H,(ID+d) */ {
                H(peekb(ID_d()));
                return i19_b00010011;
            }
            case 103: /* LD IDH,A */ {
                IDH(A());
                return i8_b00001000;
            }

            case 104: /* LD IDL,B */ {
                IDL(B());
                return i8_b00001000;
            }
            case 105: /* LD IDL,C */ {
                IDL(C());
                return i8_b00001000;
            }
            case 106: /* LD IDL,D */ {
                IDL(D());
                return i8_b00001000;
            }
            case 107: /* LD IDL,E */ {
                IDL(E());
                return i8_b00001000;
            }
            case 108: /* LD IDL,IDH */ {
                IDL(IDH());
                return i8_b00001000;
            }
            case 109: /* LD IDL,IDL */ {
                return i8_b00001000;
            }
            case 110: /* LD L,(ID+d) */ {
                L(peekb(ID_d()));
                return i19_b00010011;
            }
            case 111: /* LD IDL,A */ {
                IDL(A());
                return i8_b00001000;
            }

            case 112: /* LD (ID+d),B */ {
                pokeb(ID_d(), B());
                return i19_b00010011;
            }
            case 113: /* LD (ID+d),C */ {
                pokeb(ID_d(), C());
                return i19_b00010011;
            }
            case 114: /* LD (ID+d),D */ {
                pokeb(ID_d(), D());
                return i19_b00010011;
            }
            case 115: /* LD (ID+d),E */ {
                pokeb(ID_d(), E());
                return i19_b00010011;
            }
            case 116: /* LD (ID+d),H */ {
                pokeb(ID_d(), H());
                return i19_b00010011;
            }
            case 117: /* LD (ID+d),L */ {
                pokeb(ID_d(), L());
                return i19_b00010011;
            }
            case 119: /* LD (ID+d),A */ {
                pokeb(ID_d(), A());
                return i19_b00010011;
            }

            case 124: /* LD A,IDH */ {
                A(IDH());
                return i8_b00001000;
            }
            case 125: /* LD A,IDL */ {
                A(IDL());
                return i8_b00001000;
            }
            case 126: /* LD A,(ID+d) */ {
                A(peekb(ID_d()));
                return i19_b00010011;
            }

            case 132: /* ADD A,IDH */ {
                add_a(IDH());
                return i8_b00001000;
            }
            case 133: /* ADD A,IDL */ {
                add_a(IDL());
                return i8_b00001000;
            }
            case 134: /* ADD A,(ID+d) */ {
                add_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 140: /* ADC A,IDH */ {
                adc_a(IDH());
                return i8_b00001000;
            }
            case 141: /* ADC A,IDL */ {
                adc_a(IDL());
                return i8_b00001000;
            }
            case 142: /* ADC A,(ID+d) */ {
                adc_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 148: /* SUB IDH */ {
                sub_a(IDH());
                return i8_b00001000;
            }
            case 149: /* SUB IDL */ {
                sub_a(IDL());
                return i8_b00001000;
            }
            case 150: /* SUB (ID+d) */ {
                sub_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 156: /* SBC A,IDH */ {
                sbc_a(IDH());
                return i8_b00001000;
            }
            case 157: /* SBC A,IDL */ {
                sbc_a(IDL());
                return i8_b00001000;
            }
            case 158: /* SBC A,(ID+d) */ {
                sbc_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 164: /* AND IDH */ {
                and_a(IDH());
                return i8_b00001000;
            }
            case 165: /* AND IDL */ {
                and_a(IDL());
                return i8_b00001000;
            }
            case 166: /* AND (ID+d) */ {
                and_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 172: /* XOR IDH */ {
                xor_a(IDH());
                return i8_b00001000;
            }
            case 173: /* XOR IDL */ {
                xor_a(IDL());
                return i8_b00001000;
            }
            case 174: /* XOR (ID+d) */ {
                xor_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 180: /* OR IDH */ {
                or_a(IDH());
                return i8_b00001000;
            }
            case 181: /* OR IDL */ {
                or_a(IDL());
                return i8_b00001000;
            }
            case 182: /* OR (ID+d) */ {
                or_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 188: /* CP IDH */ {
                cp_a(IDH());
                return i8_b00001000;
            }
            case 189: /* CP IDL */ {
                cp_a(IDL());
                return i8_b00001000;
            }
            case 190: /* CP (ID+d) */ {
                cp_a(peekb(ID_d()));
                return i19_b00010011;
            }

            case 225: /* POP ID */ {
                ID(popw());
                return i14_b00001110;
            }

            case 233: /* JP (ID) */ {
                PC(ID());
                return i8_b00001000;
            }

            case 249: /* LD SP,ID */ {
                SP(ID());
                return i10_b00001010;
            }

            case 203: /* prefix CB */ {
                // Get index address (offset byte is first)
                int z = ID_d();
                // Opcode comes after offset byte
                int op = nxtpcb();
                execute_id_cb(op, z);
                // Bit instructions take 20 T states, rest 23
                return (op & xC0) == x40
                        ? 20 
                        : 23;
            }

            case 227: /* EX (SP),ID */ {
                int t = ID();
                int sp = SP();
                ID(peekw(sp));
                pokew(sp, t);
                return i23_b00010111;
            }

            case 229:    /* PUSH ID */ {
                pushw(ID());
                return i15_b00001111;
            }
        }
        return 0;
    }

    private void execute_id_cb(int op, int z) {

        switch (op) {

            case 0:  /* RLC B */ {
                B(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 1:  /* RLC C */ {
                C(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 2:  /* RLC D */ {
                D(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 3:  /* RLC E */ {
                E(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 4:  /* RLC H */ {
                H(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 5:  /* RLC L */ {
                L(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 6:  /* RLC (HL) */ {
                pokeb(z, rlc(peekb(z)));
                return;
            }
            case 7:  /* RLC A */ {
                A(op = rlc(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 8:  /* RRC B */ {
                B(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 9:  /* RRC C */ {
                C(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 10:  /* RRC D */ {
                D(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 11:  /* RRC E */ {
                E(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 12:  /* RRC H */ {
                H(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 13:  /* RRC L */ {
                L(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 14:  /* RRC (HL) */ {
                pokeb(z, rrc(peekb(z)));
                return;
            }
            case 15:  /* RRC A */ {
                A(op = rrc(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 16:  /* RL B */ {
                B(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 17:  /* RL C */ {
                C(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 18:  /* RL D */ {
                D(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 19:  /* RL E */ {
                E(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 20:  /* RL H */ {
                H(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 21:  /* RL L */ {
                L(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 22:  /* RL (HL) */ {
                pokeb(z, rl(peekb(z)));
                return;
            }
            case 23:  /* RL A */ {
                A(op = rl(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 24:  /* RR B */ {
                B(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 25:  /* RR C */ {
                C(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 26:  /* RR D */ {
                D(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 27:  /* RR E */ {
                E(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 28:  /* RR H */ {
                H(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 29:  /* RR L */ {
                L(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 30:  /* RR (HL) */ {
                pokeb(z, rr(peekb(z)));
                return;
            }
            case 31:  /* RR A */ {
                A(op = rr(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 32:  /* SLA B */ {
                B(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 33:  /* SLA C */ {
                C(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 34:  /* SLA D */ {
                D(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 35:  /* SLA E */ {
                E(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 36:  /* SLA H */ {
                H(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 37:  /* SLA L */ {
                L(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 38:  /* SLA (HL) */ {
                pokeb(z, sla(peekb(z)));
                return;
            }
            case 39:  /* SLA A */ {
                A(op = sla(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 40:  /* SRA B */ {
                B(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 41:  /* SRA C */ {
                C(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 42:  /* SRA D */ {
                D(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 43:  /* SRA E */ {
                E(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 44:  /* SRA H */ {
                H(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 45:  /* SRA L */ {
                L(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 46:  /* SRA (HL) */ {
                pokeb(z, sra(peekb(z)));
                return;
            }
            case 47:  /* SRA A */ {
                A(op = sra(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 48:  /* SLS B */ {
                B(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 49:  /* SLS C */ {
                C(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 50:  /* SLS D */ {
                D(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 51:  /* SLS E */ {
                E(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 52:  /* SLS H */ {
                H(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 53:  /* SLS L */ {
                L(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 54:  /* SLS (HL) */ {
                pokeb(z, sls(peekb(z)));
                return;
            }
            case 55:  /* SLS A */ {
                A(op = sls(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 56:  /* SRL B */ {
                B(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 57:  /* SRL C */ {
                C(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 58:  /* SRL D */ {
                D(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 59:  /* SRL E */ {
                E(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 60:  /* SRL H */ {
                H(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 61:  /* SRL L */ {
                L(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }
            case 62:  /* SRL (HL) */ {
                pokeb(z, srl(peekb(z)));
                return;
            }
            case 63:  /* SRL A */ {
                A(op = srl(peekb(z)));
                pokeb(z, op);
                return;
            }

            case 64:  /* BIT 0,B */
            case 65:  /* BIT 0,B */
            case 66:  /* BIT 0,B */
            case 67:  /* BIT 0,B */
            case 68:  /* BIT 0,B */
            case 69:  /* BIT 0,B */
            case 70:  /* BIT 0,B */
            case 71:  /* BIT 0,B */ {
                bit(x01, peekb(z));
                return;
            }

            case 72:  /* BIT 1,B */
            case 73:  /* BIT 1,B */
            case 74:  /* BIT 1,B */
            case 75:  /* BIT 1,B */
            case 76:  /* BIT 1,B */
            case 77:  /* BIT 1,B */
            case 78:  /* BIT 1,B */
            case 79:  /* BIT 1,B */ {
                bit(x02, peekb(z));
                return;
            }

            case 80:  /* BIT 2,B */
            case 81:  /* BIT 2,B */
            case 82:  /* BIT 2,B */
            case 83:  /* BIT 2,B */
            case 84:  /* BIT 2,B */
            case 85:  /* BIT 2,B */
            case 86:  /* BIT 2,B */
            case 87:  /* BIT 2,B */ {
                bit(x04, peekb(z));
                return;
            }

            case 88:  /* BIT 3,B */
            case 89:  /* BIT 3,B */
            case 90:  /* BIT 3,B */
            case 91:  /* BIT 3,B */
            case 92:  /* BIT 3,B */
            case 93:  /* BIT 3,B */
            case 94:  /* BIT 3,B */
            case 95:  /* BIT 3,B */ {
                bit(x08, peekb(z));
                return;
            }

            case 96:  /* BIT 4,B */
            case 97:  /* BIT 4,B */
            case 98:  /* BIT 4,B */
            case 99:  /* BIT 4,B */
            case 100:  /* BIT 4,B */
            case 101:  /* BIT 4,B */
            case 102:  /* BIT 4,B */
            case 103:  /* BIT 4,B */ {
                bit(x10, peekb(z));
                return;
            }

            case 104:  /* BIT 5,B */
            case 105:  /* BIT 5,B */
            case 106:  /* BIT 5,B */
            case 107:  /* BIT 5,B */
            case 108:  /* BIT 5,B */
            case 109:  /* BIT 5,B */
            case 110:  /* BIT 5,B */
            case 111:  /* BIT 5,B */ {
                bit(x20, peekb(z));
                return;
            }

            case 112:  /* BIT 6,B */
            case 113:  /* BIT 6,B */
            case 114:  /* BIT 6,B */
            case 115:  /* BIT 6,B */
            case 116:  /* BIT 6,B */
            case 117:  /* BIT 6,B */
            case 118:  /* BIT 6,B */
            case 119:  /* BIT 6,B */ {
                bit(x40, peekb(z));
                return;
            }

            case 120:  /* BIT 7,B */
            case 121:  /* BIT 7,B */
            case 122:  /* BIT 7,B */
            case 123:  /* BIT 7,B */
            case 124:  /* BIT 7,B */
            case 125:  /* BIT 7,B */
            case 126:  /* BIT 7,B */
            case 127:  /* BIT 7,B */ {
                bit(x80, peekb(z));
                return;
            }

            case 128:  /* RES 0,B */ {
                B(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 129:  /* RES 0,C */ {
                C(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 130:  /* RES 0,D */ {
                D(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 131:  /* RES 0,E */ {
                E(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 132:  /* RES 0,H */ {
                H(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 133:  /* RES 0,L */ {
                L(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 134:  /* RES 0,(HL) */ {
                pokeb(z, res(x01, peekb(z)));
                return;
            }
            case 135:  /* RES 0,A */ {
                A(op = res(x01, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 136:  /* RES 1,B */ {
                B(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 137:  /* RES 1,C */ {
                C(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 138:  /* RES 1,D */ {
                D(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 139:  /* RES 1,E */ {
                E(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 140:  /* RES 1,H */ {
                H(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 141:  /* RES 1,L */ {
                L(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 142:  /* RES 1,(HL) */ {
                pokeb(z, res(x02, peekb(z)));
                return;
            }
            case 143:  /* RES 1,A */ {
                A(op = res(x02, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 144:  /* RES 2,B */ {
                B(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 145:  /* RES 2,C */ {
                C(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 146:  /* RES 2,D */ {
                D(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 147:  /* RES 2,E */ {
                E(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 148:  /* RES 2,H */ {
                H(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 149:  /* RES 2,L */ {
                L(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 150:  /* RES 2,(HL) */ {
                pokeb(z, res(x04, peekb(z)));
                return;
            }
            case 151:  /* RES 2,A */ {
                A(op = res(x04, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 152:  /* RES 3,B */ {
                B(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 153:  /* RES 3,C */ {
                C(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 154:  /* RES 3,D */ {
                D(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 155:  /* RES 3,E */ {
                E(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 156:  /* RES 3,H */ {
                H(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 157:  /* RES 3,L */ {
                L(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 158:  /* RES 3,(HL) */ {
                pokeb(z, res(x08, peekb(z)));
                return;
            }
            case 159:  /* RES 3,A */ {
                A(op = res(x08, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 160:  /* RES 4,B */ {
                B(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 161:  /* RES 4,C */ {
                C(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 162:  /* RES 4,D */ {
                D(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 163:  /* RES 4,E */ {
                E(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 164:  /* RES 4,H */ {
                H(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 165:  /* RES 4,L */ {
                L(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 166:  /* RES 4,(HL) */ {
                pokeb(z, res(x10, peekb(z)));
                return;
            }
            case 167:  /* RES 4,A */ {
                A(op = res(x10, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 168:  /* RES 5,B */ {
                B(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 169:  /* RES 5,C */ {
                C(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 170:  /* RES 5,D */ {
                D(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 171:  /* RES 5,E */ {
                E(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 172:  /* RES 5,H */ {
                H(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 173:  /* RES 5,L */ {
                L(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 174:  /* RES 5,(HL) */ {
                pokeb(z, res(x20, peekb(z)));
                return;
            }
            case 175:  /* RES 5,A */ {
                A(op = res(x20, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 176:  /* RES 6,B */ {
                B(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 177:  /* RES 6,C */ {
                C(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 178:  /* RES 6,D */ {
                D(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 179:  /* RES 6,E */ {
                E(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 180:  /* RES 6,H */ {
                H(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 181:  /* RES 6,L */ {
                L(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 182:  /* RES 6,(HL) */ {
                pokeb(z, res(x40, peekb(z)));
                return;
            }
            case 183:  /* RES 6,A */ {
                A(op = res(x40, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 184:  /* RES 7,B */ {
                B(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 185:  /* RES 7,C */ {
                C(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 186:  /* RES 7,D */ {
                D(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 187:  /* RES 7,E */ {
                E(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 188:  /* RES 7,H */ {
                H(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 189:  /* RES 7,L */ {
                L(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 190:  /* RES 7,(HL) */ {
                pokeb(z, res(x80, peekb(z)));
                return;
            }
            case 191:  /* RES 7,A */ {
                A(op = res(x80, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 192:  /* SET 0,B */ {
                B(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 193:  /* SET 0,C */ {
                C(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 194:  /* SET 0,D */ {
                D(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 195:  /* SET 0,E */ {
                E(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 196:  /* SET 0,H */ {
                H(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 197:  /* SET 0,L */ {
                L(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 198:  /* SET 0,(HL) */ {
                pokeb(z, set(x01, peekb(z)));
                return;
            }
            case 199:  /* SET 0,A */ {
                A(op = set(x01, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 200:  /* SET 1,B */ {
                B(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 201:  /* SET 1,C */ {
                C(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 202:  /* SET 1,D */ {
                D(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 203:  /* SET 1,E */ {
                E(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 204:  /* SET 1,H */ {
                H(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 205:  /* SET 1,L */ {
                L(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 206:  /* SET 1,(HL) */ {
                pokeb(z, set(x02, peekb(z)));
                return;
            }
            case 207:  /* SET 1,A */ {
                A(op = set(x02, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 208:  /* SET 2,B */ {
                B(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 209:  /* SET 2,C */ {
                C(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 210:  /* SET 2,D */ {
                D(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 211:  /* SET 2,E */ {
                E(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 212:  /* SET 2,H */ {
                H(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 213:  /* SET 2,L */ {
                L(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 214:  /* SET 2,(HL) */ {
                pokeb(z, set(x04, peekb(z)));
                return;
            }
            case 215:  /* SET 2,A */ {
                A(op = set(x04, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 216:  /* SET 3,B */ {
                B(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 217:  /* SET 3,C */ {
                C(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 218:  /* SET 3,D */ {
                D(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 219:  /* SET 3,E */ {
                E(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 220:  /* SET 3,H */ {
                H(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 221:  /* SET 3,L */ {
                L(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 222:  /* SET 3,(HL) */ {
                pokeb(z, set(x08, peekb(z)));
                return;
            }
            case 223:  /* SET 3,A */ {
                A(op = set(x08, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 224:  /* SET 4,B */ {
                B(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 225:  /* SET 4,C */ {
                C(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 226:  /* SET 4,D */ {
                D(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 227:  /* SET 4,E */ {
                E(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 228:  /* SET 4,H */ {
                H(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 229:  /* SET 4,L */ {
                L(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 230:  /* SET 4,(HL) */ {
                pokeb(z, set(x10, peekb(z)));
                return;
            }
            case 231:  /* SET 4,A */ {
                A(op = set(x10, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 232:  /* SET 5,B */ {
                B(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 233:  /* SET 5,C */ {
                C(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 234:  /* SET 5,D */ {
                D(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 235:  /* SET 5,E */ {
                E(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 236:  /* SET 5,H */ {
                H(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 237:  /* SET 5,L */ {
                L(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 238:  /* SET 5,(HL) */ {
                pokeb(z, set(x20, peekb(z)));
                return;
            }
            case 239:  /* SET 5,A */ {
                A(op = set(x20, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 240:  /* SET 6,B */ {
                B(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 241:  /* SET 6,C */ {
                C(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 242:  /* SET 6,D */ {
                D(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 243:  /* SET 6,E */ {
                E(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 244:  /* SET 6,H */ {
                H(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 245:  /* SET 6,L */ {
                L(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 246:  /* SET 6,(HL) */ {
                pokeb(z, set(x40, peekb(z)));
                return;
            }
            case 247:  /* SET 6,A */ {
                A(op = set(x40, peekb(z)));
                pokeb(z, op);
                return;
            }

            case 248:  /* SET 7,B */ {
                B(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 249:  /* SET 7,C */ {
                C(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 250:  /* SET 7,D */ {
                D(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 251:  /* SET 7,E */ {
                E(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 252:  /* SET 7,H */ {
                H(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 253:  /* SET 7,L */ {
                L(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
            case 254:  /* SET 7,(HL) */ {
                pokeb(z, set(x80, peekb(z)));
                return;
            }
            case 255:  /* SET 7,A */ {
                A(op = set(x80, peekb(z)));
                pokeb(z, op);
                return;
            }
        }
    }

    private int in_bc() {
        int ans = inb(BC());

        setZ(ans == 0);
        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setPV(parity[ans]);
        setN(false);
        setH(false);

        return ans;
    }

    /**
     * Add with carry - alters all flags (CHECKED)
     */
    private void adc_a(int b) {
        int a = A();
        int c = Cset() ? 1 : 0;
        int wans = a + b + c;
        int ans = LO(wans);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setC((wans & x100) != 0);
        setPV(((a ^ ~b) & (a ^ ans) & x80) != 0);
        setH((((a & x0F) + (b & x0F) + c) & F_H) != 0);
        setN(false);

        A(ans);
    }

    /**
     * Add - alters all flags (CHECKED)
     */
    private void add_a(int b) {
        int a = A();
        int wans = a + b;
        int ans = LO(wans);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setC((wans & x100) != 0);
        setPV(((a ^ ~b) & (a ^ ans) & x80) != 0);
        setH((((a & x0F) + (b & x0F)) & F_H) != 0);
        setN(false);

        A(ans);
    }

    /**
     * Subtract with carry - alters all flags (CHECKED)
     */
    private void sbc_a(int b) {
        int a = A();
        int c = Cset() ? 1 : 0;
        int wans = a - b - c;
        int ans = LO(wans);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setC((wans & x100) != 0);
        setPV(((a ^ b) & (a ^ ans) & x80) != 0);
        setH((((a & x0F) - (b & x0F) - c) & F_H) != 0);
        setN(true);

        A(ans);
    }

    /**
     * Subtract - alters all flags (CHECKED)
     */
    private void sub_a(int b) {
        int a = A();
        int wans = a - b;
        int ans = LO(wans);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setC((wans & x100) != 0);
        setPV(((a ^ b) & (a ^ ans) & x80) != 0);
        setH((((a & x0F) - (b & x0F)) & F_H) != 0);
        setN(true);

        A(ans);
    }

    /**
     * Rotate Left - alters H N C 3 5 flags (CHECKED)
     */
    private void rlc_a() {
        int ans = A();
        boolean c = (ans & x80) != 0;

        if (c) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }
        ans = LO(ans);

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setN(false);
        setH(false);
        setC(c);

        A(ans);
    }

    /**
     * Rotate Right - alters H N C 3 5 flags (CHECKED)
     */
    private void rrc_a() {
        int ans = A();
        boolean c = (ans & x01) != 0;

        if (c) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setN(false);
        setH(false);
        setC(c);

        A(ans);
    }

    /**
     * Rotate Left through Carry - alters H N C 3 5 flags (CHECKED)
     */
    private void rl_a() {
        int ans = A();
        boolean c = (ans & x80) != 0;

        if (Cset()) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }

        ans = LO(ans);

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setN(false);
        setH(false);
        setC(c);

        A(ans);
    }

    /**
     * Rotate Right through Carry - alters H N C 3 5 flags (CHECKED)
     */
    private void rr_a() {
        int ans = A();
        boolean c = (ans & x01) != 0;

        if (Cset()) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setN(false);
        setH(false);
        setC(c);

        A(ans);
    }

    /**
     * Compare - alters all flags (CHECKED)
     */
    private void cp_a(int b) {
        int a = A();
        int wans = a - b;
        int ans = LO(wans);

        setS((ans & F_S) != 0);
        set3((b & F_3) != 0);
        set5((b & F_5) != 0);
        setN(true);
        setZ(ans == 0);
        setC((wans & x100) != 0);
        setH((((a & x0F) - (b & x0F)) & F_H) != 0);
        setPV(((a ^ b) & (a ^ ans) & x80) != 0);
    }

    /**
     * Bitwise and - alters all flags (CHECKED)
     */
    private void and_a(int b) {
        int ans = A() & b;

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setH(true);
        setPV(parity[ans]);
        setZ(ans == 0);
        setN(false);
        setC(false);

        A(ans);
    }

    /**
     * Bitwise or - alters all flags (CHECKED)
     */
    private void or_a(int b) {
        int ans = A() | b;

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setH(false);
        setPV(parity[ans]);
        setZ(ans == 0);
        setN(false);
        setC(false);

        A(ans);
    }

    /**
     * Bitwise exclusive or - alters all flags (CHECKED)
     */
    private void xor_a(int b) {
        int ans = LO(A() ^ b);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setH(false);
        setPV(parity[ans]);
        setZ(ans == 0);
        setN(false);
        setC(false);

        A(ans);
    }

    /**
     * Negate (Two's complement) - alters all flags (CHECKED)
     */
    private void neg_a() {
        int t = A();

        A(0);
        sub_a(t);
    }

    /**
     * One's complement - alters N H 3 5 flags (CHECKED)
     */
    private void cpl_a() {
        int ans = A() ^ xFF;

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setH(true);
        setN(true);

        A(ans);
    }

    /**
     * Decimal Adjust Accumulator - alters all flags (CHECKED)
     */
    private void daa_a() {
        int ans = A();
        int incr = 0;
        boolean carry = Cset();

        if (Hset() || (ans & x0F) > x09) {
            incr |= x06;
        }
        if (carry || (ans > x9F) || ((ans > x8F) && ((ans & x0F) > x09))) {
            incr |= x60;
        }
        if (ans > x99) {
            carry = true;
        }
        if (Nset()) {
            sub_a(incr);
        } else {
            add_a(incr);
        }

        ans = A();

        setC(carry);
        setPV(parity[ans]);
    }

    /**
     * Load a with i - (NOT CHECKED)
     */
    private void ld_a_i() {
        int ans = I();

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(IFF2());
        setH(false);
        setN(false);

        A(ans);
    }

    /**
     * Load a with r - (NOT CHECKED)
     */
    private void ld_a_r() {
        int ans = R();

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(IFF2());
        setH(false);
        setN(false);

        A(ans);
    }

    /**
     * Rotate right through a and (hl) - (NOT CHECKED)
     */
    private void rrd_a() {
        int ans = A();
        int t = peekb(HL());
        int q = t;

        t = (t >> 4) | (ans << 4);
        ans = (ans & xF0) | (q & x0F);
        pokeb(HL(), t);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(IFF2());
        setH(false);
        setN(false);

        A(ans);
    }

    /**
     * Rotate left through a and (hl) - (NOT CHECKED)
     */
    private void rld_a() {
        int ans = A();
        int t = peekb(HL());
        int q = t;

        t = (t << 4) | (ans & x0F);
        ans = (ans & xF0) | (q >> 4);
        pokeb(HL(), LO(t));

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(IFF2());
        setH(false);
        setN(false);

        A(ans);
    }

    /**
     * Test bit - alters all but C flag (CHECKED)
     */
    private void bit(int b, int r) {
        boolean bitSet = ((r & b) != 0);

        setN(false);
        setH(true);
        set3((r & F_3) != 0);
        set5((r & F_5) != 0);
        setS(b == F_S && bitSet);
        setZ(!bitSet);
        setPV(!bitSet);
    }

    /**
     * Set carry flag - alters N H 3 5 C flags (CHECKED)
     */
    private void scf() {
        int ans = A();

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setN(false);
        setH(false);
        setC(true);
    }

    /**
     * Complement carry flag - alters N 3 5 C flags (CHECKED)
     */
    private void ccf() {
        int ans = A();

        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setN(false);
        setC(!Cset());
    }

    /**
     * Rotate left - alters all flags (CHECKED)
     */
    private int rlc(int ans) {
        boolean c = (ans & x80) != 0;

        if (c) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }
        ans = LO(ans);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Rotate right - alters all flags (CHECKED)
     */
    private int rrc(int ans) {
        boolean c = (ans & x01) != 0;

        if (c) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Rotate left through carry - alters all flags (CHECKED)
     */
    private int rl(int ans) {
        boolean c = (ans & x80) != 0;

        if (Cset()) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }
        ans = LO(ans);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Rotate right through carry - alters all flags (CHECKED)
     */
    private int rr(int ans) {
        boolean c = (ans & x01) != 0;

        if (Cset()) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Shift Left Arithmetically - alters all flags (CHECKED)
     */
    private int sla(int ans) {
        boolean c = (ans & x80) != 0;
        ans = LO(ans << 1);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Shift Left and Set - alters all flags (CHECKED)
     */
    private int sls(int ans) {
        boolean c = (ans & x80) != 0;
        ans = LO((ans << 1) | x01);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Shift Right Arithmetically - alters all flags (CHECKED)
     */
    private int sra(int ans) {
        boolean c = (ans & x01) != 0;
        ans = (ans >> 1) | (ans & x80);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Shift Right Logically - alters all flags (CHECKED)
     */
    private int srl(int ans) {
        boolean c = (ans & x01) != 0;
        ans = ans >> 1;

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(parity[ans]);
        setH(false);
        setN(false);
        setC(c);

        return ans;
    }

    /**
     * Decrement - alters all but C flag (CHECKED)
     */
    private int dec8(int ans) {
        boolean pv = (ans == x80);
        boolean h = (((ans & x0F) - 1) & F_H) != 0;
        ans = LO(ans - 1);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(pv);
        setH(h);
        setN(true);

        return ans;
    }

    /**
     * Increment - alters all but C flag (CHECKED)
     */
    private int inc8(int ans) {
        boolean pv = (ans == x7F);
        boolean h = (((ans & x0F) + 1) & F_H) != 0;
        ans = LO(ans + 1);

        setS((ans & F_S) != 0);
        set3((ans & F_3) != 0);
        set5((ans & F_5) != 0);
        setZ(ans == 0);
        setPV(pv);
        setH(h);
        setN(false);

        return ans;
    }

    /**
     * Add with carry - (NOT CHECKED)
     */
    private int adc16(int a, int b) {
        int c = Cset() ? 1 : 0;
        int lans = a + b + c;
        int ans = WORD(lans);

        setS((ans & (F_S << 8)) != 0);
        set3((ans & (F_3 << 8)) != 0);
        set5((ans & (F_5 << 8)) != 0);
        setZ(ans == 0);
        setC((lans & x10000) != 0);
        setPV(((a ^ ~b) & (a ^ ans) & x8000) != 0);
        setH((((a & x0FFF) + (b & x0FFF) + c) & x1000) != 0);
        setN(false);

        return ans;
    }

    /**
     * Add - (NOT CHECKED)
     */
    private int add16(int a, int b) {
        int lans = a + b;
        int ans = WORD(lans);

        set3((ans & (F_3 << 8)) != 0);
        set5((ans & (F_5 << 8)) != 0);
        setC((lans & x10000) != 0);
        setH((((a & x0FFF) + (b & x0FFF)) & x1000) != 0);
        setN(false);

        return ans;
    }

    /**
     * Add with carry - (NOT CHECKED)
     */
    private int sbc16(int a, int b) {
        int c = Cset() ? 1 : 0;
        int lans = a - b - c;
        int ans = WORD(lans);

        setS((ans & (F_S << 8)) != 0);
        set3((ans & (F_3 << 8)) != 0);
        set5((ans & (F_5 << 8)) != 0);
        setZ(ans == 0);
        setC((lans & x10000) != 0);
        setPV(((a ^ b) & (a ^ ans) & x8000) != 0);
        setH((((a & x0FFF) - (b & x0FFF) - c) & x1000) != 0);
        setN(true);

        return ans;
    }

    /**
     * EXX
     */
    private void exx() {
        int t;

        t = HL();
        HL(_HL_);
        _HL_ = t;

        t = DE();
        DE(_DE_);
        _DE_ = t;

        t = BC();
        BC(_BC_);
        _BC_ = t;
    }

    /**
     * EX AF,AF'
     */
    private void ex_af_af() {
        int t;
        t = AF();
        AF(_AF_);
        _AF_ = t;
    }

    /**
     * Quick Increment : no flags
     */
    private static int inc16(int a) {
        return WORD(a + 1);
    }

    private static int qinc8(int a) {
        return LO(a + 1);
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
    /**
     * Bit toggling
     */
    private static int res(int bit, int val) {
        return val & ~bit;
    }

    private static int set(int bit, int val) {
        return val | bit;
    }

}