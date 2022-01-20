package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.assembler.Assembler;

import static spec.SmartAssert.assertEquals;
import static spec.WordMath.hex;

public class CPUTest {

    public static final int START = 0x0000;

    private TestData data;
    private CPU cpu;
    private int stopWhen;
    private Assembler asm;
    private boolean init;

    @Before
    public void before() {
        data = new TestData(() -> {
            if (cpu.PC() >= stopWhen) {
                data.stopCpu();
            }
        });
        init = false;
        cpu = new CPU(50.1 * 1e-6, data);
        cpu.PC(START);
        asm = new Assembler();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    private void givenPr(String program) {
        givenMm(asm.parse(program));
    }

    /**
     * Либо метод запишет данные в память без валидации (если еще никто не делал этого),
     * либо будет валидировать уже измененную ранее область памяти.
     */
    private void givenMm(String bites) {
        if (!init) {
            init = true;
            bites = bites.replace("\n", " ");
            stopWhen = bites.split(" ").length;
            data.memory().write(START, bites);
        } else {
            assertEquals(bites, asm.split(data.updatedMemory()));
        }
    }

    private void asrtCpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    private void cpuShort(String expected) {
        assertEquals(expected, cpu.toString());
    }

    private void assertMem(int addr, String expected) {
        assertEquals(expected, hex(cpu.data.peekb(addr)));
    }

    private void assertMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, data.memory().asString(new Range(begin, end)));
    }

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
        cpu.execute();

        // then
        asrtCpu("BC:   0x0000\n" +
                "DE:   0x0000\n" +
                "HL:   0x0000\n" +
                "AF:   0x0002\n" +
                "SP:   0x0000\n" +
                "PC:   0x0005\n" +
                "B,C:  0x00 0x00\n" +
                "D,E:  0x00 0x00\n" +
                "H,L:  0x00 0x00\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00000000 0b00000000\n" +
                "PC:   0b00000000 0b00000101\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000000\n" +
                "D:    0b00000000\n" +
                "E:    0b00000000\n" +
                "H:    0b00000000\n" +
                "L:    0b00000000\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");

        cpuShort("BC: 0x0000\n" +
                "DE: 0x0000\n" +
                "HL: 0x0000\n" +
                "AF: 0x0002\n" +
                "SP: 0x0000\n" +
                "PC: 0x0005\n");
    }

    @Test
    public void codeXX__NONE() {
        // when
        givenMm("08 10 18 20 28 30 38 D9 CB DD ED FD");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x0000\n" +
                "DE:   0x0000\n" +
                "HL:   0x0000\n" +
                "AF:   0x0002\n" +
                "SP:   0x0000\n" +
                "PC:   0x000C\n" +
                "B,C:  0x00 0x00\n" +
                "D,E:  0x00 0x00\n" +
                "H,L:  0x00 0x00\n" +
                "M:    0x08\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00000000 0b00000000\n" +
                "PC:   0b00000000 0b00001100\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000000\n" +
                "D:    0b00000000\n" +
                "E:    0b00000000\n" +
                "H:    0b00000000\n" +
                "L:    0b00000000\n" +
                "M:    0b00001000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code01__LXI_B_XXYY() {
        // when
        givenPr("LXI B,1234\n" +
                "NOP\n");

        givenMm("01 34 12\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1234\n" +
                "DE:   0x0000\n" +
                "HL:   0x0000\n" +
                "AF:   0x0002\n" +
                "SP:   0x0000\n" +
                "PC:   0x0004\n" +
                "B,C:  0x12 0x34\n" +
                "D,E:  0x00 0x00\n" +
                "H,L:  0x00 0x00\n" +
                "M:    0x01\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00000000 0b00000000\n" +
                "PC:   0b00000000 0b00000100\n" +
                "        76543210\n" +
                "B:    0b00010010\n" +
                "C:    0b00110100\n" +
                "D:    0b00000000\n" +
                "E:    0b00000000\n" +
                "H:    0b00000000\n" +
                "L:    0b00000000\n" +
                "M:    0b00000001\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code11__LXI_D_XXYY() {
        // when
        givenPr("LXI D,1234\n" +
                "NOP\n");

        givenMm("11 34 12\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x0000\n" +
                "DE:   0x1234\n" +
                "HL:   0x0000\n" +
                "AF:   0x0002\n" +
                "SP:   0x0000\n" +
                "PC:   0x0004\n" +
                "B,C:  0x00 0x00\n" +
                "D,E:  0x12 0x34\n" +
                "H,L:  0x00 0x00\n" +
                "M:    0x11\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00000000 0b00000000\n" +
                "PC:   0b00000000 0b00000100\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000000\n" +
                "D:    0b00010010\n" +
                "E:    0b00110100\n" +
                "H:    0b00000000\n" +
                "L:    0b00000000\n" +
                "M:    0b00010001\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code21__LXI_H_XXYY() {
        // when
        givenPr("LXI H,1234\n" +
                "NOP\n");

        givenMm("21 34 12\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x0000\n" +
                "DE:   0x0000\n" +
                "HL:   0x1234\n" +
                "AF:   0x0002\n" +
                "SP:   0x0000\n" +
                "PC:   0x0004\n" +
                "B,C:  0x00 0x00\n" +
                "D,E:  0x00 0x00\n" +
                "H,L:  0x12 0x34\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00000000 0b00000000\n" +
                "PC:   0b00000000 0b00000100\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000000\n" +
                "D:    0b00000000\n" +
                "E:    0b00000000\n" +
                "H:    0b00010010\n" +
                "L:    0b00110100\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code31__LXI_SP_XXYY() {
        // when
        givenPr("LXI SP,1234\n" +
                "NOP\n");

        givenMm("31 34 12\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x0000\n" +
                "DE:   0x0000\n" +
                "HL:   0x0000\n" +
                "AF:   0x0002\n" +
                "SP:   0x1234\n" +
                "PC:   0x0004\n" +
                "B,C:  0x00 0x00\n" +
                "D,E:  0x00 0x00\n" +
                "H,L:  0x00 0x00\n" +
                "M:    0x31\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00010010 0b00110100\n" +
                "PC:   0b00000000 0b00000100\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000000\n" +
                "D:    0b00000000\n" +
                "E:    0b00000000\n" +
                "H:    0b00000000\n" +
                "L:    0b00000000\n" +
                "M:    0b00110001\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code09__DAD_B__c0() {
        // when
        givenPr("LXI B,1234\n" +  // operand 1
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,4321\n" +  // operand 2 & result
                "DAD B\n" +       // sum HL=HL+BC, [c=0]
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 21 43\n" +
                "09\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1234\n" +
                "DE:   0x1111\n" +
                "HL:   0x5555\n" +
                "AF:   0x0002\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x12 0x34\n" +
                "D,E:  0x11 0x11\n" +
                "H,L:  0x55 0x55\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010010\n" +
                "C:    0b00110100\n" +
                "D:    0b00010001\n" +
                "E:    0b00010001\n" +
                "H:    0b01010101\n" +
                "L:    0b01010101\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code09__DAD_B__c1() {
        // when
        givenPr("LXI B,789A\n" +  // operand 1
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,A987\n" +  // operand 2 & result
                "DAD B\n" +       // sum HL=HL+BC, [c=1]
                "NOP\n");

        givenMm("01 9A 78\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 87 A9\n" +
                "09\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x789A\n" +
                "DE:   0x1111\n" +
                "HL:   0x2221\n" +
                "AF:   0x0003\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x78 0x9A\n" +
                "D,E:  0x11 0x11\n" +
                "H,L:  0x22 0x21\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x03\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b01111000\n" +
                "C:    0b10011010\n" +
                "D:    0b00010001\n" +
                "E:    0b00010001\n" +
                "H:    0b00100010\n" +
                "L:    0b00100001\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000011\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   true\n");
    }

    @Test
    public void code19__DAD_D__c0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,1234\n" +  // operand 1
                "LXI SP,2222\n" + // ignored
                "LXI H,4321\n" +  // operand 2 & result
                "DAD D\n" +       // sum HL=HL+DE, [c=0]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 34 12\n" +
                "31 22 22\n" +
                "21 21 43\n" +
                "19\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1111\n" +
                "DE:   0x1234\n" +
                "HL:   0x5555\n" +
                "AF:   0x0002\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x12 0x34\n" +
                "H,L:  0x55 0x55\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00010010\n" +
                "E:    0b00110100\n" +
                "H:    0b01010101\n" +
                "L:    0b01010101\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code19__DAD_D__c1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,789A\n" +  // operand 1
                "LXI SP,2222\n" + // ignored
                "LXI H,A987\n" +  // operand 2 & result
                "DAD D\n" +       // sum HL=HL+DE, [c=1]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 9A 78\n" +
                "31 22 22\n" +
                "21 87 A9\n" +
                "19\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1111\n" +
                "DE:   0x789A\n" +
                "HL:   0x2221\n" +
                "AF:   0x0003\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x78 0x9A\n" +
                "H,L:  0x22 0x21\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x03\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b01111000\n" +
                "E:    0b10011010\n" +
                "H:    0b00100010\n" +
                "L:    0b00100001\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000011\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   true\n");
    }

    @Test
    public void code29__DAD_H__c0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1234\n" +  // operand 1 & 2 & result
                "DAD H\n" +       // sum HL=HL+HL, [c=0]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 34 12\n" +
                "29\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1111\n" +
                "DE:   0x2222\n" +
                "HL:   0x2468\n" +
                "AF:   0x0002\n" +
                "SP:   0x3333\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x24 0x68\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00110011 0b00110011\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b00100100\n" +
                "L:    0b01101000\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code29__DAD_H__c1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,89AB\n" +  // operand 1 & 2 & result
                "DAD H\n" +       // sum HL=HL+HL, [c=1]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 AB 89\n" +
                "29\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1111\n" +
                "DE:   0x2222\n" +
                "HL:   0x1356\n" +
                "AF:   0x0003\n" +
                "SP:   0x3333\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x13 0x56\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x03\n" +
                "        76543210   76543210\n" +
                "SP:   0b00110011 0b00110011\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b00010011\n" +
                "L:    0b01010110\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000011\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   true\n");
    }

    @Test
    public void code09__DAD_SP__c0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,1234\n" + // operand 1
                "LXI H,4321\n" +  // operand 2 & result
                "DAD SP\n" +      // sum HL=HL+SP, [c=0]
                //
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 34 12\n" +
                "21 21 43\n" +
                "39\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1111\n" +
                "DE:   0x2222\n" +
                "HL:   0x5555\n" +
                "AF:   0x0002\n" +
                "SP:   0x1234\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x55 0x55\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00010010 0b00110100\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b01010101\n" +
                "L:    0b01010101\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code39__DAD_SP__c1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,789A\n" + // operand 1
                "LXI H,A987\n" +  // operand 2 & result
                "DAD SP\n" +      // sum HL=HL+SP, [c=1]
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 9A 78\n" +
                "21 87 A9\n" +
                "39\n" +
                "00");

        // when
        cpu.execute();

        // then
        asrtCpu("BC:   0x1111\n" +
                "DE:   0x2222\n" +
                "HL:   0x2221\n" +
                "AF:   0x0003\n" +
                "SP:   0x789A\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x22 0x21\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x03\n" +
                "        76543210   76543210\n" +
                "SP:   0b01111000 0b10011010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b00100010\n" +
                "L:    0b00100001\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000011\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   true\n");
    }

    @Test
    public void performance() {
        // about 3.6 sec (vs 1.4)
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

        int ticks = 10_000_000;

        // when then
        for (int tick = 0; tick < ticks; tick++) {
            cpu.PC(0x0000);
            cpu.execute();
            data.clear();
        }
    }

    @Test
    public void code02__STAX_B() {
        // when
        givenPr("LXI B,0003\n" +  // working with memory 0x0003
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0x0003)
                "STAX B\n" +      // copy (BC)=A
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 03 00\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "02\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        cpu.execute();

        // then
        assertMem(0x0003, "24");

        asrtCpu("BC:   0x0003\n" +
                "DE:   0x1111\n" +
                "HL:   0x0003\n" +
                "AF:   0x2402\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x00 0x03\n" +
                "D,E:  0x11 0x11\n" +
                "H,L:  0x00 0x03\n" +
                "M:    0x24\n" +
                "A,F:  0x24 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000011\n" +
                "D:    0b00010001\n" +
                "E:    0b00010001\n" +
                "H:    0b00000000\n" +
                "L:    0b00000011\n" +
                "M:    0b00100100\n" +
                "A:    0b00100100\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code12__STAX_D() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,0003\n" +  // working with memory 0x0003
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0x0003)
                "STAX D\n" +      // copy (DE)=A
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 11 11\n" +
                "11 03 00\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "12\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        cpu.execute();

        // then
        assertMem(0x0003, "24");

        asrtCpu("BC:   0x1111\n" +
                "DE:   0x0003\n" +
                "HL:   0x0003\n" +
                "AF:   0x2402\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x00 0x03\n" +
                "H,L:  0x00 0x03\n" +
                "M:    0x24\n" +
                "A,F:  0x24 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00000000\n" +
                "E:    0b00000011\n" +
                "H:    0b00000000\n" +
                "L:    0b00000011\n" +
                "M:    0b00100100\n" +
                "A:    0b00100100\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code0A__LDAX_B() {
        // when
        givenPr("LXI B,0003\n" +  // working with memory 0x0003
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0x0003)
                "LDAX B\n" +      // copy A=(BC)
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 03 00\n" +
                "11 11 11\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "0A\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        cpu.execute();

        // then
        assertMem(0x0003, "11");

        asrtCpu("BC:   0x0003\n" +
                "DE:   0x1111\n" +
                "HL:   0x0003\n" +
                "AF:   0x1102\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x00 0x03\n" +
                "D,E:  0x11 0x11\n" +
                "H,L:  0x00 0x03\n" +
                "M:    0x11\n" +
                "A,F:  0x11 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00000000\n" +
                "C:    0b00000011\n" +
                "D:    0b00010001\n" +
                "E:    0b00010001\n" +
                "H:    0b00000000\n" +
                "L:    0b00000011\n" +
                "M:    0b00010001\n" +
                "A:    0b00010001\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code1A__LDAX_D() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,0003\n" +  // working with memory 0x0003
                "LXI SP,2222\n" + // ignored
                "LXI H,0003\n" +  // M=(HL)=(0x0003)
                "LDAX D\n" +      // copy A=(DE)
                "NOP\n");

        cpu.A(0x24);

        givenMm("01 11 11\n" +
                "11 03 00\n" +
                "31 22 22\n" +
                "21 03 00\n" +
                "1A\n" +
                "00");

        assertMem(0x0003, "11");

        // when
        cpu.execute();

        // then
        assertMem(0x0003, "11");

        asrtCpu("BC:   0x1111\n" +
                "DE:   0x0003\n" +
                "HL:   0x0003\n" +
                "AF:   0x1102\n" +
                "SP:   0x2222\n" +
                "PC:   0x000E\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x00 0x03\n" +
                "H,L:  0x00 0x03\n" +
                "M:    0x11\n" +
                "A,F:  0x11 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00100010 0b00100010\n" +
                "PC:   0b00000000 0b00001110\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00000000\n" +
                "E:    0b00000011\n" +
                "H:    0b00000000\n" +
                "L:    0b00000011\n" +
                "M:    0b00010001\n" +
                "A:    0b00010001\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code22__SHLD_XXYY() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1234\n" +  // data will be copied to memory
                "SHLD 5678\n" +   // copy (56,79)=H (56,78)=L
                "NOP\n");

        givenMm("01 11 11\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 34 12\n" +
                "22 78 56\n" +
                "00");

        assertMem(0x5678, 2, "00 00");

        // when
        cpu.execute();

        // then
        assertMem(0x5678, 0x5679, "34 12");

        asrtCpu("BC:   0x1111\n" +
                "DE:   0x2222\n" +
                "HL:   0x1234\n" +
                "AF:   0x0002\n" +
                "SP:   0x3333\n" +
                "PC:   0x0010\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x12 0x34\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00110011 0b00110011\n" +
                "PC:   0b00000000 0b00010000\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b00010010\n" +
                "L:    0b00110100\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code2A__LHLD_XXYY() {
        // when
        givenPr("LXI B,1234\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1111\n" +  // data will be overwritten
                "LHLD 0001\n" +   // copy H=(00,02) L=(00,01)
                "NOP\n");

        givenMm("01 34 12\n" +
                "11 22 22\n" +
                "31 33 33\n" +
                "21 11 11\n" +
                "2A 01 00\n" +
                "00");

        assertMem(0x0001, 2, "34 12");

        // when
        cpu.execute();

        // then
        assertMem(0x0001, 2, "34 12");

        asrtCpu("BC:   0x1234\n" +
                "DE:   0x2222\n" +
                "HL:   0x1234\n" +
                "AF:   0x0002\n" +
                "SP:   0x3333\n" +
                "PC:   0x0010\n" +
                "B,C:  0x12 0x34\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x12 0x34\n" +
                "M:    0x00\n" +
                "A,F:  0x00 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00110011 0b00110011\n" +
                "PC:   0b00000000 0b00010000\n" +
                "        76543210\n" +
                "B:    0b00010010\n" +
                "C:    0b00110100\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b00010010\n" +
                "L:    0b00110100\n" +
                "M:    0b00000000\n" +
                "A:    0b00000000\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }

    @Test
    public void code02__STA_XXYY() {
        // when
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

        assertMem(0x1234, "00");

        // when
        cpu.execute();

        // then
        assertMem(0x1234, "24");

        asrtCpu("BC:   0x1111\n" +
                "DE:   0x2222\n" +
                "HL:   0x4444\n" +
                "AF:   0x2402\n" +
                "SP:   0x3333\n" +
                "PC:   0x0010\n" +
                "B,C:  0x11 0x11\n" +
                "D,E:  0x22 0x22\n" +
                "H,L:  0x44 0x44\n" +
                "M:    0x00\n" +
                "A,F:  0x24 0x02\n" +
                "        76543210   76543210\n" +
                "SP:   0b00110011 0b00110011\n" +
                "PC:   0b00000000 0b00010000\n" +
                "        76543210\n" +
                "B:    0b00010001\n" +
                "C:    0b00010001\n" +
                "D:    0b00100010\n" +
                "E:    0b00100010\n" +
                "H:    0b01000100\n" +
                "L:    0b01000100\n" +
                "M:    0b00000000\n" +
                "A:    0b00100100\n" +
                "        sz0h0p1c\n" +
                "F:    0b00000010\n" +
                "ts:   false\n" +
                "tz:   false\n" +
                "th:   false\n" +
                "tp:   false\n" +
                "tc:   false\n");
    }
}