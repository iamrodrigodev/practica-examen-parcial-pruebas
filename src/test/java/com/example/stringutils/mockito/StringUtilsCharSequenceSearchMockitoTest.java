package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mockito - CharSequence search")
class StringUtilsCharSequenceSearchMockitoTest {

    @Test
    void countMatchesReadsEveryCharacterForCharSearch() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(5);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('b');
        when(sequence.charAt(2)).thenReturn('a');
        when(sequence.charAt(3)).thenReturn('c');
        when(sequence.charAt(4)).thenReturn('a');

        assertEquals(3, StringUtils.countMatches(sequence, 'a'));

        verify(sequence, atLeastOnce()).length();
        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verify(sequence).charAt(2);
        verify(sequence).charAt(3);
        verify(sequence).charAt(4);
    }

    @Test
    void equalsAnyCanCompareAgainstMockedCharSequence() {
        final CharSequence option = mock(CharSequence.class);
        when(option.length()).thenReturn(3);
        when(option.charAt(0)).thenReturn('a');
        when(option.charAt(1)).thenReturn('b');
        when(option.charAt(2)).thenReturn('c');

        assertTrue(StringUtils.equalsAny("abc", "xyz", option));

        verify(option).charAt(0);
        verify(option).charAt(1);
        verify(option).charAt(2);
    }
}
