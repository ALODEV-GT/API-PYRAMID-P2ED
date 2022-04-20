package midik.controladores;

import funcionamiento.StartF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "start", urlPatterns = {"/Game/start"})
public class ControladorStart extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Peticion post /Game/start");
        new StartF(request, response).recibirCartasIniciales();
    }

}
