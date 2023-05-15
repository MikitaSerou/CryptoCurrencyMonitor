package com.serov.cryptocurrencymonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoCurrencyMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyMonitorApplication.class, args);
    }

}
