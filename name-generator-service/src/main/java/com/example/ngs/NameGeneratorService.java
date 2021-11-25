package com.example.ngs;

import com.example.ngs.kafka.ConsumerServ;
import com.example.ngs.kafka.ProducerServ;
import io.opentelemetry.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static strman.Strman.toKebabCase;

@SpringBootApplication
public class NameGeneratorService {

    public static void main(String[] args) {
        SpringApplication.run(NameGeneratorService.class, args);
    }

}

@RestController
@RequestMapping("/api/v1/names")
class NameResource {
    private static final Logger log = LoggerFactory.getLogger(NameResource.class);

    @Autowired
    private ConsumerServ consumer;
    @Autowired
    private ProducerServ producer;

    @Autowired
    private Tracer tracer;


    @GetMapping(path = "/random")
    public String name() throws Exception {
        String scientist = "",
                animal = "";

        producer.sendRequestAnimal();
        log.info("Send request animal name");


        animal = consumer.getQueue().take();
        log.info("Name={} from service AnimalNames success receive.", animal);

        producer.sendRequestScientist();
        log.info("Send request scientist name");

        Thread.sleep(1000);

        scientist = consumer.getQueue().take();
        log.info("Name={} from service ScientistNames success receive.", animal);

        String name = toKebabCase(scientist) + "-" + toKebabCase(animal);
        log.info("Compile name {} from names {} - {}", name, scientist, animal);

        return name;
    }


}