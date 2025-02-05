import Objetos.Persona;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println();

        Persona p1 = new Persona();
        Persona p2 = new Persona("Mario");
        Persona p3 = new Persona("Josue", 20);
        Persona p4 = new Persona("Pedro", 21, "Masculino");
        Persona p5 = new Persona("Brayan", 22, "Masculino", "brayan@gmail.com");

        p1.saludar();
        p2.saludar();

        // p3.saludar(p2.nombre);
        p2.nombre = "Rene";
        p2.email = "rene@gmail.com";
        // p3.saludar(p2.nombre);

        String v1 = p1.obtenerCadena();
        System.out.println(v1);
        System.out.println(p2.obtenerCadena());
        System.out.println(p3.obtenerCadena());
        System.out.println(p4.obtenerCadena());
        System.out.println(p5.obtenerCadena());
    }
}