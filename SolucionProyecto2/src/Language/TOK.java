package Language;

public enum TOK {
    KW_world        ("KW_world"),
    KW_place        ("KW_place"),
    KW_connect      ("KW_connect"),
    KW_object       ("KW_object"),
    KW_with         ("KW_with"),
    KW_at           ("KW_at"),
    KW_to           ("KW_to"),
    KW_playa        ("KW_playa"),
    KW_cueva        ("KW_cueva"),
    KW_templo       ("KW_templo"),
    KW_jungla       ("KW_jungla"),
    KW_montana      ("KW_montana"),
    KW_pueblo       ("KW_pueblo"),
    KW_isla         ("KW_isla"),
    KW_rio          ("KW_rio"),
    KW_volcan       ("KW_volcan"),
    KW_pantano      ("KW_pantano"),
    KW_tesoro       ("KW_tesoro"),
    KW_llave        ("KW_llave"),
    KW_arma         ("KW_arma"),
    KW_objetomagico ("KW_objetomagico"),
    KW_pocion       ("KW_pocion"),
    KW_trampa       ("KW_trampa"),
    KW_libro        ("KW_libro"),
    KW_herramienta  ("KW_herramienta"),
    KW_bandera      ("KW_bandera"),
    KW_gema         ("KW_gema"),
    KW_camino       ("KW_camino"),
    KW_puente       ("KW_puente"),
    KW_sendero      ("KW_sendero"),
    KW_carretera    ("KW_carretera"),
    KW_nado         ("KW_nado"),
    KW_lancha       ("KW_lancha"),
    KW_teleferico   ("KW_teleferico"),
    TK_id           ("TK_id"),
    TK_string       ("TK_string"),
    TK_int          ("TK_int"),
    TK_lBrc         ("TK_lBrc"),
    TK_rBrc         ("TK_rBrc"),
    TK_lPar         ("TK_lPar"),
    TK_rPar         ("TK_rPar"),
    TK_colon        ("TK_colon"),
    TK_comma        ("TK_comma"),
    EOF             ("EOF");

    private String name;
    private TOK(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}