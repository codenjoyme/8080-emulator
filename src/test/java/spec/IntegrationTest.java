package spec;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import spec.math.Bites;
import spec.mods.StopWhen;
import spec.mods.WhenPC;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.resources.lik.apps.klad.Klad;
import spec.stuff.AbstractTest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static spec.Constants.BASIC_LIK_V2_PROGRAM_START;
import static spec.Constants.START_POINT;
import static spec.KeyCode.*;
import static spec.math.WordMath.hi;
import static spec.math.WordMath.lo;
import static spec.platforms.Lik.FINISH_ROM6;
import static spec.platforms.Lik.START_BASIC_LIK_V2;
import static spec.stuff.SmartAssert.assertEquals;

public class IntegrationTest extends AbstractTest {

    public static final String KLAD = "klad";

    @Override
    @Before
    public void before() {
        super.before();

        reset();
        record.after(TICKS).stopCpu();
    }

    @After
    @Override
    public void after() throws Exception {
        assertScreen();

        super.after();
    }

    @Test
    public void testLik_smoke() {
        // given
        lik().loadRom(base, roms);

        // when
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("assembler", it -> it.enter("AC000").press(ENTER).after(20 * K10))
                .shoot("assembler-exit", it -> it.down(ENTER).after(5 * K10).press(END).after(5 * K10).up(ENTER).after(15 * K10))
                .shoot("memory", it -> it.enter("D9000").press(ENTER).after(30 * K10))
                .shoot("memory-exit", it -> it.press(ESC).after(5 * K10))
                .shoot("basic", it -> it.enter("B").press(ENTER).after(20 * K10))
                .shoot("basic-line1", it -> it.enter("10 CLS2").press(ENTER).after(10 * K10))
                .shoot("basic-line2", it -> it.enter("20 CLS1").press(ENTER).after(10 * K10))
                .shoot("basic-line3", it -> it.enter("30 GOTO10").press(ENTER).after(10 * K10))
                .shoot("basic-list", it -> it.enter("LIST").press(ENTER).after(10 * K10))
                .shoot("basic-run", it -> it.enter("RUN").press(ENTER).after(1 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();

        // then
        assertFromPng(pngPath("runcom"));
        assertFromPng(pngPath("stop"));
        assertFromPng(pngPath("monitor"));
        assertFromPng(pngPath("assembler"));
        assertFromPng(pngPath("assembler-exit"));
        assertFromPng(pngPath("memory"));
        assertFromPng(pngPath("memory-exit"));
        assertFromPng(pngPath("basic"));
        assertFromPng(pngPath("basic-line1"));
        assertFromPng(pngPath("basic-line2"));
        assertFromPng(pngPath("basic-line3"));
        assertFromPng(pngPath("basic-list"));
        assertFromPng(pngPath("basic-run"));
    }

    private static Function<KeyRecord.Action, KeyRecord.Action> pressEnterAndWait() {
        return pressEnterAndWait(5 * K10);
    }

    private static Function<KeyRecord.Action, KeyRecord.Action> pressEnterAndWait(int ticks) {
        return it -> it.press(ENTER).after(ticks);
    }


    @Test
    public void testLik_basic_rom() {
        // given
        lik().loadRom(base, roms);

        // when
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("basic", it -> it.enter("B").press(ENTER).after(20 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();

        // then
        // проверяем что при выполнении команды `B` монитора все копируется из ROM в RAM as is
        assertCopiedTo0000("",
                new Range(START_BASIC_LIK_V2, FINISH_ROM6));
    }

    private void assertCopiedTo0000(String changes, Range range) {
        Bites original = memory.read8arr(range);
        Bites copied = memory.read8arr(range.shift(- START_BASIC_LIK_V2));
        assertMemoryChanges("", changes, range, original, copied);
    }

    @Test
    public void testLik_basic_ram() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "basic");

        // then
        // проверяем что эта программа соответсвует basic что находится в ROM
        // за некоторыми исключениями
        assertCopiedTo0000(
                "       00     01     02     03     04     05     06     07     08     09     0A     0B     0C     0D     0E     0F     \n" +
                "D400:  -      -      -      -      -      -      -      -      -      -      -      -      -      -      -      95>9A \n" +
                "D410:  B6>00  28>00  31>00  29>44  -      -      -      -      -      -      -      -      -      -      -      -     \n" +
                "D460:  -      -      -      -      -      -      -      -      -      23>D4  02>01  -      -      -      -      -     \n" +
                "D470:  -      -      -      -      -      -      -      D4>60  01>1E  -      -      -      -      -      -      -     \n" +
                "D480:  -      -      -      -      -      -      -      -      -      -      -      -      -      1F>00  -      -     \n",
                new Range(START_BASIC_LIK_V2, FINISH_ROM6));

        // when
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("basic", it -> it.enter("J").press(ENTER).after(20 * K10))
                .shoot("line-10", it -> it.enter("10 CLS 1").press(ENTER).after(20 * K10))
                .shoot("line-20", it -> it.enter("20 CLS 2").press(ENTER).after(20 * K10))
                .shoot("line-30", it -> it.enter("30 GOTO 10").press(ENTER).after(20 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(20 * K10))
                .shoot("run", it -> it.enter("RUN").press(ENTER).after(20 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();

        // then
        assertEquals(
                "      00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F \n" +
                "1E60: 00 69 1E 0A 00 80 20 31 00 71 1E 14 00 80 20 32 \n" +
                "1E70: 00 7A 1E 1E 00 88 20 31 30 00 00 00 00 00",
                memory.all().toString(BASIC_LIK_V2_PROGRAM_START, BASIC_LIK_V2_PROGRAM_START + 30, true, true));
    }

    // TODO #34 Перенести тест в отдельный класс для тестирования `BasicCompiler` и там генерировать
    //          исходный код программы на бейсике для всех `bss` файлов.
    @Test
    public void testLik_basic_program_president() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "basic");
        Range range = lik().loadBasic2(base, roms, "president");

        // then
        assertMemory(range, "memory.log");
        assertBasicProgram(range, lik().basic("basic", "president", ".bas"));

        // when then
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("basic", it -> it.enter("J").press(ENTER).after(20 * K10))
                // TODO #31 Разобраться, почему не работает программа на бейсике, может бейсик не тот?
                //.shoot("run", it -> it.enter("RUN").press(ENTER).after(10 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(66 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_basic_program_sokoban() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "basic");
        Range range = lik().loadBasic2(base, roms, "sokoban");

        // then
        assertMemory(range, "memory.log");
        assertBasicProgram(range, lik().basic("basic", "sokoban", ".bas"));

        // when then
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("basic", it -> it.enter("J").press(ENTER).after(20 * K10))
                //.shoot("run", it -> it.enter("RUN").press(ENTER).after(10 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(66 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_basic_program_dialog() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "basic2");
        Range range = lik().loadBasic1(base, roms, "dialog");

        // then
        assertMemory(range, "memory.log");
        assertBasicProgram(range, lik().basic("basic2", "dialog", ".bas"));

        // when then
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("basic", it -> it.enter("J").press(ENTER).after(20 * K10))
                //.shoot("run", it -> it.enter("RUN").press(ENTER).after(10 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(66 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_basic_symbols() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "basic");
        int group = 0;
        Range range = memory.all().set(BASIC_LIK_V2_PROGRAM_START,
                Bites.of(
                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        /*0x00*/ 0x20, 0x2D,
                        /*0x01*/ 0x20, 0x2D,
                        /*0x02*/ 0x20, 0x2D,
                        /*0x03*/ 0x20, 0x2D,
                        /*0x04*/ 0x20, 0x2D,
                        /*0x05*/ 0x20, 0x2D,
                        /*0x06*/ 0x20, 0x2D,
                        /*0x07*/ 0x20, 0x2D,
                        /*0x08*/ 0x20, 0x2D,
                        /*0x09*/ 0x20, 0x2D,
                        /*0x0A*/ 0x20, 0x2D,
                        /*0x0B*/ 0x20, 0x2D,
                        /*0x0C*/ 0x20, 0x2D,
                        /*0x0D*/ 0x20, 0x2D,
                        /*0x0E*/ 0x20, 0x2D,
                        /*0x0F*/ 0x20, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        /*0x10*/ 0x20, 0x2D,
                        /*0x11*/ 0x20, 0x2D,
                        /*0x12*/ 0x20, 0x2D,
                        /*0x13*/ 0x20, 0x2D,
                        /*0x14*/ 0x20, 0x2D,
                        /*0x15*/ 0x20, 0x2D,
                        /*0x16*/ 0x20, 0x2D,
                        /*0x17*/ 0x20, 0x2D,
                        /*0x18*/ 0x20, 0x2D,
                        /*0x19*/ 0x20, 0x2D,
                        /*0x1A*/ 0x20, 0x2D,
                        /*0x1B*/ 0x20, 0x2D,
                        /*0x1C*/ 0x20, 0x2D,
                        /*0x1D*/ 0x20, 0x2D,
                        /*0x1E*/ 0x20, 0x2D,
                        /*0x1F*/ 0x20, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x20, 0x2D,
                        0x21, 0x2D,
                        0x22, 0x2D,
                        0x23, 0x2D,
                        0x24, 0x2D,
                        0x25, 0x2D,
                        0x26, 0x2D,
                        0x27, 0x2D,
                        0x28, 0x2D,
                        0x29, 0x2D,
                        0x2A, 0x2D,
                        0x2B, 0x2D,
                        0x2C, 0x2D,
                        0x2D, 0x2D,
                        0x2E, 0x2D,
                        0x2F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x30, 0x2D,
                        0x31, 0x2D,
                        0x32, 0x2D,
                        0x33, 0x2D,
                        0x34, 0x2D,
                        0x35, 0x2D,
                        0x36, 0x2D,
                        0x37, 0x2D,
                        0x38, 0x2D,
                        0x39, 0x2D,
                        0x3A, 0x2D,
                        0x3B, 0x2D,
                        0x3C, 0x2D,
                        0x3D, 0x2D,
                        0x3E, 0x2D,
                        0x3F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x40, 0x2D,
                        0x41, 0x2D,
                        0x42, 0x2D,
                        0x43, 0x2D,
                        0x44, 0x2D,
                        0x45, 0x2D,
                        0x46, 0x2D,
                        0x47, 0x2D,
                        0x48, 0x2D,
                        0x49, 0x2D,
                        0x4A, 0x2D,
                        0x43, 0x2D,
                        0x4C, 0x2D,
                        0x4D, 0x2D,
                        0x4E, 0x2D,
                        0x4F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x50, 0x2D,
                        0x51, 0x2D,
                        0x52, 0x2D,
                        0x53, 0x2D,
                        0x54, 0x2D,
                        0x55, 0x2D,
                        0x56, 0x2D,
                        0x57, 0x2D,
                        0x58, 0x2D,
                        0x59, 0x2D,
                        0x5A, 0x2D,
                        0x5B, 0x2D,
                        0x5C, 0x2D,
                        0x5D, 0x2D,
                        0x5E, 0x2D,
                        0x5F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x60, 0x2D,
                        0x61, 0x2D,
                        0x62, 0x2D,
                        0x63, 0x2D,
                        0x64, 0x2D,
                        0x65, 0x2D,
                        0x66, 0x2D,
                        0x67, 0x2D,
                        0x68, 0x2D,
                        0x69, 0x2D,
                        0x6A, 0x2D,
                        0x6B, 0x2D,
                        0x6C, 0x2D,
                        0x6D, 0x2D,
                        0x6E, 0x2D,
                        0x6F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x70, 0x2D,
                        0x71, 0x2D,
                        0x72, 0x2D,
                        0x73, 0x2D,
                        0x74, 0x2D,
                        0x75, 0x2D,
                        0x76, 0x2D,
                        0x77, 0x2D,
                        0x78, 0x2D,
                        0x79, 0x2D,
                        0x7A, 0x2D,
                        0x7B, 0x2D,
                        0x7C, 0x2D,
                        0x7D, 0x2D,
                        0x7E, 0x2D,
                        0x7F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x80, 0x2D,
                        0x81, 0x2D,
                        0x82, 0x2D,
                        0x83, 0x2D,
                        0x84, 0x2D,
                        0x85, 0x2D,
                        0x86, 0x2D,
                        0x87, 0x2D,
                        0x88, 0x2D,
                        0x89, 0x2D,
                        0x8A, 0x2D,
                        0x8B, 0x2D,
                        0x8C, 0x2D,
                        0x8D, 0x2D,
                        0x8E, 0x2D,
                        0x8F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0x90, 0x2D,
                        0x91, 0x2D,
                        0x92, 0x2D,
                        0x93, 0x2D,
                        0x94, 0x2D,
                        0x95, 0x2D,
                        0x96, 0x2D,
                        0x97, 0x2D,
                        0x98, 0x2D,
                        0x99, 0x2D,
                        0x9A, 0x2D,
                        0x9B, 0x2D,
                        0x9C, 0x2D,
                        0x9D, 0x2D,
                        0x9E, 0x2D,
                        0x9F, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0xA0, 0x2D,
                        0xA1, 0x2D,
                        0xA2, 0x2D,
                        0xA3, 0x2D,
                        0xA4, 0x2D,
                        0xA5, 0x2D,
                        0xA6, 0x2D,
                        0xA7, 0x2D,
                        0xA8, 0x2D,
                        0xA9, 0x2D,
                        0xAA, 0x2D,
                        0xAB, 0x2D,
                        0xAC, 0x2D,
                        0xAD, 0x2D,
                        0xAE, 0x2D,
                        0xAF, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0xB0, 0x2D,
                        0xB1, 0x2D,
                        0xB2, 0x2D,
                        0xB3, 0x2D,
                        0xB4, 0x2D,
                        0xB5, 0x2D,
                        0xB6, 0x2D,
                        0xB7, 0x2D,
                        0xB8, 0x2D,
                        0xB9, 0x2D,
                        0xBA, 0x2D,
                        0xBB, 0x2D,
                        0xBC, 0x2D,
                        0xBD, 0x2D,
                        0xBE, 0x2D,
                        0xBF, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        0xC0, 0x2D,
                        0xC1, 0x2D,
                        0xC2, 0x2D,
                        0xC3, 0x2D,
                        0xC4, 0x2D,
                        0xC5, 0x2D,
                        /*0xD6*/ 0x20, 0x2D,  // больше ничего разумного нет там
                        /*0xD7*/ 0x20, 0x2D,
                        /*0xD8*/ 0x20, 0x2D,
                        /*0xD9*/ 0x20, 0x2D,
                        /*0xDA*/ 0x20, 0x2D,
                        /*0xDB*/ 0x20, 0x2D,
                        /*0xDC*/ 0x20, 0x2D,
                        /*0xDD*/ 0x20, 0x2D,
                        /*0xDE*/ 0x20, 0x2D,
                        /*0xDF*/ 0x20, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        /*0xD0*/ 0x20, 0x2D,
                        /*0xD1*/ 0x20, 0x2D,
                        /*0xD2*/ 0x20, 0x2D,
                        /*0xD3*/ 0x20, 0x2D,
                        /*0xD4*/ 0x20, 0x2D,
                        /*0xD5*/ 0x20, 0x2D,
                        /*0xD6*/ 0x20, 0x2D,
                        /*0xD7*/ 0x20, 0x2D,
                        /*0xD8*/ 0x20, 0x2D,
                        /*0xD9*/ 0x20, 0x2D,
                        /*0xDA*/ 0x20, 0x2D,
                        /*0xDB*/ 0x20, 0x2D,
                        /*0xDC*/ 0x20, 0x2D,
                        /*0xDD*/ 0x20, 0x2D,
                        /*0xDE*/ 0x20, 0x2D,
                        /*0xDF*/ 0x20, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        /*0xE0*/ 0x20, 0x2D,
                        /*0xE1*/ 0x20, 0x2D,
                        /*0xE2*/ 0x20, 0x2D,
                        /*0xE3*/ 0x20, 0x2D,
                        /*0xE4*/ 0x20, 0x2D,
                        /*0xE5*/ 0x20, 0x2D,
                        /*0xE6*/ 0x20, 0x2D,
                        /*0xE7*/ 0x20, 0x2D,
                        /*0xE8*/ 0x20, 0x2D,
                        /*0xE9*/ 0x20, 0x2D,
                        /*0xEA*/ 0x20, 0x2D,
                        /*0xEB*/ 0x20, 0x2D,
                        /*0xEC*/ 0x20, 0x2D,
                        /*0xED*/ 0x20, 0x2D,
                        /*0xEE*/ 0x20, 0x2D,
                        /*0xEF*/ 0x20, 0x2D,

                        0x00,
                        lo(BASIC_LIK_V2_PROGRAM_START + (++group)*(5 + 16*2) + 1),
                        hi(BASIC_LIK_V2_PROGRAM_START + (group)*(5 + 16*2) + 1),
                        lo(group),
                        hi(group),
                        /*0xF0*/ 0x20, 0x2D,
                        /*0xF1*/ 0x20, 0x2D,
                        /*0xF2*/ 0x20, 0x2D,
                        /*0xF3*/ 0x20, 0x2D,
                        /*0xF4*/ 0x20, 0x2D,
                        /*0xF5*/ 0x20, 0x2D,
                        /*0xF6*/ 0x20, 0x2D,
                        /*0xF7*/ 0x20, 0x2D,
                        /*0xF8*/ 0x20, 0x2D,
                        /*0xF9*/ 0x20, 0x2D,
                        /*0xFA*/ 0x20, 0x2D,
                        /*0xFB*/ 0x20, 0x2D,
                        /*0xFC*/ 0x20, 0x2D,
                        /*0xFD*/ 0x20, 0x2D,
                        /*0xFE*/ 0x20, 0x2D,
                        /*0xFF*/ 0x20, 0x2D

                        ));

        // then
        String program = assertBasicProgram(range, testBase() + "/program");

        // when then
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("basic", it -> it.enter("J").press(ENTER).after(20 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(66 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();

        // then
        String screen = assertFromPng(pngPath("list"));
        assertEquals("* BASIC ЛИК V2 *\n" +
                "OK\n" +
                "LIST\n" +
                "\n" +
                program + "\n" +
                "OK\n" +
                "█",
                screen.replaceAll("GOSUB\n-RETURN", "GOSUB-RETURN")
                        .replaceAll("N\nEW", "NEW")
                        .replaceAll("LEN-\nSTR", "LEN-STR")
                        .replaceAll("7 Ю-A-Б-Ц-Д-E-Ф-Г-X-И-Й-K-Л-M-H-O-", "7 Ю-А-Б-Ц-Д-Е-Ф-Г-Х-И-Й-К-Л-М-Н-О-")
                        .replaceAll("8 П-Я-P-C-T-У-Ж-B-Ь-Ы-З-Ш-Э-Щ-Ч- -", "8 П-Я-Р-С-Т-У-Ж-В-Ь-Ы-З-Ш-Э-Щ-Ч- -")
        );
    }

    @Test
    public void testSpecialist_basic_ram() {
        // given
        specialist().loadRom(base, roms);
        specialist().loadGame(base, roms, "basic");

        // when
        record.reset()
                .shoot("specialist", it -> it.after(10 * K10))
                .shoot("basic", it -> it.enter("G").press(ENTER).after(20 * K10))
                .shoot("line-10", it -> it.enter("10 CLS 1").press(ENTER).after(20 * K10))
                .shoot("line-20", it -> it.enter("20 CLS 2").press(ENTER).after(20 * K10))
                .shoot("line-30", it -> it.enter("30 GOTO 10").press(ENTER).after(20 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(20 * K10))
                .shoot("run", it -> it.enter("RUN").press(ENTER).after(20 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();

        // then
        assertEquals("      00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F \n" +
                    "1E60: 00 69 1E 0A 00 80 20 31 00 71 1E 14 00 80 20 32 \n" +
                    "1E70: 00 7A 1E 1E 00 88 20 31 30 00 00 00 00 00 00 00 \n" +
                    "1E80: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00",
                memory.all().toString(0x1E60, 0x1E90, true, true));
    }

    @Test
    public void testLik_scenario() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, KLAD);

        // when
        record.after(12 * K10).down(END)
                .after(5 * K10).up(END).shoot("")

                .after(10 * K10).down(ENTER)
                .after(5 * K10).up(ENTER).shoot("")

                .after(34 * K10).down(J)
                .after(4 * K10).up(J).shoot("")

                .after(4 * K10).down(ENTER)
                .after(10 * K10).up(ENTER).shoot("")

                .after(400 * K10).down(RIGHT)
                .after(59 * K10).up(RIGHT).shoot("")

                .after(1 * K10).down(UP)
                .after(24 * K10).up(UP).shoot("")

                .after(127 * K10).down(RIGHT)
                .after(53 * K10).up(RIGHT).shoot("")

                .after(1 * K10).down(UP)
                .after(28 * K10).up(UP).shoot("")

                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_game_klad_levels() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, KLAD);

        // when then
        assertKladLevels(30);
        newKladLevel(31);
    }

    private void assertKladLevels(int maxLevel) {
        // checking memory

        // when then
        for (int level = 0; level <= maxLevel; level++) {
            Bites bites = memory.read8arr(new Range(Klad.levelBegin(level), - Klad.LEVEL_LENGTH));
            String levelMap = Klad.buildLevel(bites);

            int index = level + 3;
            fileAssert.check("Level [" + level + "]  memory", index + "_level[" + level + "]" + ".log",
                    file -> fileAssert.write(file, levelMap));
        }

        // start emulating
        //        // when then
        record.shoot("logo", it -> it.after(200 * K10))
                .shoot("logo", it -> it.after(170 * K10))
                .shoot("level[0]", it -> it.after(50 * K10))
                .stopCpu();

        cpu.PC(0x0000);
        start();

        for (int level = 1; level <= maxLevel; level++) {
            newKladLevel(level);
        }
    }

    private void newKladLevel(int level) {
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

        // этот хак позволяет запускать игру со следующим уровнем
        cpu.PC(0x4567);
        start();
    }

    @Test
    public void testLik_game_klad_recording() {
        // about 36 sec

        // given
//        Logger.DEBUG = true;
        WhereIsData.PRINT_RW = true;

        lik().loadRom(base, roms);
        Range range = lik().loadGame(base, roms, KLAD);
        String binPath = lik().app(KLAD, ".rks");
        WhereIsData data = new WhereIsData(range);
        cpu.modAdd(data);

        // when then
        assertRecord("klad.rec",
                () -> record.at(100).shoot("screeen1"),
                () -> record.at(200).shoot("screeen2"),
                () -> record.at(300).shoot("screeen3"),
                () -> record.at(400).shoot("screeen4"),
                () -> record.at(500).shoot("screeen5"),
                () -> record.at(600).shoot("screeen6"),
                () -> record.at(700).shoot("screeen7"),
                () -> record.at(800).shoot("screeen8"),
                () -> record.at(900).shoot("screeen9"),
                () -> record.at(1000).shoot("screeen10"),
                () -> record.at(1100).shoot("screeen11"),
                () -> record.at(1200).shoot("screeen12"),
                () -> record.at(1300).shoot("screeen13"),
                () -> record.at(1400).shoot("screeen14"),
                () -> record.at(1500).shoot("screeen15"),
                () -> record.at(1600).shoot("screeen16"),
                () -> record.at(1700).shoot("screeen17"),
                () -> record.at(1800).shoot("screeen18"),
                () -> record.at(1900).shoot("screeen19"),
                () -> record.at(2000).shoot("screeen20"),
                () -> record.at(2100).shoot("screeen21"),
                () -> record.at(2200).shoot("screeen22"),
                () -> record.at(2300).shoot("screeen23"),
                () -> record.at(2310).shoot("screeen24"),
                () -> record.at(2311).shoot("screeen25"),
                () -> record.at(2312).shoot("screeen26"),
                () -> record.at(2313).shoot("screeen27"),
                () -> record.at(2314).shoot("screeen28"),
                () -> record.at(2315).shoot("screeen29"),
                () -> record.at(2316).shoot("screeen30"),
                () -> record.at(2317).shoot("screeen31"), // TODO #8 тут почему-то в режиме теста game over а в реальной игре нет
                () -> record.at(2318).shoot("screeen32"),
                () -> record.at(2319).shoot("screeen33")
        );
        assertCpuAt(data);

        // check that all program was the same after running
        // when then
        String sourceCode = assertDizAssembly(data, MAIN_RESOURCES + binPath, "launchedProgram.asm");
        String recompiledFile = TEST_RESOURCES + getTestResultFolder() + "/" + "recompiled.mem";
        assertAssembly(sourceCode, recompiledFile, null);
        assertPngMemory(range, "recompiled.png");

        // when then
        lik().loadGame(base, roms, KLAD);
        assertDizAssembly(data, MAIN_RESOURCES + binPath, "newProgram.asm");
        assertPngMemory(range, "original.png");

        // when then
        assertMemory(range, "recompiled.mem", "recompiled.log");
    }

    @Test
    public void testLik_game_klad_assembler() throws IOException {
        // given
        lik().loadRom(base, roms);
        lik().loadAsm(base, roms, KLAD);

        // when then
        assertKladLevels(2);
    }

    @Test
    public void testLik_game_reversi_recording() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "reversi");

        // when
        assertRecord("reversi.rec");
    }

    @Test
    public void testLik_game_reversi2_recording() {
        // given
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "reversi2");

        // when
        assertRecord("reversi2.rec");
    }

    @Test
    public void testLik_game_chess_play() {
        // given
        IntegrationTest white = givenChessGameSession("white", true, true);
        IntegrationTest black = givenChessGameSession("black", false, false);

        // when then
        List<String> game = new LinkedList<>();
        String whiteTurn = "E2-E4";
        for (int i = 0; i < 30; i++) {
            String blackTurn = white.nextTurn(whiteTurn);
            whiteTurn = black.nextTurn(blackTurn);

            String turn = String.format("W: %s B: %s",
                    StringUtils.rightPad(whiteTurn, 6),
                    StringUtils.rightPad(blackTurn, 6));
            System.out.println(turn);
            game.add(turn);
        }
        assertEquals("W: E7-E5  B: E2-E4 \n" +
                "W: G8-F6  B: G1-F3 \n" +
                "W: B8-C6  B: B1-C3 \n" +
                "W: F8-B4  B: F1-B5 \n" +
                "W: B4-C3  B: B5-C6 \n" +
                "W: B7-C6  B: B2-C3 \n" +
                "W: O-O    B: O-O   \n" +
                "W: D7-D6  B: D2-D3 \n" +
                "W: C8-G4  B: C1-G5 \n" +
                "W: D8-E7  B: C3-C4 \n" +
                "W: G4-F3  B: D1-D2 \n" +
                "W: D6-D5  B: G2-F3 \n" +
                "W: H7-H6  B: C4-D5 \n" +
                "W: E7-F6  B: G5-F6 \n" +
                "W: C6-D5  B: C2-C4 \n" +
                "W: F6-F4  B: C4-D5 \n" +
                "W: E5-F4  B: D2-F4 \n" +
                "W: F7-F5  B: G1-G2 \n" +
                "W: F8-F5  B: E4-F5 \n" +
                "W: G8-F7  B: G2-H3 \n" +
                "W: F5-H5  B: A1-C1 \n" +
                "W: H5-G5  B: H3-G2 \n" +
                "W: G5-H5  B: G2-H3 \n" +
                "W: H5-G5  B: H3-G2 \n" +
                "W: G5-H5  B: G2-H3 \n" +
                "W: H5-G5  B: H3-G2 \n" +
                "W: G5-H5  B: G2-H3 \n" +
                "W: H5-G5  B: H3-G2 \n" +
                "W: G5-H5  B: G2-H3 \n" +
                "W: H5-G5  B: H3-G2 ",
                StringUtils.join(game, "\n"));
    }

    private IntegrationTest givenChessGameSession(String name, boolean isHard, boolean isWhite) {
        // given
        IntegrationTest result = new IntegrationTest();
        result.test = this.test;
        result.subFolder = name;
        result.before();

        result.runChessGame(isHard, isWhite);

        return result;
    }

    private void runChessGame(boolean isHard, boolean isWhite) {
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, "chess");

        // when
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", pressEnterAndWait())
                .shoot("j", it -> it.enter("J"))
                .shoot("choose-level", pressEnterAndWait())
                .shoot("select-level", it -> it.enter(isHard ? "1" : "0"))
                .shoot("choose-black-white", pressEnterAndWait())
                .shoot("select-black-white", it -> it.enter(isWhite ? "0" : "1"))
                .shoot("choose-move", pressEnterAndWait())
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    public String nextTurn(String turn) {
        record.afterLast().after(K10)
                .shoot("select-move", it -> it.enter(turn))
                .shoot("opponent-answer", pressEnterAndWait(10 * M1))
                .stopCpu();

        start();

        String screen = pngsPath("opponent-answer").getLast();
        String answer = scanner.parse(screen).split("\n")[1].replace(':', '-');
        return answer;
    }

    @Test
    public void testLik_keyboard() {
        // given
        lik().loadRom(base, roms);

        // when
        assertRecord("keyboard.rec");
    }

    // TODO #9 изучить почему этот тест не прошел нормально - риплей или рекорд не работает верно с клавишами
    @Test
    public void testLik_keyboardLine1() {
        // given
        lik().loadRom(base, roms);

        // when
        assertRecord("keyboard-line1.rec");

        // then
        assertScreen("end");
        assertFromPng(testBase() + "/end.png");
    }

    // TODO #10 сделать так же для всех остальных линий клавиатуры подробный `testLik_keyboardLine1` тест

    @Test
    public void testSpecialist_monitor() {
        // given
        specialist().loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        start();

        // then
        assertCpu();
    }

    @Test
    public void testSpecialist_game_blobcop() {
        // given
        specialist().loadRom(base, roms);
        specialist().loadGame(base, roms, "blobcop");

        // when
        cpu.PC(0x0000);
        start();

        // then
        assertCpu();
    }

    @Test
    public void testSpecialist_game_babnik() {
        // given
        specialist().loadRom(base, roms);
        specialist().loadGame(base, roms, "babnik");

        // when
        cpu.PC(0x0000);
        start();

        // then
        assertCpu();
    }

    @Test
    public void testLik_helloWorld() {
        // given
        lik().loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "hello-world/hello-world.mem", Lik.NAME, null);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // последняя команда перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));
        // cpu.modAdd(new StopWhen(0x0022));
        // если хочется подебажить внутри
        // cpu.modAdd(new WhenPC(range, cpu -> {
        //     String log = cpu.debug().log(0);
        //     System.out.println(log);
        // }));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_microcosm() {
        // given
        lik().loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "test/test.mem", Lik.NAME, null);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_exerciserPreliminary() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The preliminary test
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080PRE.MAC

        // given
        lik().loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080pre/8080pre.mem", Lik.NAME, null);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    @Ignore
    public void testLik_diagnostic_zexlax8080exerciser() {
        // zexlax.z80 - Z80 instruction set exerciser
        // Copyright (C) 1994  Frank D. Cringle
        // Modified to exercise an 8080 by Ian Bartholomew, February 2009

        // given
        memory.doNotTrackChanges();
        lik().loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080ex1/8080ex1.mem", Lik.NAME, null);
        // выводим trace только в этом диапазоне
        // debug.enable(range);
        // debug.console(true);
        // Logger.DEBUG = true;
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(4);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));
        // если хочется подебажить внутри
        AtomicInteger counter = new AtomicInteger();
        cpu.modAdd(new WhenPC(range, cpu -> {
            // JMP на начало нового теста
            if (cpu.rPC.get() == 0x002A) {
                String name = "test-" + counter.incrementAndGet();
                assertScreen(name);
                System.out.println("Test '" + name + "' done");
            }
        }));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_apofig8080exerciser() {
        // given
        lik().loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080apofig/8080apofig.mem", Lik.NAME, null);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(6);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));
        // если хочется подебажить внутри
        cpu.modAdd(new WhenPC(range, cpu -> {
            String log = cpu.debug().log(0);
            System.out.println(log);
        }));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }
}