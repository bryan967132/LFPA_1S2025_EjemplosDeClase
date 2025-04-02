package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Classes.Utils.Error;
import Classes.Utils.Outs;
import Language.*;

public class ScannerTest {
    public static void main(String[] args) {
        String input = readInput("./Inputs/Entrada.lfp");

        System.out.println("\n" + input);

        Scanner sc = new Scanner(input);

        ArrayList<Token> tokens = new ArrayList<>();
        Token tok;
        do {
            tok = sc.next_token();
            tokens.add(tok);
        } while(tok.type != TOK.EOF);

        System.out.printf("%-25s%-6s%-8s%-10s\n", "LEXEMA", "LINEA", "COLUMNA", "TIPO");
        for(Token token : tokens) {
            System.out.println(token);
        }

        System.out.println("\nERRORES");
        if(Outs.errors.size() > 0) {
            for(Error e : Outs.errors) {
                System.out.println(e);
            }
        } else {
            System.out.println("No hay errores!");
        }
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