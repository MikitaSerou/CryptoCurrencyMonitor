package com.serov.cryptocurrencymonitor.scheduling;

import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import com.serov.cryptocurrencymonitor.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final CryptoCurrencyService service;

    @Scheduled(fixedRateString = "${scheduler.rate}")
    public void updateInformation() {
      log.warn("Currencies info updating");
        service.updateCurrencies();
    }
}
