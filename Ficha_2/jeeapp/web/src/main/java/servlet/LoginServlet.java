package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ICompanyManagers;
import beans.IManageClientUsers;
import data.ClientUser;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClientUsers manageClients;
    @EJB
    private ICompanyManagers manageManagers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String email = request.getParameter("email");
        String key = request.getParameter("key");
        String destination = "/errorPage.jsp";

        String userType = request.getParameter("userType");

        if (email != null && key != null && userType!=null) {

            if (userType.equals("user")) {

                boolean auth = manageClients.login(email, key);

                if (auth) {
                    request.getSession(true).setAttribute("auth", email);
                    destination = "/secured/display.jsp";
                } else {
                    request.getSession(true).removeAttribute("auth");
                }

            } else if (userType.equals("cm")) {
                boolean auth = manageManagers.login(email, key);

                //boolean auth = manageClients.login(email, key);

                if (auth) {
                    request.getSession(true).setAttribute("auth", email);
                    destination = "/secured/displayCM.jsp";
                } else {
                    request.getSession(true).removeAttribute("auth");
                }
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}