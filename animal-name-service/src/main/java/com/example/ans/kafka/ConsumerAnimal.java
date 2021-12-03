package com.example.ans.kafka;

import com.example.ans.AnimalNameResource;
import com.example.ans.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConsumerAnimal {
    private static final Logger log = LoggerFactory.getLogger(ConsumerAnimal.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    @Autowired
    private ProducerAnimal producer;
    @Autowired
    private AnimalNameResource nameService;

    @KafkaListener(topics = "animal", groupId = "secondServices")
    public void consume(String message) {
        log.info("Received request name animal. {}", message);
        String nameAnimal = nameService.name();
        ResponseDTO response = new ResponseDTO(format.format(new Date()), nameAnimal);
        log.info("Name animal get success.");
        producer.sendAnimalName(response);
    }
}
