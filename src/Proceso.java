import java.util.*;
import java.util.Scanner;

public class Proceso {
    String nombre;
    String id;
    short instrucciones;
    int instruccionesEjecutadas;
    short ejecuciones;
    int tam;
    String direcciones;

    static Scanner sc = new Scanner(System.in);

    public static int crearProceso(int memory, Queue<Proceso> colaProc, String[] memoria){
        //Se le pedirá al usuario los datos del nuevo proceso
        int tam = (int)Math.pow(2, (int)(Math.random()*(10-6)+6)); //En este método el min, o número menor es exclusivo

        if(memory < tam){
            System.out.println("No se puede crear el proceso, es necesario ejecutar o matar otros procesos");
            return memory;
        }else {
            Proceso proceso = new Proceso();
            memory -= tam;
            System.out.println("Ingrese el nombre del proceso");
            proceso.nombre = sc.nextLine();
            proceso.id = UUID.randomUUID().toString();
            proceso.instrucciones = (short)(Math.random()*(30-10)+10);
            proceso.tam = tam;
            colaProc.add(proceso);

             Utilidades.buscarEspacio(memoria, proceso);
        }
        return memory;
    }

    public static void estadoActual(Queue<Proceso> colaProc, List<Proceso> finalizados, List <Proceso> eliminados, String[] memoria) {
        short cuentaProc = (short)colaProc.size();
        System.out.println("\nNúmero de procesos en cola: " + cuentaProc);
        System.out.println("\nProcesos finalizados exitosamente:");
        Utilidades.imprimirLista(finalizados);
        System.out.println("\nProcesos eliminados:");
        Utilidades.imprimirLista(eliminados);
        System.out.println("\nEstado actual de la memoria:");

        for(int i = 0; i < 32; i++){
            System.out.println((i + 1) + ".- " + "   " + memoria[i]);
        }
    }

    public static void procesoActual(Queue<Proceso> colaProc){
        Proceso proceso = colaProc.peek();
        assert proceso != null;
        System.out.println("Proceso activo: " + proceso.nombre);
        System.out.println("Identificador: " + proceso.id);
        System.out.println("Instrucciones totales: " + proceso.instrucciones);
        System.out.println("Instrucciones ejecutadas: " + proceso.instruccionesEjecutadas);
        System.out.println("Direcciones de memoria asignadas: " + proceso.direcciones );
    }

    public static void matarProceso(Queue<Proceso> colaProc, List<Proceso> eliminados){
        Proceso proceso = colaProc.poll();
        assert proceso != null;
        System.out.println("Proceso activo: " + proceso.nombre);
        eliminados.add(proceso);
    }

    public static void ejecutarProceso(Queue<Proceso> colaProc, List <Proceso> finalizados, String[] memoria){
        Proceso proceso = colaProc.poll();
        assert proceso != null;
        proceso.ejecuciones += 1;
        System.out.println("IMPRIMIENDO PROCESO EJECUCIONES: " + proceso.ejecuciones);
        if(proceso.instrucciones-(proceso.ejecuciones*5) <= 0){
            System.out.println("\nEl proceso " + proceso.nombre + " ha concluido su ejecucion ");
            proceso.instruccionesEjecutadas = proceso.instrucciones;
            finalizados.add(proceso);
            for(int i = 0; i < 32; i++){
                if(memoria[i].equals(proceso.nombre)){
                    memoria[i] = "-";
                }
            }
            System.out.println("Liberando memoria...");
        }else{
            proceso.instruccionesEjecutadas = proceso.ejecuciones*5;
            colaProc.add(proceso);
        }
    }

    public String imprimir(){
        return "Nombre: " + nombre + " | " + " Identificador: " + id + " | " + " Instrucciones: " + instrucciones + " | " + " Tamaño: " + tam + "MB";
    }

}
