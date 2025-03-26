package EjemploAFD;

public class App {
    public static String entrada = "110101" + '\0';
    public static char char_actual;
    public static int pos_char = 0;

    public static void main(String[] args) {
        System.out.println(entrada + (q0() ? " Es aceptada": " No es aceptada"));
    }

    public static boolean q0() {
        if((char_actual = entrada.charAt(pos_char)) != '\0') {
            if(char_actual == '0') {
                pos_char ++;
                return q1();
            }
            if(char_actual == '1') {
                pos_char ++;
                return q0();
            }
        }
        return false;
    }

    public static boolean q1() {
        if((char_actual = entrada.charAt(pos_char)) != '\0') {
            if(char_actual == '0') {
                pos_char ++;
                return q0();
            }
            if(char_actual == '1') {
                pos_char ++;
                return qf();
            }
        }
        return false;
    }

    public static boolean qf() {
        if((char_actual = entrada.charAt(pos_char)) != '\0') {
            if(char_actual == '0') {
                pos_char ++;
                return q1();
            }
            if(char_actual == '1') {
                pos_char ++;
                return q0();
            }
        }
        return true;
    }
}