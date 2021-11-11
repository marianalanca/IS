package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        if (request.getParameter("ok") != null ) {
            manageClients.deleteUser(request.getParameter("auth"));
            request.getSession(true).removeAttribute("auth");

            // aparecer mensagem a dizer que eliminou e para voltar para o menu


        } else if (request.getParameter("cancel") != null) {
            request.getRequestDispatcher("/secure/definitions.jsp").forward(request, response);
        }

    }
}