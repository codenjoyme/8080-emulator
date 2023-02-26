package spec.sound;

import javax.sound.sampled.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class OldAudio implements Audio {

    public static final int CPU_SAMPLE_RATE = 2500;

    private final SourceDataLine line;
    private final AudioFormat format;

    private byte[] buffer = new byte[10000];
    private int index = 0;

    public OldAudio() {
        try {
            // select audio format parameters
            format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(int bite) {
        buffer[index++] = (byte) bite;
        if (index >= buffer.length) {
            index = 0;
            play();
        }
    }

    @Override
    public void tick() {
        // do nothing
    }

    @Override
    public void play() {
        // prepare audio output
        try {
            line.open(format, buffer.length);
            line.start();
            // output wave form repeatedly
            line.write(buffer, 0, buffer.length);
            line.drain();
            line.stop();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}