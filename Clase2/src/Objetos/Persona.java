package Objetos;

public class Persona {
    // Atributos
    public String nombre;
    public int edad;
    public String genero;
    public String email;

    // Constructor
    public Persona() {}

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona(String nombre, int edad, String genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public Persona(String nombre, int edad, String genero, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.email = email;
    }

    public void saludar() {
        System.out.println("Hola!!!");
    }

    public void saludar(String nombre) {
        System.out.println("Hola " + nombre + "!!!");
    }

    public String obtenerCadena() {
        String cadena = "Nombre: " + nombre + ", Edad: " + edad + ", Genero: " + genero + ", e-mail: " + email;
        return cadena;
    }
}