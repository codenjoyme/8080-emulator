package spec.sound;

import javax.sound.sampled.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;

public class OldAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = 1024;

    private SourceDataLine line;
    private final AudioFormat format;
    private final ArrayBlockingQueue<Byte> audioQueue = new ArrayBlockingQueue<>(1024);

    public OldAudio() {
        try {
            format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, BUFFER_SIZE);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        line.start();
    }

    @Override
    public void write(int bite) {
        byte signed = (byte) (bite - 128);
        for (int i = 0; i < 18; i++) {
            audioQueue.offer(signed);
        }
    }

    @Override
    public void tick() {
        if (line == null) return;

        if (!audioQueue.isEmpty()) {
            byte[] buffer = new byte[Math.min(BUFFER_SIZE, audioQueue.size())];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = audioQueue.poll();
            }
            line.write(buffer, 0, buffer.length);
        }
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