package spec.sound;
import javax.sound.sampled.*;

// Thanks to the https://stackoverflow.com/a/32891220
public class Audio {

    public static final int CPU_SAMPLE_RATE = 2500;

    private byte[] buffer = new byte[10000];
    private int index = 0;

    public void play() {
        try {
            // select audio format parameters
            AudioFormat af = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            // prepare audio output
            line.open(af, buffer.length);
            line.start();

            // output wave form repeatedly
            line.write(buffer, 0, buffer.length);

            // shut down audio
            line.drain();
            line.stop();
            line.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void write(int bite) {
        buffer[index++] = (byte) bite;
        if (index >= buffer.length) {
            index = 0;
            play();
        }

    }
}