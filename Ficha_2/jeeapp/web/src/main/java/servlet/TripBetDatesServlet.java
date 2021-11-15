package servlet;

import beans.ICompanyManagers;
import data.Trip;
import data.TripDTO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/tripBetDate")
public class TripBetDatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/secured/searchBetDates.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");

        List<TripDTO> trips = manageCM.findTripsBetDates(date1, date2);

        request.getSession(true).setAttribute("trips", trips);

        request.getRequestDispatcher("/secured/trips.jsp").forward(request, response);
    }

}
