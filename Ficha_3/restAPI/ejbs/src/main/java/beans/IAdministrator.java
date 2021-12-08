package beans;

public interface IAdministrator {
    //boolean login(/*int managerId*/); //receber o id do manager??
    boolean addManagers();
    boolean addClients(/*int managerID*/);
    boolean addCurrency();
    void listManagers();
    void listClients();
    void listCurrencies();
    double creditPerClient(); //euros
    double paymentsPerClient();
    double balanceOfClient( int clientID);
    double totalCredits();
    double totalPayments();
    double totalBalance();
    void billClientLastMonth();
    void listClientsWithoutPayments();
    void clientHighestDebt();
    void managerHighestRevenue();
}
