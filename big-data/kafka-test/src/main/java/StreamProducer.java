import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StreamProducer {
    private String KAFKA_BROKER_URL = "localhost:9092";
    private String TOPIC_NAME = "testTopic";
    private int counter;

    public static void main(String[] args) {
        new StreamProducer();
    }

  public StreamProducer()
    {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "client-producer-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer(properties);
        Random random = new Random();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            String key = String.valueOf(++counter);
            String value = String.valueOf(random.nextDouble()*9999);
            kafkaProducer.send(new ProducerRecord<String, String>(TOPIC_NAME,key,value),(metadata,exception)->{
                System.out.println("Sending message =>" + "Key: " + key + " " + "Value: "+ value + "=>" + "Partition" + " " + metadata.partition() + "Offset:" + " " + metadata.offset());
            });
        },1000,1000, TimeUnit.MILLISECONDS);
    }
}
