package com.example.bfhl.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessingUtilTest {

    // --- isNumber ---

    @Test
    void isNumber_validPositive() {
        assertTrue(DataProcessingUtil.isNumber("123"));
    }

    @Test
    void isNumber_validNegative() {
        assertTrue(DataProcessingUtil.isNumber("-5"));
    }

    @Test
    void isNumber_alpha_returnsFalse() {
        assertFalse(DataProcessingUtil.isNumber("abc"));
    }

    @Test
    void isNumber_empty_returnsFalse() {
        assertFalse(DataProcessingUtil.isNumber(""));
    }

    @Test
    void isNumber_null_returnsFalse() {
        assertFalse(DataProcessingUtil.isNumber(null));
    }

    @Test
    void isNumber_mixedAlphaNum_returnsFalse() {
        assertFalse(DataProcessingUtil.isNumber("12a"));
    }

    // --- isAlphabetic ---

    @Test
    void isAlphabetic_singleChar() {
        assertTrue(DataProcessingUtil.isAlphabetic("A"));
    }

    @Test
    void isAlphabetic_multiChar() {
        assertTrue(DataProcessingUtil.isAlphabetic("ABCD"));
    }

    @Test
    void isAlphabetic_mixed_returnsFalse() {
        assertFalse(DataProcessingUtil.isAlphabetic("A1"));
    }

    @Test
    void isAlphabetic_empty_returnsFalse() {
        assertFalse(DataProcessingUtil.isAlphabetic(""));
    }

    @Test
    void isAlphabetic_null_returnsFalse() {
        assertFalse(DataProcessingUtil.isAlphabetic(null));
    }

    // --- isSpecialCharacter ---

    @Test
    void isSpecialCharacter_dollar() {
        assertTrue(DataProcessingUtil.isSpecialCharacter("$"));
    }

    @Test
    void isSpecialCharacter_multiSpecial() {
        assertTrue(DataProcessingUtil.isSpecialCharacter("$@!"));
    }

    @Test
    void isSpecialCharacter_alpha_returnsFalse() {
        assertFalse(DataProcessingUtil.isSpecialCharacter("A"));
    }

    @Test
    void isSpecialCharacter_empty_returnsFalse() {
        assertFalse(DataProcessingUtil.isSpecialCharacter(""));
    }

    // --- buildConcatString ---

    @Test
    void buildConcatString_exampleFromRequirements() {
        // Input: ["A","ABCD","DOE"] -> Combined: AABCDDOE -> Reversed: EODDDCBAA -> Alternating: EoDdCbAa
        List<String> data = Arrays.asList("A", "ABCD", "DOE");
        String result = DataProcessingUtil.buildConcatString(data);
        assertEquals("EoDdCbAa", result);
    }

    @Test
    void buildConcatString_mixedInput() {
        // Input: ["a", "1", "334", "4", "R", "$"]
        // Alpha chars: a, R -> "aR" -> reversed: "Ra" -> alternating: "Ra"
        List<String> data = Arrays.asList("a", "1", "334", "4", "R", "$");
        String result = DataProcessingUtil.buildConcatString(data);
        assertEquals("Ra", result);
    }

    @Test
    void buildConcatString_emptyList() {
        assertEquals("", DataProcessingUtil.buildConcatString(Collections.emptyList()));
    }

    @Test
    void buildConcatString_nullList() {
        assertEquals("", DataProcessingUtil.buildConcatString(null));
    }

    @Test
    void buildConcatString_noAlphabets() {
        List<String> data = Arrays.asList("1", "2", "$");
        assertEquals("", DataProcessingUtil.buildConcatString(data));
    }

    // --- applyAlternatingCaps ---

    @Test
    void applyAlternatingCaps_basic() {
        assertEquals("HeLlO", DataProcessingUtil.applyAlternatingCaps("hello"));
    }

    @Test
    void applyAlternatingCaps_empty() {
        assertEquals("", DataProcessingUtil.applyAlternatingCaps(""));
    }

    @Test
    void applyAlternatingCaps_null() {
        assertEquals("", DataProcessingUtil.applyAlternatingCaps(null));
    }

    @Test
    void applyAlternatingCaps_singleChar() {
        assertEquals("A", DataProcessingUtil.applyAlternatingCaps("a"));
    }
}
