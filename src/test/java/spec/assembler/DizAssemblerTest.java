package spec.assembler;

import org.junit.Ignore;
import org.junit.Test;
import spec.Logger;
import spec.Range;
import spec.math.Bites;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.platforms.Platform;
import spec.stuff.AbstractTest;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static spec.platforms.Platform.RESOURCES;
import static spec.stuff.SmartAssert.fail;

public class DizAssemblerTest extends AbstractTest {

    @Test
    public void testDizAssembly() {
        getAllFiles(".rks")
//                .stream().filter(pair -> pair.getValue().equals("klad"))
                .forEach(pair -> testDizAssembly(pair.getKey(), pair.getValue()));
    }

    private void testDizAssembly(Platform platform, String name) {
        Logger.info("======================================================================");
        String test = String.format("Testing [%s] %s", platform.name(), name);
        Logger.info(test);

        if (List.of("klad2", "pilot", "reversi", "tetris3", "tip-top2", "zoo", "blobcop").contains(name)) {
            Logger.info("Skipping test for %s", name);
            return; // TODO debug all errors
        }

        String asmPath = null;
        try {
            // given
            platform.loadRom(base, roms);
            Range range = platform.loadGame(base, roms, name);
            String binPath = platform.app(name, ".rks");
            asmPath = platform.app(name, ".asm");

            // when then
            assertDizAssembly(binPath, asmPath, range);
        } catch (Exception exception) {
            exception.printStackTrace();
            fail(String.format("For: %s, we got: %s", test, exception.toString()));

            if (asmPath != null) {
                new File(asmPath).delete();
                Logger.info("Deleted '%s' because of error", asmPath);
            }
        }
    }

    @Test
    @Ignore
    public void testDecompileTest_cputest() {
        // given
        lik().loadRom(base, roms);
        String binPath = RESOURCES + "test/cputest/cputest.com";
        Range range = roms.loadROM(base, binPath, 0x0000);
        String asmPath = binPath.replaceAll(".com", ".asm");

        // TODO по какой-то причине он пытается воспринимать блок данных как команды
        // TODO я не уверен так же, что его стоит загружать в 0x0000 но в 0x0100 он не работает точно
        // TODO так же при дизассемблировании переполняется 0xFFFF что странно

        // when then
        assertDizAssembly(binPath, asmPath, range);
    }

    @Test
    public void testDecompileTest_lik_romZagruzchik() {
        // given
        String binPath = lik().platform() + "/roms/01_zagr.bin";
        Range range = roms.loadROM(base, binPath, Lik.START_ZAGRUZCHIK);
        String asmPath = binPath.replaceAll(".bin", ".asm");

        // when then
        assertDizAssembly(binPath, asmPath, range);
    }

    @Test
    public void testDecompileTest_lik_romMonitor() {
        // given
        String binPath1 = lik().platform() + "/roms/02_mon-1m.bin";
        Range range1 = roms.loadROM(base, binPath1, Lik.START_MONITOR_1M);

        String binPath2 = lik().platform() + "/roms/03_mon-1m_basicLik.bin";
        Range range2 = roms.loadROM(base, binPath1, Lik.START_ROM3);

        // так как монитор частично попадает на 3ю ПЗУ микросхему, приходится тут загружать 2 файла
        Range range = new Range(range1.begin(), Lik.START_BASIC_LIK_V2 - 1);

        String asmPath = binPath1.replaceAll(".bin", ".asm").replaceAll("02_", "02-03_");
        String binPath = String.format(
                "'%s' in range %s\n" +
                ";       and partially '%s' in range: %s",
                binPath1, range1,
                binPath2, new Range(range2.begin(), range.end()));

        // when then
        assertDizAssembly(binPath, asmPath, range, 0xC9B6, 0xC9BC);
    }

    private void assertDizAssembly(String binPath, String asmPath, Range range, Integer... probablyCommands) {
        Bites original = memory.read8arr(range);

        WhereIsData data = new WhereIsData(range);
        Arrays.stream(probablyCommands)
                .forEach(addr ->
                        data.info()[addr].type(WhereIsData.Type.PROBABLE_COMMAND));
        cpu.modAdd(data);

        // when
        String sourceCode = super.assertDizAssembly(data, binPath, asmPath);
        Bites recompiled = assertAssembly(sourceCode, null);

        // then
        assertMemoryChanges("", "", range, original, recompiled);
    }
}