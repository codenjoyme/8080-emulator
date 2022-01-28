package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.io.File;
import java.net.URL;

import static spec.Constants.START_POINT;
import static spec.KeyCode.*;

public class IntegrationTest extends AbstractTest {

    private static final int K10 = 10_000;
    private static final int TICKS = 10_000_000;

    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String APP_RESOURCES = "src/main/resources/";

    @Rule
    public TestName test = new TestName();
    private URL base;

    private PngVideo video;

    @Before
    public void before() throws Exception {
        super.before();

        video = new PngVideo(hard.video(), hard.memory());
        base = new File(APP_RESOURCES).toURI().toURL();
        record.screenShoot(this::screenShoot);
        removeTestScreenShots();
        reset();
        record.after(TICKS).stopCpu();
    }

    private void reset() {
        record.reset();
        hard.reset();
    }

    private void removeTestScreenShots() {
        File dir = testDir();
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.getName().endsWith(".png")) { // на всякий случай
                file.delete();
            }
        }
        dir.delete();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    private void screenShoot() {
        screenShoot("end");
    }

    private void screenShoot(String name) {
        video.drawToFile(testDir().getAbsolutePath() + "/" + name + ".png");
    }

    private File testDir() {
        File result = new File(TEST_RESOURCES + test.getMethodName());
        if (!result.exists()) {
            result.mkdir();
        }
        return result;
    }

    @Test
    public void testLik_smoke() {
        // given
        Lik.loadRom(base, roms);

        // when
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", it -> it.press(ENTER).after(5 * K10))
                .shoot("assembler", it -> it.enter("AC000").press(ENTER).after(20 * K10))
                .shoot("exit", it -> it.down(ENTER).after(5 * K10).press(END).after(5 * K10).up(ENTER).after(15 * K10))
                .shoot("memory", it -> it.enter("D9000").press(ENTER).after(30 * K10))
                .shoot("exit", it -> it.press(ESC).after(5 * K10))
                .shoot("basic", it -> it.enter("B").press(ENTER).after(10 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_scenario() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        record.after(12 * K10).down(0x23)
                .after(5 * K10).up(0x23).shoot("")
                .after(10 * K10).down(0x0A)
                .after(5 * K10).up(0x0A).shoot("")
                .after(34 * K10).down(0x4A)
                .after(4 * K10).up(0x4A).shoot("")
                .after(4 * K10).down(0x0A)
                .after(10 * K10).up(0x0A).shoot("")
                .after(400 * K10).down(0x27)
                .after(59 * K10).up(0x27).shoot("")
                .after(1 * K10).down(0x26)
                .after(24 * K10).up(0x26).shoot("")
                .after(127 * K10).down(0x27)
                .after(53 * K10).up(0x27).shoot("")
                .after(1 * K10).down(0x26)
                .after(28 * K10).up(0x26).shoot("")
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_klad() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when then
        record.shoot("logo", it -> it.after(200 * K10))
                .shoot("logo", it -> it.after(170 * K10))
                .shoot("speed", it -> it.after(50 * K10))
                .stopCpu();

        cpu.PC(0x0000);
        start();

        int LEVELS = 32;
        for (int level = 1; level < LEVELS; level++) {
            // when then
            reset();

            // скорость выбранная в первый раз поменяла тайминги
            int speed = (level == 1) ? 60 * K10 : 60 * K10 + 13 * K10;
            record.shoot(null,
                            it -> it.after(50 * K10))
                    .shoot(null,
                            it -> it.down(RIGHT).after(speed).up(RIGHT).after(1 * K10))
                    .shoot("level[" + level + "]",
                            it -> it.down(UP).after(30 * K10).up(UP).after(100 * K10))
                    .stopCpu();

            // этот хак позволяет запускать игру со следующим уровенем
            cpu.PC(0x4567);
            start();
        }
    }

    @Test
    public void testLik_klad_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        assertRecord("klad.rec");

        // then
        screenShoot();
    }

    @Test
    public void testLik_reversi_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi");

        // when
        assertRecord("reversi.rec");

        // then
        screenShoot();
    }

    @Test
    public void testLik_reversi2_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi2");

        // when
        assertRecord("reversi2.rec");

        // then
        screenShoot();
    }

    private void assertRecord(String path) {
        fileRecorder.startWriting();
        int lastTick = hard.loadRecord(TEST_RESOURCES + "recordings/" + path);
        record.after(lastTick).stopCpu();
        cpu.PC(0xC000);
        hard.start();
    }

    @Test
    public void testLik_keyboard() {
        // given
        Lik.loadRom(base, roms);

        // when
        assertRecord("keyboard.rec");

        // then
        screenShoot();
    }

    @Test
    public void testSpecialist_monitor() {
        // given
        Specialist.loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        start();

        // then
        asrtCpu("BC:  0E80\n" +
                "DE:  8F60\n" +
                "HL:  C196\n" +
                "AF:  FF02\n" +
                "SP:  8F30\n" +
                "PC:  C191\n" +
                "B,C: 0E 80\n" +
                "D,E: 8F 60\n" +
                "H,L: C1 96\n" +
                "M:   C5\n" +
                "A,F: FF 02\n" +
                "     76543210 76543210\n" +
                "SP:  10001111 00110000\n" +
                "PC:  11000001 10010001\n" +
                "     76543210\n" +
                "B:   00001110\n" +
                "C:   10000000\n" +
                "D:   10001111\n" +
                "E:   01100000\n" +
                "H:   11000001\n" +
                "L:   10010110\n" +
                "M:   11000101\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testSpecialist_blobcop() {
        // given
        Specialist.loadRom(base, roms);
        Specialist.loadGame(base, roms, "blobcop");

        // when
        cpu.PC(0x0000);
        start();

        // then
        asrtCpu("BC:  0000\n" +
                "DE:  9D21\n" +
                "HL:  C4C9\n" +
                "AF:  8987\n" +
                "SP:  8F99\n" +
                "PC:  0FA9\n" +
                "B,C: 00 00\n" +
                "D,E: 9D 21\n" +
                "H,L: C4 C9\n" +
                "M:   76\n" +
                "A,F: 89 87\n" +
                "     76543210 76543210\n" +
                "SP:  10001111 10011001\n" +
                "PC:  00001111 10101001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   10011101\n" +
                "E:   00100001\n" +
                "H:   11000100\n" +
                "L:   11001001\n" +
                "M:   01110110\n" +
                "A:   10001001\n" +
                "     sz0h0p1c\n" +
                "F:   10000111\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  true\n");

        screenShoot();
    }

    @Test
    public void testSpecialist_babnik() {
        // given
        Specialist.loadRom(base, roms);
        Specialist.loadGame(base, roms, "babnik");

        // when
        cpu.PC(0x0000);
        start();

        // then
        asrtCpu("BC:  125C\n" +
                "DE:  0000\n" +
                "HL:  201F\n" +
                "AF:  0052\n" +
                "SP:  3FFB\n" +
                "PC:  04FE\n" +
                "B,C: 12 5C\n" +
                "D,E: 00 00\n" +
                "H,L: 20 1F\n" +
                "M:   20\n" +
                "A,F: 00 52\n" +
                "     76543210 76543210\n" +
                "SP:  00111111 11111011\n" +
                "PC:  00000100 11111110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   01011100\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00100000\n" +
                "L:   00011111\n" +
                "M:   00100000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010010\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testDiagnostic_case1() {
        // 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates
        // https://github.com/begoon/i8080-core/blob/master/TEST.ASM

        // given
        Lik.loadRom(base, roms);
        int start = roms.loadRKS(base, "test/test.rks");

        // when
        cpu.PC(start);
        start();

        // then
        asrtCpu("BC:  9000\n" +
                "DE:  FF01\n" +
                "HL:  C49C\n" +
                "AF:  FF42\n" +
                "SP:  7FF7\n" +
                "PC:  C38A\n" +
                "B,C: 90 00\n" +
                "D,E: FF 01\n" +
                "H,L: C4 9C\n" +
                "M:   00\n" +
                "A,F: FF 42\n" +
                "     76543210 76543210\n" +
                "SP:  01111111 11110111\n" +
                "PC:  11000011 10001010\n" +
                "     76543210\n" +
                "B:   10010000\n" +
                "C:   00000000\n" +
                "D:   11111111\n" +
                "E:   00000001\n" +
                "H:   11000100\n" +
                "L:   10011100\n" +
                "M:   00000000\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   01000010\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testLik_helloWorld() {
        // given
        Lik.loadRom(base, roms);
        int start = roms.loadRKS(base, "test/hello_world.rks");
        record.reset().after(300).stopCpu();
        Constants.CPU_TRACE = true;

        // when
        cpu.PC(start);
        start();

        // then
        assertCpuDebug(
                "0004  21 0D 00   LXI H,000D\n" +
                "0007  CD 1B 00   CALL 001B\n" +
                "001B  7E         MOV A,M\n" +
                "001C  FE 24      CPI 24\n" +
                "001E  C8         RZ\n" +
                "001F  CD 26 00   CALL 0026\n" +
                "0026  F5         PUSH SP\n" +
                "0027  D5         PUSH D\n" +
                "0028  E5         PUSH H\n" +
                "0029  4E         MOV C,M\n" +
                "002A  CD 37 C0   CALL C037\n" +
                "C037  E5         PUSH H\n" +
                "C038  D5         PUSH D\n" +
                "C039  C5         PUSH B\n" +
                "C03A  F5         PUSH SP\n" +
                "C03B  79         MOV A,C\n" +
                "C03C  FE 21      CPI 21\n" +
                "C03E  DA D4 C0   JC C0D4\n" +
                "C041  2A FC 8F   LHLD 8FFC\n" +
                "C044  7C         MOV A,H\n" +
                "C045  FE BE      CPI BE\n" +
                "C047  D2 B2 C0   JNC C0B2\n" +
                "C04A  C6 03      ADI 03\n" +
                "C04C  32 FD 8F   STA 8FFD\n" +
                "C04F  EB         XCHG\n" +
                "C050  79         MOV A,C\n" +
                "C051  32 E9 8F   STA 8FE9\n" +
                "C054  D6 20      SUI 20\n" +
                "C056  2A E7 8F   LHLD 8FE7\n" +
                "C059  85         ADD L\n" +
                "C05A  6F         MOV L,A\n" +
                "C05B  29         DAD H\n" +
                "C05C  29         DAD H\n" +
                "C05D  29         DAD H\n" +
                "C05E  EB         XCHG\n" +
                "C05F  00         NOP\n" +
                "C060  7C         MOV A,H\n" +
                "C061  E6 03      ANI 03\n" +
                "C063  4F         MOV C,A\n" +
                "C064  3E 05      MVI A,05\n" +
                "C066  91         SUB C\n" +
                "C067  4F         MOV C,A\n" +
                "C068  7C         MOV A,H\n" +
                "C069  E6 FC      ANI FC\n" +
                "C06B  0F         RLC\n" +
                "C06C  0F         RLC\n" +
                "C06D  C6 90      ADI 90\n" +
                "C06F  67         MOV H,A\n" +
                "C070  22 F8 8F   SHLD 8FF8\n" +
                "C073  06 08      MVI B,08\n" +
                "C075  00         NOP\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C076  1A         LDAX D\n" +
                "C077  6F         MOV L,A\n" +
                "C078  26 00      MVI H,00\n" +
                "C07A  79         MOV A,C\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C07B  29         DAD H\n" +
                "C07C  29         DAD H\n" +
                "C07D  3D         DCR A\n" +
                "C07E  C2 7B C0   JNZ C07B\n" +
                "C081  E5         PUSH H\n" +
                "C082  13         INX D\n" +
                "C083  05         DCR B\n" +
                "C084  C2 76 C0   JNZ C076\n" +
                "C087  06 08      MVI B,08\n" +
                "C089  2A F8 8F   LHLD 8FF8\n" +
                "C08C  D1         POP D\n" +
                "C08D  7A         MOV A,D\n" +
                "C08E  CD 63 C1   CALL C163\n" +
                "C163  4F         MOV C,A\n" +
                "C164  3A E9 8F   LDA 8FE9\n" +
                "C167  FE 7F      CPI 7F\n" +
                "C169  79         MOV A,C\n" +
                "C16A  CA A3 C0   JZ C0A3\n" +
                "C16D  AE         XRA M\n" +
                "C16E  C9         RET\n" +
                "FF91  FF         RST 7\n" +
                "0038  00         NOP\n" +
                "0039  00         NOP\n" +
                "003A  00         NOP\n" +
                "003B  00         NOP\n" +
                "003C  00         NOP\n" +
                "003D  00         NOP\n" +
                "003E  00         NOP\n" +
                "003F  00         NOP\n" +
                "0040  00         NOP\n" +
                "0041  00         NOP\n" +
                "0042  00         NOP\n" +
                "0043  00         NOP");

        asrtCpu("BC:  08FF\n" +
                "DE:  FF00\n" +
                "HL:  9000\n" +
                "AF:  FF86\n" +
                "SP:  FFDC\n" +
                "PC:  0044\n" +
                "B,C: 08 FF\n" +
                "D,E: FF 00\n" +
                "H,L: 90 00\n" +
                "M:   00\n" +
                "A,F: FF 86\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11011100\n" +
                "PC:  00000000 01000100\n" +
                "     76543210\n" +
                "B:   00001000\n" +
                "C:   11111111\n" +
                "D:   11111111\n" +
                "E:   00000000\n" +
                "H:   10010000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   10000110\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testDiagnostic_case2() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The basic excerciser
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080EX1.MAC

        // given
        Lik.loadRom(base, roms);
        roms.loadROM(base, "test/8080EX1.COM", 0x0100);

        // when
        cpu.PC(0x0100);
        start();

        // then
        asrtCpu("BC:  0009\n" +
                "DE:  0DF6\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFF4\n" +
                "PC:  00F0\n" +
                "B,C: 00 09\n" +
                "D,E: 0D F6\n" +
                "H,L: 00 00\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11110100\n" +
                "PC:  00000000 11110000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00001001\n" +
                "D:   00001101\n" +
                "E:   11110110\n" +
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

        screenShoot();
    }

    @Test
    public void testDiagnostic_case3() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The preliminary test
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080PRE.MAC

        // given
        Lik.loadRom(base, roms);
        roms.loadROM(base, "test/8080PRE.COM", 0x0100);

        // when
        cpu.PC(0x0100);
        start();

        // then
        asrtCpu("BC:  EC34\n" +
                "DE:  0C0A\n" +
                "HL:  0014\n" +
                "AF:  6A82\n" +
                "SP:  0500\n" +
                "PC:  0316\n" +
                "B,C: EC 34\n" +
                "D,E: 0C 0A\n" +
                "H,L: 00 14\n" +
                "M:   00\n" +
                "A,F: 6A 82\n" +
                "     76543210 76543210\n" +
                "SP:  00000101 00000000\n" +
                "PC:  00000011 00010110\n" +
                "     76543210\n" +
                "B:   11101100\n" +
                "C:   00110100\n" +
                "D:   00001100\n" +
                "E:   00001010\n" +
                "H:   00000000\n" +
                "L:   00010100\n" +
                "M:   00000000\n" +
                "A:   01101010\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testDiagnostic_case4() {
        // Diagnostics II, version 1.2, CPU test by Supersoft Associates
        // https://raw.githubusercontent.com/begoon/i8080-core/master/CPUTEST.MAC

        // given
        Lik.loadRom(base, roms);
        roms.loadROM(base, "test/CPUTEST.COM", 0x0100);

        // when
        cpu.PC(0x0100);
        start();

        // then
        asrtCpu("BC:  0002\n" +
                "DE:  0000\n" +
                "HL:  0080\n" +
                "AF:  0D93\n" +
                "SP:  2FEF\n" +
                "PC:  004E\n" +
                "B,C: 00 02\n" +
                "D,E: 00 00\n" +
                "H,L: 00 80\n" +
                "M:   00\n" +
                "A,F: 0D 93\n" +
                "     76543210 76543210\n" +
                "SP:  00101111 11101111\n" +
                "PC:  00000000 01001110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000010\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   10000000\n" +
                "M:   00000000\n" +
                "A:   00001101\n" +
                "     sz0h0p1c\n" +
                "F:   10010011\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  true\n");

        screenShoot();
    }
}
