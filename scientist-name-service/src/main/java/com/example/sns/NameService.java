package com.example.sns;

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
public class NameService {
    @Autowired
    private Tracer tracer;

    private final List<String> scientistsNames;
    private Random random;

    public NameService() throws IOException {
        InputStream inputStream = new ClassPathResource("/scientists.txt").getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            scientistsNames = reader.lines().collect(Collectors.toList());
        }
        random = new Random();
    }

    public String name() {
        Date date = new Date();
        Span span = tracer.spanBuilder("ScientistNameService").startSpan();
        span.addEvent("Entered in ScientistNameService microservice.", date.getTime());
        span.setAttribute("Timestamp start", date.getTime());

        String name = scientistsNames.get(random.nextInt(scientistsNames.size()));

        span.addEvent("Exit ScientistNameService microservice.");
        span.setAttribute("Timestamp finish", date.getTime());
        span.end();
        return name;
    }
}
