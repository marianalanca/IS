package streams;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;

public class SimpleStreamsExercisesd {
    private static final String tablename = "exercises";
    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length != 2) {
            System.err.println("Wrong arguments. Please run the class as follows:");
            System.err.println(SimpleStreamsExercisesd.class.getName() + " input-topic output-topic");
                    System.exit(1);
        }
        String topicName = args[0].toString();
        String outtopicname = args[1].toString();
        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application-d");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.Long().getClass());
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> lines = builder.stream(topicName);
        KTable<Windowed<String>, Long> addvalues = lines.
                groupByKey().
                windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).
                reduce((aggval, newval) -> aggval + newval, Materialized.as("lixo"));
        addvalues.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k +
                "-->" + v)).to(outtopicname, Produced.with(Serdes.String(),
                Serdes.String()));
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        System.out.println("Reading stream from topic " + topicName);
    }
}