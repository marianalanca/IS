package beans;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.*;

import data.ClientUser;
import data.Ticket;
import data.Trip;

@Stateless
public class ManageClientUsers implements IManageClientUsers {
    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

    public void addClientUser(String email, String password, String name, String address, String cc_number) {
        System.out.println("Adding Client " + name + "...");
        ClientUser s = new ClientUser(email, password, name, address, cc_number);
        em.persist(s);
    }

    public ClientUser findClientUser(String email) {
        System.out.println("Searching Client " + email + "...");

        TypedQuery<ClientUser> q = em.createQuery("from ClientUser where email='"+email + "'", ClientUser.class);

        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Trip findTrip(String id) {
        System.out.println("Searching Trip " + id + "...");

        TypedQuery<Trip> q = em.createQuery("from Trip where id='"+id + "'", Trip.class);

        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean registration(String email, String password, String name, String address, String cc_number, String confirmation) {
        // procurar se existe na bd
        if (password.equals(confirmation) && findClientUser(email)==null) {
            addClientUser(email, password, name, address, cc_number);
            return true;
        }
        return false;
    }

    public Boolean login(String email, String password) {
        ClientUser client = findClientUser(email);
        if (client!=null) {
            return Objects.equals(client.getPassword(), password);
        }
        return false;
    }

    public Boolean updateWallet(String email, double value) {
        ClientUser client = findClientUser(email);
        if (client!=null && client.getWallet() + value >= 0) {

            client.updateWallet(value);
            em.persist(client);

            return true;
        }
        return false;
    }

    public Ticket findTicket(Trip trip, ClientUser client) {
        for (Ticket ticket:trip.getTickets()) {
            if (ticket.getClient().equals(client)) {
                return ticket;
            }
        }
        return null;
    }

    public Ticket findTicketSeat(Trip trip, int seat) {
        for (Ticket ticket:trip.getTickets()) {
            if (ticket.getSeat() == seat) {
                return ticket;
            }
        }
        return null;
    }

    public Ticket findTicket(ClientUser client, Trip trip) {
        for (Ticket ticket:client.getTickets()) {
            if (ticket.getTrip().equals(trip)) {
                return ticket;
            }
        }
        return null;
    }

    public Boolean buyTicket(String email, String tripId, int seat) {
        ClientUser client = findClientUser(email);

        tripId = tripId.replace("/", "");

        Trip trip = findTrip(tripId);

        Ticket newTicket = new Ticket(client, trip, seat);

        if (client.getWallet() - trip.getPrice() >= 0 && trip.getTickets().size() < trip.getcapacity() &&
                findTicket(trip, newTicket.getClient())==null && findTicketSeat(trip, seat) == null) {
            //trip.addTicket(newTicket);
            //client.addTicket(newTicket);
            client.updateWallet(- trip.getPrice());

            em.persist(newTicket);
            return true;
        }
        return false;
    }

    public void returnTicket(int id) {


        Ticket ticket = em.find(Ticket.class, id);

        ticket.getClient().updateWallet(ticket.getTrip().getPrice());

        int isSuccessful = em.createQuery("delete from Ticket p where p.id=:id ")
                .setParameter("id", id)
                .executeUpdate();

    }

    public void editInfo(String email, String password, String name, String address, String cc_number) {
        ClientUser client = findClientUser(email);

        if (!Objects.equals(password, "")) {
            client.setPassword(password);
        }
        if (!Objects.equals(name, "")) {
            client.setName(name);
        }
        if (!Objects.equals(address, "")) {
            client.setAddress(address);
        }
        if (!Objects.equals(cc_number, "")) {
            client.setCc_number(cc_number);
        }

        em.persist(client);
    }

    public void deleteUser(String email) {
        ClientUser c = findClientUser(email);

        for (Ticket ticket: c.getTickets()) {
            returnTicket(ticket.getId());
        }

        em.remove(c);
    }

    public List<Trip> searchTrips(String beg, String end) {
        System.out.println("Searching trips...");

        LocalDateTime beg_date = LocalDateTime.parse(beg);
        LocalDateTime end_date = LocalDateTime.parse(end);

        Query q = em.createQuery("from Trip where departure_date between '" + beg_date.toString() + "' and '" +
                end_date.toString() + "'");

        return q.getResultList();
    }

    public List<Ticket> filterTickets(List<Ticket> unfiltered) {

        List<Ticket> filtered = unfiltered;
        filtered.removeIf(ticket -> ticket.getTrip().getDeparture_date().isBefore(LocalDateTime.now()));

        return filtered;
    }

    public List<Trip> filterTrip(List<Trip> unfiltered) {

        List<Trip> filtered = unfiltered;
        filtered.removeIf(trip -> trip.getDeparture_date().isBefore(LocalDateTime.now()));

        return filtered;
    }

}