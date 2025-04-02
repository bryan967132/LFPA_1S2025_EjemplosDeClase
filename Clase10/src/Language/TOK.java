package Language;

public enum TOK {
    KW_imprimir ("KW_imprimir"),
    KW_entero   ("KW_entero"),
    TK_entero   ("TK_entero"),
    TK_id       ("TK_id"),
    TK_cadena   ("TK_cadena"),
    TK_igual    ("TK_igual"),
    TK_llaveIzq ("TKllaveIzqc"),
    TK_llaveDer ("TK_llaveDer"),
    TK_parIzq   ("TK_parIzq"),
    TK_parDer   ("TK_parDer"),
    TK_ptComa   ("TK_ptComa"),
    TK_mas      ("TK_mas"),
    EOF         ("EOF");

    private String name;
    private TOK(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}