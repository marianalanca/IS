package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClientUsers;
import data.ClientUser;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String email = request.getParameter("email");
        String key = request.getParameter("key");
        String destination = "/error.html";

        if (email != null && key != null) {
            boolean auth = manageClients.login(email, key);

            if (auth) {
                request.getSession(true).setAttribute("auth", email);
                destination = "/secured/display.jsp";
            } else {
            request.getSession(true).removeAttribute("auth");
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}