package com.serov.cryptocurrencymonitor.repository;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
}
