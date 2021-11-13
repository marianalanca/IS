package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String destination = "/error.html";

        if (request.getParameter("wallet") != null ) {
            destination = "/secured/chargeWallet.jsp";
        } else if (request.getParameter("buy") != null) {
            destination = "/secured/searchTrips.jsp";
        } else if (request.getParameter("refund") != null) {
            destination = "/secured/searchTrips.jsp";
        } else if (request.getParameter("profile") != null) {
            destination = "/secured/definitionsMenu.jsp";
        } else if (request.getParameter("CM") != null) {
            destination = "/secured/displayCM.jsp";
        } else if (request.getParameter("edit") != null) {
            destination = "/secured/changeDefinitions.jsp";
        } else if (request.getParameter("delete") != null) {
            destination = "/secured/deletionConfirmation.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }
}
