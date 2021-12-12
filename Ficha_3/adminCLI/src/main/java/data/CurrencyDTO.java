package data;


import java.io.Serializable;

public class CurrencyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String currencyName;
    private double currencyValue;

    public CurrencyDTO() {}

    public CurrencyDTO(String currencyName, double currencyValue) {
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


    @Override
    public String toString() {
        return "ID " + id + ": Currency name - " + currencyName + "; Currency value - " + currencyValue;
    }
}
