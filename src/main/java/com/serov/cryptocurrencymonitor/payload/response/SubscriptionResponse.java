package com.serov.cryptocurrencymonitor.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Server response to a subscription request")
public record SubscriptionResponse(@Schema(description = "Subscription status info") String result) {
}
