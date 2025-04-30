package Language;

public class Token {
    public TOK type;
    public int line;
    public int column;
    public String lexeme;
    public Token(TOK tipo) {
        this.type = tipo;
        this.line = -1;
        this.column = -1;
    }
    public Token(TOK tipo, int linea, int columna, String lexema) {
        this.type = tipo;
        this.line = linea;
        this.column = columna;
        this.lexeme = lexema;
    }

    public String toString() {
        return String.format("%-25s%-6s%-8s%-10s", lexeme, line, column, type.getName());
    }
}