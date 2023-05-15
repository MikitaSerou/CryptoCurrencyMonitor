package com.serov.cryptocurrencymonitor.controller;

import com.serov.cryptocurrencymonitor.payload.ResponseMessage;
import com.serov.cryptocurrencymonitor.payload.SubscriptionDto;
import com.serov.cryptocurrencymonitor.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/notify")
    public ResponseMessage subscribe(@RequestBody SubscriptionDto dto){
        log.debug("User {} subscribed to currency {}", dto.username(), dto.symbol());
        return new ResponseMessage("OK");
    }

}
