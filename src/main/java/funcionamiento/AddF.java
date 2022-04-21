package funcionamiento;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import midik.arbol.Arbol;
import midik.arbol.Carta;
import midik.comunes.Comunes;
import midik.jsonInsert.lexerJsonInsert;
import midik.jsonInsert.parser;
import midik.singleton.SingletonArbol;

public class AddF {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public AddF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
    }

    public void insertarCarta() {
        Arbol arbol = SingletonArbol.getArbol();
        try {
            String jsonAdd = Comunes.remplazarSimbolos(Comunes.obtenerContenido(request.getReader()));
            StringReader sr = new StringReader(jsonAdd);
            lexerJsonInsert lexer = new lexerJsonInsert(sr);
            parser par = new parser(lexer);

            try {
                par.parse();
                Carta carta = par.getCarta();
                if (!par.isErrores()) {
                    arbol.insertar(carta, response);
                    
                    ArrayList<String> cartasS = arbol.preOrden();
                    for (String c : cartasS) {
                        System.out.println(c);
                    }
                    
                    System.out.println("Analisis correcto");
                } else {
                    System.out.println("Existen errores sintacticos en JsonAdd");
                    response.setStatus(400);
                }
            } catch (Exception ex) {
                System.out.println("Ocurrieron errores al analizar JsonAdd");
                response.setStatus(400);
            }

        } catch (IOException ex) {
            System.out.println("Error al leer jsonAdd");
            response.setStatus(400);
        }
    }
}
