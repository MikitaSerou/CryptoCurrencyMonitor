package com.serov.cryptocurrencymonitor.repository;

import com.serov.cryptocurrencymonitor.entity.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    @Query("SELECT c.id FROM CryptoCurrency c")
    List<Long> findAllIds();
}
