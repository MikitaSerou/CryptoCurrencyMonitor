package com.serov.cryptocurrencymonitor.repository;

import com.serov.cryptocurrencymonitor.model.CryptoCurrency;
import org.springframework.data.repository.CrudRepository;

public interface CryptoCurrencyRepository extends CrudRepository<CryptoCurrency, Long> {
}
