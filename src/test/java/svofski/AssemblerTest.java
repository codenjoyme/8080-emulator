package svofski;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.stuff.FileAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static spec.IntegrationTest.TEST_RESOURCES;
import static spec.stuff.FileAssert.write;
import static svofski.Main.PROGRAM;

public class AssemblerTest {

    private Assembler asm;

    @Rule
    public TestName test = new TestName();
    private FileAssert fileAssert;

    @Before
    public void setup() {
        asm = new Assembler();

        fileAssert = new FileAssert(TEST_RESOURCES + AssemblerTest.class.getSimpleName());
        fileAssert.removeTestsData();
    }

    @Test
    public void splitParts() {
        // given
        String[] lines = PROGRAM.split("\n");

        // when
        String result = Arrays.stream(lines)
                .map(asm::splitParts)
                .map(this::asString)
                .collect(Collectors.joining("\n"));

        // then
        assertValue("splitParts", result);
    }

    @Test
    public void splitParts_cornerCases() {
        assertEquals("[{<DB>, <00Dh,>, <00Ah,>, <'VERSION 1.0  (C) 1980'>, <,>, <00Dh,>, <00Ah,>, <'$'>}]",
                asString(asm.splitParts("        DB      00Dh, 00Ah, 'VERSION 1.0  (C) 1980', 00Dh, 00Ah, '$'\n")));

        assertEquals("[{<hello:>, <DB>, <00Dh,>, <00Ah,>, <'MICROCOSM ASSOCIATES'>}]",
                asString(asm.splitParts("hello:  DB      00Dh, 00Ah, 'MICROCOSM ASSOCIATES'\n")));
    }

    private String asString(List<List<String>> lists) {
        return lists.stream()
                .map(list -> list.stream().map(
                        it -> "<" + it + ">"
                ).collect(Collectors.joining(", ", "{", "}")))
                .collect(Collectors.joining("; ", "[", "]"));
    }

    private void assertValue(String name, String result) {
        fileAssert.check(name, name + ".log",
                file -> write(file, result));
    }

}