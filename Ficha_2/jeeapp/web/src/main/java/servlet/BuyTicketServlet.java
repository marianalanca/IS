package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;

@WebServlet("/purchase")
public class BuyTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int seat = Integer.parseInt(request.getParameter("seat"));

        String destination = "/errorPage.jsp";

        // search trip by id
        if (manageClients.buyTicket(request.getSession(true).getAttribute("auth").toString(), request.getParameter("id"),
                seat)) {
            destination = "/secured/successfulOperation.jsp";
        } else {

            destination = "secured/unsuccessfulOperation.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }
}




