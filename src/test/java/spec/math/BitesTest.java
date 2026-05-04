package spec.math;

import org.junit.Test;
import spec.Range;

import static org.junit.Assert.*;

public class BitesTest {

    // --- Constructors / factories ---

    @Test
    public void constructor_with_length_creates_zero_filled_array() {
        Bites b = new Bites(4);
        assertEquals(4, b.size());
        for (int i = 0; i < 4; i++) {
            assertEquals(0, b.get(i));
        }
    }

    @Test
    public void constructor_with_no_args_creates_empty() {
        Bites b = new Bites();
        assertEquals(0, b.size());
    }

    @Test
    public void of_varargs_creates_bites_with_given_values() {
        Bites b = Bites.of(0x01, 0xAB, 0xFF);
        assertEquals(3, b.size());
        assertEquals(0x01, b.get(0));
        assertEquals(0xAB, b.get(1));
        assertEquals(0xFF, b.get(2));
    }

    @Test
    public void of_string_parses_hex_bytes() {
        Bites b = Bites.of("01 AB FF");
        assertEquals(3, b.size());
        assertEquals(0x01, b.get(0));
        assertEquals(0xAB, b.get(1));
        assertEquals(0xFF, b.get(2));
    }

    @Test
    public void of_string_parses_without_spaces() {
        Bites b = Bites.of("01ABFF");
        assertEquals(3, b.size());
        assertEquals(0x01, b.get(0));
        assertEquals(0xAB, b.get(1));
        assertEquals(0xFF, b.get(2));
    }

    @Test
    public void of_string_parses_multiline_hex() {
        Bites b = Bites.of("01 02\n03 04");
        assertEquals(4, b.size());
        assertEquals(0x01, b.get(0));
        assertEquals(0x02, b.get(1));
        assertEquals(0x03, b.get(2));
        assertEquals(0x04, b.get(3));
    }

    @Test
    public void ofClean_strips_header_and_address_prefix() {
        // Format: first line is header, each subsequent line starts with 6 chars (addr + space)
        String dump =
                "      00 01 02 03\n" +
                "0000: 10 20 30 40";
        Bites b = Bites.ofClean(dump);
        assertEquals(4, b.size());
        assertEquals(0x10, b.get(0));
        assertEquals(0x20, b.get(1));
        assertEquals(0x30, b.get(2));
        assertEquals(0x40, b.get(3));
    }

    // --- get / set ---

    @Test
    public void get_and_set_single_element() {
        Bites b = new Bites(3);
        b.set(1, 0xAB);
        assertEquals(0xAB, b.get(1));
        assertEquals(0x00, b.get(0));
        assertEquals(0x00, b.get(2));
    }

    @Test
    public void set_bites_copies_all_elements() {
        Bites src = Bites.of(0x01, 0x02, 0x03);
        Bites dst = new Bites(3);
        dst.set(src);
        assertEquals(0x01, dst.get(0));
        assertEquals(0x02, dst.get(1));
        assertEquals(0x03, dst.get(2));
    }

    @Test
    public void set_bites_at_offset() {
        Bites src = Bites.of(0xAA, 0xBB);
        Bites dst = new Bites(4);
        dst.set(1, src);
        assertEquals(0x00, dst.get(0));
        assertEquals(0xAA, dst.get(1));
        assertEquals(0xBB, dst.get(2));
        assertEquals(0x00, dst.get(3));
    }

    @Test
    public void set_range_copies_matching_slice() {
        Bites src = Bites.of(0x11, 0x22, 0x33);
        Bites dst = new Bites(3);
        Range r = new Range(0, 2); // indices 0..2
        dst.set(r, src);
        assertEquals(0x11, dst.get(0));
        assertEquals(0x22, dst.get(1));
        assertEquals(0x33, dst.get(2));
    }

    // --- array / byteArray ---

    @Test
    public void array_returns_slice_by_range() {
        Bites b = Bites.of(0x10, 0x20, 0x30, 0x40);
        Bites slice = b.array(new Range(1, 2)); // indices 1..2
        assertEquals(2, slice.size());
        assertEquals(0x20, slice.get(0));
        assertEquals(0x30, slice.get(1));
    }

    @Test
    public void array_no_args_returns_full_copy() {
        Bites b = Bites.of(0xAA, 0xBB, 0xCC);
        Bites copy = b.array();
        assertEquals(b, copy);
    }

    @Test
    public void byteArray_returns_signed_bytes_in_range() {
        Bites b = Bites.of(0x01, 0x80, 0xFF);
        byte[] arr = b.byteArray(new Range(0, 2));
        assertEquals(3, arr.length);
        assertEquals((byte) 0x01, arr[0]);
        assertEquals((byte) 0x80, arr[1]); // signed: -128
        assertEquals((byte) 0xFF, arr[2]); // signed: -1
    }

    @Test
    public void byteArray_no_args_returns_all() {
        Bites b = Bites.of(0x01, 0x02, 0x03);
        byte[] arr = b.byteArray();
        assertEquals(3, arr.length);
        assertEquals(1, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(3, arr[2]);
    }

    // --- subset / cutFrom ---

    @Test
    public void subset_returns_first_n_elements() {
        Bites b = Bites.of(0x01, 0x02, 0x03, 0x04);
        Bites sub = b.subset(2);
        assertEquals(2, sub.size());
        assertEquals(0x01, sub.get(0));
        assertEquals(0x02, sub.get(1));
    }

    @Test
    public void cutFrom_returns_suffix_from_index() {
        Bites b = Bites.of(0xAA, 0xBB, 0xCC, 0xDD);
        Bites cut = b.cutFrom(2);
        assertEquals(2, cut.size());
        assertEquals(0xCC, cut.get(0));
        assertEquals(0xDD, cut.get(1));
    }

    // --- iterator ---

    @Test
    public void iterator_visits_all_elements_in_order() {
        Bites b = Bites.of(0x01, 0x02, 0x03);
        int[] expected = {0x01, 0x02, 0x03};
        int i = 0;
        for (int val : b) {
            assertEquals(expected[i++], val);
        }
        assertEquals(3, i);
    }

    // --- equals / hashCode ---

    @Test
    public void equals_returns_true_for_same_content() {
        Bites a = Bites.of(0x01, 0x02, 0x03);
        Bites b = Bites.of(0x01, 0x02, 0x03);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void equals_returns_false_for_different_content() {
        Bites a = Bites.of(0x01, 0x02, 0x03);
        Bites b = Bites.of(0x01, 0x02, 0x04);
        assertNotEquals(a, b);
    }

    @Test
    public void equals_returns_false_for_different_length() {
        Bites a = Bites.of(0x01, 0x02);
        Bites b = Bites.of(0x01, 0x02, 0x03);
        assertNotEquals(a, b);
    }

    @Test
    public void equals_bites_method_works() {
        Bites a = Bites.of(0xAA, 0xBB);
        Bites b = Bites.of(0xAA, 0xBB);
        assertTrue(a.equals(b));
    }

    // --- toString ---

    @Test
    public void toString_with_header_and_address() {
        Bites b = Bites.of(0x01, 0x02, 0x03);
        String s = b.toString(true, true);
        assertTrue(s.contains("0000:")); // address line
        assertTrue(s.contains("01 02 03"));
    }

    @Test
    public void toString_without_header_without_address() {
        Bites b = Bites.of(0xAB, 0xCD);
        String s = b.toString(false, false);
        assertEquals("AB CD", s);
    }

    @Test
    public void toString_default_includes_header_and_address() {
        Bites b = Bites.of(0xFF);
        String s = b.toString();
        assertTrue(s.contains("FF"));
        assertTrue(s.contains("0000:"));
    }

    // --- BitesReader ---

    @Test
    public void bitesReader_reads_chunks_in_order() {
        Bites b = Bites.of(0x01, 0x02, 0x03, 0x04, 0x05);
        Bites.BitesReader reader = b.new BitesReader();

        assertTrue(reader.hasNext());
        Bites first = reader.read(2);
        assertEquals(Bites.of(0x01, 0x02), first);

        assertTrue(reader.hasNext());
        Bites second = reader.read(3);
        assertEquals(Bites.of(0x03, 0x04, 0x05), second);

        assertFalse(reader.hasNext());
    }

    @Test
    public void bitesReader_read_zero_returns_empty() {
        Bites b = Bites.of(0x01, 0x02);
        Bites.BitesReader reader = b.new BitesReader();
        Bites empty = reader.read(0);
        assertEquals(0, empty.size());
        assertTrue(reader.hasNext()); // still has data
    }

    @Test(expected = IllegalArgumentException.class)
    public void bitesReader_read_beyond_bounds_throws() {
        Bites b = Bites.of(0x01);
        Bites.BitesReader reader = b.new BitesReader();
        reader.read(5); // more than available
    }
}
