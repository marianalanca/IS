package kafka;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.KeyValue;

// https://howtodoinjava.com/gson/gson-serialize-deserialize-json/

public class Clients {

    static List<KeyValue<String, Double>> currencies = Arrays.asList(
            new KeyValue<String, Double>("Dollar", 0.89),
            new KeyValue<String, Double>("Euro", 1.0),
            new KeyValue<String, Double>("Yen", 0.0078),
            new KeyValue<String, Double>("Pound", 1.18),
            new KeyValue<String, Double>("Won", 0.00075),
            new KeyValue<String, Double>("Franc", 0.96),
            new KeyValue<String, Double>("Rupee", 0.012),
            new KeyValue<String, Double>("Lira", 0.065));

    public static void main(String[]args) throws Exception {
        //Assign topicName to string variable
        // topicName[0] -> currencies; [1] -> payments
        List<String> topicName = Arrays.asList(args[0].toString(), args[1].toString());
        String topicNameConsumer = "DBInfo";

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

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topicNameConsumer));

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
            for (int i=0; i<50; i++) {
                KeyValue<String, Double> currency = currencies.get(new Random().nextInt(currencies.size()));
                String jsonString = gson.toJson(new Object(new Random().nextInt(100),
                        currency.key,currency.value));

                String topic = topicName.get(new Random().nextInt(2));

                // a key é o id do client
                producer.send(new ProducerRecord<String, String>(topic,
                        clients.get(new Random().nextInt(clients.size())).getId(),
                        jsonString));

                System.out.println("Sending message to topic " + topic);
            }

            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records) {
                //Object empObject = gson.fromJson(record.value(), Object.class);
                System.out.println(record.key() + " => " + record.value());
            }

            Thread.sleep(5000);

        }
        //producer.close();
    }
}
