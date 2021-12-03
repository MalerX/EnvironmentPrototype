package com.example.sns.kafka;

import com.example.sns.NameService;
import com.example.sns.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConsumerScientist {
    private static final Logger log = LoggerFactory.getLogger(ConsumerScientist.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    @Autowired
    private ProducerScientist producer;
    @Autowired
    private NameService nameService;

    @KafkaListener(topics = "scientist", groupId = "group_id")
    public void consume(String message) {
        log.info("Received request name scientist. {}", message);
        String nameScientist = nameService.name();
        ResponseDTO response = new ResponseDTO(format.format(new Date()), nameScientist);
        log.info("Name scientist get success.");
        producer.sendScientistName(response);
    }
}
