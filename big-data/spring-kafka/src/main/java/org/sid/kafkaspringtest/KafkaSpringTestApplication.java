package org.sid.kafkaspringtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaSpringTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringTestApplication.class, args);
    }

}
