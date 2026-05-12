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
@DisplayName("Mockito - supplier based defaults")
class StringUtilsSupplierMockitoTest {

    @Mock
    private Supplier<String> defaultSupplier;

    @Test
    void getIfBlankUsesSupplierWhenValueIsNull() {
        when(defaultSupplier.get()).thenReturn("generated");

        assertEquals("generated", StringUtils.getIfBlank(null, defaultSupplier));

        verify(defaultSupplier).get();
    }

    @Test
    void getIfBlankUsesSupplierWhenValueIsWhitespace() {
        when(defaultSupplier.get()).thenReturn("generated");

        assertEquals("generated", StringUtils.getIfBlank("   ", defaultSupplier));

        verify(defaultSupplier).get();
    }

    @Test
    void getIfBlankDoesNotUseSupplierWhenValueHasText() {
        assertEquals("real", StringUtils.getIfBlank("real", defaultSupplier));

        verify(defaultSupplier, never()).get();
    }

    @Test
    void getIfEmptyUsesSupplierOnlyForEmptyString() {
        when(defaultSupplier.get()).thenReturn("empty");

        assertEquals("empty", StringUtils.getIfEmpty("", defaultSupplier));
        assertEquals(" ", StringUtils.getIfEmpty(" ", defaultSupplier));

        verify(defaultSupplier).get();
    }

    @Test
    void supplierCanReturnNullAndResultStaysNull() {
        when(defaultSupplier.get()).thenReturn(null);

        assertNull(StringUtils.getIfBlank(" ", defaultSupplier));

        verify(defaultSupplier).get();
    }
}
