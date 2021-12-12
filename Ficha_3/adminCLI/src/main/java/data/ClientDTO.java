package data;


import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private double credits;
    private double payments;
    private double balance;
    private double billMonth;
    private ManagerDTO manager;

    public ClientDTO(){}

    public ClientDTO(ManagerDTO manager) {
        this.manager = manager;
    }

    public ClientDTO(int id, String name, double credits, double payments, double balance) {
        this.id = id;
        this.credits = credits;
        this.payments = payments;
        this.balance = balance;
    }

    public ClientDTO(int id, String name, double credits, double payments, double balance, ManagerDTO manager) {
        this.id = id;
        this.credits = credits;
        this.payments = payments;
        this.balance = balance;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(double billMonth) {
        this.billMonth = billMonth;
    }

    public double getPayments() {
        return payments;
    }

    public double getCredits() {
        return credits;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ManagerDTO getManager() {
        return manager;
    }

    public void setManager(ManagerDTO manager) {
        this.manager = manager;
    }
    @Override
    public String toString() {
        return "ID " + id + ": Credits - " + credits + "; Payments - " + payments + "; Balance - " + balance;
    }
}
