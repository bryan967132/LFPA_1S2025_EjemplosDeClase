package Classes.Utils;

import Language.TOK;

public enum PathType {
    CAMINO     ("camino"     , "solid"  , "black"      ),
    PUENTE     ("puente"     , "dotted" , "gray"       ),
    SENDERO    ("sendero"    , "dashed" , "saddlebrown"),
    CARRETERA  ("carretera"  , "solid"  , "darkgray"   ),
    NADO       ("nado"       , "dashed" , "deepskyblue"),
    LANCHA     ("lancha"     , "solid"  , "blue"       ),
    TELEFERICO ("teleferico" , "dotted" , "purple"     );

    private String name;
    private String style;
    private String color;

    private PathType(String name, String style, String color) {
        this.name = name;
        this.style = style;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public String getColor() {
        return color;
    }

    public static PathType get(TOK tok) {
        for (PathType type : PathType.values()) {
            if (type.getName().equals(tok.getName().substring(3))) {
                return type;
            }
        }
        return null;
    }
}