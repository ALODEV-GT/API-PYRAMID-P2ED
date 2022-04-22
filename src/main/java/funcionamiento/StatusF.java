package funcionamiento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import midik.arbol.Arbol;
import midik.generadorImagen.Generador;
import midik.singleton.SingletonArbol;

public class StatusF {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public StatusF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
    }

    public void getStatus() {
        Arbol arbol = SingletonArbol.getArbol();
        Generador generador = new Generador();
        generador.graficar("arbol.jpg", arbol.getRaiz());
        try {
            crearJson();
        } catch (IOException ex) {
            response.setStatus(400);
        }
    }

    private void crearJson() throws IOException {
        String json = "{\n";
        String serverName = request.getServerName();
        String path = serverName + "/imagenes/arbol.jpg";
        json += "\t\"path\":" + path;
        response.getWriter().append(json + "\n}");
    }
}
