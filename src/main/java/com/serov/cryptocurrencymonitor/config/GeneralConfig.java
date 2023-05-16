package com.serov.cryptocurrencymonitor.config;

import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
