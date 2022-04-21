package funcionamiento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import midik.arbol.Arbol;
import midik.singleton.SingletonArbol;

public class GetTreeF {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public GetTreeF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
    }

    public void getOrden() {
        String orden = this.request.getParameter("transversal");
        Arbol arbol = SingletonArbol.getArbol();
        try {
            switch (orden) {
                case "preOrder":
                    this.crearJson(arbol.preOrden());
                    break;
                case "inOrder":
                    this.crearJson(arbol.inOrden());
                    break;
                case "postOrder":
                    this.crearJson(arbol.postOrden());
                    break;
                default:
                    response.setStatus(400);
            }
        } catch (IOException ex) {
            response.setStatus(400);
            ex.printStackTrace(System.out);
        }
    }

    private void crearJson(ArrayList<String> cartas) throws IOException {
        String json = "{";
        for (int i = 0; i < cartas.size(); i++) {
            json += "\n\t\"" + i + "\": \"" + cartas.get(i) + "\"";
            if (i < cartas.size() - 1) {
                json += ",";
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(json + "\n}");
    }

}
