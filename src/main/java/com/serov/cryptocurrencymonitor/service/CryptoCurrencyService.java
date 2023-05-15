package com.serov.cryptocurrencymonitor.service;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;

import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrency> findAll();

    CryptoCurrency getById(Long id);

    List<Long> findAllIds();
}
