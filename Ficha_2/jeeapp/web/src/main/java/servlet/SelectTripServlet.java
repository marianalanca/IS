package servlet;

import beans.ICompanyManagers;
import data.ClientUser;
import data.Ticket;
import data.Trip;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectDate")
public class SelectTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /*
        String trip_id = request.getParameter("trip_id");

        List<ClientUser> passengers = manageCM.listPassenger(trip_id);

        request.getSession(true).setAttribute("passengersList", passengers);

        request.getRequestDispatcher("/secured/passengers.jsp").forward(request, response);
        */
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String trip_id = request.getParameter("trip_id");

        List<Ticket> passengers = manageCM.listPassenger(trip_id);

        request.getSession(true).setAttribute("passengersList", passengers);

        request.getRequestDispatcher("/secured/passengers.jsp").forward(request, response);

    }
}

