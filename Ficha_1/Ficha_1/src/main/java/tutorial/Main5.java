package tutorial;

import java.util.*;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

import static org.msgpack.template.Templates.*;

public class Main5 {
    public static void main(String[] args) throws Exception {
        // Create serialize objects.
        List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePack msgpack = new MessagePack();
        // Serialize
        byte[] raw = msgpack.write(src);

        // Deserialize directly using a template
        List<String> dst1 = msgpack.read(raw, tList(TString));

        // Or, Deserialze to Value then convert type.
        Value dynamic = msgpack.read(raw);
        List<String> dst2 = new Converter(dynamic).read(tList(TString));
    }
}