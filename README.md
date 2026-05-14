# Informe Técnico de Ingeniería de Software: Especificación de Pruebas Unitarias

## 1. Introducción y Marco Normativo
Este documento presenta la especificación exhaustiva de la suite de pruebas para StringUtils. Se fundamenta en el estándar IEEE 610, tratando cada test como un Caso de Prueba compuesto por entradas, condiciones de ejecución y resultados esperados.

---

## 2. Especificación Detallada y Relevancia de los Casos de Prueba

### 2.1. Suite de Verificación Lógica (JUnit 5 - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/junit

#### A. Clase: StringUtilsAdvancedJUnitTest (9 Casos)
Propósito y Relevancia: Control de visualización y parseo robusto. Estas funciones son críticas en interfaces de usuario (UI) para prevenir desbordamientos de texto.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J1 | Validación ancho mínimo | text, marker, width=3 | Prevención de Errores: Lanza excepción si el ancho es insuficiente para el marcador. |
| J2 | Validación ancho c/offset | text, marker, offset=6, width=6 | Robustez: Valida límites mínimos cuando se usa desplazamiento. |
| J3 | Abreviación nula | null, 10 | Estabilidad: Manejo seguro de valores nulos. |
| J4 | Abreviación vacía | "", 4 | Consistencia: Retorna cadena vacía si el input es vacío. |
| J5 | Truncado estándar | "abcdefg", 6 | UX: Asegura el formato "abc..." cuando supera el límite. |
| J6 | Límite exacto | "abcdefg", 7 | Precisión: No abrevia si el texto cabe exactamente. |
| J7 | Sobre-límite | "abcdefg", 8 | Eficiencia: No modifica la cadena si el límite es superior al largo. |
| J8 | Truncado agresivo | "abcdefg", 4 | UI: Valida el caso mínimo de abreviación "a...". |
| J9 | Split complejo | "ab--cd----ef", "--" | Integridad: Vital para procesar datos con delimitadores duplicados. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsAdvancedJUnitTest
```

#### B. Clase: StringUtilsBlankAndDefaultJUnitTest (9 Casos)
Propósito y Relevancia: Sanitización de entradas. Primera línea de defensa contra datos vacíos.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J10 | isBlank Null | null | Seguridad: Detecta nulos como blanco. |
| J11 | isBlank Empty | "" | Validación: Detecta vacíos como blanco. |
| J12 | isBlank Space | " " | Integridad: Detecta espacios como blanco. |
| J13 | isBlank Whitespace | "   " | Integridad: Detecta múltiples espacios como blanco. |
| J14 | isBlank Visible | "a" | Precisión: Identifica contenido visible. |
| J15 | isBlank c/Espacios | "  a  " | Precisión: Ignora espacios laterales para validar contenido. |
| J16 | isBlank Numérico | "0" | Seguridad de Tipos: Los números no son blancos. |
| J17 | isBlank Símbolo | "." | Seguridad de Tipos: Los símbolos no son blancos. |
| J18 | Valores por defecto | null -> "" | Robustez: Provee fallback seguro para evitar NullPointerException. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsBlankAndDefaultJUnitTest
```

#### C. Clase: StringUtilsComparisonSearchJUnitTest (7 Casos)
Propósito y Relevancia: Integridad en la búsqueda. Localización segura de información.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J19 | Comparación Null-Safe | equals(null, null) | Consistencia: Permite comparar nulos sin fallar. |
| J20 | Ordenamiento Null | compare(null, "a") | Análisis: Define un orden predecible para valores nulos. |
| J21 | Búsqueda Positiva | contains("abra") | Búsqueda: Localiza subcadenas al inicio. |
| J22 | Búsqueda Media | contains("cad") | Búsqueda: Localiza subcadenas en el medio. |
| J23 | Búsqueda Negativa | contains("xyz") | Fiabilidad: Confirma ausencia de datos. |
| J24 | Búsqueda en Vacío | contains('', 'a') | Robustez: Manejo correcto de búsquedas en cadenas vacías. |
| J25 | Índice de falla | indexOf(null) | Estabilidad: Retorna INDEX_NOT_FOUND en lugar de fallar. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsComparisonSearchJUnitTest
```

#### D. Clase: StringUtilsTransformationJUnitTest (4 Casos)
Propósito y Relevancia: Normalización de formatos. Asegura estándares visuales.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J26 | Limpieza Espacios | trim, normalizeSpace | Higiene de Datos: Elimina ruidos y normaliza espacios internos. |
| J27 | Cambio de Caso | Capitalize, Locale | Estandarización: Formateo profesional respetando reglas regionales. |
| J28 | Eliminación Acentos | "áéíóú ñ" | Normalización: Vital para búsquedas que ignoran tildes. |
| J29 | Manipulación Estructural| reverse, rotate, repeat | Utilidad: Operaciones de transformación de estructura base. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsTransformationJUnitTest
```

#### E-F. Otras Utilidades (7 Casos)
| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| J30 | Split/Join Tokens | Formateo: Reconstrucción y división de cadenas con separadores. |
| J31 | Replace/Overlay | Modificación: Reemplazo parcial y enmascaramiento de datos. |
| J32 | Padding/Abbrev Limits | Visualización: Asegura anchos fijos (relleno) y abreviación manual. |
| J33 | Codificación Charset | Interoperabilidad: Conversión segura de bytes a String usando UTF-8. |
| J34 | Substring Límites | Extracción: Manejo de índices negativos y fuera de rango. |
| J35 | Left/Right/Mid | Extracción: Obtención de fragmentos específicos de forma segura. |
| J36 | Substring Delimitado | Extracción: Obtención de texto entre o después de separadores. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsCollectionReplacementJUnitTest,StringUtilsSubstringJUnitTest
```

---

### 2.2. Suite de Validación de Comportamiento (Mockito - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/mockito

#### G. Clase: StringUtilsAdvancedMockitoTest (6 Casos)
Propósito y Relevancia: Manejo de fallos externos y optimización.

| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| M1 | Propagación Excepción | Resiliencia: Valida que StringUtils no capture errores críticos del Supplier. |
| M2 | Ignorar Supplier | Eficiencia: No llama al Supplier (DB/API) si el texto base es válido. |
| M3 | Detención en Error | Robustez: Detiene el procesamiento si el CharSequence falla durante la lectura. |
| M4 | Interacción Equals | Abstracción: Verifica que se lean los caracteres del Mock para comparar. |
| M5 | Interacción Contains | Abstracción: Verifica que se acceda al largo y caracteres del Mock. |
| M6 | Evaluación Perezosa | Performance: Garantiza que el segundo parámetro no se evalúe si el primero cumple. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsAdvancedMockitoTest
```

#### H. Clase: StringUtilsCharSequenceClassificationMockitoTest (10 Casos)
Propósito y Relevancia: Validación de reglas de negocio sobre interfaces.

| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| M7 | isNumeric Positivo | Validación: Verifica que reconozca dígitos a través de la interfaz. |
| M8 | isNumeric Early Exit | Eficiencia: Deja de leer el Mock en cuanto encuentra una letra. |
| M9 | isWhitespace Acepta | Validación: Reconoce espacios y saltos de línea mockeados. |
| M10 | isWhitespace Rechaza | Validación: Identifica caracteres visibles en el Mock. |
| M11 | isAllLowerCase | Regla: Verifica que todos los caracteres sean minúsculas. |
| M12 | isAllUpperCase | Regla: Verifica que todos los caracteres sean mayúsculas. |
| M13 | isMixedCase | Regla: Detecta combinación de mayúsculas y minúsculas. |
| M14 | containsNone | Seguridad: Confirma que caracteres prohibidos no están en el Mock. |
| M15 | containsOnly | Seguridad: Confirma que solo existen caracteres permitidos. |
| M16 | countMatches Char | Análisis: Cuenta ocurrencias interactuando con charAt. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsCharSequenceClassificationMockitoTest
```

#### I-L. Casos de Interacción y Límites (20 Casos)
| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| M17 | getIfBlank (Null) | Interacción: Llama al Supplier cuando el valor es nulo. |
| M18 | getIfBlank (Blank) | Interacción: Llama al Supplier cuando el valor es "   ". |
| M19 | getIfBlank (Text) | Eficiencia: NUNCA llama al Supplier si hay texto. |
| M20 | getIfEmpty (Empty) | Precisión: Diferencia entre vacío (llama) y espacio (no llama). |
| M21 | Supplier devuelve Null| Estabilidad: Maneja correctamente cuando el proveedor retorna nulo. |
| M22 | getIfEmpty (Input Null)| Límite: Maneja la entrada nula llamando al fallback. |
| M23 | getIfEmpty (Input Text)| Límite: No toca el supplier si el input tiene datos. |
| M24 | getIfBlank (Input Empty)| Precisión: Trata cadena vacía como blanco y llama al supplier. |
| M25 | getIfEmpty Fallback Null| Estabilidad: El resultado final puede ser nulo si el supplier así lo decide. |
| M26 | countMatches Full Scan| Exhaustividad: Verifica que recorra todo el Mock para contar. |
| M27 | equalsAny Mock | Interoperabilidad: Compara un String contra una secuencia mockeada. |
| M28 | isEmpty Length | Eficiencia: Solo debe llamar a length(), no a charAt(). |
| M29 | isBlank Interaction | Escaneo: Lee caracteres hasta encontrar el primero no blanco. |
| M30 | containsWhitespace Exit| Eficiencia: Se detiene inmediatamente al hallar el primer espacio. |
| M31 | isAlpha Early Exit | Seguridad: Se detiene al hallar el primer caracter no alfabético. |
| M32 | startsWithIgnoreCase | Prefijos: Valida case-insensitivity interactuando con el Mock. |
| M33 | endsWithIgnoreCase | Sufijos: Valida case-insensitivity al final de la secuencia. |
| M34 | startsWithAny | Identificación: Compara el inicio contra una secuencia mockeada. |
| M35 | endsWithAny | Identificación: Compara el final contra una secuencia mockeada. |
| M36 | containsAny | Búsqueda: Localiza una secuencia mockeada dentro de un String. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsSupplierMockitoTest,StringUtilsSupplierBoundaryMockitoTest,StringUtilsCharSequenceSearchMockitoTest,StringUtilsCharSequenceValidationMockitoTest,StringUtilsIgnoreCaseMockitoTest,StringUtilsPrefixSuffixMockitoTest
```

---

## 3. Conclusión de Calidad
La suite completa de 72 casos no solo verifica que el código funcione, sino que garantiza que sea seguro, eficiente y resiliente. Cada test tiene una relevancia directa en la calidad final del software entregado al cliente, cumpliendo con los estándares de Ingeniería de Requerimientos.

---
## Ejecución
Para ejecutar la suite completa:
```bash
mvn test
```
*Total de pruebas esperado: 72 (36 JUnit / 36 Mockito)*
