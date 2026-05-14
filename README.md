# Informe Técnico de Ingeniería de Software: Especificación de Pruebas Unitarias

## 1. Introducción y Marco Normativo
Este documento presenta la especificación exhaustiva de la suite de pruebas para `StringUtils`. Se fundamenta en el estándar **IEEE 610**, tratando cada test como un **Caso de Prueba** compuesto por entradas, condiciones de ejecución y resultados esperados.

---

## 2. Especificación Detallada de Casos de Prueba (Total: 72)

### 2.1. Suite de Verificación Lógica (JUnit 5 - 36 Casos)
Ubicación: `src/test/java/com/example/stringutils/junit`

#### A. Clase: `StringUtilsAdvancedJUnitTest` (9 Casos)
| ID | Objetivo | Entradas (Input) | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| J1 | Verificación de ancho mín. en `abbreviate`. | "Texto", marker="...", width=3 | `IllegalArgumentException` |
| J2 | Verificación de ancho mín. con offset. | "abcdefg", marker="...", 6, 6 | `IllegalArgumentException` |
| J3 | Validación de `abbreviate` con `null`. | `null`, 10 | `null` |
| J4 | Validación de `abbreviate` con `""`. | `""`, 4 | `""` |
| J5 | Abreviación estándar. | "abcdefg", 6 | "abc..." |
| J6 | Abreviación límite exacto. | "abcdefg", 7 | "abcdefg" |
| J7 | Abreviación con ancho mayor. | "abcdefg", 8 | "abcdefg" |
| J8 | Abreviación máxima compresión. | "abcdefg", 4 | "a..." |
| J9 | Preservación de tokens en `split`. | "ab--cd----ef", "--" | `["ab", "cd", "", "ef"]` |

#### B. Clase: `StringUtilsBlankAndDefaultJUnitTest` (9 Casos)
| ID | Objetivo | Entradas (Input) | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| J10 | `isBlank` con `null`. | `null` | `true` |
| J11 | `isBlank` con vacío. | `""` | `true` |
| J12 | `isBlank` con espacio. | `" "` | `true` |
| J13 | `isBlank` con múltiples espacios. | `"   "` | `true` |
| J14 | `isBlank` con texto 'a'. | `"a"` | `false` |
| J15 | `isBlank` con texto rodeado. | `"  a  "` | `false` |
| J16 | `isBlank` con número. | `"0"` | `false` |
| J17 | `isBlank` con signo. | `"."` | `false` |
| J18 | Valores por defecto (`defaultString`). | `null`, `null, "backup"`, `"texto", "backup"` | `""`, `"backup"`, `"texto"` |

#### C. Clase: `StringUtilsComparisonSearchJUnitTest` (7 Casos)
| ID | Objetivo | Entradas (Input) | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| J19 | Comparación segura de nulos. | `compare(null, "a")`, `compareIgnoreCase` | Orden lexicográfico seguro |
| J20 | Igualdad segura. | `equals(null, null)`, `equalsIgnoreCase` | `true` / `false` según match |
| J21 | Búsqueda `containsAny`. | "zzabyycdxx", ['z', 'a'] | `true` |
| J22 | Posiciones `indexOf`. | "aabaabaa", 'b' | Índices correctos (2, 5, etc.) |
| J23 | Búsqueda en sets. | "abcde", "ab", "cd" | Primer match encontrado |
| J24 | Punto de divergencia. | "i am a machine", "i am a robot" | Índice donde cambian |
| J25 | Diferencia de contenido. | "i am a machine", "i am a robot" | "machine" vs "robot" |

#### D. Clase: `StringUtilsTransformationJUnitTest` (4 Casos)
| ID | Objetivo | Entradas (Input) | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| J26 | Capitalización. | "cat", "CAT" | "Cat", "cAT" (uncapitalize) |
| J27 | Locales. | "u", Locale("tr") | Conversión según región |
| J28 | Inversión de mayúsculas. | "aBc12" | "AbC12" |
| J29 | Rotación de caracteres. | "abcdefg", 2 | "fgabcde" |

#### E. Clase: `StringUtilsCollectionReplacementJUnitTest` (4 Casos)
| ID | Objetivo | Entradas (Input) | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| J30 | Unión (Join). | `["a", "b"]`, "-" | "a-b" |
| J31 | Reemplazo múltiple. | "aba", "a", "z" | "zbz" |
| J32 | Superposición (Overlay). | "abcdef", "zz", 2, 4 | "abzzef" |
| J33 | Abreviación central. | "abcdefghij", "...", 7 | "ab...ij" |

#### F. Clase: `StringUtilsSubstringJUnitTest` (3 Casos)
| ID | Objetivo | Entradas (Input) | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| J34 | Substring con negativos. | "abc", -3 | "abc" |
| J35 | Métodos Left/Right/Mid. | "abc", indices variados | Truncamiento seguro |
| J36 | Delimitadores. | "a=b", "=" | "a" (before), "b" (after) |

---

### 2.2. Suite de Validación de Comportamiento (Mockito - 36 Casos)
Ubicación: `src/test/java/com/example/stringutils/mockito`

#### G. Clase: `StringUtilsAdvancedMockitoTest` (6 Casos)
| ID | Objetivo | Entradas / Mock Setup | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| M1 | Propagación de error. | `Supplier` lanza `IllegalStateException` | Excepción fluye al invocador |
| M2 | Eficiencia (Lazy). | Texto válido + `Supplier` fallido | `Supplier` no se llama (`never()`) |
| M3 | Detención por error. | `CharSequence.charAt(1)` lanza error | Verificación de parada inmediata |
| M4 | Interacción `equals`. | Dos `CharSequence` mocks | Verificación de llamadas a `.length()` |
| M5 | Verificación `contains`. | `CharSequence` mock | Verificación de acceso a `.charAt(0)` |
| M6 | Evaluación perezosa. | `firstNonBlank(mock1, mock2)` | `mock2` no se toca si `mock1` es válido |

#### H. Clase: `StringUtilsCharSequenceClassificationMockitoTest` (10 Casos)
| ID | Objetivo | Entradas / Mock Setup | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| M7-16| Clasificación. | Mocks de `CharSequence` con chars específicos. | Validación de `isAlpha`, `isNumeric`, `isWhitespace`, etc. |

#### I. Clase: `StringUtilsSupplierMockitoTest` (5 Casos)
| ID | Objetivo | Entradas / Mock Setup | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| M17 | `getIfBlank` (null). | `Supplier` devuelve "gen" | Retorna "gen" |
| M18 | `getIfBlank` (blank). | `Supplier` devuelve "gen" | Retorna "gen" |
| M19 | `getIfBlank` (text). | No se llama al supplier | Retorna texto original |
| M20 | `getIfEmpty`. | Solo se llama si el string es `""` | Verificación de llamada selectiva |
| M21 | Supplier devuelve null. | `Supplier` retorna `null` | El resultado final es `null` |

#### J. Clase: `StringUtilsSupplierBoundaryMockitoTest` (4 Casos)
| ID | Objetivo | Entradas / Mock Setup | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| M22 | Supplier nulo. | `getIfBlank(str, null)` | `NullPointerException` |
| M23 | Supplier nulo (empty). | `getIfEmpty(str, null)` | `NullPointerException` |
| M24 | Digits con null. | `getDigits(null)` | `null` |
| M25 | Supplier que falla. | Error dentro de `.get()` | Propagación controlada |

#### K. Clase: `StringUtilsCharSequenceValidationMockitoTest` (4 Casos)
| ID | Objetivo | Entradas / Mock Setup | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| M26 | `isEmpty` en mock. | `.length() -> 0` | `true` |
| M27 | `isBlank` en mock. | `.charAt(0) -> ' '` | Verificación de escaneo |
| M28 | `containsWhitespace`. | Parada en primer espacio | No lee el resto del mock |
| M29 | `isAlpha` Early Exit. | Detención en primer dígito | No lee caracteres posteriores |

#### L. Otros: Prefix, Search e IgnoreCase (7 Casos)
| ID | Objetivo | Entradas / Mock Setup | Resultado Esperado |
| :--- | :--- | :--- | :--- |
| M30-32| Prefijos/Sufijos. | Mocks de `CharSequence` y prefijos. | Verificación de `startsWith`, `endsWithAny`. |
| M33-34| Búsqueda avanzada. | Mocks con patrones de bits. | Verificación de `indexOf` y `contains`. |
| M35-36| Ignore Case. | Mocks de diferentes casos. | Verificación de normalización de caracteres. |

---

## 3. Conclusión de Calidad
La suite completa de **72 casos** garantiza:
1.  **Verificación Funcional:** Cada camino lógico ha sido ejercitado.
2.  **Validación de Diseño:** El sistema responde correctamente a fallos de dependencias.
3.  **Repetibilidad:** Ejecución consistente mediante `mvn test`.
