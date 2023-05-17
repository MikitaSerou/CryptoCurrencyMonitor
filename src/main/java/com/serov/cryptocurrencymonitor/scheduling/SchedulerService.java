package com.serov.cryptocurrencymonitor.scheduling;

import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import com.serov.cryptocurrencymonitor.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final SubscriptionService subscriptionService;

    @Scheduled(cron = "${scheduler.cron}")
    public void updateInformation() {
        log.info("Currencies info updating");
        cryptoCurrencyService.updateCurrencies();
        subscriptionService.notifyUsersSubscriptionsPriceChange();
    }
}
