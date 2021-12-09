package streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaStream {

    public static double getValue(String jsonString) {
        JSONObject data = new JSONObject(jsonString);
        //System.out.println(data.getDouble("value")/data.getDouble("currencyValue"));
        return data.getDouble("value")*data.getDouble("currencyValue");
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
        KStream<String, String> lines_payments = builder.stream(topicName_payments);

        //7. Get the credit per client (students should compute this and the following values in euros)
        KTable<String, Double> outlines_credits = lines_credits.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlines_credits.toStream().mapValues((k, v) -> k + " -Credits per client->" + v).to(outtopicname);

        //8.Get the payments (i.e., credit reimbursements) per client.
        KTable<String, Double> outlines_payments = lines_payments.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                        reduce(Double::sum);
        outlines_payments.toStream().mapValues((k, v) -> k + " -Payments per client->" + v).to(outtopicname);

        //Get the current balance of a client.
        ValueJoiner<Double, Double, Double> valueSub = (leftValue, rightValue) -> leftValue - rightValue;

        outlines_payments.join(outlines_credits,valueSub).mapValues((k, v) -> k + " -Total Balance per client-> " + v).toStream().to(outtopicname);

        //10. Get the total (i.e., sum of all persons) credits
        KTable<String, Double> outlines_totalC = lines_credits.
                map((k,v) -> new KeyValue<>("0", getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlines_totalC.toStream().mapValues((k, v) -> k + " -Total Credits->" + v).to(outtopicname);

        //11. Get the total payments
        KTable<String, Double> outlines_totalP = lines_payments.
                map((k,v) -> new KeyValue<>("0", getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlines_totalP.toStream().mapValues((k, v) -> k + " -Total Payments->" + v).to(outtopicname);

        //12. Get the total balance

        outlines_totalP.join(outlines_totalC,valueSub).mapValues((k, v) -> k + " -Total Balance-> " + v).toStream().to(outtopicname);

        //13. Compute the bill for each client for the last month1 (use a tumbling time window). -> TESTAR

        KTable<Windowed<String>, Double> bill_month = lines_credits.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                //windowedBy(TimeWindows.of(TimeUnit.DAYS.toMillis(30))).
                windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(5))).
                reduce(Double::sum);

        bill_month.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, k + " -Window-> " + v)).
                to(outtopicname, Produced.with(Serdes.String(),
                Serdes.String()));

        //13. Compute the bill for each client for the last month1 (use a tumbling time window). -> TESTAR

        KTable<Windowed<String>, Double> payment_month = lines_payments.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                windowedBy(TimeWindows.of(TimeUnit.DAYS.toMillis(30))).
                reduce(Double::sum);

        payment_month.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, k + " -Window-> " + v)).
                to(outtopicname, Produced.with(Serdes.String(),
                        Serdes.String()));

        KafkaStreams stream = new KafkaStreams(builder.build(), props);
        stream.start();

        System.out.println("Reading stream from topic " + topicName_credits);
        System.out.println("Reading stream from topic " + topicName_payments);
    }
}
