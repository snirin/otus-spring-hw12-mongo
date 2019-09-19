package ru.otus.spring.hw12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LibraryApplication12 {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication12.class);
    }
}
