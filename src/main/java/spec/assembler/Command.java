package spec.assembler;

import spec.WordMath;

import java.util.LinkedList;
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

    protected List<Integer> code(Matcher matcher) {
        return codes();
    }

    public abstract List<Integer> codes();

    protected abstract String pattern();

    protected int size() {
        return 1;
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
}