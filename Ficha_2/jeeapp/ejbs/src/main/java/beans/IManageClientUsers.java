package beans;

import data.ClientUser;
import data.Trip;

public interface IManageClientUsers {
    public void addClientUser(String email, String password, String name, String address, String cc_number);
    public ClientUser findClientUser(String email);
    public Boolean registration(String email, String password, String name, String address, String cc_number, String confirmation);
    public Boolean login(String email, String password);
    public Boolean updateWallet(String email, double value);
    public Boolean buyTicket(String email, Trip busTrip);
    public void editInfo(String email, String password, String name, String address, String cc_number);
    public void deleteUser(String email);
}
