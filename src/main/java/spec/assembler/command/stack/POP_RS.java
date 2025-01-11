package spec.assembler.command.stack;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 *                           RS=[B |D |H |PSW]
 * POP RS                       [C1|D1|E1|F1]
 *    R2<-(SP+1) R2=[C|E|L|A]
 *    R1<-(SP)   R1=[B|D|H|PSW]
 *    SP<-SP+2
 */
// FIXME test me
public class POP_RS extends Command {

    private static final List<Integer> CODES = from(
            0xC1, 0xD1, 0xE1, 0xF1);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHPSW;
    }

    @Override
    public int ticks(int command) {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = r.data().pop16(r.rSP);
        rRS(command, r).set(word);
    }
}