package streams;
import java.io.IOException;
import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
public class SimpleStreamsExercisesc {
    private static final String tablename = "exercises";
    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length != 2) {
            System.err.println("Wrong arguments. Please run the class as follows:");
            System.err.println(SimpleStreamsExercisesc.class.getName() + " input-topic output-topic");
            System.exit(1);
        }
        String topicName = args[0].toString();
        String outtopicname = args[1].toString();
        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application-c");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.Long().getClass());
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> lines = builder.stream(topicName);
        KTable<String, Long> countlines = lines.
                groupByKey().
                reduce((oldval, newval) -> oldval + newval, Materialized.as(tablename));
        countlines.mapValues(v -> "" + v).toStream().to(outtopicname,
                Produced.with(Serdes.String(), Serdes.String()));
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        System.out.println("Press enter when ready...");
        System.in.read();
        while (true) {
            ReadOnlyKeyValueStore<String, Long> keyValueStore = streams.store(tablename,
                    QueryableStoreTypes.keyValueStore());
            System.out.println("count for 355:" + keyValueStore.get("355"));
            System.out.println();
// Get the values for a range of keys available in this application instance
            KeyValueIterator<String, Long> range = keyValueStore.range("960", "980");
            while (range.hasNext()) {
                KeyValue<String, Long> next = range.next();
                System.out.println("count for " + next.key + ": " + next.value);
            }
            range.close();
            Thread.sleep(30000);
        }
    }
}