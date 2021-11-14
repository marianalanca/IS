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


        // search trip by id
        manageClients.returnTicket(request.getParameter("id"));
        request.getRequestDispatcher("/secured/display.jsp").forward(request, response);
    }
}


