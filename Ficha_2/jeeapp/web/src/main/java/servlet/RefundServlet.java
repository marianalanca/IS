package servlet;

import beans.IManageClientUsers;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/refund")
public class RefundServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //System.out.println("TICKETS: " +manageClients.findClientUser(request.getSession(true).getAttribute("auth").toString()).getTickets());
        // search trip by id
        manageClients.returnTicket(Integer.parseInt(request.getParameter("id").replace("/", "")));
        String destination = "/secured/successfulOperation.jsp";
        request.getRequestDispatcher(destination).forward(request, response);
    }
}


