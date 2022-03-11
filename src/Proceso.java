import java.util.*;
import java.util.Scanner;

/**
 * Contiene todos los metodos para crear, ver, eliminar procesos, se crean los atributos de Proceso para la creacion
 * de objetos.
 *
 * @author Karen Mariel Bastida Vargas, Luis Aldo Gomez Bolanios
 * @version 1.0
 */
public class Proceso {
    String nombre;
    String id;
    short instrucciones;
    int instruccionesEjecutadas; // Variable que lleva el total de instrucciones ejecutadas
    short ejecuciones; // Contador que cuenta el número de ejecuciones de un proceso
    int tam;
    String direcciones;

    static Scanner sc = new Scanner(System.in);

    /**
     * Crea un proceso en caso de que la memoria este llena arrojara un error, caso contario declara las instrucciones
     * necesarias para poder crear el objeto asi como hacer las asignaciones en la memoria correspondientes.
     *
     * @param memory Sirve para saber el espacio en la memoria, que hay disponible para que un proceso lo use.
     * @param colaProc Contiene a los procesos que se están ejecutando, incluyendo al proceso activo.
     * @param memoria Arreglo que simula las localidades disponibles de la memoria que usan los procesos.
     * @return Si no se puede colocar el proceso en la memoria, retorna el valor del espacio disponible.
     */
    public static int crearProceso(int memory, Queue<Proceso> colaProc, String[] memoria){
        //Se le pedirá al usuario los datos del nuevo proceso
        int tam = (int)Math.pow(2, (int)(Math.random()*(10-6)+6)); //En este método el min, o número menor es exclusivo

        if(memory < tam){
            System.out.println("No se puede crear el proceso, es necesario ejecutar o matar otros procesos");
            return memory;
        }else {
            // En este else se encuentra la creacion del proceso en caso de que haya espacio disponible para el
            // El usuario ingresa el nombre del proceso, también en la línea 41, se actualiza la memoria libre en el arreglo
            Proceso proceso = new Proceso();
            memory -= tam;
            System.out.println("Ingrese el nombre del proceso");
            proceso.nombre = sc.nextLine();
            proceso.id = UUID.randomUUID().toString(); // Este metodo de la clase UUID, se generan ids unicos de tipo String
            proceso.instrucciones = (short)(Math.random()*(30-10)+10); // Se le asigna aleatoriamente un número de instrucciones al proceso, entre 10 y 30
            proceso.tam = tam; // El tamaño del proceso entre los valores asignados en la práctica, se asigna a ese atributo del objeto proceso
            colaProc.add(proceso);

             Utilidades.buscarEspacio(memoria, proceso);
        }
        return memory;
    }

    /**
     * Muestra el estado actual del proceso, los que estan en cola, finalizados exitosamente y los eliminados. Se
     * muestra el estado actual de la memoria
     *
     * @param colaProc Contiene a los procesos que se están ejecutando, incluyendo al proceso activo.
     * @param finalizados  Contiene los procesos finalizados del programa exitosamente.
     * @param eliminados Contiene procesos que no pudieron finalizar correctamente sus ejecuciones.
     * @param memoria Arreglo que simula las localidades disponibles de la memoria que usan los procesos.
     */
    public static void estadoActual(Queue<Proceso> colaProc, List<Proceso> finalizados, List <Proceso> eliminados, String[] memoria) {
        short cuentaProc = (short)colaProc.size();
        System.out.println("\nNúmero de procesos en cola: " + cuentaProc);
        System.out.println("\nProcesos finalizados exitosamente:");
        Utilidades.imprimirLista(finalizados);
        System.out.println("\nProcesos eliminados:");
        Utilidades.imprimirLista(eliminados);
        System.out.println("\nEstado actual de la memoria:");

        Utilidades.imprimirMemoria(memoria);
    }

    /**
     * Imprime informacion del proceso actual, el nombre, su identificador, instrucciones totales, instrucciones
     * ejectuadas y las direcciones de memoria asignadas.
     *
     * @param colaProc Contiene a los procesos que se están ejecutando, incluyendo al proceso activo.
     */
    public static void procesoActual(Queue<Proceso> colaProc) {
        if (colaProc.isEmpty()) {
            System.out.println("No hay nada que ver aquí papu");
        } else {
            Proceso proceso = colaProc.peek();
            assert proceso != null;
            System.out.println("Proceso activo: " + proceso.nombre);
            System.out.println("Identificador: " + proceso.id);
            System.out.println("Instrucciones totales: " + proceso.instrucciones);
            System.out.println("Instrucciones ejecutadas: " + proceso.instruccionesEjecutadas);
            System.out.println("Direcciones de memoria asignadas: " + proceso.direcciones);
        }
    }

    /**
     * Ejecuta el primer proceso que esta en cola, ejecuta 5 instrucciones y se coloca al final de la cola, en caso de
     * que finalice se liberan sus espacios de memoria asignados y se ingresa a la cola de finalizados exitosamente.
     *
     * @param colaProc Contiene a los procesos que se están ejecutando, incluyendo al proceso activo.
     * @param finalizados Contiene los procesos finalizados del programa exitosamente.
     * @param memoria Arreglo que simula las localidades disponibles de la memoria que usan los procesos.
     */
    public static void ejecutarProceso(Queue<Proceso> colaProc, List <Proceso> finalizados, String[] memoria){
        if(colaProc.isEmpty()){
            System.out.println("No hay nada que ver aquí papu");
        }else{
            Proceso proceso = colaProc.poll();
            assert proceso != null;
            proceso.ejecuciones += 1;
            if(proceso.instrucciones-(proceso.ejecuciones*5) <= 0){ //Entra en caso de que el proceso tenga menos de 5 instrucciones
                System.out.println("\nEl proceso " + proceso.nombre + " ha concluido su ejecucion ");
                proceso.instruccionesEjecutadas = proceso.instrucciones;
                finalizados.add(proceso);
                Utilidades.liberarMemoria(memoria, proceso);
            }else{
                proceso.instruccionesEjecutadas = proceso.ejecuciones*5;
                colaProc.add(proceso);
            }
        }
    }

    /**
     * Se pasa al siguiente proceso sin ejecutar las instrucciones del que esta activo.
     *
     * @param colaProc Contiene a los procesos que se están ejecutando, incluyendo al proceso activo.
     */
    public static void siguienteProceso(Queue<Proceso> colaProc) {
        if (colaProc.isEmpty()) {
            System.out.println("No hay nada que ver aquí papu");
        } else {
            Proceso proceso = colaProc.poll();
            System.out.println("Pasando al siguiente proceso sin ejecutar instrucciones...");
            colaProc.add(proceso);
        }
    }

    /**
     * Elimina el proceso actual haciendo que no ejecute las instrucciones que restan, se libere la memoria que ocupa.
     *
     * @param colaProc Contiene a los procesos que se están ejecutando, incluyendo al proceso activo.
     * @param eliminados Contiene procesos que no pudieron finalizar correctamente sus ejecuciones.
     * @param memoria Contiene procesos que no pudieron finalizar correctamente sus ejecuciones.
     */
    public static void matarProceso(Queue<Proceso> colaProc, List<Proceso> eliminados, String[] memoria){
        if(colaProc.isEmpty()){
            System.out.println("No hay nada que ver aquí papu");
        }else{
            Proceso proceso = colaProc.poll();
            assert proceso != null;
            System.out.println("Matando proceso: " + proceso.nombre);
            eliminados.add(proceso);
            Utilidades.liberarMemoria(memoria, proceso);
            System.out.println("\nInstrucciones pendientes: " + (proceso.instrucciones-proceso.instruccionesEjecutadas));
        }

    }


    public String imprimir(){
        return "Nombre: " + nombre + " | " + " Identificador: " + id + " | " + " Instrucciones: " + instrucciones + " | " + " Tamaño: " + tam + "MB";
    }
}