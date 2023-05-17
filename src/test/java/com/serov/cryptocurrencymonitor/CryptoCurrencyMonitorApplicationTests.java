package com.serov.cryptocurrencymonitor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@DisplayName("Context test")
class CryptoCurrencyMonitorApplicationTests {

    @Test
    @DisplayName("Context up")
    void contextLoads() {
        log.info("Nice");
    }

}
