package com.serov.cryptocurrencymonitor.service.impl;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.entity.Subscription;
import com.serov.cryptocurrencymonitor.payload.request.SubscriptionDto;
import com.serov.cryptocurrencymonitor.repository.SubscriptionRepository;
import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import com.serov.cryptocurrencymonitor.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CryptoCurrencyService cryptoCurrencyService;

    @Override
    @Transactional
    public Subscription subscribeUserToCurrency(SubscriptionDto dto) {
        CryptoCurrency currency = cryptoCurrencyService.findBySymbol(dto.symbol());
        Subscription subscription = findUserCurrentSubscription(dto);
        subscription = (subscription != null) ?
                updateSubscription(currency, subscription) :
                new Subscription(currency.getPrice(), dto.username(), currency);
        subscriptionRepository.save(subscription);
        log.debug("User {}, was subscribed to {}", dto.username(), dto.symbol());
        return subscription;
    }

    private Subscription updateSubscription(CryptoCurrency currency, Subscription subscription) {
        log.debug("Update subscription for user {} to currency {}", subscription.getUsername(), currency.getSymbol());
        subscription.setCurrency(currency);
        subscription.setSubscribedPrice(currency.getPrice());
        return subscription;
    }

    private Subscription findUserCurrentSubscription(SubscriptionDto dto) {
        return subscriptionRepository.findByUsernameAndCurrency_Symbol(dto.username(), dto.symbol());
    }

    @Override
    @Transactional
    public void notifyUsersSubscriptionsPriceChange() {
        List<Subscription> subscriptionsList = subscriptionRepository.findAll();
        subscriptionsList.forEach(this::processSubscription);
    }

    private void processSubscription(Subscription subscription) {
        CryptoCurrency currentCurrencyState = subscription.getCurrency();
        double priceDifferencePercentage = calculatePriceDifferencePercentage(
                currentCurrencyState.getPrice(),
                subscription.getSubscribedPrice());
        if (priceDifferencePercentage > 1.0) {
            log.warn(String.format("Price change for currency %s exceeded 1%%. User: %s. Price difference: %.3f%%",
                    currentCurrencyState.getSymbol(), subscription.getUsername(), priceDifferencePercentage));
        }
    }

    private double calculatePriceDifferencePercentage(Double currentPrice, Double subscribedPrice) {
        return Math.abs((currentPrice - subscribedPrice) / subscribedPrice * 100);
    }

}
