package com.serov.cryptocurrencymonitor.payload.request;

import jakarta.validation.constraints.NotBlank;

public record SubscriptionDto(@NotBlank(message = "Username is required") String username,
                              @NotBlank(message = "Symbol is required") String symbol) {
}
