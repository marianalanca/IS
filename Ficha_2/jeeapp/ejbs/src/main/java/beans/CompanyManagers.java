package beans;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import data.ClientUser;
import data.CompanyManager;
import data.Ticket;
import data.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CompanyManagers implements ICompanyManagers{

    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

    public Boolean login(String email, String password) {
        CompanyManager manager = findManager(email);
        if (manager!=null) {
            System.out.println("HERE: " +manager.getPassword() + " " + password);
            return Objects.equals(manager.getPassword(), password);
        }
        return false;
    }

    public Boolean createTrip(String departure_date, String departure_point, String destination, String price, String capacity) {
        //System.out.println(departure_point + " " + destination + " " + price + " ");

        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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

        TypedQuery<Trip> t = em.createQuery("from Trip where id='"+ trip_id + "'", Trip.class);

        if(t != null){
            em.remove(t);
            return true;
        }

        return false;
    }



    public List<Trip> findFutureTrips() {

        //Logger logger = LoggerFactory.getLogger(CompanyManagers.class);

        LocalDateTime today = LocalDateTime.now();

        //logger.info("Date: " + today);

        TypedQuery<Trip> q = em.createQuery("from Trip where departure_date >= :today", Trip.class).setParameter("today", today);

        try {
            return q.getResultList();
        } catch (Exception e) {
            //logger.trace("Error" + e);
            return null;
        }
    }

    public List<Trip> findTripsBetDates(String d1, String d2) {

        //Logger logger = LoggerFactory.getLogger(CompanyManagers.class);
        //logger.info("bet " + d1 + " and " + d2 + "\n");

        TypedQuery<Trip> q = em.createQuery("from Trip where departure_date between'"+ d1 + " and "+ d2 + "'", Trip.class);

        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Trip> findTripsByDate(String d) {

        //Logger logger = LoggerFactory.getLogger(CompanyManagers.class);
        //logger.info("bet " + d1 + " and " + d2 + "\n");

        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime aux_date = LocalDateTime.parse(d);
            LocalDateTime d1 = aux_date;
            d1 = d1.minusHours(aux_date.getHour());
            d1 = d1.minusMinutes(aux_date.getMinute());
            LocalDateTime d2 = aux_date;
            d2 = d2.minusHours(aux_date.getHour()).plusHours(23);
            d2 = d2.minusMinutes(aux_date.getMinute()).plusMinutes(59);

            TypedQuery<Trip> q = em.createQuery("from Trip where departure_date between'"+ d1.toString() + " and "+ d2.toString() + "'", Trip.class);

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CompanyManager findManager(String email) {
        System.out.println("Searching Manager " + email + "...");

        TypedQuery<CompanyManager> q = em.createQuery("from CompanyManager where email='"+email + "'", CompanyManager.class);

        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


}
