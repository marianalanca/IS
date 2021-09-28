package uc.mei.is;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Class {

    @XmlElement(name = "Student")
    List<Student> list = new ArrayList<>();

    public void setList(List<Student> list) {
        this.list = list;
    }

    public void addToList(Student student) {
        list.add(student);
    }

    public List<Student> getList() {
        return list;
    }
}
