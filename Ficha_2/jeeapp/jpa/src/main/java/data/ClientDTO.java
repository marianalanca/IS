package data;

import org.hibernate.annotations.ColumnTransformer;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.*;

public class ClientDTO {
    private int id;
    private String name, address, cc_number;
    private double wallet;
    private String email;

    public ClientDTO() {}

    public ClientDTO(String name) {
        this.name = name;
    }

    public ClientDTO(String name, double wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    public ClientDTO(String email, String name, String address, String cc_number, double wallet) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.cc_number = cc_number;
       this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }

    public String getCc_number() {
        return cc_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void updateWallet(double sum) {
        this.wallet += sum;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
