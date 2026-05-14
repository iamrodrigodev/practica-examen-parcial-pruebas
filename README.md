# Informe Técnico de Ingeniería de Software: Especificación de Pruebas Unitarias

## 1. Introducción y Marco Normativo
Este documento presenta la especificación exhaustiva de la suite de pruebas para StringUtils. Se fundamenta en el estándar IEEE 610, tratando cada test como un Caso de Prueba compuesto por entradas, condiciones de ejecución y resultados esperados.

---

## 2. Especificación Detallada y Relevancia de los Casos de Prueba

### 2.1. Suite de Verificación Lógica (JUnit 5 - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/junit

#### A. Clase: StringUtilsAdvancedJUnitTest (9 Casos)
Propósito y Relevancia: Control de visualización y parseo robusto. Estas funciones son críticas en interfaces de usuario (UI) para prevenir desbordamientos de texto.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- | :--- |
| J1 | Validación ancho mínimo | text, marker, width=3 | **Prevención de Regresiones:** Asegura que el sistema falle explícitamente si un desarrollador intenta "meter" un marcador (como "...") en un espacio donde no cabe. Sin esto, el sistema podría quedar en un bucle infinito o retornar basura visual. |
| J2 | Validación ancho c/offset | text, marker, offset=6, width=6 | **Consistencia de Algoritmo:** Valida la lógica de desplazamiento. Es vital en sistemas de logs o previsualización de archivos donde solo queremos mostrar el "centro" de un dato pero respetando el límite visual. |
| J3 | Abreviación nula | null, 10 | **Seguridad en Producción:** Los datos nulos son la causa #1 de caídas en producción. Este test garantiza que la librería sea "null-safe", permitiendo que el sistema siga funcionando sin crashear ante datos incompletos. |
| J4 | Abreviación vacía | "", 4 | **Eficiencia Operacional:** Valida que el sistema no gaste ciclos de CPU intentando abreviar algo que no existe. Retornar inmediatamente una cadena vacía ahorra recursos en procesos masivos. |
| J5 | Truncado estándar | "abcdefg", 6 | **Integridad de UX:** Asegura que el usuario reciba el feedback visual correcto ("abc...") cuando el texto es demasiado largo, manteniendo la estética de la interfaz y evitando que el texto "pise" otros elementos. |
| J6 | Límite exacto | "abcdefg", 7 | **Precisión de Datos:** Verifica que el algoritmo sea "justo"; si el dato cabe exactamente, no debe modificarse. Esto previene la pérdida innecesaria de información en reportes o tablas. |
| J7 | Sobre-límite | "abcdefg", 8 | **Optimización de Memoria:** Garantiza que si no hay nada que truncar, no se cree un nuevo objeto String innecesario, optimizando el uso de memoria RAM (Heap) al reutilizar la referencia original. |
| J8 | Truncado agresivo | "abcdefg", 4 | **Manejo de Casos Borde:** Prueba el sistema bajo condiciones extremas (ancho mínimo). Es fundamental para asegurar la robustez del código ante configuraciones de pantalla muy pequeñas o móviles. |
| J9 | Split complejo | "ab--cd----ef", "--" | **Integridad de Datos en ETL:** Esencial para procesar archivos CSV o logs donde los delimitadores pueden venir duplicados por errores en la fuente. Garantiza que no se pierdan "tokens" vacíos que podrían desplazar las columnas de una base de datos. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsAdvancedJUnitTest
```

#### B. Clase: StringUtilsBlankAndDefaultJUnitTest (9 Casos)
Propósito y Relevancia: Sanitización de entradas. Primera línea de defensa contra datos vacíos.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- | :--- |
| J10 | isBlank Null | null | **Prevención de NPE:** Identificar un nulo como "blanco" permite que el resto del programa use una lógica unificada de validación sin tener que preguntar `if (x != null)` en cada línea. |
| J11 | isBlank Empty | "" | **Validación de Formularios:** Diferencia entre un campo que nunca se tocó y uno que el usuario borró. Ayuda a aplicar reglas de negocio como "Campo Obligatorio" de forma precisa. |
| J12 | isBlank Space | " " | **Calidad de Datos:** Evita que un usuario "engañe" al sistema presionando la barra espaciadora para saltarse una validación de campo requerido. |
| J13 | isBlank Whitespace | "   " | **Normalización:** Garantiza que cualquier cantidad de espacios sea tratada como ausencia de información, manteniendo la base de datos limpia de "basura" invisible. |
| J14 | isBlank Visible | "a" | **Precisión de Búsqueda:** Confirma que el sistema reconoce correctamente cuando SÍ hay datos, evitando falsos negativos en procesos de indexación. |
| J15 | isBlank c/Espacios | "  a  " | **Robustez de Input:** Valida que el contenido real sea detectado aunque esté "sucio" con espacios laterales, muy común cuando los usuarios copian y pegan datos de Excel. |
| J16 | isBlank Numérico | "0" | **Seguridad de Tipos:** Asegura que caracteres que parecen "vacíos" para un humano (pero que son datos válidos como el cero) no sean descartados por error, protegiendo la integridad financiera o estadística. |
| J17 | isBlank Símbolo | "." | **Integridad de Sintaxis:** Similar al numérico, garantiza que símbolos especiales sean tratados como contenido real, vital para procesar fórmulas o códigos. |
| J18 | Valores por defecto | null -> "" | **Robustez Extrema:** Al proporcionar un valor de "fallback" (como una cadena vacía), se elimina la posibilidad de errores en capas superiores que no sepan manejar nulos, actuando como un escudo protector. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsBlankAndDefaultJUnitTest
```

#### C. Clase: StringUtilsComparisonSearchJUnitTest (7 Casos)
Propósito y Relevancia: Integridad en la búsqueda. Localización segura de información.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- | :--- |
| J19 | Comparación Null-Safe | equals(null, null) | **Consistencia Lógica:** En Java, `null.equals()` lanzaría error. Este test garantiza que la librería maneje la igualdad de nulos de forma matemática y segura, facilitando comparaciones en colecciones. |
| J20 | Ordenamiento Null | compare(null, "a") | **Algoritmos de Clasificación:** Define qué pasa cuando intentas ordenar una lista que tiene nulos. Sin esto, los algoritmos de Sort (como en tablas de usuarios) fallarían de forma impredecible. |
| J21 | Búsqueda Positiva | contains("abra") | **Eficiencia de Filtrado:** Valida la capacidad base de encontrar datos. Es el motor detrás de cualquier barra de búsqueda "Type-ahead" o filtro de tabla. |
| J22 | Búsqueda Media | contains("cad") | **Flexibilidad de Motor:** Asegura que la búsqueda no esté limitada al inicio del texto. Es crítico para encontrar fragmentos de códigos de error o IDs dentro de mensajes largos. |
| J23 | Búsqueda Negativa | contains("xyz") | **Fiabilidad de Negación:** Garantiza que el sistema no dé falsos positivos. Un falso positivo en una búsqueda podría llevar a procesar el registro equivocado en un sistema de nómina o salud. |
| J24 | Búsqueda en Vacío | contains('', 'a') | **Manejo de Error de Usuario:** Valida que buscar algo en "nada" retorne correctamente falso, evitando errores de desbordamiento de índice (IndexOutOfBounds). |
| J25 | Índice de falla | indexOf(null) | **Control de Flujo:** Asegura un retorno predecible (-1) en lugar de una excepción. Esto permite escribir código más limpio: `if (indexOf(x) > -1)` sin bloques try-catch pesados. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsComparisonSearchJUnitTest
```

#### D. Clase: StringUtilsTransformationJUnitTest (4 Casos)
Propósito y Relevancia: Normalización de formatos. Asegura estándares visuales.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- | :--- |
| J26 | Limpieza Espacios | trim, normalizeSpace | **Higiene de Datos en DB:** Crucial antes de guardar en base de datos. Eliminar saltos de línea y tabulaciones invisibles previene que el mismo nombre (ej: "Juan" y " Juan") se guarde como dos registros distintos. |
| J27 | Cambio de Caso | Capitalize, Locale | **Estandarización Regional:** Asegura que los nombres propios se vean profesionales (mayúscula inicial). El soporte de `Locale` garantiza que reglas de idiomas como el Turco o Alemán no rompan el formato. |
| J28 | Eliminación Acentos | "áéíóú ñ" | **Optimización de Búsqueda (Fuzzy Search):** Convierte texto "sucio" en texto base para que cuando alguien busque "cancion" encuentre "canción". Vital para la usabilidad en países hispanohablantes. |
| J29 | Manipulación Estructural| reverse, rotate, repeat | **Utilidad de Algoritmos:** Operaciones base para generar IDs temporales, ofuscar datos ligeramente o crear separadores visuales en reportes de consola de forma rápida. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsTransformationJUnitTest
```

#### E-F. Otras Utilidades (7 Casos)
| ID | Objetivo | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- |
| J30 | Split/Join Tokens | **Serialización de Datos:** Permite convertir listas en una sola cadena (y viceversa) para guardar en archivos planos o enviar por red de forma compacta y estructurada. |
| J31 | Replace/Overlay | **Seguridad y Enmascaramiento:** Fundamental para ocultar datos sensibles. Por ejemplo, transformar "12345678" en "****5678" antes de mostrarlo en pantalla, cumpliendo con leyes de protección de datos (GDPR). |
| J32 | Padding/Abbrev Limits | **Alineación de Reportes:** Garantiza que los datos en tablas de texto o tickets de venta salgan alineados (rellenando con ceros o espacios), mejorando la legibilidad humana. |
| J33 | Codificación Charset | **Interoperabilidad de Sistemas:** Asegura que si recibimos datos de un sistema Linux o Windows antiguo, el texto no se corrompa (evita los símbolos raros como ), garantizando la comunicación entre servidores. |
| J34 | Substring Límites | **Resiliencia de Extracción:** Permite "cortar" texto sin miedo a que el programa se detenga si los índices están mal calculados (ej: pedir los últimos 5 caracteres de una palabra de 3). La librería maneja el error por nosotros. |
| J35 | Left/Right/Mid | **Simplicidad de Desarrollo:** Ofrece funciones de alto nivel similares a Excel. Esto reduce la posibilidad de errores manuales al manipular índices de arreglos, lo cual es una fuente común de bugs. |
| J36 | Substring Delimitado | **Parseo de Protocolos:** Vital para extraer información de formatos como `[ID:123]`. Permite obtener solo el "123" de forma limpia, facilitando la integración con APIs externas. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsCollectionReplacementJUnitTest,StringUtilsSubstringJUnitTest
```

---

### 2.2. Suite de Validación de Comportamiento (Mockito - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/mockito

#### G. Clase: StringUtilsAdvancedMockitoTest (6 Casos)
Propósito y Relevancia: Manejo de fallos externos y optimización.

| ID | Objetivo | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- |
| M1 | Propagación Excepción | **Transparencia de Errores:** Valida que la librería no "se coma" los errores graves. Si la base de datos (Supplier) falla, el programador debe saberlo de inmediato para actuar, en lugar de recibir un dato vacío falso. |
| M2 | Ignorar Supplier | **Ahorro de Costos y Latencia:** En la nube (AWS/Azure), cada llamada a una función o DB cuesta dinero y tiempo. Este test garantiza que NO llamemos a esos servicios si ya tenemos el dato que necesitamos. |
| M3 | Detención en Error | **Seguridad de Memoria:** Si un flujo de datos se corrompe en medio de la lectura, el sistema debe detenerse. Esto evita que se procesen datos parciales que podrían corromper la base de datos final. |
| M4 | Interacción Equals | **Abstracción de Datos:** Valida que la librería trabaje con la interfaz `CharSequence`. Esto permite comparar Strings contra Buffers de memoria o archivos directamente, sin tener que cargar todo el archivo en la RAM. |
| M5 | Interacción Contains | **Eficiencia de Acceso:** Verifica que la librería solo pida la información necesaria al objeto (Mock). Esto asegura que no haya lecturas redundantes que degraden el rendimiento. |
| M6 | Evaluación Perezosa | **Optimización de CPU:** Garantiza que si el primer valor ya es válido, el sistema ni siquiera mire el segundo. Es fundamental en lógicas de "Cortocircuito" para aplicaciones de alta concurrencia. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsAdvancedMockitoTest
```

#### H. Clase: StringUtilsCharSequenceClassificationMockitoTest (10 Casos)
Propósito y Relevancia: Validación de reglas de negocio sobre interfaces.

| ID | Objetivo | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- |
| M7 | isNumeric Positivo | **Validación de Seguridad:** Asegura que solo números entren en campos críticos (como montos de dinero), previniendo ataques de inyección de caracteres o errores de casteo. |
| M8 | isNumeric Early Exit | **Rendimiento Algorítmico:** Si una cadena de 1 millón de caracteres tiene una 'A' al principio, no debemos leer los otros 999,999. Este test garantiza que el sistema salga rápido, ahorrando tiempo de procesamiento. |
| M9 | isWhitespace Acepta | **Manejo de Protocolos:** Reconoce no solo espacios, sino tabulaciones y saltos de línea mockeados, asegurando que el sistema entienda el "vaciado" en diferentes sistemas operativos. |
| M10 | isWhitespace Rechaza | **Precisión de Regla:** Garantiza que caracteres invisibles pero con valor (como caracteres de control) no sean confundidos con espacios, protegiendo la integridad del dato. |
| M11 | isAllLowerCase | **Cumplimiento de Estándares:** Vital para validar nombres de usuario o correos electrónicos que por política deben ser minúsculas, evitando duplicidad de cuentas. |
| M12 | isAllUpperCase | **Validación de Códigos:** Asegura que códigos de estado (como 'ERR', 'OK') cumplan con el formato rígido esperado por otros sistemas automáticos. |
| M13 | isMixedCase | **Seguridad de Contraseñas:** Ayuda a verificar la complejidad de una cadena, asegurando que haya variedad de caracteres para mayor seguridad. |
| M14 | containsNone | **Prevención de XSS/Inyección:** Verifica que caracteres peligrosos (como `< > ;`) no estén presentes, actuando como un firewall básico a nivel de código. |
| M15 | containsOnly | **Whitelisting:** La técnica de seguridad más fuerte. Solo permite caracteres conocidos, bloqueando cualquier intento de enviar datos maliciosos o mal formados. |
| M16 | countMatches Char | **Análisis de Frecuencia:** Permite contar cuántas veces ocurre algo (ej: cuántos separadores hay) para decidir si un dato es válido antes de intentar procesarlo. |

**Comando de ejecución individual:**
```bash
mvn test -Dtest=StringUtilsCharSequenceClassificationMockitoTest
```

#### I-L. Casos de Interacción y Límites (20 Casos)
| ID | Objetivo | Propósito y Relevancia Técnica (Por qué y para qué) |
| :--- | :--- | :--- |
| M17 | getIfBlank (Null) | **Automatización de Fallback:** Garantiza que si un servicio externo falla (null), se pida automáticamente un valor de respaldo al `Supplier`, manteniendo la app siempre disponible. |
| M18 | getIfBlank (Blank) | **Limpieza de UI:** Si un usuario solo manda espacios, el sistema lo detecta y genera un valor por defecto (como "Usuario Anónimo"), mejorando la estética de la app. |
| M19 | getIfBlank (Text) | **Respeto a la Fuente:** Asegura que no sobre-escribamos datos válidos con valores por defecto, protegiendo la intención original del usuario. |
| M20 | getIfEmpty (Empty) | **Precisión Técnica:** Es la diferencia entre "No hay nada" (Empty) y "Hay algo invisible" (Space). Este test garantiza que la lógica de negocio sea exacta según el requerimiento. |
| M21 | Supplier devuelve Null| **Manejo de Cadena de Errores:** Valida qué pasa si el respaldo también falla. Evita que el sistema entre en un estado inconsistente de error sobre error. |
| M22 | getIfEmpty (Input Null)| **Capa de Abstracción:** Unifica el tratamiento de nulos y vacíos en una sola llamada de función, reduciendo la complejidad del código fuente (Clean Code). |
| M23 | getIfEmpty (Input Text)| **Eficiencia de Ejecución:** Evita ejecutar lógica pesada de respaldo si el dato de entrada ya es suficiente, optimizando la batería en dispositivos móviles. |
| M24 | getIfBlank (Input Empty)| **Lógica de Seguridad:** Trata la cadena vacía como una falta de información, obligando a generar un valor seguro de reemplazo. |
| M25 | getIfEmpty Fallback Null| **Flexibilidad de Diseño:** Permite que el sistema de respaldo decida que tampoco tiene el dato, permitiendo que el flujo de error siga su curso natural de forma controlada. |
| M26 | countMatches Full Scan| **Exactitud Estadística:** Garantiza que no se pierda ninguna ocurrencia por errores de "off-by-one" (contar uno de menos o de más) en el bucle. |
| M27 | equalsAny Mock | **Comparación Heterogénea:** Permite comparar un objeto String real contra una lista de objetos simulados, facilitando pruebas de integración complejas. |
| M28 | isEmpty Length | **Optimización de Velocidad:** Verifica que la librería sea inteligente y solo pregunte por el tamaño del objeto, evitando la lectura costosa de caracteres individuales si no es necesario. |
| M29 | isBlank Interaction | **Consumo Inteligente:** Garantiza que el sistema solo lea el Mock hasta que esté seguro del resultado, minimizando el tráfico de datos interno. |
| M30 | containsWhitespace Exit| **Early Exit Pattern:** Valida que en cuanto se encuentra un espacio, se detenga. En textos masivos, esto puede ser la diferencia entre milisegundos y segundos de espera. |
| M31 | isAlpha Early Exit | **Validación Rápida:** Detiene el escaneo al primer carácter no válido, protegiendo al sistema de procesar datos basura de forma ineficiente. |
| M32 | startsWithIgnoreCase | **Usabilidad Global:** Permite que "HOLA" y "hola" sean lo mismo sin importar cómo se implemente el objeto de texto, facilitando búsquedas amigables. |
| M33 | endsWithIgnoreCase | **Validación de Extensiones:** Útil para validar que un archivo termina en `.JPG` o `.jpg` de forma segura mediante interacciones con mocks de nombres de archivo. |
| M34 | startsWithAny | **Ruteo Dinámico:** Permite comparar el inicio de una trama contra múltiples opciones de forma rápida, vital en el desarrollo de controladores o routers. |
| M35 | endsWithAny | **Filtrado de Formatos:** Similar al de inicio, pero para validar finales de cadena complejos (como sufijos de dominios o protocolos). |
| M36 | containsAny | **Detección de Patrones:** Capacidad de localizar cualquier secuencia prohibida o permitida dentro de un texto, vital para sistemas de moderación de contenido. |

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
