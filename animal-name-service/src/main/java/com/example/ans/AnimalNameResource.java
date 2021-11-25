package com.example.ans;

import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AnimalNameResource {
    @Autowired
    private Tracer tracer;

    private final List<String> animalNames;
    private Random random;


    public AnimalNameResource() throws IOException {
        InputStream inputStream = new ClassPathResource("/animals.txt").getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            animalNames = reader.lines().collect(Collectors.toList());
        }
        random = new Random();
    }

    public String name() {
        Date date = new Date();
        Span span = tracer.spanBuilder("AnimalNameService").startSpan();
        span.addEvent("Entered in AnimalNameService microservice", date.getTime());

        String name = animalNames.get(random.nextInt(animalNames.size()));

        span.addEvent("Exit AnimalNameService microservice", date.getTime());
        span.end();
        return name;
    }
}
