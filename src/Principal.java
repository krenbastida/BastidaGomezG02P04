import java.util.*;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        short opc = 0;
        int memory = 2048;
        Queue <Proceso> colaProc = new LinkedList<>();
        List <Proceso> finalizados = new LinkedList<>();
        List <Proceso> eliminados = new LinkedList<>();
        String[] memoria = new String[2049];

        Utilidades.llenarArreglo(memoria);

        //Map <int, Proceso> mp = new HashMap <int, Proceso>();

        do {
            System.out.println("""
                    \n\n\sADMINISTRADOR DE PROCESOS
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
                switch (opc){
                    case 1:
                        System.out.println("MEMORIA ACTUALIZADA = " + memory);
                        memory = Proceso.crearProceso(memory, colaProc);
                        break;
                    case 2:
                        Proceso.estadoActual(colaProc, finalizados, eliminados, memoria);
                        break;
                    case 3:
                        Utilidades.imprimirCola(colaProc);
                        break;
                    case 4:
                        Proceso.procesoActual(colaProc);
                        break;
                    /*case 5:
                        Proceso.ejecutarProcesoActual();
                        break;
                    case 6:
                        Proceso.siguienteProceso();
                        break;*/
                    case 7:
                        Proceso.matarProceso(colaProc, eliminados);
                        break;
                    case 8:
                        break;
                }

            }catch (InputMismatchException exe){
                System.out.println("Digite un número válido, intente de nuevo");
                sc.nextLine();
            }
        }while (opc != 8);

    }
}
