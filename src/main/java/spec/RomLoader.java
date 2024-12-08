package spec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import spec.math.Bites;
import spec.math.WordMath;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static spec.math.WordMath.BITE;

public class RomLoader {

    private Cpu cpu;
    private IOPorts ports;
    private GraphicControl graphic;
    private Timings timings;
    private Memory memory;

    public RomLoader(Memory memory, Cpu cpu, IOPorts ports, GraphicControl graphic, Timings timings) {
        this.memory = memory;
        this.cpu = cpu;
        this.ports = ports;
        this.graphic = graphic;
        this.timings = timings;
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
            int length = is.available();
            Range range = new Range(offset, -length);
            logLoading(url.toString(), range);
            cpu.PC(offset);
            readBytes(is, all, range);
            return range;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO сделать преобразователь mem -> rks

    // для ПК "Специалист"
    // ADN: ML_B, ST_B
    // ADK: ML_B, ST_B
    // Bytes...
    // KSM: ML_B, ST_B
    public Range loadRKS(URL base, String path) {
        try {
            URL url = new URL(base, path);
            InputStream is = url.openStream();
            Bites header = read8arr(is, 4);
            Range range = new Range(
                    WordMath.merge(header.get(1), header.get(0)),
                    WordMath.merge(header.get(3), header.get(2)));
            if (range.isInvalid()) {
                throw new IllegalArgumentException(String.format(
                        "Rage %s is invalid for: %s", range, path));
            }

            logLoading(url.toString(), range);
            cpu.PC(range.begin());
            readBytes(is, memory.all(), range);
            return range;
            // Bites data = read8arr(is, 4); // TODO в конце еще два байта, контрольная сумма - реализовать проверку
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
     * * I/O ports state:
     *   - 12*6 bytes          - keyboard state 12 x 6 for all keys - is key pressed
     *   - 1 byte              - flags `0b__shift_alt_ctrl_A__C1_0_B_C0`
     *     + Ain               - `0b_000x_0000`
     *     + Bin               - `0b_0000_00x0`
     *     + C0in              - `0b_0000_000x`
     *     + C1in              - `0b_0000_x000`
     *     + shift             - `0b_x000_0000` is shift key pressed
     *     + alt               - `0b_0x00_0000` is alt key pressed
     *     + ctrl              - `0b_00x0_0000` is ctrl key pressed
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
     * TODO implement me
     *   - 1 byte for boolean  - 1 if `lik` true, 0 otherwise
     *   - 4 bytes for int     - `CPU tick`
     *   - 4 bytes for int     - `CPU tact`
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
                ports,
                graphic,
                timings,
                memory);
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
        switch (substringAfter(file.getName(), ".")) {
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