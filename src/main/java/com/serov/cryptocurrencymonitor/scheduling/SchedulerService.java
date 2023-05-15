package com.serov.cryptocurrencymonitor.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

    @Scheduled(fixedRateString = "${scheduler.rate}")
    public void updateInformation() {
      log.warn("Info updating");

    }
}
