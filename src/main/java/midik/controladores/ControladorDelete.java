package midik.controladores;

import funcionamiento.DeleteF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "delete", urlPatterns = {"/Game/delete"})
public class ControladorDelete extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Peticion delete /Game/delete");
        new DeleteF(request, response).eliminarCartas();
    }

}
