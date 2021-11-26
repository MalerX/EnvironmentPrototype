package com.example.ans.kafka;

import com.example.ans.AnimalNameResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerAnimal {
    private static final Logger log = LoggerFactory.getLogger(ConsumerAnimal.class);
    @Autowired
    private ProducerAnimal producer;
    @Autowired
    private AnimalNameResource nameService;

    @KafkaListener(topics = "animal", groupId = "group_id")
    public void consume(String message) {
        log.info("Received request name animal. {}", message);
        String nameAnimal = nameService.name();
        log.info("Name animal get success.");
        producer.sendScientistName(nameAnimal);
    }
}
