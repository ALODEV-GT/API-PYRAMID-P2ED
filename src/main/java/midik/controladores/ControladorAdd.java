package midik.controladores;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import midik.arbol.Carta;
import midik.comunes.Comunes;
import midik.jsonStart.lexerJsonStart;
import midik.jsonStart.parser;

@WebServlet(name = "add", urlPatterns = {"/Game/add"})
public class ControladorAdd extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Peticion post /Game/add");
        String contenido = Comunes.remplazarSimbolos(Comunes.obtenerContenido(request.getReader()));

        StringReader sr = new StringReader(contenido);
        lexerJsonStart lexer = new lexerJsonStart(sr);
        parser par = new parser(lexer);

        try {
            par.parse();
            ArrayList<Carta> nodos = par.getCartas();
            for (Carta nodo : nodos) {
                System.out.println(nodo);
            }
            System.out.println("Analisis correcto");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println("Ocurrieron errores");
        }

    }

}
