package spec.sound;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

// Thanks to the https://stackoverflow.com/a/32891220
public class RawAudioPlay {

    public static void main(String[] args) {
        try {
            // select audio format parameters
            AudioFormat af = new AudioFormat(2000, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            // generate some PCM data (a sine wave for simplicity)
            byte[] buffer = new byte[10000];
            new Random().nextBytes(buffer);

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

}