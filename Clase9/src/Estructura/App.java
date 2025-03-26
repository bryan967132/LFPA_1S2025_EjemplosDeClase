package Estructura;
import java.util.ArrayList;

public class App {
    public static ArrayList<Token> tokens = new ArrayList<>();

    public static void main(String[] a) {
        cargarTokens();

        ArrayList<Token> copiaTokens = new ArrayList<>(tokens);

        copiaTokens.remove(0); // REMUEVE LLAVE IZQUIERDA '{'

        Token tok;
        
        while(copiaTokens.get(0).tipo != TOK.TK_llaveDer) {
            String nombreAFD;
            String descripcion;

            tok = copiaTokens.remove(0); // REMUEVE ID = "Numero"
            nombreAFD = tok.lexema;
            System.out.println("======= " + nombreAFD + " =======");

            copiaTokens.remove(0); // REMUEVE DOS PUNTOS ':'

            copiaTokens.remove(0); // REMUEVE LLAVE IZQUIERDA '{'

            // ATRIBUTOS DEL AUTOMATA
            while(copiaTokens.get(0).tipo != TOK.TK_llaveDer) {
                tok = copiaTokens.remove(0); // REMUEVE RESERVADA

                copiaTokens.remove(0); // REMUEVE DOS PUNTOS ':'

                if(tok.tipo == TOK.KW_descripcion) {
                    tok = copiaTokens.remove(0); // REMUEVE CADENA
                    descripcion = tok.lexema;
                    System.out.println("Descripcion: " + descripcion);
                } else if(tok.tipo == TOK.KW_transiciones) {
                    copiaTokens.remove(0); // REMUEVE LLAVE IZQUIERDA '{'

                    // TRANSICIONES
                    System.out.println("Transiciones:");
                    while(copiaTokens.get(0).tipo != TOK.TK_llaveDer) {
                        tok = copiaTokens.remove(0); // REMUEVE ID (NOMBRE DEL ESTADO ORIGEN)
                        String estadoOrigen = tok.lexema;

                        copiaTokens.remove(0); // REMUEVE DOS PUNTOS ':'

                        copiaTokens.remove(0); // REMUEVE DOS PUNTOS '('

                        // DETALLE DE TRANSICIÓN
                        String cadenaTransicion;
                        String estadoDestino;
                        while(copiaTokens.get(0).tipo != TOK.TK_parDer) {
                            tok = copiaTokens.remove(0); // REMUEVE STRING DE TRANSICION
                            cadenaTransicion = tok.lexema;

                            copiaTokens.remove(0); // REMUEVE DOS PUNTOS '->'

                            tok = copiaTokens.remove(0); // REMUEVE ESTADO DESTINO
                            estadoDestino = tok.lexema;

                            if(copiaTokens.get(0).tipo == TOK.TK_coma) {
                                copiaTokens.remove(0);
                            }

                            System.out.println("\tDe estado " + estadoOrigen + " se mueve con " + cadenaTransicion + " hacia estado " + estadoDestino);
                        }

                        copiaTokens.remove(0); // REMUEVE DOS PUNTOS ')'

                        if(copiaTokens.get(0).tipo == TOK.TK_coma) {
                            copiaTokens.remove(0);
                        }
                    }
                    copiaTokens.remove(0); // REMUEVE LLAVE IZQUIERDA '}'
                }

                if(copiaTokens.get(0).tipo == TOK.TK_coma) {
                    copiaTokens.remove(0);
                }
            }

            copiaTokens.remove(0); // REMUEVE LLAVE DERECHA '}'

            if(copiaTokens.get(0).tipo == TOK.TK_coma) {
                copiaTokens.remove(0);
            }
        }

        copiaTokens.remove(0); // REMUEVE LLAVE DERECHA '}'

        // for(Token token : tokens) {
        //     System.out.println(token);
        // }
    }

    public static void cargarTokens() {
        tokens.add(new Token(TOK.TK_llaveIzq)); // {
        tokens.add(new Token(TOK.TK_id, "Numero")); // Numero
        tokens.add(new Token(TOK.TK_dosPuntos)); // :
        tokens.add(new Token(TOK.TK_llaveIzq)); // {
        tokens.add(new Token(TOK.KW_descripcion)); // descripcion
        tokens.add(new Token(TOK.TK_dosPuntos)); // :
        tokens.add(new Token(TOK.TK_cadena, "Numeros decimales")); // : "Cadenas numericas"
        tokens.add(new Token(TOK.TK_coma)); // ,
        tokens.add(new Token(TOK.KW_transiciones)); // transiciones
        tokens.add(new Token(TOK.TK_dosPuntos)); // :
        tokens.add(new Token(TOK.TK_llaveIzq)); // {
        tokens.add(new Token(TOK.TK_id, "S0")); // S0
        tokens.add(new Token(TOK.TK_igual)); // =
        tokens.add(new Token(TOK.TK_parIzq)); // (
        tokens.add(new Token(TOK.TK_cadena, "digit")); // digit
        tokens.add(new Token(TOK.TK_flecha)); // ->
        tokens.add(new Token(TOK.TK_id, "S1")); // S1
        tokens.add(new Token(TOK.TK_parDer)); // )
        tokens.add(new Token(TOK.TK_coma)); // ,
        tokens.add(new Token(TOK.TK_id, "S1")); // S1
        tokens.add(new Token(TOK.TK_igual)); // =
        tokens.add(new Token(TOK.TK_parIzq)); // (
        tokens.add(new Token(TOK.TK_cadena, "digit")); // digit
        tokens.add(new Token(TOK.TK_flecha)); // ->
        tokens.add(new Token(TOK.TK_id, "S1")); // S1
        tokens.add(new Token(TOK.TK_coma)); // ,
        tokens.add(new Token(TOK.TK_cadena, ".")); // .
        tokens.add(new Token(TOK.TK_flecha)); // ->
        tokens.add(new Token(TOK.TK_id, "S2")); // S2
        tokens.add(new Token(TOK.TK_parDer)); // )
        tokens.add(new Token(TOK.TK_coma)); // ,
        tokens.add(new Token(TOK.TK_id, "S2")); // S2
        tokens.add(new Token(TOK.TK_igual)); // =
        tokens.add(new Token(TOK.TK_parIzq)); // (
        tokens.add(new Token(TOK.TK_cadena, "digit")); // digit
        tokens.add(new Token(TOK.TK_flecha)); // ->
        tokens.add(new Token(TOK.TK_id, "S3")); // S3
        tokens.add(new Token(TOK.TK_parDer)); // )
        tokens.add(new Token(TOK.TK_coma)); // ,
        tokens.add(new Token(TOK.TK_id, "S3")); // S3
        tokens.add(new Token(TOK.TK_igual)); // =
        tokens.add(new Token(TOK.TK_parIzq)); // (
        tokens.add(new Token(TOK.TK_cadena, "digit")); // digit
        tokens.add(new Token(TOK.TK_flecha)); // ->
        tokens.add(new Token(TOK.TK_id, "S3")); // S3
        tokens.add(new Token(TOK.TK_parDer)); // )
        tokens.add(new Token(TOK.TK_llaveDer)); // }
        tokens.add(new Token(TOK.TK_llaveDer)); // }
        tokens.add(new Token(TOK.TK_llaveDer)); // }
        tokens.add(new Token(TOK.EOF));
    }
}