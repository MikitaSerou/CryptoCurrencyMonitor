package com.serov.cryptocurrencymonitor.controller;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;
import com.serov.cryptocurrencymonitor.payload.response.CurrencyResponse;
import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@Tag(name = "Cryptocurrency", description = "Cryptocurrency monitoring endpoints")
@RequiredArgsConstructor
@Slf4j
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @Operation(
            summary = "Getting a list of available currencies",
            description = "Returning a pre-configured list of available currencies for manipulation")
    @GetMapping
    @Cacheable("currencies")
    public List<AvailableCurrencyResponse> list() {
        return cryptoCurrencyService.getAvailableCurrenciesList();
    }

    @Operation(
            summary = "Getting information about a currency by identifier",
            description = "Returns the currency representation object held by the server")
    @GetMapping("/{id}")
    public CurrencyResponse getById(@PathVariable Long id) {
        CryptoCurrency currency =  cryptoCurrencyService.getById(id);
        return new CurrencyResponse(currency.getId(), currency.getPrice(), currency.getSymbol());
    }
}
