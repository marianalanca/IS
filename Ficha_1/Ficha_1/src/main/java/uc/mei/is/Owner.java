package uc.mei.is;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {
    @XmlAttribute
    String id;

    @XmlElement
    String name;
    String birth_date;
    String telephone;
    String address;


    @XmlElement(name = "Pet")
    List<Pet> list = new ArrayList<>();


    public Owner() {}

    public Owner(String id, String name, String birth_date, String telephone, String address) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.telephone = telephone;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setList(List<Pet> list) {
        this.list = list;
    }

    public void addToList(Pet pet) {
        list.add(pet);
    }

    public List<Pet> getList() {
        return list;
    }
}
