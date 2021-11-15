package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import data.*;
import data.ClientUser;
import data.Ticket;;

import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Session mailSession = null;

        if(trip != null){
            q2 = em.createQuery("from Ticket where trip='"+ trip_id + "'", Ticket.class);
            tickets = q2.getResultList();

            String dep_date = trip.getDeparture_date_String();
            String dep_point = trip.getDeparture_point();
            String dest = trip.getDestination();
            double price = trip.getPrice();

            if(tickets != null){

                for(Ticket t: tickets){
                    if( (client = t.getClient()) != null){
                        client.updateWallet(trip.getPrice());
                        em.persist(client);

                        try{

                            MimeMessage m = new MimeMessage(mailSession);
                            Address[] to = new InternetAddress[] {new InternetAddress(client.getEmail()) };

                            m.setRecipients(Message.RecipientType.TO, to);
                            m.setSubject("Trip canceled");
                            m.setSentDate(new java.util.Date());
                            m.setContent("Dear client, \nWe are sorry to inform that your trip was been canceled.\n " +
                                    "Departure Date: " + dep_date + "\nDeparture Point: " + dep_point +
                                    "\nDestination: " + dest + "\nPrice: " + price +
                                    "We refund the value of the ticket and we hope see you back. " +
                                    "\nYour apologies, Bus's Company","text/plain");
                            Transport.send(m);
                            System.out.println("Mail sent!");
                        }
                        catch (javax.mail.MessagingException e)
                        {
                            e.printStackTrace();
                            System.out.println("Error in Sending Mail: "+e);
                        }

                    }
                    //em.remove(t);
                }
            }
            em.remove(trip);



            return true;
        }

        return false;
    }

    public List<TripDTO> findFutureTrips() {

        LocalDateTime today = LocalDateTime.now();

        TypedQuery<Trip> q = em.createQuery("from Trip where departure_date >= :today", Trip.class).setParameter("today", today);

        try {
            return returnTDTO(q.getResultList());
        } catch (Exception e) {
            return null;
        }
    }

    public List<TripDTO> findTripsBetDates(String d1, String d2) {

        try {
            LocalDateTime beg_date = LocalDateTime.parse(d1);
            LocalDateTime end_date = LocalDateTime.parse(d2);

            Query q = em.createQuery("from Trip where departure_date between '" + beg_date.toString() + "' and '" +
                    end_date.toString() + "'");

            return returnTDTO(q.getResultList());

        } catch (Exception e) {
            return null;
        }
    }

    public List<TripDTO> findTripsByDate(String d) {

        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime aux_date = LocalDateTime.parse(d);
            LocalDateTime d1 = aux_date;
            d1 = d1.minusHours(aux_date.getHour());
            d1 = d1.minusMinutes(aux_date.getMinute());
            LocalDateTime d2 = aux_date;
            d2 = d2.minusHours(aux_date.getHour()).plusHours(23);
            d2 = d2.minusMinutes(aux_date.getMinute()).plusMinutes(59);

            Query q = em.createQuery("from Trip where departure_date between '" + d1.toString() + "' and '" +
                    d2.toString() + "'");

            return returnTDTO(q.getResultList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClientDTO> findTop5(){

        try{
            TypedQuery<ClientUser> q = em.createQuery("from ClientUser c order by size(c.tickets), c.name desc", ClientUser.class);
            q.setMaxResults(5);

            List <ClientUser> clients = q.getResultList();
            List<ClientDTO> cDTO = new ArrayList<>();
            for(ClientUser c: clients){
                cDTO.add(new ClientDTO(c.getName()));
            }

            return cDTO;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TicketDTO> listPassenger(String id_trip){
        try{
            TypedQuery<Ticket> q = em.createQuery("from Ticket where trip ='" + id_trip + "'", Ticket.class);
            List<Ticket> tickets = q.getResultList();
            List<TicketDTO> tDTO = new ArrayList<>();

            for(Ticket t: tickets){
                tDTO.add(new TicketDTO(t.getClient().getName()));
            }

            return tDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TripDTO> returnTDTO(List<Trip> trips){

        List<TripDTO> tDTO = new ArrayList<>();
        for(Trip t: trips){
            int id = Integer.parseInt(t.getId());
            tDTO.add(new TripDTO(id, t.getDeparture_date(), t.getDeparture_point(), t.getDestination(), t.getPrice(),  t.getCapacity()));
        }

        return tDTO;
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
