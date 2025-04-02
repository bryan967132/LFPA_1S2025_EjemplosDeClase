package Classes.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;
public class Outs {
    public static ArrayList<String> printConsole = new ArrayList<>();
    public static ArrayList<Error> errors = new ArrayList<>();

    public static String getStringOuts() {
        String out = printConsole.stream().map(Object::toString).collect(Collectors.joining());
        if(errors.size() > 0) {
            if(!out.equals("")) {
                out += (out.toCharArray()[out.length() - 1] != '\n' ? "\n" : "") + "\n↳ ERRORES\n";
            } else {
                out += "↳ ERRORES\n";
            }
            Outs.errors = new ArrayList<>(new HashSet<>(Outs.errors));
            Collections.sort(errors, Comparator.comparingInt((Error e) -> e.line).thenComparingInt(e -> e.column));
            out += errors.stream().map(Error::toString).collect(Collectors.joining("\n"));
        }
        return out;
    }

    public static Error newError(int line, int column, TypeError type, String description) {
        return new Error(line, column, type, description);
    }

    public static void resetOuts() {
        printConsole.clear();
        errors.clear();
    }
}