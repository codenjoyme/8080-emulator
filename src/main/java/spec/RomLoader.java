package spec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import spec.math.Bites;
import spec.math.WordMath;
import spec.platforms.Lik;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringAfter;
import static spec.Constants.*;
import static spec.math.WordMath.BITE;

public class RomLoader {

    public static final String TYPE_COM = "com";
    public static final String TYPE_ROM = "rom";
    public static final String TYPE_BIN = "bin";
    public static final String TYPE_RKS = "rks";
    public static final String TYPE_SNP = "snp";
    public static final String TYPE_BSS = "bss";
    public static final String TYPE_BS1 = "bs1";
    public static final String TYPE_MEM = "mem";

    private Cpu cpu;
    private IOPorts ports;
    private Keyboard keyboard;
    private GraphicControl graphic;
    private Timings timings;
    private Memory memory;
    private RomSwitcher romSwitcher;

    public RomLoader(Memory memory) {
        this.memory = memory;
    }

    public RomLoader(Memory memory, Cpu cpu, IOPorts ports, Keyboard keyboard, GraphicControl graphic, Timings timings, RomSwitcher romSwitcher) {
        this.memory = memory;
        this.cpu = cpu;
        this.ports = ports;
        this.keyboard = keyboard;
        this.graphic = graphic;
        this.timings = timings;
        this.romSwitcher = romSwitcher;
    }

    private void logLoading(String name, Range range) {
        Logger.info("Loading '%s' into %s", name, range);
    }

    // для ПК "Специалист"/"ЛИК"
    // чтение ПЗУ
    public Range loadROM(String base, String path, int offset) {
        return loadROM(base, path, memory.all(), offset);
    }

    public Range loadROM(String base, String path, Bites all, int offset) {
        try {
            String absolute = base + path;
            InputStream is = openStream(absolute);
            cpuPC(offset);
            return readAll(all, offset, is, absolute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream openStream(String path) throws FileNotFoundException {
        File file = new File(path).getAbsoluteFile();
        if (file.exists()) {
            Logger.debug("Opening file: %s", path);
            return new FileInputStream(file);
        }

        Logger.debug("Opening classpath resource: %s", path);
        return RomLoader.class.getResourceAsStream(path);
    }

    private Range readAll(Bites all, int offset, InputStream is, String path) throws Exception {
        int length = is.available();
        Range range = new Range(offset, -length);
        logLoading(path, range);
        readBytes(is, all, range);
        return range;
    }

    // для ПК `Специалист`/`ЛИК` и `BASIC ЛИК V2` (BSS) файл содержит программу в машинных кодах
    // 3 байта - 0xD3 0xD3 0xD3
    // массив байтов программы загружается в память начиная с 0x1E60
    public Range loadBSS(String base, String path) {
        return loadBSx(base, path, memory.all(), BASIC_LIK_V2_PROGRAM_START);
    }

    // для ПК `Специалист`/`ЛИК` и `BASIC` (BS1) файл содержит программу в машинных кодах
    // 3 байта - 0xD3 0xD3 0xD3
    // массив байтов программы загружается в память начиная с 0x2000
    public Range loadBS1(String base, String path) {
        return loadBSx(base, path, memory.all(), BASIC_V1_PROGRAM_START);
    }

    public Range loadBSx(String base, String path, Bites all, int offset) {
        try {
            String absolute = base + path;
            InputStream is = openStream(absolute);

            Bites header = read8arr(is, 3);
            header.equals(new Bites(0xD3, 0xD3, 0xD3));

            return readAll(all, offset, is, absolute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO #23 сделать преобразователь mem -> rks

    // Разновидность 1 для ПК "Специалист":
    // 2 байта  - адресс начала памяти (low byte, high byte)
    // 2 байта  - адресс конца памяти (low byte, high byte)
    // массив байтов прпограммы длинной между началом и концом
    // 2 байта  - контрольная сумма (low byte, high byte) TODO #24 реализовать проверку контрольной суммы в rks

    // Разновидность 2
    // 4 байта  - 0x70 0x8F 0x82 0x8F (префикс)
    // 16 байт  - 0x00
    // 1 байт   - 0xBC
    // 2 байта  - контрольная сумма (low byte, high byte) TODO #24 реализовать проверку контрольной суммы в rks
    // 255 байт - 0x00
    // 1 байт   - 0xE6
    // 2 байта  - адресс начала памяти (low byte, high byte)
    // 2 байта  - адресс конца памяти (low byte, high byte)
    // массив байтов прпограммы длинной между началом и концом
    public Range loadRKS(String base, String path) {
        try {
            String absolute = base + path;
            InputStream is = openStream(absolute);

            Bites header = read8arr(is, 4);
            if (header.equals(new Bites(0x70, 0x8F, 0x82, 0x8F))) {
                read8arr(is, 16);
                read8arr(is, 1).equals(new Bites(0xBC));
                Bites crc = read8arr(is, 2);
                read8arr(is, 255);
                read8arr(is, 1).equals(new Bites(0xE6));
                header = read8arr(is, 4);
            }

            Range range = new Range(
                    WordMath.merge(header.get(1), header.get(0)),
                    WordMath.merge(header.get(3), header.get(2)));
            if (range.isInvalid()) {
                throw new IllegalArgumentException(String.format(
                        "Range %s is invalid for: %s", range, path));
            }

            logLoading(absolute, range);
            cpuPC(range.begin());
            readBytes(is, memory.all(), range);
            return range;
            // Bites data = read8arr(is, 4); // TODO #24 в конце еще два байта, контрольная сумма - реализовать проверку
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void cpuPC(int begin) {
        if (cpu != null) {
            cpu.PC(begin);
        }
    }

    private Bites read8arr(InputStream is, int length) throws Exception {
        Bites header = new Bites(length);
        readBytes(is, header, new Range(0, -length));
        return header;
    }

    private int readBytes(InputStream is, Bites bites, Range range) throws Exception {
        return readBytes(is, bites, range.begin(), range.length());
    }

    private int readBytes(InputStream is, Bites bites, int offset, int length) throws Exception {
        byte[] buff = new byte[length];

        IOUtils.readFully(is, buff);

        for (int i = 0; i < length; i++) {
            bites.set(i + offset, (buff[i] + 256) & BITE);
        }
        return length;
    }

    /**
     * Load hardware snapshot.
     * Format: CPU state, memory dump, I/O ports state.
     * * CPU state:
     *   - 2 bytes             - `PC` (low byte, high byte)
     *   - 2 bytes             - `SP` (low byte, high byte)
     *   - 2 bytes             - `AF` (low byte, high byte)
     *   - 2 bytes             - `BC` (low byte, high byte)
     *   - 2 bytes             - `DE` (low byte, high byte)
     *   - 2 bytes             - `HL` (low byte, high byte)
     *   - 4 bytes for int     - `interrupt`
     *   - 4 bytes for int     - `tick`
     *   - 4 bytes for int     - `tact`
     * * Keyboard state:
     *   - 12*6 bytes          - keyboard state 12 x 6 for all keys - is key pressed
     *   - 1 byte              - flags `0b__shift_alt_ctrl_shiftEmu__cyrLat_0_0_0`
     *     + shift             - `0b_x000_0000` is shift key pressed
     *     + alt               - `0b_0x00_0000` is alt key pressed
     *     + ctrl              - `0b_00x0_0000` is ctrl key pressed
     *     + shiftEmu          - `0b_000x_0000` is shiftEmu flag is set
     *     + cyrLat            - `0b_0000_x000` is cyrLat is cyrillic or latin
     * * I/O ports state:
     *   - 1 byte              - flags `0b__0_0_0_A__C1_0_B_C0`
     *     + Ain               - `0b_000x_0000`
     *     + Bin               - `0b_0000_00x0`
     *     + C0in              - `0b_0000_000x`
     *     + C1in              - `0b_0000_x000`
     * * GraphicControl state:
     *   - 1 byte for int      - `ioDrawMode`
     * * Timings state:
     *   - 4 bytes for int     - `interrupt`
     *   - 4 bytes for int     - `refreshRate`
     *   - 1 byte for boolean  - 1 if `willReset` true, 0 otherwise
     *   - 8 bytes for long    - `last`
     *   - 4 bytes for int     - `delay`
     *   - 1 byte for boolean  - 1 if `fullSpeed` true, 0 otherwise
     *   - 8 bytes for long    - `time`
     *   - 4 bytes for int     - `iterations`
     * * Memory dump: `0x0000` - `0xFFFF`
     * * ROM switcher state:
     *   - 1 byte for boolean  - 1 if `lik` true, 0 otherwise
     */
    public Range loadSnapshot(String base, String path) {
        try {
            String absolute = base + path;
            Logger.debug("Loading snapshot from %s", absolute);

            InputStream is = openStream(absolute);
            List<StateProvider> states = allStates();
            int size = statesSize(states);
            Bites bites = new Bites(size);
            readBytes(is, bites, 0, size);

            int offset = 0;
            for (StateProvider provider : states) {
                offset = readPart(provider, offset, bites);
            }

            Logger.debug("Snapshot loaded");
            return new Range(0, -x10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSnapshot(String base, String path) {
        try {
            String absolute = base + path;
            Logger.info("Saving snapshot to %s", absolute);

            List<StateProvider> states = allStates();
            int size = statesSize(states);
            Bites bites = new Bites(size);

            int offset = 0;
            for (StateProvider provider : states) {
                offset = writePart(bites, provider.state(), offset);
            }

            FileUtils.writeByteArrayToFile(new File(absolute), bites.byteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int statesSize(List<StateProvider> states) {
        return states.stream()
                .map(StateProvider::stateSize)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private List<StateProvider> allStates() {
        return Arrays.asList(
                cpu,
                keyboard,
                ports,
                graphic,
                timings,
                memory,
                romSwitcher);
    }

    private int readPart(StateProvider provider, int offset, Bites bites) {
        Range range = new Range(0, -provider.stateSize()).shift(offset);
        provider.state(bites.array(range));
        return offset + range.length();
    }

    private static int writePart(Bites dest, Bites source, int offset) {
        Range range = new Range(0, -source.size());
        dest.set(range.shift(offset), source);
        return offset + range.length();
    }

    public Range load(String base, String path) {
        switch (getFileExt(path)) {
            case TYPE_COM: {
                return loadROM(base, path, 0x0100);
            }
            case TYPE_ROM:
            case TYPE_BIN: {
                return loadROM(base, path, 0xC000);
            }
            case TYPE_RKS: {
                return loadRKS(base, path);
            }
            case TYPE_SNP: {
                return loadSnapshot(base, path);
            }
            case TYPE_BSS: {
                loadRKS(base, new Lik().app("basic", ".rks"));
                loadBSS(base, path);
                return new Range(0, BASIC_LIK_V2_PROGRAM_START);
            }
            case TYPE_BS1: {
                loadRKS(base, new Lik().app("basic2", ".rks"));
                loadBS1(base, path);
                return new Range(0, BASIC_V1_PROGRAM_START);
            }
            case TYPE_MEM:
            default: {
                return loadROM(base, path, 0x0000);
            }
        }
    }

    public static String getFileExt(String path) {
        File file = new File(path);
        return substringAfter(file.getName(), ".").toLowerCase();
    }
}