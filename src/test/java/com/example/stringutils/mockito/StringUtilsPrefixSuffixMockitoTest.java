package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mockito - prefixes, suffixes and containsAny")
class StringUtilsPrefixSuffixMockitoTest {

    @Test
    void startsWithAnyChecksMockedPrefix() {
        final CharSequence prefix = mock(CharSequence.class);
        when(prefix.length()).thenReturn(2);
        when(prefix.charAt(0)).thenReturn('h');
        when(prefix.charAt(1)).thenReturn('o');

        assertTrue(StringUtils.startsWithAny("hola", prefix));

        verify(prefix, atLeastOnce()).length();
        verify(prefix).charAt(0);
        verify(prefix).charAt(1);
    }

    @Test
    void endsWithAnyChecksMockedSuffix() {
        final CharSequence suffix = mock(CharSequence.class);
        when(suffix.length()).thenReturn(2);
        when(suffix.charAt(0)).thenReturn('l');
        when(suffix.charAt(1)).thenReturn('a');

        assertTrue(StringUtils.endsWithAny("hola", suffix));

        verify(suffix, atLeastOnce()).length();
        verify(suffix).charAt(0);
        verify(suffix).charAt(1);
    }

    @Test
    void containsAnyChecksMockedSearchSequence() {
        final CharSequence search = mock(CharSequence.class);
        when(search.length()).thenReturn(1);
        when(search.charAt(0)).thenReturn('o');

        assertTrue(StringUtils.containsAny("hola", search));

        verify(search).length();
        verify(search).charAt(0);
    }
}
