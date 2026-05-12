package com.example.stringutils.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit - substrings and limits")
class StringUtilsSubstringJUnitTest {

    @Test
    void substringAcceptsNegativeAndOutOfRangeIndexes() {
        assertNull(StringUtils.substring(null, 0));
        assertEquals("abc", StringUtils.substring("abc", -3));
        assertEquals("", StringUtils.substring("abc", 99));
        assertEquals("bc", StringUtils.substring("abc", 1, 99));
    }

    @Test
    void leftRightAndMidCoverMinimumAndMaximumBounds() {
        assertNull(StringUtils.left(null, 2));
        assertEquals("", StringUtils.left("abc", -1));
        assertEquals("abc", StringUtils.left("abc", 99));
        assertEquals("bc", StringUtils.right("abc", 2));
        assertEquals("b", StringUtils.mid("abc", 1, 1));
        assertEquals("", StringUtils.mid("abc", 1, -1));
    }

    @Test
    void substringBeforeAfterBetweenCoverMissingSeparators() {
        assertEquals("a", StringUtils.substringBefore("a=b", "="));
        assertEquals("b", StringUtils.substringAfter("a=b", "="));
        assertEquals("", StringUtils.substringAfter("abc", "="));
        assertNull(StringUtils.substringBetween(null, "[", "]"));
        assertEquals("dato", StringUtils.substringBetween("[dato]", "[", "]"));
    }
}
