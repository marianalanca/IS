package data;

import java.util.List;

public class ManagerDTO {
    private static final long serialVersionUID = 1L;
    private int id;
    private List<Client> clientList;

    public ManagerDTO() {
    }

    public ManagerDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}


