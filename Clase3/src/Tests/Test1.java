package Tests;

import java.util.ArrayList;

import Analizador.Analizador;
import Archivos.Controlador;
import Juego.Jugador;

public class Test1 {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        String informacion = controlador.leerArchivo("./Entradas/Entrada1.lfp");

        Analizador analizador = new Analizador(informacion);

        ArrayList<Jugador> jugadores = analizador.analizar();

        for(Jugador jugador : jugadores) {
            System.out.println("Nombre: " + jugador.nombre + ", Salud: " + jugador.salud + ", Ataque: " + jugador.ataque + ", Defensa: " + jugador.defensa);
        }
    }
}