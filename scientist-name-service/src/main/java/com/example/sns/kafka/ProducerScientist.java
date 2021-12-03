package com.example.sns.kafka;

import com.example.sns.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;

@Service
public class ProducerScientist {
    private static final Logger log = LoggerFactory.getLogger(ProducerScientist.class);
    private static final String TOPIC = "generator_names";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendScientistName(ResponseDTO response) {
        try {
            response.setFromTopic(TOPIC);
            StringWriter message = new StringWriter();
            mapper.writeValue(message, response);
            kafkaTemplate.send(TOPIC, message.toString());
            log.info("Send name scientist. {}", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
