package Scanner;

public enum TOK {
    TK_llaveIzq ("TK_llaveIzq"),
    TK_llaveDer ("TK_llaveDer"),
    TK_parIzq ("TK_parIzq"),
    TK_parDer ("TK_parDer"),
    TK_coma ("TK_coma"),
    TK_numero ("TK_numero"),
    KW_suma ("KW_suma"),
    KW_resta ("KW_resta"),
    EOF ("EOF");

    private String nombre;
    private TOK(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {return nombre;}
}