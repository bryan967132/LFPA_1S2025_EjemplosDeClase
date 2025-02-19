import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class App {
    public static void main(String[] s) {
        // ejemploArrayList();
        // ejemploHashMap();
        // ejemploTreeMap();
        // ejemploLectura();
        ejemploEscritura();
    }

    public static void ejemploArrayList() {
        ArrayList<String> nombres = new ArrayList<>();
        // Agregar elementos
        nombres.add("Ana"); // 0
        nombres.add("Brayan"); // 3
        nombres.add("Carlos"); // 4
        nombres.add("Douglas"); // 5
        nombres.add("Edgar"); // 6

        nombres.add(1, "null"); // 2
        nombres.add(1, "HOLA"); // 1

        System.out.println("lista" + nombres);
        System.out.println("size = " + nombres.size());

        // ELIMINAR UNA POSICIÓN ESPECÍFICA
        nombres.remove(3);
        System.out.println("lista" + nombres);
        System.out.println("size = " + nombres.size());

        // ELIMINAR UN OBJETO ESPECÍFICO
        nombres.remove("Carlos");
        System.out.println("lista" + nombres);
        System.out.println("size = " + nombres.size());

        System.out.println(nombres.get(1));

        // ELIMINAR TODOS LOS ELEMENTOS
        nombres.clear();
        System.out.println("lista" + nombres);
        System.out.println("size = " + nombres.size());
    }

    public static void ejemploHashMap() {
        // Clave - Valor
        HashMap<Character, Integer> ascii = new HashMap<>() {{
            put('!', 33);
            put('{', 123);
        }};

        ascii.put('@', 64);
        ascii.put('d', 100);
        ascii.put('\\', 92);

        System.out.println("hashmap = " + ascii);
        System.out.println("size = " + ascii.size());

        // ACCEDER A LOS VALORES MEDIANTE SU CLAVE
        System.out.println(ascii.get('!'));
        System.out.println(ascii.get((char) 64));

        // ITERAR EN HASHMAP
        // ForEach Convencional
        System.out.println("=========ForEach Convencional=========");
        for(Map.Entry<Character, Integer> entry : ascii.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }
        // ForEach de HashMap
        System.out.println("=========ForEach de HashMap=========");
        ascii.forEach((clave, valor) -> {
            System.out.println("Clave: " + clave + ", Valor: " + valor);
        });

        // ELIMINA TODOS LOS PARES CLAVE-VALOR
        ascii.clear();
        System.out.println("hashmap = " + ascii);
        System.out.println("size = " + ascii.size());

    }

    public static void ejemploTreeMap() {
        TreeMap<Double, Boolean> arbol = new TreeMap<>() {{
            put(3.1416, true);
        }};

        arbol.put(10.7, false);
        arbol.put(0.3, false);

        System.out.println("Treemap = " + arbol);
        System.out.println("Treemap = " + arbol.size());

        // ITERAR EN HASHMAP
        // ForEach Convencional
        System.out.println("=========ForEach Convencional=========");
        for(Map.Entry<Double, Boolean> entry : arbol.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }
        // ForEach de TreeMap
        System.out.println("=========ForEach de TreeMap=========");
        arbol.forEach((clave, valor) -> {
            System.out.println("Clave: " + clave + ", Valor: " + valor);
        });

        System.out.println(arbol.get(3.1416));
        System.out.println(arbol.getOrDefault(100.0, true));

        arbol.remove(3.1416);

        arbol.clear();
        System.out.println("Treemap = " + arbol);
        System.out.println("Treemap = " + arbol.size());
    }

    public static void ejemploLectura() {
        String contenidoArchivo = "";

        try (BufferedReader br = new BufferedReader(new FileReader("./Entrada/Matriz.txt"))) {
            String linea;

            while((linea = br.readLine()) != null) {
                contenidoArchivo += linea;

                if(br.ready()) {
                    contenidoArchivo += "\n";
                }
            }

            System.out.println(contenidoArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejemploEscritura() {

        ArrayList<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Lenguajes", 5, 3));
        cursos.add(new Curso("Compi1", 5, 4));
        cursos.add(new Curso("Archivos", 5, 4));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./Salida/Reporte.html"))) {
            bw.write("<!DOCTYPE html>\n");
            bw.write("\t<body>\n");
            bw.write("\t\t<table>\n");
            bw.write("\t\t\t<tr>\n\t\t\t\t<th>Curso</th>\n\t\t\t\t<th>Creditos</th>\n\t\t\t\t<th>Semestre</th>\n\t\t\t</tr>\n");

            // for(Curso curso : cursos) {
            //     bw.write("<tr><td>" + curso.nombre + "</td><td>" + curso.creditos + "</td><td>" + curso.semestre + "</td></tr>");
            // }

            for(int i = 0; i < cursos.size(); i ++) {
                bw.write("\t\t\t<tr>\n\t\t\t\t<td>" + cursos.get(i).nombre + "</td>\n\t\t\t\t<td>" + cursos.get(i).creditos + "</td>\n\t\t\t\t<td>" + cursos.get(i).semestre + "</td>\n\t\t\t</tr>\n");
            }

            bw.write("\t\t</table>\n");
            bw.write("\t</body>\n");
            bw.write("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Curso {
    public String nombre;
    public int creditos;
    public int semestre;

    public Curso(String nombre, int creditos, int semestre) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestre = semestre;
    }
}