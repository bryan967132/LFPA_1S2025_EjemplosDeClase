package Language;

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