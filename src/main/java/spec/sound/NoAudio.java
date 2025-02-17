package spec.sound;

public class NoAudio implements Audio {

    @Override
    public void write(int bite) {
        // do nothing
    }

    @Override
    public void tick() {
        // do nothing
    }

    @Override
    public void close() {
        // do nothing
    }
}
