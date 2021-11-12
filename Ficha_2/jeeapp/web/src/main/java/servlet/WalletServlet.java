package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;

@WebServlet("/wallet")
public class WalletServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // passar para float
        int value = Integer.parseInt(request.getParameter("value"));

        String destination = "/error.html";

        if (value>0) {
            manageClients.updateWallet(request.getSession(true).getAttribute("auth").toString(), value);
            destination = "/secured/display.jsp";
            // success destination
        } else{
            // unsuccess destination
        }


        request.getRequestDispatcher(destination).forward(request, response);
    }
}