package Analizador;

import java.util.ArrayList;

import Juego.Jugador;

public class Analizador {
    // Atributos
    public String entrada;

    public Analizador(String entrada) {
        this.entrada = entrada;
    }

    public ArrayList<Jugador> analizar() {
        String[] filas = this.entrada.split("\n");
        String[] columnas;

        ArrayList<Jugador> jugadores = new ArrayList<>();

        for(int i = 1; i < filas.length; i ++) {
            columnas = filas[i].split(";");

            jugadores.add(new Jugador(
                columnas[0],
                Integer.parseInt(columnas[1]),
                Integer.parseInt(columnas[2]),
                Integer.parseInt(columnas[3])
            ));
        }

        return jugadores;
    }

    public void obtenerResultados() {
        // código para obtener resultados
        System.out.println("obteniendo resultados...");
    }

    public void obtenerErrores() {
        // código para obtener errores
        System.out.println("obteniendo errores...");
    }
}