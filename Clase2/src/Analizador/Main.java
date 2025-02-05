package Analizador;
public class Main {
    public static void main(String[] s) {
        Analizador analizador = new Analizador("");
        // analizar
        analizador.analizar();
        // resultados
        analizador.obtenerResultados();
        // errores
        analizador.obtenerErrores();
    }
}