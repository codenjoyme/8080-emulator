package spec.assembler;

import spec.Registry;
import spec.WordMath;
import spec.assembler.command.NONE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public abstract class Command {

    private int[] mapper = new int[0x100];

    public Command() {
        List<Integer> codes = codes();
        List<String> registers = registers();
        validate(codes.size(), registers.size());

        for (int i = 0; i < codes.size(); i++) {
            mapper[codes.get(i)] = i;
        }
    }

    private void validate(int codes, int registers) {
        if (this instanceof NONE
            || codes == 1
            || codes == registers) {
            return;
        }

        throw new IllegalArgumentException(
                String.format("Command codes (%s) != registers (%s)",
                        codes, registers));
    }

    public Optional<String> parse(String command) {
        Pattern pattern = Pattern.compile(pattern());
        Matcher matcher = pattern.matcher(command);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        return Optional.of(code(matcher).stream()
                .map(WordMath::hex)
                .collect(joining(" ")));
    }

    public List<Integer> code(Matcher matcher) {
        return codes();
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
        return mapper[command];
    }
}