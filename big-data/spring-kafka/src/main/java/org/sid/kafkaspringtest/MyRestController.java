package org.sid.kafkaspringtest;

import lombok.AllArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@AllArgsConstructor
public class MyRestController {

    private KafkaTemplate<String,String> kafkaTemplate;
    private KafkaTemplate<String,PageEvent> kafkaTemplatePageEvent;

    @GetMapping("/send/{message}/{topic}")
    public String send(@PathVariable String message,
                       @PathVariable String topic)
    {
        kafkaTemplate.send(topic,"key " +message.length(),message);

        return "Message" + " " + message + " " + "send to topic " + topic;
    }

    @GetMapping("/pageEvent/{page}/{topic}")
    public String sendPageEvent(@PathVariable String page,
                       @PathVariable String topic)
    {
        PageEvent pageEvent = new PageEvent(page, new Date(), new Random().nextInt(1000));
        kafkaTemplatePageEvent.send(topic,"key " +pageEvent.getPage(),pageEvent);

        return "Message" + " "  + "Date " + pageEvent.getDate() + " " +"Duration " + pageEvent.getDuration() + "send to topic " + topic;
    }
}
