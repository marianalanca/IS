package servlet;

import beans.ICompanyManagers;
import data.Trip;

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

        List<Trip> trips;
        String destination;
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");

        if(date2 != null){
            trips = manageCM.findTripsBetDates(date1, date2);
        }
        else{
            trips = manageCM.findTripsByDate(date1);
        }

        if(trips != null){
            request.setAttribute("trips", trips);
            destination = "/ups.html";
            //destination = "/secured/deleteTrip.jsp";
        }
        else{
            destination = "/teste.html";
        }


        request.getRequestDispatcher(destination).forward(request, response);
    }

}
