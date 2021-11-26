package com.example.ans;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AnimalNameResource {
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
        String name = animalNames.get(random.nextInt(animalNames.size()));
        return name;
    }
}
