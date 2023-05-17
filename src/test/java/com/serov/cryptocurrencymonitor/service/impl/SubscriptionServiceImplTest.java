package com.serov.cryptocurrencymonitor.service.impl;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import com.serov.cryptocurrencymonitor.entity.Subscription;
import com.serov.cryptocurrencymonitor.payload.request.SubscriptionDto;
import com.serov.cryptocurrencymonitor.repository.SubscriptionRepository;
import com.serov.cryptocurrencymonitor.service.CryptoCurrencyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Testing Subscription service")
@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class SubscriptionServiceImplTest {

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    private SubscriptionServiceImpl subscriptionService;

    @Test
    @DisplayName("Testing the subscription process")
    void testSubscribeUserToCurrency() {
        SubscriptionDto dto = new SubscriptionDto("user1", "BTC");
        CryptoCurrency currency = new CryptoCurrency(1L, 50000.0, "BTC");

        when(cryptoCurrencyService.findBySymbol(dto.symbol())).thenReturn(currency);
        when(subscriptionRepository.findByUsernameAndCurrency_Symbol(dto.username(), dto.symbol())).thenReturn(null);

        Subscription result = subscriptionService.subscribeUserToCurrency(dto);

        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
        assertEquals(dto.username(), result.getUsername());
        assertEquals(currency, result.getCurrency());
    }

    @Test
    @DisplayName("The logging of subscription notifications")
    void testNotifyUsersSubscriptionsPriceChange(CapturedOutput output) {
        CryptoCurrency currency = new CryptoCurrency(1L, 5000.0, "BTC");

        Subscription subscription1 = new Subscription(2050.5, "JohnDoe", currency);
        Subscription subscription2 = new Subscription(2050.6, "Karasik", currency);

        List<Subscription> subscriptionsList = Arrays.asList(subscription1, subscription2);

        when(subscriptionRepository.findAll()).thenReturn(subscriptionsList);

        subscriptionService.notifyUsersSubscriptionsPriceChange();

        assertTrue(output.toString().contains("Price change for currency BTC exceeded 1%. User: JohnDoe. Price difference: 143,843%"));
        assertTrue(output.toString().contains("Price change for currency BTC exceeded 1%. User: Karasik. Price difference: 143,831%"));
    }
}