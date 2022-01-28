package spec.assembler;

import spec.Reg;
import spec.Registry;
import spec.WordMath;
import spec.assembler.command.system.NONE;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;
import static spec.Registry._PSW;
import static spec.Registry._SP;
import static spec.WordMath.*;

public abstract class Command {

    protected int[] indexes = new int[0x100];

    public static final List<String> BD =
            Arrays.asList("B", "D");

    public static final List<String> BDHSP =
            Arrays.asList("B", "D", "H", "SP");

    public static final List<String> BCDEHLMA =
            Arrays.asList("B", "C", "D", "E", "H", "L", "M", "A");

    public Command() {
        List<Integer> codes = codes();
        List<String> registers = registers();
        validate(codes.size(), registers.size());
        initIndexes(codes, registers);
    }

    public void initIndexes(List<Integer> codes, List<String> registers) {
        for (int i = 0; i < codes.size(); i++) {
            int div = (registers.size() == 0) ? 1 : registers.size();
            indexes[codes.get(i)] = i % div;
        }
    }

    public void validate(int codes, int registers) {
        if (this instanceof NONE
            || codes == 1                               // <COMMAND>
            || codes == registers                       // <COMMAND> R
            || (codes + 1) == registers * registers) {  // MOV R,R
            return;
        }

        throw new IllegalArgumentException(
                String.format("Command codes (%s) != registers (%s) for: %s",
                        codes, registers, this.getClass().getSimpleName()));
    }

    public Optional<String> parse(String command) {
        Pattern pattern = Pattern.compile(pattern());
        Matcher matcher = pattern.matcher(command);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        String[] params = new String[matcher.groupCount()];
        for (int i = 0; i < params.length; i++) {
            params[i] = matcher.group(i + 1);
        }
        return Optional.of(code(params).stream()
                .map(WordMath::hex8)
                .collect(joining(" ")));
    }

    public List<Integer> code(String... params) {
        List<Integer> result = new ArrayList<>(3);
        if (registers().isEmpty()){
            result.addAll(codes());
        } else {
            result.add(codes().get(registers().indexOf(params[0])));
        }
        if (size() == 3) {
            String bites = params.length == 1 ? params[0] : params[1];
            result.addAll(reverse(hex8(bites)));
        }
        if (size() == 2) {
            result.addAll(hex8(params[1]));
        }
        return result;
    }

    public List<String> registers() {
        return Arrays.asList();
    }

    public abstract List<Integer> codes();

    public abstract String pattern();

    public int size() {
        return 1;
    }

    public abstract int ticks();

    protected boolean disabled() {
        return false;
    }

    public boolean itsMe(List<Integer> bites) {
        // TODO сделать оптимальнее
        return !take(new ArrayList<>(bites)).isEmpty();
    }

    public List<Integer> take(List<Integer> bites) {
        return new LinkedList<Integer>(){{
            if (!bites.isEmpty()) {
                int bite = bites.get(0);
                for (int code : codes()) {
                    if (bite == code) {
                        for (int i = 0; i < Command.this.size(); i++) {
                            add(bites.remove(0));
                        }
                        break;
                    }
                }
            }
        }};
    }

    public String print(List<Integer> bites) {
        String result = pattern();
        switch (size()) {
            case 3: {
                String word = hex16(merge(bites.get(2), bites.get(1)));
                result = result.replace("(....)", word);
                break;
            }
            case 2: {
                String bite = hex8(bites.get(1));
                result = result.replace("(..)", bite);
                break;
            }
            default: {
                result = result.replace("(.)",  // 1 byte RST command
                        String.valueOf(rindex(bites.get(0))));
            }
        }
        if (!registers().isEmpty()) {
            String reg = registers().get(rindex(bites.get(0)));
            result = result
                    .replaceFirst(Pattern.quote("(B|D)"), reg)
                    .replaceFirst(Pattern.quote("(B|C|D|E|H|L|M|A)"), reg)
                    .replaceFirst(Pattern.quote("(B|D|H|SP)"), reg)
                    .replaceFirst(Pattern.quote("(B|D|H|PSW)"), reg)
                    .replaceFirst(Pattern.quote("(B|D)"), reg);
        }
        return result;
    }

    public abstract void apply(int command, Registry r);

    public int rindex(int command) {
        return indexes[command];
    }

    /**
     * 0 - BC, 1 - DE, 2 - HL, 3 - SP
     */
    public Reg rRR(int command, Registry r) {
        return r.reg16(rindex(command), _SP);
    }

    /**
     * 0 - BC, 1 - DE, 2 - HL, 3 - PSW(AF)
     */
    public Reg rRS(int command, Registry r) {
        return r.reg16(rindex(command), _PSW);
    }

    private String name() {
        return pattern().split(" ")[0];
    }
}