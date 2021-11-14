package beans;

import data.CompanyManager;
import data.ClientUser;
import data.Ticket;
import data.Trip;
import java.util.List;

public interface ICompanyManagers {
    public CompanyManager findManager(String email);
    public Boolean login(String email, String password);
    public Boolean createTrip(String  departure_date, String departure_point, String destination, String price, String capacity);
    public Boolean deleteTrip(String trip_id);
    public List<Trip> findFutureTrips();
    public List<Trip> findTripsBetDates(String d1, String d2);
    public List<Trip> findTripsByDate(String d);
    public List<ClientUser> findTop5();
    public List<Ticket> listPassenger(String id_trip);
}
