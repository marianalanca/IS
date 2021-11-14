package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;

@WebServlet("/definitions")
public class ChangeDefinitionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // HERE colocar proteção para o email!
        manageClients.editInfo(request.getSession(true).getAttribute("auth").toString(), request.getParameter("password"),
                request.getParameter("name"), request.getParameter("address"), request.getParameter("cc"));

        request.setAttribute("profile", manageClients.findClientUser(request.getSession(true).getAttribute("auth").toString()));
        request.setAttribute("message", "Operação concluída com sucesso");
        request.getRequestDispatcher("/secured/changeDefinitions.jsp").forward(request, response);

    }
}