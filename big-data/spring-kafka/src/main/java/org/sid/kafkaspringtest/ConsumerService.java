package org.sid.kafkaspringtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "testTopic", groupId = "group-ms")
    public void onMessage(ConsumerRecord<String,String> consumerRecords)
    {
        System.out.println("********************");

        System.out.println(consumerRecords.key() + " " + consumerRecords.value());

        System.out.println("********************");
    }

    @KafkaListener(topics = "pageEventTopic", groupId = "pageEvent-Id")
    public void onMessagePageEvent(ConsumerRecord<String,String> consumerRecords)
    {
        System.out.println("********************");

        PageEvent pageEvent = pageEvent(consumerRecords.value());

        System.out.println(consumerRecords.key() + " " + pageEvent.toString());

        System.out.println("********************");
    }

    private PageEvent pageEvent(String jsonPageEvent)
    {
        PageEvent pageEventMap = null;
        JsonMapper jsonMapper = new JsonMapper();
        try {
            pageEventMap = jsonMapper.readValue(jsonPageEvent,PageEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return pageEventMap;
    }
}
