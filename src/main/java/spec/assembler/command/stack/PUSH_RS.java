package spec.assembler.command.stack;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 *                            RS=[B |D |H |PSW]
 * PUSH RS                       [C5|D5|E5|F5]
 *    (SP-1)<-R1  R1=[B|D|H|PSW]
 *    (SP-2)<-R2  R2=[C|E|L|A]
 *    SP<-SP-2
 */
// FIXME test me
public class PUSH_RS extends Command {

    private static final List<Integer> CODES = from(
            0xC5, 0xD5, 0xE5, 0xF5);

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
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = rRS(command, r).get();
        r.data().push16(r.rSP, word);
    }
}