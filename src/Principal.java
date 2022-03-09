import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        short opc = 0;
        int memory = 2048;
        do {
            System.out.println("ADMINISTRADOR DE PROCESOS\nElija una opción: \n1)Crear nuevo Proceso \n2)Ver estado actual del sistema " +
                    "\n3)Imprimir cola de procesos \n4)Ver proceso actual \n5)Ejecutar proceso actual \n6)Pasar al siguiente proceso" +
                    "\n7)Matar proceso actual \n8)Salir");
            try{
                opc = sc.nextShort();
                switch (opc){
                    case 1:
                        memory = Proceso.crearProceso(memory);
                    /*case 2:
                        Proceso.estadoActual();
                    case 3:
                        Proceso.imprimirCola();
                    case 4:
                        Proceso.verProcesoActual();
                    case 5:
                        Proceso.ejecutarProcesoActual();
                    case 6:
                        Proceso.siguienteProceso();
                    case 7:
                        Proceso.matarProceso();
                    case 8:
                        break;*/
                }

            }catch (InputMismatchException exe){
                System.out.println("Digite un número válido, intente de nuevo");
                sc.nextLine();
            }
        }while (opc != 8);
    }




}
