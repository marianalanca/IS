package messagepack;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.msgpack.template.Templates.TValue;
import static org.msgpack.template.Templates.tList;

public class MessagePack_f {

    public static void main(String[] args) throws Exception {

        Owner src = new Owner();
        Pet pet = new Pet();
        src.name = "msgpack";
        src.birth_date = "5-5-2020";
        src.telephone = "919191919";
        src.address = "rua da cabrita, vila moleza";

        pet.name = "coisa";
        src.list.add(pet);

        Owner src_1 = new Owner();
        src_1.name = "msgpack_1";
        src.birth_date = "5-5-2020";
        src.telephone = "919191919";
        src.address = "rua da cabrita, vila moleza";
        src.list.add(pet);

        ArrayList<Owner> list = new ArrayList<>();

        list.add(src);
        list.add(src_1);

        MessagePack msgpack = new org.msgpack.MessagePack();

        //
        // Serialization
        //
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Packer packer = msgpack.createPacker(out);

        packer.write(list); // List object

        //
        // Deserialization
        //
        byte[] bytes = out.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Unpacker unpacker = msgpack.createUnpacker(in);

        Owner[] dst = msgpack.read(bytes, Owner[].class);

        System.out.println(dst[1].name);
    }
}
