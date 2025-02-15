package spec.sound;

import javax.sound.sampled.*;

public class NewAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 8000;
    private static final int BUFFER_CAPACITY = 1024;

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
            bufferIndex = 0;
        }
    }

    @Override
    public void tick() {
        if (bufferIndex > 0) {
            line.write(buffer, 0, bufferIndex);
            clearBuffer(); // Очистка буфера после отправки данных
        }
    }

    private void clearBuffer() {
        for (int i = 0; i < bufferIndex; i++) {
            buffer[i] = 0; // Зануляем буфер
        }
        bufferIndex = 0; // Сброс индекса после очистки буфера
    }

    @Override
    public void play() {
        // Не используется
    }

    public void stop() {
        line.drain();
        line.stop();
        line.close();
    }
}