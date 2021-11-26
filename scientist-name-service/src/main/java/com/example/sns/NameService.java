package com.example.sns;

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
public class NameService {
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
        return scientistsNames.get(random.nextInt(scientistsNames.size()));
    }
}
