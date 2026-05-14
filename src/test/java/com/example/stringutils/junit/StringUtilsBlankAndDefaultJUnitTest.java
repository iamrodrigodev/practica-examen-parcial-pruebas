package com.example.stringutils.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("JUnit - null, empty, blank and default values")
class StringUtilsBlankAndDefaultJUnitTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "\r\n"})
    void isBlankReturnsTrueForNullEmptyAndWhitespace(final String value) {
        assertTrue(StringUtils.isBlank(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "  a  ", "0", "."})
    void isBlankReturnsFalseWhenThereIsVisibleContent(final String value) {
        assertFalse(StringUtils.isBlank(value));
    }

    @Test
    void defaultStringCoversNullAndNonNullValues() {
        assertEquals("", StringUtils.defaultString(null));
        assertEquals("backup", Objects.toString(null, "backup"));
        assertEquals("texto", Objects.toString("texto", "backup"));
    }
}
