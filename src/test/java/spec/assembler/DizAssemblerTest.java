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
import java.util.*;
import java.util.stream.Collectors;

import static spec.Constants.x10000;
import static spec.math.WordMath.hex16;
import static spec.platforms.Platform.RESOURCES;
import static spec.stuff.SmartAssert.assertEquals;
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

        if (List.of("pilot", "reversi", "tip-top2", "zoo", "blobcop").contains(name)) {
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
        assertDizAssembly(binPath, asmPath, range,
                // те адреса куда ссылались игрушки
                // TODO изучить как они это делали, и что полезного там есть
                0xC427, 0xC438, 0xC337, 0xC25A, 0xC254, 0xC010, 0xC037, 0xC170,
                // адреса что я вручную методом тыка пробовал, последовательно изучая
                // области с данными после дизассемблирования на предмет - а не код ли это?
                0xC287, 0xC3D0);
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
        assertDizAssembly(binPath, asmPath, range,
                // адреса что я вручную методом тыка пробовал, последовательно изучая
                // области с данными после дизассемблирования на предмет - а не код ли это?
                0xC9B6, 0xC9BC/*, 0xC9C7*/);
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

    @Test
    public void testToLabel() {
        assertLabels(
                "00000 > ljyzh\n" +
                "01323 > lnhhj\n" +
                "02477 > lnitx\n" +
                "027CD > ljpog\n" +
                "03EF3 > lnncu\n" +
                "03123 > lnnjf\n" +
                "03345 > ljrkh\n" +
                "04567 > lobbb\n" +
                "04FFF > loajm\n" +
                "051FF > locaw\n" +
                "05F1F > lobxj\n" +
                "0511F > locbj\n" +
                "06666 > lotfh\n" +
                "07FFF > lohyk\n" +
                "08F50 > lcmvv\n" +
                "08F51 > lcast\n" +
                "08F70 > lcmvf\n" +
                "08FE1 > lcauo\n" +
                "08FFC > lcjko\n" +
                "09999 > lcbve\n" +
                "09000 > lcopl\n" +
                "0A0A0 > lctcy\n" +
                "0BBA0 > lcule\n" +
                "0C037 > lgozx\n" +
                "0C337 > lgozr\n" +
                "0C427 > lgpdp\n" +
                "0CBA0 > ldlck\n" +
                "0DF19 > ldach\n" +
                "0E5C9 > ldeyg\n" +
                "0FFFF > lgwbe\n");

        Map<String, Set<String>> map = new LinkedHashMap<>();
        for (int addr = 0; addr < x10000; addr++) {
            String result = DizAssembler.toLabel(addr);
            if (!map.containsKey(result)) {
                map.put(result, new HashSet<>());
            }
            map.get(result).add(hex16(addr));
        }

        // чтобы небыло 2х и более адресов приводящих к одной метке
        assertEquals("", map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(entry -> entry.getKey() + " > " + entry.getValue())
                .collect(Collectors.joining("\n")));

        // чтобы небыло ни одной метки в диапазоне 16 бит длинной больше 4
        assertEquals("", map.entrySet().stream()
                .filter(entry -> entry.getKey().length() > 5)
                .map(entry -> entry.getValue().toString() + " > " + entry.getKey())
                .collect(Collectors.joining("\n")));
    }

    private static void assertLabels(String addresses) {
        String[] lines = addresses.split("\n");
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            String[] parts = line.split(" > ");
            int address = Integer.parseInt(parts[0], 16);
            String label = parts[1];
            result.append(parts[0])
                    .append(" > ")
                    .append(DizAssembler.toLabel(address))
                    .append("\n");
        }
        assertEquals(addresses, result.toString());
    }
}