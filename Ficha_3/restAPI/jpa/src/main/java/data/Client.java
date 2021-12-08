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

    @ManyToOne
    private Manager manager;

    public Client() {
        this.payments=0.0;
        this.credits=0.0;
    }

    public Client(double payments, double credits) {
        this.payments = payments;
        this.credits = credits;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}

