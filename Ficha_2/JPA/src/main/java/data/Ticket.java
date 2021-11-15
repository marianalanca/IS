package data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int seat;

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private ClientUser client;

    public Ticket() {}

    public Ticket(ClientUser client, Trip trip, int seat) {
        this.client = client;
        this.seat = seat;
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientUser getClient() {
        return client;
    }

    public void setClient(ClientUser client) {
        this.client = client;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}

