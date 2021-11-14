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
import data.ClientUser;
import data.Trip;

@WebServlet("/top5")
public class Top5Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ICompanyManagers manageCM;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ClientUser> top5 = manageCM.findTop5();

        request.getSession(true).setAttribute("top5", top5);

        request.getRequestDispatcher("/secured/top5.jsp").forward(request, response);
    }

}
