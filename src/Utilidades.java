import java.util.*;

public class Utilidades {

    public static void imprimirCola(Queue<Proceso> colaProc){
        for (Proceso imp : colaProc){
            System.out.println(imp.imprimir());
        }
    }
}
