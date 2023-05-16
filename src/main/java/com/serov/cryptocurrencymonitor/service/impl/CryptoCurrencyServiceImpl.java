package com.serov.cryptocurrencymonitor.service.impl;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.exception.CurrencyException;
import com.serov.cryptocurrencymonitor.payload.ExternalApiResponse;
import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;
import com.serov.cryptocurrencymonitor.repository.CryptoCurrencyRepository;
import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import com.serov.cryptocurrencymonitor.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final List<AvailableCurrencyResponse> availableCurrencies;
    private final CryptoCurrencyRepository cryptoCurrencyrepository;
    private final ExternalApiService externalApiService;

    @Override
    public List<AvailableCurrencyResponse> getAvailableCurrenciesList() {
        return availableCurrencies;
    }

    @Override
    public void updateCurrencies(){
        List<Long> idList = findAllIds();
        List<ExternalApiResponse> fetchedInfo = idList.stream()
                .map(externalApiService::fetchActualCurrencyPrice)
                .toList();
        fetchedInfo.forEach(e -> cryptoCurrencyrepository.save(new CryptoCurrency(e.id(), e.price(), e.symbol())));
    }

    private List<Long> findAllIds() {
        return cryptoCurrencyrepository.findAllIds();
    }

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

}
