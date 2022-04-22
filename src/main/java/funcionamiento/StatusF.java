package funcionamiento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import midik.arbol.Arbol;
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

    }
}
