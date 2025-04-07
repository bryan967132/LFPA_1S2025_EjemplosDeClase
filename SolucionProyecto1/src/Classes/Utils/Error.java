package Classes.Utils;

import java.util.Objects;

public class Error {
    public int line;
    public int column;
    public TypeError type;
    public String description;

    public Error(int line, int column, TypeError type, String description) {
        this.line = line;
        this.column = column;
        this.type = type;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Error error = (Error) obj;
        return toString().equals(error.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    public String toString() {
        return "→ Error " + type.getValue() + ", " + this.description + ". " + line + ":" + column;
    }
}