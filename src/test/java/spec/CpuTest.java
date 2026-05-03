package spec;

import org.junit.Test;
import spec.stuff.AbstractTest;

public class CpuTest extends AbstractTest {

    @Override
    public void after() throws Exception {
        asrtMem("");
        super.after();
    }

    @Test
    public void code00__NOP() {
        // given
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
    public void code08__NONE() {
        // when
        givenMm("08");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   08\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
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
    public void code10__NONE() {
        // when
        givenMm("10");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   10\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00010000\n" +
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
    public void code18__NONE() {
        // when
        givenMm("18");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   18\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00011000\n" +
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
    public void code20__NONE() {
        // when
        givenMm("20");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   20\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00100000\n" +
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
    public void code28__NONE() {
        // when
        givenMm("28");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   28\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00101000\n" +
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
    public void code30__NONE() {
        // when
        givenMm("30");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   30\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00110000\n" +
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
    public void code38__NONE() {
        // when
        givenMm("38");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   38\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111000\n" +
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
    public void codeD9__NONE() {
        // when
        givenMm("D9");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   D9\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11011001\n" +
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
    public void codeCB__NONE() {
        // when
        givenMm("CB");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   CB\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11001011\n" +
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
    public void codeDD__NONE() {
        // when
        givenMm("DD");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   DD\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11011101\n" +
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
    public void codeED__NONE() {
        // when
        givenMm("ED");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   ED\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11101101\n" +
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
    public void codeFD__NONE() {
        // when
        givenMm("FD");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   FD\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11111101\n" +
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
    public void code02__STAX_B() {
        // given
        givenPr("LXI B,0003\n" +  // working with memory 0003
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "STAX B\n" +      // copy (BC)=A
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 03 00\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "02\n" +
                "00");

        // when
        start();

        // then
        asrtMem("0003: 11 -> 24");

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
        // given
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,0003\n" +  // working with memory 0003
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "STAX D\n" +      // copy (DE)=A
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 11 11\n" +
                "11 03 00\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "12\n" +
                "00");

        // when
        start();

        // then
        asrtMem("0003: 11 -> 24");

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
        // given
        givenPr("LXI B,0003\n" +  // working with memory 0003
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "LDAX B\n" +      // copy A=(BC)
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 03 00\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "0A\n" +
                "00");

        // when
        start();

        // then
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
        // given
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,0003\n" +  // working with memory 0003
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0003) is used to check value
                "LDAX D\n" +      // copy A=(DE)
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 11 11\n" +
                "11 03 00\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "1A\n" +
                "00");

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
        asrtMem("5678: 00 -> 34\n" +
                "5679: 00 -> 12");

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
        // given
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

        // when
        start();

        // then
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
        // given
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,4444\n" +  // ignored
                "STA 1234\n" +    // copy (1234)=A
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 44 44\n" +
                "32 34 12\n" +
                "00");

        // when
        start();

        // then
        asrtMem("1234: 00 -> 24");

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
        // given
        givenPr("LXI B,1234\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,4444\n" +  // ignored
                "LDA 0001\n" +    // copy A=(0001)
                "NOP\n");

        cpu.A(0x00);

        givenMm("01 34 12\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 44 44\n" +
                "3A 01 00\n" +
                "00");

        // when
        start();

        // then
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
        // given
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
    public void code04__INR_B__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR B\n" +       // increase B=B+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "04\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1323\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0002\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 13 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010011\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
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
    public void code0C__INR_C__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR C\n" +       // increase C=C+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "0C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1224\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0006\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 24\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 06\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100100\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code14__INR_D__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR D\n" +       // increase D=D+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "14\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3545\n" +
                "HL:  5667\n" +
                "AF:  0006\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 35 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 06\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110101\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code1C__INR_E__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR E\n" +       // increase E=E+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "1C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3446\n" +
                "HL:  5667\n" +
                "AF:  0002\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 46\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000110\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
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
    public void code24__INR_H__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR H\n" +       // increase H=H+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "24\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5767\n" +
                "AF:  0002\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 57 67\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010111\n" +
                "L:   01100111\n" +
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
    public void code2C__INR_L__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR L\n" +       // increase L=L+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "2C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5668\n" +
                "AF:  0002\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 68\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
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
    public void code34__INR_M__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR M\n" +       // increase M=M+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "34\n" +
                "00");

        // when
        start();

        // then
        asrtMem("5667: 00 -> 01");

        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0002\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   01\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
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
    public void code3C__INR_A__c0() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "INR A\n" +       // increase A=A+1, [c=0]
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "3C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0102\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
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
    public void code05__DCR_B() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR B\n" +       // decrease B=B-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "05\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1123\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0016\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 11 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 16\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code0D__DCR_C() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR C\n" +       // decrease C=C-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "0D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1222\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0016\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 22\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 16\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100010\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code15__DCR_D() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR D\n" +       // decrease D=D-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "15\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3345\n" +
                "HL:  5667\n" +
                "AF:  0016\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 33 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 16\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110011\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code1D__DCR_E() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR E\n" +       // decrease E=E-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "1D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3444\n" +
                "HL:  5667\n" +
                "AF:  0016\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 44\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: 00 16\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000100\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code25__DCR_H() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR H\n" +       // decrease H=H-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "25\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5567\n" +
                "AF:  0016\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 55 67\n" +
                "M:   00\n" +
                "A,F: 00 16\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010101\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code2D__DCR_L() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR L\n" +       // decrease L=L-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "2D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5666\n" +
                "AF:  0016\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 66\n" +
                "M:   00\n" +
                "A,F: 00 16\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100110\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code35__DCR_M() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR M\n" +       // decrease M=M-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "35\n" +
                "00");

        // when
        start();

        // then
        asrtMem("5667: 00 -> FF");

        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  0086\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   FF\n" +
                "A,F: 00 86\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   11111111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code3D__DCR_A() {
        // given
        givenPr("LXI B,1223\n" +  // data
                "LXI D,3445\n" +  // data
                "LXI H,5667\n" +  // data
                "LXI SP,7889\n" + // data
                "DCR A\n" +       // decrease A=A-1
                "NOP\n");

        givenMm("01 23 12\n" +
                "11 45 34\n" +
                "21 67 56\n" +
                "31 89 78\n" +
                "3D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1223\n" +
                "DE:  3445\n" +
                "HL:  5667\n" +
                "AF:  FF86\n" +
                "SP:  7889\n" +
                "PC:  000E\n" +
                "B,C: 12 23\n" +
                "D,E: 34 45\n" +
                "H,L: 56 67\n" +
                "M:   00\n" +
                "A,F: FF 86\n" +
                "     76543210 76543210\n" +
                "SP:  01111000 10001001\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00100011\n" +
                "D:   00110100\n" +
                "E:   01000101\n" +
                "H:   01010110\n" +
                "L:   01100111\n" +
                "M:   00000000\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code06__MVI_B() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI B,12\n" +    // set B=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "06 12\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1211\n" +
                "DE:  2222\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 12 11\n" +
                "D,E: 22 22\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code0E__MVI_C() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI C,23\n" +    // set C=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "0E 23\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1123\n" +
                "DE:  2222\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 23\n" +
                "D,E: 22 22\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00100011\n" +
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
    public void code16__MVI_D() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI D,34\n" +    // set D=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "16 34\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  3422\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 11\n" +
                "D,E: 34 22\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00110100\n" +
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
    public void code1E__MVI_E() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI E,45\n" +    // set E=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "1E 45\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2245\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 11\n" +
                "D,E: 22 45\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   01000101\n" +
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
    public void code26__MVI_H() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI H,56\n" +    // set H=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "26 56\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  5633\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 56 33\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   01010110\n" +
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
    public void code2E__MVI_L() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI L,67\n" +    // set L=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "2E 67\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  3367\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 33 67\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00110011\n" +
                "L:   01100111\n" +
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
    public void code36__MVI_M() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // working with memory 3333
                "LXI SP,4444\n" + // data
                "MVI M,78\n" +    // set data in memory M=XX  (9ABC)=46
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "36 78\n" +
                "00");

        // when
        start();

        // then
        asrtMem("3333: 00 -> 78");

        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  3333\n" +
                "AF:  0002\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 33 33\n" +
                "M:   78\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   01111000\n" +
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
    public void code3E__MVI_A() {
        // given
        givenPr("LXI B,1111\n" +  // data
                "LXI D,2222\n" +  // data
                "LXI H,3333\n" +  // data
                "LXI SP,4444\n" + // data
                "MVI A,89\n" +    // set A=XX
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "21 33 33\n" +
                "31 44 44\n" +
                "3E 89\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1111\n" +
                "DE:  2222\n" +
                "HL:  3333\n" +
                "AF:  8902\n" +
                "SP:  4444\n" +
                "PC:  000F\n" +
                "B,C: 11 11\n" +
                "D,E: 22 22\n" +
                "H,L: 33 33\n" +
                "M:   00\n" +
                "A,F: 89 02\n" +
                "     76543210 76543210\n" +
                "SP:  01000100 01000100\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010001\n" +
                "C:   00010001\n" +
                "D:   00100010\n" +
                "E:   00100010\n" +
                "H:   00110011\n" +
                "L:   00110011\n" +
                "M:   00000000\n" +
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 46");

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
    public void code47__MOV_B_A() {
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
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
        // given
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

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 46");

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
    public void code4F__MOV_C_A() {
        // given
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

        // when
        start();

        // then
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

    @Test
    public void code50__MOV_D_B() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,B\n" +     // copy D=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "50\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  1278\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 12 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00010010\n" +
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
    public void code51__MOV_D_C() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,C\n" +     // copy D=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "51\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  3478\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 34 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00110100\n" +
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
    public void code52__MOV_D_D() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,D\n" +     // copy D=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "52\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code53__MOV_D_E() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,E\n" +     // copy D=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "53\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  7878\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 78 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01111000\n" +
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
    public void code54__MOV_D_H() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,H\n" +     // copy D=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "54\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  9A78\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 9A 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   10011010\n" +
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
    public void code55__MOV_D_L() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,L\n" +     // copy D=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "55\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  BC78\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: BC 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   10111100\n" +
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
    public void code56__MOV_D_M() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // working with memory 9ABC
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MVI M,46\n" +    // set data in memory (9ABC)=46
                "MOV D,M\n" +     // copy D=M  =(HL)=(9ABC)
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "36 46\n" +
                "56\n" +
                "00");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 46");

        asrtCpu("BC:  1234\n" +
                "DE:  4678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0012\n" +
                "B,C: 12 34\n" +
                "D,E: 46 78\n" +
                "H,L: 9A BC\n" +
                "M:   46\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010010\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01000110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   01000110\n" +
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
    public void code57__MOV_D_A() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV D,A\n" +     // copy D=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "57\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  0178\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 01 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   00000001\n" +
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
    public void code58__MOV_E_B() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,B\n" +     // copy E=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "58\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5612\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 12\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   00010010\n" +
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
    public void code59__MOV_E_C() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,C\n" +     // copy E=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "59\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5634\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 34\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   00110100\n" +
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
    public void code5A__MOV_E_D() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,D\n" +     // copy E=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "5A\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5656\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 56\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01010110\n" +
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
    public void code5B__MOV_E_E() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,E\n" +     // copy E=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "5B\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code5C__MOV_E_H() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,H\n" +     // copy E=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "5C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  569A\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 9A\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   10011010\n" +
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
    public void code5D__MOV_E_L() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,L\n" +     // copy E=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "5D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  56BC\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 BC\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   10111100\n" +
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
    public void code5E__MOV_E_M() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // working with memory 9ABC
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,M\n" +     // copy E=M  =(HL)=(9ABC)
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "5E\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5600\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 00\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   00000000\n" +
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
    public void code5F__MOV_E_A() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV E,A\n" +     // copy E=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "5F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5601\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 01\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   00000001\n" +
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
    public void code60__MOV_H_B() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,B\n" +     // copy H=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "60\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  12BC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 12 BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   00010010\n" +
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
    public void code61__MOV_H_C() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,C\n" +     // copy H=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "61\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  34BC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 34 BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   00110100\n" +
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
    public void code62__MOV_H_D() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,D\n" +     // copy H=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "62\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  56BC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 56 BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   01010110\n" +
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
    public void code63__MOV_H_E() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,E\n" +     // copy H=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "63\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  78BC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 78 BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   01111000\n" +
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
    public void code64__MOV_H_H() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,H\n" +     // copy H=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "64\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code65__MOV_H_L() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,L\n" +     // copy H=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "65\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  BCBC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: BC BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10111100\n" +
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
    public void code66__MOV_H_M() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,M\n" +     // copy H=M
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "66\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  00BC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 00 BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   00000000\n" +
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
    public void code67__MOV_H_A() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV H,A\n" +     // copy H=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "67\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  01BC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 01 BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   00000001\n" +
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
    public void code68__MOV_L_B() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,B\n" +     // copy L=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "68\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A12\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 12\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   00010010\n" +
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
    public void code69__MOV_L_C() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,C\n" +     // copy L=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "69\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A34\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 34\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   00110100\n" +
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
    public void code6A__MOV_L_D() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,D\n" +     // copy L=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "6A\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A56\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 56\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   01010110\n" +
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
    public void code6B__MOV_L_E() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,E\n" +     // copy L=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "6B\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A78\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 78\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   01111000\n" +
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
    public void code6C__MOV_L_H() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,H\n" +     // copy L=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "6C\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A9A\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 9A\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10011010\n" +
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
    public void code6D__MOV_L_L() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,L\n" +     // copy L=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "6D\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code6E__MOV_L_M() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,M\n" +     // copy L=M
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "6E\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A00\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 00\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   00000000\n" +
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
    public void code6F__MOV_L_A() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV L,A\n" +     // copy L=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "6F\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9A01\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A 01\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   00000001\n" +
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
    public void code70__MOV_M_B() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,B\n" +     // copy M=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "70\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 12");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   12\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00010010\n" +
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
    public void code71__MOV_M_C() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,C\n" +     // copy M=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "71\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 34");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   34\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00110100\n" +
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
    public void code72__MOV_M_D() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,D\n" +     // copy M=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "72\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 56");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   56\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   01010110\n" +
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
    public void code73__MOV_M_E() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,E\n" +     // copy M=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "73\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 78");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   78\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   01111000\n" +
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
    public void code74__MOV_M_H() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,H\n" +     // copy M=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "74\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 9A");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   9A\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   10011010\n" +
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
    public void code75__MOV_M_L() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,L\n" +     // copy M=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "75\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> BC");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   BC\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   10111100\n" +
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
    public void code76__HLT() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "HLT\n" +         // cpu hlt
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "76\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  000F\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code77__MOV_M_A() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV M,A\n" +     // copy M=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "77\n" +
                "00\n");

        // when
        start();

        // then
        asrtMem("9ABC: 00 -> 01");

        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   01\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
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
    public void testAssertMem() {
        // given
        givenMm("01 02 03\n" +
                "04\n" +
                "05\n" +
                "06 07\n" +
                "08\n" +
                "09\n" +
                "10\n" +
                "11 12 13\n" +
                "14\n" +
                "15\n" +
                "16 17\n" +
                "18\n");

        memory.resetChanges();

        // when then
        asrtMem(0x0000, "01");
        asrtMem(0x0001, "02");
        asrtMem(0x0004, 3, "05 06 07");
        asrtMem(0x0001, 0x000A, "02 03 04 05 06 07 08 09 10 11");
        asrtMem(0xC000, 10, "00 00 00 00 00 00 00 00 00 00");
        asrtMem(0xC000, 0xC00A, "00 00 00 00 00 00 00 00 00 00 00");
    }

    @Test
    public void code78__MOV_A_B() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,B\n" +     // copy A=B
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "78\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  1202\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 12 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00010010\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code79__MOV_A_C() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,C\n" +     // copy A=C
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "79\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  3402\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 34 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
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
    public void code7A__MOV_A_D() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,D\n" +     // copy A=D
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "7A\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  5602\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 56 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   01010110\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code7B__MOV_A_E() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,E\n" +     // copy A=E
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "7B\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  7802\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 78 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   01111000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code7C__MOV_A_H() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,H\n" +     // copy A=H
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "7C\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  9A02\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 9A 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   10011010\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code7D__MOV_A_L() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,L\n" +     // copy A=L
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "7D\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  BC02\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: BC 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   10111100\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code7E__MOV_A_M() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,M\n" +     // copy A=M
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "7E\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0002\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
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
    public void code7F__MOV_A_A() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,01\n" +    // data
                "MOV A,A\n" +     // copy A=A
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 01\n" +
                "7F\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0102\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
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
    public void code07__RLC__c0() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,57\n" +    // data
                "RLC\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 57\n" +
                "07\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  AE02\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: AE 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   10101110\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code07__RLC__c1() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,87\n" +    // data
                "RLC\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 87\n" +
                "07\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0F03\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 0F 03\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00001111\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code0F__RRC__c1() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,51\n" +    // data
                "RRC\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 51\n" +
                "0F\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  A803\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: A8 03\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   10101000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code0F__RRC__c0() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,86\n" +    // data
                "RRC\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 86\n" +
                "0F\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  4302\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 43 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   01000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code17__RAL__c0() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,57\n" +    // data
                "RAL\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 57\n" +
                "17\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  AE02\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: AE 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   10101110\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code17__RAL__c1() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,87\n" +    // data
                "RAL\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 87\n" +
                "17\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  0E03\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 0E 03\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00001110\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code1F__RAR__c1() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,51\n" +    // data
                "RAR\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 51\n" +
                "1F\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  2803\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 28 03\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   00101000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void code1F__RAR__c0() {
        // given
        givenPr("LXI B,1234\n" +  // data
                "LXI D,5678\n" +  // data
                "LXI H,9ABC\n" +  // data
                "LXI SP,DEF0\n" + // data
                "MVI A,86\n" +    // data
                "RAR\n" +         // to test
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 78 56\n" +
                "21 BC 9A\n" +
                "31 F0 DE\n" +
                "3E 86\n" +
                "1F\n" +
                "00\n");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  5678\n" +
                "HL:  9ABC\n" +
                "AF:  4302\n" +
                "SP:  DEF0\n" +
                "PC:  0010\n" +
                "B,C: 12 34\n" +
                "D,E: 56 78\n" +
                "H,L: 9A BC\n" +
                "M:   00\n" +
                "A,F: 43 02\n" +
                "     76543210 76543210\n" +
                "SP:  11011110 11110000\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
                "H:   10011010\n" +
                "L:   10111100\n" +
                "M:   00000000\n" +
                "A:   01000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // ACI_XX: A = A + immediate + carry

    @Test
    public void codeCE__ACI_XX_no_flags() {
        // given: A=0x10, imm=0x20, carry=0 => A=0x30, no flags
        givenPr("MVI A,10\n" +  // A = 0x10
                "ACI 20\n" +    // A = A + 0x20 + carry(0) = 0x30
                "NOP\n");

        givenMm("3E 10\n" +
                "CE 20\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 30 06\n" +
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
                "M:   00111110\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeCE__ACI_XX_zero_and_carry() {
        // given: A=0xFF, imm=0x01, carry=0 => A=0x00, Z=1, C=1, H=1
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "ACI 01\n" +    // A = 0xFF + 0x01 + 0 = 0x00, C=1, Z=1, H=1
                "NOP\n");

        givenMm("3E FF\n" +
                "CE 01\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0057\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 57\n" +
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
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    @Test
    public void codeCE__ACI_XX_sign_and_half_carry() {
        // given: A=0x7F, imm=0x01, carry=0 => A=0x80, S=1, H=1
        givenPr("MVI A,7F\n" +  // A = 0x7F
                "ACI 01\n" +    // A = 0x7F + 0x01 + 0 = 0x80, S=1, H=1
                "NOP\n");

        givenMm("3E 7F\n" +
                "CE 01\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8092\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 92\n" +
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
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10010010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeCE__ACI_XX_with_carry_in() {
        // given: carry=1, A=0x0F, imm=0x00 => A=0x10, H=1 (carry propagates through nibble)
        // set carry by adding 0xFF + 0x01 first
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "ACI 01\n" +    // A = 0x00, C=1 (sets carry flag)
                "MVI A,0F\n" +  // A = 0x0F (carry still = 1)
                "ACI 00\n" +    // A = 0x0F + 0x00 + 1 = 0x10, H=1
                "NOP\n");

        givenMm("3E FF\n" +
                "CE 01\n" +
                "3E 0F\n" +
                "CE 00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1012\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // ADC_R: A = A + register + carry  (opcodes 0x88-0x8F: B,C,D,E,H,L,M,A)

    @Test
    public void code88__ADC_B() {
        // given: A=0x10, B=0x20, carry=0 => A=0x30, P=1
        givenPr("MVI A,10\n" +  // A = 0x10
                "MVI B,20\n" +  // B = 0x20
                "ADC B\n" +     // A = A + B + carry(0) = 0x30
                "NOP\n");

        givenMm("3E 10\n" +
                "06 20\n" +
                "88\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  2000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 20 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 30 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00100000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code89__ADC_C() {
        // given: A=0x11, C=0x22, carry=0 => A=0x33, P=1
        givenPr("MVI A,11\n" +  // A = 0x11
                "MVI C,22\n" +  // C = 0x22
                "ADC C\n" +     // A = 0x11 + 0x22 + 0 = 0x33
                "NOP\n");

        givenMm("3E 11\n" +
                "0E 22\n" +
                "89\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0022\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3306\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 22\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 33 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00100010\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00110011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code8A__ADC_D() {
        // given: A=0x11, D=0x44, carry=0 => A=0x55, P=1
        givenPr("MVI A,11\n" +  // A = 0x11
                "MVI D,44\n" +  // D = 0x44
                "ADC D\n" +     // A = 0x11 + 0x44 + 0 = 0x55
                "NOP\n");

        givenMm("3E 11\n" +
                "16 44\n" +
                "8A\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  4400\n" +
                "HL:  0000\n" +
                "AF:  5506\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 44 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 55 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   01000100\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01010101\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code8B__ADC_E() {
        // given: A=0x22, E=0x33, carry=0 => A=0x55, P=1
        givenPr("MVI A,22\n" +  // A = 0x22
                "MVI E,33\n" +  // E = 0x33
                "ADC E\n" +     // A = 0x22 + 0x33 + 0 = 0x55
                "NOP\n");

        givenMm("3E 22\n" +
                "1E 33\n" +
                "8B\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0033\n" +
                "HL:  0000\n" +
                "AF:  5506\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 33\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 55 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00110011\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01010101\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code8C__ADC_H() {
        // given: A=0x10, H=0x20, carry=0 => A=0x30, P=1
        givenPr("MVI H,20\n" +  // H = 0x20
                "MVI A,10\n" +  // A = 0x10
                "ADC H\n" +     // A = 0x10 + 0x20 + 0 = 0x30
                "NOP\n");

        givenMm("26 20\n" +
                "3E 10\n" +
                "8C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  2000\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 20 00\n" +
                "M:   00\n" +
                "A,F: 30 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00100000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code8D__ADC_L() {
        // given: A=0x10, L=0x20, carry=0 => A=0x30, P=1
        givenPr("MVI L,20\n" +  // L = 0x20
                "MVI A,10\n" +  // A = 0x10
                "ADC L\n" +     // A = 0x10 + 0x20 + 0 = 0x30
                "NOP\n");

        givenMm("2E 20\n" +
                "3E 10\n" +
                "8D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0020\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 20\n" +
                "M:   00\n" +
                "A,F: 30 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00100000\n" +
                "M:   00000000\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code8E__ADC_M() {
        // given: HL=0x0001, M=memory[0x0001]=0x01 (2nd byte of LXI), A=0x10 => A=0x11, P=1
        givenPr("LXI H,0001\n" + // HL = 0x0001, so M points to 2nd byte (0x01)
                "MVI A,10\n" +   // A = 0x10
                "ADC M\n" +      // A = 0x10 + 0x01 + carry(0) = 0x11
                "NOP\n");

        givenMm("21 01 00\n" +
                "3E 10\n" +
                "8E\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0001\n" +
                "AF:  1106\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 01\n" +
                "M:   01\n" +
                "A,F: 11 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000001\n" +
                "M:   00000001\n" +
                "A:   00010001\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code8F__ADC_A() {
        // given: A=0x40, carry=0 => A = A+A+0 = 0x80, S=1
        givenPr("MVI A,40\n" +  // A = 0x40
                "ADC A\n" +     // A = 0x40 + 0x40 + 0 = 0x80, S=1
                "NOP\n");

        givenMm("3E 40\n" +
                "8F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8082\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 82\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code88__ADC_B_with_carry_in() {
        // given: set carry via 0xFF+0x01, then A=0x0F+B(0x00)+carry(1) => 0x10, H=1
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "MVI B,01\n" +  // B = 0x01
                "ADC B\n" +     // A = 0xFF+0x01+0 = 0x00, C=1 set
                "MVI A,0F\n" +  // A = 0x0F
                "MVI B,00\n" +  // B = 0x00
                "ADC B\n" +     // A = 0x0F+0x00+carry(1) = 0x10, H=1
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3E 0F\n" +
                "06 00\n" +
                "88\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1012\n" +
                "SP:  0000\n" +
                "PC:  000B\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001011\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code8F__ADC_A_with_carry_in() {
        // given: set carry via 0xFF+B(0x01), then ADC A: 0x40+0x40+carry(1) = 0x81, S=1, P=1
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "MVI B,01\n" +  // B = 0x01
                "ADC B\n" +     // A = 0x00, C=1 set
                "MVI A,40\n" +  // A = 0x40
                "ADC A\n" +     // A = 0x40+0x40+carry(1) = 0x81, S=1, P=1
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3E 40\n" +
                "8F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8186\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 81 86\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10000001\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // ADD_R: A = A + register  (opcodes 0x80-0x87: B,C,D,E,H,L,M,A), no carry input

    @Test
    public void code80__ADD_B() {
        // given: A=0x10, B=0x20, carry irrelevant => A=0x30, P=1
        givenPr("MVI A,10\n" +  // A = 0x10
                "MVI B,20\n" +  // B = 0x20
                "ADD B\n" +     // A = A + B = 0x30
                "NOP\n");

        givenMm("3E 10\n" +
                "06 20\n" +
                "80\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  2000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 20 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 30 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00100000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code81__ADD_C() {
        // given: A=0x11, C=0x22 => A=0x33, P=1
        givenPr("MVI A,11\n" +
                "MVI C,22\n" +
                "ADD C\n" +
                "NOP\n");

        givenMm("3E 11\n" +
                "0E 22\n" +
                "81\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0022\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3306\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 22\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 33 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00100010\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00110011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code82__ADD_D() {
        // given: A=0x11, D=0x44 => A=0x55, P=1
        givenPr("MVI A,11\n" +
                "MVI D,44\n" +
                "ADD D\n" +
                "NOP\n");

        givenMm("3E 11\n" +
                "16 44\n" +
                "82\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  4400\n" +
                "HL:  0000\n" +
                "AF:  5506\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 44 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 55 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   01000100\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01010101\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code83__ADD_E() {
        // given: A=0x22, E=0x33 => A=0x55, P=1
        givenPr("MVI A,22\n" +
                "MVI E,33\n" +
                "ADD E\n" +
                "NOP\n");

        givenMm("3E 22\n" +
                "1E 33\n" +
                "83\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0033\n" +
                "HL:  0000\n" +
                "AF:  5506\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 33\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 55 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00110011\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01010101\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code84__ADD_H() {
        // given: H=0x20, A=0x10 => A=0x30, P=1
        givenPr("MVI H,20\n" +
                "MVI A,10\n" +
                "ADD H\n" +
                "NOP\n");

        givenMm("26 20\n" +
                "3E 10\n" +
                "84\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  2000\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 20 00\n" +
                "M:   00\n" +
                "A,F: 30 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00100000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code85__ADD_L() {
        // given: L=0x20, A=0x10 => A=0x30, P=1
        givenPr("MVI L,20\n" +
                "MVI A,10\n" +
                "ADD L\n" +
                "NOP\n");

        givenMm("2E 20\n" +
                "3E 10\n" +
                "85\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0020\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 20\n" +
                "M:   00\n" +
                "A,F: 30 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00100000\n" +
                "M:   00000000\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code86__ADD_M() {
        // given: HL=0x0001, M=memory[0x0001]=0x01, A=0x10 => A=0x11, P=1
        givenPr("LXI H,0001\n" +
                "MVI A,10\n" +
                "ADD M\n" +
                "NOP\n");

        givenMm("21 01 00\n" +
                "3E 10\n" +
                "86\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0001\n" +
                "AF:  1106\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 01\n" +
                "M:   01\n" +
                "A,F: 11 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000001\n" +
                "M:   00000001\n" +
                "A:   00010001\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code87__ADD_A() {
        // given: A=0x40 => A = A+A = 0x80, S=1
        givenPr("MVI A,40\n" +
                "ADD A\n" +
                "NOP\n");

        givenMm("3E 40\n" +
                "87\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8082\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 82\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code80__ADD_B_zero_and_carry() {
        // given: A=0xFF, B=0x01 => A=0x00, Z=1, C=1, H=1
        givenPr("MVI A,FF\n" +
                "MVI B,01\n" +
                "ADD B\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "80\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0057\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 57\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    @Test
    public void code80__ADD_B_carry_not_consumed() {
        // given: carry=1 set first, then ADD B — carry must NOT be added (distinguishes from ADC)
        // carry set by: 0xFF + B(0x01) via ADC B => A=0x00, C=1
        // then A=0x0F, B=0x00, ADD B => A=0x0F (not 0x10 as ADC would give)
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "MVI B,01\n" +  // B = 0x01
                "ADC B\n" +     // A = 0x00, C=1 set
                "MVI A,0F\n" +  // A = 0x0F
                "MVI B,00\n" +  // B = 0x00
                "ADD B\n" +     // A = 0x0F + 0x00 + 0 = 0x0F (carry NOT consumed!)
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3E 0F\n" +
                "06 00\n" +
                "80\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0F06\n" +
                "SP:  0000\n" +
                "PC:  000B\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 0F 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001011\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00001111\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // ADI_XX: A = A + immediate  (opcode 0xC6), no carry input

    @Test
    public void codeC6__ADI_XX_no_flags() {
        // given: A=0x10, imm=0x20 => A=0x30, P=1
        givenPr("MVI A,10\n" +
                "ADI 20\n" +    // A = A + 0x20 = 0x30
                "NOP\n");

        givenMm("3E 10\n" +
                "C6 20\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3006\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 30 06\n" +
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
                "M:   00111110\n" +
                "A:   00110000\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeC6__ADI_XX_zero_and_carry() {
        // given: A=0xFF, imm=0x01 => A=0x00, Z=1, C=1, H=1
        givenPr("MVI A,FF\n" +
                "ADI 01\n" +    // A = 0xFF + 0x01 = 0x00, C=1, Z=1
                "NOP\n");

        givenMm("3E FF\n" +
                "C6 01\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0057\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 57\n" +
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
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    @Test
    public void codeC6__ADI_XX_sign_and_half_carry() {
        // given: A=0x7F, imm=0x01 => A=0x80, S=1, H=1
        givenPr("MVI A,7F\n" +
                "ADI 01\n" +    // A = 0x7F + 0x01 = 0x80, S=1, H=1
                "NOP\n");

        givenMm("3E 7F\n" +
                "C6 01\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8092\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 92\n" +
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
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10010010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeC6__ADI_XX_carry_not_consumed() {
        // given: carry=1 set first, then ADI 00 — carry must NOT be added (unlike ACI)
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "MVI B,01\n" +  // B = 0x01
                "ADC B\n" +     // A = 0x00, C=1 set
                "MVI A,0F\n" +  // A = 0x0F
                "ADI 00\n" +    // A = 0x0F + 0x00 = 0x0F (carry NOT consumed!)
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3E 0F\n" +
                "C6 00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0F06\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 0F 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00001111\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // ANA_R: A = A & register  (opcodes 0xA0-0xA7: B,C,D,E,H,L,M,A)
    // Special: H = set if (A | reg) has bit 3; C always cleared

    @Test
    public void codeA0__ANA_B() {
        // given: A=0xFF, B=0xF0 => A=0xF0, S=1, H=1, P=1
        givenPr("MVI A,FF\n" +
                "MVI B,F0\n" +
                "ANA B\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "06 F0\n" +
                "A0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  F000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  F096\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: F0 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: F0 96\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   11110000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11110000\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA1__ANA_C() {
        // given: A=0xFF, C=0x33 => A=0x33, S=0, H=1, P=1
        givenPr("MVI A,FF\n" +
                "MVI C,33\n" +
                "ANA C\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "0E 33\n" +
                "A1\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0033\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  3316\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 33\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 33 16\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00110011\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00110011\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA2__ANA_D() {
        // given: A=0xFF, D=0x55 => A=0x55, S=0, H=1, P=1
        givenPr("MVI A,FF\n" +
                "MVI D,55\n" +
                "ANA D\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "16 55\n" +
                "A2\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  5500\n" +
                "HL:  0000\n" +
                "AF:  5516\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 55 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 55 16\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   01010101\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01010101\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA3__ANA_E() {
        // given: A=0xFF, E=0xAA => A=0xAA, S=1, H=1, P=1
        givenPr("MVI A,FF\n" +
                "MVI E,AA\n" +
                "ANA E\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "1E AA\n" +
                "A3\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  00AA\n" +
                "HL:  0000\n" +
                "AF:  AA96\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 AA\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: AA 96\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   10101010\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10101010\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA4__ANA_H() {
        // given: H=0x0F, A=0xFF => A=0x0F, S=0, H=1, P=1
        givenPr("MVI H,0F\n" +
                "MVI A,FF\n" +
                "ANA H\n" +
                "NOP\n");

        givenMm("26 0F\n" +
                "3E FF\n" +
                "A4\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0F00\n" +
                "AF:  0F16\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 0F 00\n" +
                "M:   00\n" +
                "A,F: 0F 16\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00001111\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00001111\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA5__ANA_L() {
        // given: L=0xF0, A=0xFF => A=0xF0, S=1, H=1, P=1
        givenPr("MVI L,F0\n" +
                "MVI A,FF\n" +
                "ANA L\n" +
                "NOP\n");

        givenMm("2E F0\n" +
                "3E FF\n" +
                "A5\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  00F0\n" +
                "AF:  F096\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 F0\n" +
                "M:   00\n" +
                "A,F: F0 96\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   11110000\n" +
                "M:   00000000\n" +
                "A:   11110000\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA6__ANA_M() {
        // given: HL=0x0001, M=memory[0x0001]=0x01, A=0xFF => A=0x01, S=0, H=1, P=0
        givenPr("LXI H,0001\n" +
                "MVI A,FF\n" +
                "ANA M\n" +
                "NOP\n");

        givenMm("21 01 00\n" +
                "3E FF\n" +
                "A6\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0001\n" +
                "AF:  0112\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 01\n" +
                "M:   01\n" +
                "A,F: 01 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000001\n" +
                "M:   00000001\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA7__ANA_A() {
        // given: A=0xAA, ANA A => A=0xAA (identity), S=1, H=1, P=1
        givenPr("MVI A,AA\n" +
                "ANA A\n" +
                "NOP\n");

        givenMm("3E AA\n" +
                "A7\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  AA96\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: AA 96\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10101010\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA0__ANA_B_zero() {
        // given: A=0xF0, B=0x0F => A=0x00, Z=1, H=1 (bit3 in 0x0F), P=1
        givenPr("MVI A,F0\n" +
                "MVI B,0F\n" +
                "ANA B\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "06 0F\n" +
                "A0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0F00\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0056\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 0F 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 56\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00001111\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA0__ANA_B_h_zero() {
        // given: A=0xF0, B=0xF0 => A=0xF0, H=0 (neither operand has bit 3 set)
        givenPr("MVI A,F0\n" +
                "MVI B,F0\n" +
                "ANA B\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "06 F0\n" +
                "A0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  F000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  F086\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: F0 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: F0 86\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   11110000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11110000\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeA0__ANA_B_carry_cleared() {
        // given: carry=1 set first, then ANA always clears carry
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "MVI B,01\n" +  // B = 0x01
                "ADC B\n" +     // A = 0x00, C=1 set
                "MVI A,AA\n" +  // A = 0xAA
                "MVI B,FF\n" +  // B = 0xFF
                "ANA B\n" +     // A = 0xAA & 0xFF = 0xAA, C=0 (cleared!)
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3E AA\n" +
                "06 FF\n" +
                "A0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  FF00\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  AA96\n" +
                "SP:  0000\n" +
                "PC:  000B\n" +
                "B,C: FF 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: AA 96\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001011\n" +
                "     76543210\n" +
                "B:   11111111\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10101010\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // ANI_XX: A = A & immediate  (opcode 0xE6)

    @Test
    public void codeE6__ANI_XX_no_flags() {
        // given: A=0xFF, imm=0xF0 => A=0xF0, S=1, H=1, P=1
        givenPr("MVI A,FF\n" +
                "ANI F0\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "E6 F0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  F096\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: F0 96\n" +
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
                "M:   00111110\n" +
                "A:   11110000\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeE6__ANI_XX_zero() {
        // given: A=0xF0, imm=0x0F => A=0x00, Z=1, H=1
        givenPr("MVI A,F0\n" +
                "ANI 0F\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "E6 0F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0056\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 56\n" +
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
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeE6__ANI_XX_h_zero() {
        // given: A=0xF0, imm=0xF0 => A=0xF0, H=0 (neither has bit 3)
        givenPr("MVI A,F0\n" +
                "ANI F0\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "E6 F0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  F086\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: F0 86\n" +
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
                "M:   00111110\n" +
                "A:   11110000\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeE6__ANI_XX_carry_cleared() {
        // given: carry=1 set first, ANI always clears C
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "MVI B,01\n" +  // B = 0x01
                "ADC B\n" +     // A = 0x00, C=1 set
                "MVI A,AA\n" +  // A = 0xAA
                "ANI FF\n" +    // A = 0xAA & 0xFF = 0xAA, C=0 (cleared!)
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3E AA\n" +
                "E6 FF\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  AA96\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: AA 96\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10101010\n" +
                "     sz0h0p1c\n" +
                "F:   10010110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // CALL_XXYY: unconditional call subroutine  (opcode 0xCD)
    // pushes return address (PC after CALL) to stack, jumps to addr

    @Test
    public void codeCD__CALL_XXYY() {
        // given: CALL 0006 — jumps past INR C at 0x0003 (C=0 proves CALL was taken)
        givenPr("CALL 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("CD 06 00\n" +
                "0C\n" +
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
                "SP:  FFFE\n" +
                "PC:  000B\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   CD\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001011\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11001101\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 03 -> 03\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CALL was taken
    }

    @Test
    public void codeCD__CALL_XXYY_with_sp() {
        // given: LXI SP,FFFC first, then CALL 000A — SP=FFFA, INR C at 0x0006 skipped (C=0)
        givenPr("LXI SP,FFFC\n" +
                "CALL 000A\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("31 FC FF\n" +
                "CD 0A 00\n" +
                "0C\n" +
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
                "SP:  FFFA\n" +
                "PC:  000F\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   31\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111010\n" +
                "PC:  00000000 00001111\n" +
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
        asrtMem("FFFA: 00 -> 06 -> 06\nFFFB: 00 -> 00 -> 00");
        // C=0x00: INR C was skipped → proves CALL was taken
    }

    // CC_XXYY: CALL if carry  (opcode 0xDC)

    @Test
    public void codeDC__CC_XXYY_carry_set() {
        // given: carry=1 via ADC overflow, then CC 000C — call happens, INR C at 0x0008 skipped (C=0)
        givenPr("MVI A,FF\n" +
                "MVI B,01\n" +
                "ADC B\n" +     // A=0x00, carry=1
                "CC 000C\n" +   // carry=1 → CALL to 0x000C
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "DC 0C 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0057\n" +
                "SP:  FFFE\n" +
                "PC:  0012\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 57\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00010010\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CC was taken
    }

    @Test
    public void codeDC__CC_XXYY_carry_clear() {
        // given: carry=0 (default), CC not taken → INR C at 0x0003 executes (C=1)
        givenPr("CC 0005\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("DC 05 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0008\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   DC\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11011100\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CC was NOT taken
    }

    // CM_XXYY: CALL if minus (sign flag)  (opcode 0xFC)

    @Test
    public void codeFC__CM_XXYY_sign_set() {
        // given: ADD B sets S=1 (0x7F+0x01=0x80), CM — call happens, INR C at 0x0008 skipped (C=0)
        givenPr("MVI A,7F\n" +
                "MVI B,01\n" +
                "ADD B\n" +     // A=0x80, S=1
                "CM 000B\n" +   // S=1 → CALL to 0x000B
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 7F\n" +
                "06 01\n" +
                "80\n" +
                "FC 0B 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8092\n" +
                "SP:  FFFE\n" +
                "PC:  0010\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 92\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00010000\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10010010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CM was taken
    }

    @Test
    public void codeFC__CM_XXYY_sign_clear() {
        // given: ADD A with A=0x01 → A=0x02, S=0, CM not taken → INR C executes (C=1)
        givenPr("MVI A,01\n" +
                "ADD A\n" +     // A=0x02, S=0
                "CM 0008\n" +   // S=0 → NO call
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 01\n" +
                "87\n" +
                "FC 08 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0202\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 02 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000010\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CM was NOT taken
    }

    // CMA: complement accumulator A = A ^ 0xFF  (opcode 0x2F, no flags changed)

    @Test
    public void code2F__CMA_f0() {
        // given: A=0xF0 → CMA → A=0x0F, no flags changed
        givenPr("MVI A,F0\n" +
                "CMA\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "2F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0F02\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 0F 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00001111\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code2F__CMA_aa() {
        // given: A=0xAA → CMA → A=0x55, no flags changed
        givenPr("MVI A,AA\n" +
                "CMA\n" +
                "NOP\n");

        givenMm("3E AA\n" +
                "2F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  5502\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 55 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01010101\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // CMC: complement carry  (opcode 0x3F) — toggles C flag, no other flags changed

    @Test
    public void code3F__CMC_set_to_clear() {
        // given: set carry via ADC overflow, then CMC → carry cleared
        givenPr("MVI A,FF\n" +
                "MVI B,01\n" +
                "ADC B\n" +   // A=0x00, carry=1
                "CMC\n" +     // toggle carry → carry=0
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "3F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0056\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 01 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 56\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code3F__CMC_clear_to_set() {
        // given: carry=0 (default), CMC → carry=1
        givenPr("MVI A,01\n" +
                "CMC\n" +    // toggle carry 0→1
                "NOP\n");

        givenMm("3E 01\n" +
                "3F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0103\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 01 03\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    // CMP_R: compare register with A  (opcodes 0xB8-0xBF: B,C,D,E,H,L,M,A)
    // does A - reg, sets flags, A UNCHANGED

    @Test
    public void codeB8__CMP_B() {
        // A=0x10, B=0x05 → result=0x0B (A>B), A stays 0x10, F unchanged from subtraction
        givenPr("MVI A,10\n" +
                "MVI B,05\n" +
                "CMP B\n" +
                "NOP\n");

        givenMm("3E 10\n" +
                "06 05\n" +
                "B8\n" +
                "00");

        start();

        asrtCpu("BC:  0500\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 05 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000101\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB9__CMP_C() {
        givenPr("MVI A,10\n" +
                "MVI C,05\n" +
                "CMP C\n" +
                "NOP\n");

        givenMm("3E 10\n" +
                "0E 05\n" +
                "B9\n" +
                "00");

        start();

        asrtCpu("BC:  0005\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 05\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000101\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeBA__CMP_D() {
        givenPr("MVI A,10\n" +
                "MVI D,05\n" +
                "CMP D\n" +
                "NOP\n");

        givenMm("3E 10\n" +
                "16 05\n" +
                "BA\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0500\n" +
                "HL:  0000\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 05 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000101\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeBB__CMP_E() {
        givenPr("MVI A,10\n" +
                "MVI E,05\n" +
                "CMP E\n" +
                "NOP\n");

        givenMm("3E 10\n" +
                "1E 05\n" +
                "BB\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0005\n" +
                "HL:  0000\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 05\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000101\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeBC__CMP_H() {
        // H=0x05, A=0x10, HL=0x0500, M=memory[0x0500]=0x00
        givenPr("MVI H,05\n" +
                "MVI A,10\n" +
                "CMP H\n" +
                "NOP\n");

        givenMm("26 05\n" +
                "3E 10\n" +
                "BC\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0500\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 05 00\n" +
                "M:   00\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000101\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeBD__CMP_L() {
        // L=0x05, A=0x10, HL=0x0005, M=memory[0x0005]=NOP(0x00)
        givenPr("MVI L,05\n" +
                "MVI A,10\n" +
                "CMP L\n" +
                "NOP\n");

        givenMm("2E 05\n" +
                "3E 10\n" +
                "BD\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0005\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 05\n" +
                "M:   00\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000101\n" +
                "M:   00000000\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeBE__CMP_M() {
        // HL=0x000A, M=0x00, A=0x10 → result=0x10, H=1 (no borrow from low nibble), P=0
        givenPr("LXI H,000A\n" +
                "MVI A,10\n" +
                "CMP M\n" +
                "NOP\n");

        givenMm("21 0A 00\n" +
                "3E 10\n" +
                "BE\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  000A\n" +
                "AF:  1012\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 0A\n" +
                "M:   00\n" +
                "A,F: 10 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00001010\n" +
                "M:   00000000\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeBF__CMP_A() {
        // A=0x05, CMP A → result=0, Z=1, A unchanged
        givenPr("MVI A,05\n" +
                "CMP A\n" +
                "NOP\n");

        givenMm("3E 05\n" +
                "BF\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0556\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 05 56\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000101\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB8__CMP_B_less() {
        // A=0x05, B=0x10 → A<B, borrow: S=1, C=1, H=1, P=1, A unchanged
        givenPr("MVI A,05\n" +
                "MVI B,10\n" +
                "CMP B\n" +
                "NOP\n");

        givenMm("3E 05\n" +
                "06 10\n" +
                "B8\n" +
                "00");

        start();

        asrtCpu("BC:  1000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0597\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 10 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 05 97\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00010000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000101\n" +
                "     sz0h0p1c\n" +
                "F:   10010111\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    @Test
    public void codeB8__CMP_B_equal() {
        // A=0x05, B=0x05 → A==B, Z=1, C=0, H=1 (no nibble borrow), A unchanged
        givenPr("MVI A,05\n" +
                "MVI B,05\n" +
                "CMP B\n" +
                "NOP\n");

        givenMm("3E 05\n" +
                "06 05\n" +
                "B8\n" +
                "00");

        start();

        asrtCpu("BC:  0500\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0556\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 05 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 05 56\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000101\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000101\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // CNC_XXYY: CALL if no carry  (opcode 0xD4)

    @Test
    public void codeD4__CNC_XXYY_carry_clear() {
        // given: carry=0 (default), CNC 0006 — call happens, INR C at 0x0003 skipped (C=0)
        givenPr("CNC 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("D4 06 00\n" +
                "0C\n" +
                "00\n" +
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
                "SP:  FFFE\n" +
                "PC:  000C\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   D4\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11010100\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 03 -> 03\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CNC was taken
    }

    @Test
    public void codeD4__CNC_XXYY_carry_set() {
        // given: carry=1 via ADC overflow, CNC not taken → INR C at 0x0008 executes (C=1)
        givenPr("MVI A,FF\n" +
                "MVI B,01\n" +
                "ADC B\n" +     // A=0x00, carry=1
                "CNC 000A\n" +  // carry=1 → NO call
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "06 01\n" +
                "88\n" +
                "D4 0A 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0101\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0003\n" +
                "SP:  0000\n" +
                "PC:  000C\n" +
                "B,C: 01 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001100\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
        // C=0x01: INR C was executed → proves CNC was NOT taken
    }

    // CNZ_XXYY: CALL if not zero  (opcode 0xC4)

    @Test
    public void codeC4__CNZ_XXYY_nz() {
        // given: MVI A,01 → Z=0, CNZ 0006 — call happens, INR C at 0x0005 skipped (C=0)
        givenPr("MVI A,01\n" +
                "CNZ 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 01\n" +
                "C4 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0102\n" +
                "SP:  FFFE\n" +
                "PC:  000A\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 05 -> 05\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CNZ was taken
    }

    @Test
    public void codeC4__CNZ_XXYY_zero() {
        // given: Z=1 via ADD A(0x00), CNZ not taken → INR C at 0x0006 executes (C=1)
        givenPr("MVI A,00\n" +
                "ADD A\n" +     // A=0, sets Z=1
                "CNZ 0008\n" +  // Z=1 → NO call
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 00\n" +
                "87\n" +
                "C4 08 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CNZ was NOT taken
    }

    // CP_XXYY: CALL if positive (sign=0)  (opcode 0xF4)

    @Test
    public void codeF4__CP_XXYY_positive() {
        // given: MVI A,01 → S=0, CP 0006 — call happens, INR C at 0x0005 skipped (C=0)
        givenPr("MVI A,01\n" +
                "CP 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 01\n" +
                "F4 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0102\n" +
                "SP:  FFFE\n" +
                "PC:  000A\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 05 -> 05\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CP was taken
    }

    @Test
    public void codeF4__CP_XXYY_negative() {
        // given: ADD A with A=0x40 → A=0x80, S=1, CP not taken → INR C executes (C=1)
        givenPr("MVI A,40\n" +
                "ADD A\n" +     // A=0x80, S=1
                "CP 0007\n" +   // S=1 → NO call
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 40\n" +
                "87\n" +
                "F4 07 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8002\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CP was NOT taken
    }

    // CPE_XXYY: CALL if parity even (P=1)  (opcode 0xEC)

    @Test
    public void codeEC__CPE_XXYY_even() {
        // given: ADI 03 → A=0x03 (P=1), CPE 0006 — call happens, INR C at 0x0005 skipped (C=0)
        givenPr("ADI 03\n" +
                "CPE 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 03\n" +
                "EC 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0306\n" +
                "SP:  FFFE\n" +
                "PC:  000A\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 03 06\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 05 -> 05\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CPE was taken
    }

    @Test
    public void codeEC__CPE_XXYY_odd() {
        // given: ADI 01 → A=0x01 (P=0), CPE not taken → INR C at 0x0005 executes (C=1)
        givenPr("ADI 01\n" +
                "CPE 0007\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 01\n" +
                "EC 07 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0102\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CPE was NOT taken
    }

    // CPI_XX: compare immediate with A (opcode 0xFE)
    // does A - n, sets flags, A UNCHANGED

    @Test
    public void codeFE__CPI_XX_greater() {
        // A=0x10, CPI 05 → 0x10-0x05=0x0B, no special flags
        givenPr("MVI A,10\n" +
                "CPI 05\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 10\n" +
                "FE 05\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeFE__CPI_XX_equal() {
        // A=0x05, CPI 05 → 0x05-0x05=0x00, Z=1, H=1, P=1
        givenPr("MVI A,05\n" +
                "CPI 05\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 05\n" +
                "FE 05\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0556\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 05 56\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000101\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeFE__CPI_XX_less() {
        // A=0x05, CPI 10 → 0x05-0x10=-0x0B, S=1, H=1, P=1, C=1
        givenPr("MVI A,05\n" +
                "CPI 10\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 05\n" +
                "FE 10\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0597\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 05 97\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000101\n" +
                "     sz0h0p1c\n" +
                "F:   10010111\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    // CPO_XXYY: CALL if parity odd (P=0)  (opcode 0xE4)

    @Test
    public void codeE4__CPO_XXYY_odd() {
        // given: ADI 01 → A=0x01 (P=0), CPO 0006 — call happens, INR C at 0x0005 skipped (C=0)
        givenPr("ADI 01\n" +
                "CPO 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 01\n" +
                "E4 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0102\n" +
                "SP:  FFFE\n" +
                "PC:  000A\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 05 -> 05\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CPO was taken
    }

    @Test
    public void codeE4__CPO_XXYY_even() {
        // given: ADI 03 → A=0x03 (P=1), CPO not taken → INR C at 0x0005 executes (C=1)
        givenPr("ADI 03\n" +
                "CPO 0007\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 03\n" +
                "E4 07 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0302\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 03 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CPO was NOT taken
    }

    // CZ_XXYY: CALL if zero (Z=1)  (opcode 0xCC)

    @Test
    public void codeCC__CZ_XXYY_zero() {
        // given: ADD A with A=0 → Z=1, CZ 0005 — call happens, INR C at 0x0004 skipped (C=0)
        givenPr("ADD A\n" +
                "CZ 0005\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("87\n" +
                "CC 05 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0046\n" +
                "SP:  FFFE\n" +
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   87\n" +
                "A,F: 00 46\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   10000111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01000110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 04 -> 04\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves CZ was taken
    }

    @Test
    public void codeCC__CZ_XXYY_nz() {
        // given: Z=0 (default), CZ not taken → INR C at 0x0003 executes (C=1)
        givenPr("CZ 0004\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("CC 04 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   CC\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11001100\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves CZ was NOT taken
    }

    // DAA: decimal adjust accumulator  (opcode 0x27)

    @Test
    public void code27__DAA_no_change() {
        // A=0x05, ADI 03 → A=0x08 (valid BCD), DAA → no change
        givenPr("MVI A,05\n" +
                "ADI 03\n" +
                "DAA\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 05\n" +
                "C6 03\n" +
                "27\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0802\n" +
                "SP:  0000\n" +
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 08 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00001000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code27__DAA_lower_nibble_fix() {
        // A=0x09, ADI 01 → A=0x0A, DAA adds 6 to lower nibble → A=0x10 (BCD 10)
        givenPr("MVI A,09\n" +
                "ADI 01\n" +
                "DAA\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 09\n" +
                "C6 01\n" +
                "27\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1012\n" +
                "SP:  0000\n" +
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 10 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code27__DAA_upper_nibble_fix() {
        // A=0x90, ADI 10 → A=0xA0, DAA adds 0x60 → A=0x00 with carry (BCD 100)
        givenPr("MVI A,90\n" +
                "ADI 10\n" +
                "DAA\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 90\n" +
                "C6 10\n" +
                "27\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0047\n" +
                "SP:  0000\n" +
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 47\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01000111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    // DCX_RR: decrement register pair  (opcodes 0x0B=BC, 0x1B=DE, 0x2B=HL, 0x3B=SP)
    // no flags affected

    @Test
    public void code0B__DCX_B() {
        // LXI B,0010 → BC=0x0010, DCX B → BC=0x000F
        givenPr("LXI B,0010\n" +
                "DCX B\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("01 10 00\n" +
                "0B\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  000F\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 0F\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   01\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00001111\n" +
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
    public void code1B__DCX_D() {
        // LXI D,0020 → DE=0x0020, DCX D → DE=0x001F
        givenPr("LXI D,0020\n" +
                "DCX D\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("11 20 00\n" +
                "1B\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  001F\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 1F\n" +
                "H,L: 00 00\n" +
                "M:   11\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00011111\n" +
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
    public void code2B__DCX_H() {
        // LXI H,0030 → HL=0x0030, DCX H → HL=0x002F
        givenPr("LXI H,0030\n" +
                "DCX H\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("21 30 00\n" +
                "2B\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  002F\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 2F\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00101111\n" +
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
    public void code3B__DCX_SP() {
        // LXI SP,0040 → SP=0x0040, DCX SP → SP=0x003F
        givenPr("LXI SP,0040\n" +
                "DCX SP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("31 40 00\n" +
                "3B\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  003F\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   31\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00111111\n" +
                "PC:  00000000 00000111\n" +
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

    // DI: disable interrupts  (opcode 0xF3)
    // no-op in this implementation

    @Test
    public void codeF3__DI() {
        givenPr("DI\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("F3\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   F3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11110011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // EI: enable interrupts  (opcode 0xFB)
    // no-op in this implementation

    @Test
    public void codeFB__EI() {
        givenPr("EI\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("FB\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   FB\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11111011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // IN_XX: input from port to A  (opcode 0xDB)
    // port address = (A << 8) | operand; A = in8(port); in8 returns 0xFF in test environment

    @Test
    public void codeDB__IN_XX() {
        // given: A=0x01, IN 02 → port = 0x0102, in8 returns 0xFF → A=0xFF
        givenPr("MVI A,01\n" +
                "IN 02\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 01\n" +
                "DB 02\n" +
                "00\n" +
                "00\n" +
                "00");

        start();

        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  FF02\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: FF 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // JC_XXYY: JUMP if carry (C=1)  (opcode 0xDA)

    @Test
    public void codeDA__JC_XXYY_carry_set() {
        // given: MVI A,FF + ADI 01 → carry=1, JC 0009 → jump taken, INR C at 0x0007 skipped (C=0)
        givenPr("MVI A,FF\n" +
                "ADI 01\n" +
                "JC 0009\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "C6 01\n" +
                "DA 09 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0057\n" +
                "SP:  0000\n" +
                "PC:  000D\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 57\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
        // C=0x00: INR C was skipped → proves JC was taken
    }

    @Test
    public void codeDA__JC_XXYY_carry_clear() {
        // given: carry=0 (default), JC not taken → INR C at 0x0003 executes (C=1)
        givenPr("JC 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("DA 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   DA\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11011010\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JC was NOT taken
    }

    // JM_XXYY: JUMP if minus (S=1)  (opcode 0xFA)

    @Test
    public void codeFA__JM_XXYY_sign_set() {
        // given: MVI A,40 + ADD A → S=1; JM 0008 → jump taken, INR C at 0x0006 skipped (C=0)
        givenPr("MVI A,40\n" +
                "ADD A\n" +
                "JM 0008\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 40\n" +
                "87\n" +
                "FA 08 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8082\n" +
                "SP:  0000\n" +
                "PC:  000C\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 82\n" +
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
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JM was taken
    }

    @Test
    public void codeFA__JM_XXYY_sign_clear() {
        // given: sign=0 (default), JM not taken → INR C at 0x0003 executes (C=1)
        givenPr("JM 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("FA 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   FA\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11111010\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JM was NOT taken
    }

    // JMP_XXYY: unconditional JUMP  (opcode 0xC3)

    @Test
    public void codeC3__JMP_XXYY() {
        // given: JMP 0005 → jump taken, INR C at 0x0003 skipped (C=0 proves jump was taken)
        givenPr("JMP 0005\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "0C\n" +
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
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JMP was taken
    }

    // JNC_XXYY: JUMP if no carry (C=0)  (opcode 0xD2)

    @Test
    public void codeD2__JNC_XXYY_carry_clear() {
        // given: carry=0 (default), JNC 0004 → jump taken, INR C at 0x0003 skipped (C=0)
        givenPr("JNC 0004\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("D2 04 00\n" +
                "0C\n" +
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
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   D2\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11010010\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JNC was taken
    }

    @Test
    public void codeD2__JNC_XXYY_carry_set() {
        // given: carry=1 via ADI overflow, JNC not taken → INR C at 0x0007 executes (C=1)
        givenPr("MVI A,FF\n" +
                "ADI 01\n" +
                "JNC 000A\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "C6 01\n" +
                "D2 0A 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0003\n" +
                "SP:  0000\n" +
                "PC:  000B\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001011\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
        // C=0x01: INR C was executed → proves JNC was NOT taken
    }

    // JNZ_XXYY: JUMP if not zero (Z=0)  (opcode 0xC2)

    @Test
    public void codeC2__JNZ_XXYY_not_zero() {
        // given: Z=0 (default), JNZ 0004 → jump taken, INR C at 0x0003 skipped (C=0)
        givenPr("JNZ 0004\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C2 04 00\n" +
                "0C\n" +
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
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C2\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000010\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JNZ was taken
    }

    @Test
    public void codeC2__JNZ_XXYY_zero() {
        // given: Z=1 via ADD A(0), JNZ not taken → INR C at 0x0005 executes (C=1)
        givenPr("MVI A,00\n" +
                "ADD A\n" +
                "JNZ 0008\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 00\n" +
                "87\n" +
                "C2 08 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JNZ was NOT taken
    }

    // JP_XXYY: JUMP if positive (sign=0)  (opcode 0xF2)

    @Test
    public void codeF2__JP_XXYY_positive() {
        // given: S=0 (default), JP 0004 → jump taken, INR C at 0x0003 skipped (C=0)
        givenPr("JP 0004\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("F2 04 00\n" +
                "0C\n" +
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
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   F2\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11110010\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JP was taken
    }

    @Test
    public void codeF2__JP_XXYY_negative() {
        // given: S=1 via ADD A(0x40), JP not taken → INR C at 0x0006 executes (C=1)
        givenPr("MVI A,40\n" +
                "ADD A\n" +
                "JP 0009\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E 40\n" +
                "87\n" +
                "F2 09 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8002\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 80 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JP was NOT taken
    }

    // JPE_XXYY: JUMP if parity even (P=1)  (opcode 0xEA)

    @Test
    public void codeEA__JPE_XXYY_even() {
        // given: ADI 03 → A=0x03 (P=1 even parity), JPE 0006 → jump past INR C, INR C skipped (C=0)
        givenPr("ADI 03\n" +
                "JPE 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 03\n" +
                "EA 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0306\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 03 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JPE was taken
    }

    @Test
    public void codeEA__JPE_XXYY_odd() {
        // given: ADI 01 → A=0x01 (P=0 odd parity), JPE not taken → INR C executes (C=1)
        givenPr("ADI 01\n" +
                "JPE 0008\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 01\n" +
                "EA 08 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0102\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JPE was NOT taken
    }

    // JPO_XXYY: JUMP if parity odd (P=0)  (opcode 0xE2)

    @Test
    public void codeE2__JPO_XXYY_odd() {
        // given: ADI 01 → A=0x01 (P=0 odd parity), JPO 0006 → jump past INR C, INR C skipped (C=0)
        givenPr("ADI 01\n" +
                "JPO 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 01\n" +
                "E2 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0102\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JPO was taken
    }

    @Test
    public void codeE2__JPO_XXYY_even() {
        // given: ADI 03 → A=0x03 (P=1 even parity), JPO not taken → INR C executes (C=1)
        givenPr("ADI 03\n" +
                "JPO 0008\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 03\n" +
                "E2 08 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0302\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 03 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JPO was NOT taken
    }

    // JZ_XXYY: JUMP if zero (Z=1)  (opcode 0xCA)

    @Test
    public void codeCA__JZ_XXYY_zero() {
        // given: ADD A → Z=1, JZ 0005 → jump past INR C at 0x0004, INR C skipped (C=0)
        givenPr("ADD A\n" +
                "JZ 0005\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("87\n" +
                "CA 05 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0046\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   87\n" +
                "A,F: 00 46\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   10000111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01000110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves JZ was taken
    }

    @Test
    public void codeCA__JZ_XXYY_not_zero() {
        // given: Z=0 (default), JZ not taken → INR C at 0x0003 executes (C=1)
        givenPr("JZ 0006\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("CA 06 00\n" +
                "0C\n" +
                "00\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   CA\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11001010\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves JZ was NOT taken
    }

    // ORA_R: A = A | register  (opcodes 0xB0-0xB7: B,C,D,E,H,L,M,A)
    // C always cleared; H always cleared

    @Test
    public void codeB0__ORA_B() {
        // given: A=0xF0, B=0x0F => 0xF0|0x0F=0xFF, S=1, P=1
        givenPr("MVI A,F0\n" +
                "MVI B,0F\n" +
                "ORA B\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "06 0F\n" +
                "B0\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0F00\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  FF86\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 0F 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: FF 86\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00001111\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB1__ORA_C() {
        // given: A=0x0F, C=0xF0 => 0x0F|0xF0=0xFF, S=1, P=1
        givenPr("MVI A,0F\n" +
                "MVI C,F0\n" +
                "ORA C\n" +
                "NOP\n");

        givenMm("3E 0F\n" +
                "0E F0\n" +
                "B1\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  00F0\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  FF86\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 F0\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: FF 86\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   11110000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB2__ORA_D() {
        // given: A=0xAA, D=0x55 => 0xAA|0x55=0xFF, S=1, P=1
        givenPr("MVI A,AA\n" +
                "MVI D,55\n" +
                "ORA D\n" +
                "NOP\n");

        givenMm("3E AA\n" +
                "16 55\n" +
                "B2\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  5500\n" +
                "HL:  0000\n" +
                "AF:  FF86\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 55 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: FF 86\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   01010101\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB3__ORA_E() {
        // given: A=0x01, E=0x02 => 0x01|0x02=0x03, S=0, P=1
        givenPr("MVI A,01\n" +
                "MVI E,02\n" +
                "ORA E\n" +
                "NOP\n");

        givenMm("3E 01\n" +
                "1E 02\n" +
                "B3\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0002\n" +
                "HL:  0000\n" +
                "AF:  0306\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 02\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 03 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000010\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB4__ORA_H() {
        // given: A=0x12, H=0x21 => 0x12|0x21=0x33, S=0, P=1
        givenPr("MVI A,12\n" +
                "MVI H,21\n" +
                "ORA H\n" +
                "NOP\n");

        givenMm("3E 12\n" +
                "26 21\n" +
                "B4\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  2100\n" +
                "AF:  3306\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 21 00\n" +
                "M:   00\n" +
                "A,F: 33 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00100001\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00110011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB5__ORA_L() {
        // given: A=0xC0, L=0x3C => 0xC0|0x3C=0xFC, S=1, P=1
        givenPr("MVI A,C0\n" +
                "MVI L,3C\n" +
                "ORA L\n" +
                "NOP\n");

        givenMm("3E C0\n" +
                "2E 3C\n" +
                "B5\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  003C\n" +
                "AF:  FC86\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 3C\n" +
                "M:   00\n" +
                "A,F: FC 86\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00111100\n" +
                "M:   00000000\n" +
                "A:   11111100\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB6__ORA_M() {
        // given: A=0xF0, HL=0x0000, M=mem[0x0000]=0x3E => 0xF0|0x3E=0xFE, S=1, P=0
        givenPr("MVI A,F0\n" +
                "ORA M\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "B6\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  FE82\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: FE 82\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   11111110\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeB7__ORA_A() {
        // given: A=0x5A => 0x5A|0x5A=0x5A, S=0, P=1
        givenPr("MVI A,5A\n" +
                "ORA A\n" +
                "NOP\n");

        givenMm("3E 5A\n" +
                "B7\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  5A06\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 5A 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01011010\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // ORI_XX: A = A | immediate  (opcode 0xF6)
    // C always cleared; H always cleared

    @Test
    public void codeF6__ORI_XX() {
        // given: A=0xF0, imm=0x0F => 0xF0|0x0F=0xFF, S=1, P=1
        givenPr("MVI A,F0\n" +
                "ORI 0F\n" +
                "NOP\n");

        givenMm("3E F0\n" +
                "F6 0F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  FF86\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: FF 86\n" +
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
                "M:   00111110\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void codeF6__ORI_XX_zero() {
        // given: A=0x00, imm=0x00 => 0x00|0x00=0x00, Z=1, P=1
        givenPr("ORI 00\n" +
                "NOP\n");

        givenMm("F6 00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0046\n" +
                "SP:  0000\n" +
                "PC:  0003\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   F6\n" +
                "A,F: 00 46\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000011\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11110110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01000110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    // OUT_XX: output A to port  (opcode 0xD3)
    // port = operand byte; no flags or registers changed

    @Test
    public void codeD3__OUT_XX() {
        // given: A=0x42, OUT 01 => A=0x42 sent to port 1, no register changes
        givenPr("MVI A,42\n" +
                "OUT 01\n" +
                "NOP\n");

        givenMm("3E 42\n" +
                "D3 01\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  4202\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 42 02\n" +
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
                "M:   00111110\n" +
                "A:   01000010\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // PCHL: PC = HL  (opcode 0xE9)
    // Unconditional jump to address in HL register pair; no flags changed
    // INR C sentinel: C=0x00 means PCHL was taken (INR C skipped)

    @Test
    public void codeE9__PCHL() {
        // given: HL=0x0005, PCHL => PC jumps to 0x0005; INR C at 0x0004 skipped (C=0x00)
        givenPr("LXI H,0005\n" +
                "PCHL\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("21 05 00\n" +
                "E9\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0005\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 05\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x00: INR C was skipped → proves PCHL was taken
    }

    // POP_RS: pop register pair from stack  (opcodes 0xC1=BC, 0xD1=DE, 0xE1=HL, 0xF1=PSW)
    // rl = mem[SP], rh = mem[SP+1], SP += 2; no flags changed (except POP PSW restores F)

    @Test
    public void codeC1__POP_B() {
        // given: push BC=0x1234 to stack, clear BC, then pop → BC restored to 0x1234
        givenPr("LXI B,1234\n" +
                "LXI SP,FFFE\n" +
                "PUSH B\n" +
                "LXI B,0000\n" +
                "POP B\n" +
                "NOP\n");

        givenMm("01 34 12\n" +
                "31 FE FF\n" +
                "C5\n" +
                "01 00 00\n" +
                "C1\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  000C\n" +
                "B,C: 12 34\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   01\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001100\n" +
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
        asrtMem("FFFC: 00 -> 34 -> 34\nFFFD: 00 -> 12 -> 12");
    }

    @Test
    public void codeD1__POP_D() {
        // given: push DE=0x5678 to stack, clear DE, then pop → DE restored to 0x5678
        givenPr("LXI D,5678\n" +
                "LXI SP,FFFE\n" +
                "PUSH D\n" +
                "LXI D,0000\n" +
                "POP D\n" +
                "NOP\n");

        givenMm("11 78 56\n" +
                "31 FE FF\n" +
                "D5\n" +
                "11 00 00\n" +
                "D1\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  5678\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  000C\n" +
                "B,C: 00 00\n" +
                "D,E: 56 78\n" +
                "H,L: 00 00\n" +
                "M:   11\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
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
        asrtMem("FFFC: 00 -> 78 -> 78\nFFFD: 00 -> 56 -> 56");
    }

    @Test
    public void codeE1__POP_H() {
        // given: push HL=0xABCD to stack, clear HL, then pop → HL restored to 0xABCD
        givenPr("LXI H,ABCD\n" +
                "LXI SP,FFFE\n" +
                "PUSH H\n" +
                "LXI H,0000\n" +
                "POP H\n" +
                "NOP\n");

        givenMm("21 CD AB\n" +
                "31 FE FF\n" +
                "E5\n" +
                "21 00 00\n" +
                "E1\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  ABCD\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  000C\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: AB CD\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   10101011\n" +
                "L:   11001101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFC: 00 -> CD -> CD\nFFFD: 00 -> AB -> AB");
    }

    @Test
    public void codeF1__POP_PSW() {
        // given: MVI A,5A + ORI 00 → A=0x5A F=0x06; push PSW; MVI A,00; pop PSW → A/F restored
        givenPr("MVI A,5A\n" +
                "ORI 00\n" +
                "LXI SP,FFFE\n" +
                "PUSH PSW\n" +
                "MVI A,00\n" +
                "POP PSW\n" +
                "NOP\n");

        givenMm("3E 5A\n" +
                "F6 00\n" +
                "31 FE FF\n" +
                "F5\n" +
                "3E 00\n" +
                "F1\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  5A06\n" +
                "SP:  FFFE\n" +
                "PC:  000C\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 5A 06\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01011010\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        asrtMem("FFFC: 00 -> 06 -> 06\nFFFD: 00 -> 5A -> 5A");
    }

    // PUSH_RS: push register pair onto stack  (opcodes 0xC5=BC, 0xD5=DE, 0xE5=HL, 0xF5=PSW)
    // (SP-1) = rh, (SP-2) = rl, SP -= 2; no flags changed

    @Test
    public void codeC5__PUSH_B() {
        // given: LXI SP,FFFE, LXI B,1234, PUSH B => mem[FFFD]=0x12, mem[FFFC]=0x34, SP=FFFC
        givenPr("LXI SP,FFFE\n" +
                "LXI B,1234\n" +
                "PUSH B\n" +
                "NOP\n");

        givenMm("31 FE FF\n" +
                "01 34 12\n" +
                "C5\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1234\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFC\n" +
                "PC:  0008\n" +
                "B,C: 12 34\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   31\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111100\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   00110100\n" +
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
        asrtMem("FFFC: 00 -> 34 -> 34\nFFFD: 00 -> 12 -> 12");
    }

    @Test
    public void codeD5__PUSH_D() {
        // given: LXI SP,FFFE, LXI D,5678, PUSH D => mem[FFFD]=0x56, mem[FFFC]=0x78, SP=FFFC
        givenPr("LXI SP,FFFE\n" +
                "LXI D,5678\n" +
                "PUSH D\n" +
                "NOP\n");

        givenMm("31 FE FF\n" +
                "11 78 56\n" +
                "D5\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  5678\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFC\n" +
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 56 78\n" +
                "H,L: 00 00\n" +
                "M:   31\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111100\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   01010110\n" +
                "E:   01111000\n" +
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
        asrtMem("FFFC: 00 -> 78 -> 78\nFFFD: 00 -> 56 -> 56");
    }

    @Test
    public void codeE5__PUSH_H() {
        // given: LXI SP,FFFE, LXI H,ABCD, PUSH H => mem[FFFD]=0xAB, mem[FFFC]=0xCD, SP=FFFC
        givenPr("LXI SP,FFFE\n" +
                "LXI H,ABCD\n" +
                "PUSH H\n" +
                "NOP\n");

        givenMm("31 FE FF\n" +
                "21 CD AB\n" +
                "E5\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  ABCD\n" +
                "AF:  0002\n" +
                "SP:  FFFC\n" +
                "PC:  0008\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: AB CD\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111100\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   10101011\n" +
                "L:   11001101\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFC: 00 -> CD -> CD\nFFFD: 00 -> AB -> AB");
    }

    @Test
    public void codeF5__PUSH_PSW() {
        // given: MVI A,5A + ORI 00 → A=0x5A F=0x06; LXI SP,FFFE; PUSH PSW
        givenPr("MVI A,5A\n" +
                "ORI 00\n" +
                "LXI SP,FFFE\n" +
                "PUSH PSW\n" +
                "NOP\n");

        givenMm("3E 5A\n" +
                "F6 00\n" +
                "31 FE FF\n" +
                "F5\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  5A06\n" +
                "SP:  FFFC\n" +
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 5A 06\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111100\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   01011010\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        asrtMem("FFFC: 00 -> 06 -> 06\nFFFD: 00 -> 5A -> 5A");
    }

    // RC: return if carry (C=1)  (opcode 0xD8)
    // INR C sentinel: C=0x00 means RC was taken (INR C skipped)

    @Test
    public void codeD8__RC_carry_set() {
        // given: JMP over subroutine, setup carry=1, CALL 0003, RC taken (C=0)
        // subroutine at 0x0003: RC, INR C (sentinel)
        givenPr("JMP 0005\n" +
                "RC\n" +
                "INR C\n" +
                "MVI A,FF\n" +
                "ADI 01\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "D8\n" +
                "0C\n" +
                "3E FF\n" +
                "C6 01\n" +
                "CD 03 00\n" +
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
                "AF:  0057\n" +
                "SP:  0000\n" +
                "PC:  0011\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 57\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00010001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010111\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
        asrtMem("FFFE: 00 -> 0C -> 0C\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RC was taken
    }

    @Test
    public void codeD8__RC_carry_clear() {
        // given: carry=0 (default), RC not taken → INR C executes (C=0x01)
        givenPr("RC\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("D8\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   D8\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11011000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RC was NOT taken
    }

    // RET: unconditional return  (opcode 0xC9)
    // pops return address from stack, jumps there; no flags changed
    // INR C sentinel: C=0x00 means RET was taken (INR C skipped)

    @Test
    public void codeC9__RET() {
        // given: JMP 0005 (past subroutine), CALL 0003 pushes 0x0008, RET jumps to 0x0008 (C=0)
        // subroutine at 0x0003: RET, INR C (sentinel, skipped by RET)
        givenPr("JMP 0005\n" +
                "RET\n" +
                "INR C\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "C9\n" +
                "0C\n" +
                "CD 03 00\n" +
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
                "PC:  000D\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RET was taken
    }

    // RM: return if minus (S=1)  (opcode 0xF8)
    // INR C sentinel: C=0x00 means RM was taken (INR C skipped)

    @Test
    public void codeF8__RM_minus() {
        // given: JMP 0005, ADI 80 sets S=1, CALL 0003, RM taken (C=0)
        // subroutine at 0x0003: RM, INR C
        givenPr("JMP 0005\n" +
                "RM\n" +
                "INR C\n" +
                "ADI 80\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "F8\n" +
                "0C\n" +
                "C6 80\n" +
                "CD 03 00\n" +
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
                "AF:  8082\n" +
                "SP:  0000\n" +
                "PC:  000F\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 80 82\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 0A -> 0A\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RM was taken
    }

    @Test
    public void codeF8__RM_positive() {
        // given: S=0 (default), RM not taken → INR C executes (C=0x01)
        givenPr("RM\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("F8\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   F8\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11111000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RM was NOT taken
    }

    // RNC: return if no carry (C=0)  (opcode 0xD0)
    // INR C sentinel: C=0x00 means RNC was taken (INR C skipped)

    @Test
    public void codeD0__RNC_carry_clear() {
        // given: JMP 0005, carry=0 (default), CALL 0003, RNC taken (C=0)
        // subroutine at 0x0003: RNC, INR C
        givenPr("JMP 0005\n" +
                "RNC\n" +
                "INR C\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "D0\n" +
                "0C\n" +
                "CD 03 00\n" +
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
                "PC:  000D\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RNC was taken
    }

    @Test
    public void codeD0__RNC_carry_set() {
        // given: carry=1 via ADI FF+ADI 01, RNC not taken → INR C executes (C=0x01)
        givenPr("MVI A,FF\n" +
                "ADI 01\n" +
                "RNC\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("3E FF\n" +
                "C6 01\n" +
                "D0\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0003\n" +
                "SP:  0000\n" +
                "PC:  0008\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 03\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  true\n");
        // C=0x01: INR C was executed → proves RNC was NOT taken
    }

    // RNZ: return if not zero (Z=0)  (opcode 0xC0)
    // INR C sentinel: C=0x00 means RNZ was taken (INR C skipped)

    @Test
    public void codeC0__RNZ_not_zero() {
        // given: JMP 0005, Z=0 (default), CALL 0003, RNZ taken (C=0)
        // subroutine at 0x0003: RNZ, INR C
        givenPr("JMP 0005\n" +
                "RNZ\n" +
                "INR C\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "C0\n" +
                "0C\n" +
                "CD 03 00\n" +
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
                "PC:  000D\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RNZ was taken
    }

    @Test
    public void codeC0__RNZ_zero() {
        // given: ADD A with A=0 sets Z=1; RNZ not taken → INR C executes (C=0x01)
        givenPr("ADD A\n" +
                "RNZ\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("87\n" +
                "C0\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   87\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   10000111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RNZ was NOT taken
    }

    // RP: return if positive (S=0)  (opcode 0xF0)
    // INR C sentinel: C=0x00 means RP was taken (INR C skipped)

    @Test
    public void codeF0__RP_positive() {
        // given: JMP 0005, S=0 (default), CALL 0003, RP taken (C=0)
        // subroutine at 0x0003: RP, INR C
        givenPr("JMP 0005\n" +
                "RP\n" +
                "INR C\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "F0\n" +
                "0C\n" +
                "CD 03 00\n" +
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
                "PC:  000D\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RP was taken
    }

    @Test
    public void codeF0__RP_negative() {
        // given: ADI 80 sets S=1; RP not taken → INR C executes (C=0x01)
        givenPr("ADI 80\n" +
                "RP\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 80\n" +
                "F0\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  8002\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 80 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   10000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RP was NOT taken
    }

    // RPE: return if parity even (P=1)  (opcode 0xE8)
    // INR C sentinel: C=0x00 means RPE was taken (INR C skipped)

    @Test
    public void codeE8__RPE_even() {
        // given: JMP 0005, ADI 03 sets P=1 (0x03=2bits=even), CALL 0003, RPE taken (C=0)
        // subroutine at 0x0003: RPE, INR C
        givenPr("JMP 0005\n" +
                "RPE\n" +
                "INR C\n" +
                "ADI 03\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "E8\n" +
                "0C\n" +
                "C6 03\n" +
                "CD 03 00\n" +
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
                "AF:  0306\n" +
                "SP:  0000\n" +
                "PC:  000F\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 03 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 0A -> 0A\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RPE was taken
    }

    @Test
    public void codeE8__RPE_odd() {
        // given: P=0 (default), RPE not taken → INR C executes (C=0x01)
        givenPr("RPE\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("E8\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   E8\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11101000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RPE was NOT taken
    }

    // RPO: return if parity odd (P=0)  (opcode 0xE0)
    // INR C sentinel: C=0x00 means RPO was taken (INR C skipped)

    @Test
    public void codeE0__RPO_odd() {
        // given: JMP 0005, P=0 (default), CALL 0003, RPO taken (C=0)
        // subroutine at 0x0003: RPO, INR C
        givenPr("JMP 0005\n" +
                "RPO\n" +
                "INR C\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "E0\n" +
                "0C\n" +
                "CD 03 00\n" +
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
                "PC:  000D\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001101\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 08 -> 08\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RPO was taken
    }

    @Test
    public void codeE0__RPO_even() {
        // given: ADI 03 sets P=1 (0x03=2bits=even); RPO not taken → INR C executes (C=0x01)
        givenPr("ADI 03\n" +
                "RPO\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C6 03\n" +
                "E0\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0302\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C6\n" +
                "A,F: 03 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000110\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RPO was NOT taken
    }

    // RZ: return if zero (Z=1)  (opcode 0xC8)
    // INR C sentinel: C=0x00 means RZ was taken (INR C skipped)

    @Test
    public void codeC8__RZ_zero() {
        // given: JMP 0005, ADD A sets Z=1, CALL 0003, RZ taken (C=0)
        // subroutine at 0x0003: RZ, INR C
        givenPr("JMP 0005\n" +
                "RZ\n" +
                "INR C\n" +
                "ADD A\n" +
                "CALL 0003\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C3 05 00\n" +
                "C8\n" +
                "0C\n" +
                "87\n" +
                "CD 03 00\n" +
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
                "AF:  0046\n" +
                "SP:  0000\n" +
                "PC:  000E\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C3\n" +
                "A,F: 00 46\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01000110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 09 -> 09\nFFFF: 00 -> 00");
        // C=0x00: INR C was skipped → proves RZ was taken
    }

    @Test
    public void codeC8__RZ_not_zero() {
        // given: Z=0 (default), RZ not taken → INR C executes (C=0x01)
        givenPr("RZ\n" +
                "INR C\n" +
                "NOP\n" +
                "NOP\n");

        givenMm("C8\n" +
                "0C\n" +
                "00\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0001\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 01\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   C8\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11001000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        // C=0x01: INR C was executed → proves RZ was NOT taken
    }

    // RST N: restart — push return address, jump to N*8
    // RST 0 (0xC7 → 0x0000), RST 1 (0xCF → 0x0008), ..., RST 7 (0xFF → 0x0038)
    // Strategy: INR C sentinel at 0x0001 (or after RST) — C=0 proves RST was taken (jumped away)

    @Test
    public void codeC7__RST_0() {
        // given: NOPs at 0x0000-0x0002, RST 0 at 0x0003, INR C at 0x0004
        // RST 0 jumps to 0x0000; INR C never executed → C=0
        givenPr("NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "RST 0\n" +
                "INR C\n");

        givenMm("00\n" +
                "00\n" +
                "00\n" +
                "C7\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0001\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00000001\n" +
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
        asrtMem("FFFE: 00 -> 04 -> 04\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0004 was never executed → RST 0 jumped to 0x0000
    }

    @Test
    public void codeCF__RST_1() {
        // given: RST 1 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 1 jumps to 0x0008; INR C never executed → C=0
        givenPr("RST 1\n" +
                "INR C\n");

        givenMm("CF\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   CF\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11001111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 1 jumped to 0x0008
    }

    @Test
    public void codeD7__RST_2() {
        // given: RST 2 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 2 jumps to 0x0010; INR C never executed → C=0
        givenPr("RST 2\n" +
                "INR C\n");

        givenMm("D7\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0011\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   D7\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00010001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11010111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 2 jumped to 0x0010
    }

    @Test
    public void codeDF__RST_3() {
        // given: RST 3 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 3 jumps to 0x0018; INR C never executed → C=0
        givenPr("RST 3\n" +
                "INR C\n");

        givenMm("DF\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0019\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   DF\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00011001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11011111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 3 jumped to 0x0018
    }

    @Test
    public void codeE7__RST_4() {
        // given: RST 4 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 4 jumps to 0x0020; INR C never executed → C=0
        givenPr("RST 4\n" +
                "INR C\n");

        givenMm("E7\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0021\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   E7\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00100001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11100111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 4 jumped to 0x0020
    }

    @Test
    public void codeEF__RST_5() {
        // given: RST 5 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 5 jumps to 0x0028; INR C never executed → C=0
        givenPr("RST 5\n" +
                "INR C\n");

        givenMm("EF\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0029\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   EF\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00101001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11101111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 5 jumped to 0x0028
    }

    @Test
    public void codeF7__RST_6() {
        // given: RST 6 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 6 jumps to 0x0030; INR C never executed → C=0
        givenPr("RST 6\n" +
                "INR C\n");

        givenMm("F7\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0031\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   F7\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00110001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11110111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 6 jumped to 0x0030
    }

    @Test
    public void codeFF__RST_7() {
        // given: RST 7 at 0x0000, INR C at 0x0001 (sentinel)
        // RST 7 jumps to 0x0038; INR C never executed → C=0
        givenPr("RST 7\n" +
                "INR C\n");

        givenMm("FF\n" +
                "0C");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFFE\n" +
                "PC:  0039\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   FF\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111110\n" +
                "PC:  00000000 00111001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   11111111\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
        asrtMem("FFFE: 00 -> 01 -> 01\nFFFF: 00 -> 00");
        // C=0x00: INR C at 0x0001 was never executed → RST 7 jumped to 0x0038
    }

    // SBB_R: subtract register with borrow (carry)  (opcodes 0x98-0x9F)
    // A = A - R - carry; sets S, Z, H, P, C flags

    @Test
    public void code98__SBB_B() {
        // given: A=0x30, B=0x10, carry=0 => A=0x30-0x10-0=0x20, P=1
        givenPr("MVI A,30\n" +
                "MVI B,10\n" +
                "SBB B\n" +
                "NOP\n");

        givenMm("3E 30\n" +
                "06 10\n" +
                "98\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  2012\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 10 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 20 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00010000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00100000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code99__SBB_C() {
        // given: A=0x33, C=0x11, carry=0 => A=0x33-0x11-0=0x22, P=1
        givenPr("MVI A,33\n" +
                "MVI C,11\n" +
                "SBB C\n" +
                "NOP\n");

        givenMm("3E 33\n" +
                "0E 11\n" +
                "99\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0011\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  2216\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 11\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 22 16\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00010001\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00100010\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code9A__SBB_D() {
        // given: A=0x40, D=0x20, carry=0 => A=0x40-0x20-0=0x20, P=1
        givenPr("MVI A,40\n" +
                "MVI D,20\n" +
                "SBB D\n" +
                "NOP\n");

        givenMm("3E 40\n" +
                "16 20\n" +
                "9A\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  2000\n" +
                "HL:  0000\n" +
                "AF:  2012\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 20 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 20 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00100000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00100000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code9B__SBB_E() {
        // given: A=0x55, E=0x22, carry=0 => A=0x55-0x22-0=0x33, P=1
        givenPr("MVI A,55\n" +
                "MVI E,22\n" +
                "SBB E\n" +
                "NOP\n");

        givenMm("3E 55\n" +
                "1E 22\n" +
                "9B\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0022\n" +
                "HL:  0000\n" +
                "AF:  3316\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 22\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 33 16\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00100010\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00110011\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code9C__SBB_H() {
        // given: A=0x50, H=0x10, carry=0 => A=0x50-0x10-0=0x40, P=1
        givenPr("MVI A,50\n" +
                "MVI H,10\n" +
                "SBB H\n" +
                "NOP\n");

        givenMm("3E 50\n" +
                "26 10\n" +
                "9C\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  1000\n" +
                "AF:  4012\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 10 00\n" +
                "M:   00\n" +
                "A,F: 40 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00010000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   01000000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code9D__SBB_L() {
        // given: A=0x45, L=0x12, carry=0 => A=0x45-0x12-0=0x33, P=1
        givenPr("MVI A,45\n" +
                "MVI L,12\n" +
                "SBB L\n" +
                "NOP\n");

        givenMm("3E 45\n" +
                "2E 12\n" +
                "9D\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0012\n" +
                "AF:  3316\n" +
                "SP:  0000\n" +
                "PC:  0006\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 12\n" +
                "M:   00\n" +
                "A,F: 33 16\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00010010\n" +
                "M:   00000000\n" +
                "A:   00110011\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code9E__SBB_M() {
        // given: HL=0x0001, M=0x01 (2nd byte of LXI), A=0x11 => A=0x11-0x01-0=0x10, P=1
        givenPr("LXI H,0001\n" +
                "MVI A,11\n" +
                "SBB M\n" +
                "NOP\n");

        givenMm("21 01 00\n" +
                "3E 11\n" +
                "9E\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0001\n" +
                "AF:  1012\n" +
                "SP:  0000\n" +
                "PC:  0007\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 01\n" +
                "M:   01\n" +
                "A,F: 10 12\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000111\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000001\n" +
                "M:   00000001\n" +
                "A:   00010000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void code9F__SBB_A() {
        // given: A=0x42, carry=0 => A=0x42-0x42-0=0x00, Z=1, P=1
        givenPr("MVI A,42\n" +
                "SBB A\n" +
                "NOP\n");

        givenMm("3E 42\n" +
                "9F\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  0056\n" +
                "SP:  0000\n" +
                "PC:  0004\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 00 56\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00000100\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010110\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n");
    }

    @Test
    public void code98__SBB_B_with_carry_in() {
        // given: A=0x30, B=0x10, carry=1 => A=0x30-0x10-1=0x1F, P=0
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "ADI 01\n" +    // A = 0xFF + 0x01 = 0x00, carry=1
                "MVI A,30\n" +  // A = 0x30 (MVI doesn't touch carry)
                "MVI B,10\n" +  // B = 0x10
                "SBB B\n" +     // A = 0x30 - 0x10 - 1 = 0x1F
                "NOP\n");

        givenMm("3E FF\n" +
                "C6 01\n" +
                "3E 30\n" +
                "06 10\n" +
                "98\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  1000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1F02\n" +
                "SP:  0000\n" +
                "PC:  000A\n" +
                "B,C: 10 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 1F 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001010\n" +
                "     76543210\n" +
                "B:   00010000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00011111\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    // SBI_XX: A = A - immediate - carry  (opcode 0xDE)

    @Test
    public void codeDE__SBI_XX_no_carry() {
        // given: A=0x30, imm=0x10, carry=0 => A=0x30-0x10-0=0x20
        givenPr("MVI A,30\n" +  // A = 0x30
                "SBI 10\n" +    // A = A - 0x10 - carry(0) = 0x20
                "NOP\n");

        givenMm("3E 30\n" +
                "DE 10\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  2012\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 20 12\n" +
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
                "M:   00111110\n" +
                "A:   00100000\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void codeDE__SBI_XX_borrow() {
        // given: A=0x10, imm=0x20, carry=0 => A=0x10-0x20-0=0xF0, C=1, S=1, P=1
        givenPr("MVI A,10\n" +  // A = 0x10
                "SBI 20\n" +    // A = 0x10 - 0x20 - 0 = 0xF0, borrow -> C=1
                "NOP\n");

        givenMm("3E 10\n" +
                "DE 20\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  F097\n" +
                "SP:  0000\n" +
                "PC:  0005\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: F0 97\n" +
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
                "M:   00111110\n" +
                "A:   11110000\n" +
                "     sz0h0p1c\n" +
                "F:   10010111\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }

    @Test
    public void codeDE__SBI_XX_with_carry_in() {
        // given: carry=1, A=0x30, imm=0x10 => A=0x30-0x10-1=0x1F
        givenPr("MVI A,FF\n" +  // A = 0xFF
                "ADI 01\n" +    // A = 0x00, carry=1
                "MVI A,30\n" +  // A = 0x30 (carry still = 1)
                "SBI 10\n" +    // A = 0x30 - 0x10 - 1 = 0x1F
                "NOP\n");

        givenMm("3E FF\n" +
                "C6 01\n" +
                "3E 30\n" +
                "DE 10\n" +
                "00");

        // when
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  0000\n" +
                "HL:  0000\n" +
                "AF:  1F02\n" +
                "SP:  0000\n" +
                "PC:  0009\n" +
                "B,C: 00 00\n" +
                "D,E: 00 00\n" +
                "H,L: 00 00\n" +
                "M:   3E\n" +
                "A,F: 1F 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000000 00000000\n" +
                "PC:  00000000 00001001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00111110\n" +
                "A:   00011111\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }
}
