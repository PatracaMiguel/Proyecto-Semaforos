import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    private double costoFinal;
    private int iteraciones;
    private int[] mejorSolucion;

    private double temperaturaInicial = 100.0;
    private double tasaEnfriamiento = 0.95;
    private double temperaturaMinima = 0.1;

    private Random random = new Random();

    public void ejecutar() {
        System.out.println("Iniciando Simulated Annealing");

        int[] estadoActual = new int[] { Parametros.verdeNorteSur, Parametros.verdeEsteOeste };
        double costoActual = calcularCosto(estadoActual);

        int[] mejorEstado = estadoActual.clone();
        double mejorCosto = costoActual;

        double temperatura = temperaturaInicial;
        this.iteraciones = 0;

        System.out.printf("Estado inicial: %s | Costo inicial: %.2f | Temperatura inicial: %.2f%n",
                Arrays.toString(estadoActual), costoActual, temperatura);

        while (temperatura > temperaturaMinima) {
            iteraciones++;

            int[] vecino = generarVecino(estadoActual);
            double costoVecino = calcularCosto(vecino);

            double deltaF = costoActual - costoVecino;

            double probabilidadAceptacion = Math.exp(deltaF / temperatura);
            double numeroAleatorio = random.nextDouble();

            if (deltaF > 0) {
                estadoActual = vecino;
                costoActual = costoVecino;
                System.out.printf("Paso %d: Mejora directa -> [NS: %ds, EO: %ds] | Costo: %.2f | T: %.2f%n",
                        iteraciones, vecino[0], vecino[1], costoVecino, temperatura);
            } else if (probabilidadAceptacion > numeroAleatorio) {
                estadoActual = vecino;
                costoActual = costoVecino;
                System.out.printf(
                        "Paso %d: Escapando de un bache -> [NS: %ds, EO: %ds] | Costo: %.2f | Prob: %.3f | T: %.2f%n",
                        iteraciones, vecino[0], vecino[1], costoVecino, probabilidadAceptacion, temperatura);
            }

            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                mejorEstado = estadoActual.clone();
            }
            temperatura *= tasaEnfriamiento;
        }

        this.costoFinal = mejorCosto;
        this.mejorSolucion = mejorEstado;

        System.out.println("Se ha alcanzado la solución óptima global.");
        System.out.printf("Punto exacto Configuración de Semáforos: [NS: %ds, EO: %ds]%n", this.mejorSolucion[0],
                this.mejorSolucion[1]);
        System.out.printf("Costo final obtenido: %.2f%n", this.costoFinal);
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