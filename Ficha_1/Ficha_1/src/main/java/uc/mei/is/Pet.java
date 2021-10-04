package uc.mei.is;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
// order of the fields in XML
// @XmlType(propOrder = {"price", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet {

    @XmlAttribute
    String id;

    @XmlElement
    String name;
    String species;
    String gender;
    String weight;
    String birth_date;
    String free_form;

    public Pet(String id, String name, String species, String  gender, String  weight, String birth_date,String  free_form ){
        this.id = id;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.weight = weight;
        this.birth_date = birth_date;
        this.free_form = free_form;
    }

    public Pet(){}

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setFree_form(String free_form) {
        this.free_form = free_form;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getFree_form() {
        return free_form;
    }

    public String getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public String getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
