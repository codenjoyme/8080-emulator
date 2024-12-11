package spec.assembler;

import org.junit.Ignore;
import org.junit.Test;
import spec.Logger;
import spec.Range;
import spec.math.Bites;
import spec.mods.WhereIsData;
import spec.platforms.Platform;
import spec.stuff.AbstractTest;

import java.io.File;
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
        Range range = roms.loadROM(base, binPath, 0xC000);
        String asmPath = binPath.replaceAll(".bin", ".asm");

        // when then
        assertDizAssembly(binPath, asmPath, range);
    }

    private void assertDizAssembly(String binPath, String asmPath, Range range) {
        Bites original = memory.read8arr(range);

        WhereIsData data = new WhereIsData(range);
        cpu.modAdd(data);

        // when

        String sourceCode = super.assertDizAssembly(data, binPath, asmPath);
        Bites recompiled = assertAssembly(sourceCode, null);

        // then
        assertMemoryChanges("", "", range, original, recompiled);
    }
}