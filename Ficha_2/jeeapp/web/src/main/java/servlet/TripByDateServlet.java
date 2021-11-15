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

@WebServlet("/tripByDate")
public class TripByDateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/secured/searchByDate.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String destination;
        String date1 = request.getParameter("date1");

        List<TripDTO> trips = manageCM.findTripsByDate(date1);

        request.getSession(true).setAttribute("trips", trips);
        destination = "/secured/trips.jsp";

        request.getRequestDispatcher(destination).forward(request, response);
    }

}
