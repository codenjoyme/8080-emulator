package spec.sound;
import javax.sound.sampled.*;
import java.util.Random;

// Thanks to the https://stackoverflow.com/a/32891220
public class Audio {

    private byte[] buffer = new byte[100];
    private int index = 0;

    public void play() {
        try {
            // select audio format parameters
            AudioFormat af = new AudioFormat(2000, 8, 1, false, false);
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