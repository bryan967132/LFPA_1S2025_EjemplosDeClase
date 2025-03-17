package Scanner;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Utils.Error;
import Utils.TipoError;

public class Scanner {
    private int pos_char = 0;
    private int char_line = 1;
    private int char_col = 1;
    private char[] entrada;
    private char last_char;
    private char next_char;
    private String buffer = "";

    public ArrayList<Error> errores = new ArrayList<>();

    // Clave: Lexema de la palabra Reservada
    // Valor: Tipo de Token TOK
    private Map<String, TOK> reservadas = new TreeMap<>() {{
        put("descripcion", TOK.KW_descripcion);
        // AQUÍ VAN LAS DEMÁS PALABRAS RESERVADAS
    }};

    public Scanner(String entrada) {
        this.entrada = (entrada + '\0').toCharArray();
    }

    private void iniciarBuffer(char char_actual) {
        buffer = String.valueOf(char_actual);
        char_col ++;
        pos_char ++;
        last_char = char_actual;
    }

    private void agregarBuffer(char char_actual) {
        buffer += char_actual;
        char_col ++;
        pos_char ++;
        last_char = char_actual;
    }

    private Token S1() {
        if(Character.isLetterOrDigit(next_char = entrada[pos_char])) {
            agregarBuffer(next_char);
            return S1();
        }
        return new Token(reservadas.getOrDefault(buffer, TOK.TK_identificador), char_line, char_col, buffer);
    }

    private Token S2() {
        if((next_char = entrada[pos_char]) != '"') {
            agregarBuffer(next_char);
            return S2();
        }
        agregarBuffer(next_char);
        return S3();
    }

    private Token S3() {
        return new Token(TOK.TK_cadena, char_line, char_col, buffer);
    }

    private Token S4() {
        return new Token(TOK.TK_llaveIzq, char_line, char_col, buffer);
    }

    private Token S0() {
        Token token;

        while((next_char = entrada[pos_char]) != '\0') {
            // S1: Para identificadores
            if(Character.isLetter(next_char)) {
                iniciarBuffer(next_char);
                return S1();
            }

            // S2: Para cadenas
            if(next_char == '"') {
                iniciarBuffer(next_char);
                return S2();
            }

            // S4: Llava Izquierda
            if(next_char == '{') {
                iniciarBuffer(next_char);
                return S4();
            }

            // Caracteres Ignorados
            if(next_char == ' ') {
                char_col ++;
            } else if(next_char == '\t') {
                char_col += String.valueOf(next_char).length();
            } else if(next_char == '\n') {
                char_col = 1;
                char_line ++;
            } else { // Error Léxico
                char_col ++;
                errores.add(new Error(char_line, char_col, TipoError.LEXICO, "Caracter no reconocido. «" + next_char + "»"));
            }

            pos_char ++;
        }

        return new Token(TOK.EOF); // End Of File (EOF)
    }

    public Token siguiente_token() {
        return S0();
    }
}