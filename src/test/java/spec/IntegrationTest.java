package spec;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.math.Bites;
import spec.mods.StopWhen;
import spec.mods.WhenPC;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.stuff.AbstractTest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static spec.Constants.START_POINT;
import static spec.KeyCode.*;
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
    public void testLik_basic() {
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
        // проверяем что при выполнении команды `B` монитора вес
        Range range = new Range(START_BASIC_LIK_V2, FINISH_ROM6);
        Bites original = memory.read8arr(range);
        Bites copied = memory.read8arr(range.shift(- START_BASIC_LIK_V2));
        assertMemoryChanges("", "", range, original, copied);
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
        record.shoot("logo", it -> it.after(200 * K10))
                .shoot("logo", it -> it.after(170 * K10))
                .shoot("speed", it -> it.after(50 * K10))
                .stopCpu();

        cpu.PC(0x0000);
        start();

        int LEVELS = 33;
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

            // этот хак позволяет запускать игру со следующим уровнем
            cpu.PC(0x4567);
            start();
        }
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
        String sourceCode = assertDizAssembly(data, binPath, "launchedProgram.asm");
        String recompiledFile = TEST_RESOURCES + getTestResultFolder() + "/" + "recompiled.mem";
        assertAssembly(sourceCode, recompiledFile, null);
        assertPngMemory(range, "recompiled.png");

        // when then
        lik().loadGame(base, roms, KLAD);
        assertDizAssembly(data, binPath, "newProgram.asm");
        assertPngMemory(range, "original.png");

        // when then
        assertMemory(range, "recompiled.mem", "recompiled.log");
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
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "hello-world/hello-world.mem", Lik.NAME);
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
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "test/test.mem", Lik.NAME);
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
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080pre/8080pre.mem", Lik.NAME);
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
    public void testLik_diagnostic_zexlax8080exerciser() {
        // zexlax.z80 - Z80 instruction set exerciser
        // Copyright (C) 1994  Frank D. Cringle
        // Modified to exercise an 8080 by Ian Bartholomew, February 2009

        // given
        memory.doNotTrackChanges();
        lik().loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080ex1/8080ex1.mem", Lik.NAME);
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
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080apofig/8080apofig.mem", Lik.NAME);
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