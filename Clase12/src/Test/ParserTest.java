package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import Classes.Utils.Outs;
import Language.*;

public class ParserTest {
    public static void main(String ... args) {
        String input = readInput("./Inputs/Entrada.lfp");

        // System.out.println();
        // String[] in = input.split("\n");
        // int length = String.valueOf(in.length).length();
        // for(int i = 0; i < in.length; System.out.printf("%" + length + "d %s\n", ++i, in[i - 1]));

        Scanner sc = new Scanner(input);

        Parser parser = new Parser(sc);
        parser.parse();

        System.out.println("-");
        System.out.println("\u001B[32mJavaEs$\u001B[0m");
        System.out.println(Outs.getStringOuts());
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