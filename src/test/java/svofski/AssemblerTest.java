package svofski;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import spec.stuff.FileAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static spec.IntegrationTest.APP_RESOURCES;
import static spec.IntegrationTest.TEST_RESOURCES;
import static spec.stuff.FileAssert.*;

@RunWith(Parameterized.class)
public class AssemblerTest {

    private Assembler asm;

    @Rule
    public TestName test = new TestName();
    private FileAssert fileAssert;

    private String program;
    private String name;
    private String dir;

    public AssemblerTest(String name) {
        this.name = name;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String base = APP_RESOURCES + "/test/";
        Path start = Paths.get(base);
        try (Stream<Path> stream = Files.walk(start)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".asm"))
                    .map(file -> new Object[]{file.toString().substring(base.length() - 1)})
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Before
    public void setup() {
        asm = new Assembler();
        fileAssert = new FileAssert(TEST_RESOURCES + AssemblerTest.class.getSimpleName());
        program = read(new File(APP_RESOURCES + "/test/" + name));
        dir = new File(name).getParent();
    }

    @Test
    public void assemble() {
        Map<String, Object> data = asm.process(program);

        assertValue("memory.json", asString(data.get("mem")));
        assertValue("hex.json", asString(data.get("hex")));
        assertValue("gutter.json", asString(data.get("gutter")));
        assertValue("listing.json", asString(data.get("listing")));
        assertValue("errors.json", asString(data.get("errors")));
        assertValue("xref.json", asString(data.get("xref")));
        assertValue("labels.json", asString(data.get("labels")));
        assertValue("info.json", asString(data.get("info")));
    }

    @Test
    public void resolveNumber() {
        String name = dir + "/method/resolveNumber.json";
        fileAssert.checkJson(name,
                (input, fields) -> asm.resolveNumber((String) input.get(0)));
    }

    @Test
    public void evaluateExpression2() {
        String name = dir + "/method/evaluateExpression2.json";
        fileAssert.checkJson(name,
                (input, getField) -> {
                    Map labels = getField.apply("labels");

                    // TODO setup Gson to convert Double to Integer automatically
                    labels.forEach((key, value) -> {
                        if (value instanceof Double) {
                            labels.put(key, ((Double) value).intValue());
                        }
                    });

                    asm.labels = labels;
                    asm.xref = new HashMap<>();

                    return asm.evaluateExpression2(
                            (String) input.get(0),
                            ((Double) input.get(1)).intValue(),
                            ((Double) input.get(2)).intValue());
                });
    }

    @Test
    public void splitParts() {
        // given
        String[] lines = program.split("\n");

        // when then
        assertValue("splitParts.log", splitParts(lines));
    }

    private String splitParts(String[] lines) {
        return Arrays.stream(lines)
                .map(asm::splitParts)
                .map(this::listAsString)
                .collect(joining("\n"));
    }

    @Test
    public void splitParts_cornerCases() {
        assertEquals("[{<DB>, <00Dh,>, <00Ah,>, <'VERSION 1.0  (C) 1980'>, <,>, <00Dh,>, <00Ah,>, <'$'>}]",
                listAsString(asm.splitParts("        DB      00Dh, 00Ah, 'VERSION 1.0  (C) 1980', 00Dh, 00Ah, '$'\n")));

        assertEquals("[{<hello:>, <DB>, <00Dh,>, <00Ah,>, <'MICROCOSM ASSOCIATES'>}]",
                listAsString(asm.splitParts("hello:  DB      00Dh, 00Ah, 'MICROCOSM ASSOCIATES'\n")));
    }

    private String listAsString(List<List<String>> lists) {
        return lists.stream()
                .map(list -> list.stream().map(
                        it -> "<" + it + ">"
                ).collect(joining(", ", "{", "}")))
                .collect(joining("; ", "[", "]"));
    }

    private void assertValue(String name, String result) {
        fileAssert.check(name, dir + "/" + name,
                file -> write(file, result));
    }
}