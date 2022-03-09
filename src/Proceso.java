import java.util.*;
import java.util.Scanner;

public class Proceso {
    String nombre;
    String id;
    short instrucciones;
    int tam;

    static Scanner sc = new Scanner(System.in);

    public static int crearProceso(int memory, Queue<Proceso> colaProc){
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

        for(int i = 1; i < 2049; i++){
            System.out.println(i + ".- " + "   " + memoria[i]);
        }
    }

    public static void procesoActual(Queue<Proceso> colaProc){
        Proceso proceso = colaProc.peek();
        assert proceso != null;
        System.out.println("Proceso activo: " + proceso.nombre);
    }

    public static void matarProceso(Queue<Proceso> colaProc, List<Proceso> eliminados){
        Proceso proceso = colaProc.poll();
        assert proceso != null;
        System.out.println("Proceso activo: " + proceso.nombre);
        eliminados.add(proceso);
    }

    public String imprimir(){
        return "Nombre: " + nombre + " | " + " Identificador: " + id + " | " + " Instrucciones: " + instrucciones + " | " + " Tamaño: " + tam + "MB";
    }

}
