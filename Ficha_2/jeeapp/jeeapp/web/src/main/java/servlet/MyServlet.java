package servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Student;

@WebServlet("/webaccess")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

}