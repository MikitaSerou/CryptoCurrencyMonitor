package com.serov.cryptocurrencymonitor.service;

import com.serov.cryptocurrencymonitor.entity.Subscription;
import com.serov.cryptocurrencymonitor.payload.request.SubscriptionDto;

public interface SubscriptionService {
    Subscription subscribeUserToCurrency(SubscriptionDto dto);

    void notifyUsersSubscriptionsPriceChange();
}
