package com.example.sns.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerScientist {
    private static final Logger log = LoggerFactory.getLogger(ProducerScientist.class);
    private static final String TOPIC = "generator_names";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendScientistName(String name) {
        log.info("Send name scientist. {}", name);
        kafkaTemplate.send(TOPIC, name);
    }
}
