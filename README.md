# Informe Técnico: Análisis, Diseño y Ejecución de Pruebas Unitarias

## 1. Introducción y Marco Teórico
Este proyecto se fundamenta en los principios de la **Ingeniería de Requerimientos** y el **Diseño de Pruebas** según el estándar **IEEE 610**. El objetivo primordial es la verificación y validación de la clase `StringUtils` mediante una suite de pruebas técnica y formalmente estructurada.

### 1.1. Definición de Caso de Prueba (IEEE 610)
Cada prueba implementada en este repositorio cumple estrictamente con la definición normativa:
> "Un conjunto de entradas de prueba, condiciones de ejecución y resultados esperados desarrollados con un objetivo particular."

### 1.2. Verificación vs. Validación
- **Verificación:** Se garantiza que los métodos de `StringUtils` (como `abbreviate` o `split`) se comporten exactamente según la especificación técnica de Apache Commons, construyendo la lógica correctamente.
- **Validación:** Se asegura que las pruebas reflejen las necesidades reales de los desarrolladores que consumen la librería, garantizando que el sistema sea consistente, completo e inequívoco.

---

## 2. Metodología de Diseño de Pruebas
El diseño se ha realizado analizando los requisitos funcionales para determinar las **condiciones de prueba** óptimas.

### 2.1. Técnicas de Diseño Empleadas
Se han utilizado diversas técnicas para cubrir los requisitos de manera exhaustiva:
- **Análisis de Valores Límite:** Aplicado en tests de `substring` y `abbreviate` para verificar el comportamiento en las fronteras de los índices y anchos permitidos.
- **Partición de Equivalencia:** Clasificación de entradas en grupos (null, empty, whitespace, valid text) para reducir la redundancia y maximizar la cobertura.
- **Pruebas de Estado de Dependencias (Mocking):** Validación del comportamiento del sistema ante fallos o respuestas específicas de sus colaboradores (`Supplier`, `CharSequence`).

---

## 3. Estructura de la Suite (Balance 50/50)

### 3.1. Casos de Prueba JUnit (Verificación de Requisitos Funcionales)
**Total: 36 Tests**
- **Objetivo:** Encontrar defectos en la lógica algorítmica y brindar confianza sobre la calidad del software.
- **Procedimiento:** Definición de entradas precisas e inequívocas mediante `@ParameterizedTest`.
- **Resultados Esperados:** Validación de la salida contra el estándar esperado (ej: `assertEquals`, `assertThrows`).

| Clase de Prueba | Condición de Prueba | Técnica |
| :--- | :--- | :--- |
| `AdvancedJUnitTest` | Manejo de anchos insuficientes. | Pruebas Negativas (Excepciones) |
| `BlankAndDefaultJUnitTest` | Consistencia en la definición de "Blank". | Partición de Equivalencia |
| `SubstringJUnitTest` | Precisión en el manejo de índices out-of-bounds. | Valores Límite |

### 3.2. Casos de Prueba Mockito (Validación de Interacciones)
**Total: 36 Tests**
- **Objetivo:** Garantizar que los requisitos se aborden adecuadamente en la interacción con interfaces y evitar efectos secundarios.
- **Procedimiento:** Inyección de mocks para controlar las condiciones de ejecución y verificar el flujo de control.
- **Resultados Esperados:** Verificación de interacciones (ej: `verify`, `verifyNoInteractions`).

| Clase de Prueba | Condición de Prueba | Técnica |
| :--- | :--- | :--- |
| `AdvancedMockitoTest` | Propagación de fallos desde dependencias. | Inyección de Errores |
| `SupplierMockitoTest` | Evaluación perezosa (Lazy Evaluation). | Verificación de Comportamiento |
| `CharSequenceMockitoTest` | Eficiencia en el recorrido de secuencias. | Verificación de Interacciones |

---

## 4. Trazabilidad y Repetibilidad
Para garantizar la **repetibilidad**, los procedimientos de prueba están automatizados mediante **Maven**. La matriz de trazabilidad se gestiona implícitamente a través de las anotaciones `@DisplayName`, vinculando cada test a un requerimiento específico de la funcionalidad de `StringUtils`.

## 5. Ejecución
```bash
mvn test
```
*Total: 72 Casos de Prueba (36 Verificación Lógica / 36 Validación de Comportamiento)*
