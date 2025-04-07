package Classes.AFD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Graphviz {
    public static void exportGraph(String DFAName, String DFADot) {
        File file = new File("Data");
            if(!file.exists()) {
                file.mkdirs();
            }
        // ESCRITURA DEL ARCHIVO .dot
        String dotPath = "./Data/AFD_" + DFAName + ".dot";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dotPath), "UTF-8"))) {
            bw.write(DFADot);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // CONVERSIÓN DE .dot A .png
        String pngPath = "./Data/AFD_" + DFAName + ".png";
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", pngPath, dotPath);
            pb.redirectErrorStream(true);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}