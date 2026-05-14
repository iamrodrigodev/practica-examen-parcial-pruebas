# Proyecto: Práctica de Pruebas Unitarias para StringUtils

## 1. Descripción General
Este proyecto consiste en una suite de pruebas exhaustiva para la clase `StringUtils`, diseñada para validar su lógica interna y comportamiento mediante el uso de frameworks de vanguardia. El enfoque principal es la aplicación de estándares de ingeniería de software para asegurar la calidad y resiliencia del código.

## 2. Stack Tecnológico
- **Java:** Versión 21 (LTS).
- **Gestión de Proyecto:** Apache Maven.
- **Frameworks de Testing:**
  - **JUnit 5 (Jupiter):** Verificación de la lógica algorítmica.
  - **Mockito 5:** Validación de interacciones, mocks y evaluación perezosa.

## 3. Estructura del Proyecto
- `src/main/java/`: Contiene la implementación de `StringUtils.java`.
- `src/test/java/com/example/stringutils/junit/`: Suites de pruebas lógicas (36 casos).
- `src/test/java/com/example/stringutils/mockito/`: Suites de validación de comportamiento (36 casos).

## 4. Ejecución de Pruebas
Para ejecutar la suite completa de 72 casos de prueba, utilice el siguiente comando:

```bash
mvn test
```

### Comandos para suites específicas:
- **Ejecutar JUnit:** `mvn test -Dtest=StringUtils*JUnitTest`
- **Ejecutar Mockito:** `mvn test -Dtest=StringUtils*MockitoTest`

## 5. Resumen de Calidad
- **Total de pruebas:** 72.
- **Estado:** 100% Exitosas.
- **Cobertura:** Validación de casos nulos, límites de ancho, manipulación de secuencias y optimización de recursos (lazy loading).
