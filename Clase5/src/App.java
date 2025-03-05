import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class App {
    static ArrayList<Jugador> jugadores = new ArrayList<>();
    static ArrayList<Jugador> clonJugadores = new ArrayList<>();

    public static void main(String[] a) {
        cargarArchivo("./Entrada1.lfp");
    }

    public static void cargarArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            String[] jugador;

            br.readLine();

            jugadores.clear();
            clonJugadores.clear();

            while((linea = br.readLine()) != null) {
                jugador = linea.split(";");
                jugadores.add(new Jugador(jugador[0], Integer.parseInt(jugador[1]), Integer.parseInt(jugador[2]), Integer.parseInt(jugador[3])));
            }

            // Clon de la lista original para reportes
            for(Jugador j : jugadores) {
                clonJugadores.add(j.clone());
            }

            // jugadores.get(0).ataque = 80;

            // System.out.println("ORIGINAL");
            // for(Jugador j : jugadores) {
            //     System.out.println("Nombre: " + j.nombre + ", Salud: " + j.salud + ", Ataque: " + j.ataque + ", Defensa: " + j.defensa);
            // }
            
            // System.out.println("CLON");
            // for(Jugador j : clonJugadores) {
            //     System.out.println("Nombre: " + j.nombre + ", Salud: " + j.salud + ", Ataque: " + j.ataque + ", Defensa: " + j.defensa);
            // }

            int rondaN = 1;
            do {
                ArrayList<Pelea> ronda = new ArrayList<>();
                System.out.println("\u001B[96mRONDA: " + rondaN + "\u001B[0m");
                Jugador jug1, jug2 = null;
                for(int i = 0; i < jugadores.size(); i += 2) {
                    jug1 = jugadores.get(i);
                    jug2 = i < jugadores.size() - 1 ? jugadores.get(i + 1) : null;

                    ronda.add(new Pelea(jug1, jug2));
                }

                jugadores.clear();

                for(Pelea pelea : ronda) {
                    jugadores.add(pelea.iniciar());
                }

                rondaN ++;
            } while(jugadores.size() > 1);

            System.out.println("\u001B[32mGANADOR DEL TORNEO: " + jugadores.get(0) + "\u001B[0m");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Pelea {
    public Jugador j1;
    public Jugador j2;

    public Pelea(Jugador j1, Jugador j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    public Jugador iniciar() {
        j1.revivir();
        if(j2 == null) {
            System.out.println("\u001B[33mGana Jugador 1 (Default): " + j1 + "\u001B[0m");
            return j1;
        }
        j2.revivir();

        int dano1 = j1.ataque - j2.defensa;
        int dano2 = j2.ataque - j1.defensa;

        System.out.println("\u001B[33m" + j1 + " vs " + j2 + "\u001B[0m");
        if(dano1 > 0 || dano2 > 0) {
            while(j1.vida > 0 && j2.vida > 0) {
                if(dano1 > 0) {
                    j2.vida -= dano1;
                    // impresión del detalle de ataque de j1 a j2
                } else {
                    // impresión del detalle de que no hace daño j1 a j2
                }
                if(dano2 > 0) {
                    j1.vida -= dano2;
                    // impresión del detalle de ataque de j2 a j1
                } else {
                    // impresión del detalle de que no hace daño j2 a j1
                }
            }

            if(j1.vida > 0) {
                System.out.println("Gana Jugador 1: " + j1.nombre);
                return j1;
            }
            if(j2.vida > 0) {
                System.out.println("Gana Jugador 2: " + j2.nombre);
                return j2;
            }
        }
        return new Random().nextInt(2) == 0 ? j1 : j2;
    }
}

class Jugador {
    public String nombre;
    public int salud;
    public int ataque;
    public int defensa;
    public int vida;

    public Jugador(String nombre, int salud, int ataque, int defensa) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
        this.vida = salud * 10;
    }

    public void revivir() {
        this.vida = salud * 10;
    }

    public Jugador clone() {
        return new Jugador(nombre, salud, ataque, defensa);
    }

    public String toString() {
        return nombre;
    }
}