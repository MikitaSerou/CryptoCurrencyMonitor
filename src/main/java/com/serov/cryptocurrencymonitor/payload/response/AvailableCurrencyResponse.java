package com.serov.cryptocurrencymonitor.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Presentation of information about available cryptocurrencies for monitoring and subscription.")
public record AvailableCurrencyResponse(@Schema(description = "Currency id")
                                        Long id,
                                        @Schema(description = "The abbreviation used to indicate a currency and also " +
                                                "for sending subscription request")
                                        String symbol) {
}
