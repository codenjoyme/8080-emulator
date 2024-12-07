package spec;

import org.junit.Test;

import static org.junit.Assert.*;
import static spec.Constants.*;

public class ConstantsTest {

    @Test
    public void testConstants() {
        assertEquals("[9000:BFFF]" , SCREEN.toString());
        assertEquals("[C000:F7FF]" , ROM.toString());
        assertEquals("[F800:FFFE]" , PORTS.toString());
    }

}