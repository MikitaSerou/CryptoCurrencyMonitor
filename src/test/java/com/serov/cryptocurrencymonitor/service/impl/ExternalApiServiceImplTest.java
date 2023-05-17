package com.serov.cryptocurrencymonitor.service.impl;

import com.serov.cryptocurrencymonitor.exception.CurrencyException;
import com.serov.cryptocurrencymonitor.payload.ExternalApiResponse;
import com.serov.cryptocurrencymonitor.service.ExternalApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Testing fetching data from external api")
@ExtendWith(MockitoExtension.class)
public class ExternalApiServiceImplTest {

    @Value("${api.url}")
    private String apiUrl;

    @Autowired
    private ExternalApiService externalApiService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @DisplayName("Test for successfully fetching")
    public void testFetchActualCurrencyPriceSuccess() {
        Long currencyId = 123L;
        String jsonResponse = "[{ \"id\": 123, \"symbol\": \"Bitcoin\", \"price_usd\": \"50000.0\" }]";
        ExternalApiResponse expectedResponse = new ExternalApiResponse(123L, "Bitcoin", 50000.0);

        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(String.format(apiUrl, currencyId), String.class))
                .thenReturn(responseEntity);
        ExternalApiResponse result = externalApiService.fetchActualCurrencyPrice(currencyId);

        assertEquals(expectedResponse, result);
        verify(restTemplate, times(1)).getForEntity(String.format(apiUrl, currencyId), String.class);
    }

    @Test
    @DisplayName("Test for throwing an exception when RestClientException occurs")
    public void testFetchActualCurrencyPriceErrorRestClientException() {
        Long currencyId = 123L;

        when(restTemplate.getForEntity(String.format(apiUrl, currencyId), String.class))
                .thenThrow(new RestClientException("Connection refused"));

        assertThrows(RuntimeException.class,
                () -> externalApiService.fetchActualCurrencyPrice(currencyId));

        verify(restTemplate, times(1)).getForEntity(String.format(apiUrl, currencyId), String.class);
    }

    @Test
    @DisplayName("Test for throwing an exception when non-OK status")
    public void testFetchActualCurrencyPriceErrorStatusCode() {
        Long currencyId = 123L;
        String emptyResponseBody = "";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(emptyResponseBody, HttpStatus.BAD_REQUEST);
        when(restTemplate.getForEntity(String.format(apiUrl, currencyId), String.class))
                .thenReturn(responseEntity);

        assertThrows(RuntimeException.class,
                () -> externalApiService.fetchActualCurrencyPrice(currencyId));

        verify(restTemplate, times(1)).getForEntity(String.format(apiUrl, currencyId), String.class);
    }

    @Test
    @DisplayName("Test for throwing an exception when the response body is empty")
    public void testFetchActualCurrencyPriceEmptyResponseBody() {
        Long currencyId = 123L;
        String jsonResponse = "";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(String.format(apiUrl, currencyId), String.class))
                .thenReturn(responseEntity);

        assertThrows(CurrencyException.class,
                () -> externalApiService.fetchActualCurrencyPrice(currencyId));

        verify(restTemplate, times(1)).getForEntity(String.format(apiUrl, currencyId), String.class);
    }

    @Test
    @DisplayName("Test for throwing an exception when the fetched array is empty")
    public void testFetchActualCurrencyPriceEmptyApiResponse() {
        Long currencyId = 123L;
        String jsonResponse = "[]";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(String.format(apiUrl, currencyId), String.class))
                .thenReturn(responseEntity);

        assertThrows(RuntimeException.class,
                () -> externalApiService.fetchActualCurrencyPrice(currencyId));

        verify(restTemplate, times(1)).getForEntity(String.format(apiUrl, currencyId), String.class);
    }
}