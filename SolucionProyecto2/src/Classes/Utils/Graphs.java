package Classes.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import Classes.World.World;

public class Graphs {
    public static void exportGraph(World world) {
        String dotPath = "./Graphs/" + world.name + ".dot";
        try (Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dotPath), "UTF-8"))) {
            bw.write(world.getDot());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String pngPath = "./Graphs/" + world.name + ".png";
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-o", pngPath, dotPath);
            pb.redirectErrorStream(true);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}