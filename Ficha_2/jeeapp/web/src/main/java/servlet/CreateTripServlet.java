package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ICompanyManagers;

@WebServlet("/createTrip")
public class CreateTripServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String departure_point = request.getParameter("departure_point");
        String destination_point = request.getParameter("destination");
        String price = request.getParameter("price");
        String capacity = request.getParameter("capacity");
        String departure_date = request.getParameter("departure_date");

        //String departure_date = "2021-11-14 17:55";
        String destination = "/ups.html";

        if ( departure_date != null && departure_point !=null && destination_point!=null && price!=null && capacity!=null){
            if(manageCM.createTrip(departure_date, departure_point, destination_point, price, capacity)){
                destination = "/secured/displayCM.jsp";
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

}