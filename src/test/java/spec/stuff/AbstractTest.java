package spec.stuff;

import org.junit.After;
import org.junit.Before;
import spec.*;
import spec.assembler.Assembler;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static spec.Constants.*;
import static spec.assembler.Assembler.asString;
import static spec.math.WordMath.hex8;
import static spec.stuff.SmartAssert.assertEquals;
import static spec.stuff.TrackUpdatedMemory.TRACK_ALL_CHANGES;

public abstract class AbstractTest {

    public static int START = 0x0000;

    protected boolean memoryInit;

    protected Hardware hard;
    protected TrackUpdatedMemory memory;
    protected Cpu cpu;
    protected Assembler asm;
    protected KeyRecord record;
    protected RomLoader roms;
    protected FileRecorder fileRecorder;
    protected CpuDebug debug;
    protected KeyLogger keyLogger;
    protected IOPorts ports;
    protected GraphicControl graphic;
    protected Timings timings;
    protected RomSwitcher romSwitcher;

    @Before
    public void setup() {
        SmartAssert.setup();
    }

    @Before
    public void before() {
        Logger.DEBUG = false;

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, null) {

            @Override
            protected Memory createMemory() {
                return memory = new TrackUpdatedMemory(x10000, TRACK_ALL_CHANGES);
            }

            @Override
            protected Cpu createCpu(int ticksPerInterrupt) {
                // interrupt будет проходить каждый тик
                return cpu = super.createCpu(1);
            }

            @Override
            protected GraphicControl createGraphicControl(Container parent) {
                return AbstractTest.this.createGraphicControl(parent);
            }

            @Override
            protected Timings createTimings() {
                return AbstractTest.this.createTimings(this);
            }

            @Override
            protected void out8(int port, int bite) {
                // делаем все по быстрому
            }

            @Override
            protected void outPort8(int port, int bite) {
                // делаем все по быстрому
            }
        };

        fileRecorder = hard.fileRecorder();
        fileRecorder.stopWriting();

        keyLogger = hard.keyLogger();
        keyLogger.console(false);

        roms = hard.roms();
        record = hard.record();
        asm = hard.cpu().asm();
        ports = hard.ports();
        graphic = hard.graphic();
        timings = hard.timings();
        memory.resetChanges();
        romSwitcher = hard.romSwitcher();

        debug = cpu.debug();
        debug.disable();
        debug.console(false);

        memoryInit = false;

        cpu.PC(START);
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    protected GraphicControl createGraphicControl(Container parent) {
        return mock(GraphicControl.class);
    }

    protected Timings createTimings(Hardware hard) {
        return new Timings(hard) {
            @Override
            public void updateState() {
                // делаем все по быстрому
            }

            @Override
            public void sleep(long millis) {
                // делаем все по быстрому
            }
        };
    }

    public void givenPr(String program) {
        String bites = asString(asm.split(asm.assembly(program)));
        givenMm(bites);
        assertEquals(program, asm.dizAssembly(bites));
    }

    /**
     * Либо метод запишет данные в память без валидации (если еще никто не делал этого),
     * либо будет валидировать уже измененную ранее область памяти.
     */
    public void givenMm(String bites) {
        if (!memoryInit) {
            memoryInit = true;
            memory.write8str(START, bites.replace("\n", " "));
        }
        String split = asString(asm.split(memory.changedBites()));
        if (!split.endsWith("\n")) {
            split += "\n";
        }
        if (!bites.endsWith("\n")) {
            bites += "\n";
        }
        assertEquals(bites, split);
        int ticks = split.split("\n").length;
        record.after(ticks).stopCpu();
    }

    public void asrtCpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    public void cpuShort(String expected) {
        assertEquals(expected, cpu.toString());
    }

    public void asrtMem(int addr, String expected) {
        assertEquals(expected, hex8(cpu.data().read8(addr)));
    }

    public void asrtMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, memory.read8srt(new Range(begin, end)));
    }

    public void assertTrace(String expected) {
        assertEquals(expected, trace());
    }

    public String trace() {
        return String.join("\n", debug.lines());
    }

    public void start() {
        memory.resetChanges();
        hard.start();
    }

    public void asrtMem(String expected) {
        assertEquals(expected, memory.details());
        memory.resetChanges();
    }
}