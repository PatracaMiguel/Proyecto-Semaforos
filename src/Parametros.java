public class Parametros {
        public static int vehiculosNorteSur = 40;
        public static int vehiculosEsteOeste = 30;

        public static int tiempoCiclo = 120;

        public static int verdeNorteSur = 60;
        public static int verdeEsteOeste = 60;

        public static void cargarParametros() {

                System.out.println(" Módulo 1");

                System.out.println("Escenario :");
                System.out.println("Se simula una intersección con dos sentidos");
                System.out.println("de circulación: Norte-Sur y Este-Oeste.");

                System.out.println("El objetivo es distribuir los tiempos de");
                System.out.println("luz verde de los semáforos para reducir");
                System.out.println("el tiempo de espera de los vehículos.");

                System.out.println("Datos del escenario");

                System.out.println("Vehículos Norte-Sur: " + vehiculosNorteSur);
                System.out.println("Vehículos Este-Oeste: " + vehiculosEsteOeste);

                System.out.println("Tiempo total del ciclo: " + tiempoCiclo + " segundos");

                System.out.println("Luz verde Norte-Sur: " + verdeNorteSur + " segundos");

                System.out.println("Luz verde Este-Oeste: " + verdeEsteOeste + " segundos");

                System.out.println("Función de costo");

                System.out.println("La función de costo representa el tiempo");
                System.out.println("total de espera de los vehículos.");

                System.out.println("El objetivo de los algoritmos será");
                System.out.println("minimizar este costo.");

                System.out.println("\nEscenario cargado correctamente.");
        }
}