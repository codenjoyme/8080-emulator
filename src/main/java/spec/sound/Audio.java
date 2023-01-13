package spec.sound;
import javax.sound.sampled.*;

// Thanks to the https://stackoverflow.com/a/32891220
public class Audio {

    public static final int CPU_SAMPLE_RATE = 2500;

    private final SourceDataLine line;
    private final AudioFormat format;

    private byte[] buffer = new byte[100];
    private int index = 0;


    public Audio() {
        try {
            // select audio format parameters
            format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            // prepare audio output
            line.open(format, buffer.length);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(int bite) {
        buffer[index++] = (byte) bite;
        if (index >= buffer.length) {
            index = 0;
            play();
        }
    }

    public void play() {
        // output wave form repeatedly
        line.write(buffer, 0, buffer.length);
    }
}