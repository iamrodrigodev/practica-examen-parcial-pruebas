package com.example.stringutils.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito - supplier boundary values")
class StringUtilsSupplierBoundaryMockitoTest {

    @Mock
    private Supplier<String> supplier;

    @Test
    void getIfEmptyUsesSupplierForNullInput() {
        when(supplier.get()).thenReturn("fallback");

        assertEquals("fallback", StringUtils.getIfEmpty(null, supplier));

        verify(supplier).get();
    }

    @Test
    void getIfEmptyDoesNotUseSupplierForText() {
        assertEquals("text", StringUtils.getIfEmpty("text", supplier));

        verify(supplier, never()).get();
    }

    @Test
    void getIfBlankUsesSupplierForEmptyInput() {
        when(supplier.get()).thenReturn("fallback");

        assertEquals("fallback", StringUtils.getIfBlank("", supplier));

        verify(supplier).get();
    }

    @Test
    void getIfEmptyCanReturnNullWhenSupplierReturnsNull() {
        when(supplier.get()).thenReturn(null);

        assertNull(StringUtils.getIfEmpty("", supplier));

        verify(supplier).get();
    }
}
