package streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import Serdes.ClientDebt;
import Serdes.CustomSerdes;

public class KafkaStream {

    public static long month = 30;

    public static double getValue(String jsonString) {
        JSONObject toString = new JSONObject(jsonString);
        return toString.getDouble("value")*toString.getDouble("currencyValue");
    }

    public static int getManagerId(String jsonString) {
        JSONObject toString = new JSONObject(jsonString);
        return toString.getInt("managerId");
    }

    public static String toDBFormat(String c, String k, Double v){
        return "{\"schema\":{\"type\":\"struct\",\"fields\":" +
                "[" +
                "{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"}," +
                "{\"type\":\"double\",\"optional\":false,\"field\":\"" + c + "\"}" +
                "]," +
                "\"optional\":false}," +
                "\"payload\":{\"id\":" + k + ",\"" + c + "\":" + v + "}}";
        }

    public static String toDBFormat(String c, String k, String v){
        return "{\"schema\":{\"type\":\"struct\",\"fields\":" +
                "[" +
                "{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"}," +
                "{\"type\":\"int32\",\"optional\":false,\"field\":\"" + c + "\"}" +
                "]," +
                "\"optional\":false}," +
                "\"payload\":{\"id\":" + k + ",\"" + c + "\":" + v + "}}";
    }

    public static String toDBFormat(String c, String k, Long v){
        return "{\"schema\":{\"type\":\"struct\",\"fields\":" +
                "[" +
                "{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"}," +
                "{\"type\":\"double\",\"optional\":false,\"field\":\"" + c + "\"}" +
                "]," +
                "\"optional\":false}," +
                "\"payload\":{\"id\":" + k + ",\"" + c + "\":" + v + "}}";
    }



    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length != 2) {
            System.err.println("Wrong arguments. Please run the class as follows:");
            System.err.println(streams.KafkaStream.class.getName() + " input-topic-Credits input-topic-Payments");
            System.exit(1);
        }
        String topicCredits = args[0].toString();
        String topicPayments = args[1].toString();

        String clientTopic = "client";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> lines_credits = builder.stream(topicCredits);
        KStream<String, String> lines_payments = builder.stream(topicPayments);

        //7. Get the credit per client (students should compute this and the following values in euros)
        KTable<String, Double> outlinesCredits = lines_credits.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlinesCredits.toStream().mapValues((k, v) -> toDBFormat("credits", k, v)).to(clientTopic);


        //8.Get the payments (i.e., credit reimbursements) per client
        KTable<String, Double> outlinesPayments = lines_payments.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                        reduce( Double::sum);
        outlinesPayments.toStream().mapValues((k, v) -> toDBFormat("payments", k, v)).to(clientTopic);

        //9. Get the current balance of a client
        ValueJoiner<Double, Double, Double> valueSub = (leftValue, rightValue) -> leftValue - rightValue;

        KTable<String, Double> outlinesBalance = outlinesCredits.join(outlinesPayments,valueSub);

        outlinesBalance.mapValues((k, v) -> toDBFormat("balance", k, v)).toStream().to(clientTopic);

        //10. Get the total (i.e., sum of all persons) credits
        KTable<String, Double> outlinesTotalC = lines_credits.
                map((k,v) -> new KeyValue<>("0", getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlinesTotalC.toStream().mapValues((k, v) -> toDBFormat("credits", "0", v)).to(clientTopic);

        //11. Get the total payments
        KTable<String, Double> outlinesTotalP = lines_payments.
                map((k,v) -> new KeyValue<>("0", getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);
        outlinesTotalP.toStream().mapValues((k, v) -> toDBFormat("payments", "0", v)).to(clientTopic);

        //12. Get the total balance -> VERIFICAR
        outlinesTotalC.join(outlinesTotalP,valueSub).mapValues((k, v) -> toDBFormat("balance", k, v)).toStream().to(clientTopic);

        //13. Compute the bill for each client for the last month1 (use a tumbling time window). -> TESTAR
        // TODO
        KTable<Windowed<String>, Double> bill_month = lines_credits.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                windowedBy(TimeWindows.of(Duration.ofDays(TimeUnit.DAYS.toMillis(month)))).
                //windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(5))).
                reduce(Double::sum);

        bill_month.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, toDBFormat("billmonth", k, v))).
                to(clientTopic, Produced.with(Serdes.String(),
                Serdes.String()));

        //14. Get the list of clients without payments for the last two months. -> TESTAR
        // TODO
        KTable<Windowed<String>, Long> payment_month = lines_payments.
                map((k,v) -> new KeyValue<>(k, getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                windowedBy(TimeWindows.of(Duration.ofDays(TimeUnit.MINUTES.toMillis(5)))).
                count();

        payment_month.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, toDBFormat("paymentstwomonths", k, v))).
                to(clientTopic, Produced.with(Serdes.String(),
                        Serdes.String()));

        //15. Get the data of the person with the highest outstanding debt (i.e., the most negative current balance)
        // aggregate


        outlinesBalance.toStream()
                .map((k,v) -> new KeyValue<>("0", new ClientDebt(k,v)))
                .groupByKey(Grouped.with(Serdes.String(), CustomSerdes.ClientDebt()))
                .aggregate(() -> new ClientDebt("0",Double.MAX_VALUE),
                        (key, value, aggregate) -> {
                            if(value.getValue() < aggregate.getValue()){
                                return new ClientDebt(value.getClientId(), value.getValue());
                            }
                            else{
                                return aggregate;
                            }
                        }, Materialized.with(Serdes.String(), CustomSerdes.ClientDebt()))
                .mapValues((k, v) -> {
                    return toDBFormat("highestdebtid", "0", v.getClientId().toString());
                })
                .toStream()
                .to(clientTopic);


        // 16. Get the data of the manager who has made the highest revenue in payments from his or her clients.

        KTable<String, Double> outlinesManPayments = lines_payments.
                map((k,v) -> new KeyValue<>(String.valueOf(getManagerId(v)), getValue(v))).
                groupByKey(Grouped.with(Serdes.String(), Serdes.Double())).
                reduce(Double::sum);

        outlinesManPayments.toStream()
                .map((k,v) -> new KeyValue<>("0", new ClientDebt(k,v)))
                .groupByKey(Grouped.with(Serdes.String(), CustomSerdes.ClientDebt()))
                .aggregate(() -> new ClientDebt("0",Double.MIN_VALUE),
                        (key, value, aggregate) -> {
                            if(value.getValue() > aggregate.getValue()){
                                return new ClientDebt(value.getClientId(), value.getValue());
                            }
                            else{
                                return aggregate;
                            }
                        }, Materialized.with(Serdes.String(), CustomSerdes.ClientDebt()))
                .mapValues((k, v) -> {
                    return toDBFormat("highestrevid", k, v.getClientId());
                })
                .toStream()
                .to(clientTopic);


        KafkaStreams stream = new KafkaStreams(builder.build(), props);
        stream.start();

        System.out.println("Reading stream from topic " + topicCredits);
        System.out.println("Reading stream from topic " + topicPayments);
    }
}
