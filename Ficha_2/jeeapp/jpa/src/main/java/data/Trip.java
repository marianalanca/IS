package data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Trip {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime departure_date;
    private String departure_point, destination;
    private int capacity;
    private double price;

    /*
    @ManyToOne(cascade = CascadeType.ALL)
    private Bus bus; */

    @ManyToMany(mappedBy="tickets")
    private List<ClientUser> passengers;

    public Trip() {
    }

    public Trip(LocalDateTime departure_date, String departure_point, String destination, double price, int capacity) {
        this.departure_date = departure_date;
        this.departure_point = departure_point;
        this.destination = destination;
        this.capacity = capacity;
        this.price = price;
    }

    public void setId(int id) { this.id = id; }

    public void setDeparture_date(LocalDateTime departure_date) {
        this.departure_date = departure_date;
    }

    public void setDeparture_point(String departure_point) {
        this.departure_point = departure_point;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setcapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDeparture_date() {
        return departure_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPassengers(List<ClientUser> passengers) {
        this.passengers = passengers;
    }

    // TEST
    /*
    public Boolean addPassengers(ClientUser passenger) {
        if (passengers.size() < bus.getCapacity() && !passengers.contains(passenger)) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }*/

    public int getId() {
        return id;
    }

    public String getDeparture_point() {
        return departure_point;
    }

    public String getDestination() {
        return destination;
    }

    public int getcapacity(){ return  capacity; }

    /*public List<AppUser> getPassengers() {
        return passengers;
    }*/
}
