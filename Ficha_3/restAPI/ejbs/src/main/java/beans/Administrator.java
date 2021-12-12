package beans;

import data.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class Administrator /*implements IAdministrator*/{

    @PersistenceContext(unitName = "database")
    EntityManager em;

    public boolean addManagers(){
        Manager m = new Manager();
        em.persist(m);
        return true;
    }

    public boolean addClients(int id_manager/*double payments, double credits*/){

        TypedQuery<Manager> q = em.createQuery("from Manager where id='" + id_manager + "'", Manager.class);

        try {
            Manager m = q.getSingleResult();

            Client c = new Client(/*payments, credits*/);
            c.setManager(m);
            em.persist(c);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean addCurrency(String name, double value){
        Currency c = new Currency(name, value);
        em.persist(c);
        return true;
    }

    public List<ManagerDTO> listManagers(){
        TypedQuery<Manager> q = em.createQuery("from Manager", Manager.class);

        try {
            List <ManagerDTO> list = new ArrayList<>();
            for(Manager m: q.getResultList()){
                list.add(new ManagerDTO(m.getId()));
            }

            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClientDTO> listClients(){
        TypedQuery<Client> q = em.createQuery("from Client", Client.class);

        try {
            List <ClientDTO> list = new ArrayList<>();
            for(Client c: q.getResultList()){
                list.add(new ClientDTO(c.getId(), c.getPayments(), c.getCredits(), c.getBalance()));
            }

            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Currency> listCurrencies(){
        TypedQuery<Currency> q = em.createQuery("from Currency", Currency.class);

        try {
            return q.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double creditPerClient(){
        return 0.0;
    }

    public double paymentsPerClient(){
        return 0.0;
    }

    public double balanceOfClient( int clientID){
        return 0.0;
    }

    public double totalCredits(){
        return 0.0;
    }

    public double totalPayments(){
        return 0.0;
    }

    public double totalBalance(){
        return 0.0;
    }

    public void billClientLastMonth(){

    }

    //last 2 months
    public void listClientsWithoutPayments(){

    }

    public void clientHighestDebt(){

    }

    public void managerHighestRevenue(){

    }

}
