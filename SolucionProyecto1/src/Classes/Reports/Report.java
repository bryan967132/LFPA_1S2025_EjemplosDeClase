package Classes.Reports;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import Classes.Utils.Error;
import Language.Token;

public class Report {
    public static void generate(String fileName, ArrayList<Token> tokens, ArrayList<Error> errors) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Reports/" + fileName + ".html"), "UTF-8"))) {
            bw.write("<!DOCTYPE html>\n");
            bw.write("\t<meta charset=\"UTF-8\">\n");
            bw.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            bw.write("\t<title>Reportes</title>\n");
            bw.write("\t<style>\n");
            bw.write("\t\tbody {\n");
            bw.write("\t\t\tfont-family: Arial, sans-serif;\n");
            bw.write("\t\t\tbackground-color: #f4f4f4;\n");
            bw.write("\t\t\ttext-align: center;\n");
            bw.write("\t\t\tmargin: 20px;\n");
            bw.write("\t\t}\n");
            bw.write("\t\th1, h2 {\n");
            bw.write("\t\t\tcolor: #333;\n");
            bw.write("\t\t}\n");
            bw.write("\t\ttable {\n");
            bw.write("\t\t\twidth: 80%;\n");
            bw.write("\t\t\tmargin: 20px auto;\n");
            bw.write("\t\t\tborder-collapse: collapse;\n");
            bw.write("\t\t\tbackground-color: #fff;\n");
            bw.write("\t\t\tbox-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n");
            bw.write("\t\t}\n");
            bw.write("\t\tth, td {\n");
            bw.write("\t\t\tborder: 1px solid #ddd;\n");
            bw.write("\t\t\tpadding: 10px;\n");
            bw.write("\t\t\ttext-align: left;\n");
            bw.write("\t\t}\n");
            bw.write("\t\tth {\n");
            bw.write("\t\t\tbackground-color: #007BFF;\n");
            bw.write("\t\t\tcolor: white;\n");
            bw.write("\t\t}\n");
            bw.write("\t\ttr:nth-child(even) {\n");
            bw.write("\t\t\tbackground-color: #f2f2f2;\n");
            bw.write("\t\t}\n");
            bw.write("\t</style>\n");
            bw.write("\t<body>\n");
            bw.write("\t\t<h1>Reportes</h1>\n");
            bw.write("\t\t<h2>Reportes de Tokens</h2>\n");
            bw.write("\t\t<table>\n");
            bw.write("\t\t\t<tr>\n\t\t\t\t<th>Tipo</th>\n\t\t\t\t<th>Lexema</th>\n\t\t\t\t<th>Linea</th>\n\t\t\t\t<th>Columna</th>\n\t\t\t</tr>\n");

            for(Token token : tokens) {
                bw.write("\t\t\t<tr>\n\t\t\t\t<td>" + token.type.getName() + "</td>\n\t\t\t\t<td>" + token.lexeme + "</td>\n\t\t\t\t<td>" + token.line + "</td>\n\t\t\t\t<td>" + token.column + "</td>\n\t\t\t</tr>\n");
            }

            bw.write("\t\t</table>\n");
            bw.write("\t\t<h2>Reportes de Errores</h2>\n");
            bw.write("\t\t<table>\n");
            bw.write("\t\t\t<tr>\n\t\t\t\t<th>Descripcion</th>\n\t\t\t\t<th>Linea</th>\n\t\t\t\t<th>Columna</th>\n\t\t\t</tr>\n");

            for(Error error : errors) {
                bw.write("\t\t\t<tr>\n\t\t\t\t<td>" + error.description + "</td>\n\t\t\t\t<td>" + error.line + "</td>\n\t\t\t\t<td>" + error.column + "</td>\n\t\t\t</tr>\n");
            }

            bw.write("\t\t</table>\n");
            bw.write("\t</body>\n");
            bw.write("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
