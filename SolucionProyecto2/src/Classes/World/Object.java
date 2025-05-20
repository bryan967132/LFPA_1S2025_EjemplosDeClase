package Classes.World;

import Classes.Abstracts.Component;
import Classes.Utils.ObjectType;

public class Object extends Component {
    public String name;
    public ObjectType type;
    public ObjectPlace place;

    public Object(String name, ObjectType type, ObjectPlace place) {
        this.name = name;
        this.type = type;
        this.place = place;
    }

    public void print() {
        System.out.println(String.format("\tObject: {name = %s, type = %s, place = %s}", name, type, place));
    }

    public String getDot() {
        return new StringBuilder(
            place.thereAreCoords() ?
            String.format("\n\t%s [shape=%s fillcolor=%s label=\"%s %s\" pos=\"%s\"];", name.replace(" ", "_"), type.getShape(), type.getFillColor(), type.getEmoji(), name, place.getDot()) :
            String.format("\n\t%s [shape=%s fillcolor=%s label=\"%s %s\"];", name.replace(" ", "_"), type.getShape(), type.getFillColor(), type.getEmoji(), name) +
            String.format("\n\t%s -> %s [style=dashed color=black label=\"en\" dir=none];", name.replace(" ", "_"), place.getDot())
        ).toString();
    }
}