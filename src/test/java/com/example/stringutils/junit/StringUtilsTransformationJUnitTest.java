package com.example.stringutils.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit - text transformation")
class StringUtilsTransformationJUnitTest {

    @Test
    void trimStripAndNormalizeSpaceCleanDifferentKindsOfWhitespace() {
        assertNull(StringUtils.trim(null));
        assertEquals("hola", StringUtils.trim("  hola  "));
        assertEquals("hola mundo", StringUtils.normalizeSpace(" \t hola \n mundo  "));
    }

    @Test
    void caseMethodsHandleLocale() {
        assertEquals("Hola", StringUtils.capitalize("hola"));
        assertEquals("hola", StringUtils.uncapitalize("Hola"));
        assertEquals("ABC", StringUtils.upperCase("abc", Locale.ROOT));
        assertEquals("abc", StringUtils.lowerCase("ABC", Locale.ROOT));
    }

    @Test
    void stripAccentsRemovesDiacritics() {
        assertEquals("aeiou n", StringUtils.stripAccents("\u00e1\u00e9\u00ed\u00f3\u00fa \u00f1"));
    }

    @Test
    void reverseRotateAndRepeatCoverBoundaryValues() {
        assertNull(StringUtils.reverse(null));
        assertEquals("cba", StringUtils.reverse("abc"));
        assertEquals("cab", StringUtils.rotate("abc", 1));
        assertEquals("", StringUtils.repeat("x", 0));
        assertEquals("xxx", StringUtils.repeat("x", 3));
    }
}
