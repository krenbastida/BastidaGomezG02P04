import java.util.*;

/**
 * Clase principal, contiene el metodo main que llama a los diferentes metodos para crear, borrar, guardar procesos.
 * Se declara la memoria, listas, colas, etc.
 *
 * @author Karen Mariel Bastida Vargas, Luis Aldo Gomez Bolanios
 * @version 1.0
 */
public class Principal {
    static Scanner sc = new Scanner(System.in);

    /**
     * Corre el programa principal, contiene un menu para llamar a los demas procesos.
     * @param args Parametros por default del metodo main
     */
    public static void main(String[] args) {
        short opc = 0;
        int memory = 2048; // Variable que nos ayuda a controlar el tamaño de la memoria.
        Queue <Proceso> colaProc = new LinkedList<>(); // Contiene los procesos activos y los que están en espera.
        List <Proceso> finalizados = new LinkedList<>(); // Lista de procesos finalizados.
        List <Proceso> eliminados = new LinkedList<>(); // Lista de procesos eliminados.
        String[] memoria = new String[32]; // Arreglo que contiene detallado los espacios de memoria que ocupan los procesos.

        Utilidades.llenarArreglo(memoria);

        // MENÚ
        do {
            System.out.println("""                                      
                    \sADMINISTRADOR DE PROCESOS
                    Elija una opción:\s
                    1)Crear nuevo Proceso\s
                    2)Ver estado actual del sistema\s
                    3)Imprimir cola de procesos\s
                    4)Ver proceso actual\s
                    5)Ejecutar proceso actual\s
                    6)Pasar al siguiente proceso
                    7)Matar proceso actual\s
                    8)Salir""");
            try{
                opc = sc.nextShort();
                switch (opc) {
                    case 1 -> {
                        System.out.println("MEMORIA ACTUALIZADA = " + memory);
                        memory = Proceso.crearProceso(memory, colaProc, memoria);
                    }
                    case 2 -> Proceso.estadoActual(colaProc, finalizados, eliminados, memoria);
                    case 3 -> Utilidades.imprimirCola(colaProc);
                    case 4 -> Proceso.procesoActual(colaProc);
                    case 5 -> Proceso.ejecutarProceso(colaProc, finalizados, memoria);
                    case 6 -> Proceso.siguienteProceso(colaProc);
                    case 7 -> Proceso.matarProceso(colaProc, eliminados, memoria);
                    case 8 -> {
                        System.out.println("Saliendo...");
                        Utilidades.imprimirCola(colaProc);
                    }
                }
            }catch (InputMismatchException exe){
                System.out.println("Digite un número válido, intente de nuevo.");
                sc.nextLine();
            }
        }while (opc != 8);
    }
}
