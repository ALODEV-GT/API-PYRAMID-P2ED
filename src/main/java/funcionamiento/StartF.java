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
import midik.jsonStart.lexerJsonStart;
import midik.jsonStart.parser;
import midik.singleton.SingletonArbol;

public class StartF {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public StartF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
    }

    public void recibirCartasIniciales() {
        Arbol arbol = SingletonArbol.getArbol();
        try {
            String jsonStart = Comunes.remplazarSimbolos(Comunes.obtenerContenido(request.getReader()));
            StringReader sr = new StringReader(jsonStart);
            lexerJsonStart lexer = new lexerJsonStart(sr);
            parser par = new parser(lexer);

            try {
                par.parse();
                ArrayList<Carta> nodos = par.getCartas();
                if (!par.isErrores()) {
                    for (Carta carta : nodos) {
                        arbol.insertar(carta, response);
                    }
                    arbol.preOrden(arbol.getRaiz());
                    System.out.println("Analisis correcto");
                }
            } catch (Exception ex) {
                System.out.println("Ocurrieron errores al analizar JsonStart");
                response.setStatus(400);
            }

        } catch (IOException ex) {
            System.out.println("Error al leer jsonStart");
            response.setStatus(400);
        }

    }
}
