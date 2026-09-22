import java.util.Locale;

/**
 * Módulo 4: Comparación de Resultados.
 *
 * Lee los datos guardados por los Módulos 2 (Hill Climbing) y
 * 3 (Simulated Annealing) y muestra una tabla comparativa con el
 * costo final y el número de iteraciones de cada algoritmo.
 */
public class CompararResultados {

    private static final String RUTA_HILL_CLIMBING = "resultados/hill_climbing.txt";
    private static final String RUTA_SIMULATED_ANNEALING = "resultados/simulated_annealing.txt";

    public static void compararResultados() {
        System.out.println("\n Módulo 4");

        ResultadosIO.Resultado hill = ResultadosIO.cargar(RUTA_HILL_CLIMBING);
        ResultadosIO.Resultado sa = ResultadosIO.cargar(RUTA_SIMULATED_ANNEALING);

        if (hill == null || sa == null) {
            System.out.println("No se encontraron los resultados guardados de los Módulos 2 y/o 3.");
            System.out.println("Primero ejecuta:");
            System.out.println("  1. Módulo 2 (Hill Climbing)");
            System.out.println("  2. Módulo 3 (Simulated Annealing)");
            System.out.println("y después vuelve a seleccionar la opción 'Comparar resultados'.");
            return;
        }

        double mejora = 0.0;
        if (hill.costoFinal > 0) {
            mejora = (1.0 - sa.costoFinal / hill.costoFinal) * 100.0;
        }

        String linea = "============================================================";
        String linea2 = "------------------------------------------------------------";

        System.out.println(linea);
        System.out.println("              COMPARACIÓN DE RESULTADOS");
        System.out.println(linea);
        System.out.printf(Locale.US, "%-19s | %10s | %11s | %13s%n",
                "Algoritmo", "Costo", "Iteraciones", "Configuración");
        System.out.println(linea2);
        System.out.printf(Locale.US, "%-19s | %10.2f | %11d | %13s%n",
                "Hill Climbing", hill.costoFinal, hill.iteraciones, formatearSolucion(hill.mejorSolucion));
        System.out.printf(Locale.US, "%-19s | %10.2f | %11d | %13s%n",
                "Simulated Annealing", sa.costoFinal, sa.iteraciones, formatearSolucion(sa.mejorSolucion));
        System.out.println(linea2);
        System.out.printf(Locale.US, "Mejora de Simulated Annealing sobre Hill Climbing: %.2f%%%n", mejora);
        System.out.println(linea);

        if (sa.costoFinal < hill.costoFinal) {
            System.out.println("\nHill Climbing quedó atorado en un óptimo local,");
            System.out.println("mientras que Simulated Annealing logró escapar de los baches");
            System.out.println("y encontrar una mejor solución.");
        } else if (sa.costoFinal == hill.costoFinal) {
            System.out.println("\nAmbos algoritmos llegaron al mismo costo final en esta ejecución.");
        } else {
            System.out.println("\nEn esta ejecución, Simulated Annealing no superó a Hill Climbing.");
            System.out.println("Se recomienda ajustar la temperatura inicial o la tasa de enfriamiento.");
        }
    }

    /** Muestra el arreglo de la solución como "[55, 65]". */
    private static String formatearSolucion(int[] solucion) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < solucion.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(solucion[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}