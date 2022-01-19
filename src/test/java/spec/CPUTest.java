package spec;

import org.junit.Before;
import org.junit.Test;
import spec.assembler.Assembler;

import static junit.framework.TestCase.assertEquals;

public class CPUTest {

    public static final int START = 0x0000;

    private TestAccessor accessor;
    private CPU cpu;
    private int stopWhen;
    private Assembler asm;

    @Before
    public void setup() {
        accessor = new TestAccessor(() -> {
            if (cpu.PC() >= stopWhen) {
                accessor.stopCpu();
            }
        });
        cpu = new CPU(50.1 * 1e-6, accessor);
        cpu.PC(START);
        asm = new Assembler();
    }

    private void memory(String bites) {
        stopWhen = bites.split(" ").length;
        accessor.memory().write(START, bites);
    }

    private void asrtCpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    private void cpuShort(String expected) {
        assertEquals(expected, cpu.toString());
    }

    @Test
    public void test__NOP__0x00() {
        // when
        givenPr("NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n" +
                "NOP\n");

        asrtMem("00\n" +
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

    private void asrtMem(String bites) {
        assertEquals(bites, asm.split(accessor.updatedMemory()));
    }

    private void givenPr(String program) {
        String bites = asm.parse(program);
        memory(bites);
    }

    @Test
    public void test__LXI_B_XXYY__0x01() {
        // when
        givenPr("LXI B,1234\n" +
                "NOP\n");

        asrtMem("01 34 12\n" +
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
    public void test__LXI_D_XXYY__0x01() {
        // when
        givenPr("LXI D,1234\n" +
                "NOP\n");

        asrtMem("11 34 12\n" +
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
    public void test__LXI_H_XXYY__0x21() {
        // when
        givenPr("LXI H,1234\n" +
                "NOP\n");

        asrtMem("21 34 12\n" +
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
    public void test__LXI_SP_XXYY__0x31() {
        // when
        givenPr("LXI SP,1234\n" +
                "NOP\n");

        asrtMem("31 34 12\n" +
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
    public void test__DAD_B__0x09__caseC0() {
        // when
        givenPr("LXI B,1234\n" +  // operand 1
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,4321\n" +  // operand 2 & result
                "DAD B\n" +       // sum HL=HL+BC, [c=0]
                "NOP\n");

        asrtMem("01 34 12\n" +
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
    public void test__DAD_B__0x09__caseC1() {
        // when
        givenPr("LXI B,789A\n" +  // operand 1
                "LXI D,1111\n" +  // ignored
                "LXI SP,2222\n" + // ignored
                "LXI H,A987\n" +  // operand 2 & result
                "DAD B\n" +       // sum HL=HL+BC, [c=1]
                "NOP\n");

        asrtMem("01 9A 78\n" +
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
    public void test__DAD_D__0x09__caseC0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,1234\n" +  // operand 1
                "LXI SP,2222\n" + // ignored
                "LXI H,4321\n" +  // operand 2 & result
                "DAD D\n" +       // sum HL=HL+DE, [c=0]
                "NOP\n");

        asrtMem("01 11 11\n" +
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
    public void test__DAD_D__0x09__caseC1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,789A\n" +  // operand 1
                "LXI SP,2222\n" + // ignored
                "LXI H,A987\n" +  // operand 2 & result
                "DAD D\n" +       // sum HL=HL+DE, [c=1]
                "NOP\n");

        asrtMem("01 11 11\n" +
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
    public void test__DAD_H__0x09__caseC0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,1234\n" +  // operand 1 & 2 & result
                "DAD H\n" +       // sum HL=HL+HL, [c=0]
                "NOP\n");

        asrtMem("01 11 11\n" +
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
    public void test__DAD_H__0x09__caseC1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,3333\n" + // ignored
                "LXI H,89AB\n" +  // operand 1 & 2 & result
                "DAD H\n" +       // sum HL=HL+HL, [c=1]
                "NOP\n");

        asrtMem("01 11 11\n" +
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
    public void test__DAD_SP__0x09__caseC0() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,1234\n" + // operand 1
                "LXI H,4321\n" +  // operand 2 & result
                "DAD SP\n" +      // sum HL=HL+SP, [c]
                "NOP\n");

        asrtMem("01 11 11\n" +
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
    public void test__DAD_SP__0x09__caseC1() {
        // when
        givenPr("LXI B,1111\n" +  // ignored
                "LXI D,2222\n" +  // ignored
                "LXI SP,789A\n" + // operand 1
                "LXI H,A987\n" +  // operand 2 & result
                "DAD SP\n" +      // sum HL=HL+SP, [1]
                "NOP\n");

        asrtMem("01 11 11\n" +
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
        // about 2.6 sec (vs 1.4)
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
            accessor.clear();
        }
    }
}