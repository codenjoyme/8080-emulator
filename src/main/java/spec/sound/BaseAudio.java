package spec.sound;

import javax.sound.sampled.*;

public abstract class BaseAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_COUNT = 2;

    private byte[] buffer;
    private int bufferSize;
    private int writePos = 0;
    private int readPos = 0;
    private SourceDataLine line;
    private Thread audio;
    private boolean running = true;

    public BaseAudio(int bufferSize) {
        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize * BUFFER_COUNT];

        AudioFormat format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, bufferSize);
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
                available = (writePos - readPos + bufferSize * BUFFER_COUNT) % (bufferSize * BUFFER_COUNT);
            }
            if (available > 0) {
                int toRead = Math.min(available, bufferSize - (readPos % bufferSize));
                line.write(buffer, readPos % (bufferSize * BUFFER_COUNT), toRead);
                readPos += toRead;
            }
        }
    }

    private void writeOnce(int bite) {
        synchronized (this) {
            buffer[writePos % (bufferSize * BUFFER_COUNT)] = (byte) (bite - 128);
            writePos++;
        }
    }

    protected void writeTimes(int bite, int times) {
        if (line == null) return;

        for (int i = 0; i < times; i++) {
            writeOnce(bite);
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
}
