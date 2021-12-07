package kafka;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

// https://howtodoinjava.com/gson/gson-serialize-deserialize-json/

public class Clients {

    static List<String> currencies = Arrays.asList("dollar", "Euro", "Yen", "Pound", "Won", "Franc", "Rupee", "Lira");

    public static void main(String[]args) throws Exception {
        //Assign topicName to string variable
        // topicName[0] -> currencies; [1] -> payments
        List<String> topicName = Arrays.asList(args[0].toString(), args[1].toString());
        //String topicName_credit = args[0].toString();
        //String topicName_payment = args[1].toString();
        // create instance for properties to access producer configs
        Properties props = new Properties();

        System.out.println("SOU O CLIENTS");

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // gerar lista de clients -> test
        List<Client_test> clients = Arrays.asList(
                new Client_test("01", 0, 0),
                new Client_test("02", 0, 0),
                new Client_test("03", 0, 0),
                new Client_test("04", 0, 0)
        );

        while (true) {
            for (int i=0; i<100; i++) {
                String jsonString = gson.toJson(new Object(new Random().nextInt(100),
                        currencies.get(new Random().nextInt(currencies.size())),1));

                String topic = topicName.get(new Random().nextInt(2));

                // a key é o id do client
                producer.send(new ProducerRecord<String, String>(topic,
                        clients.get(new Random().nextInt(clients.size())).getId(),
                        jsonString));

                System.out.println("Sending message to topic " + topic);
            }

            Thread.sleep(5000);

        }
        //producer.close();
    }
}
