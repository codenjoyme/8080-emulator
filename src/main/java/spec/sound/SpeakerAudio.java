package spec.sound;

import static spec.Constants.AUDIO_BYTES_PER_TICK;

public class SpeakerAudio extends BaseAudio implements Audio {

    private int current;

    public SpeakerAudio() {
        super(1024);
    }

    @Override
    public void write(int bite) {
        current = bite;
    }

    @Override
    public void tick() {
        writeTimes(current, AUDIO_BYTES_PER_TICK);
    }
}