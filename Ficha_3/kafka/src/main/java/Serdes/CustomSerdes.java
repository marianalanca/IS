package Serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {
    static public final class ClientDebtSerde
            extends Serdes.WrapperSerde<ClientDebt> {
        public ClientDebtSerde() {
            super(new JsonSerializer<>(),
                    new JsonDeserializer<>(ClientDebt.class));
        }
    }

    public static Serde<ClientDebt> ClientDebt() {
        return new CustomSerdes.ClientDebtSerde();
    }
}
