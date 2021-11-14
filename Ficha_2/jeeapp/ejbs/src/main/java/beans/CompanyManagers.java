package beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import data.ClientUser;
import data.Ticket;
import data.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CompanyManagers implements ICompanyManagers{

    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

    public Boolean createTrip(String departure_date, String departure_point, String destination, String price, String capacity) {

        try {
            LocalDateTime aux_date = LocalDateTime.parse(departure_date);

            double p = Double.parseDouble(price);
            int c = Integer.parseInt(capacity);

            Trip t =  new Trip(aux_date, departure_point, destination, p, c);
            em.persist(t);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Boolean deleteTrip(String trip_id){

        TypedQuery<Trip> q = em.createQuery("from Trip where id='"+ trip_id + "'", Trip.class);
        Trip trip = q.getSingleResult();
        TypedQuery<Ticket> q2;
        List<Ticket> tickets;
        ClientUser client;

        if(trip != null){
            q2 = em.createQuery("from Ticket where trip='"+ trip_id + "'", Ticket.class);
            tickets = q2.getResultList();

            if(tickets != null){

                for(Ticket t: tickets){
                    if( (client = t.getClient()) != null){
                        client.updateWallet(trip.getPrice());
                        //em.persist(client); //?
                    }
                    em.remove(t); //?
                }
                //em.remove(tickets);
            }
            em.remove(trip);
            return true;
        }

        return false;
    }

    public List<Trip> findFutureTrips() {

        LocalDateTime today = LocalDateTime.now();

        TypedQuery<Trip> q = em.createQuery("from Trip where departure_date >= :today", Trip.class).setParameter("today", today);

        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Trip> findTripsBetDates(String d1, String d2) {

        try {
            LocalDateTime beg_date = LocalDateTime.parse(d1);
            LocalDateTime end_date = LocalDateTime.parse(d2);

            Query q = em.createQuery("from Trip where departure_date between '" + beg_date.toString() + "' and '" +
                    end_date.toString() + "'");

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Trip> findTripsByDate(String d) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime aux_date = LocalDateTime.parse(d);
            LocalDateTime d1 = aux_date;
            d1 = d1.minusHours(aux_date.getHour());
            d1 = d1.minusMinutes(aux_date.getMinute());
            LocalDateTime d2 = aux_date;
            d2 = d2.minusHours(aux_date.getHour()).plusHours(23);
            d2 = d2.minusMinutes(aux_date.getMinute()).plusMinutes(59);

            Query q = em.createQuery("from Trip where departure_date between '" + d1.toString() + "' and '" +
                    d2.toString() + "'");

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClientUser> findTop5(){

        try{
            TypedQuery<ClientUser> q = em.createQuery("from ClientUser c order by size(c.tickets), c.name desc", ClientUser.class);
            q.setMaxResults(5);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ticket> listPassenger(String id_trip){
        try{
            TypedQuery<Ticket> q = em.createQuery("from Ticket where trip ='" + id_trip + "'", Ticket.class);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
