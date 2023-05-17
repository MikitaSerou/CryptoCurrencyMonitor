package com.serov.cryptocurrencymonitor.payload;

import com.google.gson.annotations.SerializedName;

public record ExternalApiResponse(Long id, String symbol, @SerializedName("price_usd") Double price) {
}
