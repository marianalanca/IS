package messagepack;

import org.msgpack.annotation.Message;

import java.util.ArrayList;
import java.util.List;

@Message // Annotation
public class Owner {
    public String name;
    public String birth_date;
    public String telephone;
    public String address;

    List<Pet> list = new ArrayList<>();
}
