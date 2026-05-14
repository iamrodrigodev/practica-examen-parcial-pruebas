package com.example.stringutils.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("JUnit - Casos Avanzados y Manejo de Excepciones (20/20)")
class StringUtilsAdvancedJUnitTest {

    @Test
    @DisplayName("abbreviate() lanza IllegalArgumentException si el ancho mínimo no se cumple")
    void abbreviateThrowsExceptionForInvalidMaxWidth() {
        // Arrange
        String text = "Texto largo para abreviar";
        String marker = "...";
        int invalidMaxWidth = 3; // marker.length() + 1 is 4, so 3 is invalid

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> StringUtils.abbreviate(text, marker, invalidMaxWidth)
        );
        assertEquals("Minimum abbreviation width is 4", exception.getMessage());
    }

    @Test
    @DisplayName("abbreviate() lanza IllegalArgumentException con offset si el ancho es muy pequeño")
    void abbreviateThrowsExceptionForInvalidMaxWidthWithOffset() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> StringUtils.abbreviate("abcdefghijklmno", "...", 6, 6) // minAbbrevWidthOffset is 7
        );
        assertEquals("Minimum abbreviation width with offset is 7", exception.getMessage());
    }

    @ParameterizedTest(name = "Texto: ''{0}'', MaxWidth: {1} -> ''{2}''")
    @MethodSource("provideValidAbbreviationScenarios")
    @DisplayName("abbreviate() trunca correctamente con offset y anchos válidos")
    void abbreviateWorksCorrectlyWithValidLimits(String text, int maxWidth, String expected) {
        assertEquals(expected, StringUtils.abbreviate(text, maxWidth));
    }

    private static Stream<Arguments> provideValidAbbreviationScenarios() {
        return Stream.of(
            Arguments.of(null, 10, null),
            Arguments.of("", 4, ""),
            Arguments.of("abcdefg", 6, "abc..."),
            Arguments.of("abcdefg", 7, "abcdefg"),
            Arguments.of("abcdefg", 8, "abcdefg"),
            Arguments.of("abcdefg", 4, "a...")
        );
    }

    @Test
    @DisplayName("splitByWholeSeparatorPreserveAllTokens() preserva tokens vacíos complejos")
    void splitByWholeSeparatorPreservesEmptyTokens() {
        String input = "ab--cd----ef";
        String separator = "--";
        
        String[] result = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, separator);
        
        // "ab", "cd", "", "ef"
        assertArrayEquals(new String[]{"ab", "cd", "", "ef"}, result);
    }
}
