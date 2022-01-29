package spec.assembler;

import spec.Reg;
import spec.Registry;
import spec.assembler.command.system.NONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spec.Registry._PSW;
import static spec.Registry._SP;
import static spec.WordMath.*;

public abstract class Command {

    public static final List<String> BD =
            Arrays.asList("B", "D");

    public static final List<String> BDHSP =
            Arrays.asList("B", "D", "H", "SP");

    public static final List<String> BDHPSW =
            Arrays.asList("B", "D", "H", "PSW");

    public static final List<String> BCDEHLMA =
            Arrays.asList("B", "C", "D", "E", "H", "L", "M", "A");

    protected int[] indexes = new int[0x100];
    private Pattern regexp;
    private String pattern;
    private String name;

    public Command() {
        List<Integer> codes = codes();
        List<String> registers = registers();
        validate(codes.size(), registers.size());
        initIndexes(codes, registers);
        name = getClass().getSimpleName().split("_")[0];
        pattern = name() + replace(operands());
        regexp = Pattern.compile(pattern());
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

    public String parse(String command) {
        Matcher matcher = regexp.matcher(command);
        if (!matcher.matches()) {
            return null;
        }
        String[] params = new String[matcher.groupCount()];
        for (int i = 0; i < params.length; i++) {
            params[i] = matcher.group(i + 1);
        }
        return hex(code(params));
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
            String param = registers().isEmpty() ? params[0] : params[1];
            result.addAll(hex8(param));
        }
        return result;
    }

    public List<String> registers() {
        return Arrays.asList();
    }

    public abstract List<Integer> codes();

    public String operands() {
        if (size() == 3) {
            return "4";
        }
        if (size() == 2) {
            return "2";
        }
        if (registers() == BD) {
            return "RD";
        }
        if (registers() == BDHSP) {
            return "RR";
        }
        if (registers() == BDHPSW) {
            return "RS";
        }
        if (registers() == BCDEHLMA) {
            return "R";
        }
        return "";
    }

    public String name() {
        return name;
    }

    public String pattern() {
        return pattern;
    }

    private String replace(String operands) {
        if (operands.isEmpty()) {
            return "";
        }

        return " " + operands
                .replace("4", "(....)")
                .replace("2", "(..)")
                .replace("RD", "(B|D)")
                .replace("RR", "(B|D|H|SP)")
                .replace("RS", "(B|D|H|PSW)")
                .replace("R", "(B|C|D|E|H|L|M|A)");
    }

    public int size() {
        return 1;
    }

    public abstract int ticks();

    protected boolean disabled() {
        return false;
    }

    public List<Integer> take(int[] bites, int index) {
        List<Integer> result = new LinkedList<>();
        for (int i = index; i < index + size(); i++) {
            result.add(bites[i]);
        }
        return result;
    }

    public String print(List<Integer> bites) {
        String result = pattern();
        switch (size()) {
            case 3: {
                String word = hex16(merge(bites.get(2), bites.get(1)));
                result = replace(result, "(....)", word);
                break;
            }
            case 2: {
                String bite = hex8(bites.get(1));
                result = replace(result, "(..)", bite);
                break;
            }
            default: {
                result = replace(result, "(.)",  // 1 byte RST command
                        String.valueOf(rindex(bites.get(0))));
            }
        }
        if (!registers().isEmpty()) {
            String reg = registers().get(rindex(bites.get(0)));
            result = replace(result, "(B|D)", reg);
            result = replace(result, "(B|C|D|E|H|L|M|A)", reg);
            result = replace(result, "(B|D|H|SP)", reg);
            result = replace(result, "(B|D|H|PSW)", reg);
            result = replace(result, "(B|D)", reg);
        }
        return result;
    }

    private String replace(String source, String search, String replacement) {
        int index = source.indexOf(search);
        if (index == -1) {
            return source;
        }
        return source.substring(0, index)
                + replacement
                + source.substring(index + search.length());
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

}