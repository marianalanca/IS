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

        request.setAttribute("profile", manageClients.findClientUser(request.getSession(true).getAttribute("auth").toString()));

        if (request.getParameter("ok") != null ) {
            manageClients.deleteUser(request.getSession(true).getAttribute("auth").toString());

            request.getSession(true).removeAttribute("auth");

            request.getRequestDispatcher("/index.jsp").forward(request, response);

            // aparecer mensagem a dizer que eliminou e para voltar para o menu


        } else if (request.getParameter("cancel") != null) {
            request.getRequestDispatcher("/secured/definitionsMenu.jsp").forward(request, response);
        }

    }
}