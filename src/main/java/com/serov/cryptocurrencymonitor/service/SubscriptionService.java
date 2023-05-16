package com.serov.cryptocurrencymonitor.service;

import com.serov.cryptocurrencymonitor.entity.Subscription;
import com.serov.cryptocurrencymonitor.payload.request.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    Subscription subscribeUserToCurrency(SubscriptionDto dto);

    List<Subscription> findAll();
}
