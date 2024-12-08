package spec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import spec.math.Bites;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import static spec.Constants.x10000;
import static spec.math.WordMath.*;

public class RomLoader {

    private static final int SNAPSHOT_APPS_STATE_SIZE = 14;
    private static final int SNAPSHOT_IO_PORTS_KEYS_STATE_SIZE = 12;

    private Memory memory;
    private Cpu cpu;
    private IOPorts ports;
    private GraphicControl graphic;

    public RomLoader(Memory memory, Cpu cpu, IOPorts ports, GraphicControl graphic) {
        this.memory = memory;
        this.cpu = cpu;
        this.ports = ports;
        this.graphic = graphic;
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
                    merge(header.get(1), header.get(0)),
                    merge(header.get(3), header.get(2)));
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
     *      - 2 bytes          - `PC` (low byte, high byte)
     *      - 2 bytes          - `SP` (low byte, high byte)
     *      - 2 bytes          - `AF` (low byte, high byte)
     *      - 2 bytes          - `BC` (low byte, high byte)
     *      - 2 bytes          - `DE` (low byte, high byte)
     *      - 2 bytes          - `HL` (low byte, high byte)
     *    * I/O ports state:
     *      - 1 byte           - flags `0b__shift_alt_ctrl_A__C1_0_B_C0`
     *        + Ain            - `0b_000x_0000`
     *        + Bin            - `0b_0000_00x0`
     *        + C0in           - `0b_0000_000x`
     *        + C1in           - `0b_0000_x000`
     *        + shift          - `0b_x000_0000` is shift key pressed
     *        + alt            - `0b_0x00_0000` is alt key pressed
     *        + ctrl           - `0b_00x0_0000` is ctrl key pressed
     *      - 12*6 bytes       - keyboard state 12 x 6 for all keys - is key pressed
     *    * Application state:
     *      - 1 byte for int   - `GraphicControl.ioDrawMode`
     *      TODO implement me
     *      - 4 bytes for int  - `CPU tick`
     *      - 4 bytes for int  - `CPU tact`
     *      - 4 bytes for int  - `interrupt`
     *      - 4 bytes for int  - `refreshRate`
     *      - 8 bytes for long - `last`
     *      - 4 bytes for int  - `delay`
     *      - 1 byte for other flags:
     *        + fullSpeed      - `0b0000_000x`
     *        + lik            - `0b0000_00x0`
     *        + willReset      - `0b0000_0x00`
     *      - 8 bytes for long - `time`
     *      - 4 bytes for int  - `iterations`
     *    * Memory dump: `0x0000` - `0xFFFF`
     */
    public Range loadSnapshot(URL base, String path) {
        try {
            URL url = new URL(base, path);
            InputStream is = url.openStream();
            Bites header = read8arr(is, SNAPSHOT_APPS_STATE_SIZE + SNAPSHOT_IO_PORTS_KEYS_STATE_SIZE);

            cpu.PC(merge(header.get(1), header.get(0)));
            cpu.SP(merge(header.get(3), header.get(2)));
            cpu.AF(merge(header.get(5), header.get(4)));
            cpu.BC(merge(header.get(7), header.get(6)));
            cpu.DE(merge(header.get(9), header.get(8)));
            cpu.HL(merge(header.get(11), header.get(10)));

            ports.state(header.get(12));

            graphic.ioDrawMode(header.get(13));

            Range keysRange = new Range(0, -SNAPSHOT_IO_PORTS_KEYS_STATE_SIZE).shift(SNAPSHOT_APPS_STATE_SIZE);
            ports.keyState(header.array(keysRange));

            Range range = new Range(0, -x10000);
            readBytes(is, memory.all(), 0, range.length());

            Logger.debug("Snapshot loaded from %s", url);
            return range;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSnapshot(URL base, String path) {
        try {
            URL url = new URL(base, path);
            Bites bites = new Bites(x10000 + SNAPSHOT_APPS_STATE_SIZE + SNAPSHOT_IO_PORTS_KEYS_STATE_SIZE);

            bites.set(0, lo(cpu.PC()));
            bites.set(1, hi(cpu.PC()));
            bites.set(2, lo(cpu.SP()));
            bites.set(3, hi(cpu.SP()));
            bites.set(4, cpu.F());
            bites.set(5, cpu.A());
            bites.set(6, cpu.C());
            bites.set(7, cpu.B());
            bites.set(8, cpu.E());
            bites.set(9, cpu.D());
            bites.set(10, cpu.L());
            bites.set(11, cpu.H());

            bites.set(12, ports.state());

            bites.set(13, graphic.ioDrawMode());

            Range keysRange = new Range(0, -SNAPSHOT_IO_PORTS_KEYS_STATE_SIZE).shift(SNAPSHOT_APPS_STATE_SIZE);
            bites.set(keysRange, ports.keyState());

            Range memoryRange = new Range(0, -x10000);
            bites.set(memoryRange.shift(keysRange.end() + 1), memory.all());

            FileUtils.writeByteArrayToFile(new File(url.getFile()), bites.byteArray());

            Logger.info("Snapshot saved to %s", url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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