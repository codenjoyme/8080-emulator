package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import spec.math.Bites;
import spec.math.WordMath;
import spec.platforms.Lik;
import spec.utils.JsonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

    private Memory memory;
    private Hardware hardware;

    public RomLoader(Memory memory) {
        this.memory = memory;
    }

    public RomLoader(Hardware hardware) {
        this.hardware = hardware;
        this.memory = hardware.memory();
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
        if (hardware.cpu() != null) {
            hardware.cpu().PC(begin);
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

    private String readString(InputStream is) throws Exception {
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }

    private int readBytes(InputStream is, Bites bites, int offset, int length) throws Exception {
        byte[] buff = new byte[length];

        IOUtils.readFully(is, buff);

        for (int i = 0; i < length; i++) {
            bites.set(i + offset, (buff[i] + 256) & BITE);
        }
        return length;
    }

    public Range loadSnapshot(String base, String path) {
        try {
            String absolute = base + path;
            Logger.debug("Loading json snapshot from: %s", absolute);

            InputStream is = openStream(absolute);

            String json = readString(is);
            JsonObject object = JsonUtils.gson().fromJson(json, JsonObject.class);

            hardware.fromJson(object);

            Logger.debug("Snapshot loaded");
            return new Range(0, -x10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load hardware snapshot in json format.
     * {
     *   "Cpu": {
     *     "PC": "2345",            // `PC` (hex low byte, hex high byte)
     *     "SP": "6789",            // `SP` (hex low byte, hex high byte)
     *     "AF": "1216",            // `AF` (hex low byte, hex high byte)
     *     "BC": "5678",            // `BC` (hex low byte, hex high byte)
     *     "DE": "9ABC",            // `DE` (hex low byte, hex high byte)
     *     "HL": "DEF0",            // `HL` (hex low byte, hex high byte)
     *     "interrupt": 4630,       // `interrupt`
     *     "tick": 448585456,       // `tick`
     *     "tact": 305419896        // `tact`
     *   },
     *   "Timings": {
     *     "interrupt": 1234,       // `interrupt`
     *     "refreshRate": 100,      // `refreshRate`
     *     "willReset": 0,          // `willReset` 1 if true, 0 otherwise
     *     "last": 1733699664007,   // `last`
     *     "delay": 135659,         // `delay`
     *     "fullSpeed": 1,          // `fullSpeed` 1 if true, 0 otherwise
     *     "time": 1733699156833    // `time`
     *   },
     *   "IOPorts": {
     *     "Ain": 0,                // `Ain`  1 if true, 0 otherwise
     *     "Bin": 0,                // `Bin`  1 if true, 0 otherwise
     *     "C0in": 1,               // `C0in` 1 if true, 0 otherwise
     *     "C1in": 0                // `C1in` 1 if true, 0 otherwise
     *   },
     *   "RomSwitcher": {
     *     "platform": "specialist" // `platform` `specialist` or `lik`
     *   },
     *   "Keyboard": {
     *     "keyStatus": [           // array key matrix (1 - pressed, 0 - released)
     *       "110101001110",        // size 12 x 6 + shift + reset
     *       "011100101001",
     *       "101000001100",
     *       "000100011111",
     *       "001110010101",
     *       "100010000110"
     *     ],
     *     "shift": 1,              // `shift`    1 if true, 0 otherwise
     *     "alt": 0,                // `alt`      1 if true, 0 otherwise
     *     "ctrl": 1,               // `ctrl`     1 if true, 0 otherwise
     *     "shiftEmu": 0,           // `shiftEmu` 1 if true, 0 otherwise
     *     "cyrLat": 1              // `cyrLat`   1 if true, 0 otherwise
     *   },
     *   "GraphicControl": {
     *     "ioDrawMode": 3          // `ioDrawMode`
     *                              // `0` Border highlights active window (and audio mode)
     *                              // `1` Border highlights writing byte to Port A
     *                              // `2` Border highlights writing byte to Port B
     *                              // `3` Border highlights writing byte to Port C
     *                              // `4` Border highlights writing byte to Port RgSYS
     *   },
     *   "AudioDriver": {
     *     "audioMode": 1           // `audioMode` 1 if true (AUDIO_MODE_LINE_OUT)
     *                              //             0 otherwise (AUDIO_MODE_SPEAKER)
     *   },
     *   "Memory": {
     *       "data": [              // memory in hex
     *       "      00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F ",  // fist line - address
     *       "0000: C3 00 40 21 00 00 22 E1 8F 21 00 13 22 3A 0F 21 ",  // second line - data
     *       "0010: 00 07 22 3E 0F 0E 1F CD 37 C0 3E 05 32 53 0F AF ",  // ...
     *       // ...
     *     ]
     *   }
     * }
     */
    public void saveSnapshot(String base, String path) {
        try {
            String absolute = base + path;
            Logger.info("Saving json snapshot to: %s", absolute);

            JsonElement json = hardware.toJson();

            FileUtils.writeByteArrayToFile(new File(absolute),
                    JsonUtils.asString(json).getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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