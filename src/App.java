import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println("Sistema Inteligente de Control de Semáforos");
        System.out.println("Caso de estudio: Distribuir los tiempos de luz verde en los semáforos");

        Scanner entrada = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("Elige una de las siguientes opciones:");
            System.out.println("1. Cargar parámetros e inicializar el escenario");
            System.out.println("2. Ejecutar Hill Climbing");
            System.out.println("3. Ejecutar Simulated Annealing");
            System.out.println("4. Comparar resultados");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            opcion = entrada.nextInt();

            switch (opcion) {

                case 1:
                    Parametros.cargarParametros();
                    break;

                /*
                 * case 2:
                 * HillClimbing.ejecutarHillClimbing();
                 * break;
                 * 
                 * case 3:
                 * SimulatedAnnealing.ejecutarSimulatedAnnealing();
                 * break;
                 * 
                 * case 4:
                 * CompararResultados.compararResultados();
                 * break;
                 * 
                 * case 5:
                 * System.out.println("\nSaliendo del programa...");
                 * break;
                 */

                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);

        entrada.close();
    }
}