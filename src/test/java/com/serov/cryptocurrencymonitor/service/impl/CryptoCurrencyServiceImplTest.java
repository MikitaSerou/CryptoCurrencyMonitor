package com.serov.cryptocurrencymonitor.service.impl;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.exception.CurrencyException;
import com.serov.cryptocurrencymonitor.payload.ExternalApiResponse;
import com.serov.cryptocurrencymonitor.payload.response.AvailableCurrencyResponse;
import com.serov.cryptocurrencymonitor.repository.CryptoCurrencyRepository;
import com.serov.cryptocurrencymonitor.service.ExternalApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CryptoCurrencyServiceImplTest {

    @Autowired
    private CryptoCurrencyServiceImpl cryptoCurrencyService;

    @MockBean
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @MockBean
    private ExternalApiService externalApiService;

    @Test
    void testGetAvailableCurrenciesList() {
        List<AvailableCurrencyResponse> expectedList = Arrays.asList(
                new AvailableCurrencyResponse(90L, "BTC"),
                new AvailableCurrencyResponse(80L, "ETH"),
                new AvailableCurrencyResponse(48543L, "SOL")
        );

        List<AvailableCurrencyResponse> actualList = cryptoCurrencyService.getAvailableCurrenciesList();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testUpdateCurrencies() {
        List<Long> idList = List.of(1L, 2L, 3L);
        List<ExternalApiResponse> fetchedInfo = List.of(
                new ExternalApiResponse(1L, "BTC", 50000.0),
                new ExternalApiResponse(2L, "ETH", 2000.0),
                new ExternalApiResponse(3L, "XRP", 1.5)
        );

        when(cryptoCurrencyRepository.findAllIds()).thenReturn(idList);
        when(externalApiService.fetchActualCurrencyPrice(anyLong()))
                .thenReturn(fetchedInfo.get(0), fetchedInfo.get(1), fetchedInfo.get(2));

        cryptoCurrencyService.updateCurrencies();

        verify(cryptoCurrencyRepository, times(3)).save(any(CryptoCurrency.class));
    }

    @Test
    void testFindAll() {
        List<CryptoCurrency> expectedCurrencies = List.of(
                new CryptoCurrency(1L, 50000.0, "BTC"),
                new CryptoCurrency(2L, 2000.0, "ETH"),
                new CryptoCurrency(3L, 1.5, "XRP")
        );

        when(cryptoCurrencyRepository.findAll()).thenReturn(expectedCurrencies);

        List<CryptoCurrency> actualCurrencies = cryptoCurrencyService.findAll();

        assertEquals(expectedCurrencies, actualCurrencies);
    }

    @Test
    void testGetById() {
        Long currencyId = 90L;
        CryptoCurrency expectedCurrency = new CryptoCurrency(currencyId, 50000.0, "BTC");

        when(cryptoCurrencyRepository.findById(currencyId)).thenReturn(Optional.of(expectedCurrency));

        CryptoCurrency actualCurrency = cryptoCurrencyService.getById(currencyId);

        assertEquals(expectedCurrency, actualCurrency);
    }

    @Test
    void testFindBySymbol() {
        String symbol = "BTC";
        CryptoCurrency expectedCurrency = new CryptoCurrency(90L, 50000.0, symbol);

        when(cryptoCurrencyRepository.findBySymbol(symbol)).thenReturn(Optional.of(expectedCurrency));

        CryptoCurrency actualCurrency = cryptoCurrencyService.findBySymbol(symbol);

        assertEquals(expectedCurrency, actualCurrency);
    }

    @Test
    public void testGetByIdThrowsCurrencyExceptionWhenCurrencyNotFound() {
        Long id = 1L;
        when(cryptoCurrencyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CurrencyException.class, () -> cryptoCurrencyService.getById(id));
    }

    @Test
    public void testFindBySymbolThrowsCurrencyExceptionWhenCurrencyNotFound() {
        String symbol = "LOL";
        when(cryptoCurrencyRepository.findBySymbol(symbol)).thenReturn(Optional.empty());

        assertThrows(CurrencyException.class, () -> cryptoCurrencyService.findBySymbol(symbol));
    }
}