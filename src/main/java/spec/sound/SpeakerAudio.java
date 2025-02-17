package spec.sound;

import javax.sound.sampled.*;

import static spec.Constants.AUDIO_BYTES_PER_TICK;

public class SpeakerAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_CAPACITY = 2048*40;

    private final DataLine.Info info;
    private final AudioFormat format;

    private final byte[][] buffers = new byte[2][BUFFER_CAPACITY]; // Два буфера для double buffering
    private int currentBuffer = 0;  // Индекс текущего буфера для записи
    private int playBuffer = 1;  // Индекс буфера для воспроизведения
    private int index = 0;
    private int current;

    private SourceDataLine line;

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
        buffers[currentBuffer][index++] = (byte) (current - 128);
        if (index >= buffers[currentBuffer].length) {
            soundBuffer();
        }
    }

    private void soundBuffer() {
        line.flush();
        line.write(buffers[playBuffer], 0, buffers[playBuffer].length);
        playBuffer = currentBuffer;
        currentBuffer = 1 - currentBuffer;  // Переключение буферов
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
        java.util.Arrays.fill(buffers[currentBuffer], (byte) 0);
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