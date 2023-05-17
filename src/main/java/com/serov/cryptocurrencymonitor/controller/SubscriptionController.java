package com.serov.cryptocurrencymonitor.controller;

import com.serov.cryptocurrencymonitor.entity.Subscription;
import com.serov.cryptocurrencymonitor.payload.request.SubscriptionDto;
import com.serov.cryptocurrencymonitor.payload.response.SubscriptionResponse;
import com.serov.cryptocurrencymonitor.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/subscription")
@Tag(name = "Subscription", description = "Subscription manipulations endpoints")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Create or update existed subscription",
            description = "Endpoint for creating a subscription." +
                    " If the user is already subscribed to a currency with the provided name," +
                    " update the subscription with the current price")
    @PostMapping("/notify")
    public SubscriptionResponse subscribe(@Valid @RequestBody SubscriptionDto dto) {
        log.debug("User {} subscribing to currency {}", dto.username(), dto.symbol());
        Subscription subscription = subscriptionService.subscribeUserToCurrency(dto);
        return new SubscriptionResponse("User " + subscription.getUsername() +
                " was subscribed to currency " + dto.symbol() +
                ". Current price: " + subscription.getSubscribedPrice() + " (usd).");
    }

}
