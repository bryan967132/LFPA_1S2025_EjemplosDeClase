package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import Classes.AFD.*;
import Classes.Reports.Report;
import Classes.Utils.Outs;
import Language.*;

public class ScannerTest {
    public static HashMap<String, AFD> afds = new HashMap<>();

    public static void main(String[] args) {
        analizar("Entrada");
        analizar("Archivo1");
        analizar("Archivo2");
    }

    private static void analizar(String fileName) {
        Outs.resetOuts();
        String input = readInput("./Inputs/" + fileName + ".lfp");
        Scanner sc = new Scanner(input);
        AFDBuilder afdB = new AFDBuilder(sc);
        HashMap<String, AFD> result = afdB.getAFDs();
        afds.putAll(result);

        for(String nombreAFD : afds.keySet()) {
            String dotAFD = afds.get(nombreAFD).graficar();
            Graphviz.exportGraph(nombreAFD, dotAFD);
        }

        Report.generate(fileName, afdB.tokens, Outs.errors);
    }

    private static String readInput(String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String texto = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            return texto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}