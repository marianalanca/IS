package data;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int seat;

    @ManyToOne(cascade = CascadeType.ALL)
    private Trip trip;

    @ManyToOne(cascade = CascadeType.ALL)
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

