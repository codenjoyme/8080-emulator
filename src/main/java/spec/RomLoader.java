package spec;

import java.io.BufferedInputStream;
import java.io.InputStream;

import static spec.WordMath.BITE;
import static spec.WordMath.merge;

public class RomLoader {

    private Memory memory;
    private Cpu cpu;

    public RomLoader(Memory memory, Cpu cpu) {
        this.memory = memory;
        this.cpu = cpu;
    }

    // чтение ПЗУ ZX Spectrum
    public void loadROMZ(String name, InputStream is) throws Exception {
        Range range = new Range(0, -0x4000);
        logLoading(name, range);
        readBytes(is, memory.all(), range);
    }

    private void logLoading(String name, Range range) {
        System.out.printf("Loading '%s' into %s\n",
                name, range);
    }

    // для ПК "Специалист"
    // чтение ПЗУ
    public void loadROM(String name, InputStream is, int offset) throws Exception {
        Range range = new Range(offset, -0x0800);
        logLoading(name, range);
        readBytes(is, memory.all(), range);
    }

    // для ПК "Специалист"
    // ADN: ML_B, ST_B
    // ADK: ML_B, ST_B
    // Bytes...
    // KSM: ML_B, ST_B
    public void loadRKS(String name, InputStream is) throws Exception {
        int[] header = read8arr(is, 4);
        Range range = new Range(
                merge(header[1], header[0]),
                merge(header[3], header[2]));

        logLoading(name, range);
        readBytes(is, memory.all(), range);

        // int[] data = read8arr(is, 4); // TODO в конце еще два байта, зачем?
        cpu.PC(range.begin());
    }

    private int[] read8arr(InputStream is, int length) throws Exception {
        int[] header = new int[length];
        readBytes(is, header, new Range(0, -length));
        return header;
    }

    // Чтение байт из потока InputStream is
    private int readBytes(InputStream is, int[] a, Range range) throws Exception {
        try {
            int off = range.begin();
            int n = range.length();
            BufferedInputStream bis = new BufferedInputStream(is, n);

            int toRead = n;             // число байт для прочтения (передано int n)
            byte[] buff = new byte[n]; // массив заданного числа int n БАЙТ!

            while (toRead > 0) { // от числа байт для прочтения до 0.
                // BufferedInputStream( is, n )
                int nRead = bis.read(buff, n - toRead, toRead);
                toRead -= nRead;
            }

            for (int i = 0; i < n; i++) { // буффер "byte" превращаем в буффер "int"
                a[i + off] = (buff[i] + 256) & BITE;
            }
            return n;
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            throw e;
        }
    }
}
