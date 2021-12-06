package kafka;

public class Object {
    private String user;
    private long value;
    private String currency;

    public Object(String user, long value, String currency) {
        this.user=user;
        this.value=value;
        this.currency=currency;
    }

    public String getUser() {
        return user;
    }

    public long getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
