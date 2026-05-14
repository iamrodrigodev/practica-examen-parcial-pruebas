# Práctica: Pruebas Unitarias con StringUtils

Proyecto de implementación de pruebas unitarias avanzadas para la clase `StringUtils` (Apache Commons Lang 3), diseñado para cumplir con los estándares académicos de calidad y cobertura.

## Objetivo
Demostrar el dominio de pruebas unitarias mediante la validación de lógica pura y comportamiento de dependencias en una de las librerías más utilizadas de Java.

## Criterios de Evaluación (Metodología)

Para garantizar una cobertura integral y un cumplimiento estricto de los requisitos, se aplicó un balance exacto de **50/50** entre frameworks:

### 1. JUnit 5 (50% - 36 Tests)
Enfoque en **Lógica Pura y Casos Borde**:
- **Pruebas Parametrizadas**: Validación masiva de estados (null, empty, blank).
- **Manejo de Excepciones**: Uso de `assertThrows` para validar robustez ante entradas inválidas.
- **Lógica Compleja**: Pruebas de algoritmos de partición (`split`) y abreviación con offsets.

### 2. Mockito (50% - 36 Tests)
Enfoque en **Comportamiento e Interacciones**:
- **Simulación de Interfaces**: Uso de mocks para `Supplier<String>` y `CharSequence`.
- **Verificación Estricta**: Uso de `verify`, `times()`, y `verifyNoInteractions` para asegurar la eficiencia del código.
- **Propagación de Errores**: Verificación de cómo la clase bajo prueba reacciona ante fallos en sus dependencias.

## Tecnologías
- **Java 21**
- **Maven**
- **JUnit 5 (Jupiter)**
- **Mockito 5**

## Ejecución
Para ejecutar la suite completa de 72 pruebas:
```bash
mvn test
```

## Estructura
- `src/main/java`: Contiene `StringUtils.java` (código fuente optimizado).
- `src/test/java/com/example/stringutils/junit`: Suite de lógica y algoritmos.
- `src/test/java/com/example/stringutils/mockito`: Suite de interacciones y mocks.
