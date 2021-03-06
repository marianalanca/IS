package servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;
import data.Trip;
import data.TripDTO;

@WebServlet("/searchTrip")
public class SearchTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TripDTO> resSearch = manageClients.filterTrip(manageClients.searchTrips(request.getParameter("beg_date"),
                request.getParameter("end_date")));

        for (TripDTO d: resSearch) {
            System.out.println("HERE: " + d.getId() + " " + d.getDeparture_point());
        }

        double walletValue = manageClients.findClientUser(request.getSession(true).getAttribute("auth").toString()).getWallet();

        request.setAttribute("tripSearch", resSearch);
        request.setAttribute("wallet", walletValue);

        request.getRequestDispatcher("secured/buyTicket.jsp").forward(request, response);
    }
}