package com.serov.cryptocurrencymonitor.service;

import com.serov.cryptocurrencymonitor.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> findAll();
}
