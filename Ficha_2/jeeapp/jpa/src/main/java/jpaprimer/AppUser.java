package jpaprimer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name, address, cc_number;
    @Temporal(TemporalType.DATE)
    private Date birth;
    private int age;
    private double wallet;
    private String email, password;

    // REVER
    @ManyToMany
    private List<Trip> tickets;


    public AppUser() {}

    public AppUser(String email, String password, String name, String address, String cc_number, int age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.cc_number = cc_number;
        this.age = age;
        wallet = 0.0;
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

    public int getAge() {
        return age;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
