package Classes.World;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Classes.Abstracts.Component;

public class World {
    public String name;
    public ArrayList<Component> components;

    public World(String name, ArrayList<Component> components) {
        this.name = name;
        this.components = components;
    }

    public void print() {
        System.out.println("World: " + name);
        for(Component cmp : components) {
            cmp.print();
        }
    }

    public String getDot() {
        return new StringBuilder("digraph \"" + name + "\" {")
            .append("\n\tnode[style=filled];")
            .append("\n\tlayout=neato;")
            .append("\n\trankdir=LR;")
            .append(components.stream().map(Component::getDot).collect(Collectors.joining()))
            .append("\n}")
            .toString();
    }
}