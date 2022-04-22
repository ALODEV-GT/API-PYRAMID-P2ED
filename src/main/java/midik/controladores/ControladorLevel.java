package midik.controladores;

import funcionamiento.LevelF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "get-level", urlPatterns = {"/Game/get-level"})
public class ControladorLevel extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Peticion get /Game/get-level");
        new LevelF(request, response).getNivel();
    }

}
