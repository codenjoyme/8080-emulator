package spec.sound;

import javax.sound.sampled.*;

public class NewAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 2500; // here should be 8000 for `BUDDY`
    private static final int BUFFER_CAPACITY = 128;

    private final DataLine.Info info;
    private final AudioFormat format;

    private final byte[] buffer = new byte[BUFFER_CAPACITY];
    private SourceDataLine line;
    private int index = 0;

    public NewAudio() {
        format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        info = new DataLine.Info(SourceDataLine.class, format);
        createLine();
    }

    private void createLine() {
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(int bite) {
        if (line == null) return;

        buffer[index++] = (byte) (bite - 128);
        if (index >= buffer.length) {
            soundBuffer();
        }
    }

    private void soundBuffer() {
        line.flush();
        line.write(buffer, 0, buffer.length);
        clearBuffer();
    }

    @Override
    public void tick() {
        write(128); // remove this to better `BUDY`
    }

    private void clearBuffer() {
        java.util.Arrays.fill(buffer, (byte) 0); // Полная очистка содержимого буфера
        index = 0;
    }

    @Override
    public void play() {
        // do nothing
    }

    @Override
    public void close() {
        if (line == null) return;

        line.drain();
        line.close();
        line = null;
    }

}