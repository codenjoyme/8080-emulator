package spec.assembler.command.system;

import java.util.List;

public class NONE extends NOP {

    private static final List<Integer> CODES = from(
            0x08, 0x10, 0x18, 0x20, 0x28, 0x30, 0x38,
            0xD9, 0xCB, 0xDD, 0xED, 0xFD);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 1; // TODO если тут поставить 0, тогда PC счетчик будет неправильно бегать, на 1 больше
    }
}
