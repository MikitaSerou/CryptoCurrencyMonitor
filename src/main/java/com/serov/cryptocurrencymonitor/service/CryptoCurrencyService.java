package com.serov.cryptocurrencymonitor.service;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;

import java.util.List;

public interface CryptoCurrencyService {

    List<AvailableCurrencyResponse> getAvailableCurrenciesList();

    List<CryptoCurrency> findAll();

    CryptoCurrency getById(Long id);

    List<Long> findAllIds();
}
