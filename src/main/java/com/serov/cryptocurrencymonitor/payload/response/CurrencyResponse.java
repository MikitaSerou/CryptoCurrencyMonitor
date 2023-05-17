package com.serov.cryptocurrencymonitor.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Cryptocurrency representation")
public record CurrencyResponse(@Schema(description = "Identifier")
                               Long id,
                               @Schema(description = "Current price")
                               Double price,
                               @Schema(description = "Currency symbol (BTC, ETH, SOL)")
                               String symbol) {
}
