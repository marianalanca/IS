package data;


import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private double credit;
    private double payment;
    private double balance;
    private ManagerDTO manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ManagerDTO getAdmin() {
        return manager;
    }

    public void setAdmin(ManagerDTO admin) {
        this.manager = admin;
    }

    public ClientDTO(int id, String name, double credit, double payment, double balance, ManagerDTO manager) {
        this.id = id;
        this.credit = credit;
        this.payment = payment;
        this.balance = balance;
        this.manager = manager;
    }

    public ClientDTO(ManagerDTO manager) {
        this.manager = manager;
    }

    public ClientDTO(){};

    @Override
    public String toString() {
        return "ID: " + id + " Credit: " + credit + " Payment: " + payment + " Balance: " + balance;
    }
}
