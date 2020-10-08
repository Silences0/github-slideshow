package com.example.layuidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LayuidemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayuidemoApplication.class, args);
    }

}
