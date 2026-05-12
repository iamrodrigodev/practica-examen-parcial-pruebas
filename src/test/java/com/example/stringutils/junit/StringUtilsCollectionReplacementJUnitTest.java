package com.example.stringutils.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit - collections, replacement and exceptions")
class StringUtilsCollectionReplacementJUnitTest {

    @Test
    void splitAndJoinCoverEmptyTokensAndSeparators() {
        assertArrayEquals(new String[] {"a", "b"}, StringUtils.split("a,b", ','));
        assertArrayEquals(new String[] {"a", "", "b"}, StringUtils.splitPreserveAllTokens("a,,b", ','));
        assertEquals("a-b-c", StringUtils.join(new String[] {"a", "b", "c"}, "-"));
    }

    @Test
    void replaceRemoveAndOverlayTransformExpectedParts() {
        assertEquals("hexxo", StringUtils.replace("hello", "l", "x", 2));
        assertEquals("heo", StringUtils.remove("hello", 'l'));
        assertEquals("abZZef", StringUtils.overlay("abcdef", "ZZ", 2, 4));
    }

    @Test
    void paddingAndAbbreviationCoverMinimumAndMaximumWidths() {
        assertEquals("00a", StringUtils.leftPad("a", 3, '0'));
        assertEquals("a00", StringUtils.rightPad("a", 3, '0'));
        assertEquals(" a ", StringUtils.center("a", 3));
        assertEquals("abc...", StringUtils.abbreviate("abcdefg", 6));
        assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", 7));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.abbreviate("abcdefg", 3));
    }

    @Test
    void encodedStringUsesProvidedCharset() {
        assertEquals("hola", StringUtils.toEncodedString("hola".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
    }
}
