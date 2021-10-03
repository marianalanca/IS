package messagepack;

import org.msgpack.annotation.Message;

@Message // Annotation
public class Pet {
    public String id;
    public String name;
    public String species;
    public String gender;
    public String weight;
    public String birth_date;
    public String free_form;
}
