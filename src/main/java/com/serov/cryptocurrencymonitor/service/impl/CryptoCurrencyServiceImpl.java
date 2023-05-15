package com.serov.cryptocurrencymonitor.service.impl;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.exception.CurrencyException;
import com.serov.cryptocurrencymonitor.repository.CryptoCurrencyRepository;
import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyrepository;

    @Override
    public List<CryptoCurrency> findAll() {
        log.debug("Get all currencies");
        return cryptoCurrencyrepository.findAll();
    }

    @Override
    public CryptoCurrency getById(Long id) {
        log.debug("Get currency by id");
        return cryptoCurrencyrepository.findById(id)
                .orElseThrow(() -> new CurrencyException("Can not find currency by id: " + id));
    }

    @Override
    public List<Long> findAllIds() {
        return cryptoCurrencyrepository.findAllIds();
    }

}
