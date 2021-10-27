package jpaprimer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Trip {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date departure_date;
    private String departure_point, destination;
    private double price;

    public Trip() {
    }

    public Trip(Date departure_date, String departure_point, String destination, double price, Bus bus) {
        this.departure_date = departure_date;
        this.departure_point = departure_point;
        this.destination = destination;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeparture_date(Date departure_date) {
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

    public Date getDeparture_date() {
        return departure_date;
    }

    public double getPrice() {
        return price;
    }


    public int getId() {
        return id;
    }

    public String getDeparture_point() {
        return departure_point;
    }

    public String getDestination() {
        return destination;
    }
}
