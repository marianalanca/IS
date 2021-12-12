package Serdes;

public class ValueAggregate {
    private int clientId;
    private Double minBal;

    public ValueAggregate() {
    }

    public int getClientId() {
        return clientId;
    }

    public Double getMinBal() {
        return minBal;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setMinBal(Double minBal) {
        this.minBal = minBal;
    }
}
