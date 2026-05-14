# Práctica: Pruebas Unitarias Avanzadas con StringUtils

PROYECTO DE PREPARACIÓN PARA EXAMEN PARCIAL - PRUEBAS DE SOFTWARE

## 🎯 Objetivo del Proyecto
Implementar una suite de pruebas de alta calidad para la clase `StringUtils` de Apache Commons. El enfoque principal es demostrar el uso correcto de **JUnit 5** para validación de algoritmos y **Mockito** para la verificación de comportamiento e interacciones.

---

## 📊 Metodología de Pruebas (Balance 50/50)

Para cumplir con el requisito académico, se han implementado **72 casos de prueba** divididos equitativamente:

### 1. Suite JUnit (36 Tests - Enfoque: Lógica y Algoritmos)
Ubicada en `src/test/java/com/example/stringutils/junit`. Se centra en la pureza del código y el manejo de datos.

| Categoría de Test | Casos de Prueba Detallados | Razón de la Elección |
| :--- | :--- | :--- |
| **Validación de Estados** | `isBlank`, `isEmpty`, `isAnyBlank`. Pruebas con `null`, `""`, `" "`, `\t`, `\n`. | Es la base de la robustez. Se usa `@ParameterizedTest` para cubrir todas las variantes de "vacío" en una sola lógica. |
| **Manipulación y Límites** | `substring`, `left`, `right`, `mid`. Pruebas con índices negativos y fuera de rango. | `StringUtils` destaca por no lanzar excepciones en estos casos; el test asegura que devuelva `EMPTY` o el string original según la regla. |
| **Algoritmos Complejos** | `splitByWholeSeparatorPreserveAllTokens`, `abbreviate`. Pruebas con separadores múltiples y tokens vacíos adyacentes. | Valida que la lógica de partición no "pierda" datos cuando hay separadores seguidos, un error común en implementaciones simples. |
| **Manejo de Errores** | `assertThrows` en `abbreviate` con anchos menores al marcador (ej: < 4). | Verifica que la clase proteja su integridad lanzando `IllegalArgumentException` cuando los parámetros violan la lógica del negocio. |

### 2. Suite Mockito (36 Tests - Enfoque: Interacciones y Comportamiento)
Ubicada en `src/test/java/com/example/stringutils/mockito`. Se centra en cómo `StringUtils` interactúa con objetos externos.

| Categoría de Test | Casos de Prueba Detallados | Razón de la Elección |
| :--- | :--- | :--- |
| **Lazy Evaluation (Supplier)** | `getIfBlank(str, Supplier)`. Uso de `when(...).thenReturn(...)` y `verify(..., never())`. | Demuestra que el `Supplier` solo se ejecuta si el string es blanco. Es una prueba de **eficiencia y rendimiento**. |
| **Interacción con Interfaces** | Mocks de `CharSequence`. Verificación de llamadas a `.length()` y `.charAt(i)`. | `StringUtils` trabaja con interfaces, no solo con `String`. El mock permite contar cuántas veces se consulta el objeto para validar el algoritmo. |
| **Propagación de Fallos** | Simulación de excepciones en `Supplier` o `CharSequence`. | Asegura que si una dependencia externa falla, `StringUtils` no oculte el error de forma silenciosa, sino que lo propague correctamente. |
| **Control de Flujo** | `verifyNoInteractions` y `verifyNoMoreInteractions`. | Es el criterio de "limpieza". Asegura que el código no haga llamadas innecesarias a objetos después de haber encontrado un resultado (Early Exit). |

---

## 🛠️ Tecnologías y Estándares
- **Java 21**: Uso de records y sintaxis moderna.
- **Maven**: Gestión de ciclo de vida.
- **JUnit 5**: Uso de `@DisplayName`, `@ParameterizedTest` y `assertAll`.
- **Mockito 5**: Uso de `@Mock`, `verify` y stubs avanzados.
- **Conventional Commits**: Historial de git organizado por tipos (`feat`, `fix`, `refactor`, `docs`).

---

## 🚀 Ejecución de Pruebas
Para validar el balance y la aprobación total:
```bash
mvn test
```
*Total de pruebas esperado: 72 (36 JUnit / 36 Mockito)*
