package Language;

import java.util.Map;
import java.util.TreeMap;

import Classes.Utils.Error;
import Classes.Utils.Outs;
import Classes.Utils.TypeError;

public class Scanner {
    private int pos_char = 0;
    private int char_line = 1;
    private int char_col = 1;
    private char[] input;
    private char last_char;
    private char next_char;
    private String buffer = "";
    private boolean look_ahead = false;

    private Map<String, TOK> keywords = new TreeMap<>() {{
        put("imprimir", TOK.KW_imprimir);
        put("entero",   TOK.KW_entero);
    }};

    public Scanner(String input) {
        this.input = (input + '\0').toCharArray();
        pos_char = 0;
    }

    private void initBuffer(char current_char) {
        buffer = String.valueOf(current_char);
        char_col ++;
        pos_char ++;
        last_char = current_char;
    }

    private void addBuffer(char current_char) {
        buffer += current_char;
        char_col ++;
        pos_char ++;
        last_char = current_char;
    }

    private Token S1() {
        if(Character.isLetterOrDigit(next_char = input[pos_char]) || next_char == '_') {
            addBuffer(next_char);
            return S1();
        }
        return new Token(keywords.getOrDefault(buffer, TOK.TK_id), char_line, char_col, buffer);
    }

    private Token S2() {
        if((next_char = input[pos_char]) != '"') {
            addBuffer(next_char);
            return S2();
        }
        addBuffer(next_char);
        return S3();
    }

    private Token S3() {
        return new Token(TOK.TK_cadena, char_line, char_col, buffer.substring(1, buffer.length() - 1));
    }

    private Token S4() {
        if(Character.isDigit(next_char = input[pos_char])) {
            addBuffer(next_char);
            return S4();
        }
        return new Token(TOK.TK_entero, char_line, char_col, buffer);
    }

    private Token S5() {
        return new Token(TOK.TK_igual, char_line, char_col, buffer);
    }

    private Token S6() {
        return new Token(TOK.TK_llaveIzq, char_line, char_col, buffer);
    }

    private Token S7() {
        return new Token(TOK.TK_llaveDer, char_line, char_col, buffer);
    }

    private Token S8() {
        return new Token(TOK.TK_parIzq, char_line, char_col, buffer);
    }

    private Token S9() {
        return new Token(TOK.TK_parDer, char_line, char_col, buffer);
    }

    private Token S10() {
        return new Token(TOK.TK_ptComa, char_line, char_col, buffer);
    }

    private Token S11() {
        return new Token(TOK.TK_mas, char_line, char_col, buffer);
    }

    private Token S12() {
        return new Token(TOK.TK_por, char_line, char_col, buffer);
    }

    private Token S0() {
        while(pos_char < input.length && (next_char = input[pos_char]) != '\0') {
            if(Character.isLetter(next_char)) { // ID || Reservadas
                initBuffer(next_char);
                return S1();
            }

            if(next_char == '"') { // Cadenas
                initBuffer(next_char);
                return S2();
            }

            if(Character.isDigit(next_char)) { // Enteros
                initBuffer(next_char);
                return S4();
            }

            if(next_char == '=') { // Igual
                initBuffer(next_char);
                return S5();
            }

            if(next_char == '{') { // Llave Izquierda
                initBuffer(next_char);
                return S6();
            }

            if(next_char == '}') { // Llave Derecha
                initBuffer(next_char);
                return S7();
            }

            if(next_char == '(') { // Parentesis Izquierdo
                initBuffer(next_char);
                return S8();
            }

            if(next_char == ')') { // Parentesis Derecho
                initBuffer(next_char);
                return S9();
            }

            if(next_char == ';') { // Punto y coma
                initBuffer(next_char);
                return S10();
            }

            if(next_char == '+') { // Más
                initBuffer(next_char);
                return S11();
            }

            if(next_char == '*') { // Por
                initBuffer(next_char);
                return S12();
            }

            // Caracteres Ignorados
            if(next_char == ' ') {
                char_col ++;
            } else if(next_char == '\t') {
                char_col += String.valueOf(next_char).length();
            } else if(next_char == '\n') {
                char_col = 1;
                char_line ++;
            } else { // Errores Léxicos
                char_col ++;
                if(!look_ahead) {
                    Outs.errors.add(new Error(char_line, char_col, TypeError.LEXICAL, "Caracter no reconocido. «" + next_char + "»"));
                }
            }

            pos_char ++;
        }

        return new Token(TOK.EOF, char_line, char_col, "EOF");
    }

    public Token next_token() {
        return S0();
    }

    public Token look_ahead() {
        int pos_char_aux = pos_char;
        int char_line_aux = char_line;
        int char_col_aux = char_col;
        char last_char_aux = last_char;
        char next_char_aux = next_char;
        String buffer_aux = buffer;
        look_ahead = true;
        Token token = next_token();
        pos_char = pos_char_aux;
        char_line = char_line_aux;
        char_col = char_col_aux;
        last_char = last_char_aux;
        next_char = next_char_aux;
        buffer = buffer_aux;
        look_ahead = false;
        return token;
    }
}