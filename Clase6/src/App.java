import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Scanner.*;
import Utils.Error;

public class App {

    public static void main(String[] a) {
        String input = readInput("./Entrada1.lfp");

        System.out.println(input);

        Scanner sc = new Scanner(input);

        Token tok;

        ArrayList<Token> tokens = new ArrayList<>();
        do {
            tok = sc.next_token();
            tokens.add(tok);
        } while(tok.tipo != TOK.EOF);

        // System.out.println("\nTOKENS");
        // System.out.printf("%-25s%-6s%-8s%-10s\n", "LEXEMA", "LINEA", "COLUMNA", "TIPO");
        // for(Token token : tokens) {
        //     System.out.println(token);
        // }

        // EXTRACCIÓN DE TOKENS PARA DAR FUNCIONALIDAD AL NUEVO LENGUAJE
        // EXTRAER LLAVE IZQUIERDA
        tokens.remove(0);
        ArrayList<String> resultados = new ArrayList<>();
        while(tokens.get(0).tipo != TOK.TK_llaveDer) {
            Token token = tokens.remove(0); // EXTRAE PALABRA RESERVADA PARA OPERACIÓN (suma|resta)
            tokens.remove(0); // EXTRAE PARÉNTESIS IZQUIERDO
            String operacion = "";
            int resultado = 0;
            if(token.tipo == TOK.KW_suma) {
                while(tokens.get(0).tipo != TOK.TK_parDer) { // BUCLE PARA EXTRAER LISTA DE VALORES (numeros separados por coma)
                    token = tokens.remove(0); // EXTRACCIÓN DE NÚMERO
                    resultado += Integer.parseInt(token.lexema);
                    operacion += token.lexema;

                    if(tokens.get(0).tipo == TOK.TK_coma) { // VALIDACIÓN SI HAY COMA LUEGO DE UN NÚMERO
                        tokens.remove(0); // EXTRAER COMA QUE SEPARA CADA VALOR
                        operacion += " + ";
                    }
                }
                resultados.add(operacion + " = " + resultado);
            } else if(token.tipo == TOK.KW_resta) {
                while(tokens.get(0).tipo != TOK.TK_parDer) { // BUCLE PARA EXTRAER LISTA DE VALORES (numeros separados por coma)
                    token = tokens.remove(0); // EXTRACCIÓN DE NÚMERO
                    resultado -= Integer.parseInt(token.lexema);
                    operacion += (operacion.equals("") ? "- " : "") + token.lexema;

                    if(tokens.get(0).tipo == TOK.TK_coma) { // VALIDACIÓN SI HAY COMA LUEGO DE UN NÚMERO
                        operacion += " - ";
                        tokens.remove(0); // EXTRAER COMA QUE SEPARA CADA VALOR
                    }
                }
                resultados.add(operacion + " = " + resultado);
            }
            tokens.remove(0); // EXTRAE PARÉNTESIS DERECHO
        }
        // EXTRAER LLAVE DERECHA
        tokens.remove(0);

        System.out.println("RESULTADOS");
        System.out.println(String.join("\n", resultados));

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