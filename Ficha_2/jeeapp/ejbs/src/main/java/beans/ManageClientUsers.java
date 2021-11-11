package beans;

import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.*;

import data.ClientUser;

@Stateless
public class ManageClientUsers implements IManageClientUsers {
    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

    public void addClientUser(String email, String password, String name, String address, String cc_number, int age) {
        System.out.println("Adding Client " + name + "...");
        ClientUser s = new ClientUser(email, password, name, address, cc_number, age);
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

    public Boolean registration(String email, String password, String name, String address, String cc_number, String confirmation, int age) {
        // procurar se existe na bd
        if (password.equals(confirmation) && findClientUser(email)==null) {
            System.out.println("Adding Client " + name + "...");
            ClientUser s = new ClientUser(email, password, name, address, cc_number, age);
            em.persist(s);
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
}