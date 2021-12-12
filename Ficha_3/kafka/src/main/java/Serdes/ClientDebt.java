package Serdes;

public class ClientDebt {
    private int clientId;
    private Double balance;

    public ClientDebt() {
    }

    public ClientDebt(int clientId, Double balance) {
        this.clientId = clientId;
        this.balance = balance;
    }

    public int getClientId() {
        return clientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
