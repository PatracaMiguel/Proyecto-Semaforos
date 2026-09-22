# INSTRUCCIONES DE INTEGRACIÓN — Guardado de resultados (Módulos 2 y 3)

Para que el **Módulo 4 (Comparación de Resultados)** funcione, los Módulos 2 y 3
deben **guardar su resultado final en un archivo de texto** al terminar de ejecutarse.

La clase `ResultadosIO` (ya en `src/`) se encarga de todo. Solo falta agregar **una
línea** al final del método `ejecutar()` de cada algoritmo.

---

## 1. En `HillClimbing.java` → al final de `ejecutar()`

```java
ResultadosIO.guardar("resultados/hill_climbing.txt",
        this.costoFinal, this.iteraciones, this.mejorSolucion);
```

Quedaría justo después de:

```java
System.out.println("Iteraciones realizadas: " + this.iteraciones + "\n");
```

## 2. En `SimulatedAnnealing.java` → al final de `ejecutar()`

```java
ResultadosIO.guardar("resultados/simulated_annealing.txt",
        this.costoFinal, this.iteraciones, this.mejorSolucion);
```

Quedaría justo después de:

```java
System.out.println("Iteraciones realizadas: " + this.iteraciones + "\n");
```

---

## Formato del archivo que se genera

La clase `ResultadosIO` escribe y lee este formato (texto plano):

```
CostoFinal: 98.20
Iteraciones: 87
MejorSolucion: 75,45
```

- No modificar el formato manualmente.
- La carpeta `resultados/` se crea automáticamente la primera vez.

## Comportamiento del Módulo 4 si faltan datos

Si el Módulo 4 no encuentra los archivos (porque los Módulos 2 y/o 3 no se han
ejecutado todavía), muestra un mensaje claro: *"Primero ejecuta los Módulos 2 y 3"*.

## Notas

- Los 3 módulos deben estar en la misma carpeta `src/` al compilar:
  `javac -d bin src/*.java`
- Ejecutar desde la raíz del proyecto: `java -cp bin App`
- No subir la carpeta `resultados/` al repositorio (es generada en cada ejecución).