package book;

import beans.Administrator;
import data.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/myservice")
@Produces(MediaType.APPLICATION_JSON)
public class MyService {
    @EJB
    private Administrator manageAdmin;


    @GET
    @Path("/addManager")
    public Boolean addManager() {
        return manageAdmin.addManagers(/**/);
    }

    @POST
    @Path("/addClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean addClient(Manager m) {
        return manageAdmin.addClients(m.getId());
    }

    @POST
    @Path("/addCurrency")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean addCurrency(Currency c) {
        return manageAdmin.addCurrency(c.getCurrencyName(), c.getCurrencyValue());
    }

    @GET
    @Path("/listManagres")
    public List<Manager> listManagers() {
        return manageAdmin.listManagers();
    }

    @GET
    @Path("/listClients")
    public List<Client> listClients() {
        return manageAdmin.listClients();
    }

    @GET
    @Path("/listCurrencies")
    public List<Currency> listCurrencies() {
        return manageAdmin.listCurrencies();
    }


}