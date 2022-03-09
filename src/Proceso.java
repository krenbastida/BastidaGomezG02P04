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

    public String imprimir(){
        return "Nombre: " + nombre + " | " + " Identificador: " + id + " | " + " Instrucciones: " + instrucciones + " | " + " Tamaño: " + tam + "MB";
    }

}
