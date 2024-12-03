package svofski;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.stuff.FileAssert;

import java.util.Arrays;
import java.util.stream.Collectors;

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

        fileAssert = new FileAssert(TEST_RESOURCES + test.getMethodName());
        fileAssert.removeTestsData();
    }

    @Test
    public void splitParts() {
        // given
        String[] lines = PROGRAM.split("\n");

        // when
        String result = Arrays.stream(lines)
                .map(asm::splitParts)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        // then
        assertValue("splitParts", result);
    }

    private void assertValue(String name, String result) {
        fileAssert.check(name, AssemblerTest.class.getSimpleName() + "/" + name + ".log",
                file -> write(file, result));
    }

}