import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

/**
 * Clase auxiliar para guardar y leer los resultados de los Módulos 2 y 3.
 *
 * Formato de archivo (texto plano):
 *   CostoFinal: 98.20
 *   Iteraciones: 87
 *   MejorSolucion: 75,45
 *
 * Uso desde los Módulos 2 y 3 (al final de ejecutar()):
 *   ResultadosIO.guardar("resultados/hill_climbing.txt",
 *                         costoFinal, iteraciones, mejorSolucion);
 *
 * Uso desde el Módulo 4:
 *   ResultadosIO.Resultado r = ResultadosIO.cargar("resultados/hill_climbing.txt");
 */
public class ResultadosIO {

    /** Contenedor de los datos de una ejecución (costos e iteraciones). */
    public static class Resultado {
        public final double costoFinal;
        public final int iteraciones;
        public final int[] mejorSolucion;

        public Resultado(double costoFinal, int iteraciones, int[] mejorSolucion) {
            this.costoFinal = costoFinal;
            this.iteraciones = iteraciones;
            this.mejorSolucion = mejorSolucion;
        }
    }

    /** Guarda los resultados de una ejecución en un archivo de texto. */
    public static void guardar(String archivo, double costo, int iteraciones, int[] solucion) {
        try {
            Path ruta = Paths.get(archivo);
            if (ruta.getParent() != null) {
                Files.createDirectories(ruta.getParent());
            }

            StringBuilder contenido = new StringBuilder();
            contenido.append("CostoFinal: ").append(String.format(Locale.US, "%.2f", costo)).append('\n');
            contenido.append("Iteraciones: ").append(iteraciones).append('\n');
            contenido.append("MejorSolucion: ").append(formatearValores(solucion)).append('\n');

            Files.write(ruta, contenido.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("[Guardado] Resultados escritos en: " + archivo);
        } catch (IOException e) {
            System.out.println("[Error] No se pudo guardar el archivo: " + archivo);
        }
    }

    /**
     * Lee un archivo de resultados.
     * Devuelve null si el archivo no existe o si no se puede leer correctamente.
     */
    public static Resultado cargar(String archivo) {
        try {
            Path ruta = Paths.get(archivo);
            if (!Files.exists(ruta)) {
                return null;
            }

            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            double costo = 0.0;
            int iteraciones = 0;
            int[] solucion = new int[0];
            boolean encontreCosto = false;
            boolean encontreIteraciones = false;

            for (String linea : lineas) {
                String limpia = linea.trim();
                if (limpia.startsWith("CostoFinal:")) {
                    costo = Double.parseDouble(valorDe(limpia));
                    encontreCosto = true;
                } else if (limpia.startsWith("Iteraciones:")) {
                    iteraciones = Integer.parseInt(valorDe(limpia));
                    encontreIteraciones = true;
                } else if (limpia.startsWith("MejorSolucion:")) {
                    solucion = parsearValores(valorDe(limpia));
                }
            }

            if (!encontreCosto || !encontreIteraciones) {
                System.out.println("[Error] Archivo de resultados con formato inválido: " + archivo);
                return null;
            }

            return new Resultado(costo, iteraciones, solucion);
        } catch (Exception e) {
            System.out.println("[Error] No se pudo leer el archivo de resultados: " + archivo);
            return null;
        }
    }

    /** Formatea un arreglo de enteros como "55,65". */
    private static String formatearValores(int[] valores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(valores[i]);
        }
        return sb.toString();
    }

    /** Convierte "55,65" en {55, 65}. */
    private static int[] parsearValores(String texto) {
        String limpio = texto.trim();
        if (limpio.isEmpty()) {
            return new int[0];
        }
        String[] partes = limpio.split(",");
        int[] valores = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            valores[i] = Integer.parseInt(partes[i].trim());
        }
        return valores;
    }

    /** Extrae el valor que está después de ":" en líneas como "CostoFinal: 98.20". */
    private static String valorDe(String linea) {
        int indice = linea.indexOf(':');
        if (indice < 0) {
            return "";
        }
        return linea.substring(indice + 1).trim();
    }
}