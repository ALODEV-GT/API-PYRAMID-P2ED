package midik.controladores;

import funcionamiento.AddF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "add", urlPatterns = {"/Game/add"})
public class ControladorAdd extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Peticion post /Game/add");
        new AddF(request, response).insertarCarta();
    }

}
