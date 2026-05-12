package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mockito - CharSequence validation")
class StringUtilsCharSequenceValidationMockitoTest {

    @Test
    void isEmptyReadsLengthFromMockedCharSequence() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(0);

        assertTrue(StringUtils.isEmpty(sequence));

        verify(sequence, atLeastOnce()).length();
    }

    @Test
    void isBlankReadsCharactersUntilItFindsText() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(3);
        when(sequence.charAt(0)).thenReturn(' ');
        when(sequence.charAt(1)).thenReturn('\t');
        when(sequence.charAt(2)).thenReturn('x');

        assertFalse(StringUtils.isBlank(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verify(sequence).charAt(2);
    }

    @Test
    void containsWhitespaceStopsWhenWhitespaceAppears() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(3);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn(' ');

        assertTrue(StringUtils.containsWhitespace(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verify(sequence, never()).charAt(2);
    }

    @Test
    void isAlphaRejectsDigitAndStopsEarly() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(3);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('1');

        assertFalse(StringUtils.isAlpha(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verify(sequence, never()).charAt(2);
    }
}
