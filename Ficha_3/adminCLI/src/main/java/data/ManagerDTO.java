package data;

import java.io.Serializable;

public class ManagerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ManagerDTO(int id) {
        this.id = id;
    }

    public ManagerDTO(){};

    @Override
    public String toString() {
        return "ID: " + id;
    }
}
