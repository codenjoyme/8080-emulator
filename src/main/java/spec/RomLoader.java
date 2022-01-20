package spec;

import java.io.BufferedInputStream;
import java.io.InputStream;

import static spec.WordMath.BITE;
import static spec.WordMath.WORD;

public class RomLoader {

    private final Memory memory;
    private final Cpu cpu;

    public RomLoader(Memory memory, Cpu cpu) {
        this.memory = memory;
        this.cpu = cpu;
    }

    // чтение ПЗУ ZX Spectrum
    public void loadROMZ(String name, InputStream is) throws Exception {
        int length = 0x4000;
        int offset = 0;
        logLoading(name, offset, length);
        readBytes(is, memory.all(), offset, length);
    }

    private void logLoading(String name, int offset, int length) {
        System.out.printf("Loading '%s' into [%04X:%04X]\n",
                name,
                offset & WORD,
                (offset + length) & WORD);
    }

    // для ПК "Специалист"
    // чтение ПЗУ
    public void loadROM(String name, InputStream is, int offset) throws Exception {
        int length = 0x800;
        logLoading(name, offset, length);
        readBytes(is, memory.all(), offset, length);
    }

    // для ПК "Специалист"
    // ADN: ML_B, ST_B
    // ADK: ML_B, ST_B
    // Bytes...
    // KSM: ML_B, ST_B
    public void loadRKS(String name, InputStream is) throws Exception {
        int[] header = new int[6];

        readBytes(is, header, 0, 4);
        int ABeg = header[1] * 256 + header[0];
        int AEnd = header[3] * 256 + header[2];
        int ALen = AEnd - ABeg;
        logLoading(name, ABeg, ALen);
        readBytes(is, memory.all(), ABeg, ALen);
        readBytes(is, header, 4, 2);

        cpu.PC(ABeg);
    }

    // Чтение байт из потока InputStream is
    private int readBytes(InputStream is, int[] a, int off, int n) throws Exception {
        try {//try readBytes(             is, int mem[],     0, 16384 );
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
