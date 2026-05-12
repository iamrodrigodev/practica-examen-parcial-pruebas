package com.example.stringutils.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("JUnit - comparison and search")
class StringUtilsComparisonSearchJUnitTest {

    @Test
    void equalsAndEqualsIgnoreCaseAreNullSafe() {
        assertTrue(StringUtils.equals(null, null));
        assertFalse(StringUtils.equals(null, "abc"));
        assertTrue(StringUtils.equalsIgnoreCase("ABC", "abc"));
        assertFalse(StringUtils.equalsIgnoreCase("ABC", "abd"));
    }

    @Test
    void compareHandlesNullOrdering() {
        assertEquals(0, StringUtils.compare(null, null));
        assertTrue(StringUtils.compare(null, "a") < 0);
        assertTrue(StringUtils.compare("a", null) > 0);
        assertTrue(StringUtils.compare("a", "b") < 0);
    }

    @ParameterizedTest
    @CsvSource({
        "abracadabra, abra, true",
        "abracadabra, cad, true",
        "abracadabra, xyz, false",
        "'', a, false"
    })
    void containsFindsExpectedSubstrings(final String text, final String search, final boolean expected) {
        assertEquals(expected, StringUtils.contains(text, search));
    }

    @Test
    void indexOfReturnsMinusOneForMissingOrNullValues() {
        assertEquals(StringUtils.INDEX_NOT_FOUND, StringUtils.indexOf(null, "a"));
        assertEquals(StringUtils.INDEX_NOT_FOUND, StringUtils.indexOf("abc", "x"));
        assertEquals(1, StringUtils.indexOf("abc", "b"));
    }
}
