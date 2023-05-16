package com.serov.cryptocurrencymonitor.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serov.cryptocurrencymonitor.exception.CurrencyException;
import com.serov.cryptocurrencymonitor.payload.ExternalApiResponse;
import com.serov.cryptocurrencymonitor.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalApiServiceImpl implements ExternalApiService {

    private final RestTemplate restTemplate;
    @Value("${api.url}")
    private String API_URL;
    private final Gson gson = new GsonBuilder().create();

    @Override
    public ExternalApiResponse fetchActualCurrencyPrice(Long id) throws CurrencyException {
        String requestUrl = String.format(API_URL, id);
        ResponseEntity<String> response = executeRequest(requestUrl);
        log.debug("Fetched data from external api: {}", response.getBody());
        String jsonResult = getJsonResult(response);
        return parseApiResponse(jsonResult);
    }

    private ResponseEntity<String> executeRequest(String requestUrl) {
        try {
            return restTemplate.getForEntity(requestUrl, String.class);
        } catch (RestClientException e) {
            log.error("Error while trying to fetch external api data: ", e);
            throw new RuntimeException("Error while trying to fetch external api data: " + e.getMessage());
        }
    }

    private String getJsonResult(ResponseEntity<String> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Error while trying to fetch external api data. Status code: " + response.getStatusCode());
            throw new RuntimeException("Error while trying to fetch external api data. Status code: " + response.getStatusCode());
        }
        String jsonResult = response.getBody();
        if (jsonResult == null || jsonResult.isEmpty()) {
            log.error("Empty response body while trying to fetch external api data.");
            throw new CurrencyException("Empty response body while trying to fetch external api data.");
        }
        return jsonResult;
    }

    private ExternalApiResponse parseApiResponse(String jsonResult) {
        return Arrays.stream(gson.fromJson(jsonResult, ExternalApiResponse[].class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't fetch api data"));
    }
}
