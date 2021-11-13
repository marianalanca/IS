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

@WebServlet("/deleteTrip")
public class DeleteTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String destination = "/error.html";

        List<Trip> trips = manageCM.findFutureTrips();

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String destination = "/error.html";

        String id = request.getParameter("trip_id");
        if(id!=null){
            if(manageCM.deleteTrip(id)){
                destination = "/secured/displayCM.jsp";
            }
        }
    }
}
