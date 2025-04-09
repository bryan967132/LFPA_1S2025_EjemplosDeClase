package Language;

import java.util.HashMap;

import Classes.Utils.Error;
import Classes.Utils.Outs;
import Classes.Utils.TypeError;

public class Parser {
    private Scanner sc;
    private Token lookahead;

    private HashMap<String, Integer> variables = new HashMap<>();

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    public void parse() {
        INICIO();
    }

    private void INICIO() {
        INSTRUCCIONES();
    }

    private void INSTRUCCIONES() {
        // <INSTRUCCIONES> ::=<INSTRUCCION> <INSTRUCCIONES> | <INSTRUCCION>
        INSTRUCCION();

        if(match(TOK.EOF)) {
            return;
        }

        if(match(TOK.KW_entero, TOK.KW_imprimir)) {
            INSTRUCCIONES();
            return;
        }

        Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba «" + (lookahead.lexeme != null ? lookahead.lexeme : "EOF") + "»"));
    }

    private void INSTRUCCION() {
        // <INSTRUCCION> ::= <DECLARACION> | <IMPRESION>
        if(match(TOK.KW_entero)) {
            DECLARACION();
            return;
        }
        if(match(TOK.KW_imprimir)) {
            IMPRESION();
            return;
        }
    }

    private void DECLARACION() {
        // <DECLARACION> ::= 'entero' TK_id '=' <EXP> ';'
        consume(TOK.KW_entero);
        Token name = consume(TOK.TK_id);
        consume(TOK.TK_igual);
        int v = EXP();
        variables.put(name.lexeme, v);
        consume(TOK.TK_ptComa);
    }

    private void IMPRESION() {
        // <IMPRESION> ::= 'imprimir' '(' <EXP> ')' ';'
        consume(TOK.KW_imprimir);
        consume(TOK.TK_parIzq);
        int v = EXP();
        Outs.printConsole.add(String.valueOf(v));
        consume(TOK.TK_parDer);
        consume(TOK.TK_ptComa);
    }

    private int EXP() {
        // <EXP> ::= <EXP1> '+' <EXP> | <EXP1>
        int v1 = EXP1();

        if(!match(TOK.TK_mas)) {
            return v1;
        }

        consume(TOK.TK_mas);

        int v2 = EXP();
        return v1 + v2;
    }

    private int EXP1() {
        // <EXP1> ::= <VALOR> '*' <EXP1> | <VALOR>
        int v1 = VALOR();

        if(!match(TOK.TK_por)) {
            return v1;
        }

        consume(TOK.TK_por);

        int v2 = EXP1();
        return v1 * v2;
    }

    private int VALOR() {
        // <VALOR> ::= TK_id | TK_entero
        if(match(TOK.TK_id, TOK.TK_entero)) {
            Token p = consume(lookahead.type);
            return
                p.type == TOK.TK_id ?
                variables.get(p.lexeme) :
                Integer.parseInt(p.lexeme);
        }
        return 0;
    }

    private Token consume(TOK type) {
        if(match(type)) {
            return sc.next_token();
        }
        Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba «" + (lookahead.lexeme != null ? lookahead.lexeme : "EOF") + "»"));
        sc.next_token();
        return null;
    }

    private boolean match(TOK... types) {
        lookahead = sc.look_ahead();
        for(TOK type : types) {
            if(lookahead.type == type) {
                return true;
            }
        }
        return false;
    }
}