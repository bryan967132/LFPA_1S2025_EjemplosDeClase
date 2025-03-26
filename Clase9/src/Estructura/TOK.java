package Estructura;

public enum TOK {
    KW_descripcion  ("KW_descripcion"),
    KW_transiciones ("KW_transiciones"),
    TK_id           ("TK_id"),
    TK_cadena       ("TK_cadena"),
    TK_flecha       ("TK_flecha"),
    TK_dosPuntos    ("TK_dosPuntos"),
    TK_coma         ("TK_coma"),
    TK_igual        ("TK_igual"),
    TK_llaveIzq     ("TK_llaveIzq"),
    TK_llaveDer     ("TK_llaveDer"),
    TK_parIzq       ("TK_parIzq"),
    TK_parDer       ("TK_parDer"),
    EOF             ("EOF");

    private String nombre;
    private TOK(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}