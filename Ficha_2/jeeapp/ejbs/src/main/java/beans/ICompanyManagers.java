package beans;

import data.Trip;

import java.util.List;

public interface ICompanyManagers {
    public Boolean createTrip(String  departure_date, String departure_point, String destination, String price, String capacity);
    public Boolean deleteTrip(String trip_id);
    public List<Trip> findFutureTrips();
    List<Trip> findTripsBetDates(String d1, String d2);
    List<Trip> findTripsByDate(String d);
}
