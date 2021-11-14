package servlet;

import beans.IManageClientUsers;
import data.Ticket;
import data.Trip;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String destination = "/errorPage.jsp";

        if (request.getParameter("wallet") != null ) {
            double walletValue = manageClients.findClientUser(request.getSession(true).getAttribute("auth")
                    .toString()).getWallet();
            request.setAttribute("wallet", walletValue);

            String message = "";
            request.setAttribute("message", message);
            destination = "/secured/chargeWallet.jsp";
        } else if (request.getParameter("buy") != null) {
            destination = "/secured/searchTrips.jsp";
        } else if (request.getParameter("refund") != null) {
            // ir buscar as trips da pessoa
            request.setAttribute("tickets", manageClients.filterTickets(manageClients.findClientUser(request.getSession(true)
                    .getAttribute("auth").toString()).getTickets()));
            destination = "/secured/tripRefund.jsp";
        } else if (request.getParameter("profile") != null || request.getParameter("menuDefinitions") != null) {
            request.setAttribute("profile", manageClients.findClientUser(request.getSession(true).getAttribute("auth")
                    .toString()));
            destination = "/secured/definitionsMenu.jsp";
        } else if (request.getParameter("CM") != null) {
            destination = "/secured/displayCM.jsp";
        } else if (request.getParameter("edit") != null) {
            String message = "";
            request.setAttribute("message", message);
            destination = "/secured/changeDefinitions.jsp";
        } else if (request.getParameter("delete") != null) {
            destination = "/secured/deletionConfirmation.jsp";
        } else if (request.getParameter("menu") != null) {
            destination = "/secured/display.jsp";
        } else if (request.getParameter("backSearchTrip") != null) {
            destination = "/secured/searchTrips.jsp";
        } else if (request.getParameter("home") != null) {
            destination = "/index.jsp";
        } else if (request.getParameter("register") != null) {
            String message = "";
            request.setAttribute("message", message);
            destination = "/registration.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }
}
