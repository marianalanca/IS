package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String cc_number = request.getParameter("cc");
        String address = request.getParameter("address");
        //String birthday = request.getParameter("birthday");
        String password = request.getParameter("password");
        String conf = request.getParameter("confirmation");

        String destination = "/error.html";

        // não está a dar bem!
        if (name!=null && email!=null && cc_number!=null && address!=null && password!=null){
            if (manageClients.registration(email, password, name, address,cc_number,conf, 5)) {
                destination = "/index.jsp";
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}