# Informe Técnico de Ingeniería de Software: Especificación de Pruebas Unitarias

## 1. Introducción y Marco Normativo
Este documento presenta la especificación exhaustiva de la suite de pruebas para StringUtils. Se fundamenta en el estándar IEEE 610, tratando cada test como un Caso de Prueba compuesto por entradas, condiciones de ejecución y resultados esperados.

---

## 2. Especificación Detallada y Relevancia de los Casos de Prueba

### 2.1. Suite de Verificación Lógica (JUnit 5 - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/junit

#### A. Clase: StringUtilsAdvancedJUnitTest (9 Casos)
Propósito y Relevancia: Control de visualización y parseo robusto. Estas funciones son críticas en interfaces de usuario (UI) para prevenir desbordamientos de texto y en el procesamiento de archivos (Logs, CSV) donde la integridad de los tokens es vital.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J1-J2| Validación de anchos. | Textos y marcadores variados. | Prevención de Errores: Asegura que el sistema no intente "abreviar" en espacios físicamente imposibles, evitando bugs visuales. |
| J3-J8| Abreviación segura. | Offsets y límites exactos. | UX/UI: Garantiza que la información crítica se muestre de forma legible sin importar el tamaño del contenedor. |
| J9 | Split complejo. | "ab--cd----ef", "--" | Integridad de Datos: Vital para sistemas que consumen datos de terceros donde los delimitadores pueden estar duplicados. |

#### B. Clase: StringUtilsBlankAndDefaultJUnitTest (9 Casos)
Propósito y Relevancia: Sanitización de entradas. Es la primera línea de defensa en cualquier aplicación para evitar que datos vacíos o nulos lleguen a la lógica de negocio o base de datos.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J10-17| isBlank vs isEmpty. | null, "", "  ", \t | Seguridad de Tipos: Diferenciar entre un campo "no tocado" y uno "llenado solo con espacios" para aplicar reglas de validación correctas. |
| J18 | Valores por defecto. | defaultString | Robustez: Evita el clásico NullPointerException al proporcionar un valor seguro de fallback automáticamente. |

#### C. Clase: StringUtilsComparisonSearchJUnitTest (7 Casos)
Propósito y Relevancia: Integridad en la búsqueda. Permite localizar información dentro de grandes volúmenes de texto de forma segura y eficiente.

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J19-20| Comparación segura. | Nulos y diferentes casos. | Consistencia: Permite ordenar y comparar datos de usuarios (ej: nombres) sin que el sistema falle por valores nulos. |
| J21-25| Búsqueda y Diferencia. | containsAny, indexOfDiff | Análisis de Datos: Útil para comparar versiones de un mismo registro o detectar cambios en campos de texto largo. |

#### D. Clase: StringUtilsTransformationJUnitTest (4 Casos)
Propósito y Relevancia: Normalización de formatos. Asegura que los datos sigan un estándar visual o técnico (ej: nombres en mayúscula inicial).

| ID | Objetivo | Entradas (Input) | Propósito y Relevancia Técnica |
| :--- | :--- | :--- | :--- |
| J26-29| Capitalize, Swap, Rotate. | "cat", Locale, "abcde" | Estandarización: Crucial para reportes y exportación de datos donde el formato debe ser uniforme y profesional. |

#### E-F. Otras Utilidades (7 Casos)
| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| J30-33| Join, Overlay, Replace. | Formateo: Generación de cadenas complejas (ej: IDs, números de tarjeta enmascarados) de forma segura. |
| J34-36| Substrings y Delimitadores. | Extracción: Permite diseccionar strings (ej: extraer el dominio de un correo) sin riesgos de errores de índice. |

---

### 2.2. Suite de Validación de Comportamiento (Mockito - 36 Casos)
Ubicación: src/test/java/com/example/stringutils/mockito

#### G. Clase: StringUtilsAdvancedMockitoTest (6 Casos)
Propósito y Relevancia: Optimización de recursos y manejo de fallos externos. Valida que el sistema sea eficiente y no se "rompa" ante errores de sus dependencias.

| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| M1-M2| Propagación y Eficiencia. | Resiliencia: Asegura que si una base de datos (Supplier) falla, el sistema lo maneje, y que solo se llame a la BD si es estrictamente necesario. |
| M3-M5| Interacción con Interfaces. | Abstracción: Valida que el código funcione con cualquier objeto de texto (CharSequence), no solo con String, permitiendo mayor flexibilidad. |
| M6 | Evaluación Perezosa. | Performance: Garantiza que no se gasten ciclos de CPU procesando el segundo parámetro si el primero ya resolvió la necesidad. |

#### H. Clase: StringUtilsCharSequenceClassificationMockitoTest (10 Casos)
Propósito y Relevancia: Validación de reglas de negocio. Asegura que los datos cumplen con el formato esperado antes de procesarlos.

| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| M7-16| Alpha, Numeric, Space. | Seguridad: Evita inyecciones de caracteres no deseados en campos sensibles como teléfonos, nombres o códigos postales. |

#### I-L. Casos de Interacción y Límites (20 Casos)
| ID | Objetivo | Propósito y Relevancia Técnica |
| :--- | :--- | :--- |
| M17-21| Supplier Behavior. | Flexibilidad: Permite que los valores por defecto sean dinámicos (calculados al momento) y no estáticos. |
| M22-25| Boundary Exceptions. | Estabilidad: Verifica que el sistema falle con gracia (NPE controlados) cuando se le pasan proveedores de datos nulos. |
| M26-29| Escaneo de Mocks. | Eficiencia de Algoritmo: Valida que StringUtils deje de leer el texto en cuanto encuentra lo que busca (Early Exit). |
| M30-36| Prefix/Suffix/Search. | Identificación: Crucial para ruteo de archivos, validación de URLs y protocolos de comunicación. |

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
