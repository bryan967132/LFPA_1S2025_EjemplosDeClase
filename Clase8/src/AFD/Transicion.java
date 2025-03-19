package AFD;

import java.util.HashMap;

public class Transicion {
    // Clave: Caractere de transicion
    // Valor: Nombre de estado destino
    public HashMap<String, String> tabla = new HashMap<>();

    public Transicion(HashMap<String, String> tabla) {
        this.tabla = tabla;
    }
}