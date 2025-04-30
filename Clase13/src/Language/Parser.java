package Language;

import java.util.Arrays;

import Classes.Utils.Error;
import Classes.Utils.Outs;
import Classes.Utils.TypeError;

public class Parser {
    private Scanner sc;
    private Token lookahead;

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    public void parse() {
        START();
    }

    private void START() {
        // <START> ::= <WORLDS> EOF
        WORLDS();

        while((lookahead = sc.next_token()).type != TOK.EOF) {
            Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba '" + lookahead.lexeme + "'"));
        }
    }

    private void WORLDS() {
        // <WORLDS> ::= <WORLD> ',' <WORLDS> | <WORLD>
        try {
            WORLD();
        } catch(Exception e) {}

        if(match(TOK.EOF)) {
            return;
        }

        if(match(TOK.TK_comma)) {
            WORLDS();
            return;
        }

        // Recuperación de Errores Sintácticos: MODO PÁNICO
        while(!Arrays.asList(TOK.TK_comma, TOK.EOF).contains((lookahead = sc.next_token()).type)) {
            Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba '" + lookahead.lexeme + "'"));
        }
        Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba '" + lookahead.lexeme + "'"));

        if(match(TOK.TK_comma)) {
            WORLDS();
            return;
        }
    }

    private void WORLD() {
        // <WORLD> ::= 'world' TK_string '{' <ELEMENTS> '}'
        consume(TOK.KW_world);
        consume(TOK.TK_string);
        consume(TOK.TK_lbrc);
        ELEMENTS();        
        consume(TOK.TK_rbrc);
    }

    private void ELEMENTS() {
        // <ELEMENTS> ::= <ELEMENT> <ELEMENTS> | <ELEMENT>
        try {
            ELEMENT();
        } catch(Exception e) {}

        if(match(TOK.TK_rbrc)) {
            return;
        }

        if(match(TOK.KW_place, TOK.KW_connect, TOK.KW_object)) {
            ELEMENTS();
            return;
        }

        // Recuperación de Errores Sintácticos: MODO PÁNICO.
        while(!Arrays.asList(TOK.TK_rpar, TOK.EOF).contains((lookahead = sc.next_token()).type)) {
            Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba '" + lookahead.lexeme + "'"));
        }
        Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba '" + lookahead.lexeme + "'"));

        if(match(TOK.KW_place, TOK.KW_connect, TOK.KW_object)) {
            ELEMENTS();
            return;
        }
    }

    private void ELEMENT() {
        // <ELEMENT> ::= <PLACE> | <CONNECT> | <OBJECT>
        if(match(TOK.KW_place)) {
            PLACE();
            return;
        }
        // if(match(TOK.KW_connect)) {
        //     CONNECT();
        //     return;
        // }
        // if(match(TOK.KW_object)) {
        //     OBJECT();
        //     return;
        // }
    }

    private void PLACE() {
        // <PLACE> ::= 'place' TK_id ':' <PLACETYPE> 'at' '(' TK_int ',' TK_int ')'
        consume(TOK.KW_place);
        Token t = consume(TOK.TK_id);
        System.out.println(t.lexeme);
        consume(TOK.TK_colon);
        PLACETYPE();
        consume(TOK.KW_at);
        consume(TOK.TK_lpar);
        consume(TOK.TK_int);
        consume(TOK.TK_comma);
        consume(TOK.TK_int);
        consume(TOK.TK_rpar);
    }

    private void PLACETYPE() {
        // <PLACETYPE> ::= 'playa'  | 'cueva'  | 'templo' | 'isla'   | 'pueblo'
        if(match(TOK.KW_playa, TOK.KW_cueva, TOK.KW_templo, TOK.KW_isla, TOK.KW_pueblo)) {
            consume(lookahead.type);
        }
    }

    private Token consume(TOK type) {
        if(match(type)) {
            return sc.next_token();
        }
        Outs.errors.add(new Error(lookahead.line, lookahead.column, TypeError.SYNTAX, "No se esperaba '" + lookahead.lexeme + "'"));
        // sc.next_token();
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