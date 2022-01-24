package spec;

import org.junit.Test;

public class CpuTest extends AbstractTest {

    @Test
    public void code00__NOP() {
        // when
        givenPr("NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("00\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        cpuShort("BC: 0000\n" +
                "DE: 0000\n" +
                "HL: 0000\n" +
                "AF: 0002\n" +
                "SP: 0000\n" +
                "PC: 0005\n");
    }

    @Test
    public void codeXX__NONE() {
        // when
        givenMm("08\n" +
                "10\n" +
                "18\n" +
                "20\n" +
                "28\n" +
                "30\n" +
                "38\n" +
                "D9\n" +
                "CB\n" +
                "DD\n" +
                "ED\n" +
                "FD");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  000C\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   08\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00001000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code01__LXI_B_XXYY() {
        // when
        givenPr("LXI B,1234\n" + // set B=XX, C=YY
                "NOP\n");

        givenMm("01 34 12\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 12 34\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   01\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00000001\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code11__LXI_D_XXYY() {
        // when
        givenPr("LXI D,1234\n" + // set D=XX, E=YY
                "NOP\n");

        givenMm("11 34 12\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  1234\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 12 34\n" +
                "H,L: 00 00\n" +
                "M:   11\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00010010\n" +
                "E:   00110100\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00010001\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code21__LXI_H_XXYY() {
        // when
        givenPr("LXI H,1234\n" + // set H=XX, L=YY
                "NOP\n");

        givenMm("21 34 12\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  1234\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 12 34\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00010010\n" +
                "L:   00110100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code31__LXI_SP_XXYY() {
        // when
        givenPr("LXI SP,1234\n" + // set SP=XXYY
                "NOP\n");

        givenMm("31 34 12\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  1234\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   31\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00010010 00110100\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00110001\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code09__DAD_B__c0() {
        // when
        givenPr("LXI B,1234\n" +  // operand 1
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,4321\n" +  // operand 2 & result
                "DAD B\n" +       // add HL=HL+BC, [c=0]
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 21 43\n" +
                "09\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  1111\n" +
                "HL:  5555\n" +
                "AF:  0002\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 12 34\n" +
                "D,E: 11 11\n" +
                "H,L: 55 55\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00010001\n" +
                "E:   00010001\n" +
                "H:   01010101\n" +
                "L:   01010101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code09__DAD_B__c1() {
        // when
        givenPr("LXI B,789A\n" +  // operand 1
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,A987\n" +  // operand 2 & result
                "DAD B\n" +       // add HL=HL+BC, [c=1]
                "NOP\n");

        givenMm("01 9A 78\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 87 A9\n" +
                "09\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  789A\n" +
                "DE:  1111\n" +
                "HL:  2221\n" +
                "AF:  0003\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 78 9A\n" +
                "D,E: 11 11\n" +
                "H,L: 22 21\n" +
                "M:   00\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   01111000\n" +
                "C:   10011010\n" +
                "D:   00010001\n" +
                "E:   00010001\n" +
                "H:   00100010\n" +
                "L:   00100001\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code19__DAD_D__c0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,1234\n" +  // operand 1
                "LXI SP,2222\n" + // ignored
                "LXI H,4321\n" +  // operand 2 & result
                "DAD D\n" +       // add HL=HL+DE, [c=0]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 34 12\n" +
                "31 22 22\n" +
                "21 21 43\n" +
                "19\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  1234\n" +
                "HL:  5555\n" +
                "AF:  0002\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 12 34\n" +
                "H,L: 55 55\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00010010\n" +
                "E:   00110100\n" +
                "H:   01010101\n" +
                "L:   01010101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code19__DAD_D__c1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,789A\n" +  // operand 1
                "LXI SP,2222\n" + // ignored
                "LXI H,A987\n" +  // operand 2 & result
                "DAD D\n" +       // add HL=HL+DE, [c=1]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 9A 78\n" +
                "31 22 22\n" +
                "21 87 A9\n" +
                "19\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  789A\n" +
                "HL:  2221\n" +
                "AF:  0003\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 78 9A\n" +
                "H,L: 22 21\n" +
                "M:   00\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   01111000\n" +
                "E:   10011010\n" +
                "H:   00100010\n" +
                "L:   00100001\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code29__DAD_H__c0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1234\n" +  // operand 1 & 2 & result
                "DAD H\n" +       // add HL=HL+HL, [c=0]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 34 12\n" +
                "29\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  2468\n" +
                "AF:  0002\n" +
                "SP:  3333\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 24 68\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00100100\n" +
                "L:   01101000\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code29__DAD_H__c1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,89AB\n" +  // operand 1 & 2 & result
                "DAD H\n" +       // add HL=HL+HL, [c=1]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 AB 89\n" +
                "29\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  1356\n" +
                "AF:  0003\n" +
                "SP:  3333\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 13 56\n" +
                "M:   00\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00010011\n" +
                "L:   01010110\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code09__DAD_SP__c0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,1234\n" + // operand 1
                "LXI H,4321\n" +  // operand 2 & result
                "DAD SP\n" +      // add HL=HL+SP, [c=0]
                //
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 34 12\n" +
                "21 21 43\n" +
                "39\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  5555\n" +
                "AF:  0002\n" +
                "SP:  1234\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 55 55\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00010010 00110100\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   01010101\n" +
                "L:   01010101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code39__DAD_SP__c1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,789A\n" + // operand 1
                "LXI H,A987\n" +  // operand 2 & result
                "DAD SP\n" +      // add HL=HL+SP, [c=1]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 9A 78\n" +
                "21 87 A9\n" +
                "39\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  2221\n" +
                "AF:  0003\n" +
                "SP:  789A\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 22 21\n" +
                "M:   00\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10011010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00100010\n" +
                "L:   00100001\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void performance() {
        // about 0.95 sec (vs 1.4) / 1_000_000
        // when
        givenPr("LXI B,1111\n" +
                "LXI SP,789A\n" +
                "DAD D\n" +
                "NOP\n" +
                "DAD D\n" +
                "LXI H,A987\n" +
                "NOP\n" +
                "DAD SP\n" +
                "LXI D,2222\n" +
                "NOP\n" +
                "DAD B\n" +
                "LXI H,A987\n" +
                "NOP\n" +
                "DAD H\n" +
                "NOP\n");

        int ticks = 1_000_000;

        // when then
        for (int tick = 0; tick < ticks; tick++) {
            hardware.reset();
            cpu().PC(0x0000);
            cpu().execute();
        }
    }

    @Test
    public void code02__STAX_B() {
        // when
        givenPr("LXI B,0003\n" +  // working with memory 0003
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "STAX B\n" +      // copy (BC)=A
                "NOP\n");

        cpu().A(0x24);

        givenMm("01 03 00\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "02\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        start();

        // then
        assertMem(0x0003, "24");

        asrtCpu("BC:  0003\n" +
                "DE:  1111\n" +
                "HL:  0003\n" +
                "AF:  2402\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 00 03\n" +
                "D,E: 11 11\n" +
                "H,L: 00 03\n" +
                "M:   24\n" +
                "A,F: 24 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000011\n" +
                "D:   00010001\n" +
                "E:   00010001\n" +
                "H:   00000000\n" +
                "L:   00000011\n" +
                "M:   00100100\n" +
                "A:   00100100\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code12__STAX_D() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,0003\n" +  // working with memory 0003
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "STAX D\n" +      // copy (DE)=A
                "NOP\n");

        cpu().A(0x24);

        givenMm("01 11 11\n" +
                "11 03 00\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "12\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        start();

        // then
        assertMem(0x0003, "24");

        asrtCpu("BC:  1111\n" +
                "DE:  0003\n" +
                "HL:  0003\n" +
                "AF:  2402\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 00 03\n" +
                "H,L: 00 03\n" +
                "M:   24\n" +
                "A,F: 24 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00000000\n" +
                "E:   00000011\n" +
                "H:   00000000\n" +
                "L:   00000011\n" +
                "M:   00100100\n" +
                "A:   00100100\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code0A__LDAX_B() {
        // when
        givenPr("LXI B,0003\n" +  // working with memory 0003
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "LDAX B\n" +      // copy A=(BC)
                "NOP\n");

        cpu().A(0x24);

        givenMm("01 03 00\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "0A\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        start();

        // then
        assertMem(0x0003, "11");

        asrtCpu("BC:  0003\n" +
                "DE:  1111\n" +
                "HL:  0003\n" +
                "AF:  1102\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 00 03\n" +
                "D,E: 11 11\n" +
                "H,L: 00 03\n" +
                "M:   11\n" +
                "A,F: 11 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000011\n" +
                "D:   00010001\n" +
                "E:   00010001\n" +
                "H:   00000000\n" +
                "L:   00000011\n" +
                "M:   00010001\n" +
                "A:   00010001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code1A__LDAX_D() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,0003\n" +  // working with memory 0003
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "LDAX D\n" +      // copy A=(DE)
                "NOP\n");

        cpu().A(0x24);

        givenMm("01 11 11\n" +
                "11 03 00\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "1A\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        start();

        // then
        assertMem(0x0003, "11");

        asrtCpu("BC:  1111\n" +
                "DE:  0003\n" +
                "HL:  0003\n" +
                "AF:  1102\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 00 03\n" +
                "H,L: 00 03\n" +
                "M:   11\n" +
                "A,F: 11 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00000000\n" +
                "E:   00000011\n" +
                "H:   00000000\n" +
                "L:   00000011\n" +
                "M:   00010001\n" +
                "A:   00010001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code22__SHLD_XXYY() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1234\n" +  // data will be copied to memory
                "SHLD 5678\n" +   // copy (5679)=H, (5678)=L
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 34 12\n" +
                "22 78 56\n" +
                "00");

        assertMem(0x5678, 2, "00 00");

        // when
        start();

        // then
        assertMem(0x5678, 0x5679, "34 12");

        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  1234\n" +
                "AF:  0002\n" +
                "SP:  3333\n" +
                "PC:  0010\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 12 34\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00010010\n" +
                "L:   00110100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code2A__LHLD_XXYY() {
        // when
        givenPr("LXI B,1234\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1111\n" +  // data will be overwritten
                "LHLD 0001\n" +   // copy H=(0002), L=(0001)
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 11 11\n" +
                "2A 01 00\n" +
                "00");

        assertMem(0x0001, 2, "34 12");

        // when
        start();

        // then
        assertMem(0x0001, 2, "34 12");

        asrtCpu("BC:  1234\n" +
                "DE:  2222\n" +
                "HL:  1234\n" +
                "AF:  0002\n" +
                "SP:  3333\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 22 22\n" +
                "H,L: 12 34\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00010010\n" +
                "L:   00110100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code32__STA_XXYY() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,4444\n" +  // ignored
                "STA 1234\n" +    // copy (1234)=A
                "NOP\n");

        cpu().A(0x24);

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 44 44\n" +
                "32 34 12\n" +
                "00");

        assertMem(0x1234, "00");

        // when
        start();

        // then
        assertMem(0x1234, "24");

        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  4444\n" +
                "AF:  2402\n" +
                "SP:  3333\n" +
                "PC:  0010\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 44 44\n" +
                "M:   00\n" +
                "A,F: 24 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   01000100\n" +
                "L:   01000100\n" +
                "M:   00000000\n" +
                "A:   00100100\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code3A__LDA_XXYY() {
        // when
        givenPr("LXI B,1234\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,4444\n" +  // ignored
                "LDA 0001\n" +    // copy A=(0001)
                "NOP\n");

        cpu().A(0x00);

        givenMm("01 34 12\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 44 44\n" +
                "3A 01 00\n" +
                "00");

        assertMem(0x0001, "34");

        // when
        start();

        // then
        assertMem(0x0001, "34");

        asrtCpu("BC:  1234\n" +
                "DE:  2222\n" +
                "HL:  4444\n" +
                "AF:  3402\n" +
                "SP:  3333\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 22 22\n" +
                "H,L: 44 44\n" +
                "M:   00\n" +
                "A,F: 34 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   01000100\n" +
                "L:   01000100\n" +
                "M:   00000000\n" +
                "A:   00110100\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code03__INX_B() {
        // when
        givenPr("LXI B,1234\n" +  // will be increased
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,3333\n" +  // ignored
                "INX B\n" +       // increase BC=BC+1
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 33 33\n" +
                "03\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1235\n" +
                "DE:  1111\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 12 35\n" +
                "D,E: 11 11\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110101\n" +
                "D:   00010001\n" +
                "E:   00010001\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code03__INX_B__c0__ignoredOverflow() {
        // when
        givenPr("LXI B,FFFF\n" +  // will be increased with overflow
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,3333\n" +  // ignored
                "INX B\n" +       // increase BC=BC+1, [c=0] ignored
                "NOP\n");

        givenMm("01 FF FF\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 33 33\n" +
                "03\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  1111\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 00 00\n" +
                "D,E: 11 11\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00010001\n" +
                "E:   00010001\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code13__INX_D() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,1234\n" +  // will be increased
                "LXI SP,2222\n" + // ignored
                "LXI H,3333\n" +  // ignored
                "INX D\n" +       // increase DE=DE+1
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 34 12\n" +
                "31 22 22\n" +
                "21 33 33\n" +
                "13\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  1235\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 12 35\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00010010\n" +
                "E:   00110101\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code13__INX_D__c0__ignoredOverflow() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,FFFF\n" +  // will be increased with overflow
                "LXI SP,2222\n" + // ignored
                "LXI H,3333\n" +  // ignored
                "INX D\n" +       // increase DE=DE+1, [c=0] ignored
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 FF FF\n" +
                "31 22 22\n" +
                "21 33 33\n" +
                "13\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  0000\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  2222\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 00 00\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00100010 00100010\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code23__INX_H() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1234\n" +  // will be increased
                "INX H\n" +       // increase HL=HL+1
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 34 12\n" +
                "23\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  1235\n" +
                "AF:  0002\n" +
                "SP:  3333\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 12 35\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00010010\n" +
                "L:   00110101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code23__INX_H__c0__ignoredOverflow() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,FFFF\n" +  // will be increased with overflow
                "INX H\n" +       // increase HL=HL+1, [c=0] ignored
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 FF FF\n" +
                "23\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  3333\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 00 00\n" +
                "M:   01\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00110011 00110011\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00000001\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code33__INX_SP() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,1234\n" + // will be increased
                "LXI H,3333\n" +  // ignored
                "INX SP\n" +      // increase HL=HL+1
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 34 12\n" +
                "21 33 33\n" +
                "33\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  1235\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00010010 00110101\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code33__INX_SP__c0__ignoredOverflow() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,FFFF\n" + // will be increased with overflow
                "LXI H,3333\n" +  // ignored
                "INX SP\n" +      // increase SP=SP+1, [c=0] ignored
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 FF FF\n" +
                "21 33 33\n" +
                "33\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  000E\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeXX__INR_R__c0() { // TODO разделить этот тест
        // when
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR B\n" +       // increase B=B+1, [c=0]
                "INR C\n" +       // increase C=C+1, [c=0]
                "INR D\n" +       // increase D=D+1, [c=0]
                "INR E\n" +       // increase E=E+1, [c=0]
                "INR H\n" +       // increase H=H+1, [c=0]
                "INR L\n" +       // increase L=L+1, [c=0]
                "INR M\n" +       // increase M=M+1, [c=0]
                "INR A\n" +       // increase A=A+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "04\n" +
                "0C\n" +
                "14\n" +
                "1C\n" +
                "24\n" +
                "2C\n" +
                "34\n" +
                "3C\n" +
                "00");

        assertMem(0x5768, "00");

        // when
        start();

        // then
        assertMem(0x5768, "01");

        asrtCpu("BC:  1324\n" +
                "DE:  3546\n" +
                "HL:  5768\n" +
                "AF:  0102\n" +
                "SP:  7889\n" +
                "PC:  0015\n" +
                "B,C: 13 24\n" +
                "D,E: 35 46\n" +
                "H,L: 57 68\n" +
                "M:   01\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00010101\n" +
                "     76543210\n" +
                "B:   00010011\n" +
                "C:   00100100\n" +
                "D:   00110101\n" +
                "E:   01000110\n" +
                "H:   01010111\n" +
                "L:   01101000\n" +
                "M:   00000001\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeXX__DCR_R() { // TODO разделить этот тест
        // when
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR B\n" +       // decrease B=B-1
                "DCR C\n" +       // decrease C=C-1
                "DCR D\n" +       // decrease D=D-1
                "DCR E\n" +       // decrease E=E-1
                "DCR H\n" +       // decrease H=H-1
                "DCR L\n" +       // decrease L=L-1
                "DCR M\n" +       // decrease M=M-1
                "DCR A\n" +       // decrease A=A-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "05\n" +
                "0D\n" +
                "15\n" +
                "1D\n" +
                "25\n" +
                "2D\n" +
                "35\n" +
                "3D\n" +
                "00");

        assertMem(0x5566, "00");

        // when
        start();

        // then
        assertMem(0x5566, "FF");

        asrtCpu("BC:  1122\n" +
                "DE:  3344\n" +
                "HL:  5566\n" +
                "AF:  FF92\n" +
                "SP:  7889\n" +
                "PC:  0015\n" +
                "B,C: 11 22\n" +
                "D,E: 33 44\n" +
                "H,L: 55 66\n" +
                "M:   FF\n" +
                "A,F: FF 92\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00010101\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00100010\n" +
                "D:   00110011\n" +
                "E:   01000100\n" +
                "H:   01010101\n" +
                "L:   01100110\n" +
                "M:   11111111\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10010010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeXX__MVI_R() { // TODO разделить этот тест
        // when
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI B,12\n" +    // set B=XX
                "MVI C,23\n" +    // set C=XX
                "MVI D,34\n" +    // set D=XX
                "MVI E,45\n" +    // set E=XX
                "MVI H,56\n" +    // set H=XX
                "MVI L,67\n" +    // set L=XX
                "MVI M,78\n" +    // set M=XX
                "MVI A,89\n" +    // set A=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "06 12\n" +
                "0E 23\n" +
                "16 34\n" +
                "1E 45\n" +
                "26 56\n" +
                "2E 67\n" +
                "36 78\n" +
                "3E 89\n" +
                "00");

        assertMem(0x5667, "00");

        // when
        start();

        // then
        assertMem(0x5667, "78");

        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  8902\n" +
                "SP:  4444\n" +
                "PC:  001D\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   78\n" +
                "A,F: 89 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00011101\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   01111000\n" +
                "A:   10001001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code40__MOV_B_B() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV B,B\n" +     // copy B=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "40\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code41__MOV_B_C() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV B,C\n" +     // copy B=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "41\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  3434\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 34 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00110100\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code42__MOV_B_D() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV B,D\n" +     // copy B=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "42\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  5634\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 56 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   01010110\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code43__MOV_B_E() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV B,E\n" +     // copy B=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "43\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  7834\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 78 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   01111000\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code44__MOV_B_H() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV B,H\n" +     // copy B=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "44\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  9A34\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 9A 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   10011010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code45__MOV_B_L() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV B,L\n" +     // copy B=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "45\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  BC34\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: BC 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   10111100\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code46__MOV_B_M() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // working with memory 9ABC
                "LXI SP,DEF0\n" + // data
                "MVI M,46\n" +    // set data in memory (9ABC)=46
                "MOV B,M\n" +     // copy B=M  =(HL)=(9ABC)
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "36 46\n" +
                "46\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "46");

        asrtCpu("BC:  4634\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 46 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   46\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   01000110\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   01000110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code47__MOV_B_L() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV B,A\n" +     // copy B=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "47\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  0134\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 01 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code48__MOV_C_B() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV C,B\n" +     // copy C=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "48\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  1212\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 12\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00010010\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code49__MOV_C_C() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV C,C\n" +     // copy C=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "49\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code4A__MOV_C_D() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV C,D\n" +     // copy C=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "4A\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  1256\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 56\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   01010110\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code4B__MOV_C_E() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV C,E\n" +     // copy C=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "4B\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  1278\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 78\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   01111000\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code4C__MOV_C_H() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV C,H\n" +     // copy C=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "4C\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  129A\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 9A\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   10011010\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code4D__MOV_C_L() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MOV C,L\n" +     // copy C=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "4D\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  12BC\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  000E\n" +
                "B,C: 12 BC\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   10111100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code4E__MOV_C_M() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // working with memory 9ABC
                "LXI SP,DEF0\n" + // data
                "MVI M,46\n" +    // set data in memory (9ABC)=46
                "MOV C,M\n" +     // copy C=M  =(HL)=(9ABC)
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "36 46\n" +
                "4E\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "46");

        asrtCpu("BC:  1246\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 46\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   46\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   01000110\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   01000110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code4F__MOV_C_L() {
        // when
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV C,A\n" +     // copy C=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "4F\n" +
                "00");

        assertMem(0x9ABC, "00");

        // when
        start();

        // then
        assertMem(0x9ABC, "00");

        asrtCpu("BC:  1201\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 01\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00000001\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }
}