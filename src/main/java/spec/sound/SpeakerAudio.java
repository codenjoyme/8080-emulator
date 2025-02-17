package spec.sound;

import javax.sound.sampled.*;

import static spec.Constants.AUDIO_BYTES_PER_TICK;

public class SpeakerAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = 1024*4;
    private static final int BUFFER_COUNT = 2;

    private byte[] buffer = new byte[BUFFER_SIZE * BUFFER_COUNT];
    private int writePos = 0;
    private int readPos = 0;
    private SourceDataLine line;
    private Thread audio;
    private boolean running = true;
    private int current;

    public SpeakerAudio() {
        AudioFormat format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, BUFFER_SIZE);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        audio = new Thread(this::processAudio);
        audio.start();
    }

    private void processAudio() {
        int available;
        while (running) {
            synchronized (this) {
                available = (writePos - readPos + BUFFER_SIZE * BUFFER_COUNT) % (BUFFER_SIZE * BUFFER_COUNT);
            }
            if (available > 0) {
                int toRead = Math.min(available, BUFFER_SIZE - (readPos % BUFFER_SIZE));
                line.write(buffer, readPos % (BUFFER_SIZE * BUFFER_COUNT), toRead);
                readPos += toRead;
            }
        }
    }

    public void write() {
        synchronized (this) {
            buffer[writePos % (BUFFER_SIZE * BUFFER_COUNT)] = (byte) (current - 128);
            writePos++;
        }
    }

    @Override
    public void close() {
        if (line != null) {
            running = false;
            line.drain();
            line.close();
            line = null;
        }
        try {
            if (audio != null) {
                audio.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void write(int bite) {
        current = bite;
    }

    @Override
    public void tick() {
        for (int i = 0; i < AUDIO_BYTES_PER_TICK; i++) {
            write();
        }
    }
}