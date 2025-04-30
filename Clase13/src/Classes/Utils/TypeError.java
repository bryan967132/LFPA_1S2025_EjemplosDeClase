package Classes.Utils;

public enum TypeError {
    LEXICAL  ("LEXICO"    ) ,
    SYNTAX   ("SINTACTICO") ,
    SEMANTIC ("SEMANTICO" ) ;

    private String value;
    private TypeError(String value){
        this.value = value;
    }
    public String getValue() {return value;}
}