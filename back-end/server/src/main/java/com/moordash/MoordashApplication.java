package com.moordash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // Activate annotation-based transaction management
@Slf4j
@EnableCaching
@EnableScheduling
public class MoordashApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoordashApplication.class, args);
        log.info("server started");
    }
}
