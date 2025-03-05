import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import Scanner.Scanner;
import Scanner.Token;
import Utils.Error;

public class App {

    public static void main(String[] a) {
        String input = readInput("./Entrada1.lfp");

        System.out.println(input);

        Scanner sc = new Scanner(input);

        Token token;

        System.out.println("TOKEN" + " ".repeat(25 - "TOKEN".length()) + "LINE" + " ".repeat(6 - "LINE".length()) + "COLUMN" + " ".repeat(8 - "COLUMN".length()) + "TYPE");
        do {
            token = sc.next_token();
            System.out.println(token.lexema + " ".repeat(25 - String.valueOf(token.lexema).length()) + token.linea + " ".repeat(6 - String.valueOf(token.linea).length()) + token.columna + " ".repeat(8 - String.valueOf(token.columna).length()) + token.tipo.getNombre());
        } while(token.lexema != null);

        System.out.println("\nERRORES LÉXICOS");
        for(Error er : sc.errores) {
            System.out.println(er);
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