# Informe Técnico de Ingeniería de Software: Especificación de Pruebas Unitarias

## 1. Introducción y Marco Normativo
Este documento presenta la especificación exhaustiva de la suite de pruebas para StringUtils. Se fundamenta en el estándar IEEE 610, tratando cada test como un Caso de Prueba compuesto por entradas, condiciones de ejecución y resultados esperados.

---

## 2. Especificación Detallada y Relevancia de los Casos de Prueba

### 2.1. Suite de Verificación Lógica (JUnit 5 - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/junit

#### A. Clase: StringUtilsAdvancedJUnitTest (9 Casos)
Propósito y Relevancia: Control de visualización y parseo robusto. Estas funciones son críticas en interfaces de usuario (UI) para prevenir desbordamientos de texto.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| J1 | text, marker, width=3 | StringUtils.abbreviate(text, marker, width) | IllegalArgumentException | **Prevención de Regresiones:** Asegura que el sistema falle explícitamente si se intenta usar un marcador en un espacio insuficiente, evitando bucles infinitos. |
| J2 | text, marker, offset=6, width=6 | StringUtils.abbreviate(text, marker, offset, width) | IllegalArgumentException | **Consistencia de Algoritmo:** Valida la lógica de desplazamiento y límites mínimos de ancho con offset para previsualizaciones seguras. |
| J3 | null, 10 | StringUtils.abbreviate(null, 10) | null | **Seguridad en Producción:** Garantiza que la librería sea "null-safe", evitando caídas (NPE) ante datos incompletos. |
| J4 | "", 4 | StringUtils.abbreviate("", 4) | "" | **Eficiencia Operacional:** Valida el retorno inmediato para cadenas vacías, ahorrando ciclos de CPU. |
| J5 | "abcdefg", 6 | StringUtils.abbreviate("abcdefg", 6) | "abc..." | **Integridad de UX:** Asegura el truncado correcto con marcador para mantener la estética de la interfaz de usuario. |
| J6 | "abcdefg", 7 | StringUtils.abbreviate("abcdefg", 7) | "abcdefg" | **Precisión de Datos:** Verifica que no se modifique el texto si cabe exactamente en el ancho permitido. |
| J7 | "abcdefg", 8 | StringUtils.abbreviate("abcdefg", 8) | "abcdefg" | **Optimización de Memoria:** Garantiza que se reutilice la referencia original si no es necesario truncar. |
| J8 | "abcdefg", 4 | StringUtils.abbreviate("abcdefg", 4) | "a..." | **Manejo de Casos Borde:** Prueba el sistema bajo condiciones de ancho mínimo para asegurar robustez en pantallas pequeñas. |
| J9 | "ab--cd----ef", "--" | StringUtils.splitByWholeSeparatorPreserveAllTokens(...) | ["ab", "cd", "", "ef"] | **Integridad de Datos en ETL:** Esencial para procesar archivos donde los delimitadores pueden venir duplicados, preservando tokens vacíos. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsAdvancedJUnitTest
```

#### B. Clase: StringUtilsBlankAndDefaultJUnitTest (9 Casos)
Propósito y Relevancia: Sanitización de entradas. Primera línea de defensa contra datos vacíos.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| J10 | null | StringUtils.isBlank(null) | true | **Prevención de NPE:** Identificar nulos como "blanco" unifica la lógica de validación. |
| J11 | "" | StringUtils.isBlank("") | true | **Validación de Formularios:** Diferencia entre campo no tocado y campo borrado por el usuario. |
| J12 | " " | StringUtils.isBlank(" ") | true | **Calidad de Datos:** Evita "engaños" al sistema mediante el uso de espacios en blanco. |
| J13 | "   " | StringUtils.isBlank("   ") | true | **Normalización:** Trata cualquier cantidad de espacios como ausencia de información. |
| J14 | "a" | StringUtils.isBlank("a") | false | **Precisión de Búsqueda:** Confirma la detección correcta de contenido real. |
| J15 | "  a  " | StringUtils.isBlank("  a  ") | false | **Robustez de Input:** Detecta contenido real aunque esté rodeado de espacios laterales. |
| J16 | "0" | StringUtils.isBlank("0") | false | **Seguridad de Tipos:** Asegura que caracteres válidos como el cero no sean descartados por error. |
| J17 | "." | StringUtils.isBlank(".") | false | **Integridad de Sintaxis:** Garantiza que símbolos especiales sean tratados como contenido real. |
| J18 | null, "" | StringUtils.defaultString(null) | "" | **Robustez Extrema:** Proporciona valores de fallback para eliminar errores en capas superiores. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsBlankAndDefaultJUnitTest
```

#### C. Clase: StringUtilsComparisonSearchJUnitTest (7 Casos)
Propósito y Relevancia: Integridad en la búsqueda. Localización segura de información.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| J19 | null, null | StringUtils.equals(null, null) | true | **Consistencia Lógica:** Maneja la igualdad de nulos de forma segura sin lanzar excepciones. |
| J20 | null, "a" | StringUtils.compare(null, "a") | < 0 (negativo) | **Algoritmos de Clasificación:** Define un ordenamiento predecible para nulos en listas y tablas. |
| J21 | "abracadabra", "abra" | StringUtils.contains("abracadabra", "abra") | true | **Eficiencia de Filtrado:** Valida la capacidad base de encontrar fragmentos de texto. |
| J22 | "abracadabra", "cad" | StringUtils.contains("abracadabra", "cad") | true | **Flexibilidad de Motor:** Asegura que se encuentren fragmentos en cualquier posición del texto. |
| J23 | "abracadabra", "xyz" | StringUtils.contains("abracadabra", "xyz") | false | **Fiabilidad de Negación:** Garantiza que no existan falsos positivos en las búsquedas. |
| J24 | "", "a" | StringUtils.contains("", "a") | false | **Manejo de Error de Usuario:** Evita errores de índice al buscar en cadenas vacías. |
| J25 | null, "a" | StringUtils.indexOf(null, "a") | -1 | **Control de Flujo:** Asegura un retorno predecible para evitar bloques try-catch pesados. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsComparisonSearchJUnitTest
```

#### D. Clase: StringUtilsTransformationJUnitTest (4 Casos)
Propósito y Relevancia: Normalización de formatos. Asegura estándares visuales.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| J26 | "  hola  " | StringUtils.trim("  hola  ") | "hola" | **Higiene de Datos:** Crucial para normalizar entradas antes de persistir en base de datos. |
| J27 | "hola" | StringUtils.capitalize("hola") | "Hola" | **Estandarización Regional:** Asegura que nombres propios sigan reglas de formato profesional. |
| J28 | "áéíóú ñ" | StringUtils.stripAccents("áéíóú ñ") | "aeiou n" | **Fuzzy Search:** Normaliza texto para mejorar la usabilidad en búsquedas con/sin acentos. |
| J29 | "abc", 1 | StringUtils.rotate("abc", 1) | "cab" | **Utilidad de Algoritmos:** Operación base para manipulación estructural de cadenas e IDs. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsTransformationJUnitTest
```

#### E-F. Otras Utilidades (7 Casos)
Ubicación: StringUtilsCollectionReplacementJUnitTest, StringUtilsSubstringJUnitTest

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| J30 | "a,b", ',' | StringUtils.split("a,b", ',') | ["a", "b"] | **Serialización de Datos:** Permite convertir listas en cadenas y viceversa de forma estructurada. |
| J31 | "hello", "l", "x", 2 | StringUtils.replace("hello", "l", "x", 2) | "hexxo" | **Seguridad y Enmascaramiento:** Fundamental para ocultar o transformar datos sensibles (GDPR). |
| J32 | "a", 3, '0' | StringUtils.leftPad("a", 3, '0') | "00a" | **Alineación de Reportes:** Garantiza legibilidad humana en tablas y tickets alineando datos. |
| J33 | "hola".getBytes(), UTF_8 | StringUtils.toEncodedString(...) | "hola" | **Interoperabilidad:** Asegura la correcta decodificación de caracteres entre diferentes sistemas. |
| J34 | "abc", -3 | StringUtils.substring("abc", -3) | "abc" | **Resiliencia de Extracción:** Maneja índices fuera de rango o negativos sin lanzar excepciones. |
| J35 | "abc", 2 | StringUtils.right("abc", 2) | "bc" | **Simplicidad de Desarrollo:** Ofrece funciones de alto nivel para manipular extremos de cadenas. |
| J36 | "[dato]", "[", "]" | StringUtils.substringBetween("[dato]", "[", "]") | "dato" | **Parseo de Protocolos:** Facilita la extracción de información encapsulada en formatos específicos. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsCollectionReplacementJUnitTest,StringUtilsSubstringJUnitTest
```

---

### 2.2. Suite de Validación de Comportamiento (Mockito - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/mockito

#### G. Clase: StringUtilsAdvancedMockitoTest (6 Casos)
Propósito y Relevancia: Manejo de fallos externos y optimización.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| M1 | Mocked Supplier | StringUtils.getIfBlank(val, supplier) | IllegalStateException | **Transparencia de Errores:** Valida la propagación de excepciones desde servicios externos (Supplier). |
| M2 | "Valid", Mock Supplier | StringUtils.getIfBlank("Valid", supplier) | "Valid" | **Ahorro de Latencia:** Garantiza que no se llame al Supplier si el dato original ya es válido. |
| M3 | Mock CharSequence | StringUtils.contains(sequence, 'a') | Exception | **Seguridad de Memoria:** El sistema debe detenerse inmediatamente ante fallos en el flujo de datos. |
| M4 | Mock s1, Mock s2 | StringUtils.equalsIgnoreCase(s1, s2) | false | **Abstracción de Datos:** Valida la interacción con la interfaz CharSequence para procesar diversos buffers. |
| M5 | Mock sequence, 'a' | StringUtils.contains(sequence, 'a') | true | **Eficiencia de Acceso:** Verifica que solo se pida la información necesaria al objeto mockeado. |
| M6 | Mock s1, Mock s2 | StringUtils.firstNonBlank(s1, s2) | s1 | **Optimización de CPU:** Garantiza la evaluación perezosa (lazy) en lógicas de cortocircuito. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsAdvancedMockitoTest
```

#### H. Clase: StringUtilsCharSequenceClassificationMockitoTest (10 Casos)
Propósito y Relevancia: Validación de reglas de negocio sobre interfaces.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| M7 | Mocked digits | StringUtils.isNumeric(sequence) | true | **Validación de Seguridad:** Asegura que solo números entren en campos críticos, evitando inyecciones. |
| M8 | Mocked letter at start | StringUtils.isNumeric(sequence) | false | **Early Exit:** Detiene la lectura al primer carácter no numérico para ahorrar procesamiento. |
| M9 | Mocked whitespace | StringUtils.isWhitespace(sequence) | true | **Manejo de Protocolos:** Reconoce diversos tipos de espacios (tabs, saltos) mediante mocks. |
| M10 | Mocked visible char | StringUtils.isWhitespace(sequence) | false | **Precisión de Regla:** Protege la integridad del dato evitando confundir caracteres de control con espacios. |
| M11 | Mocked lowercase | StringUtils.isAllLowerCase(sequence) | true | **Cumplimiento de Estándares:** Validación de políticas de formato en nombres de usuario o correos. |
| M12 | Mocked uppercase | StringUtils.isAllUpperCase(sequence) | true | **Validación de Códigos:** Asegura que códigos de estado cumplan con formatos rígidos esperados. |
| M13 | Mocked mixed case | StringUtils.isMixedCase(sequence) | true | **Seguridad de Contraseñas:** Ayuda a verificar la variedad de caracteres para mayor seguridad. |
| M14 | Mocked seq, 'x', 'y' | StringUtils.containsNone(sequence, 'x', 'y') | true | **Prevención de XSS:** Bloquea caracteres peligrosos actuando como un firewall a nivel de código. |
| M15 | Mocked seq, 'a', 'b' | StringUtils.containsOnly(sequence, 'a', 'b') | true | **Whitelisting:** Solo permite caracteres conocidos, la técnica de seguridad más fuerte. |
| M16 | Mocked seq, 'z' | StringUtils.countMatches(sequence, 'z') | 0 | **Análisis de Frecuencia:** Permite validar la estructura del dato contando ocurrencias específicas. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsCharSequenceClassificationMockitoTest
```

#### I-L. Casos de Interacción y Límites (20 Casos)
Ubicación: StringUtilsSupplierMockitoTest, StringUtilsSupplierBoundaryMockitoTest, etc.

| ID | Entradas | Condiciones de Ejecución | Resultado Esperado | Relevancia Técnica |
| :--- | :--- | :--- | :--- | :--- |
| M17 | null, Supplier | StringUtils.getIfBlank(null, supplier) | "generated" | **Automatización de Fallback:** Pide valor al Supplier si la entrada es nula, asegurando disponibilidad. |
| M18 | "   ", Supplier | StringUtils.getIfBlank("   ", supplier) | "generated" | **Limpieza de UI:** Genera valores por defecto si la entrada solo contiene espacios en blanco. |
| M19 | "real", Supplier | StringUtils.getIfBlank("real", supplier) | "real" | **Respeto a la Fuente:** Protege la intención del usuario no sobreescribiendo datos válidos. |
| M20 | "", Supplier | StringUtils.getIfEmpty("", supplier) | "empty" | **Precisión Técnica:** Diferencia correctamente entre cadena vacía y espacios en blanco. |
| M21 | Supplier returns null | StringUtils.getIfBlank(null, supplier) | null | **Manejo de Errores:** Controla el escenario donde el respaldo también falla, evitando inconsistencias. |
| M22 | null, Supplier | StringUtils.getIfEmpty(null, supplier) | "fallback" | **Capa de Abstracción:** Unifica el tratamiento de nulos y vacíos en una sola llamada de función. |
| M23 | "text", Supplier | StringUtils.getIfEmpty("text", supplier) | "text" | **Eficiencia de Ejecución:** Evita ejecutar el Supplier si el dato de entrada ya es suficiente. |
| M24 | "", Supplier | StringUtils.getIfBlank("", supplier) | "fallback" | **Lógica de Seguridad:** Trata la cadena vacía como falta de información para reemplazo seguro. |
| M25 | Supplier returns null | StringUtils.getIfEmpty(null, supplier) | null | **Flexibilidad de Diseño:** Permite que el sistema de respaldo delegue el error de forma controlada. |
| M26 | Mock sequence, 'a' | StringUtils.countMatches(sequence, 'a') | 3 | **Exactitud Estadística:** Garantiza el escaneo completo de la secuencia sin errores de índice. |
| M27 | "abc", "xyz", Mock opt | StringUtils.equalsAny("abc", "xyz", option) | true | **Comparación Heterogénea:** Compara strings reales contra objetos simulados en pruebas complejas. |
| M28 | Mock sequence (empty) | StringUtils.isEmpty(sequence) | true | **Optimización de Velocidad:** Solo consulta el tamaño del objeto, evitando lecturas costosas de caracteres. |
| M29 | Mock sequence (text) | StringUtils.isBlank(sequence) | false | **Consumo Inteligente:** Solo lee el Mock hasta determinar el resultado, minimizando tráfico interno. |
| M30 | Mock seq (w/space) | StringUtils.containsWhitespace(sequence) | true | **Early Exit Pattern:** Valida que el escaneo se detenga al encontrar el primer espacio. |
| M31 | Mock seq (digit) | StringUtils.isAlpha(sequence) | false | **Validación Rápida:** Detiene el escaneo al detectar el primer carácter no alfabético. |
| M32 | "hola", Mock prefix | StringUtils.startsWithIgnoreCase("hola", prefix) | true | **Usabilidad Global:** Permite comparaciones de prefijos sin importar el caso (mayúsculas/minúsculas). |
| M33 | "hola", Mock suffix | StringUtils.endsWithIgnoreCase("hola", suffix) | true | **Validación de Extensiones:** Útil para validar tipos de archivos mediante sufijos mockeados. |
| M34 | "hola", Mock prefix | StringUtils.startsWithAny("hola", prefix) | true | **Ruteo Dinámico:** Compara el inicio de una trama contra múltiples opciones de forma eficiente. |
| M35 | "hola", Mock suffix | StringUtils.endsWithAny("hola", suffix) | true | **Filtrado de Formatos:** Valida finales de cadena complejos contra múltiples sufijos permitidos. |
| M36 | "hola", Mock search | StringUtils.containsAny("hola", search) | true | **Detección de Patrones:** Capacidad de localizar secuencias prohibidas/permitidas para moderación. |

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
