package data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bus_number;
    private int capacity;

    @OneToMany(mappedBy = "bus")
    private List<Trip> bt;

    public Bus() {
    }

    public Bus(String bus_number, int capacity) {
        this.bus_number = bus_number;
        this.capacity = capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public String getBus_number() {
        return bus_number;
    }
}
