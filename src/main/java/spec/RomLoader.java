package spec;

import spec.math.Bites;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import static spec.math.WordMath.BITE;
import static spec.math.WordMath.merge;

public class RomLoader {

    private Memory memory;
    private Cpu cpu;

    public RomLoader(Memory memory, Cpu cpu) {
        this.memory = memory;
        this.cpu = cpu;
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

    private int readBytes(InputStream is, Bites a, Range range) throws Exception {
        int off = range.begin();
        int n = range.length();

        byte[] buff = new byte[n];

        IOUtils.readFully(is, buff);

        for (int i = 0; i < n; i++) {
            a.set(i + off, (buff[i] + 256) & BITE);
        }
        return n;
    }

    public void loadSnapshot(URL base, String path) {
        try {
            loadRKS(base, path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Range load(URL base, String path) {
        try {
            File file = new File(path);
            switch (substringAfter(file.getName(), ".")) {
                case "com": {
                    return loadROM(base, file.getPath(), 0x0100);
                }
                case "rom":
                case "bin": {
                    return loadROM(base, file.getPath(), 0xC000);
                }
                case "rks": {
                    return loadRKS(base, file.getPath());
                }
                case "mem":
                default: {
                    return loadROM(base, file.getPath(), 0x0000);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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