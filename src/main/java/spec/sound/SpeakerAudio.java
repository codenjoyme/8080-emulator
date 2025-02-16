package spec.sound;

import javax.sound.sampled.*;

import static spec.Constants.AUDIO_BYTES_PER_TICK;

public class SpeakerAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_CAPACITY = 2048;

    private final DataLine.Info info;
    private final AudioFormat format;

    private final byte[] buffer = new byte[BUFFER_CAPACITY];
    private SourceDataLine line;
    private int index = 0;
    private int current;

    public SpeakerAudio() {
        format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        info = new DataLine.Info(SourceDataLine.class, format);
        createLine();
        current = 128;
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

        current = bite;
    }

    public void write() {
        buffer[index++] = (byte) (current - 128);
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
        if (line == null) return;

        for (int i = 0; i < AUDIO_BYTES_PER_TICK; i++) {
            write();
        }
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