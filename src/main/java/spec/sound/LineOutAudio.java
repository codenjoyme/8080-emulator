package spec.sound;

import static spec.Constants.LINE_OUT_AUDIO_BYTES_PER_WRITE;

public class LineOutAudio extends BaseAudio implements Audio {

    public LineOutAudio() {
        super(1024);
    }

    @Override
    public void write(int bite) {
        writeTimes(bite, LINE_OUT_AUDIO_BYTES_PER_WRITE);
    }

    @Override
    public void tick() {
        // do nothing
    }
}