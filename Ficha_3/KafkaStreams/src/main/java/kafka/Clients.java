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
        String topicName_credit = args[0].toString();
        String topicName_payment = args[1].toString();
        // create instance for properties to access producer configs
        Properties props = new Properties();

        System.out.println("SOU O PRODUCER");

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


        for (int i = 0; i < 1000; i++) {

            String jsonString = gson.toJson(new Object("user", new Random().nextInt(100000),
                    currencies.get(new Random().nextInt(currencies.size()))));

            producer.send(new ProducerRecord<String, String>(topicName_credit, "send",
                    jsonString));

            System.out.println("Sending message " + (i + 1) + " to topic " + topicName_credit);
        }
        producer.close();
    }
}
