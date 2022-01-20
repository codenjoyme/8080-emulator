package spec;

/**
 * @(#)Z80.java 1.1 27/04/97 Adam Davidson & Andrew Pollard
 */

import spec.assembler.Assembler;
import spec.assembler.Command;

import static spec.Constants.*;
import static spec.WordMath.*;

/**
 * <p>The Z80 class emulates the Zilog Z80 microprocessor.</p>
 *
 * @author <A HREF="http://www.odie.demon.co.uk/spectrum">Adam Davidson & Andrew Pollard</A>
 * @version 1.1 27 Apr 1997
 */

public class CPU extends Registry {

    private static final boolean[] parity = new boolean[256]; static {
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
    
    private int interrupt;
    private Assembler asm;

    public CPU(double clockFrequencyInMHz, Data data) {
        super(data);
        // Количество тактов на 1 прерывание, которое происходит 50 раз в секунду.
        // 1000000/50 раз в секунду
        interrupt = (int) ((clockFrequencyInMHz * 1e6) / 50);
        asm = new Assembler();
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
        int bite = read8(PC());
        PC(inc16(PC()));
        return bite;
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

    /**
     * Interrupt handlers
     */
    private static boolean interruptNeeded(int tick) {
        return tick >= 0;  
    }

    /**
     * Основной цикл выполнения кода
     */
    public void execute() {
        // закладываем время до прерывания
        int ticks = -interrupt;

        // цикл выборки/выполнения
        while (true) {
            if (interruptNeeded(ticks)) {
                if (!data.interrupt()) {
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
//                case 11:    /* DEC BC */ {
//                    BC(dec16(BC()));
//                    ticks += 6;
//                    break;
//                }
//                case 27:    /* DEC DE */ {
//                    DE(dec16(DE()));
//                    ticks += 6;
//                    break;
//                }
//                case 43:    /* DEC HL */ {
//                    HL(dec16(HL()));
//                    ticks += 6;
//                    break;
//                }
//                case 59:    /* DEC SP */ {
//                    SP(dec16(SP()));
//                    ticks += 6;
//                    break;
//                }

                /* INC * */
//                case 4:    /* INC B */ {
//                    B(inc8(B()));
//                    ticks += 4;
//                    break;
//                }
//                case 12:    /* INC C */ {
//                    C(inc8(C()));
//                    ticks += 4;
//                    break;
//                }
//                case 20:    /* INC D */ {
//                    D(inc8(D()));
//                    ticks += 4;
//                    break;
//                }
//                case 28:    /* INC E */ {
//                    E(inc8(E()));
//                    ticks += 4;
//                    break;
//                }
//                case 36:    /* INC H */ {
//                    H(inc8(H()));
//                    ticks += 4;
//                    break;
//                }
//                case 44:    /* INC L */ {
//                    L(inc8(L()));
//                    ticks += 4;
//                    break;
//                }
//                case 52:    /* INC (HL) */ {
//                    write8(HL(), inc8(read8(HL())));
//                    ticks += 11;
//                    break;
//                }
//                case 60:    /* INC A() */ {
//                    A(inc8(A()));
//                    ticks += 4;
//                    break;
//                }

                /* DEC * */
//                case 5:    /* DEC B */ {
//                    B(dec8(B()));
//                    ticks += 4;
//                    break;
//                }
//                case 13:    /* DEC C */ {
//                    C(dec8(C()));
//                    ticks += 4;
//                    break;
//                }
//                case 21:    /* DEC D */ {
//                    D(dec8(D()));
//                    ticks += 4;
//                    break;
//                }
//                case 29:    /* DEC E */ {
//                    E(dec8(E()));
//                    ticks += 4;
//                    break;
//                }
//                case 37:    /* DEC H */ {
//                    H(dec8(H()));
//                    ticks += 4;
//                    break;
//                }
//                case 45:    /* DEC L */ {
//                    L(dec8(L()));
//                    ticks += 4;
//                    break;
//                }
//                case 53:    /* DEC (HL) */ {
//                    int hl = HL();
//                    write8(hl, dec8(read8(hl)));
//                    ticks += 11;
//                    break;
//                }
//                case 61:    /* DEC A() */ {
//                    A(dec8(A()));
//                    ticks += 4;
//                    break;
//                }

                /* LD *,N */
                case 6:    /* LD B,n */ {
                    B(read8PC());
                    ticks += 7;
                    break;
                }
                case 14:    /* LD C,n */ {
                    C(read8PC());
                    ticks += 7;
                    break;
                }
                case 22:    /* LD D,n */ {
                    D(read8PC());
                    ticks += 7;
                    break;
                }
                case 30:    /* LD E,n */ {
                    E(read8PC());
                    ticks += 7;
                    break;
                }
                case 38:    /* LD H,n */ {
                    H(read8PC());
                    ticks += 7;
                    break;
                }
                case 46:    /* LD L,n */ {
                    L(read8PC());
                    ticks += 7;
                    break;
                }
                case 54:    /* LD (HL),n */ {
                    write8(HL(), read8PC());
                    ticks += 10;
                    break;
                }
                case 62:    /* LD A,n */ {
                    A(read8PC());
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
                    B(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 71:    /* LD B,A */ {
                    B(A());
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
                    C(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 79:    /* LD C,A */ {
                    C(A());
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
                    D(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 87:    /* LD D,A */ {
                    D(A());
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
                    E(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 95:    /* LD E,A */ {
                    E(A());
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
                    H(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 103:    /* LD H,A */ {
                    H(A());
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
                    L(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 111:    /* LD L,A */ {
                    L(A());
                    ticks += 4;
                    break;
                }

                /* LD (HL),* */
                case 112:    /* LD (HL),B */ {
                    write8(HL(), B());
                    ticks += 7;
                    break;
                }
                case 113:    /* LD (HL),C */ {
                    write8(HL(), C());
                    ticks += 7;
                    break;
                }
                case 114:    /* LD (HL),D */ {
                    write8(HL(), D());
                    ticks += 7;
                    break;
                }
                case 115:    /* LD (HL),E */ {
                    write8(HL(), E());
                    ticks += 7;
                    break;
                }
                case 116:    /* LD (HL),H */ {
                    write8(HL(), H());
                    ticks += 7;
                    break;
                }
                case 117:    /* LD (HL),L */ {
                    write8(HL(), L());
                    ticks += 7;
                    break;
                }
                case 118:    /* HALT */ {
                    int haltsToInterrupt = (-ticks - 1) / 4 + 1;
                    ticks += haltsToInterrupt * 4;
                    break;
                }
                case 119:    /* LD (HL),A */ {
                    write8(HL(), A());
                    ticks += 7;
                    break;
                }

                /* LD A,* */
                case 120:    /* LD A,B */ {
                    A(B());
                    ticks += 4;
                    break;
                }
                case 121:    /* LD A,C */ {
                    A(C());
                    ticks += 4;
                    break;
                }
                case 122:    /* LD A,D */ {
                    A(D());
                    ticks += 4;
                    break;
                }
                case 123:    /* LD A,E */ {
                    A(E());
                    ticks += 4;
                    break;
                }
                case 124:    /* LD A,H */ {
                    A(H());
                    ticks += 4;
                    break;
                }
                case 125:    /* LD A,L */ {
                    A(L());
                    ticks += 4;
                    break;
                }
                case 126:    /* LD A,(HL) */ {
                    A(read8(HL()));
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
                    add_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 135:    /* ADD A,A */ {
                    add_a(A());
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
                    adc_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 143:    /* ADC A,A */ {
                    adc_a(A());
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
                    sub_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 151:    /* SUB A() */ {
                    sub_a(A());
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
                    sbc_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 159:    /* SBC A,A */ {
                    sbc_a(A());
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
                    and_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 167:    /* AND A() */ {
                    and_a(A());
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
                    xor_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 175:    /* XOR A() */ {
                    xor_a(A());
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
                    or_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 183:    /* OR A() */ {
                    or_a(A());
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
                    cp_a(read8(HL()));
                    ticks += 7;
                    break;
                }
                case 191:    /* CP A() */ {
                    cp_a(A());
                    ticks += 4;
                    break;
                }

                /* RET cc */
                case 192:    /* RET NZ */ {
                    if (!tz()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 200:    /* RET Z */ {
                    if (tz()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 208:    /* RET NC */ {
                    if (!tc()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 216:    /* RET C */ {
                    if (tc()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 224:    /* RET PO */ {
                    if (!tp()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 232:    /* RET PE */ {
                    if (tp()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 240:    /* RET P */ {
                    if (!ts()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }
                case 248:    /* RET M */ {
                    if (ts()) {
                        popPC();
                        ticks += 11;
                    } else {
                        ticks += 15;
                    }
                    break;
                }

                /* POP,Various */
                case 193:    /* POP BC */ {
                    BC(pop16());
                    ticks += 10;
                    break;
                }
                case 201: /* RET */ {
                    popPC();
                    ticks += 10;
                    break;
                }
                case 209:    /* POP DE */ {
                    DE(pop16());
                    ticks += 10;
                    break;
                }
                case 225:    /* POP HL */ {
                    HL(pop16());
                    ticks += 10;
                    break;
                }
                case 233: /* JP (HL) */ {
                    PC(HL());
                    ticks += 4;
                    break;
                }
                case 241:    /* POP AF */ {
                    AF(pop16());
                    ticks += 10;
                    break;
                }
                case 249:    /* LD SP,HL */ {
                    SP(HL());
                    ticks += 6;
                    break;
                }

                /* JP cc,nn */
                case 194:    /* JP NZ,nn */ {
                    if (!tz()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 202:    /* JP Z,nn */ {
                    if (tz()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 210:    /* JP NC,nn */ {
                    if (!tc()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 218:    /* JP C,nn */ {
                    if (tc()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 226:    /* JP PO,nn */ {
                    if (!tp()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 234:    /* JP PE,nn */ {
                    if (tp()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 242:    /* JP P,nn */ {
                    if (!ts()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                case 250:    /* JP M,nn */ {
                    if (ts()) {
                        PC(read16PC());
                    } else {
                        PC(word(PC() + 2));
                    }
                    ticks += 10;
                    break;
                }
                
                /* Various */
                case 195:    /* JP nn */ {
                    PC(read16(PC()));
                    ticks += 10;
                    break;
                }
                case 211:    /* OUT (n),A */ {
                    data.out8(read8PC(), A());
                    ticks += 11;
                    break;
                }
                case 219:    /* IN A,(n) */ {
                    A(inb((A() << 8) | read8PC()));
                    ticks += 11;
                    break;
                }
                case 227:    /* EX (SP),HL */ {
                    int t = HL();
                    int sp = SP();
                    HL(read16(sp));
                    write16(sp, t);
                    ticks += 19;
                    break;
                }
                case 235:    /* EX DE,HL */ {
                    int t = HL();
                    HL(DE());
                    DE(t);
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
                case 204: /* CALL Z,nn */ {
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
                case 212: /* CALL NC,nn */ {
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
                case 220: /* CALL C,nn */ {
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
                case 228: /* CALL PO,nn */ {
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
                case 236: /* CALL PE,nn */ {
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
                case 244: /* CALL P,nn */ {
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
                case 252: /* CALL M,nn */ {
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

                /* PUSH,Various */
                case 197:    /* PUSH BC */ {
                    push16(BC());
                    ticks += 11;
                    break;
                }
                case 205:    /* CALL nn */ {
                    int t = read16PC();
                    pushPC();
                    PC(t);
                    ticks += 17;
                    break;
                }
                case 213:    /* PUSH DE */ {
                    push16(DE());
                    ticks += 11;
                    break;
                }
                case 229:    /* PUSH HL */ {
                    push16(HL());
                    ticks += 11;
                    break;
                }
                case 245:    /* PUSH AF */ {
                    push16(AF());
                    ticks += 11;
                    break;
                }
                /* op A,N */
                case 198: /* ADD A,N */ {
                    add_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 206: /* ADC A,N */ {
                    adc_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 214: /* SUB N */ {
                    sub_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 222: /* SBC A,N */ {
                    sbc_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 230: /* AND N */ {
                    and_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 238: /* XOR N */ {
                    xor_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 246: /* OR N */ {
                    or_a(read8PC());
                    ticks += 7;
                    break;
                }
                case 254: /* CP N */ {
                    cp_a(read8PC());
                    ticks += 7;
                    break;
                }

                /* RST n */
                case 199:    /* RST 0 */ {
                    pushPC();
                    PC(0);
                    ticks += 11;
                    break;
                }
                case 207:    /* RST 8 */ {
                    pushPC();
                    PC(8);
                    ticks += 11;
                    break;
                }
                case 215:    /* RST 16 */ {
                    pushPC();
                    PC(16);
                    ticks += 11;
                    break;
                }
                case 223:    /* RST 24 */ {
                    pushPC();
                    PC(24);
                    ticks += 11;
                    break;
                }
                case 231:    /* RST 32 */ {
                    pushPC();
                    PC(32);
                    ticks += 11;
                    break;
                }
                case 239:    /* RST 40 */ {
                    pushPC();
                    PC(40);
                    ticks += 11;
                    break;
                }
                case 247:    /* RST 48 */ {
                    pushPC();
                    PC(48);
                    ticks += 11;
                    break;
                }
                case 255:    /* RST 56 */ {
                    pushPC();
                    PC(56);
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
        int a = A();
        int c = tci();
        int wans = a + b + c;
        int ans = lo(wans);

        ts((ans & T7s) != 0);
        tz(ans == 0);
        tc((wans & x100) != 0);
        tp(((a ^ ~b) & (a ^ ans) & x80) != 0);
        th((((a & x0F) + (b & x0F) + c) & T4h) != 0);

        A(ans);
    }

    /**
     * Add - alters all flags (CHECKED)
     */
    private void add_a(int b) {
        int a = A();
        int wans = a + b;
        int ans = lo(wans);

        ts((ans & T7s) != 0);
        tz(ans == 0);
        tc((wans & x100) != 0);
        tp(((a ^ ~b) & (a ^ ans) & x80) != 0);
        th((((a & x0F) + (b & x0F)) & T4h) != 0);

        A(ans);
    }

    /**
     * Subtract with carry - alters all flags (CHECKED)
     */
    private void sbc_a(int b) {
        int a = A();
        int c = tci();
        int wans = a - b - c;
        int ans = lo(wans);

        ts((ans & T7s) != 0);
        tz(ans == 0);
        tc((wans & x100) != 0);
        tp(((a ^ b) & (a ^ ans) & x80) != 0);
        th((((a & x0F) - (b & x0F) - c) & T4h) != 0);

        A(ans);
    }

    /**
     * Subtract - alters all flags (CHECKED)
     */
    private void sub_a(int b) {
        int a = A();
        int wans = a - b;
        int ans = lo(wans);

        ts((ans & T7s) != 0);
        tz(ans == 0);
        tc((wans & x100) != 0);
        tp(((a ^ b) & (a ^ ans) & x80) != 0);
        th((((a & x0F) - (b & x0F)) & T4h) != 0);

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
        ans = lo(ans);

        th(false);
        tc(c);

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

        th(false);
        tc(c);

        A(ans);
    }

    /**
     * Rotate Left through Carry - alters H N C 3 5 flags (CHECKED)
     */
    private void rl_a() {
        int ans = A();
        boolean c = (ans & x80) != 0;

        if (tc()) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }

        ans = lo(ans);

        th(false);
        tc(c);

        A(ans);
    }

    /**
     * Rotate Right through Carry - alters H N C 3 5 flags (CHECKED)
     */
    private void rr_a() {
        int ans = A();
        boolean c = (ans & x01) != 0;

        if (tc()) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        th(false);
        tc(c);

        A(ans);
    }

    /**
     * Compare - alters all flags (CHECKED)
     */
    private void cp_a(int b) {
        int a = A();
        int wans = a - b;
        int ans = lo(wans);

        ts((ans & T7s) != 0);
        tz(ans == 0);
        tc((wans & x100) != 0);
        th((((a & x0F) - (b & x0F)) & T4h) != 0);
        tp(((a ^ b) & (a ^ ans) & x80) != 0);
    }

    /**
     * Bitwise and - alters all flags (CHECKED)
     */
    private void and_a(int b) {
        int ans = A() & b;

        ts((ans & T7s) != 0);
        th(true);
        tp(parity[ans]);
        tz(ans == 0);
        tc(false);

        A(ans);
    }

    /**
     * Bitwise or - alters all flags (CHECKED)
     */
    private void or_a(int b) {
        int ans = A() | b;

        ts((ans & T7s) != 0);
        th(false);
        tp(parity[ans]);
        tz(ans == 0);
        tc(false);

        A(ans);
    }

    /**
     * Bitwise exclusive or - alters all flags (CHECKED)
     */
    private void xor_a(int b) {
        int ans = lo(A() ^ b);

        ts((ans & T7s) != 0);
        th(false);
        tp(parity[ans]);
        tz(ans == 0);
        tc(false);

        A(ans);
    }


    /**
     * One's complement - alters N H 3 5 flags (CHECKED)
     */
    private void cpl_a() {
        int ans = A() ^ xFF;

        th(true);

        A(ans);
    }

    /**
     * Decimal Adjust Accumulator - alters all flags (CHECKED)
     */
    private void daa_a() {
        int ans = A();
        int incr = 0;
        boolean carry = tc();

        if (th() || (ans & x0F) > x09) {
            incr |= x06;
        }
        if (carry || (ans > x9F) || ((ans > x8F) && ((ans & x0F) > x09))) {
            incr |= x60;
        }
        if (ans > x99) {
            carry = true;
        }
        sub_a(incr);

        ans = A();

        tc(carry);
        tp(parity[ans]);
    }

    /**
     * Set carry flag - alters N H 3 5 C flags (CHECKED)
     */
    private void scf() {
        th(false);
        tc(true);
    }

    /**
     * Complement carry flag - alters N 3 5 C flags (CHECKED)
     */
    private void ccf() {
        tc(!tc());
    }


    /**
     * Decrement - alters all but C flag (CHECKED)
     */
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

    /**
     * Increment - alters all but C flag (CHECKED)
     */
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