package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spec.assembler.Assembler;
import spec.stuff.SmartAssert;

import static spec.stuff.SmartAssert.*;

public class AssemblerTest {

    private Assembler assembler;

    @Before
    public void setup() {
        assembler = new Assembler();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    @Test
    public void test() {
        assertEquals("LXI B,3412\n", assembler.dizAssembly("01 12 34"));
    }
}
