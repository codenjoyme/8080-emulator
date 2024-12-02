package spec.assembler;

import spec.Reg;
import spec.Registry;
import spec.math.WordMath;
import spec.assembler.command.system.NONE;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spec.Registry._PSW;
import static spec.Registry._SP;
import static spec.math.WordMath.*;

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

    public static List<Integer> from(Integer... input) {
        return Arrays.asList(input);
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

    public int[] parse(String command) {
        Matcher matcher = regexp.matcher(command);
        if (!matcher.matches()) {
            return null;
        }
        String[] params = new String[matcher.groupCount()];
        for (int i = 0; i < params.length; i++) {
            params[i] = matcher.group(i + 1);
        }
        return code(params);
    }

    public int[] code(String... params) {
        int[] result = new int[size()];
        if (registers().isEmpty()) {
            List<Integer> codes = codes();
            result[0] = codes.get(0);
        } else {
            result[0] = codes().get(registers().indexOf(params[0]));
        }
        if (size() == 3) {
            String bites = params.length == 1 ? params[0] : params[1];
            int[] arr = hex8(bites);
            result[2] = arr[0];
            result[1] = arr[1];
        }
        if (size() == 2) {
            String param = registers().isEmpty() ? params[0] : params[1];
            int[] arr = hex8(param);
            result[1] = arr[0];
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

    public abstract int ticks(int command);

    protected boolean disabled() {
        return false;
    }

    public int[] take(int[] bites, int index) {
        int[] result = new int[size()];
        for (int i = index; i < index + size(); i++) {
            try {
                result[i - index] = bites[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException(
                        String.format("Needed %s bites, but only %s found for command %s: [%s]",
                                size(), result.length, name,
                                WordMath.hex(bites)), e);
            }
        }
        return result;
    }

    public String print(int[] bites, boolean canonical) {
        String result = pattern();
        switch (size()) {
            case 3: {
                String word = hex16(merge(bites[2], bites[1]));
                if (canonical) {
                    word = '0' + word + 'h';
                }
                result = replace(result, "(....)", word);
                break;
            }
            case 2: {
                String bite = hex8(bites[1]);
                if (canonical) {
                    bite = '0' + bite + 'h';
                }
                result = replace(result, "(..)", bite);
                break;
            }
            default: {
                result = replace(result, "(.)",  // 1 byte RST command
                        String.valueOf(rindex(bites[0])));
            }
        }
        if (!registers().isEmpty()) {
            String reg = registers().get(rindex(bites[0]));
            result = replace(result, "(B|D)", reg);
            result = replace(result, "(B|C|D|E|H|L|M|A)", reg);
            result = replace(result, "(B|D|H|SP)", reg);
            result = replace(result, "(B|D|H|PSW)", reg);
            result = replace(result, "(B|D)", reg);
        }
        return result;
    }

    protected String replace(String source, String search, String replacement) {
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

    public static final int iB = 0;
    public static final int iC = 1;
    public static final int iD = 2;
    public static final int iE = 3;
    public static final int iH = 4;
    public static final int iL = 5;
    public static final int iM = 6;
    public static final int iA = 7;

    public static final int iBC = 0;
    public static final int iDE = 1;
    public static final int iHL = 2;
    public static final int iSP = 3;
    public static final int iPSW = 3;

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

    public boolean isJump() {
        return getClass().getPackage().getName().contains("jump");
    }

    public boolean isCall() {
        return getClass().getPackage().getName().contains("call");
    }
}