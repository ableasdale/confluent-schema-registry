import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

public class SampleKafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {

        final Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29091");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);


        producer.send(new ProducerRecord<>("test", "test"),
                (event, ex) -> {
                    if (ex != null)
                        LOG.error("Exception:",ex);
                    else
                        LOG.info(String.format("Produced event to topic %s: key = %-10s value = %s", "x", "y", "z"));
                });
        producer.flush();
        LOG.info(String.format("An event was produced to topic"));
        producer.close();
    }
}
