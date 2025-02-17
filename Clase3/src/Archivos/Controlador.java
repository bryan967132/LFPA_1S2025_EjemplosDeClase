package Archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Controlador {
    public Controlador() {}

    public String leerArchivo(String ruta) {
        String contenido = ""; // Va a almacenar el contenido

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while((linea = lector.readLine()) != null) { // guardar el valor de la línea leida en la variable y evaluar que haya leido algo (!= null)
                contenido += linea.replace(" ", "");

                if(lector.ready()) {
                    contenido += "\n";
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return contenido; // retornará el contenido del archivo leido
    }
}