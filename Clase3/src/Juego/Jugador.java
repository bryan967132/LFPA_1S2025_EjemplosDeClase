package Juego;

public class Jugador {
    // Atributos
    public String nombre;
    public int salud;
    public int ataque;
    public int defensa;

    public Jugador(String nombre, int salud, int ataque, int defensa) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
    }
}