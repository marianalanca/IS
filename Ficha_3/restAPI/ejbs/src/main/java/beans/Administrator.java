package beans;

import data.Client;
import data.Currency;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Administrator implements IAdministrator{


    @PersistenceContext(unitName = "database")
    EntityManager em;

    public boolean addManagers(){
        return false;
    }

    public boolean addClients(/*double payments, double credits*/){
        Client c = new Client(/*payments, credits*/);
        em.persist(c);
        return false;
    }

    public boolean addCurrency(){
        return false;
    }

    public void listManagers(){

    }

    public void listClients(){

    }

    public void listCurrencies(){

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
