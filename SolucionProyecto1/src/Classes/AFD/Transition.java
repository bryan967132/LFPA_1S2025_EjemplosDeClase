package Classes.AFD;

import java.util.HashMap;

public class Transition {
    // Clave: Caractere de transicion
    // Valor: Nombre de estado destino
    public HashMap<String, String> tabla = new HashMap<>();

    public Transition(HashMap<String, String> tabla) {
        this.tabla = tabla;
    }
}