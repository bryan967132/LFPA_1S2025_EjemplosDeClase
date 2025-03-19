package Utils;

public enum TipoError {
    LEXICO ("LEXICO") ;

    private String value;
    private TipoError(String value){
        this.value = value;
    }
    public String getValue() {return value;}
}