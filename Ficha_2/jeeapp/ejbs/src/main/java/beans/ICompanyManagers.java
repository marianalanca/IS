package beans;

import java.util.Date;
import data.ClientUser;
import data.Student;
import data.Trip;

import java.util.List;

public interface ICompanyManagers {
    public Boolean createTrip(String  departure_date, String departure_point, String destination, String price, String capacity);
}
