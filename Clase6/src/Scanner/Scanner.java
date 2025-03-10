package Scanner;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.Error;
import Utils.TipoError;

public class Scanner {

    // Posiciones de caracter
    private int char_line = 1;
    private int char_col = 1;
    private int char_pos = 0;

    // Caracter
    private char next_char;
    private char last_char;

    // Lexema
    private String buffer;

    // Palabras Clave o Reservadas
    private HashMap<String, TOK> keywords = new HashMap<>() {{
        put("suma", TOK.KW_suma);
        put("resta", TOK.KW_resta);
    }};

    // Secuencia de caracteres de entrada
    private char[] input;

    // Lista de errores léxicos
    public ArrayList<Error> errores = new ArrayList<>();

    public Scanner(String input) {
        this.input = (input + '\0').toCharArray();
    }

    private String getBuffer() {
        return buffer;
    }

    private void iniciarBuffer(char char_actual) {
        buffer = String.valueOf(char_actual);
        char_col ++;
        char_pos ++;
        last_char = char_actual;
    }

    private void agregarBuffer(char char_actual) {
        buffer += char_actual;
        char_col ++;
        char_pos ++;
        last_char = char_actual;
    }

    private Token S1() {
        return new Token(TOK.TK_llaveIzq, getBuffer(), char_line, char_col);
    }

    private Token S2() {
        return new Token(TOK.TK_llaveDer, getBuffer(), char_line, char_col);
    }

    private Token S3() {
        return new Token(TOK.TK_parIzq, getBuffer(), char_line, char_col);
    }

    private Token S4() {
        return new Token(TOK.TK_parDer, getBuffer(), char_line, char_col);
    }

    private Token S5() {
        return new Token(TOK.TK_coma, getBuffer(), char_line, char_col);
    }

    private Token S6() {
        if(Character.isDigit(next_char = input[char_pos])) {
            agregarBuffer(next_char);
            return S6();
        }
        return new Token(TOK.TK_numero, getBuffer(), char_line, char_col);
    }

    private Token S7() {
        if(Character.isLetterOrDigit(next_char = input[char_pos])) {
            agregarBuffer(next_char);
            return S7();
        }
        TOK resultado = keywords.getOrDefault(buffer, null);
        if(resultado == TOK.KW_suma || resultado == TOK.KW_resta) {
            return new Token(resultado, getBuffer(), char_line, char_col);
        }
        errores.add(new Error(TipoError.LEXICO, getBuffer(), char_line, char_col));
        return null;
    }

    private void S8() {
        if((next_char = input[char_pos]) != '"') {
            agregarBuffer(next_char);
            S8();
            return;
        }
        agregarBuffer(next_char);
        S9();
    }

    private void S9() {}

    // Extrae un token cada vez que se llama a la función
    public Token next_token() {
        return S0();
    }

    private Token S0() {
        Token token_tmp;
        while((next_char = input[char_pos]) != '\0') {
            // Tokens y palabras reservadas
            // Estado S1
            if(next_char == '{') {
                iniciarBuffer(next_char);
                return S1();
            }
            // Estado S2
            if(next_char == '}') {
                iniciarBuffer(next_char);
                return S2();
            }
            // Estado S3
            if(next_char == '(') {
                iniciarBuffer(next_char);
                return S3();
            }
            // Estado S4
            if(next_char == ')') {
                iniciarBuffer(next_char);
                return S4();
            }
            // Estado S5
            if(next_char == ',') {
                iniciarBuffer(next_char);
                return S5();
            }
            // Estado S6: Numeros
            if(Character.isDigit(next_char)) {
                iniciarBuffer(next_char);
                return S6();
            }
            // Estados S7: Reservadas
            if(Character.isLetter(next_char)) {
                iniciarBuffer(next_char);
                if((token_tmp = S7()) != null) {
                    return token_tmp;
                }
                continue;
            }
            // Estado S8: Comentarios (Se ingnoran)
            if(next_char == '"') {
                iniciarBuffer(next_char);
                S8();
                continue;
            }

            // Estado S10: Caracteres que se van a ignorar
            if(next_char == ' ') {
                char_col ++;
            } else if(next_char == '\t') {
                char_col += String.valueOf(next_char).length();
            } else if(next_char == '\n') {
                char_line ++;
                char_col = 1;
            }
            // Errores léxicos
            else {
                errores.add(new Error(TipoError.LEXICO, String.valueOf(next_char), char_line, char_col));
            }

            char_pos ++;
        }

        return new Token(TOK.EOF);
    }
}