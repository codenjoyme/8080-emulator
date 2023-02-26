package spec.sound;

import javax.sound.sampled.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NewAudio implements Runnable, Audio {

    public static final int CPU_SAMPLE_RATE = 2500;

    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private SourceDataLine line;
    private AudioFormat format;

    private byte[] buffer = new byte[100];
    private int index = 0;
    private int state = 0;

    public NewAudio() {
        try {
            format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(int bite) {
        state = bite;
    }

    @Override
    public void tick() {
        buffer[index++] = (byte) state;
        if (index >= buffer.length) {
            index = 0;
            play();
        }
    }

    @Override
    public void run() {
        try {
            line.open(format, buffer.length);
            line.start();
            line.write(buffer, 0, buffer.length);
            line.drain();
            line.stop();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void play() {
        executor.submit(this);
    }
}