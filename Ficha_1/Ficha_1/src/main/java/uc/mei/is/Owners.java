package uc.mei.is;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Owners {
    @XmlElement(name = "Owner")
    List<Owner> list = new ArrayList<>();

    public List<Owner> getList() {
        return list;
    }

    public void setList(List<Owner> list) {
        this.list = list;
    }

    public Owners() {}

    public Owners(List<Owner> list) {
        this.list = list;
    }


}
