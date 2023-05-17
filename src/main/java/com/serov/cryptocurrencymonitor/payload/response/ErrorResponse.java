package com.serov.cryptocurrencymonitor.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The object returned in case of an exception")
public record ErrorResponse(@Schema(description = "Short internal error name for developers")
                            String errorName,
                            @Schema(description = "Short description of the error with its cause")
                            String message) {
}
