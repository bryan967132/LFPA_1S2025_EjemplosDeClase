package Language;

public enum TOK {
    KW_world   ("KW_world"),
    KW_place   ("KW_place"),
    KW_playa   ("KW_playa"),
    KW_cueva   ("KW_cueva"),
    KW_templo  ("KW_templo"),
    KW_isla    ("KW_isla"),
    KW_pueblo  ("KW_pueblo"),
    KW_at      ("KW_at"),
    KW_connect ("KW_connect"),
    KW_to      ("KW_to"),
    KW_with    ("KW_with"),
    KW_object  ("KW_object"),
    KW_sendero ("KW_sendero"),
    KW_puente  ("KW_puente"),
    KW_lancha  ("KW_lancha"),
    KW_carretera ("KW_carretera"),
    KW_tesoro  ("KW_tesoro"),
    KW_llave   ("KW_llave"),
    KW_libro   ("KW_libro"),
    TK_string  ("TK_string"),
    TK_id      ("TK_id"),
    TK_int     ("TK_int"),
    TK_lpar    ("TK_lpar"),
    TK_rpar    ("TK_rpar"),
    TK_lbrc    ("TK_lbrc"),
    TK_rbrc    ("TK_rbrc"),
    TK_comma   ("TK_comma"),
    TK_colon   ("TK_colon"),
    EOF        ("EOF");

    private String name;
    private TOK(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}