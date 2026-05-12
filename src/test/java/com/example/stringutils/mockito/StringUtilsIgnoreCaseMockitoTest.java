package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mockito - ignore case prefix and suffix")
class StringUtilsIgnoreCaseMockitoTest {

    @Test
    void startsWithIgnoreCaseWorksWithMockedPrefix() {
        final CharSequence prefix = mock(CharSequence.class);
        when(prefix.length()).thenReturn(2);
        when(prefix.charAt(0)).thenReturn('H');
        when(prefix.charAt(1)).thenReturn('O');

        assertTrue(StringUtils.startsWithIgnoreCase("hola", prefix));

        verify(prefix, atLeastOnce()).length();
        verify(prefix).charAt(0);
        verify(prefix).charAt(1);
    }

    @Test
    void endsWithIgnoreCaseWorksWithMockedSuffix() {
        final CharSequence suffix = mock(CharSequence.class);
        when(suffix.length()).thenReturn(2);
        when(suffix.charAt(0)).thenReturn('L');
        when(suffix.charAt(1)).thenReturn('A');

        assertTrue(StringUtils.endsWithIgnoreCase("hola", suffix));

        verify(suffix, atLeastOnce()).length();
        verify(suffix).charAt(0);
        verify(suffix).charAt(1);
    }
}
