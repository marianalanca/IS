package Serdes;

public class ClientDebt {
    private String clientId;
    private Double value;

    public ClientDebt() {
    }

    public ClientDebt(String clientId, Double balance) {
        this.clientId = clientId;
        this.value = balance;
    }

    public String getClientId() {
        return clientId;
    }

    public Double getValue() {
        return value;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setValue(Double balance) {
        this.value = balance;
    }
}
