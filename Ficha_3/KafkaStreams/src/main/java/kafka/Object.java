package kafka;

public class Object {
    private double value;
    private String currency;
    private double currencyValue;

    public Object(double value, String currency, double currencyValue) {
        this.value = value;
        this.currency = currency;
        this.currencyValue = currencyValue;
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
}
