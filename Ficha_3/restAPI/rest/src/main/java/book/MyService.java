package book;

import beans.Administrator;
//import beans.IAdministrator;

import java.sql.Time;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/myservice")
@Produces(MediaType.APPLICATION_JSON)
public class MyService {
    @EJB
    private Administrator manageAdmin;


    @GET
    @Path("/addManager")
    public String addManager() {
        String name = "name_" + new Time(Calendar.getInstance().getTimeInMillis());
        manageAdmin.addManagers(/**/);
        return name;
    }

    @GET
    @Path("/addClient")
    public String addClient() {
        String name = "name_" + new Time(Calendar.getInstance().getTimeInMillis());
        manageAdmin.addClients(/**/);
        return name;
    }

    /*@GET
    @Path("/list")
    public List<Student> method3() {
        System.out.println("M3 executing....");
        List<Student> list = manageStudents.listStudents();

        return list;
    }*/
}