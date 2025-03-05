package Utils;

public enum TipoError {
    LEXICO ("Lexico");

    private String nombre;
    private TipoError(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {return nombre;}
}