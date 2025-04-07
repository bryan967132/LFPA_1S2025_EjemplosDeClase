package Classes.AFD;

import java.util.ArrayList;
import java.util.HashMap;

public class Attrib {
    public String name;
    public String value;
    public ArrayList<String> valueArray;
    public HashMap<String, Transition> valueHash;

    public Attrib(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Attrib(String name, ArrayList<String> valueArray) {
        this.name = name;
        this.valueArray = valueArray;
    }

    public Attrib(String name, HashMap<String, Transition> valueHash) {
        this.name = name;
        this.valueHash = valueHash;
    }
}