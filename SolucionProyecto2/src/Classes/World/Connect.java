package Classes.World;

import Classes.Abstracts.Component;
import Classes.Utils.PathType;

public class Connect extends Component {
    public String placeA;
    public String placeB;
    public PathType type;

    public Connect(String placeA, String placeB, PathType type) {
        this.placeA = placeA;
        this.placeB = placeB;
        this.type = type;
    }

    public void print() {
        System.out.println(String.format("\tConnect: {placeA = %s, placeB = %s, type = %s}", placeA, placeB, type));
    }

    public String getDot() {
        return new StringBuilder(
            String.format("\n\t%s -> %s [style=%s color=%s fontcolor=%s label=\"%s\"];", placeA, placeB, type.getStyle(), type.getColor(), type.getColor(), type.getName())
        ).toString();
    }
}