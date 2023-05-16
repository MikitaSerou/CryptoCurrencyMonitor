package com.serov.cryptocurrencymonitor.controller;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;
import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
@Slf4j
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping
    public List<AvailableCurrencyResponse> list() {
        return cryptoCurrencyService.getAvailableCurrenciesList();
    }

    @GetMapping("/{id}")
    public CryptoCurrency getById(@PathVariable Long id) {
        return cryptoCurrencyService.getById(id);
    }
}
