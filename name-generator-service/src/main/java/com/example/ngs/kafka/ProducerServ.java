package com.example.ngs.kafka;

import com.example.ngs.model.RequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProducerServ {
    private static final Logger log = LoggerFactory.getLogger(ProducerServ.class);
    private static final String TOPIC_ANIMAL = "animal";
    private static final String TOPIC_SCIENTIST = "scientist";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    @Autowired
    private KafkaTemplate<String, String> producer;

    public void sendRequestAnimal() {
        try {
            RequestDTO request = new RequestDTO(format.format(new Date()), TOPIC_ANIMAL);
            StringWriter message = new StringWriter();
            mapper.writeValue(message, request);
            producer.send(TOPIC_ANIMAL, message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequestScientist() {
        try {
            RequestDTO request = new RequestDTO(format.format(new Date()), TOPIC_SCIENTIST);
            StringWriter message = new StringWriter();
            mapper.writeValue(message, request);
            producer.send(TOPIC_SCIENTIST, message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
