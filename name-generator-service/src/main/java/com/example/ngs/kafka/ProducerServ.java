package com.example.ngs.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServ {
    private static final Logger log = LoggerFactory.getLogger(ProducerServ.class);
    private static final String TOPIC_ANIMAL = "animal";
    private static final String TOPIC_SCIENTIST = "scientist";
    @Autowired
    private KafkaTemplate<String, String> producer;

    public void sendRequestAnimal() {
        producer.send(TOPIC_ANIMAL, "get name animal");
    }

    public void sendRequestScientist() {
        producer.send(TOPIC_SCIENTIST, "get name scientist");
    }
}
