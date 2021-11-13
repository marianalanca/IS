package servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ICompanyManagers;
import data.Trip;

@WebServlet("/deleteTrips")
public class DeleteTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Trip> trips = manageCM.findFutureTrips();
        String destination = "/secured/deleteTrip.jsp";

        request.getSession(true).setAttribute("trips", trips);

        request.getRequestDispatcher(destination).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String destination = "/error.html";

        String trip_id = request.getParameter("trip_id");
        if(manageCM.deleteTrip(trip_id)){
            destination = "";
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }

}