package beans;

import data.Student;

import java.util.List;

public interface IManageStudents {
    public void addStudent(String name);

    public List<Student> listStudents();
}
