package com.serov.cryptocurrencymonitor.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Json request object for subscription")
public record SubscriptionDto(@Schema(description = "Username for subscription")
                              @NotBlank(message = "Username is required")
                              String username,
                              @Schema(description = "Currency symbol (BTC, ETH, SOL)")
                              @NotBlank(message = "Symbol is required") String symbol) {
}
