package beans;

import data.ClientUser;
import data.Ticket;
import data.Trip;
import data.TripDTO;

import java.util.List;

public interface IManageClientUsers {
    public void addClientUser(String email, String password, String name, String address, String cc_number);
    public ClientUser findClientUser(String email);
    public Trip findTrip(String id);
    public Boolean registration(String email, String password, String name, String address, String cc_number,
                                String confirmation);
    public Boolean login(String email, String password);
    public Boolean updateWallet(String email, double value);
    public Boolean buyTicket(String email, String tripId, int seat);
    public void editInfo(String email, String password, String name, String address, String cc_number);
    public void deleteUser(String email);
    public List<TripDTO> searchTrips(String beg, String end);
    public void returnTicket(int id);
    public List<Ticket> filterTickets(String email);
    public List<TripDTO> filterTrip(List<TripDTO> unfiltered);
}
