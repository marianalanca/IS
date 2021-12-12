package book;

import beans.Administrator;
import data.*;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("/listManagers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listManagers() {
        return Response.ok().entity(manageAdmin.listManagers()).build();
    }

    @GET
    @Path("/listClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listClients() {
        return Response.ok().entity(manageAdmin.listClients()).build();
    }

    @GET
    @Path("/listCurrencies")
    public Response listCurrencies() {
        return Response.ok().entity(manageAdmin.listCurrencies()).build();
    }


}