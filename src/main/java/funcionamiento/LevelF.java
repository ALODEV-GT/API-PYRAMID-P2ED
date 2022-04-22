package funcionamiento;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import midik.arbol.Arbol;
import midik.singleton.SingletonArbol;

public class LevelF {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public LevelF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
    }

    public void getNivel() {
        Arbol arbol = SingletonArbol.getArbol();
        try {
            int nivel = Integer.valueOf(this.request.getParameter("level")) - 1;
            ArrayList<String> cartas = arbol.getNivel(nivel);
            crearJson(cartas);
        } catch (IOException | NumberFormatException e) {
            response.setStatus(400);
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
