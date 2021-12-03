package com.example.ngs.kafka;

import com.example.ngs.model.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ConsumerServ {
    private static final Logger log = LoggerFactory.getLogger(ConsumerServ.class);
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "generator_names", groupId = "name_gen")
    public void consume(ConsumerRecord<String, String> record) {
        log.info("Receive name={} from topic={}", record.value(), record.topic());
        try {
            ResponseDTO response = mapper.readValue(record.value(), ResponseDTO.class);
            queue.put(response.getName());
            log.trace("Response {} add in queue.", record.value());
        } catch (InterruptedException e) {
            log.error("Error in consume: ", e);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<String> getQueue() {
        return queue;
    }
}
