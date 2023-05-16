package com.serov.cryptocurrencymonitor.repository;

import com.serov.cryptocurrencymonitor.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUsernameAndCurrency_Symbol(String username, String symbol);
}