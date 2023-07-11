import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class GenericRecordExample {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {
        // 1. Parse an AVSC file to get the Avro (AVSC) Schema
        Schema.Parser parser = new Schema.Parser();
        Schema schema;
        try {
            schema = parser.parse(Files.readString(Path.of("src/main/avro/purchase.avsc"), StandardCharsets.UTF_8));
            LOG.debug("Schema: " + schema.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 2. Create a GenericRecord
        GenericRecordBuilder purchase = new GenericRecordBuilder(schema);
        purchase.set("item", "thing");
        purchase.set("total_cost", 2.34);
        purchase.set("customer_id", "xr3i");
        GenericData.Record pur = purchase.build();
        LOG.info("What do we have?"+pur);

        final Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29091");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("schema.registry.url", "http://localhost:8081");
        props.put("value.serializer", io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        Producer<String, String> producer = new KafkaProducer<>(props);

        produceTo(producer, "avro-test-topic-new", pur);
        producer.flush();
        LOG.info(String.format("An event was produced to topic"));
        producer.close();

    }
    private static void produceTo(Producer<String, String> producer, String topic, Object o) {
        producer.send(new ProducerRecord(topic, o),
                (event, ex) -> {
                    if (ex != null)
                        LOG.error("Exception:",ex);
                    else
                        LOG.info(String.format("Produced event to topic %s: key = %-10s value = %s", "x", "y", "z"));
                });
    }
}
