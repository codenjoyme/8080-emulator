package svofski;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import spec.math.Bites;
import spec.stuff.FileAssert;
import spec.stuff.SmartAssert;

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
        return findAllFiles(APP_RESOURCES + "test/", ".asm");
    }

    public static List<Object[]> findAllFiles(String base, String type) {
        Path start = Paths.get(base);
        try (Stream<Path> stream = Files.walk(start)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(type))
                    .map(file -> new Object[]{ file.toString().replace("\\", "/").substring(base.length()) })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Before
    public void setup() {
        SmartAssert.setup();
    }

    @Before
    public void before() {
        asm = new Assembler();
        fileAssert = new FileAssert(TEST_RESOURCES + AssemblerTest.class.getSimpleName());
        program = fileAssert.read(new File(APP_RESOURCES + "/test/" + name));
        dir = new File(name).getParent();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
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

        Map<String, String> info = (Map) data.get("info");
        assertValue("info.json", asString(info));

        String memFile = APP_RESOURCES + "test/" + dir + "/" + info.get("binFileName");
        Bites bin = (Bites) data.get("bin");
        assertValue(memFile, bin.byteArray());

        Bites wave = (Bites) data.get("wave");
        assertValue("wave.wav", wave.byteArray());
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

    @Test
    public void simpleCompile() {
        // given
        String sourceCode =
                ";************************\n" +
                "; Prints 'HELLO WORLD\\n'\n" +
                ";************************\n" +
                "\n" +
                "        .project hello-world.mem\n" +
                "        .tape специалистъ-mon\n" +
                "        CPU     8080\n" +
                "        .ORG    00000h\n" +
                "\n" +
                "start:  LXI     H,hello\n" +
                "        CALL    bdos\n" +
                "        JMP     wboot      ; exit\n" +
                ";\n" +
                "hello:  DB      00Dh, 00Ah, 'HELLO WORLD', 00Dh, 00Ah, '$'\n" +
                ";\n" +
                "bdos    EQU     0C037h     ; PRINT CHAR PROCEDURE\n" +
                "wboot:  JMP     0C800h     ; BACK TO SYSTEM\n" +
                "end:";

        // when
        Bites result = asm.compile(sourceCode);

        // then
        assertEquals(
                "      00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F \n" +
                "0000: 21 09 00 CD 37 C0 C3 19 00 0D 0A 48 45 4C 4C 4F \n" +
                "0010: 20 57 4F 52 4C 44 0D 0A 24 C3 00 C8 \n",
                result.toString());
    }

    private String listAsString(List<List<String>> lists) {
        return lists.stream()
                .map(list -> list.stream().map(
                        it -> "<" + it + ">"
                ).collect(joining(", ", "{", "}")))
                .collect(joining("; ", "[", "]"));
    }

    private void assertValue(String name, byte[] result) {
        String path = (name.startsWith("src/") ? name : dir + "/" + name);
        fileAssert.check(name, path,
                file -> fileAssert.write(file, result));
    }

    private void assertValue(String name, String result) {
        assertValue(name, result.getBytes());
    }
}