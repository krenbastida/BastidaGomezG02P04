import java.util.*;

public class Utilidades {

    public static void imprimirCola(Queue<Proceso> colaProc){
        for (Proceso imp : colaProc){
            System.out.println(imp.imprimir());
        }
    }

    public static void imprimirLista(List<Proceso> impr){
        int cont = 1;
        if(impr.isEmpty()){
            System.out.println("No hay procesos finalizados o eliminados");
        }else {
            for(Proceso imp : impr){
                System.out.println(cont + ".- " + imp.nombre + "\n");
            }
        }
    }

    public static void llenarArreglo(String[] memoria){
        for(int i = 1; i < 2049; i++){
            memoria[i] = "-";
        }
    }

    public static void buscarEspacio(String[] memoria, Proceso proceso){
        short espacio = (short) (proceso.tam/64);
        short contador = 0;

        for(int i = 1; i < 33; i++){
            if(memoria[i].equals("-")){
                contador++;
            }else{
                contador = 0;
            }
            if(contador == espacio) {
                for(int j = i - espacio; j <= i; j++){
                    memoria[j] = proceso.nombre;
                }
                break;
            }
        }
    }
}
