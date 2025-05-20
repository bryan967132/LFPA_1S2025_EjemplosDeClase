package Classes.Utils;

import Language.TOK;

public enum ObjectType {
    TESORO       ("tesoro"      , "🎁", "box3d"    , "gold"          ),
    LLAVE        ("llave"       , "🔑", "pentagon" , "lightsteelblue"),
    ARMA         ("arma"        , "🗡️", "diamond"  , "orangered"     ),
    OBJETOMAGICO ("objetomagico", "✨", "component", "violet"        ),
    POCION       ("pocion"      , "⚗️", "cylinder" , "plum"          ),
    TRAMPA       ("trampa"      , "💣", "hexagon"  , "crimson"       ),
    LIBRO        ("libro"       , "📕", "note"     , "navajowhite"   ),
    HERRAMIENTA  ("herramienta" , "🛠️", "folder"   , "darkkhaki"     ),
    BANDERA      ("bandera"     , "🚩", "tab"      , "white"         ),
    GEMA         ("gema"        , "💎", "egg"      , "deepskyblue"   );

    private String name;
    private String shape;
    private String fillcolor;
    private String emoji;

    private ObjectType(String name, String emoji, String shape, String fillcolor) {
        this.name = name;
        this.emoji = emoji;
        this.shape = shape;
        this.fillcolor = fillcolor;
    }

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getShape() {
        return shape;
    }

    public String getFillColor() {
        return fillcolor;
    }

    public static ObjectType get(TOK tok) {
        for (ObjectType type : ObjectType.values()) {
            if (type.getName().equals(tok.getName().substring(3))) {
                return type;
            }
        }
        return null;
    }
}