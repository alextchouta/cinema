import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StreamConsumer {
    private String KAFKA_BROKER_URL = "localhost:9092";
    private String TOPIC_NAME = "testTopic";

    public static void main(String[] args) {
     //   new StreamConsumer();

        BigDecimal bigDecimal1 = new BigDecimal("1.2345678");
        BigDecimal  bigDecimal2 = new BigDecimal("0.01");

        System.out.println(bigDecimal1.subtract(bigDecimal1.remainder(bigDecimal2)));

        //System.out.println(1.2345678-(1.2345678%0.01));


    }

    public StreamConsumer()
    {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test-group-1");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,30000);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singleton(TOPIC_NAME));

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            System.out.println("-----------------------");
            ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(10));
            consumerRecords.forEach(consumerRecord ->{
                System.out.println("Key" + " " +consumerRecord.key() + " " + "Value "+ consumerRecord.value() + "=>" + consumerRecord.offset());
            });
        },1000,1000, TimeUnit.MILLISECONDS);
    }
}
