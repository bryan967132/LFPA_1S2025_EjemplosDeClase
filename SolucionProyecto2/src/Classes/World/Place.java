package Classes.World;

import Classes.Abstracts.Component;
import Classes.Utils.PlaceType;

public class Place extends Component {
    public String name;
    public PlaceType type;
    public int x;
    public int y;

    public Place(String name, PlaceType type, int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.println(String.format("\tPlace: {at = (%d, %d), name = \"%s\", type = %s}", x, y, name, type));
    }

    public String getDot() {
        return new StringBuilder(
            String.format("\n\t%s [shape=%s fillcolor=%s pos=\"%d, %d!\"];", name, type.getShape(), type.getFillColor(), x, y)
        ).toString();
    }
}