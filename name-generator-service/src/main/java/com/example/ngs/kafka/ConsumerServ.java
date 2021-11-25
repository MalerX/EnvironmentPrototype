package com.example.ngs.kafka;

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

    @KafkaListener(topics = "generator_names", groupId = "name_gen")
    public void consume(ConsumerRecord<String, String> record) {
        log.info("Receive name={} from topic={}", record.value(), record.topic());
        try {
            queue.put(record.value());
            log.trace("Response {} add in queue.", record.value());
        } catch (InterruptedException e) {
            log.error("Error in consume: ", e);
        }
    }

    public BlockingQueue<String> getQueue() {
        return queue;
    }
}
