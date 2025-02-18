package spec.sound;

import javax.sound.sampled.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class BaseAudio implements Audio {

    public static final boolean CAN_SKIP_DATA = true;
    public static final boolean DO_NOT_SKIP_DATA = !CAN_SKIP_DATA;

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_COUNT = 2;

    private final boolean allowDataSkip;
    private ArrayBlockingQueue<Byte> audioQueue;
    private SourceDataLine line;
    private Thread audio;
    private volatile boolean running = true;
    private final int bufferSize;

    public BaseAudio(int bufferSize, boolean allowDataSkip) {
        this.bufferSize = bufferSize;
        this.allowDataSkip = allowDataSkip;
        audioQueue = new ArrayBlockingQueue<>(bufferSize * BUFFER_COUNT);

        AudioFormat format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, bufferSize * 4);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        audio = new Thread(this::processAudio);
        audio.start();
    }

    private void processAudio() {
        try {
            while (running || !audioQueue.isEmpty()) {
                while (!audioQueue.isEmpty()) {
                    byte[] buffer = new byte[Math.min(bufferSize, audioQueue.size())];
                    for (int i = 0; i < buffer.length; i++) {
                        Byte data = allowDataSkip
                                ? audioQueue.poll()
                                : audioQueue.poll(10, TimeUnit.MILLISECONDS);
                        if (data != null) {
                            buffer[i] = data;
                        }
                    }
                    line.write(buffer, 0, buffer.length);
                }
                Thread.sleep(allowDataSkip ? 1 : 10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void writeTimes(int bite, int times) {
        byte signed = (byte) (bite - 128);
        try {
            for (int i = 0; i < times; i++) {
                if (allowDataSkip) {
                    if (!audioQueue.offer(signed)) {
                        audioQueue.clear();
                        audioQueue.offer(signed);
                    }
                } else {
                    audioQueue.put(signed);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        running = false;
        try {
            audio.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (line != null) {
            line.drain();
            line.close();
        }
    }
}