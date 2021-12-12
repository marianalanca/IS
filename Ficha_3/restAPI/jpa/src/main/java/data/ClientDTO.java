package data;

import java.io.Serializable;
import javax.persistence.*;

public class ClientDTO{
    private int id;
    private double payments;
    private double credits;
    private double balance;
    private double billMonth;
    private Manager manager;

    public ClientDTO() {
        this.payments=0.0;
        this.credits=0.0;
        this.balance=0.0;
        this.billMonth=0.0;
    }

    public ClientDTO(int id, double credits) {
        this.id = id;
        this.credits = credits;
    }

    public ClientDTO(int id, double payments, double credits, double balance) {
        this.id = id;
        this.payments = payments;
        this.credits = credits;
        this.balance = balance;
    }

    public ClientDTO(double payments, double credits, double balance) {
        this.payments = payments;
        this.credits = credits;
        this.balance = balance;
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

    public int getId() {
        return id;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}


