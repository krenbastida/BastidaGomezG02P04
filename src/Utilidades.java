import java.util.*;

/**
 * Esta clase tiene la finalidad de contener los métodos que pueden usar otros métodos o clases
 * con un objetivo general, aplicable a los elementos que contiene el programa.
 */


public class Utilidades {

    // Este metodo imprime los elementos de la cola.
    public static void imprimirCola(Queue<Proceso> colaProc){
        if(colaProc.isEmpty()){
            System.out.println("No hay nada que imprimir");
        }else{
            for (Proceso imp : colaProc){
                System.out.println(imp.imprimir());
            }
        }

    }

    // Este metodo imprime los elementos de las listas.
    public static void imprimirLista(List<Proceso> impr){
        int cont = 1;
        if(impr.isEmpty()){
            System.out.println("No hay procesos");
        }else {
            for(Proceso imp : impr){
                System.out.println(cont + ".- " + imp.nombre + "\n");
            }
        }
    }

    // Este metodo llena el arreglo (memoria) con los procesos.
    public static void llenarArreglo(String[] memoria){
        for(int i = 0; i < 32; i++) {
            memoria[i] = "-";
        }
    }

    // Este metodo busca espacio en el arreglo del mismo tamaño que el proceso
    // que se creara, y le asigna esas localidades al atrinuto direcciones.
    public static void buscarEspacio(String[] memoria, Proceso proceso){
        short espacio = (short) (proceso.tam/64);
        short contador = 0;

        for(int i = 0; i < 32; i++){
            if(memoria[i].equals("-")){
                contador++;
            }else{
                contador = 0;
            }
            if(contador == espacio) {
                for(int j = (i+1) - espacio; j <= i; j++){
                    memoria[j] = proceso.nombre;
                    String l = String.valueOf((i+2)-contador); // Este metodo de la clase string, obtiene el valor o numero y lo convierte a un string,
                    String h = String.valueOf(i+1); // esto lo hicimos para concatenar las localidades y que el objeto pueda imprimir en donde se encuentra.
                    proceso.direcciones ="Localidad " + l + " a "+ h;
                }
                break;
            }
        }
    }

    // Este arreglo sustituye el nombre del proceso por un -, lo que significa que es una localidad o espacio libre y puede colocarse un nuevo proceso ahí.
    public static void liberarMemoria(String[] memoria, Proceso proceso){
        for(int i = 0; i < 32; i++){
            if(memoria[i].equals(proceso.nombre)){
                memoria[i] = "-";
            }
        }
        System.out.println("Liberando memoria...");
    }

    //Este metodo imprime los elementos de la memoria, en este caso las localidades con los respectivos nombres de los procesos existentes.
    public static void imprimirMemoria(String[] memoria){
        for(int i = 0; i < 32; i++){
            System.out.println((i + 1) + ".- " + "   " + memoria[i]);
        }
    }
}
