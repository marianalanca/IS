package streams;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

public class KafkaStream {

    public static double getValue(String jsonString) {
        JSONObject data = new JSONObject(jsonString);
        System.out.println(jsonString);
        return data.getDouble("value")/data.getDouble("currencyValue");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length != 3) {
            System.err.println("Wrong arguments. Please run the class as follows:");
            System.err.println(streams.KafkaStream.class.getName() + " input-topic output-topic");
            System.exit(1);
        }
        String topicName_credits = args[0].toString();
        String topicName_payments = args[1].toString();
        String outtopicname = args[2].toString();
        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> lines_credits = builder.stream(topicName_credits);

        //faz a soma
        KTable<String, Double> outlines_credits = lines_credits.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlines_credits.toStream().mapValues((k, v) -> k + "- Credits->" + v).to(outtopicname);

        // -------------------------------------------------------------------------------------------

        KStream<String, String> lines_payments = builder.stream(topicName_payments);

        //faz a soma
        KTable<String, Double> outlines_payments = lines_payments.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlines_payments.toStream().mapValues((k, v) -> k + "- Payments->" + v).to(outtopicname);

        KafkaStreams stream = new KafkaStreams(builder.build(), props);
        stream.start();


        System.out.println("Reading stream from topic " + topicName_credits);
        System.out.println("Reading stream from topic " + topicName_payments);
    }
}
