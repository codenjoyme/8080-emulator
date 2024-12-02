package spec.math;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CollisionFreeTickEventProcessorTest {

    @Test
    public void test() {
        // given
        CollisionFreeTickEventProcessor<String> processor = new CollisionFreeTickEventProcessor<>();
        processor.addEvent(1, "string1");
        processor.addEvent(11, "string2");
        processor.addEvent(111, "string3");
        processor.addEvent(1111, "string4");
        processor.addEvent(11111, "string5");
        processor.addEvent(111111, "string6");
        processor.addEvent(1111111, "string7");
        processor.addEvent(11111111, "string8");
        processor.addEvent(111111111, "string9");
        processor.addEvent(1111111111, "string10");

        // when then
        assertEquals("string1", processor.getEvent(1));
        assertEquals("string2", processor.getEvent(11));
        assertEquals("string3", processor.getEvent(111));
        assertEquals("string4", processor.getEvent(1111));
        assertEquals("string5", processor.getEvent(11111));
        assertEquals("string6", processor.getEvent(111111));
        assertEquals("string7", processor.getEvent(1111111));
        assertEquals("string8", processor.getEvent(11111111));
        assertEquals("string9", processor.getEvent(111111111));
        assertEquals("string10", processor.getEvent(1111111111));

        Set<Integer> ticks = processor.getTicks();
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            if (ticks.contains(i)) {
                continue;
            }
            assertEquals(null, processor.getEvent(i));
        }
    }

}