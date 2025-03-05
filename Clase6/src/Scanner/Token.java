package Scanner;

public class Token {
    // Atributos de Token
    public TOK tipo;
    public String lexema;
    public int linea;
    public int columna;

    public Token(TOK tipo) {
        this.tipo = tipo;
        this.linea = -1;
        this.columna = -1;
    }

    public Token(TOK tipo, String lexema, int linea, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
    }
}