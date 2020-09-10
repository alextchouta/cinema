package org.sid.kafkaspringtest;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @KafkaListener(topics = "testTopic", groupId = "group-ms")
    public void onMessage(ConsumerRecords<String,String> consumerRecords)
    {
        System.out.println("********************");
        consumerRecords.forEach(recordMessage ->{
            System.out.println(recordMessage.key() + " " + recordMessage.value());
        });
        System.out.println();
    }
}
