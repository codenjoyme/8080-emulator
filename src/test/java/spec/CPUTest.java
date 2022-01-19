package spec;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CPUTest {

    public static final int START = 0x0000;

    private TestAccessor accessor;
    private CPU cpu;
    private int stopWhen;

    @Before
    public void setup() {
        accessor = new TestAccessor(() -> {
            if (cpu.pc() >= stopWhen) {
                accessor.stopCpu();
            }
        });
        cpu = new CPU(50.1 * 1e-6, accessor);
        cpu.startAt(START);
    }

    private void memory(String bites) {
        stopWhen = bites.split(" ").length;
        accessor.memory().write(START, bites);
    }

    private void cpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    @Test
    public void testNop() {
        // when
        memory("00 00 00 00 00");
        cpu.execute();

        // then
        cpu("BC:   0x0000\n" +
            "DE:   0x0000\n" +
            "HL:   0x0000\n" +
            "AF:   0x0002\n" +
            "SP:   0x0000\n" +
            "PC:   0x0005\n" +
            "B,C:  0x00 0x00\n" +
            "D,E:  0x00 0x00\n" +
            "H,L:  0x00 0x00\n" +
            "A,F:  0x00 0x02\n" +
            "B:    0b00000000\n" +
            "C:    0b00000000\n" +
            "D:    0b00000000\n" +
            "E:    0b00000000\n" +
            "H:    0b00000000\n" +
            "L:    0b00000000\n" +
            "A:    0b00000000\n" +
            "F:    0b00000010\n" +
            "ts:   false\n" +
            "tz:   false\n" +
            "th:   false\n" +
            "tp:   false\n" +
            "tc:   false\n");
    }
}