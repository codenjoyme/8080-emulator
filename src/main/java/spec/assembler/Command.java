package spec.assembler;

public interface Command {

    int[] code(int[] data);

    String name();
}
