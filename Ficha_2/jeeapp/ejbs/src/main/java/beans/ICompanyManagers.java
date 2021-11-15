package beans;

import data.*;

import java.util.List;

public interface ICompanyManagers {
    CompanyManager findManager(String email);
    Boolean login(String email, String password);
    Boolean createTrip(String  departure_date, String departure_point, String destination, String price, String capacity);
    Boolean deleteTrip(String trip_id);
    List<TripDTO> findFutureTrips();
    List<TripDTO> findTripsBetDates(String d1, String d2);
    List<TripDTO> findTripsByDate(String d);
    List<ClientDTO> findTop5();
    List<TicketDTO> listPassenger(String id_trip);
}
