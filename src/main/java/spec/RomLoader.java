package spec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import spec.math.Bites;
import spec.math.WordMath;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static spec.Constants.BASIC_LIK_V2_PROGRAM_START;
import static spec.math.WordMath.BITE;

public class RomLoader {

    private Cpu cpu;
    private IOPorts ports;
    private Keyboard keyboard;
    private GraphicControl graphic;
    private Timings timings;
    private Memory memory;
    private RomSwitcher romSwitcher;

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
    public Range loadROM(URL base, String path, int offset) {
        return loadROM(base, path, memory.all(), offset);
    }

    public Range loadROM(URL base, String path, Bites all, int offset) {
        try {
            URL url = new URL(base, path);
            InputStream is = url.openStream();
            cpu.PC(offset);
            Range range = readAll(all, offset, is, url);
            return range;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Range readAll(Bites all, int offset, InputStream is, URL url) throws Exception {
        int length = is.available();
        Range range = new Range(offset, -length);
        logLoading(url.toString(), range);
        readBytes(is, all, range);
        return range;
    }

    // для ПК `Специалист`/`ЛИК` и `BASIC ЛИК V2` (BSS) файл содержит программу в машинных кодах
    // 3 байта - 0xD3 0xD3 0xD3
    // массив байтов программы загружается в память начиная с 0x1E60
    public Range loadBSS(URL base, String path) {
        try {
            URL url = new URL(base, path);
            InputStream is = url.openStream();

            Bites header = read8arr(is, 3);
            header.equals(new Bites(0xD3, 0xD3, 0xD3));

            int offset = BASIC_LIK_V2_PROGRAM_START;
            Range range = readAll(memory.all(), offset, is, url);
            return range;
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
    public Range loadRKS(URL base, String path) {
        try {
            URL url = new URL(base, path);
            InputStream is = url.openStream();

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

            logLoading(url.toString(), range);
            cpu.PC(range.begin());
            readBytes(is, memory.all(), range);
            return range;
            // Bites data = read8arr(is, 4); // TODO #24 в конце еще два байта, контрольная сумма - реализовать проверку
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public Range loadSnapshot(URL base, String path) {
        try {
            URL url = new URL(base, path);
            InputStream is = url.openStream();
            List<StateProvider> states = allStates();
            int size = statesSize(states);
            Bites bites = new Bites(size);
            readBytes(is, bites, 0, size);

            int offset = 0;
            for (StateProvider provider : states) {
                offset = readPart(provider, offset, bites);
            }

            Logger.debug("Snapshot loaded from %s", url);
            return new Range(0, size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSnapshot(URL base, String path) {
        try {
            URL url = new URL(base, path);
            List<StateProvider> states = allStates();
            int size = statesSize(states);
            Bites bites = new Bites(size);

            int offset = 0;
            for (StateProvider provider : states) {
                offset = writePart(bites, provider.state(), offset);
            }

            FileUtils.writeByteArrayToFile(new File(url.getFile()), bites.byteArray());
            Logger.info("Snapshot saved to %s", url);
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
        return List.of(
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

    public Range load(URL base, String path) {
        File file = new File(path);
        switch (substringAfter(file.getName(), ".").toLowerCase()) {
            case "com": {
                return loadROM(base, path, 0x0100);
            }
            case "rom":
            case "bin": {
                return loadROM(base, path, 0xC000);
            }
            case "rks": {
                return loadRKS(base, path);
            }
            case "snp": {
                return loadSnapshot(base, path);
            }
            case "bss": {
                loadBSS(base, path);
                return new Range(0, BASIC_LIK_V2_PROGRAM_START);
            }
            case "mem":
            default: {
                return loadROM(base, path, 0x0000);
            }
        }
    }

    private String substringAfter(String name, String delimiter) {
        int index = name.indexOf(delimiter);
        if (index == -1) {
            return name;
        }
        return name.substring(index + 1);
    }
}