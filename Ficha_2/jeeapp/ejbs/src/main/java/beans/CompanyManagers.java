package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import data.Trip;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CompanyManagers implements ICompanyManagers{

    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

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

}
