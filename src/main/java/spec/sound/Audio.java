package spec.sound;

public interface Audio {

    void write(int bite);

    void tick();

    void close();

    void allowDataSkip(boolean allow);
}
