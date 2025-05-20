package Classes.Utils;

import Language.TOK;

public enum PlaceType {
    PLAYA   ("playa"   , "ellipse"       , "lightblue"           ),
    CUEVA   ("cueva"   , "box"           , "gray"                ),
    TEMPLO  ("templo"  , "octagon"       , "gold"                ),
    JUNGLA  ("jungla"  , "parallelogram" , "forestgreen"         ),
    MONTANA ("montana" , "triangle"      , "sienna"              ),
    PUEBLO  ("pueblo"  , "house"         , "burlywood"           ),
    ISLA    ("isla"    , "invtriangle"   , "lightgoldenrodyellow"),
    RIO     ("rio"     , "hexagon"       , "deepskyblue"         ),
    VOLCAN  ("volcan"  , "doublecircle"  , "orangered"           ),
    PANTANO ("pantano" , "trapezium"     , "darkseagreen"        );

    private String name;
    private String shape;
    private String fillcolor;

    private PlaceType(String name, String shape, String fillcolor) {
        this.name = name;
        this.shape = shape;
        this.fillcolor = fillcolor;
    }

    public String getName() {
        return name;
    }

    public String getShape() {
        return shape;
    }

    public String getFillColor() {
        return fillcolor;
    }

    public static PlaceType get(TOK tok) {
        for (PlaceType type : PlaceType.values()) {
            if (type.getName().equals(tok.getName().substring(3))) {
                return type;
            }
        }
        return null;
    }
}