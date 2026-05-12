package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DisplayName("Mockito - CharSequence classification")
class StringUtilsCharSequenceClassificationMockitoTest {

    @Test
    void isNumericAcceptsMockedDigits() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(3);
        when(sequence.charAt(0)).thenReturn('1');
        when(sequence.charAt(1)).thenReturn('2');
        when(sequence.charAt(2)).thenReturn('3');

        assertTrue(StringUtils.isNumeric(sequence));

        verify(sequence, atLeastOnce()).length();
        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verify(sequence).charAt(2);
    }

    @Test
    void isNumericRejectsMockedLetterAndStopsEarly() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(3);
        when(sequence.charAt(0)).thenReturn('1');
        when(sequence.charAt(1)).thenReturn('a');

        assertFalse(StringUtils.isNumeric(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verify(sequence, never()).charAt(2);
    }

    @Test
    void isWhitespaceAcceptsMockedWhitespaceOnly() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn(' ');
        when(sequence.charAt(1)).thenReturn('\n');

        assertTrue(StringUtils.isWhitespace(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void isWhitespaceRejectsVisibleCharacter() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn(' ');
        when(sequence.charAt(1)).thenReturn('x');

        assertFalse(StringUtils.isWhitespace(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void isAllLowerCaseAcceptsMockedLowercaseLetters() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('z');

        assertTrue(StringUtils.isAllLowerCase(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void isAllUpperCaseAcceptsMockedUppercaseLetters() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn('A');
        when(sequence.charAt(1)).thenReturn('Z');

        assertTrue(StringUtils.isAllUpperCase(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void isMixedCaseAcceptsMockedMixedCharacters() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('A');

        assertTrue(StringUtils.isMixedCase(sequence));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void containsNoneAcceptsMockedSequenceWithoutForbiddenCharacters() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('b');

        assertTrue(StringUtils.containsNone(sequence, 'x', 'y'));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void containsOnlyAcceptsMockedSequenceWithValidCharacters() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('b');

        assertTrue(StringUtils.containsOnly(sequence, 'a', 'b'));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }

    @Test
    void countMatchesReturnsZeroForMockedSequenceWithoutMatches() {
        final CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(2);
        when(sequence.charAt(0)).thenReturn('a');
        when(sequence.charAt(1)).thenReturn('b');

        assertEquals(0, StringUtils.countMatches(sequence, 'z'));

        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
    }
}
