package data;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double payments;
    private double credits;
    private double balance;
    private double billMonth;
    private Long paymentsTwoMonths;
    private int highestDebtId;
    private int highestRevId;

    @ManyToOne
    private Manager manager;

    public Client() {
        this.payments=0.0;
        this.credits=0.0;
        this.balance=0.0;
        this.billMonth=0.0;
        this.paymentsTwoMonths = 0L;
    }

    public Client(double payments, double credits, double balance) {
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

    public Long isPaymentsTwoMonths() {
        return paymentsTwoMonths;
    }

    public void setBillMonth(double billMonth) {
        this.billMonth = billMonth;
    }

    public void setPaymentsTwoMonths(Long paymentsTwoMonths) {
        this.paymentsTwoMonths = paymentsTwoMonths;
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

    public Long getPaymentsTwoMonths() {
        return paymentsTwoMonths;
    }

    public int getHighestDebtId() {
        return highestDebtId;
    }

    public int getHighestRevId() {
        return highestRevId;
    }

    public void setHighestDebtId(int highestDebtId) {
        this.highestDebtId = highestDebtId;
    }

    public void setHighestRevId(int highestRevId) {
        this.highestRevId = highestRevId;
    }
}

