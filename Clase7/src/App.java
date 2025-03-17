import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Scanner.*;
import Utils.Error;

public class App {

    public static void main(String[] a) {
        String input = readInput("./Entrada1.lfp");

        Scanner sc = new Scanner(input);

        Token tok;

        ArrayList<Token> tokens = new ArrayList<>();
        do {
            tok = sc.siguiente_token();
            tokens.add(tok);
        } while(tok.tipo != TOK.EOF);

        System.out.println("\nTOKENS");
        System.out.printf("%-25s%-6s%-8s%-10s\n", "LEXEMA", "LINEA", "COLUMNA", "TIPO");

        for(Token token : tokens) {
            System.out.printf("%-25s%-6s%-8s%-10s\n", token.lexema, token.linea, token.columna, token.tipo.getNombre());
        }

        System.out.println("\nERRORES LÉXICOS");
        if(sc.errores.size() > 0) {
            for(Error e : sc.errores) {
                System.out.println(e);
            }
        } else {
            System.out.println("¡No hay errores léxicos!");
        }
    }

    public static String readInput(String path) {
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