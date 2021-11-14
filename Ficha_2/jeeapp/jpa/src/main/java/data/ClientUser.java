package data;

import org.hibernate.annotations.ColumnTransformer;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.*;

@Entity
public class ClientUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name, address, cc_number;
    private double wallet;
    private String email;
    @ColumnTransformer(read = "pgp_sym_decrypt(password::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public ClientUser() {}

    public ClientUser(String email, String password, String name, String address, String cc_number) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.cc_number = cc_number;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }


}
