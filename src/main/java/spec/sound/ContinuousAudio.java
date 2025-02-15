package spec.sound;

import javax.sound.sampled.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ContinuousAudio implements Audio {

    private static final int BUFFER_SIZE = 512; // Размер буфера для воспроизведения
    private final SourceDataLine line;
    private final AudioFormat format;
    private final ExecutorService executor;

    public ContinuousAudio() {
        try {
            format = new AudioFormat(2500, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, BUFFER_SIZE);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        line.start();
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void write(int bite) {
        byte[] data = new byte[]{(byte) bite};
        executor.execute(() -> line.write(data, 0, 1));
    }

    @Override
    public void tick() {
        // не требуется для непрерывного вывода
    }

    @Override
    public void play() {
        // не требуется для непрерывного вывода
    }

    public void stop() {
        executor.shutdown();
        line.drain();
        line.stop();
        line.close();
    }
}
