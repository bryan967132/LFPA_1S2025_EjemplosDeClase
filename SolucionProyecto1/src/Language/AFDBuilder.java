package Language;

import java.util.ArrayList;
import java.util.HashMap;

import Classes.AFD.AFD;
import Classes.AFD.Transition;

public class AFDBuilder {
    public ArrayList<Token> tokens = new ArrayList<>();
    private Scanner sc;
    public AFDBuilder(Scanner sc) {
        this.sc = sc;
    }
    public HashMap<String, AFD> getAFDs() {
        Token tok;
        ArrayList<Token> tokens = new ArrayList<>();
        do {
            tok = sc.next_token();
            tokens.add(tok);
        } while(tok.type != TOK.EOF);

        this.tokens.addAll(tokens);

        tokens.remove(0); // Llave Izquierda
        HashMap<String, AFD> afds = new HashMap<>();
        while(tokens.get(0).type != TOK.TK_rbrc) {
            Token token = tokens.remove(0); // ID: Nombre del AFD
            String nombre = token.lexeme;
            tokens.remove(0); // Dos Puntos
            tokens.remove(0); // Llave Izquierda
            afds.put(nombre, buildAFD(nombre, tokens));
            tokens.remove(0); // Llave Derecha

            if(tokens.get(0).type == TOK.TK_comma) {
                tokens.remove(0); // Coma
            }
        }
        tokens.remove(0); // Llave Izquierda
        return afds;
    }

    private AFD buildAFD(String nombre, ArrayList<Token> tokensAFDs) {
        String descripcion = "";
        ArrayList<String> estados = new ArrayList<>();
        ArrayList<String> alfabeto = new ArrayList<>();
        String estadoInicial = "";
        ArrayList<String> finales = new ArrayList<>();
        String estadoOrigen = "";
        String charTransicion = "";
        String estadoDestino = "";
        HashMap<String, Transition> transiciones = new HashMap<>();

        while(tokensAFDs.get(0).type != TOK.TK_rbrc) {
            Token token = tokensAFDs.remove(0); // Reservada
            tokensAFDs.remove(0); // Dos Puntos
            if(token.type == TOK.RW_descripcion) {
                token = tokensAFDs.remove(0); // Cadena
                descripcion = token.lexeme;
            } else if(token.type == TOK.RW_estados) {
                tokensAFDs.remove(0); // Corchete Izquierdo
                while(tokensAFDs.get(0).type != TOK.TK_rbrk) {
                    token = tokensAFDs.remove(0); // Identificador
                    estados.add(token.lexeme);

                    if(tokensAFDs.get(0).type == TOK.TK_comma) {
                        tokensAFDs.remove(0); // Coma
                    }
                }
                tokensAFDs.remove(0); // Corchete Derecho
            } else if(token.type == TOK.RW_alfabeto) {
                tokensAFDs.remove(0); // Corchete Izquierdo
                while(tokensAFDs.get(0).type != TOK.TK_rbrk) {
                    token = tokensAFDs.remove(0); // Cadena
                    alfabeto.add(token.lexeme);

                    if(tokensAFDs.get(0).type == TOK.TK_comma) {
                        tokensAFDs.remove(0); // Coma
                    }
                }
                tokensAFDs.remove(0); // Corchete Derecho
            } else if(token.type == TOK.RW_inicial) {
                token = tokensAFDs.remove(0); // Identificador
                estadoInicial = token.lexeme;
            } else if(token.type == TOK.RW_finales) {
                tokensAFDs.remove(0); // Corchete Izquierdo
                while(tokensAFDs.get(0).type != TOK.TK_rbrk) {
                    token = tokensAFDs.remove(0); // Identificador
                    finales.add(token.lexeme);

                    if(tokensAFDs.get(0).type == TOK.TK_comma) {
                        tokensAFDs.remove(0); // Coma
                    }
                }
                tokensAFDs.remove(0); // Corchete Derecho
            } else if(token.type == TOK.RW_transiciones) {
                tokensAFDs.remove(0); // Llave Izquierda
                while(tokensAFDs.get(0).type != TOK.TK_rbrc) {
                    token = tokensAFDs.remove(0); // Estado Origen
                    estadoOrigen = token.lexeme;
                    tokensAFDs.remove(0); // Igual

                    HashMap<String, String> transicionesS = new HashMap<>();

                    tokensAFDs.remove(0); // Parentesis Izquierdo

                    while(tokensAFDs.get(0).type != TOK.TK_rpar) {
                        token = tokensAFDs.remove(0); // Caracter de Transicion
                        charTransicion = token.lexeme;
                        tokensAFDs.remove(0); // Flecha
                        token = tokensAFDs.remove(0); // Identificador
                        estadoDestino = token.lexeme;

                        transicionesS.put(charTransicion, estadoDestino);

                        if(tokensAFDs.get(0).type == TOK.TK_comma) {
                            tokensAFDs.remove(0); // Coma
                        }
                    }

                    tokensAFDs.remove(0); // Parentesis Derecho

                    transiciones.put(estadoOrigen, new Transition(transicionesS));

                    if(tokensAFDs.get(0).type == TOK.TK_comma) {
                        tokensAFDs.remove(0); // Coma
                    }
                }
                tokensAFDs.remove(0); // Llave Derecha
            }

            if(tokensAFDs.get(0).type == TOK.TK_comma) {
                tokensAFDs.remove(0);
            }
        }

        return new AFD(nombre, descripcion, estados, alfabeto, estadoInicial, finales, transiciones);
    }
}