package spec.sound;

public class LineOutAudio extends BaseAudio implements Audio {

    public LineOutAudio() {
        super(1024);
    }

    @Override
    public void write(int bite) {
        writeTimes(bite, 18);
    }

    @Override
    public void tick() {
        // do nothing
    }
}