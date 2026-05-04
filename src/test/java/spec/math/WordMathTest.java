package spec.math;

import org.junit.Test;
import spec.Range;

import static org.junit.Assert.*;
import static spec.math.WordMath.*;

public class WordMathTest {

    // --- lo / hi ---

    @Test
    public void lo_extracts_low_byte() {
        assertEquals(0x34, lo(0x1234));
        assertEquals(0xFF, lo(0x00FF));
        assertEquals(0x00, lo(0xFF00));
    }

    @Test
    public void hi_extracts_high_byte() {
        assertEquals(0x12, hi(0x1234));
        assertEquals(0x00, hi(0x00FF));
        assertEquals(0xFF, hi(0xFF00));
    }

    // --- loh / hih (nibbles) ---

    @Test
    public void loh_extracts_low_nibble() {
        assertEquals(0xA, loh(0xBA));
        assertEquals(0x0, loh(0xF0));
        assertEquals(0xF, loh(0x0F));
    }

    @Test
    public void hih_extracts_high_nibble() {
        assertEquals(0xB, hih(0xBA));
        assertEquals(0xF, hih(0xF0));
        assertEquals(0x0, hih(0x0F));
    }

    // --- splitBites / joinBites ---

    @Test
    public void splitBites_splits_two_bytes() {
        Bites b = splitBites(0x1234, 2);
        assertEquals(2, b.size());
        assertEquals(0x34, b.get(0)); // low byte first
        assertEquals(0x12, b.get(1)); // high byte second
    }

    @Test
    public void splitBites_splits_four_bytes() {
        Bites b = splitBites(0x12345678L, 4);
        assertEquals(4, b.size());
        assertEquals(0x78, b.get(0));
        assertEquals(0x56, b.get(1));
        assertEquals(0x34, b.get(2));
        assertEquals(0x12, b.get(3));
    }

    @Test
    public void joinBites_reconstructs_word() {
        Bites b = Bites.of(0x34, 0x12); // lo at 0, hi at 1
        long result = joinBites(b, new Range(0, 1));
        assertEquals(0x1234L, result);
    }

    @Test
    public void splitBites_then_joinBites_round_trip() {
        long original = 0xABCD;
        Bites b = splitBites(original, 2);
        long result = joinBites(b, new Range(0, 1));
        assertEquals(original, result);
    }

    // --- merge ---

    @Test
    public void merge_two_bytes_into_word() {
        assertEquals(0x1234, merge(0x12, 0x34));
        assertEquals(0xFF00, merge(0xFF, 0x00));
        assertEquals(0x00FF, merge(0x00, 0xFF));
    }

    @Test
    public void merge_bites_at_offset() {
        Bites b = Bites.of(0xAA, 0x34, 0x12); // offset 1: hi=0x12, lo=0x34
        assertEquals(0x1234, merge(b, 1));
    }

    // --- word / wordShift ---

    @Test
    public void word_masks_to_16_bits() {
        assertEquals(0x0000, word(0x10000));
        assertEquals(0xFFFF, word(0xFFFF));
        assertEquals(0x1234, word(0x1234));
    }

    @Test
    public void wordShift_handles_negative() {
        assertEquals(0xFFFF, wordShift(-1));
        assertEquals(0x0000, wordShift(0));
    }

    @Test
    public void wordShift_handles_overflow() {
        assertEquals(0x0000, wordShift(0x10000));
        assertEquals(0x0001, wordShift(0x10001));
    }

    @Test
    public void wordShift_leaves_valid_values_unchanged() {
        assertEquals(0x1234, wordShift(0x1234));
        assertEquals(0x7FFF, wordShift(0x7FFF));
    }

    // --- hex8 (int) ---

    @Test
    public void hex8_formats_byte_as_two_hex_chars() {
        assertEquals("00", hex8(0x00));
        assertEquals("FF", hex8(0xFF));
        assertEquals("0A", hex8(0x0A));
        assertEquals("AB", hex8(0xAB));
    }

    @Test
    public void hex8_pads_single_digit_with_leading_zero() {
        assertEquals("01", hex8(0x01));
        assertEquals("0F", hex8(0x0F));
    }

    // TODO #7: hex8 for values > 0xFF returns more than 2 chars (not truncated)
    @Test
    public void hex8_bug_over_0xFF_returns_more_than_two_chars() {
        // This documents the known TODO #7 behavior: hex8(0x100) gives "100", not "00"
        String result = hex8(0x100);
        assertEquals("100", result); // bug: should be "00" if truncated
    }

    // --- hex8 (String) ---

    @Test
    public void hex8_string_parses_hex() {
        assertEquals(0xFF, hex8("FF"));
        assertEquals(0x00, hex8("00"));
        assertEquals(0xAB, hex8("AB"));
        assertEquals(0xAB, hex8("ab"));
    }

    // --- hex16 (int) ---

    @Test
    public void hex16_formats_word_as_four_hex_chars() {
        assertEquals("0000", hex16(0x0000));
        assertEquals("FFFF", hex16(0xFFFF));
        assertEquals("1234", hex16(0x1234));
        assertEquals("00FF", hex16(0x00FF));
    }

    // TODO #7: hex16 for values > 0xFFFF returns more than 4 chars
    @Test
    public void hex16_bug_over_0xFFFF_returns_more_than_four_chars() {
        String result = hex16(0x10000);
        assertEquals("10000", result); // bug: should be "0000" if truncated
    }

    // --- hex16 (String) ---

    @Test
    public void hex16_string_parses_four_char_hex() {
        assertEquals(0x1234, hex16("1234"));
        assertEquals(0xFFFF, hex16("FFFF"));
        assertEquals(0x00FF, hex16("00FF"));
    }

    // --- canonical ---

    @Test
    public void canonical_wraps_with_prefix_and_suffix() {
        assertEquals("0FFh", canonical("FF"));
        assertEquals("01234h", canonical("1234"));
    }

    // --- bits ---

    @Test
    public void bits_formats_as_8_bit_binary_string() {
        assertEquals("00000000", bits(0x00));
        assertEquals("11111111", bits(0xFF));
        assertEquals("10000000", bits(0x80));
        assertEquals("00000001", bits(0x01));
        assertEquals("10101010", bits(0xAA));
    }

    // --- inc16 / dec16 ---

    @Test
    public void inc16_increments_with_wrap() {
        assertEquals(0x0001, inc16(0x0000));
        assertEquals(0x1235, inc16(0x1234));
        assertEquals(0x0000, inc16(0xFFFF)); // wraps
    }

    @Test
    public void dec16_decrements_with_wrap() {
        assertEquals(0x0000, dec16(0x0001));
        assertEquals(0x1233, dec16(0x1234));
        assertEquals(0xFFFF, dec16(0x0000)); // wraps
    }

    // --- hex(Bites) ---

    @Test
    public void hex_bites_formats_as_space_separated_hex() {
        Bites b = Bites.of(0x01, 0xAB, 0xFF);
        assertEquals("01 AB FF", hex(b));
    }

    @Test
    public void hex_bites_single_element() {
        assertEquals("42", hex(Bites.of(0x42)));
    }

    // --- padLeft / padRight ---

    @Test
    public void padLeft_pads_with_leading_chars() {
        assertEquals("00FF", padLeft("FF", 4, '0'));
        assertEquals("  X", padLeft("X", 3, ' '));
        assertEquals("AB", padLeft("AB", 2, '0')); // already long enough
    }

    @Test
    public void padRight_pads_with_trailing_chars() {
        assertEquals("FF00", padRight("FF", 4, '0'));
        assertEquals("X  ", padRight("X", 3, ' '));
        assertEquals("AB", padRight("AB", 2, '0'));
    }

    // --- isSet ---

    @Test
    public void isSet_returns_true_when_bit_is_set() {
        assertTrue(isSet(0xFF, 0x01));
        assertTrue(isSet(0x80, 0x80));
        assertTrue(isSet(0b10101010, 0b00100000));
    }

    @Test
    public void isSet_returns_false_when_bit_is_clear() {
        assertFalse(isSet(0x00, 0x01));
        assertFalse(isSet(0x7F, 0x80));
        assertFalse(isSet(0b01010101, 0b00100000));
    }

    // --- bitToString ---

    @Test
    public void bitToString_returns_plus_for_true() {
        assertEquals("+", bitToString(true));
    }

    @Test
    public void bitToString_returns_minus_for_false() {
        assertEquals("-", bitToString(false));
    }
}
