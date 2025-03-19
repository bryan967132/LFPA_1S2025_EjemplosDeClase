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
        StringBuilder dot = new StringBuilder("digraph AFD1 {");
        dot.append("\n\trankdir = LR;");
        dot.append("\n\tgraph[fontname=\"Arial\" labelloc=t];");
        dot.append("\n\tnode[shape=circle];");
        dot.append("\n\tlabel=<");
        dot.append("\n\t\t<font color=\"black\">" + nombre + "</font><br/>");
        dot.append("\n\t\t<font color=\"#2B90FF\">Descripción: " + descripcion + "</font><br align=\"left\"/>");
        dot.append("\n\t\t<font color=\"#FF4A4A\">Estados: " + String.join(", ", estados) + "</font><br align=\"left\"/>");
        dot.append("\n\t\t<font color=\"#FFA142\">Alfabeto: " + String.join(", ", alfabeto) + "</font><br align=\"left\"/>");
        dot.append("\n\t\t<font color=\"#48C748\">Estado Inicial: " + inicial + "</font><br align=\"left\"/>");
        dot.append("\n\t\t<font color=\"#B166DA\">Estados de Aceptación:" + String.join(", ", finales) + "</font><br align=\"left\"/>\n\t>;");
        for(String estado : estados) {
            dot.append("\n\t").append(estado).append(" [label=\"").append(estado).append("\"");
            if(finales.contains(estado)) {
                dot.append(" peripheries=2");
            }
            dot.append("];");
        }
        transiciones.forEach((estadoOrigen, transiciones) -> {
            transiciones.tabla.forEach((charTransicion, estadoDestino) -> {
                dot.append("\n\t")
                .append(estadoOrigen)
                .append(" -> ")
                .append(estadoDestino)
                .append(" [label=\"").append(charTransicion).append("\"];");
            });
        });
        dot.append("\n}");
        return dot.toString();
    }
}