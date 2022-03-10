import java.util.*;

public class Utilidades {

    public static void imprimirCola(Queue<Proceso> colaProc){
        if(colaProc.isEmpty()){
            System.out.println("No hay nada que imprimir");
        }else{
            for (Proceso imp : colaProc){
                System.out.println(imp.imprimir());
            }
        }

    }

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

    public static void llenarArreglo(String[] memoria){
        for(int i = 0; i < 32; i++) {
            memoria[i] = "-";
        }
    }

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
                    String l = String.valueOf((i+2)-contador);
                    String h = String.valueOf(i+1);
                    proceso.direcciones ="Localidad " + l + " a "+ h;
                }
                break;
            }
        }
    }

    public static void liberarMemoria(String[] memoria, Proceso proceso){
        for(int i = 0; i < 32; i++){
            if(memoria[i].equals(proceso.nombre)){
                memoria[i] = "-";
            }
        }
        System.out.println("Liberando memoria...");
    }

    public static void imprimirMemoria(String[] memoria){
        for(int i = 0; i < 32; i++){
            System.out.println((i + 1) + ".- " + "   " + memoria[i]);
        }
    }
}
