package com.serov.cryptocurrencymonitor.config;

import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
public class GeneralConfig {

    @Bean(name = "availableCurrencies")
    public List<AvailableCurrencyResponse> getAvailableCurrencies(){
        return Arrays.asList(
                new AvailableCurrencyResponse(90L, "BTC"),
                new AvailableCurrencyResponse(80L, "ETH"),
                new AvailableCurrencyResponse(48543L, "SOL")
        );
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("currencies");
    }
}
