package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito - Escenarios Avanzados y Propagación de Excepciones (20/20)")
class StringUtilsAdvancedMockitoTest {

    @Mock
    private Supplier<String> exceptionSupplier;

    @Test
    @DisplayName("getIfBlank() propaga la excepción si el Supplier falla")
    void getIfBlankPropagatesSupplierException() {
        // Arrange
        when(exceptionSupplier.get()).thenThrow(new IllegalStateException("Fallo en base de datos"));

        // Act & Assert
        IllegalStateException thrown = assertThrows(
            IllegalStateException.class,
            () -> StringUtils.getIfBlank(null, exceptionSupplier)
        );
        
        assertEquals("Fallo en base de datos", thrown.getMessage());
        verify(exceptionSupplier).get(); // Verificamos que sí intentó llamarlo
    }

    @Test
    @DisplayName("getIfBlank() ignora el Supplier fallido si el texto es válido")
    void getIfBlankIgnoresFailingSupplierIfTextIsValid() {
        // Arrange (No configuramos when...thenThrow porque no debería ser llamado)

        // Act
        String result = StringUtils.getIfBlank("Texto válido", exceptionSupplier);

        // Assert
        assertEquals("Texto válido", result);
        verifyNoInteractions(exceptionSupplier); // Garantiza cero interacciones (muy valioso en exámenes)
    }

    @Test
    @DisplayName("contains() se detiene inmediatamente si charAt lanza excepción en el Mock de CharSequence")
    void containsPropagatesExceptionFromMockedCharSequence() {
        // Arrange
        CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(10);
        when(sequence.charAt(0)).thenReturn('H');
        when(sequence.charAt(1)).thenThrow(new IndexOutOfBoundsException("Simulación de error"));

        // Act & Assert
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> StringUtils.contains(sequence, 'a') // Buscará iterando charAt
        );

        // Assert
        verify(sequence, times(2)).length();
        verify(sequence).charAt(0);
        verify(sequence).charAt(1);
        verifyNoMoreInteractions(sequence); // Aseguramos que no iteró más allá del error
    }

    @Test
    @DisplayName("equalsIgnoreCase() verifica interacciones con CharSequence")
    void equalsIgnoreCaseVerifiesInteractions() {
        CharSequence s1 = mock(CharSequence.class);
        CharSequence s2 = mock(CharSequence.class);
        
        when(s1.length()).thenReturn(3);
        when(s2.length()).thenReturn(3);
        
        // Simular que son diferentes en el primer caracter ignorando case
        // Apache usa regionMatches que llama a charAt
        when(s1.charAt(0)).thenReturn('A');
        when(s2.charAt(0)).thenReturn('b');

        assertFalse(StringUtils.equalsIgnoreCase(s1, s2));
        
        verify(s1, atLeastOnce()).length();
        verify(s2, atLeastOnce()).length();
    }

    @Test
    @DisplayName("contains() verifica interacción con CharSequence")
    void containsVerifiesInteractions() {
        CharSequence sequence = mock(CharSequence.class);
        when(sequence.length()).thenReturn(3);
        when(sequence.charAt(0)).thenReturn('a');
        
        assertTrue(StringUtils.contains(sequence, 'a'));
        
        verify(sequence, atLeastOnce()).length();
        verify(sequence).charAt(0);
    }

    @Test
    @DisplayName("firstNonBlank() verifica que solo se evalúan los necesarios")
    void firstNonBlankVerifiesLazyEvaluation() {
        CharSequence s1 = mock(CharSequence.class);
        CharSequence s2 = mock(CharSequence.class);
        
        when(s1.length()).thenReturn(1);
        when(s1.charAt(0)).thenReturn('X'); // No es blanco

        assertEquals(s1, StringUtils.firstNonBlank(s1, s2));
        
        verifyNoInteractions(s2); // s2 no debe ser evaluado si s1 no es blanco
    }
}
