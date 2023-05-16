package com.serov.cryptocurrencymonitor.controller;

import com.serov.cryptocurrencymonitor.entity.Subscription;
import com.serov.cryptocurrencymonitor.payload.request.SubscriptionDto;
import com.serov.cryptocurrencymonitor.payload.response.ResponseMessage;
import com.serov.cryptocurrencymonitor.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/notify")
    public ResponseMessage subscribe(@Valid @RequestBody SubscriptionDto dto) {
        log.debug("User {} subscribing to currency {}", dto.username(), dto.symbol());
        Subscription subscription = subscriptionService.subscribeUserToCurrency(dto);
        return new ResponseMessage("User " + subscription.getUsername() +
                " was subscribed to currency " + dto.symbol() +
                ". Current price: " + subscription.getSubscribedPrice() + " (usd).");
    }

}
