package com.serov.cryptocurrencymonitor.service;

import com.serov.cryptocurrencymonitor.payload.ExternalApiResponse;

public interface ExternalApiService {
    ExternalApiResponse fetchActualCurrencyPrice(Long id);
}
