package spec.assembler;

import spec.WordMath;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public abstract class Command {

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

    protected abstract List<Integer> code(Matcher matcher);

    protected abstract String pattern();

    protected int size() {
        return 1;
    }
}
