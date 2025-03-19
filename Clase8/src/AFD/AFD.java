package AFD;

import java.util.ArrayList;
import java.util.HashMap;

public class AFD {
    public String nombre;
    public String descripcion;
    public ArrayList<String> estados;
    public ArrayList<String> alfabeto;
    public String inicial;
    public ArrayList<String> finales;
    public HashMap<String, Transicion> transiciones;

    public AFD(
        String nombre, String descripcion, ArrayList<String> estados,
        ArrayList<String> alfabeto, String inicial, ArrayList<String> finales,
        HashMap<String, Transicion> transiciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.inicial = inicial;
        this.finales = finales;
        this.transiciones = transiciones;
    }

    public String graficar() {
        StringBuilder dot = new StringBuilder("digraph AFD1 {\n\tnode[shape=circle];");
        for(String estado : estados) {
            dot.append("\n\t").append(estado).append(" [label=\"").append(estado).append("\"");
            if(finales.contains(estado)) {
                dot.append(" shape=doublecircle");
            }
            dot.append("];");
        }
        transiciones.forEach((estadoOrigen, transiciones) -> {
            transiciones.tabla.forEach((charTransicion, estadoDestino) -> {
                dot.append("\n\t")
                .append(estadoOrigen)
                .append(" -> ")
                .append(estadoDestino)
                .append("[label=\"").append(charTransicion).append("\"];");
            });
        });
        dot.append("\n}");
        return dot.toString();
    }
}