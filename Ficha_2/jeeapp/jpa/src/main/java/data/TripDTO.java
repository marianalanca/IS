package data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TripDTO {
    private int id;
    private LocalDateTime departure_date;
    private String departure_point, destination;
    private int capacity;
    private double price;

    public TripDTO() {
    }

    public TripDTO(LocalDateTime departure_date, String departure_point, String destination, double price, int capacity) {
        this.departure_date = departure_date;
        this.departure_point = departure_point;
        this.destination = destination;
        this.capacity = capacity;
        this.price = price;
    }

    public TripDTO(int id, LocalDateTime departure_date, String departure_point, String destination, double price, int capacity) {
        this.departure_date = departure_date;
        this.departure_point = departure_point;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
        this.id = id;
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

    public String getDeparture_date_String() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return departure_date.format(formatter);
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

    public int getCapacity(){ return  capacity; }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

