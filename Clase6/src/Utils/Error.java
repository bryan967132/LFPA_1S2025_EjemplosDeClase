package Utils;

public class Error {
    public TipoError tipo;
    public String caracter;
    public int linea;
    public int columna;

    public Error(TipoError tipo, String caracter, int linea, int columna) {
        this.tipo = tipo;
        this.caracter = caracter;
        this.linea = linea;
        this.columna = columna;
    }

    public String toString() {
        return "→ Error " + tipo.getNombre() + ", " + caracter + ". " + linea + ":" + columna;
    }
}