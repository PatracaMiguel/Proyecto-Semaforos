import java.util.Arrays;
import java.util.Random;

public class HillClimbing {
    private double costoFinal;
    private int iteraciones;
    private int[] mejorSolucion;

    private Random random = new Random();

    public void ejecutar() {
        System.out.println("Iniciando Hill Climbing ");

        int[] estadoActual = new int[] { Parametros.verdeNorteSur, Parametros.verdeEsteOeste };
        double costoActual = calcularCosto(estadoActual);

        this.iteraciones = 0;
        boolean mejoraEncontrada = true;

        System.out.printf("Estado inicial: %s | Costo inicial: %.2f",
                Arrays.toString(estadoActual), costoActual);

        while (mejoraEncontrada) {
            mejoraEncontrada = false;
            int intentosVecindad = 20;

            for (int i = 0; i < intentosVecindad; i++) {
                int[] vecino = generarVecino(estadoActual);
                double costoVecino = calcularCosto(vecino);

                if (costoVecino < costoActual) {
                    iteraciones++;
                    System.out.printf("Paso %d: Mejora -> [NS: %ds, EO: %ds] | Costo: %.2f\n",
                            iteraciones, vecino[0], vecino[1], costoVecino);

                    estadoActual = vecino;
                    costoActual = costoVecino;
                    mejoraEncontrada = true;
                    break;
                }
            }
        }

        this.costoFinal = costoActual;
        this.mejorSolucion = estadoActual;

        System.out.println("Se ha alcanzado un óptimo local.");
        System.out.printf("Punto exacto Configuración de Semáforos: [NS: %ds, EO: %ds]", this.mejorSolucion[0],
                this.mejorSolucion[1]);
        System.out.printf("Costo final obtenido: %.2f\n", this.costoFinal);
        System.out.println("Iteraciones realizadas: " + this.iteraciones + "\n");
    }

    private int[] generarVecino(int[] estado) {
        int[] vecino = estado.clone();
        int delta = random.nextBoolean() ? 5 : -5;

        int nuevoNS = vecino[0] + delta;
        int nuevoEO = Parametros.tiempoCiclo - nuevoNS;

        if (nuevoNS >= 10 && nuevoNS <= (Parametros.tiempoCiclo - 10)) {
            vecino[0] = nuevoNS;
            vecino[1] = nuevoEO;
        }

        return vecino;
    }

    public double calcularCosto(int[] tiempos) {
        int vNS = tiempos[0];
        int vEO = tiempos[1];

        int rojoNS = Parametros.tiempoCiclo - vNS;
        int rojoEO = Parametros.tiempoCiclo - vEO;

        double esperaNS = Parametros.vehiculosNorteSur * Math.pow(rojoNS, 2) / (2.0 * Parametros.tiempoCiclo);
        double esperaEO = Parametros.vehiculosEsteOeste * Math.pow(rojoEO, 2) / (2.0 * Parametros.tiempoCiclo);

        double noLinealidad = 12.0 * Math.sin(vNS * 0.3);

        return esperaNS + esperaEO + noLinealidad;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public int[] getMejorSolucion() {
        return mejorSolucion;
    }
}