package Classes.World;

public class ObjectPlace {
    public int x;
    public int y;
    public String placeName;

    public ObjectPlace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ObjectPlace(String placeName) {
        this.placeName = placeName;
    }

    public String toString() {
        return placeName != null ? placeName : String.format("(%d, %d)", x, y);
    }

    public String getDot() {
        return placeName != null ? placeName : String.format("%d, %d!", x, y);
    }

    public boolean thereAreCoords() {
        return placeName == null;
    }
}