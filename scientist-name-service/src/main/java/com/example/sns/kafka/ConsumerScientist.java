package com.example.sns.kafka;

import com.example.sns.NameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerScientist {
    private static final Logger log = LoggerFactory.getLogger(ConsumerScientist.class);
    @Autowired
    private ProducerScientist producer;
    @Autowired
    private NameService nameService;

    @KafkaListener(topics = "scientist", groupId = "group_id")
    public void consume(String message) {
        log.info("Received request name scientist. {}", message);
        String nameScientist = nameService.name();
        log.info("Name scientist get success.");
        producer.sendScientistName(nameScientist);
    }
}
