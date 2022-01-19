package spec.assembler;

public interface Command {

    int[] code(int[] data);

    String pattern();

    default int size() {
        return 1;
    }
}
