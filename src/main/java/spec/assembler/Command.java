package spec.assembler;

import spec.Registry;
import spec.WordMath;
import spec.assembler.command.NONE;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;
import static spec.WordMath.hex;
import static spec.WordMath.reverse;

public abstract class Command {

    private int[] indexes = new int[0x100];

    public static final List<String> BD = Arrays.asList("B", "D");
    public static final List<String> BDHSP = Arrays.asList("B", "D", "H", "SP");

    public Command() {
        List<Integer> codes = codes();
        List<String> registers = registers();
        validate(codes.size(), registers.size());

        for (int i = 0; i < codes.size(); i++) {
            indexes[codes.get(i)] = i;
        }
    }

    private void validate(int codes, int registers) {
        if (this instanceof NONE
            || codes == 1
            || codes == registers) {
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
                .map(WordMath::hex)
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
            result.addAll(reverse(hex(bites)));
        }
        if (size() == 2) {
            result.addAll(hex(params[1]));
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

    public abstract void apply(int command, Registry r);

    public int rindex(int command) {
        return indexes[command];
    }
}