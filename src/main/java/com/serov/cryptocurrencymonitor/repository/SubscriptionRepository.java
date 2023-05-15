package com.serov.cryptocurrencymonitor.repository;

import com.serov.cryptocurrencymonitor.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}