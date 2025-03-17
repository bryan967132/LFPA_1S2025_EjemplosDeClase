package Scanner;

public enum TOK {
    KW_descripcion   ("KW_descripcion"),
    TK_identificador ("TK_identificador"),
    TK_cadena        ("TK_cadena"),
    TK_llaveIzq      ("TK_llaveIzq"),
    EOF              ("EOF");

    private String nombre;
    private TOK(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}