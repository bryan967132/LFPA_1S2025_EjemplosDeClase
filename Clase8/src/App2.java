import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import AFD.AFD;
import AFD.Transicion;

public class App2 {
    public static void main(String[] a) {
        HashMap<String, AFD> automatas = new HashMap<>();

        String nombre = "AFD1";

        String descripcion = "\"Cadenas numéricas\"".substring(1, "\"Cadenas numéricas\"".length() - 1);

        ArrayList<String> estados = new ArrayList<>();
        estados.add("S0");
        estados.add("S1");
        estados.add("S2");
        estados.add("S3");
        estados.add("S4");
        estados.add("S5");
        estados.add("S6");
        estados.add("S7");
        estados.add("S8");

        ArrayList<String> alfabeto = new ArrayList<>();
        // alfabeto.add(token.lexema.substring(1, token.lexema.length() - 2));
        alfabeto.add("\"1\"".substring(1, "\"1\"".length() - 1));
        alfabeto.add("\"2\"".substring(1, "\"2\"".length() - 1));
        alfabeto.add("\"3\"".substring(1, "\"3\"".length() - 1));

        String estadoInicial = "S0";

        ArrayList<String> finales = new ArrayList<>();
        finales.add("S0");
        finales.add("S1");
        finales.add("S2");
        finales.add("S3");
        finales.add("S5");
        finales.add("S6");
        finales.add("S7");
        finales.add("S8");

        HashMap<String, Transicion> transiciones = new HashMap<>();

        HashMap<String, String> transicionesS0 = new HashMap<>();
        transicionesS0.put("1", "S1");
        transicionesS0.put("2", "S2");
        transicionesS0.put("3", "S3");
        transiciones.put("S0", new Transicion(transicionesS0));

        HashMap<String, String> transicionesS1 = new HashMap<>();
        transicionesS1.put("2", "S1");
        transiciones.put("S1", new Transicion(transicionesS1));

        HashMap<String, String> transicionesS2 = new HashMap<>();
        transicionesS2.put("2", "S1");
        transicionesS2.put("3", "S4");
        transiciones.put("S2", new Transicion(transicionesS2));

        HashMap<String, String> transicionesS3 = new HashMap<>();
        transicionesS3.put("1", "S5");
        transicionesS3.put("2", "S6");
        transicionesS3.put("3", "S7");
        transiciones.put("S3", new Transicion(transicionesS3));

        HashMap<String, String> transicionesS4 = new HashMap<>();
        transicionesS4.put("1", "S8");
        transicionesS4.put("3", "S4");
        transiciones.put("S4", new Transicion(transicionesS4));

        HashMap<String, String> transicionesS5 = new HashMap<>();
        transicionesS5.put("1", "S5");
        transiciones.put("S5", new Transicion(transicionesS5));

        HashMap<String, String> transicionesS6 = new HashMap<>();
        transicionesS6.put("2", "S6");
        transiciones.put("S6", new Transicion(transicionesS6));

        HashMap<String, String> transicionesS7 = new HashMap<>();
        transicionesS7.put("1", "S8");
        transicionesS7.put("2", "S6");
        transicionesS7.put("3", "S7");
        transiciones.put("S7", new Transicion(transicionesS7));

        AFD nuevoAFD = new AFD(nombre, descripcion, estados, alfabeto, estadoInicial, finales, transiciones);

        automatas.put(nombre, nuevoAFD);

        // SELECT BOX: Nombres de automatas
        String nombreAFD = "AFD1";

        String dotAFD = automatas.get(nombreAFD).graficar();

        // ESCRITURA DEL ARCHIVO .dot
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Salida" + nombreAFD + ".dot"), "UTF-8"))) {
            bw.write(dotAFD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // CONVERSIÓN DE .dot A .png
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}