import java.util.*;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        short opc = 0;
        int memory = 2048;
        Queue <Proceso> colaProc = new LinkedList<>();
        List <Proceso> finalizados = new LinkedList<>();
        List <Proceso> eliminados = new LinkedList<>();
        String[] memoria = new String[32];

        Utilidades.llenarArreglo(memoria);

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
                System.out.println("Digite un número válido, intente de nuevo");
                sc.nextLine();
            }
        }while (opc != 8);

    }
}
