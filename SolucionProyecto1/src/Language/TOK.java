package Language;

public enum TOK {
    RW_descripcion  ("RW_descripcion"),
    RW_estados      ("RW_estados"),
    RW_alfabeto     ("RW_alfabeto"),
    RW_inicial      ("RW_inicial"),
    RW_finales      ("RW_finales"),
    RW_transiciones ("RW_transiciones"),
    TK_id           ("TK_id"),
    TK_string       ("TK_string"),
    TK_arrow        ("TK_arrow"),
    TK_colon        ("TK_colon"),
    TK_comma        ("TK_comma"),
    TK_assign       ("TK_assign"),
    TK_lbrc         ("TK_lbrc"),
    TK_rbrc         ("TK_rbrc"),
    TK_lbrk         ("TK_lbrk"),
    TK_rbrk         ("TK_rbrk"),
    TK_lpar         ("TK_lpar"),
    TK_rpar         ("TK_rpar"),
    EOF             ("EOF");

    private String name;
    private TOK(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}