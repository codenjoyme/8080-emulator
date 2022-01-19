package spec.assembler;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;
import static spec.Memory.hex;

public abstract class Command {

    public Optional<String> parse(String command) {
        Pattern pattern = Pattern.compile(pattern());
        Matcher matcher = pattern.matcher(command);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        return Optional.of(Arrays.stream(code(matcher))
                .mapToObj(i -> hex(i))
                .collect(joining(" ")));
    }

    protected abstract int[] code(Matcher matcher);

    protected abstract String pattern();

    protected int size() {
        return 1;
    }
}
