package beans;

import data.ClientUser;
import data.Student;

import java.util.List;

public interface IManageClientUsers {
    public void addClientUser(String email, String password, String name, String address, String cc_number, int age);
    public ClientUser findClientUser(String email);
    public Boolean registration(String email, String password, String name, String address, String cc_number, String confirmation, int age);
    public Boolean login(String email, String password);
}
