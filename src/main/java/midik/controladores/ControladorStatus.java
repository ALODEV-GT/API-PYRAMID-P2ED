package midik.controladores;

import funcionamiento.StatusF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "status-avltree", urlPatterns = {"/Game/status-avltree"})
public class ControladorStatus extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Peticion get /Game/status-avltree");
        new StatusF(request, response).getStatus();
    }

}
