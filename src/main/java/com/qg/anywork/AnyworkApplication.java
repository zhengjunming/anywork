package com.qg.anywork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ming
 */
@SpringBootApplication
@EnableScheduling
public class AnyworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyworkApplication.class, args);
    }
}
