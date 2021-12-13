package kafka;

public class ValueCurrency {
    private double value;
    private String currency;
    private double currencyValue;
    private int managerId;

    public ValueCurrency() {
    }

    public ValueCurrency(double value, String currency, double currencyValue, int managerId) {
        this.value = value;
        this.currency = currency;
        this.currencyValue = currencyValue;
        this.managerId = managerId;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
