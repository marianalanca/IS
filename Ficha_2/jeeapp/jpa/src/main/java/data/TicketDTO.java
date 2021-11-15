package data;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class TicketDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int seat;
    private String clientName;

    //private Trip trip;
    //private ClientUser client;

    public TicketDTO() {}

    public TicketDTO(String client) {
        this.clientName = client;
    }

    public TicketDTO(String client, int seat) {
        this.clientName = client;
        this.seat = seat;
        //this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String client) {
        this.clientName = client;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    /*
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }*/
}


