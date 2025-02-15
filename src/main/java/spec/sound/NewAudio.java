package spec.sound;

import javax.sound.sampled.*;

public class NewAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 2500;
    private static final int BUFFER_CAPACITY = 128;

    private final SourceDataLine line;
    private final byte[] buffer = new byte[BUFFER_CAPACITY];
    private int bufferIndex = 0;

    public NewAudio() {
        try {
            AudioFormat format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(int bite) {
        buffer[bufferIndex++] = (byte) (bite - 128);
        if (bufferIndex >= buffer.length) {
            synchronized (this) {
                line.write(buffer, 0, buffer.length);
                clearBuffer();
            }
        }
    }

    @Override
    public void tick() {
        synchronized (this) {
            if (bufferIndex > 0) {
                line.write(buffer, 0, bufferIndex);
                clearBuffer();
            }
        }
    }

    private void clearBuffer() {
        java.util.Arrays.fill(buffer, (byte) 0); // Полная очистка содержимого буфера
        bufferIndex = 0;
    }

    @Override
    public void play() {
        // Не используется
    }

}