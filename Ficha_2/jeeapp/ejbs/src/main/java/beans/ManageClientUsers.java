package beans;

import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.*;

import data.ClientUser;
import data.Trip;

@Stateless
public class ManageClientUsers implements IManageClientUsers {
    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

    public void addClientUser(String email, String password, String name, String address, String cc_number) {
        System.out.println("Adding Client " + name + "...");
        ClientUser s = new ClientUser(email, password, name, address, cc_number);
        em.persist(s);
    }

    public ClientUser findClientUser(String email) {
        System.out.println("Searching Client " + email + "...");

        TypedQuery<ClientUser> q = em.createQuery("from ClientUser where email='"+email + "'", ClientUser.class);

        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean registration(String email, String password, String name, String address, String cc_number, String confirmation) {
        // procurar se existe na bd
        if (password.equals(confirmation) && findClientUser(email)==null) {
            addClientUser(email, password, name, address, cc_number);
            return true;
        }
        return false;
    }

    public Boolean login(String email, String password) {
        ClientUser client = findClientUser(email);
        if (client!=null) {
            return Objects.equals(client.getPassword(), password);
        }
        return false;
    }

    public Boolean updateWallet(String email, double value) {
        ClientUser client = findClientUser(email);
        if (client!=null && client.getWallet() + value >= 0) {

            client.updateWallet(value);
            em.persist(client);

            return true;
        }
        return false;
    }

    public Boolean buyTicket(String email, Trip busTrip) {
        ClientUser client = findClientUser(email);
        double price = busTrip.getPrice();
        if (client!=null && client.getWallet() - price >= 0 /*&& busTrip.addPassengers(client)*/) {

            client.updateWallet(- price);

            em.persist(client);
            em.persist(busTrip);

            return true;
        }
        return false;
    }

    // Test
    public void editInfo(String email, String password, String name, String address, String cc_number) {
        ClientUser client = findClientUser(email);

        if (!Objects.equals(password, "")) {
            client.setPassword(password);
        }
        if (!Objects.equals(name, "")) {
            client.setName(name);
        }
        if (!Objects.equals(address, "")) {
            client.setAddress(address);
        }
        if (!Objects.equals(cc_number, "")) {
            client.setCc_number(cc_number);
        }

        em.persist(client);
    }

    public void deleteUser(String email) {
        ClientUser c = findClientUser(email);
        // eliminar
        // devolver todos os lugares em que esteja



        em.remove(c);
    }

}