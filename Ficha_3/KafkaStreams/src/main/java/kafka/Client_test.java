package kafka;

public class Client_test {
    private String id;
    private double payments;
    private double credits;

    public Client_test(String id, double payments, double credits) {
        this.id = id;
        this.payments = payments;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public double getPayments() {
        return payments;
    }

    public double getCredits() {
        return credits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
