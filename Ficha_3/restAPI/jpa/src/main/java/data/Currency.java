package data;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String currencyName;
    private double currencyValue;

    public Currency() {}

    public Currency(String currencyName, double currencyValue) {
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public int getId() {
        return id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }
}

